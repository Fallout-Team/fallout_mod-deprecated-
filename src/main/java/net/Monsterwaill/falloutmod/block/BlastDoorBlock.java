package net.Monsterwaill.falloutmod.block;

import net.Monsterwaill.falloutmod.block.entities.BlastDoorBlockEntity;
import net.Monsterwaill.falloutmod.block.entities.FalloutBlockEntities;
import net.Monsterwaill.falloutmod.sounds.FalloutSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BarrierBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlastDoorBlock extends DirectionalBaseEntityBlock {
    public static final BooleanProperty OPENED = BlockStateProperties.OPEN;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    private static final VoxelShape OPENED_AABB = Block.box(0, 0, 0, 0, 0, 0);
    private static final VoxelShape AABB_SOUTH_NORTH = Block.box(-16,0,0,16,48,16);
    private static final VoxelShape AABB_EAST_WEST = Block.box(0,0,-16,16,48,16);

    protected BlastDoorBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(OPENED,false).setValue(POWERED,false));
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return switch(state.getValue(FACING)) {
            case WEST,EAST -> AABB_EAST_WEST;
            default -> AABB_SOUTH_NORTH;
        };
    }


    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter p_60573_, BlockPos p_60574_, CollisionContext p_60575_) {
        if (isOpen(state)) {
            return OPENED_AABB;
        }

        return super.getCollisionShape(state, p_60573_, p_60574_, p_60575_);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return FalloutBlockEntities.BLAST_DOOR.get().create(p_153215_,p_153216_);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_153212_, BlockState p_153213_, BlockEntityType<T> type) {
        return type == FalloutBlockEntities.BLAST_DOOR.get() ? BlastDoorBlockEntity::tick : null;
    }
    @Nullable
    @Override
    public <T extends BlockEntity> GameEventListener getListener(ServerLevel p_221121_, T p_221122_) {
        return super.getListener(p_221121_, p_221122_);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(OPENED);
        builder.add(POWERED);
    }

    @Override
    public void onRemove(BlockState p_60515_, Level p_60516_, BlockPos p_60517_, BlockState p_60518_, boolean p_60519_) {
        destroyBarriers(p_60516_,p_60515_,p_60517_);

        super.onRemove(p_60515_, p_60516_, p_60517_, p_60518_, p_60519_);
    }

    @Override
    public void onPlace(BlockState p_60566_, Level p_60567_, BlockPos p_60568_, BlockState p_60569_, boolean p_60570_) {
        createAdjacentBarriers(p_60567_,p_60568_,p_60566_);

        super.onPlace(p_60566_, p_60567_, p_60568_, p_60569_, p_60570_);
    }

    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos pos2, boolean p_52781_) {
        if (isPowered(state) == level.hasNeighborSignal(pos)) return;

        setPowered(level,pos,state,level.hasNeighborSignal(pos));
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player p_60506_, InteractionHand hand, BlockHitResult p_60508_) {
        if (hand == InteractionHand.MAIN_HAND && !level.isClientSide) {
            setOpened(level,pos,state,!isOpen(state));
        }

        return super.use(state, level, pos, p_60506_, hand, p_60508_);
    }

    public static boolean isOpen(BlockState state) {
        return state.getValue(OPENED);
    }
    public static void setOpened(Level level,BlockPos pos,BlockState state, boolean val) {
        level.setBlockAndUpdate(pos,state.setValue(OPENED,val));

        if (val) {
            destroyBarriers(level,state,pos);
            level.playSound(null,pos, FalloutSounds.BLAST_DOOR_OPEN.get(), SoundSource.AMBIENT,1f,1f);
        }
    }

    public static boolean isPowered(BlockState state) {
        return state.getValue(POWERED);
    }
    public static void setPowered(Level level,BlockPos pos,BlockState state, boolean val) {
        level.setBlockAndUpdate(pos,state.setValue(POWERED,val));
        setOpened(level, pos, state, val);
    }

    public static void createAdjacentBarriers(LevelAccessor level, BlockPos pos, BlockState state) {
        if (!(state.getBlock() instanceof BlastDoorBlock)) return;

        Direction direction = state.getValue(FACING);

        // Place two barrier blocks above
        // Temporary variable names for a final and check at the end to decide whether to give up and delete everything lol
        boolean j = placeBarrierIfNotAir(level,pos.above());
        boolean i = placeBarrierIfNotAir(level,pos.above().above());
        boolean k = false,l = false,m = false;

        if (direction == Direction.EAST || direction == Direction.WEST) {
            k = placeBarrierIfNotAir(level,pos.north());
            l = placeBarrierIfNotAir(level,pos.north().above());
            m = placeBarrierIfNotAir(level,pos.north().above().above());
        } else if (direction == Direction.NORTH || direction == Direction.SOUTH) {
            k = placeBarrierIfNotAir(level,pos.west());
            l = placeBarrierIfNotAir(level,pos.west().above());
            m = placeBarrierIfNotAir(level,pos.west().above().above());
        }

        boolean flag = i && j && k && l && m;

        if (!flag) destroyBarriersAndSelf(level,state,pos);
    }

    public static void destroyBarriersAndSelf(LevelAccessor level, BlockState state, BlockPos pos) {
        if (!(state.getBlock() instanceof BlastDoorBlock)) return;

        destroyBarriers(level,state,pos);
        level.removeBlock(pos,true);
    }

    public static void destroyBarriers(LevelAccessor level, BlockState state, BlockPos pos) {
        if (!(state.getBlock() instanceof BlastDoorBlock)) return;

        Direction direction = state.getValue(FACING);


        destroyBarrierIfMatching(level,pos.above());
        destroyBarrierIfMatching(level,pos.above().above());


        if (direction == Direction.EAST || direction == Direction.WEST) {
            destroyBarrierIfMatching(level,pos.north());
            destroyBarrierIfMatching(level,pos.north().above());
            destroyBarrierIfMatching(level,pos.north().above().above());
        } else if (direction == Direction.NORTH || direction == Direction.SOUTH) {
            destroyBarrierIfMatching(level,pos.west());
            destroyBarrierIfMatching(level,pos.west().above());
            destroyBarrierIfMatching(level,pos.west().above().above());
        }
    }

    private static void destroyBarrierIfMatching(LevelAccessor level, BlockPos pos) {
        destroyBlockIfMatching(level,pos,Blocks.BARRIER.defaultBlockState());
    }
    private static void destroyBlockIfMatching(LevelAccessor level, BlockPos pos, BlockState state) {
        if (level.getBlockState(pos) == state) level.removeBlock(pos,true);
    }

    private static boolean placeBarrierIfNotAir(LevelAccessor level,BlockPos pos) {
        return placeBlockIfNotAir(level, Blocks.BARRIER.defaultBlockState(),pos);
    }

    private static boolean placeBlockIfNotAir(LevelAccessor level,BlockState block,BlockPos pos) {
        if (!level.getBlockState(pos).isAir()) return false;

        level.setBlock(pos,block,2);
        return true;
    }
}
