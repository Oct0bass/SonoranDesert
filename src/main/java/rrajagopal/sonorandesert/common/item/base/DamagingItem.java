package rrajagopal.sonorandesert.common.item.base;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.level.Level;

public class DamagingItem extends Item {
    private int damage;

    public DamagingItem(Item.Properties properties, int damage) {
        super(properties);
        this.damage = damage;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {
        entityLiving.hurt(DamageSource.CACTUS, this.damage);
        return super.finishUsingItem(stack, worldIn, entityLiving);
    }
}
