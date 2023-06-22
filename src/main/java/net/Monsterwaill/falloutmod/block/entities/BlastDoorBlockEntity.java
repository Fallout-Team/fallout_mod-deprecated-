package net.Monsterwaill.falloutmod.block.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import static net.Monsterwaill.falloutmod.block.BlastDoorBlock.isOpen;

public class BlastDoorBlockEntity extends BlockEntity {
    private float topY = 23.8f,bottomY = 23.8f;
    public BlastDoorBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
    public BlastDoorBlockEntity(BlockPos pos, BlockState state) {
        this(FalloutBlockEntities.BLAST_DOOR.get(), pos, state);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);

        tag.putFloat("bottomY", bottomY);
        tag.putFloat("topY", topY);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);

        this.bottomY = tag.getFloat("bottomY");
        this.topY = tag.getFloat("topY");
    }

    public float getTopY() {
        return this.topY;
    }
    public float getBottomY() {
        return this.bottomY;
    }

    // @TODO Fix problems with animation being choppy as its bound to the 20tps rate
    public void updateAnimation() {
        if (isOpen(this.getBlockState())) {
            if (this.getBottomY() < 35) {
                this.bottomY = this.getBottomY() + 0.24f;
            }
            if (this.getTopY() > -11) {
                this.topY = this.getTopY() - 0.74f;
            }
        } else {
            if (this.getBottomY() > 24) {
                this.bottomY = this.getBottomY() - 0.32f;
            }
            if (this.getTopY() < 23.8) {
                this.topY = this.getTopY() + 1f;
            }
        }
    }


    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T t) {
        BlastDoorBlockEntity entity = (BlastDoorBlockEntity) level.getBlockEntity(pos);

        if (entity == null) return;

        entity.updateAnimation();
    }
}
