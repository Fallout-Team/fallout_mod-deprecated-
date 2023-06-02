package net.Monsterwaill.falloutmod;


import com.mojang.logging.LogUtils;
import net.Monsterwaill.falloutmod.block.FalloutBlocks;
import net.Monsterwaill.falloutmod.block.TARDISBlock;
import net.Monsterwaill.falloutmod.block.entities.FalloutBlockEntities;
import net.Monsterwaill.falloutmod.block.entities.TARDISBlockEntity;
import net.Monsterwaill.falloutmod.item.FalloutItems;
import net.Monsterwaill.falloutmod.models.TARDISModel;
import net.Monsterwaill.falloutmod.network.Network;
import net.Monsterwaill.falloutmod.renderers.TARDISRenderer;
import net.Monsterwaill.falloutmod.sounds.FalloutSounds;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(FalloutMod.MOD_ID)
public class FalloutMod {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "falloutmod";

    public FalloutMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        FalloutItems.ITEMS.register(modEventBus);
        FalloutBlocks.BLOCKS.register(modEventBus);
        FalloutBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        FalloutSounds.SOUNDS.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        Network.register();
    }


    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }

        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers renderers) {
            renderers.registerBlockEntityRenderer(FalloutBlockEntities.TARDIS_BLOCK_ENTITY.get(), TARDISRenderer::new);
        }

        @SubscribeEvent
        public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(TARDISModel.LAYER_LOCATION,TARDISModel::createBodyLayer);
        }
    }
    @Mod.EventBusSubscriber(modid = FalloutMod.MOD_ID)
    public static class CommonEvents {

        @Mod.EventBusSubscriber(modid = FalloutMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
        public static class ModEventBusEvents {

        }

        @SubscribeEvent
        public void onBlockPlace(BlockEvent.EntityPlaceEvent event) {
            if (event.getState().getBlock() instanceof TARDISBlock) {
                TARDISBlockEntity blockEntity = (TARDISBlockEntity) event.getLevel().getBlockEntity(event.getPos());
                blockEntity.getAnimation().setupAnimation();
            }
        }
    }
}
