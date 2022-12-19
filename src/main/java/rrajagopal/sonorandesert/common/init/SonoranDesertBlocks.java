package rrajagopal.sonorandesert.common.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rrajagopal.sonorandesert.common.SonoranDesert;
import rrajagopal.sonorandesert.common.block.*;
import rrajagopal.sonorandesert.common.init.util.WoodRegistry;

@SuppressWarnings("unused")
public class SonoranDesertBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SonoranDesert.MODID);

    //Cacti
    public static final RegistryObject<Block> BARREL_CACTUS = BLOCKS.register("barrel_cactus", () -> new BlockBarrelCactus(Block.Properties.copy(Blocks.CACTUS)));
    public static final RegistryObject<Block> PRICKLY_PEAR = BLOCKS.register("prickly_pear", () -> new BlockPricklyPear(Block.Properties.copy(Blocks.CACTUS).noOcclusion()));

    //Bushes
    public static final RegistryObject<Block> CREOSOTE = BLOCKS.register("creosote", () -> new Block(Block.Properties.of(Material.PLANT)));
    public static final RegistryObject<Block> BRITTLEBUSH = BLOCKS.register("brittlebush", () -> new Block(Block.Properties.of(Material.PLANT).noOcclusion()));
    public static final RegistryObject<Block> JOJOBA = BLOCKS.register("jojoba", () -> new Block(Block.Properties.of(Material.PLANT)));

    public static final WoodRegistry MESQUITE = new WoodRegistry(BLOCKS, SonoranDesertItems.ITEMS, "mesquite", MaterialColor.SAND, MaterialColor.WOOD);
}
