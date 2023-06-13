package net.Monsterwaill.falloutmod.block;

import net.Monsterwaill.falloutmod.block.entities.FalloutBlockEntities;
import net.Monsterwaill.falloutmod.block.entities.RadioBlockEntity;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;

public class RadioBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty IS_PLAYING = BooleanProperty.create("is_playing");
    private SimpleSoundInstance soundInstance;

    // Shapes for each direction so the hitbox is the right size
    public static final VoxelShape NORTH_AABB = Block.box(0, 0, 2.5, 16, 12, 13.5); // I don't think AABB is the right name for these, but thats how that idiot Loqor does it.
    public static final VoxelShape EAST_AABB = Block.box(2.5, 0, 0, 13.5, 12, 16);
    public static final VoxelShape SOUTH_AABB = Block.box(0, 0, 2.5, 16, 12, 13.5);
    public static final VoxelShape WEST_AABB = Block.box(2.5, 0, 0, 13.5, 12, 16);

    // Make sure the radio has the correct facings set in its blockstate
    // And also make sure its render_type is cutout
    public RadioBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(IS_PLAYING,false));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return FalloutBlockEntities.RADIO_BLOCK_ENTITY.get().create(pos, state);
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
    public static SoundEvent getRandomDiscSound(RandomSource random) {
        ArrayList<SoundEvent> sounds = new ArrayList<>();
        Collections.addAll(sounds, ForgeRegistries.SOUND_EVENTS.getValues().toArray(new SoundEvent[0]));
        sounds.removeIf(soundEvent -> !soundEvent.getLocation().getPath().contains("music_disc"));
        return sounds.get(random.nextInt(sounds.size()));
    }

    // Storing this value in block states is easier than making packets as it makes sure that it is synced across server and client.
    private static boolean isPlaying(BlockState state) {
        return state.getValue(IS_PLAYING);
    }
    private static void setPlaying(Level level,BlockPos pos,BlockState state, boolean val) {
        level.setBlockAndUpdate(pos,state.setValue(IS_PLAYING,val));
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (isPlaying(state)) {
            Vec3 vec3 = Vec3.atBottomCenterOf(pos).add(0.0D, 1.2F, 0.0D);
            float f = random.nextInt(4) / 24.0F;
            level.addParticle(ParticleTypes.NOTE, vec3.x(), vec3.y(), vec3.z(), 0D, f, 0.0D); // @TODO too many of these particels + they're all GREEN
        }
    }

    // Code that is ran when you right-click the block
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        // Example code - Plays a random song which can be stopped
        if(level.isClientSide) {return InteractionResult.SUCCESS;}

        RadioBlockEntity radio = (RadioBlockEntity) level.getBlockEntity(pos);

        if(!isPlaying(state)) {
            radio.playRandomDisc(pos, level.random);
        }
        else{
            radio.stopSound();
        }
        setPlaying(level,pos,state,!isPlaying(state));

        return InteractionResult.SUCCESS;
    }

    @Override
    public void onRemove(BlockState p_60515_, Level level, BlockPos pos, BlockState p_60518_, boolean p_60519_) {

        RadioBlockEntity radio = (RadioBlockEntity) level.getBlockEntity(pos);
        radio.stopSound();

        super.onRemove(p_60515_, level, pos, p_60518_, p_60519_);
    }
}
