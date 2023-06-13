package net.Monsterwaill.falloutmod.block;

import net.Monsterwaill.falloutmod.block.entities.FalloutBlockEntities;
import net.Monsterwaill.falloutmod.block.entities.StandBlockEntity;
import net.Monsterwaill.falloutmod.item.FalloutItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StandBlock extends HorizontalDirectionalBlock implements EntityBlock {

    public StandBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(HorizontalDirectionalBlock.FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return this.defaultBlockState().setValue(FACING, blockPlaceContext.getHorizontalDirection().getOpposite());
    }
    // Shapes for each direction so the hitbox is the right size
    public static final VoxelShape NORTH_AABB = Block.box(1, 0, 2, 15, 14, 14);
    public static final VoxelShape EAST_AABB = Block.box(2, 0, 1, 14, 14, 15);
    public static final VoxelShape SOUTH_AABB = Block.box(1, 0, 2, 15, 14, 14);
    public static final VoxelShape WEST_AABB = Block.box(2, 0, 1, 14, 14, 15);

    @Override
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return switch (state.getValue(FACING)) {
            case EAST -> EAST_AABB;
            case SOUTH -> SOUTH_AABB;
            case WEST -> WEST_AABB;
            default -> NORTH_AABB;
        };
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level worldIn, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand handIn, @NotNull BlockHitResult hit) {
        if (!worldIn.isClientSide()) {
            if (handIn != InteractionHand.MAIN_HAND) return InteractionResult.PASS;

            if (worldIn.getBlockEntity(pos) instanceof StandBlockEntity standBlockEntity) {
                if (player.getMainHandItem().getItem() == FalloutItems.PIPBOY.get()) {
                    standBlockEntity.dropHandIfPresent(player);
                    standBlockEntity.setPipboy(player.getMainHandItem().copy());
                    player.getMainHandItem().shrink(1);
                    return InteractionResult.SUCCESS;
                }
                standBlockEntity.dropHandIfPresent(player);
            }
        }

        return super.use(state, worldIn, pos, player, handIn, hit);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return FalloutBlockEntities.PIP_BOY_STAND.get().create(pos, state);
    }

}
