package net.Monsterwaill.falloutmod.network.packets;

import net.Monsterwaill.falloutmod.item.custom.armour.PowerArmourItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import static net.Monsterwaill.falloutmod.entities.PowerArmourEntity.isWearingAllPowerArmour;
import static net.Monsterwaill.falloutmod.entities.PowerArmourEntity.spawnNew;

public class TakeOffPowerArmourC2SPacket {
    public TakeOffPowerArmourC2SPacket() {

    }

    public TakeOffPowerArmourC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // SERVER
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();

            if (isWearingAllPowerArmour(player)) {
                spawnNew(level, player.getOnPos(), player);
            }
        });
        return true;
    }
}

