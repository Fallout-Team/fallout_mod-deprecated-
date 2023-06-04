package net.Monsterwaill.falloutmod.block.entities;

import net.Monsterwaill.falloutmod.animation.ExteriorAnimation;
import net.Monsterwaill.falloutmod.sounds.FalloutSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import static net.Monsterwaill.falloutmod.block.RadioBlock.*;

public class RadioBlockEntity extends BlockEntity {
    // This is just the TARDIS from my mod but with just the animation bits lol
    private SimpleSoundInstance sound;
    public RadioBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
    public RadioBlockEntity(BlockPos pos, BlockState state) {
        this(FalloutBlockEntities.RADIO_BLOCK_ENTITY.get(), pos, state);
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide || hand != InteractionHand.MAIN_HAND) {
            return InteractionResult.SUCCESS;
        }


        return InteractionResult.SUCCESS;
    }

    public void playRandomDisc(BlockPos pos){
        this.sound = SimpleSoundInstance.forRecord(getRandomDiscSound(), pos.getX(), pos.getY(), pos.getZ());

        Minecraft.getInstance().getSoundManager().play(this.sound);
    }

    public void stopSound() {
        if (this.sound == null) return;

        Minecraft.getInstance().getSoundManager().stop(this.sound);
    }
}
