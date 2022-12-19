package rrajagopal.sonorandesert.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import rrajagopal.sonorandesert.common.SonoranDesert;
import rrajagopal.sonorandesert.datagen.loot.SonoranDesertLootTableProvider;
import rrajagopal.sonorandesert.datagen.tags.SonoranDesertBlockTagProvider;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = SonoranDesert.MODID)
public class SonoranDesertDataGenerator {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        generator.addProvider(event.includeServer(), new SonoranDesertRecipeProvider(generator));
        generator.addProvider(event.includeServer(), new SonoranDesertLootTableProvider(generator));
        generator.addProvider(event.includeServer(), new SonoranDesertBlockTagProvider(generator, SonoranDesert.MODID, event.getExistingFileHelper()));
    }
}
