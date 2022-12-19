package rrajagopal.sonorandesert.common.init;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rrajagopal.sonorandesert.common.SonoranDesert;
import rrajagopal.sonorandesert.common.item.ItemUtils;
import rrajagopal.sonorandesert.common.item.base.DamagingItem;


@SuppressWarnings("unused")
public class SonoranDesertItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SonoranDesert.MODID);

    public static final RegistryObject<Item> PRICKLY_PEAR_FRUIT = ITEMS.register("prickly_pear_fruit", () -> new DamagingItem(new Item.Properties().food(ItemUtils.createFood(1, 0f)).tab(CreativeModeTab.TAB_FOOD), 1));
    public static final RegistryObject<Item> DESPINED_PRICKLY_PEAR_FRUIT = ITEMS.register("despined_prickly_pear_fruit", () -> new Item(new Item.Properties().food(ItemUtils.createFood(2, 2f)).tab(CreativeModeTab.TAB_FOOD)));
    public static final RegistryObject<Item> PRICKLY_PEAR_PADDLE = ITEMS.register("prickly_pear_paddle", () -> new DamagingItem(new Item.Properties().food(ItemUtils.createFood(2,1f)).tab(CreativeModeTab.TAB_FOOD), 3));
    public static final RegistryObject<Item> COOKED_PRICKLY_PEAR_PADDLE = ITEMS.register("cooked_prickly_pear_paddle", () -> new Item(new Item.Properties().food(ItemUtils.createFood(5, 3f)).tab(CreativeModeTab.TAB_FOOD)));

    public static final RegistryObject<Item> BARREL_CACTUS_FRUIT = ITEMS.register("barrel_cactus_fruit", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<Item> COOKED_BARREL_CACTUS_FRUIT = ITEMS.register("cooked_barrel_cactus_fruit", () -> new Item(new Item.Properties().food(ItemUtils.createFood(3, 2f)).tab(CreativeModeTab.TAB_FOOD)));

    public static final RegistryObject<Item> PRICKLY_PEAR_SEEDS = ITEMS.register("prickly_pear_seeds", () -> new ItemNameBlockItem(SonoranDesertBlocks.PRICKLY_PEAR.get(), new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<Item> BARREL_CACTUS_SEEDS = ITEMS.register("barrel_cactus_seeds", () -> new ItemNameBlockItem(SonoranDesertBlocks.BARREL_CACTUS.get(), new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));

    public static final RegistryObject<Item> BRITTLEBUSH = ITEMS.register("brittlebush", () -> new BlockItem(SonoranDesertBlocks.BRITTLEBUSH.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Item> JOJOBA = ITEMS.register("jojoba", () -> new BlockItem(SonoranDesertBlocks.JOJOBA.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Item> CREOSOTE = ITEMS.register("creosote", () -> new BlockItem(SonoranDesertBlocks.CREOSOTE.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
}
