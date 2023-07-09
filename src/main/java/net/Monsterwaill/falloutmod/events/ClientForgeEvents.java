package net.Monsterwaill.falloutmod.events;

import net.Monsterwaill.falloutmod.FalloutMod;
import net.Monsterwaill.falloutmod.data.FalloutConstants;
import net.Monsterwaill.falloutmod.item.custom.PipBoyItem;
import net.Monsterwaill.falloutmod.network.Network;
import net.Monsterwaill.falloutmod.network.packets.TakeOffPowerArmourC2SPacket;
import net.Monsterwaill.falloutmod.util.Keybinding;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FalloutMod.MOD_ID, value = Dist.CLIENT)
public class ClientForgeEvents {

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if(Keybinding.PIPBOY_KEY.consumeClick()) {
            if(Minecraft.getInstance().player.getItemBySlot(EquipmentSlot.OFFHAND).getItem() instanceof PipBoyItem pipBoyItem && !(Minecraft.getInstance().player.getItemBySlot(EquipmentSlot.MAINHAND).getItem() instanceof PipBoyItem)) {
                Minecraft.getInstance().setScreen(ClientModEvents.createPipBoyScreen(Component.translatable(FalloutConstants.PIPBOY_SCREEN), Minecraft.getInstance().player, pipBoyItem.getPipColorName()));
            }
        }
        if (Keybinding.TAKE_OFF_POWER_KEY.consumeClick()) {
            Network.sendToServer(new TakeOffPowerArmourC2SPacket());
        }
    }

}
