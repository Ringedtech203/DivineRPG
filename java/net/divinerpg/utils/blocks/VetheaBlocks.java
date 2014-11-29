package net.divinerpg.utils.blocks;

import net.divinerpg.api.blocks.BlockMod;
import net.divinerpg.api.blocks.BlockModDoor;
import net.divinerpg.api.blocks.BlockModGlass;
import net.divinerpg.api.blocks.BlockModGrass;
import net.divinerpg.api.blocks.BlockModLeaves;
import net.divinerpg.api.blocks.BlockModLog;
import net.divinerpg.api.blocks.BlockModSpawner;
import net.divinerpg.api.blocks.BlockModVine;
import net.divinerpg.blocks.vethea.BlockAcid;
import net.divinerpg.blocks.vethea.BlockHelioticBeam;
import net.divinerpg.blocks.vethea.BlockInfusionTable;
import net.divinerpg.blocks.vethea.BlockKarosCannon;
import net.divinerpg.blocks.vethea.BlockKarosHeatTile;
import net.divinerpg.blocks.vethea.BlockNightmareBed;
import net.divinerpg.blocks.vethea.BlockVetheaPlant;
import net.divinerpg.utils.material.EnumBlockType;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class VetheaBlocks {

    public static final Block             dreamwreckerSpawner    = new BlockModSpawner("dreamWreckerSpawner", "Dreamwrecker", "fireCrystal");
    public static final Block             vermenousSpawner       = new BlockModSpawner("vermenousSpawner", "Vermenous", "fireCrystal");
    public static final Block             twinsSpawner           = new BlockModSpawner("twinsSpawner", "Twins", "fireCrystal");
    public static final Block             biphronSpawner         = new BlockModSpawner("biphronSpawner", "Biphron", "fireCrystal");
    public static final Block             gorgosionSpawner       = new BlockModSpawner("gorgosionSpawner", "Gorgosion", "fireCrystal");

    public static final Block             wreckAltar             = new BlockMod("wreckAltar", 0.7F, DivineRPGTabs.spawner);
    public static final Block             quadroticAltar         = new BlockMod("quadroticAltar", 0.7F, DivineRPGTabs.spawner);
    public static final Block             karosAltar             = new BlockMod("karosAltar", 0.7F, DivineRPGTabs.spawner);
    public static final Block             raglokAltar            = new BlockMod("raglokAltar", 0.7F, DivineRPGTabs.spawner);
    public static final Block             lunicAltar             = new BlockMod("lunicAltar", 0.7F, DivineRPGTabs.spawner);
    
    public static final Block             dreamStone             = new BlockMod("dreamStone", 0.4F, DivineRPGTabs.vethea);
    public static final Block             dreamDirt              = new BlockMod(EnumBlockType.DIRT, "dreamDirt", 0.3F, DivineRPGTabs.vethea);
    public static final Block             dreamGrass             = new BlockModGrass((BlockMod) dreamDirt, "dreamGrass", 0.3F);
    public static final Block             fireCrystal            = new BlockMod(EnumBlockType.GLASS, "fireCrystal", 1.0F, DivineRPGTabs.vethea).setLightLevel(0.7F);

    public static final Block             dreamWoodLog           = new BlockModLog("dreamWoodLog").setCreativeTab(DivineRPGTabs.vethea);
    public static final Block             dreamWoodLeaves        = new BlockModLeaves("dreamWoodLeaves", 0.3F).setCreativeTab(DivineRPGTabs.vethea);
    public static final Block             dreamBricks1           = new BlockMod("dreamBricks1", 1.0F, DivineRPGTabs.vethea);
    public static final Block             dreamBricks2           = new BlockMod("dreamBricks2", 1.0F, DivineRPGTabs.vethea);
    public static final Block             lunaStone              = new BlockMod("lunaStone", 1.0F, DivineRPGTabs.vethea);
    public static final Block             lunaBricks             = new BlockMod("lunaBrick", 1.0F, DivineRPGTabs.vethea);
    public static final Block             metalCaging            = new BlockMod("metalCaging", 1.0F, DivineRPGTabs.vethea);
    public static final Block             dreamLampOn            = new BlockMod(EnumBlockType.GLASS, "dreamLampOn", 1.0F, DivineRPGTabs.vethea).setLightLevel(1);
    public static final Block             dreamLamp              = new BlockMod(EnumBlockType.GLASS, "dreamLampOff", 1.0F, DivineRPGTabs.vethea);
    public static final Block             weedwoodVine           = new BlockModVine("weedWoodVine").setCreativeTab(DivineRPGTabs.vethea);
    public static final Block             blossomingWeedwoodVine = new BlockModVine("blossemedWeedWoodVine").setCreativeTab(DivineRPGTabs.vethea);
    public static final Block             cryptWall              = new BlockMod("cryptWall", 1.0F, DivineRPGTabs.vethea);
    public static final Block             smoothGlass            = new BlockModGlass("smoothGlass", 0.3F).setCreativeTab(DivineRPGTabs.vethea);
    public static final Block             villageLamp            = new BlockMod(EnumBlockType.GLASS, "villageLamp", 1.0F, DivineRPGTabs.vethea).setLightLevel(1);
    public static final Block             cellLamp               = new BlockMod(EnumBlockType.GLASS, "cellLamp", 1.0F, DivineRPGTabs.vethea).setLightLevel(1);
    public static final Block             barredDoor             = new BlockModDoor(EnumBlockType.IRON, "barredDoorBlock", 3.0F, null);
    public static final Block             firelight              = new BlockMod(EnumBlockType.GLASS, "firelight", 1.0F, DivineRPGTabs.vethea).setLightLevel(1).setResistance(1);
    public static final Block             hyrewood               = new BlockModLog("hyreWoodLog").setCreativeTab(DivineRPGTabs.vethea);
    public static final Block             mintwoodLeaves         = new BlockModLeaves("mintwoodLeaves", 0.3F).setCreativeTab(DivineRPGTabs.vethea);
    public static final Block             firewall               = new BlockMod("fireWall", 1.0F, DivineRPGTabs.vethea);
    public static final Block             karosHeatTileGreen     = new BlockKarosHeatTile(false, "karosHeatTileGreen");
    public static final Block             karosHeatTileRed       = new BlockKarosHeatTile(true, "karosHeatTileRed");
    public static final Block             firewood               = new BlockModLog("fireWoodLog").setCreativeTab(DivineRPGTabs.vethea);
    public static final Block             bacterialAcid          = new BlockAcid("bacterialAcid");
    public static final Block             blockAcid              = new BlockAcid("acidBlock");
    public static final Block             everstone              = new BlockMod("greenEverstone", 3.0F, DivineRPGTabs.vethea);
    public static final Block             lunicAcid              = new BlockAcid("lunicAcid");
    public static final Block             firewoodLeaves         = new BlockModLeaves("fireWoodLeaves", 0.3F).setCreativeTab(DivineRPGTabs.vethea);
    public static final Block             hyrewoodLeaves         = new BlockModLeaves("hyreWoodLeaves", 0.3F).setCreativeTab(DivineRPGTabs.vethea);
    public static final Block             gemtopGreen            = new BlockVetheaPlant("greenGemTop");
    public static final Block             purpleDulah            = new BlockVetheaPlant("purpleDulah");
    public static final Block             yellowDulah            = new BlockVetheaPlant("yellowDulah");
    public static final Block             greenDulah             = new BlockVetheaPlant("greenDulah");
    public static final Block             mintwood               = new BlockModLog("mintWoodLog").setCreativeTab(DivineRPGTabs.vethea);
    public static final Block             cracklespike           = new BlockVetheaPlant("crackleSpike");
    public static final Block             fernite                = new BlockVetheaPlant("fernite");
    public static final Block             bulatobe               = new BlockVetheaPlant("bulbatobe");
    public static final Block             shineGrass             = new BlockVetheaPlant("shineGrass").setLightLevel(0.4F);
    //public static final Block             shimmer                = new BlockShimmer().setLightLevel(0.4F).setUnlocalizedName("shimmer").setStepSound(Block.soundGrassFootstep);
    public static final Block             hiveWall               = new BlockMod(EnumBlockType.GLASS, "hiveWalls", 1.0F, DivineRPGTabs.vethea).setLightLevel(0.533F);
    //public static final Block             dreamglow              = new BlockShimmer().setLightLevel(0.4F).setUnlocalizedName("dreamglow").setStepSound(Block.soundGrassFootstep);
    public static final Block             hyrewoodVine           = new BlockModVine("hyreWoodVine").setCreativeTab(DivineRPGTabs.vethea);
    public static final Block             chamberWall            = new BlockMod(EnumBlockType.ROCK, "chamberWall1", false, DivineRPGTabs.vethea);
    public static final Block             chamberWall1           = new BlockMod(EnumBlockType.ROCK, "chamberWall2", false, DivineRPGTabs.vethea);
    public static final Block             chamberWall2           = new BlockMod(EnumBlockType.ROCK, "chamberWall3", false, DivineRPGTabs.vethea);
    public static final Block             whiteEverstone         = new BlockMod(EnumBlockType.ROCK, "whiteEverstone", false, DivineRPGTabs.vethea);
    public static final Block             darkEverstone          = new BlockMod(EnumBlockType.ROCK, "darkEverstone", false, DivineRPGTabs.vethea);
    public static final Block             hungerstone1           = new BlockMod(EnumBlockType.ROCK, "hungerStone1", false, DivineRPGTabs.vethea);
    public static final Block             hungerstone2           = new BlockMod(EnumBlockType.ROCK, "hungerStone2", false, DivineRPGTabs.vethea);
    public static final Block             cryptFloor             = new BlockMod(EnumBlockType.ROCK, "cryptFloor", false, DivineRPGTabs.vethea);
    public static final Block             hallWall               = new BlockMod(EnumBlockType.ROCK, "hallWall", false, DivineRPGTabs.vethea);
    public static final Block             karosBricks            = new BlockMod(EnumBlockType.ROCK, "karosBrick", false, DivineRPGTabs.vethea);
    public static final Block             karosBricks2           = new BlockMod(EnumBlockType.ROCK, "karosBrick2", false, DivineRPGTabs.vethea);
    public static final Block             infusionTable          = new BlockInfusionTable().setName("infusionTable").setBlockUnbreakable();
    public static final BlockHelioticBeam helioticBeam           = (BlockHelioticBeam) new BlockHelioticBeam("helioticBeam");
    public static final BlockKarosCannon  karosCannon            = (BlockKarosCannon) new BlockKarosCannon(Material.rock).setName("karosCannon");
    public static final Block             nightmareBedBlock      = new BlockNightmareBed();
}
