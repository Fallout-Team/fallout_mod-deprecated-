package net.Monsterwaill.falloutmod.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.Monsterwaill.falloutmod.block.entities.StandBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;

public class StandRenderer implements BlockEntityRenderer<StandBlockEntity> {

    public StandRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(StandBlockEntity standBlockEntity, float p_112308_, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int p_112312_) {
        poseStack.pushPose();
        poseStack.translate(0.4, 0.7, 0.4);

        BlockState blockstate = standBlockEntity.getBlockState();
        float blockRotation = blockstate.getValue(HorizontalDirectionalBlock.FACING).toYRot();
        poseStack.scale(0.6F, 0.6F, 0.6F);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(-blockRotation -180));
        poseStack.mulPose(Vector3f.XP.rotationDegrees(23));
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(0));
        Minecraft.getInstance().getItemRenderer().renderStatic(standBlockEntity.getPipboy(), ItemTransforms.TransformType.FIXED, packedLight, OverlayTexture.NO_OVERLAY, poseStack, bufferSource, Minecraft.getInstance().player.getId());
        poseStack.popPose();
    }
}
