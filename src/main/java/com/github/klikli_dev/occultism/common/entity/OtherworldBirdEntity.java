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

package com.github.klikli_dev.occultism.common.entity;

import com.github.klikli_dev.occultism.Occultism;
import com.github.klikli_dev.occultism.registry.OccultismEffects;
import com.github.klikli_dev.occultism.registry.OccultismItems;
import com.google.common.collect.ImmutableList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.UUID;

public class OtherworldBirdEntity extends ParrotEntity implements IFamiliar {

    // region Fields
    public static final float MAX_BOOST_DISTANCE = 8f;

    public SitGoal sitGoal;
    // endregion Fields

    // region Initialization
    public OtherworldBirdEntity(EntityType<? extends ParrotEntity> type, World worldIn) {
        super(type, worldIn);
    }
    // endregion Initialization

    //region Static Methods
    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return ParrotEntity.createAttributes(); // =registerAttributes
    }
    // endregion Getter / Setter

    // region Overrides

    // region Getter / Setter

    @Override
    protected void registerGoals() {
        // same as parrot, except we don't land on shoulders.
        this.sitGoal = new SitGoal(this);
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(2, this.sitGoal);
        this.goalSelector.addGoal(2, new FollowOwnerGoal(this, 1.0D, 5.0F, 1.0F, true));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomFlyingGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new FollowMobGoal(this, 1.0D, 3.0F, 7.0F));
    }

    @Override
    public void aiStep() {
        // Every 10 ticks, attempt to refresh the owner buff
        if (!this.level.isClientSide && this.level.getGameTime() % 10 == 0 && this.isTame()) {
            LivingEntity owner = this.getOwner();
            if (owner != null && this.distanceTo(owner) < MAX_BOOST_DISTANCE) {
                // close enough to boost
                for (EffectInstance effect : this.getFamiliarEffects())
                    owner.addEffect(effect);
            }
        }

        super.aiStep();
    }

    @Override
    public void setOwnerUUID(@Nullable UUID ownerId) {
        super.setOwnerUUID(ownerId);
    }

    @Override
    public LivingEntity getFamiliarOwner() {
        return this.getOwner();
    }

    @Override
    public void setFamiliarOwner(LivingEntity owner) {
        this.setOwnerUUID(owner.getUUID());
    }

    @Override
    public Entity getEntity() {
        return this;
    }

    @Override
    public Iterable<EffectInstance> getFamiliarEffects() {

        //only provide effect if enabled
        if (this.isEffectEnabled()) {
            return ImmutableList.of(new EffectInstance(Effects.JUMP, 60, 5, false, false),
                    new EffectInstance(Effects.SLOW_FALLING,
                            20 * Occultism.SERVER_CONFIG.spiritJobs.drikwingFamiliarSlowFallingSeconds.get(), 0, false,
                            false),
                    new EffectInstance(OccultismEffects.DOUBLE_JUMP.get(), 120, 4, false, false));

        }
        return Collections.emptyList();
    }
    // endregion Overrides

    @Override
    public ActionResultType mobInteract(PlayerEntity playerIn, Hand hand) {
        ItemStack stack = playerIn.getItemInHand(hand);
        if (stack.getItem() == OccultismItems.FAMILIAR_RING.get()) {
            return stack.interactLivingEntity(playerIn, this, hand);
        }
        return super.mobInteract(playerIn, hand);
    }
//endregion Static Methods
}
