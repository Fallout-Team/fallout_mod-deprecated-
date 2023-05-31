package net.Monsterwaill.falloutmod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class modCreativeModeTab {
    public static final CreativeModeTab FALLOUT_TAB = new CreativeModeTab("fallouttab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(modItems.CREATIVETAB.get());
        }
    };
}
