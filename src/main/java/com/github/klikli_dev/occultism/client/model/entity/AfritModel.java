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

package com.github.klikli_dev.occultism.client.model.entity;

import com.github.klikli_dev.occultism.common.entity.spirit.AfritEntity;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class AfritModel extends BipedModel<AfritEntity> {
    //region Fields
    public ModelRenderer nose;
    public ModelRenderer earLeft;
    public ModelRenderer earRight;
    public ModelRenderer wingedWings;
    public ModelRenderer wingedWingsLeft;
    public ModelRenderer wingedWingsRight;

    //endregion Fields

    //region Initialization
    public AfritModel() {
        super(1.0f);
        this.leftArmPose = ArmPose.EMPTY;
        this.rightArmPose = ArmPose.EMPTY;
        this.texWidth = 64;
        this.texHeight = 64;

        this.head = new ModelRenderer(this, 0, 0);
        this.head.setPos(0.0F, 0.0F, 0.0F);
        this.head.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.0F);

        this.hat = new ModelRenderer(this, 1, 45);
        this.hat.setPos(0.0F, -3.0F, 0.0F);
        this.hat.addBox(-4.0F, -8.0F, -4.0F, 8, 10, 8, 0.5F);

        this.earLeft = new ModelRenderer(this, 0, 0);
        this.earLeft.setPos(4.0F, -11.3F, 3.5F);
        this.earLeft.addBox(-0.5F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(this.earLeft, -0.5009094953223726F, 0.0F, 0.0F);

        this.earRight = new ModelRenderer(this, 0, 0);
        this.earRight.setPos(-4.0F, -11.3F, 3.5F);
        this.earRight.addBox(-0.5F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(this.earRight, -0.5009094953223726F, 0.0F, 0.0F);

        this.nose = new ModelRenderer(this, 24, 0);
        this.nose.setPos(0.0F, -5.0F, -4.4F);
        this.nose.addBox(-1.0F, 0.0F, 0.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(this.nose, -0.4553564018453205F, 0.0F, 0.0F);

        this.leftArm = new ModelRenderer(this, 44, 22);
        this.leftArm.setPos(5.0F, 3.0F, -1.0F);
        this.leftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 11, 4, 0.0F);
        this.setRotateAngle(this.leftArm, -0.7499679795819634F, 0.0F, 0.0F);

        this.rightArm = new ModelRenderer(this, 44, 22);
        this.rightArm.setPos(-5.0F, 3.0F, -1.0F);
        this.rightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(this.rightArm, -0.7499679795819634F, 0.0F, 0.0F);

        this.body = new ModelRenderer(this, 16, 20);
        this.body.setPos(0.0F, 0.0F, 0.0F);
        this.body.addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, 0.0F);

        this.leftLeg = new ModelRenderer(this, 0, 22);
        this.leftLeg.mirror = true;
        this.leftLeg.setPos(2.0F, 12.0F, 0.0F);
        this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);

        this.rightLeg = new ModelRenderer(this, 0, 22);
        this.rightLeg.setPos(-2.0F, 12.0F, 0.0F);
        this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);

        this.head.addChild(this.earRight);
        this.head.addChild(this.earLeft);
        this.head.addChild(this.nose);

        this.wingedWings = new ModelRenderer(this);
        this.wingedWings.setPos(0.0F, 24.0F, 0.0F);
        this.body.addChild(this.wingedWings);

        this.wingedWingsLeft = new ModelRenderer(this);
        this.wingedWingsLeft.setPos(1.5F, -21.5F, 3.0F);
        this.setRotateAngle(this.wingedWingsLeft, 0.4363F, 0.8727F, 0.0F);
        this.wingedWings.addChild(this.wingedWingsLeft);
        this.wingedWingsLeft.texOffs(40, 38);
        this.wingedWingsLeft.addBox(-1.0F, -1.0F, 0.0F, 1, 1, 11);
        this.wingedWingsLeft.texOffs(32, 0);
        this.wingedWingsLeft.addBox(-0.1F, 0.0F, -0.4226F, 0, 9, 11);

        this.wingedWingsRight = new ModelRenderer(this);
        this.wingedWingsRight.setPos(-0.5F, -21.5F, 3.0F);
        this.setRotateAngle(this.wingedWingsRight, 0.4363F, -0.8727F, 0.0F);
        this.wingedWings.addChild(this.wingedWingsRight);
        this.wingedWingsRight.texOffs(40, 38);
        this.wingedWingsRight.addBox(-1.0F, -1.0F, 0.0F, 1, 1, 11);
        this.wingedWingsRight.texOffs(32, 0);
        this.wingedWingsRight.addBox(-0.1F, 0.0F, -0.4226F, 0, 9, 11);

    }
    //endregion Initialization

    //region Methods
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
    //endregion Methods
}
