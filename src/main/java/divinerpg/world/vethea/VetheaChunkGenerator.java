package divinerpg.world.vethea;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import divinerpg.*;
import divinerpg.registries.*;
import divinerpg.world.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.biome.*;
import net.minecraft.world.biome.provider.*;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.settings.*;

import java.util.*;

public class VetheaChunkGenerator extends DivineChunkGenerator {
    private final List<VetheaLevelGenerators> levels = new ArrayList<>();
    private int floorHeight = 48;
    private int roofHeight = 16;
    protected Random rand;

    public static final Codec<VetheaChunkGenerator> CODEC = RecordCodecBuilder.create(
            (instance) -> instance.group(
                    BiomeProvider.CODEC.fieldOf("biome_source").forGetter((surfaceChunkGenerator) -> surfaceChunkGenerator.biomeSource),
                    DimensionStructuresSettings.CODEC.fieldOf("structures").forGetter((ChunkGenerator::getSettings))
            ).apply(instance, instance.stable(VetheaChunkGenerator::new)));


    public VetheaChunkGenerator(BiomeProvider provider, DimensionStructuresSettings settings) {
        super(provider, settings);
        this.rand = new Random();



        for (int i = 0; i < 4; i++) {
            VetheaLevelGenerators level = new VetheaLevelGenerators(floorHeight * i, roofHeight);
            customize(level, i);
            levels.add(level);
        }
    }

    @Override
    protected Codec<? extends ChunkGenerator> codec() {
        return CODEC;
    }

    @Override
    public void applyBiomeDecoration(WorldGenRegion region, StructureManager structureManager) {
        int x = region.getCenterX() * 16;
        int z = region.getCenterZ() * 16;
        BlockPos pos = new BlockPos(x, 0, z);
        long worldSeed = region.getSeed();

        Random random = region.getRandom();
        levels.forEach(level -> level.populate(region, random, region.getCenterX(), region.getCenterZ()));

        this.rand.setSeed(region.getSeed());
        long k = this.rand.nextLong() / 2L * 2L + 1L;
        long l = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed(region.getCenterX() * k + region.getCenterZ() * l ^ region.getSeed());

        Biome biome = region.getBiome(new BlockPos(x, 0, z));

        if(this.rand.nextInt(100) == 0) {
            int y = 20 + this.rand.nextInt(8);

//            int houseNumber = this.rand.nextInt(StructureRegistry.HUNGER_HOUSES.length);
//            StructureRegistry.HUNGER_HOUSES[houseNumber].generate(world, this.rand, new BlockPos(x, y, z));
        }
//        levels.forEach(level -> level.generateChunk(, x, z));
        this.rand.setSeed(region.getCenterX() * k + region.getCenterZ() * l ^ region.getSeed());
        super.applyBiomeDecoration(region, structureManager);
    }




    private void customize(VetheaLevelGenerators level, int position) {
        //
        // Crystal lakes
        //
//        WorldGenMinable large = new WorldGenMinable(BlockRegistry.fireCrystal.defaultBlockState(),
//                50,
//                BlockStateMatcher.forBlock(BlockRegistry.dreamGrass));
//
//        WorldGenMinable meduim = new WorldGenMinable(BlockRegistry.fireCrystal.defaultBlockState(),
//                45,
//                BlockStateMatcher.forBlock(BlockRegistry.dreamGrass));
//
//        WorldGenMinable small = new WorldGenMinable(BlockRegistry.fireCrystal.defaultBlockState(),
//                20,
//                BlockStateMatcher.forBlock(BlockRegistry.dreamGrass));

        // surface Y pos for current floor
        int surfaceLevel = position * floorHeight + roofHeight;

//        level.addWorldGen(new FeatureEnhanced(large, 7 * 6, surfaceLevel));
//        level.addWorldGen(new FeatureEnhanced(meduim, 2 * 6, surfaceLevel));
//        level.addWorldGen(new FeatureEnhanced(small, 6, surfaceLevel));

        // ************************************************************************************

        //
        // Trees
        //
        for (int i = 0; i < 7; i++) {
            TemplatePlacer tree = new TemplatePlacer(createForAllLevels(String.format("floatingtree%s", i + 1)),
                    new BlockPos(11, (floorHeight - roofHeight) / 2, 11));

            // trees will be over player height
            level.addWorldGen(new FeatureEnhanced(tree, 5, surfaceLevel + 8));
        }

        // ************************************************************************************

        //
        // Pillar (does not generate on the top layer)
        //
        if(position < 3) {
            level.addWorldGen(new FeatureEnhanced(new VetheaPillarFeature(BlockStateFeatureConfig.CODEC, floorHeight - roofHeight), 24, surfaceLevel));
        }

        //
        // Infusion
        //
        level.addWorldGen(new FeatureEnhanced(new TemplatePlacer(createForAllLevels("infusionoutpost"), true), 43, surfaceLevel));

        //
        // Lamps
        //
        for (int i = 1; i <= 2; i++) {
            level.addWorldGen(new FeatureEnhanced(new TemplatePlacer(createForAllLevels(String.format("lamp%s", i)), new BlockPos(10, 0, 10)), 40, surfaceLevel));
        }

        //
        // Items
        //
        String[] items = {"bow", "hook", "mushroom", "pickaxe", "pointedsquare", "ring", "sword", "trident"};

        for (String item : items) {
            level.addWorldGen(new FeatureEnhanced(new TemplatePlacer(createForAllLevels(item), new BlockPos(3, 0, 16)), 60, surfaceLevel));
        }

        //
        // Level dependent structures
        //
        switch (position) {
            case 0:

//                level.addStructure(new Crypt1(world, 10));
//                level.addStructure(new Crypt2(world, 12));
//
                for (int i = 4; i <= 6; i++) {
                    level.addWorldGen(new FeatureEnhanced(
                            new TemplatePlacer(createForVethea(position, "tree" + i),
                                    true), 24, surfaceLevel));
                }

                addFlowers(level, surfaceLevel, BlockRegistry.greenGemtop, BlockRegistry.purpleGemtop, BlockRegistry.yellowDulah, BlockRegistry.greenDulah);
                break;

            case 1:

//                level.addStructure(new Hive(world, 64));
//                level.addStructure(new Temple1(world, 64));
//                level.addStructure(new Temple2(world, 64));


                level.addWorldGen(new FeatureEnhanced(
                        new TemplatePlacer(createForVethea(position, "tree3"),
                                true), 10, surfaceLevel));

                level.addWorldGen(new FeatureEnhanced(
                        new TemplatePlacer(createForVethea(0, "tree3"),
                                true), 10, surfaceLevel));

                addFlowers(level, surfaceLevel, BlockRegistry.fernite, BlockRegistry.dreamglow, BlockRegistry.shimmer);
                break;

            case 2:

//                level.addStructure(new QuadroticPost(world, 112));
//                level.addStructure(new KarosMadhouse(world, 112));
//
                for (int i = 7; i <= 8; i++) {
                    level.addWorldGen(new FeatureEnhanced(
                            new TemplatePlacer(createForVethea(position, "tree" + i),
                                    true), 24, surfaceLevel));
                }

                addFlowers(level, surfaceLevel, BlockRegistry.shineGrass, BlockRegistry.cracklespike, BlockRegistry.bulbatobe);
                break;

            case 3:
//                level.addStructure(new Evergarden(world, 160));
//                level.addStructure(new RaglokChamber(world, 160));
//                level.addStructure(new WreckHall(world, 160));

                addFlowers(level, surfaceLevel, BlockRegistry.shimmer, BlockRegistry.shineGrass, BlockRegistry.dreamglow);
                break;
        }
    }
    private void addFlowers(VetheaLevelGenerators level, int height, Block... blocks) {
        for (Block block : blocks) {
            DivineRPG.LOGGER.info("tried to spawn flower");
//            level.addWorldGen(new FeatureEnhanced(new WorldGenVetheanFlower(block), 5, height));
        }
    }

    private ResourceLocation createForVethea(int level, String structureFolder) {
        return new ResourceLocation(DivineRPG.MODID, String.format("vethea/layer%s/%s", level + 1, structureFolder));
    }

    private ResourceLocation createForAllLevels(String structureName) {
        return new ResourceLocation(DivineRPG.MODID, String.format("vethea/all_floors/%s", structureName));
    }

}