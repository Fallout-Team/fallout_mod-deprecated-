package net.Monsterwaill.falloutmod.events;

import net.Monsterwaill.falloutmod.FalloutMod;
import net.Monsterwaill.falloutmod.entities.FalloutEntities;
import net.Monsterwaill.falloutmod.entities.PowerArmor;
import net.Monsterwaill.falloutmod.entities.PowerArmourEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FalloutMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(FalloutEntities.POWER_ARMOR.get(), PowerArmor.setAttributes());
        event.put(FalloutEntities.POWER_ARMOR_ENTITY.get(), PowerArmourEntity.createAttributes().build());
    }
}
