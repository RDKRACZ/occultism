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

package com.github.klikli_dev.occultism.client.gui.storage;

import com.github.klikli_dev.occultism.api.common.data.SortDirection;
import com.github.klikli_dev.occultism.api.common.data.SortType;
import com.github.klikli_dev.occultism.common.container.storage.StorageControllerContainer;
import com.github.klikli_dev.occultism.common.tile.StorageControllerTileEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;

public class StorageControllerGui extends StorageControllerGuiBase<StorageControllerContainer> {

    //region Fields
    protected StorageControllerTileEntity storageController;
    //endregion Fields

    //region Initialization
    public StorageControllerGui(StorageControllerContainer container, PlayerInventory playerInventory,
                                ITextComponent name) {
        super(container, playerInventory, name);
        this.storageController = container.getStorageController();
    }
    //endregion Initialization

    //region Overrides
    @Override
    protected boolean isGuiValid() {
        return true;
    }

    @Override
    protected BlockPos getEntityPosition() {
        return this.storageController.getBlockPos();
    }

    @Override
    public SortDirection getSortDirection() {
        return this.storageController.getSortDirection();
    }

    @Override
    public void setSortDirection(SortDirection sortDirection) {
        this.storageController.setSortDirection(sortDirection);
    }

    @Override
    public SortType getSortType() {
        return this.storageController.getSortType();
    }

    @Override
    public void setSortType(SortType sortType) {
        this.storageController.setSortType(sortType);
    }
    //endregion Overrides
}
