//package net.Monsterwaill.falloutmod.events;
//
//import net.Monsterwaill.falloutmod.FalloutMod;
//import net.Monsterwaill.falloutmod.block.entities.FalloutBlockEntities;
//import net.Monsterwaill.falloutmod.models.TARDISModel;
//import net.Monsterwaill.falloutmod.renderers.TARDISRenderer;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.client.event.EntityRenderersEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod;
//
//@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = FalloutMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
//public class ClientEvents {
//
//        @SubscribeEvent
//        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers renderers) {
//            renderers.registerBlockEntityRenderer(FalloutBlockEntities.TARDIS_BLOCK_ENTITY.get(), TARDISRenderer::new);
//        }
//
//        @SubscribeEvent
//        public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
//            event.registerLayerDefinition(TARDISModel.LAYER_LOCATION,TARDISModel::createBodyLayer);
//        }
//}
// do it in FalloutMod instead
