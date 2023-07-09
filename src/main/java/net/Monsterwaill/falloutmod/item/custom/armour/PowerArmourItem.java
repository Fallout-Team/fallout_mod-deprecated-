package net.Monsterwaill.falloutmod.item.custom.armour;

import net.Monsterwaill.falloutmod.item.FalloutArmourMaterials;
import net.Monsterwaill.falloutmod.models.PowerArmourModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class PowerArmourItem extends ArmorItem {
    public PowerArmourItem(ArmorMaterial material, EquipmentSlot slot, Properties options) {
        super(material,slot,options);
    }
    public PowerArmourItem(EquipmentSlot slot, Properties options) {
        this(FalloutArmourMaterials.POWER_ARMOUR, slot, options);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        // Rendering the custom armour
        consumer.accept(new IClientItemExtensions() {
            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
//                PowerArmourItem item = (PowerArmourItem) itemStack.getItem(); // frick you craig im casting
                return new PowerArmourModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(PowerArmourModel.LAYER_LOCATION));
            }
        });

        super.initializeClient(consumer);
    }

    @Override
    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return "falloutmod:textures/armour/power.png";
    }
}
