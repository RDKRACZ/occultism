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

package com.github.klikli_dev.occultism.common.item.tool;

import com.github.klikli_dev.occultism.common.block.ChalkGlyphBlock;
import com.github.klikli_dev.occultism.registry.OccultismSounds;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Supplier;

public class ChalkItem extends Item {
    //region Fields
    Supplier<ChalkGlyphBlock> glyphBlock;
//endregion Fields

    //region Initialization
    public ChalkItem(Properties properties, Supplier<ChalkGlyphBlock> glyphBlock) {
        super(properties);
        this.glyphBlock = glyphBlock;
    }

    //endregion Initialization

    //region Overrides


    @Override
    public ActionResultType useOn(ItemUseContext context) {
        World world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = world.getBlockState(pos);
        PlayerEntity player = context.getPlayer();
        boolean isReplacing = world.getBlockState(pos).getBlock()
                .canBeReplaced(state, new BlockItemUseContext(context));

        if (!world.isClientSide) {
            //only place if player clicked at a top face
            //only if the block can be placed or is replacing an existing block
            if ((context.getClickedFace() == Direction.UP
                    && this.glyphBlock.get().canSurvive(world.getBlockState(pos.above()), world, pos.above())) || isReplacing) {
                ItemStack heldChalk = context.getItemInHand();
                BlockPos placeAt = isReplacing ? pos : pos.above();

                world.setBlockAndUpdate(placeAt,
                        this.glyphBlock.get().getStateForPlacement(new BlockItemUseContext(context)));

                world.playSound(null, pos, OccultismSounds.CHALK.get(), SoundCategory.PLAYERS, 0.5f,
                        1 + 0.5f * player.getRandom().nextFloat());

                if (!player.isCreative())
                    heldChalk.hurtAndBreak(1, player, t -> {
                    });
            }
        }
        return ActionResultType.SUCCESS;
    }

    //endregion Overrides

}
