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

package com.github.klikli_dev.occultism.common.job;

import com.github.klikli_dev.occultism.api.common.container.IItemStackComparator;
import com.github.klikli_dev.occultism.common.entity.ai.DepositItemsGoal;
import com.github.klikli_dev.occultism.common.entity.ai.FellTreesGoal;
import com.github.klikli_dev.occultism.common.entity.ai.PickupItemsGoal;
import com.github.klikli_dev.occultism.common.entity.ai.ReplantSaplingGoal;
import com.github.klikli_dev.occultism.common.entity.spirit.SpiritEntity;
import com.github.klikli_dev.occultism.common.misc.ItemStackComparator;
import com.github.klikli_dev.occultism.common.misc.ItemTagComparator;
import com.github.klikli_dev.occultism.registry.OccultismTags;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.LongNBT;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LumberjackJob extends SpiritJob {

    //region Fields
    protected ReplantSaplingGoal replantSaplingGoal;
    protected PickupItemsGoal pickupItemsGoal;
    protected FellTreesGoal fellTreesGoal;
    protected DepositItemsGoal depositItemsGoal;
    protected List<IItemStackComparator> itemsToPickUp = new ArrayList<>();
    private Set<BlockPos> ignoredTrees = new HashSet<>();
    private BlockPos lastFelledTree = null;
    //endregion Fields


    //region Initialization
    public LumberjackJob(SpiritEntity entity) {
        super(entity);
    }
    //endregion Initialization

    //region Overrides
    @Override
    public void init() {
        this.entity.goalSelector.addGoal(0, this.pickupItemsGoal = new PickupItemsGoal(this.entity));
        this.entity.goalSelector.addGoal(2, this.fellTreesGoal = new FellTreesGoal(this.entity));
        this.entity.goalSelector.addGoal(3, this.replantSaplingGoal = new ReplantSaplingGoal(this.entity));
        this.entity.goalSelector.addGoal(4, this.depositItemsGoal = new DepositItemsGoal(this.entity));

        this.itemsToPickUp.add(new ItemTagComparator(ItemTags.LOGS));
        this.itemsToPickUp.add(new ItemTagComparator(ItemTags.LEAVES));
        this.itemsToPickUp.add(new ItemTagComparator(ItemTags.SAPLINGS));
        this.itemsToPickUp.add(new ItemTagComparator(OccultismTags.FRUITS));
        this.itemsToPickUp.add(new ItemStackComparator(new ItemStack(Items.STICK), false));
    }

    @Override
    public void cleanup() {
        this.entity.targetSelector.removeGoal(this.replantSaplingGoal);
        this.entity.targetSelector.removeGoal(this.pickupItemsGoal);
        this.entity.goalSelector.removeGoal(this.fellTreesGoal);
        this.entity.goalSelector.removeGoal(this.depositItemsGoal);
    }

    @Override
    public boolean canPickupItem(ItemEntity entity) {

        ItemStack stack = entity.getItem();
        for (IItemStackComparator comparator : this.itemsToPickUp) {
            if (comparator.matches(stack))
                return true;
        }
        return false;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT compound = super.serializeNBT();
        ListNBT list = new ListNBT();
        for (BlockPos ignoredTree : this.ignoredTrees) {
            list.add(LongNBT.valueOf(ignoredTree.asLong()));
        }
        compound.put("ignoredTrees", list);
        return compound;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        super.deserializeNBT(nbt);

        this.ignoredTrees = new HashSet<>();
        if (nbt.contains("ignoredTrees")) {
            ListNBT list = nbt.getList("ignoredTrees", Constants.NBT.TAG_LIST);
            for (int i = 0; i < list.size(); i++) {
                this.ignoredTrees.add(BlockPos.of(((LongNBT) list.get(i)).getAsLong()));
            }
        }
    }

    public Set<BlockPos> getIgnoredTrees() {
        return this.ignoredTrees;
    }

    public void setIgnoredTrees(Set<BlockPos> ignoredTrees) {
        this.ignoredTrees = ignoredTrees;
    }

    public BlockPos getLastFelledTree() {
        return this.lastFelledTree;
    }

    public void setLastFelledTree(BlockPos lastFelledTree) {
        this.lastFelledTree = lastFelledTree;
    }

    //endregion Overrides
}
