package net.Monsterwaill.falloutmod.item.custom;

import net.Monsterwaill.falloutmod.sounds.FalloutSounds;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class STIMPACKITEM extends Item {
    public STIMPACKITEM(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        // Play the stimpak use noise on the player
        level.playSound(null, player, FalloutSounds.STIMPACK_USE.get(), SoundSource.AMBIENT,1f,1f);
        player.heal(6);
        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 60));
        if(!level.isClientSide()) {
            // Cooldown timer
            player.getCooldowns().addCooldown(this, 100);
        }
        return super.use(level, player, hand);
    }
}
