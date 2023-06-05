package net.Monsterwaill.falloutmod.models;// Made with Blockbench 4.7.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class PowerArmor<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("falloutmod", "powerarmor"), "main");
	private final ModelPart LeftLeg;
	private final ModelPart RightLeg;
	private final ModelPart Head;
	private final ModelPart RightArm;
	private final ModelPart LeftArm;
	private final ModelPart Torso;

	public PowerArmor(ModelPart root) {
		this.LeftLeg = root.getChild("LeftLeg");
		this.RightLeg = root.getChild("RightLeg");
		this.Head = root.getChild("Head");
		this.RightArm = root.getChild("RightArm");
		this.LeftArm = root.getChild("LeftArm");
		this.Torso = root.getChild("Torso");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(20, 38).addBox(-1.0F, -13.0F, -2.0F, 0.0F, 11.0F, 2.0F, new CubeDeformation(0.04F))
		.texOffs(13, 31).addBox(-1.0F, -2.0F, -3.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(35, 38).addBox(3.0F, -13.0F, -2.0F, 0.0F, 11.0F, 2.0F, new CubeDeformation(0.04F))
		.texOffs(47, 24).addBox(0.0F, -13.0F, -3.0F, 2.0F, 11.0F, 0.0F, new CubeDeformation(0.04F))
		.texOffs(48, 12).addBox(0.0F, -13.0F, 1.0F, 2.0F, 11.0F, 0.0F, new CubeDeformation(0.04F)), PartPose.offset(1.0F, 24.0F, 1.0F));

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(30, 38).addBox(-5.0F, -13.0F, -2.0F, 0.0F, 11.0F, 2.0F, new CubeDeformation(0.04F))
		.texOffs(30, 31).addBox(-5.0F, -2.0F, -3.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(25, 38).addBox(-1.0F, -13.0F, -2.0F, 0.0F, 11.0F, 2.0F, new CubeDeformation(0.04F))
		.texOffs(45, 38).addBox(-4.0F, -13.0F, -3.0F, 2.0F, 11.0F, 0.0F, new CubeDeformation(0.04F))
		.texOffs(47, 0).addBox(-4.0F, -13.0F, 1.0F, 2.0F, 11.0F, 0.0F, new CubeDeformation(0.04F)), PartPose.offset(1.0F, 24.0F, 1.0F));

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -27.0F, -4.0F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(15, 38).addBox(-9.0F, -25.0F, -2.0F, 0.0F, 11.0F, 2.0F, new CubeDeformation(0.004F))
		.texOffs(0, 28).addBox(-9.0F, -14.0F, -3.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(10, 36).addBox(-5.0F, -25.0F, -2.0F, 0.0F, 11.0F, 2.0F, new CubeDeformation(0.004F))
		.texOffs(43, 12).addBox(-8.0F, -25.0F, -3.0F, 2.0F, 11.0F, 0.0F, new CubeDeformation(0.004F))
		.texOffs(42, 0).addBox(-8.0F, -25.0F, 1.0F, 2.0F, 11.0F, 0.0F, new CubeDeformation(0.004F))
		.texOffs(21, 24).addBox(-9.0F, -27.0F, -3.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 24.0F, 1.0F));

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(5, 35).addBox(3.0F, -25.0F, -2.0F, 0.0F, 11.0F, 2.0F, new CubeDeformation(0.004F))
		.texOffs(25, 11).addBox(3.0F, -14.0F, -3.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(25, 0).addBox(3.0F, -27.0F, -3.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 35).addBox(7.0F, -25.0F, -2.0F, 0.0F, 11.0F, 2.0F, new CubeDeformation(0.004F))
		.texOffs(40, 38).addBox(4.0F, -25.0F, -3.0F, 2.0F, 11.0F, 0.0F, new CubeDeformation(0.004F))
		.texOffs(38, 18).addBox(4.0F, -25.0F, 1.0F, 2.0F, 11.0F, 0.0F, new CubeDeformation(0.004F)), PartPose.offset(1.0F, 24.0F, 1.0F));

		PartDefinition Torso = partdefinition.addOrReplaceChild("Torso", CubeListBuilder.create().texOffs(0, 11).addBox(-4.0F, -25.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		LeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Torso.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}