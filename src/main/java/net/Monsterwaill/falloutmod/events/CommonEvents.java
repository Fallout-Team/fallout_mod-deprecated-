package net.Monsterwaill.falloutmod.events;

import net.Monsterwaill.falloutmod.FalloutMod;
import net.Monsterwaill.falloutmod.block.BlastDoorBlock;
import net.Monsterwaill.falloutmod.block.TARDISBlock;
import net.Monsterwaill.falloutmod.block.entities.TARDISBlockEntity;
import net.Monsterwaill.falloutmod.entities.FalloutEntities;
import net.Monsterwaill.falloutmod.entities.PowerArmor;
import net.Monsterwaill.falloutmod.models.layers.PowerArmorLayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.components.OptionsList;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.Monsterwaill.falloutmod.models.PowerArmorModel;

import static net.Monsterwaill.falloutmod.block.BlastDoorBlock.createAdjacentBarriers;

@Mod.EventBusSubscriber(modid = FalloutMod.MOD_ID)
public class CommonEvents {

    @SubscribeEvent
    public void onBlockPlace(BlockEvent.EntityPlaceEvent event) {

        BlockState blockState = event.getPlacedBlock();
        Block block = blockState.getBlock();

        if (block instanceof TARDISBlock tardisBlock) {
            if (event.getLevel().getBlockEntity(event.getPos()) instanceof TARDISBlockEntity tardisBlockEntity) {
                tardisBlockEntity.getAnimation().setupAnimation();
            }
        }
        if (block instanceof BlastDoorBlock blast) {
            createAdjacentBarriers((Level) event.getLevel(),event.getPos(),event.getState());
        }
    }

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(FalloutEntities.POWER_ARMOR.get(), PowerArmor.setAttributes());
    }
}
