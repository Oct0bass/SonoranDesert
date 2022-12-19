
package rrajagopal.sonorandesert.common.init.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class WoodRegistry {
    public final String name;

    private DeferredRegister<Block> blockRegistry;
    private DeferredRegister<Item> itemRegistry;

    private RegistryObject<Block> wood;
    private RegistryObject<Block> strippedWood;
    private RegistryObject<Block> log;
    private RegistryObject<Block> strippedLog;
    private RegistryObject<Block> leaves;
    private RegistryObject<Block> planks;
    private RegistryObject<Block> fence;
    private RegistryObject<Block> sign;
    private RegistryObject<Block> wallSign;
    private RegistryObject<Block> door;
    private RegistryObject<Block> trapdoor;
    private RegistryObject<Block> button;

    private RegistryObject<Item> woodItem;
    private RegistryObject<Item> strippedWoodItem;
    private RegistryObject<Item> logItem;
    private RegistryObject<Item> strippedLogItem;
    private RegistryObject<Item> leavesItem;
    private RegistryObject<Item> planksItem;
    private RegistryObject<Item> fenceItem;
    private RegistryObject<Item> signItem;
    private RegistryObject<Item> doorItem;
    private RegistryObject<Item> trapdoorItem;
    private RegistryObject<Item> buttonItem;
    private RegistryObject<Item> boatItem;
    private RegistryObject<Item> chestBoatItem;

    private Boat.Type boat;

    public WoodType type;

    public final MaterialColor topColor;
    public final MaterialColor barkColor;
    //This is ugly and I hate it
    private static Method Blocks_leaves = ObfuscationReflectionHelper.findMethod(Blocks.class, "leaves", SoundType.class);
    private static Constructor Boat$Type_init = ObfuscationReflectionHelper.findConstructor(Boat.Type.class, Block.class, String.class);
    private static LeavesBlock leaves(SoundType soundType) {
        try {
            return (LeavesBlock) Blocks_leaves.invoke(null, soundType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private RegistryObject<Item> registerBlock(RegistryObject<Block> block, CreativeModeTab tab) {
        return registerBlock(block, new Item.Properties().tab(tab));
    }
    private RegistryObject<Item> registerBlock(RegistryObject<Block> block, Item.Properties properties) {
        return itemRegistry.register(block.getId().toString(), () -> new BlockItem(block.get(), properties));
    }
    private static Boat.Type boatType(Block block, String name) {
        try {
            return (Boat.Type) Boat$Type_init.newInstance(block, name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Boolean never(BlockState p_50779_, BlockGetter p_50780_, BlockPos p_50781_, EntityType<?> p_50782_) {
        return false;
    }

    private static RotatedPillarBlock log(MaterialColor p_50789_, MaterialColor p_50790_) {
        return new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, (p_152624_) -> {
            return p_152624_.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? p_50789_ : p_50790_;
        }).strength(2.0F).sound(SoundType.WOOD));
    }

    public WoodRegistry(DeferredRegister<Block> blockRegistry, DeferredRegister<Item> itemRegistry, String name, MaterialColor topColor, MaterialColor barkColor) {
        this.blockRegistry = blockRegistry;
        this.itemRegistry = itemRegistry;
        this.name = name;
        this.topColor = topColor;
        this.barkColor = barkColor;
        this.type = WoodType.register(WoodType.create(name));

        this.registerAll();
    }

    private void registerAll() {
        wood = blockRegistry.register(name + "_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, topColor).strength(2.0F).sound(SoundType.WOOD)));
        strippedWood = blockRegistry.register("stripped_" + name + "_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, topColor).strength(2.0F).sound(SoundType.WOOD)));
        log = blockRegistry.register(name + "_log", () -> log(topColor, barkColor));
        strippedLog = blockRegistry.register("stripped_" + name + "_log", () -> log(topColor, topColor));
        leaves = blockRegistry.register(name + "_leaves", () -> leaves(SoundType.GRASS));
        planks = blockRegistry.register(name + "_planks", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD, topColor).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
        sign = blockRegistry.register(name + "_sign", () -> new StandingSignBlock(BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), type));
        wallSign = blockRegistry.register(name + "_wall_sign", () -> new WallSignBlock(BlockBehaviour.Properties.of(Material.WOOD, log.get().defaultMaterialColor()).noCollission().strength(1.0F).sound(SoundType.WOOD).dropsLike(sign.get()), type));
        fence = blockRegistry.register(name + "_fence", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, planks.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
        door = blockRegistry.register(name + "_door", () -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, planks.get().defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()));
        trapdoor = blockRegistry.register(name + "_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(WoodRegistry::never)));
        button = blockRegistry.register(name + "_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)));

        boat = boatType(planks.get(), name);

        woodItem = registerBlock(wood, CreativeModeTab.TAB_BUILDING_BLOCKS);
        strippedWoodItem = registerBlock(strippedWood, CreativeModeTab.TAB_BUILDING_BLOCKS);
        logItem = registerBlock(log, CreativeModeTab.TAB_BUILDING_BLOCKS);
        strippedLogItem = registerBlock(strippedLog, CreativeModeTab.TAB_BUILDING_BLOCKS);
        leavesItem = registerBlock(leaves, CreativeModeTab.TAB_DECORATIONS);
        planksItem = registerBlock(planks, CreativeModeTab.TAB_BUILDING_BLOCKS);
        signItem = itemRegistry.register(sign.getId().toString(), () -> new SignItem((new Item.Properties()).stacksTo(16).tab(CreativeModeTab.TAB_DECORATIONS), sign.get(), wallSign.get()));
        fenceItem = registerBlock(fence, CreativeModeTab.TAB_BUILDING_BLOCKS);
        doorItem = itemRegistry.register(door.toString(), () -> new DoubleHighBlockItem(door.get(), (new Item.Properties()).tab(CreativeModeTab.TAB_REDSTONE)));
        trapdoorItem = registerBlock(trapdoor, CreativeModeTab.TAB_REDSTONE);
        buttonItem = registerBlock(button, CreativeModeTab.TAB_REDSTONE);

        boatItem = itemRegistry.register(name + "_boat", () -> new BoatItem(false, boat, (new Item.Properties()).stacksTo(1).tab(CreativeModeTab.TAB_TRANSPORTATION)));
        chestBoatItem = itemRegistry.register(name + "_chest_boat", () -> new BoatItem(true, boat, (new Item.Properties()).stacksTo(1).tab(CreativeModeTab.TAB_TRANSPORTATION)));
    }

    public RegistryObject<Block> getWood() {
        return wood;
    }

    public RegistryObject<Block> getStrippedWood() {
        return strippedWood;
    }

    public RegistryObject<Block> getLog() {
        return log;
    }

    public RegistryObject<Block> getStrippedLog() {
        return strippedLog;
    }

    public RegistryObject<Block> getLeaves() {
        return leaves;
    }

    public RegistryObject<Block> getPlanks() {
        return planks;
    }

    public RegistryObject<Block> getFence() {
        return fence;
    }

    public RegistryObject<Block> getSign() {
        return sign;
    }

    public RegistryObject<Block> getWallSign() {
        return wallSign;
    }

    public RegistryObject<Block> getDoor() {
        return door;
    }

    public RegistryObject<Block> getTrapdoor() {
        return trapdoor;
    }

    public RegistryObject<Block> getButton() {
        return button;
    }

    public RegistryObject<Item> getWoodItem() {
        return woodItem;
    }

    public RegistryObject<Item> getStrippedWoodItem() {
        return strippedWoodItem;
    }

    public RegistryObject<Item> getLogItem() {
        return logItem;
    }

    public RegistryObject<Item> getStrippedLogItem() {
        return strippedLogItem;
    }

    public RegistryObject<Item> getLeavesItem() {
        return leavesItem;
    }

    public RegistryObject<Item> getPlanksItem() {
        return planksItem;
    }

    public RegistryObject<Item> getFenceItem() {
        return fenceItem;
    }

    public RegistryObject<Item> getSignItem() {
        return signItem;
    }

    public RegistryObject<Item> getDoorItem() {
        return doorItem;
    }

    public RegistryObject<Item> getTrapdoorItem() {
        return trapdoorItem;
    }

    public RegistryObject<Item> getButtonItem() {
        return buttonItem;
    }

    public RegistryObject<Item> getBoatItem() {
        return boatItem;
    }

    public RegistryObject<Item> getChestBoatItem() {
        return chestBoatItem;
    }
}

