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

import com.github.klikli_dev.occultism.common.entity.spirit.DjinniEntity;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class DjinniModel extends BipedModel<DjinniEntity> {
    //region Fields
    private final ModelRenderer leftHorn;
    private final ModelRenderer rightHorn;
    private final ModelRenderer leftEar;
    private final ModelRenderer leftInnerEar;
    private final ModelRenderer rightEar;
    private final ModelRenderer rightInnerEar;
    private final ModelRenderer wingedWings;
    private final ModelRenderer wingedWingsLeft;
    private final ModelRenderer wingedWingsRight;


    //endregion Fields

    //region Initialization
    public DjinniModel() {
        super(1.0f);
        this.leftArmPose = ArmPose.EMPTY;
        this.rightArmPose = ArmPose.EMPTY;
        this.texWidth = 64;
        this.texHeight = 64;

        this.head = new ModelRenderer(this);
        this.head.setPos(0.0F, 0.0F, 0.0F);
        this.head.addBox("bipedHead", -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F, 0, 14);

        this.hat = new ModelRenderer(this);
        this.hat.setPos(0.0F, 0.0F, 0.0F);

        this.leftHorn = new ModelRenderer(this);
        this.leftHorn.setPos(3.0F, -8.0F, -2.0F);
        this.setRotationAngle(this.leftHorn, 0.0F, 0.0F, 0.3491F);
        this.head.addChild(this.leftHorn);
        this.leftHorn.addBox("leftHorn", 0.0F, -2.0F, 0.0F, 1, 2, 1, 0.0F, 16, 43);
        this.leftHorn.addBox("leftHorn", 1.0F, -4.0F, 0.0F, 1, 3, 1, 0.0F, 41, 3);

        this.rightHorn = new ModelRenderer(this);
        this.rightHorn.setPos(-3.0F, -8.0F, -2.0F);
        this.setRotationAngle(this.rightHorn, 0.0F, 0.0F, -0.3491F);
        this.head.addChild(this.rightHorn);
        this.rightHorn.addBox("rightHorn", -1.0F, -2.0F, 0.0F, 1, 2, 1, 0.0F, 28, 42);
        this.rightHorn.addBox("rightHorn", -2.0F, -4.0F, 0.0F, 1, 3, 1, 0.0F, 0, 39);

        this.leftEar = new ModelRenderer(this);
        this.leftEar.setPos(5.0F, -4.0F, 1.0F);
        this.setRotationAngle(this.leftEar, 0.0F, 0.2618F, 0.0F);
        this.head.addChild(this.leftEar);
        this.leftEar.addBox("leftEar", 0.0F, -2.0F, -1.0F, 1, 2, 1, 0.0F, 40, 25);
        this.leftEar.addBox("leftEar", 0.0F, -3.0F, 0.0F, 1, 1, 1, 0.0F, 38, 31);
        this.leftEar.addBox("leftEar", 0.0F, -4.0F, 1.0F, 1, 1, 3, 0.0F, 12, 39);
        this.leftEar.addBox("leftEar", 0.0F, -3.0F, 4.0F, 1, 1, 1, 0.0F, 34, 31);
        this.leftEar.addBox("leftEar", 0.0F, 0.0F, 0.0F, 1, 1, 2, 0.0F, 37, 0);
        this.leftEar.addBox("leftEar", 0.0F, -1.0F, 2.0F, 1, 1, 1, 0.0F, 30, 31);
        this.leftEar.addBox("leftEar", 0.0F, -2.0F, 3.0F, 1, 1, 1, 0.0F, 34, 4);

        this.leftInnerEar = new ModelRenderer(this);
        this.leftInnerEar.setPos(-1.0F, 0.0F, -3.0F);
        this.leftEar.addChild(this.leftInnerEar);
        this.leftInnerEar.addBox("leftInnerEar", 0.0F, -2.0F, 3.0F, 1, 1, 3, 0.0F, 36, 4);
        this.leftInnerEar.addBox("leftInnerEar", 0.0F, -1.0F, 3.0F, 1, 1, 2, 0.0F, 24, 19);
        this.leftInnerEar.addBox("leftInnerEar", 0.0F, -3.0F, 4.0F, 1, 1, 3, 0.0F, 35, 25);

        this.rightEar = new ModelRenderer(this);
        this.rightEar.setPos(-5.0F, -4.0F, 1.0F);
        this.setRotationAngle(this.rightEar, 0.0F, -0.2618F, 0.0F);
        this.head.addChild(this.rightEar);
        this.rightEar.addBox("rightEar", -1.0F, -2.0F, -1.0F, 1, 2, 1, 0.0F, 17, 39);
        this.rightEar.addBox("rightEar", -1.0F, -3.0F, 0.0F, 1, 1, 1, 0.0F, 26, 31);
        this.rightEar.addBox("rightEar", -1.0F, -4.0F, 1.0F, 1, 1, 3, 0.0F, 24, 15);
        this.rightEar.addBox("rightEar", -1.0F, -3.0F, 4.0F, 1, 1, 1, 0.0F, 22, 31);
        this.rightEar.addBox("rightEar", -1.0F, 0.0F, 0.0F, 1, 1, 2, 0.0F, 0, 3);
        this.rightEar.addBox("rightEar", -1.0F, -1.0F, 2.0F, 1, 1, 1, 0.0F, 30, 4);
        this.rightEar.addBox("rightEar", -1.0F, -2.0F, 3.0F, 1, 1, 1, 0.0F, 28, 19);

        this.rightInnerEar = new ModelRenderer(this);
        this.rightInnerEar.setPos(-1.0F, 0.0F, -3.0F);
        this.rightEar.addChild(this.rightInnerEar);
        this.rightInnerEar.addBox("rightInnerEar", 1.0F, -2.0F, 3.0F, 1, 1, 3, 0.0F, 0, 18);
        this.rightInnerEar.addBox("rightInnerEar", 1.0F, -1.0F, 3.0F, 1, 1, 2, 0.0F, 0, 0);
        this.rightInnerEar.addBox("rightInnerEar", 1.0F, -3.0F, 4.0F, 1, 1, 3, 0.0F, 0, 14);

        this.leftArm = new ModelRenderer(this);
        this.leftArm.setPos(6.0F, 3.0F, -1.0F);
        this.setRotationAngle(this.leftArm, -0.75F, 0.0F, 0.0F);
        this.leftArm.addBox("bipedLeftArm", 0.0F, -1.6816F, -1.2683F, 3, 11, 3, 0.0F, 44, 45);

        this.rightArm = new ModelRenderer(this);
        this.rightArm.setPos(-6.0F, 3.0F, -1.0F);
        this.setRotationAngle(this.rightArm, -0.75F, 0.0F, 0.0F);
        this.rightArm.addBox("bipedLeftArm", -3.0F, -2.0F, -2.0F, 3, 11, 3, 0.0F, 32, 45);

        this.body = new ModelRenderer(this);
        this.body.setPos(0.0F, 0.0F, 0.0F);
        this.body.addBox("bipedBody", -6.0F, 0.0F, -3.0F, 12, 8, 6, 0.0F, 0, 0);
        this.body.addBox("bipedBody", -4.0F, 8.0F, -3.0F, 8, 4, 6, 0.0F, 30, 8);

        this.wingedWings = new ModelRenderer(this);
        this.wingedWings.setPos(0.0F, 24.0F, 0.0F);
        this.body.addChild(this.wingedWings);

        this.wingedWingsLeft = new ModelRenderer(this);
        this.wingedWingsLeft.setPos(1.5F, -21.5F, 3.0F);
        this.setRotationAngle(this.wingedWingsLeft, 0.4363F, 0.8727F, 0.0F);
        this.wingedWings.addChild(this.wingedWingsLeft);
        this.wingedWingsLeft.addBox("wingedWingsLeft", -1.0F, -1.0F, 0.0F, 1, 1, 11, 0.0F, 33, 33);
        this.wingedWingsLeft.addBox("wingedWingsLeft", -0.1F, 0.0F, -0.4226F, 0, 9, 11, 0.0F, 22, 22);

        this.wingedWingsRight = new ModelRenderer(this);
        this.wingedWingsRight.setPos(-0.5F, -21.5F, 3.0F);
        this.setRotationAngle(this.wingedWingsRight, 0.4363F, -0.8727F, 0.0F);
        this.wingedWings.addChild(this.wingedWingsRight);
        this.wingedWingsRight.addBox("wingedWingsRight", -1.0F, -1.0F, 0.0F, 1, 1, 11, 0.0F, 22, 19);
        this.wingedWingsRight.addBox("wingedWingsRight", -0.1F, 0.0F, -0.4226F, 0, 9, 11, 0.0F, 0, 19);

        this.leftLeg = new ModelRenderer(this);
        this.leftLeg.setPos(-2.0F, 12.0F, 0.0F);
        this.leftLeg.addBox("bipedLeftLeg", -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, 16, 42);

        this.rightLeg = new ModelRenderer(this);
        this.rightLeg.setPos(2.0F, 12.0F, 0.0F);
        this.rightLeg.addBox("bipedRightLeg", -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, 0, 39);
    }
    //endregion Initialization

    //region Methods
    public void setupAnim(DjinniEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
                          float netHeadYaw, float headPitch) {

        super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);


        //Copied from BipedModel, removed all code except that which accesses rotationPointX
        this.rightArm.x = -6.0F;
        this.leftArm.x = 6.0F;

        if (this.attackTime > 0.0F) {
            //original x needs to be used as the multiplier here
            this.rightArm.x = -MathHelper.cos(this.body.yRot) * 6.0F;
            this.leftArm.x = MathHelper.cos(this.body.yRot) * 6.0F;
        }
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
    //endregion Methods
}
