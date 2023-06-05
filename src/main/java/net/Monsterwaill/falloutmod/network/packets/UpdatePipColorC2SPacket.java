package net.Monsterwaill.falloutmod.network.packets;

import net.Monsterwaill.falloutmod.animation.ExteriorAnimation;
import net.Monsterwaill.falloutmod.animation.ExteriorClassicAnimation;
import net.Monsterwaill.falloutmod.block.entities.TARDISBlockEntity;
import net.Monsterwaill.falloutmod.item.custom.PipBoyItem;
import net.Monsterwaill.falloutmod.screens.EnumPipColor;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class UpdatePipColorC2SPacket {
    public boolean messageIsValid;

    private EnumPipColor color;
    private UUID playerUUID;

    public UpdatePipColorC2SPacket(EnumPipColor color, UUID uuid) {
        this.color = color;
        this.playerUUID = uuid;
        this.messageIsValid = true;
    }

    public UpdatePipColorC2SPacket() {
        this.messageIsValid = false;
    }

    public static UpdatePipColorC2SPacket decode(FriendlyByteBuf buf) {
        UpdatePipColorC2SPacket packet = new UpdatePipColorC2SPacket();

        try {
            packet.color = buf.readEnum(EnumPipColor.class);
            packet.playerUUID = buf.readUUID();
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            System.out.println("Exception while reading Packet: " + e);
            return packet;
        }

        packet.messageIsValid = true;
        return packet;
    }

    public void encode(FriendlyByteBuf buf) {
        if (!this.messageIsValid) return;

        buf.writeEnum(this.color);
        buf.writeUUID(this.playerUUID);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // Make sure it's only executed on the physical client
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
                Level level = Minecraft.getInstance().level;

                if (level == null) {return;}

                Player player = level.getPlayerByUUID(this.playerUUID);

                if (player.getItemInHand(InteractionHand.OFF_HAND).getItem() instanceof PipBoyItem pipBoyItem) {
                    pipBoyItem.setPipColorName(this.color);
                }
            });
        });
        return true;
    }
}
