package rrajagopal.sonorandesert.common.init;

import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import rrajagopal.sonorandesert.common.SonoranDesert;

public class SonoranDesertBiomes {
    public static DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, SonoranDesert.MODID);

    protected static int calculateSkyColor(float color) {
        float $$1 = color / 3.0F;
        $$1 = Mth.clamp($$1, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
    }

    private static BiomeSpecialEffects.Builder defaultEffects(float temperature) {
        return new BiomeSpecialEffects.Builder().waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(null);
    }
    private static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    private static MobSpawnSettings.Builder sonoranDesertMobSpawnSettings() {
        MobSpawnSettings.Builder settings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.desertSpawns(settings);
        return settings;
    }

    private static BiomeGenerationSettings.Builder sonoranDesertGenerationSettings() {
        BiomeGenerationSettings.Builder settings = new BiomeGenerationSettings.Builder();
        globalOverworldGeneration(settings);
        BiomeDefaultFeatures.addDefaultOres(settings);
        BiomeDefaultFeatures.addDefaultSoftDisks(settings);
        BiomeDefaultFeatures.addDefaultGrass(settings);
        BiomeDefaultFeatures.addFossilDecoration(settings);
        return settings;
    }


    public static RegistryObject<Biome> SONORAN_DESERT = BIOMES.register("sonorandesert", () -> new Biome.BiomeBuilder()
            .precipitation(Biome.Precipitation.RAIN)
            .temperature(2f)
            .downfall(0.1f)
            .specialEffects(defaultEffects(2f).grassColorOverride(0x828555).build())
            .generationSettings(sonoranDesertGenerationSettings().build())
            .mobSpawnSettings(sonoranDesertMobSpawnSettings().build())
            .build());
}
