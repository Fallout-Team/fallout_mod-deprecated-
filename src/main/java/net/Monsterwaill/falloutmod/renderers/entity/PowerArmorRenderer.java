package net.Monsterwaill.falloutmod.renderers.entity;

import net.Monsterwaill.falloutmod.FalloutMod;
import net.Monsterwaill.falloutmod.entities.PowerArmor;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class PowerArmorRenderer extends EntityRenderer<PowerArmor> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(FalloutMod.MOD_ID, "textures/entities/power_armor.png");

    public PowerArmorRenderer(EntityRendererProvider.Context p_174008_) {
        super(p_174008_);
    }

    @Override
    public ResourceLocation getTextureLocation(PowerArmor p_114482_) {
        return TEXTURE;
    }
}
