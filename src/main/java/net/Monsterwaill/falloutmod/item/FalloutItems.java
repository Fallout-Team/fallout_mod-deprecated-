package net.Monsterwaill.falloutmod.item;

import net.Monsterwaill.falloutmod.FalloutMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FalloutItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, FalloutMod.MOD_ID);
    public static final RegistryObject<Item> CAP = ITEMS.register("cap",
            () -> new Item(new Item.Properties().tab(FalloutTabs.FALLOUT_TAB)));
    public static final RegistryObject<Item> HOLOTAPE = ITEMS.register("holotape",
            () -> new Item(new Item.Properties().tab(FalloutTabs.FALLOUT_TAB).stacksTo(1)));
    public static final RegistryObject<Item> CREATIVETAB = ITEMS.register("creativetab",
            () -> new NotHoldableItem(new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus );
    }
}
