package rrajagopal.sonorandesert.datagen.loot;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.resources.ResourceLocation;
import rrajagopal.sonorandesert.datagen.loot.tables.SonoranDesertBlockLootTables;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class SonoranDesertLootTableProvider extends LootTableProvider {
    public SonoranDesertLootTableProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker) {
        for (Map.Entry<ResourceLocation, LootTable> entry : map.entrySet())
            LootTables.validate(validationtracker, entry.getKey(), entry.getValue());
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return ImmutableList.of(Pair.of(SonoranDesertBlockLootTables::new, LootContextParamSets.BLOCK));
    }
}
