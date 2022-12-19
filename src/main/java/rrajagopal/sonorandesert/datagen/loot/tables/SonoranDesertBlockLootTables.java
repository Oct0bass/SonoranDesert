package rrajagopal.sonorandesert.datagen.loot.tables;

import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import rrajagopal.sonorandesert.common.init.SonoranDesertBlocks;
import rrajagopal.sonorandesert.common.init.SonoranDesertItems;

import java.util.Set;

public class SonoranDesertBlockLootTables extends BlockLoot {
    protected LootPool.Builder dropRandom(Item item, int min, int max) {
        return LootPool.lootPool().add(LootItem.lootTableItem(item)).apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return Set.of(
                SonoranDesertBlocks.PRICKLY_PEAR.get(),
                SonoranDesertBlocks.BARREL_CACTUS.get()
        );
    }

    @Override
    protected void addTables() {
        this.add(SonoranDesertBlocks.PRICKLY_PEAR.get(), (pricklyPear) -> LootTable.lootTable()
                .withPool(dropRandom(SonoranDesertItems.PRICKLY_PEAR_PADDLE.get(), 2, 5))
                .withPool(dropRandom(SonoranDesertItems.PRICKLY_PEAR_FRUIT.get(), 4, 9)));
        this.add(SonoranDesertBlocks.BARREL_CACTUS.get(), (barrelCactus) -> LootTable.lootTable()
                .withPool(dropRandom(SonoranDesertItems.BARREL_CACTUS_FRUIT.get(), 3, 7)));
    }
}
