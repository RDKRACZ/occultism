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

package com.github.klikli_dev.occultism.client.gui.controls;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.util.ResourceLocation;

public class SizedImageButton extends ImageButton {
    //region Fields
    public final ResourceLocation resourceLocation;
    public final int xTexStart;
    public final int yTexStart;
    public final int xDiffOffset;
    public final int textureWidth;
    public final int textureHeight;
    public final int textureMapWidth;
    public final int textureMapHeight;
    //endregion Fields

    /**
     * A button that supports texture size, as well as a foreground texture
     *
     * @param xIn              the draw position x
     * @param yIn              the draw position y
     * @param widthIn          the button draw width
     * @param heightIn         the button draw height
     * @param textureOffsetX   the x offset in the texture map
     * @param textureOffsetY   the y offset in the texture map
     * @param hoverOffsetX     the x offset for the hover textures
     * @param textureWidth     the x size to take from the texture map
     * @param textureHeight    the y size to take from the texture map
     * @param textureMapWidth  the x size of the texture map.
     * @param textureMapHeight the y size of the texture map.
     * @param resourceLocation the resource location for the textures
     */
    //region Initialization
    public SizedImageButton(int xIn, int yIn, int widthIn, int heightIn, int textureOffsetX,
                            int textureOffsetY, int hoverOffsetX, int textureWidth, int textureHeight,
                            int textureMapWidth, int textureMapHeight, ResourceLocation resourceLocation,
                            IPressable handler) {
        super(xIn, yIn, widthIn, heightIn, textureOffsetX, textureOffsetY, 0, resourceLocation, handler);
        this.xTexStart = textureOffsetX;
        this.yTexStart = textureOffsetY;
        this.xDiffOffset = hoverOffsetX;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.textureMapWidth = textureMapWidth;
        this.textureMapHeight = textureMapHeight;
        this.resourceLocation = resourceLocation;
    }
    //endregion Initialization

    //region Overrides
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    //endregion Overrides

    //region Methods
    @Override
    public void renderButton(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        if (this.visible) {
            Minecraft.getInstance().getTextureManager().bind(this.resourceLocation);
            RenderSystem.disableDepthTest();
            int i = this.xTexStart;
            int j = this.yTexStart;
            if (this.isHovered()) {
                i += this.xDiffOffset;
            }
            blit(stack, this.x, this.y, this.width, this.height, i, j, this.textureWidth, this.textureHeight, this.textureMapWidth, this.textureMapHeight);
            RenderSystem.enableDepthTest();
        }

    }
//endregion Methods
}
