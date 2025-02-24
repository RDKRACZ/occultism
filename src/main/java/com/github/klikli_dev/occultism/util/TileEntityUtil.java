/*
 * MIT License
 *
 * Copyright 2020 klikli-dev
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT
 * OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package com.github.klikli_dev.occultism.util;

import com.github.klikli_dev.occultism.api.common.data.GlobalBlockPos;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

import javax.annotation.Nullable;

public class TileEntityUtil {
    //region Static Methods

    /**
     * Gets the tile entity from the given global position.
     *
     * @param world the world. Returns null if the dimension is unloaded.
     * @param pos   the global position.
     * @return the tile entity or null.
     */
    public static TileEntity get(World world, GlobalBlockPos pos) {
        if (pos == null)
            return null;

        if (world.dimension() == pos.getDimensionKey()) {
            return getWorldTileEntityUnchecked(world, pos.getPos());
        }
        if (world.isClientSide) //can only access other dimensions on the server.
            return null;

        World dimensionWorld = ServerLifecycleHooks.getCurrentServer().getLevel(pos.getDimensionKey());
        if (dimensionWorld != null)
            return getWorldTileEntityUnchecked(dimensionWorld, pos.getPos());

        return null;
    }

    @Nullable
    /**
     * Copied from org.cyclops.cyclopscore.helper.TileHelpers
     * Allows IntegratedDynamics and addons to safely interact with Occultism
     * @author rubensworks
     */
    public static TileEntity getWorldTileEntityUnchecked(World world, BlockPos pos) {
        if (World.isOutsideBuildHeight(pos)) {
            return null;
        } else {
            TileEntity tileentity = null;
            if (world.updatingBlockEntities) {
                tileentity = world.getPendingBlockEntityAt(pos);
            }

            if (tileentity == null) {
                tileentity = world.getChunkAt(pos).getBlockEntity(pos, Chunk.CreateEntityType.IMMEDIATE);
            }

            if (tileentity == null) {
                tileentity = world.getPendingBlockEntityAt(pos);
            }

            return tileentity;
        }
    }


    /**
     * Updates the tile entity at the given position (mark dirty & send updates)
     *
     * @param world the world to update
     * @param pos   the position to update
     */
    public static void updateTile(World world, BlockPos pos) {
        if (world == null || world.isClientSide || !world.hasChunkAt(pos)) {
            return;
        }

        BlockState state = world.getBlockState(pos);
        world.sendBlockUpdated(pos, state, state, 2);
        world.blockEntityChanged(pos, world.getBlockEntity(pos));
    }

    /**
     * Checks all faces of a tile entity for the given capability.
     *
     * @param tileEntity the tile entity to check.
     * @param capability the capability to check for.
     * @return true if the capability is found on any face.
     */
    public static boolean hasCapabilityOnAnySide(TileEntity tileEntity, Capability<?> capability) {
        for (Direction face : Direction.values()) {
            if (tileEntity.getCapability(capability, face).isPresent())
                return true;
        }
        return false;
    }

    /**
     * Creates the item entity with nbt from the tile entity.
     * Default pickup delay is set.
     *
     * @param itemStack  the stack to drop.
     * @param tileEntity the tile entity to get nbt from.
     * @return the item entity.
     */
    public static ItemEntity getDroppedItemWithNbt(ItemStack itemStack, TileEntity tileEntity) {
        CompoundNBT compoundnbt = tileEntity.serializeNBT();
        if (!compoundnbt.isEmpty()) {
            itemStack.addTagElement("BlockEntityTag", compoundnbt);
        }
        ItemEntity itementity =
                new ItemEntity(tileEntity.getLevel(), tileEntity.getBlockPos().getX(), tileEntity.getBlockPos().getY(),
                        tileEntity.getBlockPos().getZ(), itemStack);
        itementity.setDefaultPickUpDelay();
        return itementity;
    }

    /**
     * Handles the common use case of dropping self with tile entity nbt on block change during replace.
     *
     * @param block    the current block.
     * @param state    the old state.
     * @param world    the world
     * @param pos      the position.
     * @param newState the new state
     */
    public static void onBlockChangeDropWithNbt(Block block, BlockState state, World world, BlockPos pos,
                                                BlockState newState) {
        if (state.getBlock() != newState.getBlock()) {
            if (!world.isClientSide) {
                TileEntity tile = world.getBlockEntity(pos);
                if (tile != null) {
                    world.addFreshEntity(TileEntityUtil.getDroppedItemWithNbt(new ItemStack(block), tile));
                }
            }
            world.updateNeighbourForOutputSignal(pos, block);
        }
    }

    /**
     * Handles the common use case of giving self as item with tile entity nbt.
     *
     * @param block the current block.
     * @param world the world
     * @param pos   the position.
     */
    public static ItemStack getItemWithNbt(Block block, IBlockReader world, BlockPos pos) {
        ItemStack itemStack = new ItemStack(block);
        TileEntity tileEntity = world.getBlockEntity(pos);
        CompoundNBT compoundnbt = tileEntity.serializeNBT();
        if (!compoundnbt.isEmpty()) {
            itemStack.addTagElement("BlockEntityTag", compoundnbt);
        }

        return itemStack;
    }
    //endregion Static Methods
}
