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

package com.github.klikli_dev.occultism.common.block.crops;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.List;

public interface IReplantableCrops {
    //region Fields
    float EXHAUSTION_PER_HARVEST = 0.005f;
    //endregion Fields

    //region Getter / Setter
    IItemProvider getCropsItem();

    IItemProvider getSeedsItem();
    //endregion Getter / Setter

    //region Overrides

    //endregion Getter / Setter
    //region Methods
    default ActionResultType onHarvest(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand) {
        if (!world.isClientSide) {
            if (state.getValue(CropsBlock.AGE) >= 7) {
                List<ItemStack> drops = Block.getDrops(state, (ServerWorld) world, pos, null, player,
                        player.getItemInHand(hand));

                // From 1.15 -> does not exist any more and I guess we don't need it.
                //                ForgeEventFactory.fireBlockHarvesting(
                //                        NonNullList.from(ItemStack.EMPTY, drops.toArray(new ItemStack[0])), world, pos, state,
                //                        0, 1.0F, false, player);

                //reset crop
                world.setBlockAndUpdate(pos, state.setValue(CropsBlock.AGE, 0));
                for (ItemStack stack : drops) {
                    InventoryHelper.dropItemStack(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, stack);
                }

                player.swing(hand);
                player.causeFoodExhaustion(EXHAUSTION_PER_HARVEST);

                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.PASS;
    }
    //endregion Methods
}
