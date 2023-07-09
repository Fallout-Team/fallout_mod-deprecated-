package net.Monsterwaill.falloutmod.models;

import net.Monsterwaill.falloutmod.FalloutMod;
import net.Monsterwaill.falloutmod.entities.HumanoidEntity;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;

// This class practically already existed as PlayerModel, so im just making it extend PlayerModel instead.
public class HumanoidEntityModel extends PlayerModel<HumanoidEntity> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(FalloutMod.MOD_ID, "humanoid_entity"),"main");
	public HumanoidEntityModel(ModelPart p_170821_) {
		super(p_170821_, true);
	}
}