package net.Monsterwaill.falloutmod.events;

import net.Monsterwaill.falloutmod.FalloutMod;
import net.Monsterwaill.falloutmod.block.entities.FalloutBlockEntities;
import net.Monsterwaill.falloutmod.data.FalloutConstants;
import net.Monsterwaill.falloutmod.entities.FalloutEntities;
import net.Monsterwaill.falloutmod.item.custom.PipBoyItem;
import net.Monsterwaill.falloutmod.models.PowerArmor;
import net.Monsterwaill.falloutmod.models.TARDISModel;
import net.Monsterwaill.falloutmod.network.Network;
import net.Monsterwaill.falloutmod.renderers.TARDISRenderer;
import net.Monsterwaill.falloutmod.renderers.entity.PowerArmorRenderer;
import net.Monsterwaill.falloutmod.screens.EnumPipColor;
import net.Monsterwaill.falloutmod.screens.PipBoySettingsScreen;
import net.Monsterwaill.falloutmod.screens.PipBoyStatsScreen;
import net.Monsterwaill.falloutmod.util.Keybinding;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = FalloutMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents
{
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {

    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers renderers) {
        renderers.registerBlockEntityRenderer(FalloutBlockEntities.TARDIS_BLOCK_ENTITY.get(), TARDISRenderer::new);
        renderers.registerEntityRenderer(FalloutEntities.POWER_ARMOR.get(), PowerArmorRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(TARDISModel.LAYER_LOCATION,TARDISModel::createBodyLayer);
        event.registerLayerDefinition(PowerArmor.LAYER_LOCATION, PowerArmor::createBodyLayer);
    }

    public static Screen createPipBoyScreen(Component component, Player player, EnumPipColor pipColor) {
        return new PipBoyStatsScreen(component, player, null, pipColor);
    }

    public static Screen createSettingsScreen(Component component, Player player, Screen screen) {
        return new PipBoySettingsScreen(component, player, screen);
    }

    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event) {
        event.register(Keybinding.PIPBOY_KEY);
    }
}