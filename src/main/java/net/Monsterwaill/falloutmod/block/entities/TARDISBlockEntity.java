package net.Monsterwaill.falloutmod.block.entities;

import com.mojang.logging.LogUtils;
import net.Monsterwaill.falloutmod.animation.ExteriorAnimation;
import net.Monsterwaill.falloutmod.animation.ExteriorClassicAnimation;
import net.Monsterwaill.falloutmod.sounds.FalloutSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.slf4j.Logger;

public class TARDISBlockEntity extends BlockEntity {
    // This is just the TARDIS from my mod but with just the animation bits lol
    private ExteriorAnimation animation;
    public TARDISBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
    public TARDISBlockEntity(BlockPos pos, BlockState state) {
        this(FalloutBlockEntities.TARDIS_BLOCK_ENTITY.get(), pos, state);
    }

    public void use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide || hand != InteractionHand.MAIN_HAND) {
            return;
        }

        level.playSound(null,pos, FalloutSounds.DEMATERIALISE.get(), SoundSource.AMBIENT,1f,1f);
        this.getAnimation().start();
    }

    public float getAlpha() {
        return this.getAnimation().getAlpha();
    }

    public ExteriorAnimation getAnimation() {
        if (this.animation == null) {
//            this.animation = this.getTARDIS().getExteriorAnimation();
            this.animation = new ExteriorClassicAnimation(this);
            LogUtils.getLogger().debug("Created new CLASSIC EXTERIOR ANIMATION for " + this);
        }
        return this.animation;
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T entity) {
        if (!(entity instanceof TARDISBlockEntity exterior)) {return;}

        exterior.getAnimation().tick();

        if (exterior.getAlpha() <= 0f) {
            exterior.finished();
        }
    }

    private void finished() {
        this.getLevel().destroyBlock(this.getBlockPos(),false);
        ExperienceOrb xp = new ExperienceOrb(this.getLevel(),this.getBlockPos().getX(),this.getBlockPos().getY(),this.getBlockPos().getZ(),10);
        this.getLevel().addFreshEntity(xp);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putFloat("alpha",this.getAlpha());
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.getAnimation().setAlpha(tag.getFloat("alpha"));
    }
}
