package net.Monsterwaill.falloutmod.block.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BlastDoorBlockEntity extends BlockEntity {
    public BlastDoorBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
    public BlastDoorBlockEntity(BlockPos pos, BlockState state) {
        this(FalloutBlockEntities.BLAST_DOOR.get(), pos, state);
    }
}
