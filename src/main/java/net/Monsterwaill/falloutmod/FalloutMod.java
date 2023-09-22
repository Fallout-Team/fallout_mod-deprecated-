package net.Monsterwaill.falloutmod;


import net.Monsterwaill.falloutmod.block.FalloutBlocks;
import net.Monsterwaill.falloutmod.block.entities.FalloutBlockEntities;
import net.Monsterwaill.falloutmod.data.client.FalloutEnglishUK;
import net.Monsterwaill.falloutmod.data.client.FalloutEnglishUS;
import net.Monsterwaill.falloutmod.data.client.FalloutSoundGen;
import net.Monsterwaill.falloutmod.data.client.FalloutTokiPona;
import net.Monsterwaill.falloutmod.data.server.FalloutBlockTags;
import net.Monsterwaill.falloutmod.entities.FalloutEntities;
import net.Monsterwaill.falloutmod.item.FalloutItems;
import net.Monsterwaill.falloutmod.network.Network;
import net.Monsterwaill.falloutmod.sounds.FalloutSounds;
import net.Monsterwaill.falloutmod.world.feature.ModConfiguredFeatures;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

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
        FalloutEntities.ENTITY_TYPES.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::onGatherData);
        ModConfiguredFeatures.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void onGatherData(GatherDataEvent e) {
        DataGenerator generator = e.getGenerator();
        ExistingFileHelper existingFileHelper = e.getExistingFileHelper();
        generator.addProvider(e.includeClient(), new FalloutEnglishUS(generator));
        generator.addProvider(e.includeClient(), new FalloutEnglishUK(generator));
        generator.addProvider(e.includeClient(), new FalloutTokiPona(generator));
        generator.addProvider(e.includeClient(), new FalloutSoundGen(generator,existingFileHelper));
        generator.addProvider(e.includeServer(), new FalloutBlockTags(generator, existingFileHelper));
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        Network.register();
    }

}
