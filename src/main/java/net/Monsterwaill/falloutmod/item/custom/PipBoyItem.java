package net.Monsterwaill.falloutmod.item.custom;

import net.Monsterwaill.falloutmod.data.FalloutConstants;
import net.Monsterwaill.falloutmod.events.ClientModEvents;
import net.Monsterwaill.falloutmod.screens.EnumPipColor;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
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

    public EnumPipColor pipColorName;

    public PipBoyItem(Properties properties) {
        super(properties);
        setPipColorName(EnumPipColor.GREEN);
    }

    public void setPipColorName(EnumPipColor color) {
        this.pipColorName = color;
    }

    public EnumPipColor getPipColorName() {
        return this.pipColorName;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.translatable(FalloutConstants.PIPBOY_USAGE));
            String __colorNames = this.pipColorName.toString();
            String pipColorName = __colorNames.replaceAll("_", " ");
            components.add(Component.literal(pipColorName));
        } else {
            components.add(Component.translatable(FalloutConstants.ITEM_SHIFT));
        }
        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return super.use(level, player, hand);
    }
}
