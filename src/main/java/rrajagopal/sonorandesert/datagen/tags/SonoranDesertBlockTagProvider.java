package rrajagopal.sonorandesert.datagen.tags;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import rrajagopal.sonorandesert.common.SonoranDesert;

public class SonoranDesertBlockTagProvider extends BlockTagsProvider {
    public SonoranDesertBlockTagProvider(DataGenerator generator, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        TagKey<Block> cactiPlantableOn = BlockTags.create(new ResourceLocation(SonoranDesert.MODID, "cacti_plantable_on"));

        tag(cactiPlantableOn).add(Blocks.SAND, Blocks.GRAVEL);
    }
}
