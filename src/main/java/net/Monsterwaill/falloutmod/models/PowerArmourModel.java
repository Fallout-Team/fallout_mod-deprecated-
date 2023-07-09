package net.Monsterwaill.falloutmod.models;// Made with Blockbench 4.7.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.Monsterwaill.falloutmod.FalloutMod;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class PowerArmourModel<T extends LivingEntity> extends HumanoidModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(FalloutMod.MOD_ID, "powerarmourmodel"), "main");
	private final ModelPart hat;
	private final ModelPart left_leg;
	private final ModelPart right_leg;
	private final ModelPart body;
	private final ModelPart left_arm;
	private final ModelPart right_arm;
	private final ModelPart head;

	public PowerArmourModel(ModelPart root) {
		super(root);
		this.hat = root.getChild("hat");
		this.left_leg = root.getChild("left_leg");
		this.right_leg = root.getChild("right_leg");
		this.body = root.getChild("body");
		this.left_arm = root.getChild("left_arm");
		this.right_arm = root.getChild("right_arm");
		this.head = root.getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(17, 59).addBox(0.0F, -8.0F, -2.5F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.1F))
		.texOffs(0, 57).addBox(0.0F, -14.0F, -2.5F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(68, 49).addBox(0.0F, -3.1F, -2.5F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.2F))
		.texOffs(0, 4).addBox(1.0F, -8.75F, 0.75F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(55, 55).addBox(-4.0F, -8.0F, -2.5F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.1F))
		.texOffs(54, 6).addBox(-4.0F, -14.0F, -2.5F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(64, 67).addBox(-4.0F, -3.1F, -2.5F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.2F))
		.texOffs(0, 0).addBox(-3.0F, -8.75F, 0.75F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 29).addBox(9.0F, -27.6194F, -4.4134F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(52, 29).addBox(9.0F, -26.6194F, -5.4134F, 8.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(32, 70).addBox(11.0F, -24.6194F, -5.6634F, 4.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(28, 84).addBox(11.5F, -23.6194F, -5.9134F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(37, 23).addBox(9.0F, -23.0F, -2.5F, 8.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 46).addBox(10.0F, -22.0F, -3.25F, 6.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-13.0F, 25.6194F, 1.9134F));

		PartDefinition body_r1 = body.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(33, 0).addBox(-12.0F, -23.5F, 6.0F, 8.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(21.0F, -2.0814F, -0.6592F, 0.3927F, 0.0F, 0.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(42, 40).addBox(17.25F, -35.0F, -2.5F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(62, 18).addBox(17.25F, -35.0F, -2.5F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.1F))
		.texOffs(34, 59).addBox(17.25F, -28.0F, -2.5F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.2F))
		.texOffs(71, 6).addBox(16.9599F, -37.6892F, -1.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-25.25F, 33.0F, 0.0F));

		PartDefinition right_arm_r1 = left_arm.addOrReplaceChild("right_arm_r1", CubeListBuilder.create().texOffs(29, 12).addBox(0.0F, -24.5F, 6.0F, 7.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.8922F, -11.0256F, -9.5F, 0.0F, 0.0F, -0.3927F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(47, 67).addBox(22.6422F, -27.4744F, -1.5F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.1F))
		.texOffs(59, 38).addBox(22.6422F, -20.4744F, -1.5F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.2F))
		.texOffs(25, 40).addBox(22.6422F, -27.4744F, -1.5F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(71, 12).addBox(23.9271F, -30.1637F, -0.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-18.6422F, 25.4744F, -1.0F));

		PartDefinition left_arm_r1 = right_arm.addOrReplaceChild("left_arm_r1", CubeListBuilder.create().texOffs(25, 29).addBox(-7.0F, -24.5F, 6.0F, 7.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(21.0F, -3.5F, -8.5F, 0.0F, 0.0F, 0.3927F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -35.0F, -4.5F, 8.0F, 9.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(1, 80).addBox(-5.0F, -32.0F, -1.5F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(1, 80).mirror().addBox(4.0F, -32.0F, -1.5F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 18).addBox(-5.0F, -28.5F, -3.5F, 10.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(58, 0).addBox(-4.0F, -32.815F, 1.8627F, 8.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(15, 71).addBox(-3.75F, -34.815F, 2.6127F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(59, 49).addBox(2.9F, -34.815F, 2.2627F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition respirator_r1 = head.addOrReplaceChild("respirator_r1", CubeListBuilder.create().texOffs(0, 69).addBox(-23.0F, -29.0165F, -1.0312F, 4.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(21.0F, -9.2335F, 21.5312F, 0.7854F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		hat.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}