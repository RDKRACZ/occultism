/*
 * MIT License
 *
 * Copyright 2021 vemerion
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

import com.github.klikli_dev.occultism.common.entity.DeerFamiliarEntity;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.util.math.MathHelper;

/**
 * Created using Tabula 8.0.0
 */
public class DeerFamiliarModel extends EntityModel<DeerFamiliarEntity> {

    private static final float PI = (float) Math.PI;

    public ModelRenderer body;
    public ModelRenderer leftFrontLeg1;
    public ModelRenderer tail;
    public ModelRenderer neck;
    public ModelRenderer rightFrontLeg1;
    public ModelRenderer leftBackLeg1;
    public ModelRenderer rightBackLeg1;
    public ModelRenderer leftFrontLeg2;
    public ModelRenderer head;
    public ModelRenderer nose;
    public ModelRenderer leftEar;
    public ModelRenderer rightEar;
    public ModelRenderer leftHorn1;
    public ModelRenderer rightHorn1;
    public ModelRenderer hammerHandle;
    public ModelRenderer hammerHead;
    public ModelRenderer hammerSpikes1;
    public ModelRenderer hammerSpikes2;
    public ModelRenderer hammerSpikes3;
    public ModelRenderer hammerSpikes4;
    public ModelRenderer hammerSpikes5;
    public ModelRenderer hammerSpikes6;
    public ModelRenderer hammerSpikes7;
    public ModelRenderer hammerSpikes8;
    public ModelRenderer leftHorn2;
    public ModelRenderer leftHorn3;
    public ModelRenderer rightHorn2;
    public ModelRenderer rightHorn3;
    public ModelRenderer rightFrontLeg2;
    public ModelRenderer leftBackLeg2;
    public ModelRenderer rightBackLeg2;

    public DeerFamiliarModel() {
        this.texWidth = 64;
        this.texHeight = 16;
        this.rightBackLeg2 = new ModelRenderer(this, 34, 10);
        this.rightBackLeg2.mirror = true;
        this.rightBackLeg2.setPos(0.01F, 3.8F, 0.0F);
        this.rightBackLeg2.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightBackLeg2, -0.11728612207217244F, 0.0F, 0.0F);
        this.rightHorn1 = new ModelRenderer(this, 50, 4);
        this.rightHorn1.setPos(-0.7F, -2.5F, 0.3F);
        this.rightHorn1.addBox(-0.5F, -3.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightHorn1, 0.0F, 0.0F, -0.3127630032889644F);
        this.leftFrontLeg1 = new ModelRenderer(this, 0, 0);
        this.leftFrontLeg1.setPos(1.4F, -0.2F, -3.9F);
        this.leftFrontLeg1.addBox(-0.5F, 0.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftFrontLeg1, -0.11728612207217244F, 0.0F, 0.0F);
        this.hammerSpikes2 = new ModelRenderer(this, 58, 10);
        this.hammerSpikes2.setPos(0.2F, -1.2F, 0.4F);
        this.hammerSpikes2.addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(hammerSpikes2, 0.0F, 0.0F, 0.7853981633974483F);
        this.tail = new ModelRenderer(this, 22, 0);
        this.tail.setPos(0.0F, -4.0F, 4.5F);
        this.tail.addBox(-1.0F, -0.5F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tail, -0.4363323129985824F, 0.0F, 0.0F);
        this.rightFrontLeg1 = new ModelRenderer(this, 0, 0);
        this.rightFrontLeg1.mirror = true;
        this.rightFrontLeg1.setPos(-1.4F, -0.2F, -3.9F);
        this.rightFrontLeg1.addBox(-0.5F, 0.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightFrontLeg1, -0.11728612207217244F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setPos(0.0F, 16.4F, 0.0F);
        this.body.addBox(-2.0F, -5.0F, -5.0F, 4.0F, 5.0F, 10.0F, 0.0F, 0.0F, 0.0F);
        this.neck = new ModelRenderer(this, 30, 0);
        this.neck.setPos(0.0F, -4.0F, -4.0F);
        this.neck.addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(neck, 0.3909537457888271F, 0.0F, 0.0F);
        this.leftHorn2 = new ModelRenderer(this, 58, 0);
        this.leftHorn2.setPos(0.3F, -2.5F, 0.0F);
        this.leftHorn2.addBox(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftHorn2, 0.0F, 0.0F, 1.1344640137963142F);
        this.leftBackLeg1 = new ModelRenderer(this, 28, 9);
        this.leftBackLeg1.setPos(1.4F, -0.2F, 3.9F);
        this.leftBackLeg1.addBox(-0.5F, 0.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftBackLeg1, 0.11728612207217244F, 0.0F, 0.0F);
        this.leftEar = new ModelRenderer(this, 22, 3);
        this.leftEar.setPos(1.0F, -2.5F, 0.5F);
        this.leftEar.addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftEar, -0.3909537457888271F, 0.0F, 0.7819074915776542F);
        this.hammerSpikes4 = new ModelRenderer(this, 58, 10);
        this.hammerSpikes4.setPos(1.2F, 0.3F, 0.4F);
        this.hammerSpikes4.addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(hammerSpikes4, 0.0F, 0.0F, 0.7853981633974483F);
        this.hammerSpikes5 = new ModelRenderer(this, 58, 9);
        this.hammerSpikes5.setPos(0.5F, -0.2F, 1.2F);
        this.hammerSpikes5.addBox(0.0F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(hammerSpikes5, 0.7853981633974483F, 0.0F, 0.0F);
        this.hammerHead = new ModelRenderer(this, 50, 12);
        this.hammerHead.setPos(6.0F, 0.0F, 0.0F);
        this.hammerHead.addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(hammerHead, 0.0F, 0.7853981633974483F, 0.0F);
        this.leftFrontLeg2 = new ModelRenderer(this, 18, 0);
        this.leftFrontLeg2.setPos(-0.01F, 3.8F, 0.0F);
        this.leftFrontLeg2.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftFrontLeg2, 0.11728612207217244F, 0.0F, 0.0F);
        this.rightHorn3 = new ModelRenderer(this, 18, 5);
        this.rightHorn3.setPos(0.0F, -2.7F, 0.0F);
        this.rightHorn3.addBox(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightHorn3, 0.5864306020384839F, 0.0F, 0.0F);
        this.rightHorn2 = new ModelRenderer(this, 58, 0);
        this.rightHorn2.setPos(-0.3F, -2.5F, 0.0F);
        this.rightHorn2.addBox(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightHorn2, 0.0F, 0.0F, -1.1344640137963142F);
        this.rightFrontLeg2 = new ModelRenderer(this, 18, 0);
        this.rightFrontLeg2.mirror = true;
        this.rightFrontLeg2.setPos(0.01F, 3.8F, 0.0F);
        this.rightFrontLeg2.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightFrontLeg2, 0.11728612207217244F, 0.0F, 0.0F);
        this.leftBackLeg2 = new ModelRenderer(this, 34, 10);
        this.leftBackLeg2.setPos(-0.01F, 3.8F, 0.0F);
        this.leftBackLeg2.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftBackLeg2, -0.11728612207217244F, 0.0F, 0.0F);
        this.hammerSpikes1 = new ModelRenderer(this, 58, 10);
        this.hammerSpikes1.setPos(-0.2F, -1.2F, -0.6F);
        this.hammerSpikes1.addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(hammerSpikes1, 0.0F, 0.0F, 0.7853981633974483F);
        this.rightEar = new ModelRenderer(this, 22, 3);
        this.rightEar.mirror = true;
        this.rightEar.setPos(-1.0F, -2.5F, 0.5F);
        this.rightEar.addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightEar, -0.3909537457888271F, 0.0F, -0.7819074915776542F);
        this.head = new ModelRenderer(this, 38, 0);
        this.head.setPos(0.0F, -2.5F, -0.3F);
        this.head.addBox(-1.5F, -3.0F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.hammerSpikes7 = new ModelRenderer(this, 58, 9);
        this.hammerSpikes7.setPos(0.6F, 1.2F, -0.1F);
        this.hammerSpikes7.addBox(0.0F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(hammerSpikes7, 0.7853981633974483F, 0.0F, 0.0F);
        this.hammerSpikes6 = new ModelRenderer(this, 58, 9);
        this.hammerSpikes6.setPos(-0.4F, 0.3F, 1.2F);
        this.hammerSpikes6.addBox(0.0F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(hammerSpikes6, 0.7853981633974483F, 0.0F, 0.0F);
        this.hammerHandle = new ModelRenderer(this, 38, 14);
        this.hammerHandle.setPos(-1.8F, 0.3F, -1.3F);
        this.hammerHandle.addBox(0.0F, -0.5F, -0.5F, 5.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.hammerSpikes3 = new ModelRenderer(this, 58, 10);
        this.hammerSpikes3.setPos(1.2F, -0.3F, -0.6F);
        this.hammerSpikes3.addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(hammerSpikes3, 0.0F, 0.0F, 0.7853981633974483F);
        this.hammerSpikes8 = new ModelRenderer(this, 58, 9);
        this.hammerSpikes8.setPos(-0.5F, 1.2F, 0.3F);
        this.hammerSpikes8.addBox(0.0F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(hammerSpikes8, 0.7853981633974483F, 0.0F, 0.0F);
        this.nose = new ModelRenderer(this, 51, 5);
        this.nose.setPos(0.0F, -1.01F, -1.5F);
        this.nose.addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.leftHorn1 = new ModelRenderer(this, 50, 4);
        this.leftHorn1.setPos(0.7F, -2.5F, 0.3F);
        this.leftHorn1.addBox(-0.5F, -3.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftHorn1, 0.0F, 0.0F, 0.3127630032889644F);
        this.leftHorn3 = new ModelRenderer(this, 18, 5);
        this.leftHorn3.setPos(0.0F, -2.7F, 0.0F);
        this.leftHorn3.addBox(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftHorn3, 0.5864306020384839F, 0.0F, 0.0F);
        this.rightBackLeg1 = new ModelRenderer(this, 28, 9);
        this.rightBackLeg1.mirror = true;
        this.rightBackLeg1.setPos(-1.4F, -0.2F, 3.9F);
        this.rightBackLeg1.addBox(-0.5F, 0.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightBackLeg1, 0.11728612207217244F, 0.0F, 0.0F);
        this.rightBackLeg1.addChild(this.rightBackLeg2);
        this.head.addChild(this.rightHorn1);
        this.body.addChild(this.leftFrontLeg1);
        this.hammerHead.addChild(this.hammerSpikes2);
        this.body.addChild(this.tail);
        this.body.addChild(this.rightFrontLeg1);
        this.body.addChild(this.neck);
        this.leftHorn1.addChild(this.leftHorn2);
        this.body.addChild(this.leftBackLeg1);
        this.head.addChild(this.leftEar);
        this.hammerHead.addChild(this.hammerSpikes4);
        this.hammerHead.addChild(this.hammerSpikes5);
        this.hammerHandle.addChild(this.hammerHead);
        this.leftFrontLeg1.addChild(this.leftFrontLeg2);
        this.rightHorn1.addChild(this.rightHorn3);
        this.rightHorn1.addChild(this.rightHorn2);
        this.rightFrontLeg1.addChild(this.rightFrontLeg2);
        this.leftBackLeg1.addChild(this.leftBackLeg2);
        this.hammerHead.addChild(this.hammerSpikes1);
        this.head.addChild(this.rightEar);
        this.neck.addChild(this.head);
        this.hammerHead.addChild(this.hammerSpikes7);
        this.hammerHead.addChild(this.hammerSpikes6);
        this.nose.addChild(this.hammerHandle);
        this.hammerHead.addChild(this.hammerSpikes3);
        this.hammerHead.addChild(this.hammerSpikes8);
        this.head.addChild(this.nose);
        this.head.addChild(this.leftHorn1);
        this.leftHorn1.addChild(this.leftHorn3);
        this.body.addChild(this.rightBackLeg1);
    }                                                 

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
                               float red, float green, float blue, float alpha) {
        ImmutableList.of(this.body).forEach((modelRenderer) -> {
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    private float toRad(float deg) {
        return (float) Math.toRadians(deg);
    }

    @Override
    public void prepareMobModel(DeerFamiliarEntity entityIn, float limbSwing, float limbSwingAmount,
            float partialTick) {
        this.neck.xRot = entityIn.getNeckRot(partialTick);
        
        this.hammerHandle.visible = entityIn.hasBlacksmithUpgrade();
    }

    @Override
    public void setupAnim(DeerFamiliarEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
                          float netHeadYaw, float headPitch) {
        if (entityIn.isEating()) {
            this.head.xRot = MathHelper.cos(ageInTicks * 0.8f) * 0.2f;
            this.head.yRot = 0;
        } else {
            this.head.yRot = netHeadYaw * (PI / 180f) * 0.5f;
            this.head.xRot = headPitch * (PI / 180f) * 0.5f;
        }
        
        if (this.attackTime > 0.01) {
            this.head.yRot = MathHelper.sin(this.attackTime * PI) * toRad(50);
            this.head.zRot = MathHelper.sin(this.attackTime * PI) * toRad(-40);
            this.nose.yRot = MathHelper.sin(this.attackTime * PI) * toRad(18);
            this.nose.zRot = MathHelper.sin(this.attackTime * PI) * toRad(-18);
        } else {
            this.head.zRot = 0;
            this.nose.yRot = 0;
            this.nose.zRot = 0;
        }

        this.tail.xRot = MathHelper.cos(limbSwing * 0.7f) * 0.4f * limbSwingAmount - 0.3f;
        this.body.xRot = 0;

        if (entityIn.isPartying()) {
            this.body.xRot = this.toRad(-20);
            this.setRotateAngle(this.rightBackLeg1, MathHelper.cos(ageInTicks / 2 + PI) * this.toRad(5) + this.toRad(20), 0, 0);
            this.setRotateAngle(this.leftBackLeg1, MathHelper.cos(ageInTicks / 2) * this.toRad(5) + this.toRad(20), 0, 0);
            this.setRotateAngle(this.rightFrontLeg1, MathHelper.cos(ageInTicks / 2) * this.toRad(30) - this.toRad(40), 0, 0);
            this.setRotateAngle(this.leftFrontLeg1, MathHelper.cos(ageInTicks / 2 + PI) * this.toRad(30) - this.toRad(40), 0, 0);

            this.rightBackLeg2.z = 0;
            this.leftBackLeg2.z = 0;
            this.rightFrontLeg2.z = 0;
            this.leftFrontLeg2.z = 0;

            this.rightBackLeg2.xRot = -0.1f;
            this.leftBackLeg2.xRot = -0.1f;
            this.rightFrontLeg2.xRot = 0.1f;
            this.leftFrontLeg2.xRot = 0.1f;
        } else if (entityIn.isSitting()) {
            this.setRotateAngle(this.rightBackLeg1, this.toRad(80), this.toRad(10), -this.toRad(40));
            this.setRotateAngle(this.leftBackLeg1, this.toRad(80), this.toRad(10), -this.toRad(40));
            this.setRotateAngle(this.rightFrontLeg1, -this.toRad(80), -this.toRad(10), -this.toRad(40));
            this.setRotateAngle(this.leftFrontLeg1, -this.toRad(80), -this.toRad(10), -this.toRad(40));

            this.rightBackLeg2.z = -0.5f;
            this.leftBackLeg2.z = -0.5f;
            this.rightFrontLeg2.z = 0.5f;
            this.leftFrontLeg2.z = 0.5f;

            this.rightBackLeg2.xRot = -this.toRad(150);
            this.leftBackLeg2.xRot = -this.toRad(150);
            this.rightFrontLeg2.xRot = this.toRad(150);
            this.leftFrontLeg2.xRot = this.toRad(150);
        } else {
            boolean fast = entityIn.getAttributeValue(Attributes.MOVEMENT_SPEED) > 0.4;
            this.setRotateAngle(this.rightBackLeg1,
                    MathHelper.cos(limbSwing * 0.7f + (fast ? PI : 0)) * 1.4f * limbSwingAmount, 0, 0);
            this.setRotateAngle(this.leftBackLeg1, MathHelper.cos(limbSwing * 0.7f + PI) * 1.4f * limbSwingAmount, 0,
                    0);
            this.setRotateAngle(this.rightFrontLeg1,
                    MathHelper.cos(limbSwing * 0.7f + (fast ? 0 : PI)) * 1.4f * limbSwingAmount, 0, 0);
            this.setRotateAngle(this.leftFrontLeg1, MathHelper.cos(limbSwing * 0.7f) * 1.4f * limbSwingAmount, 0, 0);

            this.rightBackLeg2.z = 0;
            this.leftBackLeg2.z = 0;
            this.rightFrontLeg2.z = 0;
            this.leftFrontLeg2.z = 0;

            this.rightBackLeg2.xRot = -0.1f;
            this.leftBackLeg2.xRot = -0.1f;
            this.rightFrontLeg2.xRot = 0.1f;
            this.leftFrontLeg2.xRot = 0.1f;
        }
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
