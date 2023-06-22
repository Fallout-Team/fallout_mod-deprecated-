package net.Monsterwaill.falloutmod.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.Monsterwaill.falloutmod.block.entities.BlastDoorBlockEntity;
import net.Monsterwaill.falloutmod.block.entities.TARDISBlockEntity;
import net.Monsterwaill.falloutmod.models.BlastDoorModel;
import net.Monsterwaill.falloutmod.models.TARDISModel;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import static net.Monsterwaill.falloutmod.block.BlastDoorBlock.isOpen;

public class BlastDoorRenderer implements BlockEntityRenderer<BlastDoorBlockEntity> {
    private BlastDoorModel<?> model;
    public BlastDoorRenderer(BlockEntityRendererProvider.Context context) {
        this.model = new BlastDoorModel<>(context.bakeLayer(BlastDoorModel.LAYER_LOCATION));
    }

    @Override
    public void render(BlastDoorBlockEntity entity, float partialTick, PoseStack stack, MultiBufferSource source, int packedLight, int packedOverlay) {
        Direction direction = (entity.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING));

        stack.mulPose(Vector3f.YP.rotationDegrees(direction.toYRot()));
        stack.mulPose(Vector3f.XN.rotationDegrees(180.0f));

//        stack.scale(0.5f,0.5f,0.5f);
        stack.translate(0, -1.5f, 0);

        if (direction == Direction.NORTH) {
            stack.translate(0,0,0.5);
        }
        else if (direction == Direction.SOUTH) {
            stack.translate(0,0,-0.5);
        }
        else if (direction == Direction.EAST) {
            stack.translate(-0,0,0.5);
        }
        else if (direction == Direction.WEST) {
            stack.translate(0,0,-0.5);
        }

        this.model.render(entity,stack, source.getBuffer(RenderType.entityTranslucentCull(BlastDoorModel.TEXTURE)), packedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
    }

    @Override
    public boolean shouldRenderOffScreen(BlastDoorBlockEntity p_112306_) {
        return true;
    }
}
