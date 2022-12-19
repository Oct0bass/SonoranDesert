package rrajagopal.sonorandesert.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import rrajagopal.sonorandesert.common.init.SonoranDesertBlocks;
import rrajagopal.sonorandesert.common.init.SonoranDesertItems;
import rrajagopal.sonorandesert.common.init.util.WoodRegistry;

import java.util.function.Consumer;

public class SonoranDesertRecipeProvider extends RecipeProvider {
    public SonoranDesertRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    protected void modEnabledRecipe(Consumer<FinishedRecipe> consumer, FinishedRecipe recipe, String modid) {
        new ConditionalRecipe.Builder().addRecipe(recipe).addCondition(new ModLoadedCondition(modid)).build(consumer, recipe.getId());
    }

    protected static void planksFromLog(Consumer<FinishedRecipe> p_206409_, ItemLike p_206410_, ItemLike p_206411_) {
        ShapelessRecipeBuilder.shapeless(p_206410_, 4).requires(p_206411_).group("planks").unlockedBy("has_log", has(p_206411_)).save(p_206409_);
    }

    protected void woodRecipes(Consumer<FinishedRecipe> consumer, WoodRegistry wood) {
        planksFromLog(consumer, wood.getPlanksItem().get(), wood.getLogItem().get());
        woodFromLogs(consumer, wood.getWoodItem().get(), wood.getLogItem().get());
        woodenBoat(consumer, wood.getBoatItem().get(), wood.getPlanksItem().get());
        generateRecipes(consumer, wood.getFamily());
    }

    protected void shapelessSeeds(Consumer<FinishedRecipe> consumer, ItemLike seed, ItemLike fruit) {
        ShapelessRecipeBuilder.shapeless(seed).requires(fruit).unlockedBy(getHasName(fruit), has(fruit)).save(consumer);
    }

    protected void cooking(Consumer<FinishedRecipe> consumer, ItemLike raw, ItemLike cooked) {
        Ingredient rawIngredient = Ingredient.of(raw);
        String baseName = cooked.asItem().toString();

        SimpleCookingRecipeBuilder.cooking(rawIngredient, cooked, 0.1f, 100, RecipeSerializer.SMOKING_RECIPE).unlockedBy(getHasName(raw), has(raw)).save(consumer, baseName + "_from_smoking");
        SimpleCookingRecipeBuilder.cooking(rawIngredient, cooked, 0.1f, 600, RecipeSerializer.CAMPFIRE_COOKING_RECIPE).unlockedBy(getHasName(raw), has(raw)).save(consumer, baseName + "_from_campfire");
        SimpleCookingRecipeBuilder.smelting(rawIngredient, cooked, 0.2f, 200).unlockedBy(getHasName(raw), has(raw)).save(consumer);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        shapelessSeeds(consumer, SonoranDesertItems.PRICKLY_PEAR_SEEDS.get(), SonoranDesertItems.PRICKLY_PEAR_FRUIT.get());
        shapelessSeeds(consumer, SonoranDesertItems.BARREL_CACTUS_SEEDS.get(), SonoranDesertItems.BARREL_CACTUS_FRUIT.get());

        cooking(consumer, SonoranDesertItems.PRICKLY_PEAR_FRUIT.get(), SonoranDesertItems.DESPINED_PRICKLY_PEAR_FRUIT.get());
        cooking(consumer, SonoranDesertItems.PRICKLY_PEAR_PADDLE.get(), SonoranDesertItems.COOKED_PRICKLY_PEAR_PADDLE.get());
        cooking(consumer, SonoranDesertItems.BARREL_CACTUS_FRUIT.get(), SonoranDesertItems.COOKED_BARREL_CACTUS_FRUIT.get());

        woodRecipes(consumer, SonoranDesertBlocks.MESQUITE);
    }
}
