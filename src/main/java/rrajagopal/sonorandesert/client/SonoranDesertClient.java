package rrajagopal.sonorandesert.client;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import rrajagopal.sonorandesert.common.SonoranDesert;
import rrajagopal.sonorandesert.common.init.SonoranDesertBlocks;

@Mod.EventBusSubscriber(value=Dist.CLIENT, modid=SonoranDesert.MODID, bus=Mod.EventBusSubscriber.Bus.MOD)
public class SonoranDesertClient {
    @SubscribeEvent
    public static void setRenderTypes(FMLClientSetupEvent e) {
        SonoranDesert.LOGGER.debug("Setting render types.");
        ItemBlockRenderTypes.setRenderLayer(SonoranDesertBlocks.PRICKLY_PEAR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(SonoranDesertBlocks.BARREL_CACTUS.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(SonoranDesertBlocks.BRITTLEBUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(SonoranDesertBlocks.CREOSOTE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(SonoranDesertBlocks.JOJOBA.get(), RenderType.cutout());
    }
}
