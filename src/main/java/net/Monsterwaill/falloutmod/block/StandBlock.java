package net.Monsterwaill.falloutmod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;



public class StandBlock extends HorizontalDirectionalBlock {

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
    public static final VoxelShape NORTH_AABB = Block.box(1, 0, 1, 15, 6, 15);
    public static final VoxelShape EAST_AABB = Block.box(1, 0, 1, 15, 6, 15);
    public static final VoxelShape SOUTH_AABB = Block.box(1, 0, 1, 15, 6, 15);
    public static final VoxelShape WEST_AABB = Block.box(1, 0, 1, 15, 6, 15);

    @Override
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return switch (state.getValue(FACING)) {
            case NORTH -> NORTH_AABB;
            case EAST -> EAST_AABB;
            case SOUTH -> SOUTH_AABB;
            case WEST -> WEST_AABB;
            default -> throw new RuntimeException("Invalid facing direction in getShape() for StandBlock");
        };
    }

}
