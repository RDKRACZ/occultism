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

package com.github.klikli_dev.occultism.registry;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

import com.github.klikli_dev.occultism.Occultism;
import com.github.klikli_dev.occultism.common.block.CandleBlock;
import com.github.klikli_dev.occultism.common.block.ChalkGlyphBlock;
import com.github.klikli_dev.occultism.common.block.DimensionalMineshaftBlock;
import com.github.klikli_dev.occultism.common.block.GoldenSacrificialBowlBlock;
import com.github.klikli_dev.occultism.common.block.SacrificialBowlBlock;
import com.github.klikli_dev.occultism.common.block.SpiritAttunedCrystalBlock;
import com.github.klikli_dev.occultism.common.block.SpiritFireBlock;
import com.github.klikli_dev.occultism.common.block.crops.ReplantableCropsBlock;
import com.github.klikli_dev.occultism.common.block.otherworld.IesniumOreNaturalBlock;
import com.github.klikli_dev.occultism.common.block.otherworld.OtherstoneNaturalBlock;
import com.github.klikli_dev.occultism.common.block.otherworld.OtherworldLeavesNaturalBlock;
import com.github.klikli_dev.occultism.common.block.otherworld.OtherworldLogNaturalBlock;
import com.github.klikli_dev.occultism.common.block.otherworld.OtherworldSaplingBlock;
import com.github.klikli_dev.occultism.common.block.otherworld.OtherworldSaplingNaturalBlock;
import com.github.klikli_dev.occultism.common.block.storage.StableWormholeBlock;
import com.github.klikli_dev.occultism.common.block.storage.StorageControllerBlock;
import com.github.klikli_dev.occultism.common.block.storage.StorageStabilizerBlock;
import com.github.klikli_dev.occultism.common.entity.CthulhuFamiliarEntity;
import com.github.klikli_dev.occultism.common.world.tree.OtherworldNaturalTree;
import com.github.klikli_dev.occultism.common.world.tree.OtherworldTree;

import net.minecraft.block.AirBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.PushReaction;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class OccultismBlocks {

    //region Fields
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            Occultism.MODID);
    public static final Map<ResourceLocation, BlockDataGenSettings> BLOCK_DATA_GEN_SETTINGS = new HashMap<>();

    //Blocks without item
    public static final RegistryObject<SpiritFireBlock> SPIRIT_FIRE = register("spirit_fire",
            () -> new SpiritFireBlock(
                    Block.Properties.of(Material.FIRE, MaterialColor.FIRE).noCollission().randomTicks()
                            .strength(0, 0).lightLevel((state) -> 12)
                            .sound(SoundType.WOOL)), false,
            LootTableType.EMPTY);
    
    public static final RegistryObject<Block> LIGHTED_AIR = register("lighted_air", () -> new AirBlock(
            Block.Properties.of(Material.AIR).noCollission().air().noDrops().lightLevel(s -> 15).randomTicks()) {
        @Override
        public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
            if (world.getEntitiesOfClass(CthulhuFamiliarEntity.class, new AxisAlignedBB(pos),
                    e -> e.hasBlacksmithUpgrade()).isEmpty())
                world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
        }
    });

    //can't use builder here because of random private builder methods ..
    public static final Material GLYPH_MATERIAL = new Material(MaterialColor.NONE, false, false,
            false, true, false, true, PushReaction.DESTROY);

    public static final Block.Properties GLYPH_PROPERTIES = Block.Properties.of(GLYPH_MATERIAL)
            .sound(SoundType.WOOL).noCollission()
            .strength(5f, 30);
    public static final RegistryObject<ChalkGlyphBlock> CHALK_GLYPH_WHITE = register("chalk_glyph_white",
            () -> new ChalkGlyphBlock(GLYPH_PROPERTIES, 0xffffff, () -> OccultismItems.CHALK_WHITE.get()),
            false, LootTableType.EMPTY);
    public static final RegistryObject<ChalkGlyphBlock> CHALK_GLYPH_GOLD = register("chalk_glyph_gold",
            () -> new ChalkGlyphBlock(GLYPH_PROPERTIES, 0xf0d700, () -> OccultismItems.CHALK_GOLD.get()), false,
            LootTableType.EMPTY);
    public static final RegistryObject<ChalkGlyphBlock> CHALK_GLYPH_PURPLE = register("chalk_glyph_purple",
            () -> new ChalkGlyphBlock(GLYPH_PROPERTIES, 0x9c0393, () -> OccultismItems.CHALK_PURPLE.get()),
            false, LootTableType.EMPTY);
    public static final RegistryObject<ChalkGlyphBlock> CHALK_GLYPH_RED = register("chalk_glyph_red",
            () -> new ChalkGlyphBlock(GLYPH_PROPERTIES, 0xcc0101, () -> OccultismItems.CHALK_RED.get()), false,
            LootTableType.EMPTY);

    //Resources
    public static final RegistryObject<Block> OTHERSTONE = register("otherstone", () -> new Block(
            Block.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5f, 30)));
    //Components
    public static final RegistryObject<SlabBlock> OTHERSTONE_SLAB = register("otherstone_slab", () -> new SlabBlock(Block.Properties.copy(OTHERSTONE.get())));
    public static final RegistryObject<Block> OTHERSTONE_PEDESTAL = register("otherstone_pedestal", () -> new Block(Block.Properties.copy(OTHERSTONE.get())));
    public static final RegistryObject<Block> STORAGE_CONTROLLER_BASE = register("storage_controller_base",
            () -> new Block(Block.Properties.copy(OTHERSTONE.get()).noOcclusion()));
    public static final RegistryObject<OtherstoneNaturalBlock> OTHERSTONE_NATURAL =
            register("otherstone_natural", () -> new OtherstoneNaturalBlock(
                            Block.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5f, 30)),
                    true, LootTableType.OTHERWORLD_BLOCK);
    public static final RegistryObject<Block> OTHERWORLD_LOG =
            register("otherworld_log", () -> new RotatedPillarBlock(Block.Properties.of(Material.WOOD, (state) -> {
                return state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MaterialColor.WOOD : MaterialColor.PODZOL;
            }).strength(2.0F).sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistryObject<Block> OTHERWORLD_LOG_NATURAL =
            register("otherworld_log_natural", () -> new OtherworldLogNaturalBlock(Block.Properties.of(Material.WOOD, (state) -> {
                return state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MaterialColor.WOOD : MaterialColor.PODZOL;
            }).strength(2.0f)), true, LootTableType.OTHERWORLD_BLOCK);
    public static final RegistryObject<LeavesBlock> OTHERWORLD_LEAVES =
            register("otherworld_leaves", () -> new LeavesBlock(
                    Block.Properties.of(Material.LEAVES).sound(SoundType.GRASS)
                            .strength(0.2f).randomTicks().noOcclusion()), true, LootTableType.CUSTOM);
    public static final RegistryObject<OtherworldLeavesNaturalBlock> OTHERWORLD_LEAVES_NATURAL =
            register("otherworld_leaves_natural", () -> new OtherworldLeavesNaturalBlock(
                    Block.Properties.of(Material.LEAVES).sound(SoundType.GRASS)
                            .strength(0.2f).randomTicks().noOcclusion()), true, LootTableType.CUSTOM);
    public static final RegistryObject<OtherworldSaplingBlock> OTHERWORLD_SAPLING =
            register("otherworld_sapling", () -> new OtherworldSaplingBlock(new OtherworldTree(),
                    Block.Properties.of(Material.PLANT).sound(SoundType.GRASS)
                            .strength(0.0f).randomTicks().noCollission()));
    public static final RegistryObject<OtherworldSaplingNaturalBlock> OTHERWORLD_SAPLING_NATURAL =
            register("otherworld_sapling_natural", () -> new OtherworldSaplingNaturalBlock(new OtherworldNaturalTree(),
                    Block.Properties.of(Material.PLANT).sound(SoundType.GRASS)
                            .strength(0.0f).randomTicks().noCollission()), false, LootTableType.OTHERWORLD_BLOCK);
    public static final RegistryObject<Block> COPPER_ORE = register("copper_ore", () -> new Block(Block.Properties.copy(Blocks.IRON_ORE)));
    public static final RegistryObject<Block> SILVER_ORE = register("silver_ore", () -> new Block(Block.Properties.copy(Blocks.IRON_ORE)));
    public static final RegistryObject<Block> IESNIUM_ORE = register("iesnium_ore", () -> new Block(Block.Properties.copy(Blocks.IRON_ORE)));
    public static final RegistryObject<IesniumOreNaturalBlock> IESNIUM_ORE_NATURAL =
            register("iesnium_ore_natural", () -> new IesniumOreNaturalBlock(Block.Properties.copy(Blocks.IRON_ORE)),
                    true, LootTableType.OTHERWORLD_BLOCK);
    public static final RegistryObject<Block> COPPER_BLOCK = register("copper_block", () -> new Block(Block.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> SILVER_BLOCK = register("silver_block", () -> new Block(Block.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> IESNIUM_BLOCK = register("iesnium_block", () -> new Block(Block.Properties.copy(Blocks.IRON_BLOCK)));

    //Decorative and Ritual Blocks
    public static final RegistryObject<CandleBlock> CANDLE_WHITE = register("candle_white", () -> new CandleBlock(
            Block.Properties.of(Material.DECORATION).sound(SoundType.WOOL).noCollission()
                    .strength(0.1f, 0).lightLevel((state) -> 12)));
    public static final RegistryObject<SpiritAttunedCrystalBlock> SPIRIT_ATTUNED_CRYSTAL =
            register("spirit_attuned_crystal", () -> new SpiritAttunedCrystalBlock(
                    Block.Properties.of(Material.STONE).sound(SoundType.STONE).noOcclusion()
                            .strength(1.5f, 30).lightLevel((state) -> 8)));

    //Machines
    public static final RegistryObject<SacrificialBowlBlock> SACRIFICIAL_BOWL =
            register("sacrificial_bowl", () -> new SacrificialBowlBlock(
                    Block.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5f, 30)
                            .noOcclusion()));
    public static final RegistryObject<GoldenSacrificialBowlBlock> GOLDEN_SACRIFICIAL_BOWL =
            register("golden_sacrificial_bowl", () -> new GoldenSacrificialBowlBlock(
                    Block.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5f, 30)
                            .noOcclusion()));

    public static final RegistryObject<StorageControllerBlock> STORAGE_CONTROLLER = register("storage_controller",
            () -> new StorageControllerBlock(
                    Block.Properties.of(Material.STONE).sound(SoundType.STONE)
                            .strength(5f, 100).noOcclusion()), true, LootTableType.EMPTY);
    public static final RegistryObject<StorageStabilizerBlock> STORAGE_STABILIZER_TIER1 = register(
            "storage_stabilizer_tier1", () -> new StorageStabilizerBlock(
                    Block.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5f, 30)
                            .noOcclusion()));
    public static final RegistryObject<StorageStabilizerBlock> STORAGE_STABILIZER_TIER2 = register(
            "storage_stabilizer_tier2", () -> new StorageStabilizerBlock(
                    Block.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5f, 30)
                            .noOcclusion()));
    public static final RegistryObject<StorageStabilizerBlock> STORAGE_STABILIZER_TIER3 = register(
            "storage_stabilizer_tier3", () -> new StorageStabilizerBlock(
                    Block.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5f, 30)
                            .noOcclusion()));
    public static final RegistryObject<StorageStabilizerBlock> STORAGE_STABILIZER_TIER4 = register(
            "storage_stabilizer_tier4", () -> new StorageStabilizerBlock(
                    Block.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5f, 30)
                            .noOcclusion()));

    public static final RegistryObject<StableWormholeBlock> STABLE_WORMHOLE = register("stable_wormhole",
            () -> new StableWormholeBlock(
                    Block.Properties.of(Material.STONE).sound(SoundType.STONE).noCollission()
                            .strength(2f, 2).noOcclusion()), false, LootTableType.EMPTY);

    public static final RegistryObject<DimensionalMineshaftBlock> DIMENSIONAL_MINESHAFT =
            register("dimensional_mineshaft", () -> new DimensionalMineshaftBlock(
                    Block.Properties.of(Material.STONE).sound(SoundType.STONE)
                            .strength(1.5f, 30)
                            .noOcclusion()
            ));

    //Crops
    public static final RegistryObject<ReplantableCropsBlock> DATURA = register("datura",
            () -> new ReplantableCropsBlock(
                    Block.Properties.of(Material.PLANT).sound(SoundType.CROP).noCollission().randomTicks()
                            //registry object is wrapped in lambda to account for load order and circular dependencies
                            .strength(0, 0), () -> OccultismItems.DATURA_SEEDS.get(),
                    () -> OccultismItems.DATURA.get()), false, LootTableType.REPLANTABLE_CROP);

    //Dummy
    public static final RegistryObject<Block> SKELETON_SKULL_DUMMY = register("skeleton_skull_dummy", () -> new Block(
            Block.Properties.of(Material.DECORATION).strength(1.0F)), false);
    public static final RegistryObject<Block> WITHER_SKELETON_SKULL_DUMMY = register("wither_skeleton_skull_dummy", () -> new Block(
            Block.Properties.of(Material.DECORATION).strength(1.0F)), false);

    //endregion Fields

    //region Static Methods
    public static <I extends Block> RegistryObject<I> register(final String name, final Supplier<? extends I> sup) {
        return register(name, sup, true);
    }

    public static <I extends Block> RegistryObject<I> register(final String name, final Supplier<? extends I> sup,
                                                               boolean generateDefaultBlockItem) {
        return register(name, sup, generateDefaultBlockItem, LootTableType.DROP_SELF);
    }

    public static <I extends Block> RegistryObject<I> register(final String name, final Supplier<? extends I> sup,
                                                               boolean generateDefaultBlockItem,
                                                               LootTableType lootTableType) {
        RegistryObject<I> object = BLOCKS.register(name, sup);
        BLOCK_DATA_GEN_SETTINGS.put(object.getId(), new BlockDataGenSettings(generateDefaultBlockItem, lootTableType));
        return object;
    }
    //endregion Static Methods

    public enum LootTableType {
        EMPTY,
        DROP_SELF,
        REPLANTABLE_CROP,
        OTHERWORLD_BLOCK,
        CUSTOM
    }

    public static class BlockDataGenSettings {
        //region Fields
        public boolean generateDefaultBlockItem;
        public LootTableType lootTableType;
        //endregion Fields

        //region Initialization
        public BlockDataGenSettings(boolean generateDefaultBlockItem,
                                    LootTableType lootTableType) {
            this.generateDefaultBlockItem = generateDefaultBlockItem;
            this.lootTableType = lootTableType;
        }
        //endregion Initialization
    }
}
