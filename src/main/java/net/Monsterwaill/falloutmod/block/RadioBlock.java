package net.Monsterwaill.falloutmod.block;

import net.minecraft.client.Minecraft;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class RadioBlock extends HorizontalDirectionalBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty IS_PLAYING = BooleanProperty.create("is_playing");

    // Shapes for each direction so the hitbox is the right size
    public static final VoxelShape NORTH_AABB = Block.box(0, 0, 2.5, 16, 12, 13.5); // I don't think AABB is the right name for these, but thats how that idiot Loqor does it.
    public static final VoxelShape EAST_AABB = Block.box(2.5, 0, 0, 13.5, 12, 16);
    public static final VoxelShape SOUTH_AABB = Block.box(0, 0, 2.5, 16, 12, 13.5);
    public static final VoxelShape WEST_AABB = Block.box(2.5, 0, 0, 13.5, 12, 16);;

    // Make sure the radio has the correct facings set in its blockstate
    // And also make sure its render_type is cutout
    public RadioBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(IS_PLAYING,false));
    }

    // Correctly sets the hitbox
    @Override
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return switch (state.getValue(FACING)) {
            case NORTH -> NORTH_AABB;
            case EAST -> EAST_AABB;
            case SOUTH -> SOUTH_AABB;
            case WEST -> WEST_AABB;
            default -> throw new RuntimeException("Invalid facing direction in getShape() for RadioBlock");
        };
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(IS_PLAYING);
    }

    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    // Definitely a better way of doing this but im just doing it as an example so
    private static SoundEvent getRandomDiscSound() {
        Random random = new Random();
        int choice = random.nextInt(1,12); // 12 for the 12 different discs

        return switch (choice) {
            case 1 -> SoundEvents.MUSIC_DISC_BLOCKS;
            case 2 -> SoundEvents.MUSIC_DISC_CAT;
            case 3 -> SoundEvents.MUSIC_DISC_CHIRP;
            case 4 -> SoundEvents.MUSIC_DISC_FAR;
            case 5 -> SoundEvents.MUSIC_DISC_MALL;
            case 6 -> SoundEvents.MUSIC_DISC_MELLOHI;
            case 7 -> SoundEvents.MUSIC_DISC_OTHERSIDE;
            case 8 -> SoundEvents.MUSIC_DISC_PIGSTEP;
            case 9 -> SoundEvents.MUSIC_DISC_STAL;
            case 10 -> SoundEvents.MUSIC_DISC_STRAD;
            case 11 -> SoundEvents.MUSIC_DISC_WAIT;
            case 12 -> SoundEvents.MUSIC_DISC_WARD;
            default -> SoundEvents.ANVIL_HIT;
        };
    }

    // Storing this value in block states is easier than making packets as it makes sure that it is synced across server and client.
    private static boolean isPlaying(BlockState state) {
        return state.getValue(IS_PLAYING);
    }
    private static void setPlaying(Level level,BlockPos pos,BlockState state, boolean val) {
        level.setBlockAndUpdate(pos,state.setValue(IS_PLAYING,val));
    }

    // Code that is ran when you right-click the block
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        // Example code - Plays a random song which can be stopped
        if(!isPlaying(state)){
            level.playSound(null, pos, getRandomDiscSound(), SoundSource.MUSIC, 1f,1f);
        }
        else{
            Minecraft.getInstance().getSoundManager().reload();
        }
        setPlaying(level,pos,state,!isPlaying(state));
        return InteractionResult.SUCCESS;
    }
}
