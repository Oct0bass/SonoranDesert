
package rrajagopal.sonorandesert.common.init.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.data.BlockFamilies;
import net.minecraft.data.BlockFamily;
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
    private RegistryObject<Block> slab;
    private RegistryObject<Block> stairs;
    private RegistryObject<Block> fence;
    private RegistryObject<Block> fenceGate;
    private RegistryObject<Block> sign;
    private RegistryObject<Block> wallSign;
    private RegistryObject<Block> door;
    private RegistryObject<Block> trapdoor;
    private RegistryObject<Block> button;
    private RegistryObject<Block> pressurePlate;

    private RegistryObject<Item> woodItem;
    private RegistryObject<Item> strippedWoodItem;
    private RegistryObject<Item> logItem;
    private RegistryObject<Item> strippedLogItem;
    private RegistryObject<Item> leavesItem;
    private RegistryObject<Item> planksItem;
    private RegistryObject<Item> slabItem;
    private RegistryObject<Item> stairsItem;
    private RegistryObject<Item> fenceItem;
    private RegistryObject<Item> fenceGateItem;
    private RegistryObject<Item> signItem;
    private RegistryObject<Item> doorItem;
    private RegistryObject<Item> trapdoorItem;
    private RegistryObject<Item> buttonItem;
    private RegistryObject<Item> pressurePlateItem;
    private RegistryObject<Item> boatItem;
    private RegistryObject<Item> chestBoatItem;

    private Boat.Type boat;

    public WoodType type;
    private BlockFamily family;

    public final MaterialColor topColor;
    public final MaterialColor barkColor;
    //This is ugly and I hate it
    private static Method Blocks_leaves = ObfuscationReflectionHelper.findMethod(Blocks.class, "leaves", SoundType.class);
    private static Method BlockFamilies_familyBuilder = ObfuscationReflectionHelper.findMethod(BlockFamilies.class, "familyBuilder", Block.class);
    private static LeavesBlock leaves(SoundType soundType) {
        try {
            return (LeavesBlock) Blocks_leaves.invoke(null, soundType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private static BlockFamily.Builder familyBuilder(Block block) {
        try {
            return (BlockFamily.Builder) BlockFamilies_familyBuilder.invoke(null, block);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private RegistryObject<Item> registerBlock(RegistryObject<Block> block, CreativeModeTab tab) {
        return registerBlock(block, new Item.Properties().tab(tab));
    }
    private RegistryObject<Item> registerBlock(RegistryObject<Block> block, Item.Properties properties) {
        return itemRegistry.register(block.getId().getPath(), () -> new BlockItem(block.get(), properties));
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
        stairs = blockRegistry.register(name + "_stairs", () -> new StairBlock(() -> planks.get().defaultBlockState(), BlockBehaviour.Properties.copy(planks.get())));
        slab = blockRegistry.register(name + "_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, topColor).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
        sign = blockRegistry.register(name + "_sign", () -> new StandingSignBlock(BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), type));
        wallSign = blockRegistry.register(name + "_wall_sign", () -> new WallSignBlock(BlockBehaviour.Properties.of(Material.WOOD, log.get().defaultMaterialColor()).noCollission().strength(1.0F).sound(SoundType.WOOD).dropsLike(sign.get()), type));
        fence = blockRegistry.register(name + "_fence", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, planks.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
        fenceGate = blockRegistry.register(name + "_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, planks.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
        door = blockRegistry.register(name + "_door", () -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, planks.get().defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()));
        trapdoor = blockRegistry.register(name + "_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(WoodRegistry::never)));
        button = blockRegistry.register(name + "_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)));
        pressurePlate = blockRegistry.register(name + "_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, planks.get().defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)));

        woodItem = registerBlock(wood, CreativeModeTab.TAB_BUILDING_BLOCKS);
        strippedWoodItem = registerBlock(strippedWood, CreativeModeTab.TAB_BUILDING_BLOCKS);
        logItem = registerBlock(log, CreativeModeTab.TAB_BUILDING_BLOCKS);
        strippedLogItem = registerBlock(strippedLog, CreativeModeTab.TAB_BUILDING_BLOCKS);
        leavesItem = registerBlock(leaves, CreativeModeTab.TAB_DECORATIONS);
        planksItem = registerBlock(planks, CreativeModeTab.TAB_BUILDING_BLOCKS);
        stairsItem = registerBlock(stairs, CreativeModeTab.TAB_BUILDING_BLOCKS);
        slabItem = registerBlock(slab, CreativeModeTab.TAB_BUILDING_BLOCKS);
        signItem = itemRegistry.register(sign.getId().getPath(), () -> new SignItem((new Item.Properties()).stacksTo(16).tab(CreativeModeTab.TAB_DECORATIONS), sign.get(), wallSign.get()));
        fenceItem = registerBlock(fence, CreativeModeTab.TAB_BUILDING_BLOCKS);
        fenceGateItem = registerBlock(fenceGate, CreativeModeTab.TAB_REDSTONE);
        doorItem = itemRegistry.register(door.getId().getPath(), () -> new DoubleHighBlockItem(door.get(), (new Item.Properties()).tab(CreativeModeTab.TAB_REDSTONE)));
        trapdoorItem = registerBlock(trapdoor, CreativeModeTab.TAB_REDSTONE);
        buttonItem = registerBlock(button, CreativeModeTab.TAB_REDSTONE);
        pressurePlateItem = registerBlock(pressurePlate, CreativeModeTab.TAB_REDSTONE);
        //TODO: Custom boats
        boatItem = itemRegistry.register(name + "_boat", () -> new BoatItem(false, Boat.Type.OAK, (new Item.Properties()).stacksTo(1).tab(CreativeModeTab.TAB_TRANSPORTATION)));
        chestBoatItem = itemRegistry.register(name + "_chest_boat", () -> new BoatItem(true, Boat.Type.OAK, (new Item.Properties()).stacksTo(1).tab(CreativeModeTab.TAB_TRANSPORTATION)));
    }

    public BlockFamily getFamily() {
        if (family == null) {
            family = familyBuilder(planks.get()).slab(slab.get()).button(button.get()).door(door.get()).fence(fence.get()).fenceGate(fenceGate.get()).sign(sign.get(), wallSign.get()).stairs(stairs.get()).trapdoor(trapdoor.get()).pressurePlate(pressurePlate.get())
                    .recipeGroupPrefix("wooden").recipeUnlockedBy("has_planks").getFamily();
        }
        return family;
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

    public RegistryObject<Block> getStairs() {
        return stairs;
    }

    public RegistryObject<Block> getSlab() {
        return slab;
    }

    public RegistryObject<Block> getFence() {
        return fence;
    }

    public RegistryObject<Block> getFenceGate() {
        return fenceGate;
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

    public RegistryObject<Block> getPressurePlate() {
        return pressurePlate;
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

    public RegistryObject<Item> getStairsItem() {
        return stairsItem;
    }

    public RegistryObject<Item> getSlabItem() {
        return slabItem;
    }

    public RegistryObject<Item> getFenceItem() {
        return fenceItem;
    }

    public RegistryObject<Item> getFenceGateItem() {
        return fenceGateItem;
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

    public RegistryObject<Item> getPressurePlateItem() {
        return pressurePlateItem;
    }

    public RegistryObject<Item> getBoatItem() {
        return boatItem;
    }

    public RegistryObject<Item> getChestBoatItem() {
        return chestBoatItem;
    }
}
