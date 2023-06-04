package net.Monsterwaill.falloutmod.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.Monsterwaill.falloutmod.block.entities.TARDISBlockEntity;
import net.Monsterwaill.falloutmod.models.TARDISModel;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class TARDISRenderer implements BlockEntityRenderer<TARDISBlockEntity> {
    private TARDISModel<?> model;
    public TARDISRenderer(BlockEntityRendererProvider.Context context) {
        this.model = new TARDISModel<>(context.bakeLayer(TARDISModel.LAYER_LOCATION));
    }

    @Override
    public void render(TARDISBlockEntity entity, float partialTick, PoseStack stack, MultiBufferSource source, int packedLight, int packedOverlay) {
        Direction direction = (entity.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING));

        stack.mulPose(Vector3f.YP.rotationDegrees(direction.toYRot()));
        stack.mulPose(Vector3f.XN.rotationDegrees(180.0f));

        // Usually, I would fix issues with movements in the model as a per model thing but i cannot be bothered and we're only having one exterior soo

        stack.scale(0.5f,0.5f,0.5f);
        stack.translate(0, -1.4f, 0);

        if (direction == Direction.NORTH) {
            stack.translate(-1,0,1);
        }
        else if (direction == Direction.SOUTH) {
            stack.translate(1,0,-1);
        }
        else if (direction == Direction.EAST) {
            stack.mulPose(Vector3f.YP.rotationDegrees(180f));
            stack.translate(-1,0,-1);
        }
        else if (direction == Direction.WEST) {
            stack.mulPose(Vector3f.YP.rotationDegrees(180f));
            stack.translate(1,0,1);
        }

        this.model.renderToBuffer(stack, source.getBuffer(RenderType.entityTranslucentCull(TARDISModel.TEXTURE)), LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY,1,1,1,entity.getAlpha());
    }

    @Override
    public boolean shouldRenderOffScreen(TARDISBlockEntity p_112306_) {
        return true;
    }
}
