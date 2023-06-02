package net.Monsterwaill.falloutmod.models;// Made with Blockbench 4.7.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.Monsterwaill.falloutmod.FalloutMod;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class TARDISModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(FalloutMod.MOD_ID, "tardis_exterior"), "main");
	public static final ResourceLocation TEXTURE = new ResourceLocation(FalloutMod.MOD_ID, "textures/block/tardis.png");
	private final ModelPart box;

	public TARDISModel(ModelPart root) {
		this.box = root.getChild("box");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition box = partdefinition.addOrReplaceChild("box", CubeListBuilder.create().texOffs(362, 172).addBox(-16.58F, -79.7F, -21.62F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(346, 149).addBox(23.48F, -79.7F, -21.62F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(354, 118).addBox(23.455F, -79.7F, 18.79F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(368, 150).addBox(-16.53F, -79.7F, 18.665F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 22.0F, 0.0F));

		PartDefinition Base = box.addOrReplaceChild("Base", CubeListBuilder.create().texOffs(0, 0).addBox(-24.045F, -2.16F, -24.455F, 49.0F, 4.0F, 49.0F, new CubeDeformation(0.0F)), PartPose.offset(4.56F, 0.16F, 0.0F));

		PartDefinition roof_stacks_and_lamp = box.addOrReplaceChild("roof_stacks_and_lamp", CubeListBuilder.create().texOffs(138, 156).addBox(-20.0176F, -81.6226F, -20.3F, 41.0F, 5.0F, 41.0F, new CubeDeformation(0.0F))
				.texOffs(2, 169).addBox(-18.0326F, -83.5726F, -18.285F, 37.0F, 3.0F, 37.0F, new CubeDeformation(0.0F))
				.texOffs(229, 202).addBox(-15.0626F, -84.3326F, -15.255F, 31.0F, 1.0F, 31.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5776F, 0.5026F, -0.2F));

		PartDefinition lamp = roof_stacks_and_lamp.addOrReplaceChild("lamp", CubeListBuilder.create().texOffs(18, 28).addBox(-1.5019F, -91.8296F, -1.4731F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.075F, 0.325F, -0.3F));

		PartDefinition outer_lamp_casing = lamp.addOrReplaceChild("outer_lamp_casing", CubeListBuilder.create().texOffs(0, 0).addBox(-3.4788F, -85.0985F, -3.4712F, 8.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 12).addBox(-3.1354F, -93.0451F, -2.8396F, 7.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.2783F, 0.0F));

		PartDefinition cube_r1 = outer_lamp_casing.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(20, 12).addBox(0.0F, -92.7164F, -4.4528F, 0.0F, 7.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.525F, 0.6283F, 0.5F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r2 = outer_lamp_casing.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(20, 12).addBox(0.0F, -92.7164F, -4.4528F, 0.0F, 7.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.525F, 0.6283F, 0.5F, 0.0F, 0.7854F, 0.0F));

		PartDefinition inner_lamp_casing = lamp.addOrReplaceChild("inner_lamp_casing", CubeListBuilder.create().texOffs(10, 76).addBox(-1.9986F, -86.0598F, -1.9764F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(10, 76).addBox(-1.9986F, -92.5598F, -1.9764F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(30, 8).addBox(-1.4986F, -86.8098F, -1.4764F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.1F))
				.texOffs(30, 8).addBox(-1.4986F, -91.8098F, -1.4764F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.1F))
				.texOffs(0, 52).addBox(0.525F, -92.2914F, -2.9528F, 0.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.025F, 0.0F));

		PartDefinition cube_r3 = inner_lamp_casing.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 52).addBox(0.0F, -92.7164F, -3.4528F, 0.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.525F, 0.425F, 0.5F, 0.0F, -1.5708F, 0.0F));

		PartDefinition door_frame_stacks = box.addOrReplaceChild("door_frame_stacks", CubeListBuilder.create().texOffs(129, 111).addBox(-20.69F, -60.9F, -21.835F, 43.0F, 1.0F, 43.0F, new CubeDeformation(0.1F))
				.texOffs(148, 0).addBox(-20.715F, -60.1F, -21.76F, 43.0F, 1.0F, 43.0F, new CubeDeformation(0.0F))
				.texOffs(130, 54).addBox(-20.19F, -59.5F, -21.185F, 42.0F, 2.0F, 42.0F, new CubeDeformation(-0.1F)), PartPose.offset(4.56F, -9.96F, 0.0F));

		PartDefinition front = door_frame_stacks.addOrReplaceChild("front", CubeListBuilder.create().texOffs(177, 308).addBox(17.06F, -59.8F, -20.71F, 1.0F, 68.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(186, 308).addBox(-17.19F, -59.8F, -20.71F, 1.0F, 68.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left = door_frame_stacks.addOrReplaceChild("left", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r4 = left.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(141, 308).addBox(16.91F, -59.8F, -20.81F, 1.0F, 68.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r5 = left.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(132, 308).addBox(-17.84F, -59.8F, -20.81F, 1.0F, 68.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4F, 0.0F, 0.3F, 0.0F, 1.5708F, 0.0F));

		PartDefinition right = door_frame_stacks.addOrReplaceChild("right", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r6 = right.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(150, 308).addBox(-17.84F, -59.8F, 18.14F, 1.0F, 68.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4F, 0.0F, 0.3F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r7 = right.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(303, 128).addBox(16.91F, -59.8F, 18.14F, 1.0F, 68.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4F, 0.0F, -0.05F, 0.0F, 1.5708F, 0.0F));

		PartDefinition back = door_frame_stacks.addOrReplaceChild("back", CubeListBuilder.create().texOffs(123, 308).addBox(17.31F, -59.8F, 18.09F, 1.0F, 68.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(168, 308).addBox(-17.44F, -59.8F, 18.09F, 1.0F, 68.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition ppcb_signs = box.addOrReplaceChild("ppcb_signs", CubeListBuilder.create().texOffs(0, 111).addBox(-19.03F, -32.68F, -24.92F, 39.0F, 6.0F, 50.0F, new CubeDeformation(0.0F)), PartPose.offset(4.56F, -43.75F, 0.0F));

		PartDefinition cube_r8 = ppcb_signs.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 111).addBox(-19.905F, -32.68F, -25.095F, 39.0F, 6.0F, 50.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4F, 0.0F, 0.3F, 0.0F, -1.5708F, 0.0F));

		PartDefinition pillars = box.addOrReplaceChild("pillars", CubeListBuilder.create().texOffs(99, 302).addBox(-22.08F, -66.88F, -22.6F, 5.0F, 77.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(301, 234).addBox(17.9F, -66.88F, -22.6F, 5.0F, 77.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(73, 297).addBox(17.9F, -66.88F, 17.405F, 5.0F, 77.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(302, 45).addBox(-22.08F, -66.88F, 17.405F, 5.0F, 77.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(4.56F, -10.88F, 0.0F));

		PartDefinition sides = box.addOrReplaceChild("sides", CubeListBuilder.create().texOffs(117, 202).addBox(20.14F, -60.72F, -17.03F, 1.0F, 69.0F, 35.0F, new CubeDeformation(0.0F))
				.texOffs(109, 209).addBox(20.31F, -58.12F, -0.135F, 1.0F, 66.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(190, 202).addBox(-19.84F, -60.72F, -17.63F, 1.0F, 69.0F, 35.0F, new CubeDeformation(0.0F))
				.texOffs(194, 307).addBox(-20.11F, -58.17F, -0.235F, 1.0F, 66.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(4.56F, -9.04F, -0.1F));

		PartDefinition cube_r9 = sides.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(199, 307).addBox(-20.235F, -58.12F, -0.485F, 1.0F, 66.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(-1, 209).addBox(-20.04F, -60.72F, -17.905F, 1.0F, 69.0F, 35.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4F, 0.0F, 0.4F, 0.0F, 1.5708F, 0.0F));

		PartDefinition left_door = box.addOrReplaceChild("left_door", CubeListBuilder.create(), PartPose.offset(-18.5F, -48.0F, -21.5F));

		PartDefinition pto_handle_r1 = left_door.addOrReplaceChild("pto_handle_r1", CubeListBuilder.create().texOffs(276, 354).addBox(14.7F, -35.38F, -27.135F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(36.34F, 39.42F, 16.34F, 0.0F, 1.5708F, 0.0F));

		PartDefinition door_divider_r1 = left_door.addOrReplaceChild("door_divider_r1", CubeListBuilder.create().texOffs(204, 307).addBox(13.555F, -58.42F, -0.61F, 1.0F, 66.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(23.46F, 38.96F, 15.42F, 0.0F, 1.5708F, 0.0F));

		PartDefinition left_door_r1 = left_door.addOrReplaceChild("left_door_r1", CubeListBuilder.create().texOffs(265, 239).addBox(14.18F, -58.645F, -17.23F, 1.0F, 66.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(23.46F, 38.96F, 16.34F, 0.0F, 1.5708F, 0.0F));

		PartDefinition right_door = box.addOrReplaceChild("right_door", CubeListBuilder.create(), PartPose.offset(18.5F, -48.0F, -21.45F));

		PartDefinition lock_r1 = right_door.addOrReplaceChild("lock_r1", CubeListBuilder.create().texOffs(-1, 20).addBox(14.318F, -36.645F, -12.2F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(15.1F, -33.66F, -12.25F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(73, 213).addBox(14.18F, -58.72F, -12.88F, 1.0F, 66.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.66F, 38.96F, 16.29F, 0.0F, 1.5708F, 0.0F));

		PartDefinition bone = box.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 512, 512);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		box.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}