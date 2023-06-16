package net.Monsterwaill.falloutmod.models;// Made with Blockbench 4.7.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.Monsterwaill.falloutmod.FalloutMod;
import net.Monsterwaill.falloutmod.block.entities.BlastDoorBlockEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

import static net.Monsterwaill.falloutmod.block.BlastDoorBlock.isOpen;

public class BlastDoorModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(FalloutMod.MOD_ID, "blast_door_model"), "main");
	public static final ResourceLocation TEXTURE = new ResourceLocation(FalloutMod.MOD_ID, "textures/block/blast_door.png");

	private final ModelPart door_top;
	private final ModelPart door_bottom;

	public BlastDoorModel(ModelPart root) {
		this.door_top = root.getChild("door_top");
		this.door_bottom = root.getChild("door_bottom");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition door_top = partdefinition.addOrReplaceChild("door_top", CubeListBuilder.create().texOffs(17, 48).addBox(7.0F, -38.0F, 2.0F, 4.0F, 19.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(17, 48).mirror().addBox(-11.0F, -38.0F, 2.0F, 4.0F, 19.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 0).addBox(-16.0F, -48.0F, 4.0F, 32.0F, 32.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 37).addBox(-16.0F, -16.0F, 3.0F, 32.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 48).addBox(-11.0F, -19.0F, 0.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 48).addBox(7.0F, -19.0F, 0.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 48).addBox(-11.0F, -19.0F, 8.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 48).addBox(7.0F, -19.0F, 8.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, -6.0F));

		PartDefinition cube_r1 = door_top.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(17, 48).mirror().addBox(-2.0F, -9.5F, -1.0F, 4.0F, 19.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(17, 48).mirror().addBox(-20.0F, -9.5F, -1.0F, 4.0F, 19.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-9.0F, -28.5F, 9.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition door_bottom = partdefinition.addOrReplaceChild("door_bottom", CubeListBuilder.create().texOffs(0, 69).addBox(-16.0F, -12.0F, -3.0F, 32.0F, 12.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(29, 48).addBox(-11.0F, -12.0F, -6.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(29, 48).addBox(7.0F, -12.0F, -6.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(29, 48).addBox(-11.0F, -12.0F, 2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(29, 48).addBox(7.0F, -12.0F, 2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		door_top.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		door_bottom.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void render(BlastDoorBlockEntity entity, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		if (isOpen(entity.getBlockState())) {
			if (door_bottom.y < 35) {
				door_bottom.y = door_bottom.y + 0.12f;
			}
			if (door_top.y > -11) {
				door_top.y = door_top.y - 0.37f;
			}
		} else {
			if (door_bottom.y > 24) {
				door_bottom.y = door_bottom.y - 0.16f;
			}
			if (door_top.y < 23.8) {
				door_top.y = door_top.y + 0.5f;
			}
		}
		renderToBuffer(poseStack,vertexConsumer,packedLight,packedOverlay,red,green,blue,alpha);
	}
}