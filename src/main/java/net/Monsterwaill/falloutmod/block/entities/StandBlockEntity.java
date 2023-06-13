package net.Monsterwaill.falloutmod.block.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class StandBlockEntity extends BlockEntity {

    private ItemStack pipboy;


    public StandBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
        this.pipboy = ItemStack.EMPTY;
    }

    public ItemStack getPipboy() {
        return pipboy;
    }

    // ==== Inventory ====
    public void setPipboy(ItemStack stack) {
        this.pipboy = stack;
        setChanged();
    }

    public void clearContent() {
        this.setPipboy(ItemStack.EMPTY);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        if (nbt.contains("PipBoy", 10)) {
            this.setPipboy(ItemStack.of(nbt.getCompound("PipBoy")));
        }
    }

    public void dropHandIfPresent(Player player) {
        ItemStack pipboyItem = getPipboy();
        if (!pipboyItem.isEmpty()) {
            if (player != null) {
                if (!player.addItem(getPipboy())) {
                    Containers.dropItemStack(level, worldPosition.getX(), worldPosition.getY() + 1, worldPosition.getZ(), pipboyItem);
                    clearContent();
                }
            } else {
                Containers.dropItemStack(level, worldPosition.getX(), worldPosition.getY() + 1, worldPosition.getZ(), pipboyItem);
                clearContent();
            }
        }
    }


    @Override
    public void saveAdditional(CompoundTag compound) {
        compound.put("PipBoy", this.getPipboy().save(new CompoundTag()));
        super.saveAdditional(compound);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        CompoundTag compoundTag = new CompoundTag();
        saveAdditional(compoundTag);
        return compoundTag;
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
