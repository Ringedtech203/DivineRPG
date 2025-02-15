package divinerpg;

import divinerpg.compat.ModCompat;
import divinerpg.config.*;
import divinerpg.events.*;
import divinerpg.registries.*;
import divinerpg.util.*;
import divinerpg.util.vanilla.*;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.*;

import static divinerpg.registries.BlockRegistry.BLOCK_ITEMS;

@Mod(DivineRPG.MODID)
public class DivineRPG {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "divinerpg";

    public DivineRPG() {
        var bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(DivineTab::registerTabs);
        BlockRegistry.BLOCKS.register(bus);
        ItemRegistry.ITEMS.register(bus);
        BLOCK_ITEMS.register(bus);
        BlockEntityRegistry.BLOCK_ENTITIES.register(bus);
        FluidRegistry.FLUIDS.register(bus);
        FluidRegistry.FLUID_TYPES.register(bus);
        MenuTypeRegistry.CONTAINERS.register(bus);
        EntityRegistry.ENTITIES.register(bus);
        ParticleRegistry.PARTICLES.register(bus);
        RecipeRegistry.Serailizers.SERIALIZER.register(bus);
        SoundRegistry.SOUNDS.register(bus);
        EnchantmentRegistry.ENCHANTS.register(bus);
        MobEffectRegistry.EFFECTS.register(bus);
        RecipeRegistry.Types.RECIPE_TYPES.register(bus);
        FeatureRegistry.FEATURES.register(bus);
        StructureRegistry.STRUCTURE_TYPE.register(bus);
        PaintingRegistry.PAINTING_VARIANTS.register(bus);
        PointOfInterestRegistry.POI.register(bus);
        LootModifierRegistry.GLOBAL_LOOT_MODIFIERS.register(bus);
        EventRegistry.init();
        bus.addListener(this::setup);
        bus.addListener(this::post);
        bus.addListener(this::client);
        MinecraftForge.EVENT_BUS.addGenericListener(Entity.class, AttatchCapabilityEvent::onAttachCapabilitiesPlayer);
        MinecraftForge.EVENT_BUS.addListener(AttatchCapabilityEvent::onRegisterCapabilities);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC, DivineRPG.MODID + "/divinerpg-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfig.SPEC, DivineRPG.MODID + "/divinerpg-common.toml");
        NetworkingRegistry.register();

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        ModCompat.initCommon(event);
        TriggerRegistry.registerTriggers();
        PlacementModifierRegistry.init();

        event.enqueueWork(() -> {
            LogStripper.setup(event);
            PlantPots.setup(event);
        });
        Compostables.setup(event);
    }
    private void client(final FMLClientSetupEvent event) {
        ModelPropRegistry.init();
        MenuTypeRegistry.registerScreenFactories();
        BlockEntityRegistry.renderTiles();
        MinecraftForge.EVENT_BUS.register(new ArcanaRenderer());
        MinecraftForge.EVENT_BUS.register(new EventClientLogin());
        Utils.loadHatInformation();
    }
    private void post(final FMLLoadCompleteEvent event){
    }
}