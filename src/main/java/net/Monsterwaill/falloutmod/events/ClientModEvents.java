package net.Monsterwaill.falloutmod.events;

import net.Monsterwaill.falloutmod.FalloutMod;
import net.Monsterwaill.falloutmod.block.entities.FalloutBlockEntities;
import net.Monsterwaill.falloutmod.entities.FalloutEntities;
import net.Monsterwaill.falloutmod.models.BlastDoorModel;
import net.Monsterwaill.falloutmod.models.PowerArmorModel;
import net.Monsterwaill.falloutmod.models.TARDISModel;
import net.Monsterwaill.falloutmod.models.layers.PowerArmorLayer;
import net.Monsterwaill.falloutmod.renderers.BlastDoorRenderer;
import net.Monsterwaill.falloutmod.renderers.StandRenderer;
import net.Monsterwaill.falloutmod.renderers.TARDISRenderer;
import net.Monsterwaill.falloutmod.screens.EnumPipColor;
import net.Monsterwaill.falloutmod.screens.PipBoySettingsScreen;
import net.Monsterwaill.falloutmod.screens.PipBoyStatsScreen;
import net.Monsterwaill.falloutmod.util.Keybinding;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.Map;

@Mod.EventBusSubscriber(modid = FalloutMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents
{
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {

    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers renderers) {
        renderers.registerBlockEntityRenderer(FalloutBlockEntities.TARDIS_BLOCK_ENTITY.get(), TARDISRenderer::new);
        renderers.registerBlockEntityRenderer(FalloutBlockEntities.BLAST_DOOR.get(), BlastDoorRenderer::new);
        renderers.registerBlockEntityRenderer(FalloutBlockEntities.PIP_BOY_STAND.get(), StandRenderer::new);
        //renderers.registerEntityRenderer(FalloutEntities.POWER_ARMOR.get(), PowerArmorRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(TARDISModel.LAYER_LOCATION,TARDISModel::createBodyLayer);
        event.registerLayerDefinition(BlastDoorModel.LAYER_LOCATION,BlastDoorModel::createBodyLayer);
        event.registerLayerDefinition(PowerArmorModel.LAYER_LOCATION, PowerArmorModel::createBodyLayer);
    }

    public static Screen createPipBoyScreen(Component component, Player player, EnumPipColor pipColor) {
        return new PipBoyStatsScreen(component, player, null, pipColor);
    }

    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event) {
        event.register(Keybinding.PIPBOY_KEY);
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void renderPlayer(final EntityRenderersEvent.AddLayers event) {
        /*LivingEntityRenderer renderer = event.getRenderer(EntityType.PLAYER);
        PowerArmorLayer<LivingEntity, EntityModel<LivingEntity>> layer = new PowerArmorLayer<>(renderer, event.getEntityModels());
        renderer.addLayer(layer);*/
    }
}