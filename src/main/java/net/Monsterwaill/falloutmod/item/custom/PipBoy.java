package net.Monsterwaill.falloutmod.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PipBoy extends Item {

    public PipBoy(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.literal("The Pip-Boy 2000 Mk VI is only operational when held in the offhand slot. For more info please refer to your Pip-Boy manual provided by vault tech.").withStyle(ChatFormatting.GREEN));
        } else {
            components.add(Component.literal("Press shift!").withStyle(ChatFormatting.DARK_RED));
        }

        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(!level.isClientSide() && hand == InteractionHand.OFF_HAND) {
            //testing to see if this works by making it say a number
            // the number should say itself in chat ig
            showRandomNumber(player);
            player.getCooldowns().addCooldown(this, 50);
        }

        return super.use(level, player, hand);
    }
    private void showRandomNumber(Player player) {
        player.sendSystemMessage(Component.literal("Number:" + getRandomNumber()));
    }

    private int getRandomNumber() {
        return RandomSource.createNewThreadLocalInstance().nextIntBetweenInclusive(1, 11);
    }
}
