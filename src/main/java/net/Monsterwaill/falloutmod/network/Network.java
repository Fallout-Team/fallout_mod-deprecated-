package net.Monsterwaill.falloutmod.network;

import net.Monsterwaill.falloutmod.FalloutMod;
import net.Monsterwaill.falloutmod.network.packets.TakeOffPowerArmourC2SPacket;
import net.Monsterwaill.falloutmod.network.packets.UpdateExteriorAnimationS2CPacket;
import net.Monsterwaill.falloutmod.network.packets.UpdatePipColorC2SPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class Network {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(FalloutMod.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(UpdateExteriorAnimationS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(UpdateExteriorAnimationS2CPacket::decode)
                .encoder(UpdateExteriorAnimationS2CPacket::encode)
                .consumerMainThread(UpdateExteriorAnimationS2CPacket::handle)
                .add();
        net.messageBuilder(UpdatePipColorC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(UpdatePipColorC2SPacket::decode)
                .encoder(UpdatePipColorC2SPacket::encode)
                .consumerMainThread(UpdatePipColorC2SPacket::handle)
                .add();
        net.messageBuilder(TakeOffPowerArmourC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(TakeOffPowerArmourC2SPacket::new)
                .encoder(TakeOffPowerArmourC2SPacket::toBytes)
                .consumerMainThread(TakeOffPowerArmourC2SPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static <MSG> void sendToAll(MSG message) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }
}