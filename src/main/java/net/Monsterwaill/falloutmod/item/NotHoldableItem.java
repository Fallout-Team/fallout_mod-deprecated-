package net.Monsterwaill.falloutmod.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class NotHoldableItem extends Item {
    public NotHoldableItem(Properties properties) {
        super(properties);
    }
    public NotHoldableItem() {
        super(new Properties());
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int p_41407_, boolean p_41408_) {
        super.inventoryTick(stack, level, entity, p_41407_, p_41408_);

        // On exist
        stack.shrink(stack.getCount()); // Dont.
    }
}
