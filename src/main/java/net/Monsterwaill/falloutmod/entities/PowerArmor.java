package net.Monsterwaill.falloutmod.entities;

import net.Monsterwaill.falloutmod.data.server.FalloutBlockTags;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PowerArmor extends LivingEntity {

    protected PowerArmor(EntityType<? extends LivingEntity> p_20966_, Level p_20967_) {
        super(p_20966_, p_20967_);
    }

    public static AttributeSupplier setAttributes() {
        return PowerArmor.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).build();
    }

    public static AttributeSupplier.Builder createMobAttributes() {
        return LivingEntity.createLivingAttributes().add(Attributes.FOLLOW_RANGE, 16.0D).add(Attributes.ATTACK_KNOCKBACK);
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    public Iterable<ItemStack> getArmorSlots() {
        return null;
    }

    @Override
    public ItemStack getItemBySlot(EquipmentSlot p_21127_) {
        return null;
    }

    @Override
    public void setItemSlot(EquipmentSlot p_21036_, ItemStack p_21037_) {

    }

    @Override
    public HumanoidArm getMainArm() {
        return null;
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return null;
    }
}
