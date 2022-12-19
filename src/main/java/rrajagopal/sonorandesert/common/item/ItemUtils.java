package rrajagopal.sonorandesert.common.item;

import net.minecraft.world.food.FoodProperties;

public class ItemUtils {
    //Builders are annoying
    public static FoodProperties createFood(int hunger, float saturation) {
        return new FoodProperties.Builder().nutrition(hunger).saturationMod(saturation).build();
    }
}
