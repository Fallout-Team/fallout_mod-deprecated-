package net.Monsterwaill.falloutmod.item;

import net.Monsterwaill.falloutmod.FalloutMod;
import net.Monsterwaill.falloutmod.item.custom.PipBoyItem;
import net.Monsterwaill.falloutmod.item.custom.armour.PowerArmourItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
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
    public static final RegistryObject<Item> PIPBOY = ITEMS.register("pip_boy",
            () -> new PipBoyItem(new Item.Properties().tab(FalloutTabs.FALLOUT_TAB).stacksTo(1).setNoRepair().fireResistant()));
    public static final RegistryObject<Item> STIMPACK = ITEMS.register("stimpack",
            () -> new Item(new Item.Properties().tab(FalloutTabs.FALLOUT_TAB).stacksTo(1).setNoRepair()));

    // @TODO this could be like automated and stuff but theres only one set for now so no point
    public static final RegistryObject<PowerArmourItem> POWER_HELMET = ITEMS.register("power_helmet",
            () -> new PowerArmourItem(EquipmentSlot.HEAD,new Item.Properties().tab(FalloutTabs.FALLOUT_TAB).stacksTo(1)));
    public static final RegistryObject<PowerArmourItem> POWER_CHESTPLATE = ITEMS.register("power_chestplate",
            () -> new PowerArmourItem(EquipmentSlot.CHEST,new Item.Properties().tab(FalloutTabs.FALLOUT_TAB).stacksTo(1)));
    public static final RegistryObject<PowerArmourItem> POWER_LEGS = ITEMS.register("power_leggings",
            () -> new PowerArmourItem(EquipmentSlot.LEGS,new Item.Properties().tab(FalloutTabs.FALLOUT_TAB).stacksTo(1)));
    public static final RegistryObject<PowerArmourItem> POWER_BOOTS = ITEMS.register("power_boots",
            () -> new PowerArmourItem(EquipmentSlot.FEET,new Item.Properties().tab(FalloutTabs.FALLOUT_TAB).stacksTo(1)));
}
