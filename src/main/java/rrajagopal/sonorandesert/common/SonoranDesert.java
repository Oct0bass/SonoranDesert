package rrajagopal.sonorandesert.common;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rrajagopal.sonorandesert.common.biome.SonoranDesertRegion;
import rrajagopal.sonorandesert.common.init.SonoranDesertBiomes;
import rrajagopal.sonorandesert.common.init.SonoranDesertBlocks;
import rrajagopal.sonorandesert.common.init.SonoranDesertItems;
import terrablender.api.Regions;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SonoranDesert.MODID)
public class SonoranDesert {
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public static final String MODID = "sonorandesert";

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Regions.register(new SonoranDesertRegion(new ResourceLocation(SonoranDesert.MODID, "overworld"), 2));
        });
    }

    public SonoranDesert() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::commonSetup);
        SonoranDesertBlocks.BLOCKS.register(bus);
        SonoranDesertItems.ITEMS.register(bus);
        SonoranDesertBiomes.BIOMES.register(bus);
    }
}
