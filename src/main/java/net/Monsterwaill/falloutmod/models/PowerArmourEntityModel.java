package net.Monsterwaill.falloutmod.models;

import net.Monsterwaill.falloutmod.FalloutMod;
import net.Monsterwaill.falloutmod.entities.HumanoidEntity;
import net.Monsterwaill.falloutmod.entities.PowerArmourEntity;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;

// This class practically already existed as PlayerModel, so im just making it extend PlayerModel instead.
public class PowerArmourEntityModel extends PlayerModel<PowerArmourEntity> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(FalloutMod.MOD_ID, "power_armour_entity"),"main");
	public PowerArmourEntityModel(ModelPart p_170821_) {
		super(p_170821_, true);
	}
}