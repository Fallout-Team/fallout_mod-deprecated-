package net.Monsterwaill.falloutmod.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PIPBOY extends Item {
    public PIPBOY(Properties properties) {
        super(properties);
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
