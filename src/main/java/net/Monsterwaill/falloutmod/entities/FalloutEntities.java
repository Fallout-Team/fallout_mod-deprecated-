package net.Monsterwaill.falloutmod.entities;

import net.Monsterwaill.falloutmod.FalloutMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FalloutEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, FalloutMod.MOD_ID);

    public static final RegistryObject<EntityType<PowerArmor>> POWER_ARMOR =
            ENTITY_TYPES.register("power_armor",
                    () -> EntityType.Builder.of(PowerArmor::new, MobCategory.CREATURE)
                            .sized(1, 2.2f)
                            .build(new ResourceLocation(FalloutMod.MOD_ID, "power_armor").toString()));

}
