package net.Monsterwaill.falloutmod.item.custom;

import net.Monsterwaill.falloutmod.data.FalloutConstants;
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

public class PipBoyItem extends Item {

    public PipBoyItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.translatable(FalloutConstants.PIPBOY_USAGE));
        } else {
            components.add(Component.translatable(FalloutConstants.ITEM_SHIFT));
        }
        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(!level.isClientSide() && hand == InteractionHand.OFF_HAND) {
            messageRandomNumber(player);
            player.getCooldowns().addCooldown(this, 50);
        }

        return super.use(level, player, hand);
    }
    private void messageRandomNumber(Player player) {
        player.sendSystemMessage(Component.translatable(FalloutConstants.NUMBER, getRandomNumber()));
    }

    private int getRandomNumber() {
        return RandomSource.createNewThreadLocalInstance().nextIntBetweenInclusive(1, 11);
    }
}
