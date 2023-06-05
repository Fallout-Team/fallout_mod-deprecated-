package net.Monsterwaill.falloutmod.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.Monsterwaill.falloutmod.FalloutMod;
import net.Monsterwaill.falloutmod.network.Network;
import net.Monsterwaill.falloutmod.network.packets.UpdatePipColorC2SPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Widget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.OptionsScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;

import java.util.Random;
import java.util.UUID;

public class PipBoyScreen extends Screen {

    //Green set
    private static final ResourceLocation GREEN_DFLT = new ResourceLocation(FalloutMod.MOD_ID,"textures/gui/pipboy_default.png");
    private static final ResourceLocation STAT = new ResourceLocation(FalloutMod.MOD_ID,"textures/gui/pipboy_stat.png");
    private static final ResourceLocation RADIO = new ResourceLocation(FalloutMod.MOD_ID,"textures/gui/pipboy_radio.png");

    private Player player;
    private EnumPipColor getPipColor = EnumPipColor.GREEN;
    private int textColor;
    protected int imageWidth = 256;
    protected int imageHeight = 131;
    private ResourceLocation texture;
    private Button stats, radio, inv, options, capture;

    public PipBoyScreen(Component component, Player player) {
        super(component);
        this.player = player;
        if(this.getPipColor == EnumPipColor.GREEN) {
            this.textColor = 0x4CFF00;
            this.texture = GREEN_DFLT;
        }

        if (this.minecraft == null) {
            this.minecraft = this.getMinecraft();
        }
    }

    @Override
    protected void init() {
        super.init();
        int l = (this.height - this.imageHeight) / 2;
        int i = (this.width - this.imageWidth) / 2;
        assert this.minecraft != null;

        //Screens
        this.stats = new Button((i) + (this.imageWidth/2) + -85,l + 10,25,10, Component.nullToEmpty("[[]]"), (p_96786_) -> {
            this.toStats(this);
        });
        this.radio = new Button((i) + (this.imageWidth/2) + 57,l + 10,25,10, Component.nullToEmpty("[[]]"), (p_96786_) -> {
            this.toRadio(this);
        });
        this.inv = new Button((i) + (this.imageWidth/2) - 50,l + 10,25,10, Component.nullToEmpty("[[]]"), (p_96786_) -> {
            this.openInventoryScreen();
        });

        //Top buttons
        this.options = new Button((i) + (this.imageWidth/2) + -112,l + 10,10,10, Component.nullToEmpty("[]"), (p_96786_) -> {
            this.toOptions(this);
        });
        this.capture = new Button((i) + (this.imageWidth/2) + 100,l + 10,10,10, Component.nullToEmpty("[]"), (p_96786_) -> {
            this.fakeout();
        });

        //Adding widgets: If you need to see the widget, add "Renderable" in front of Widget to get "this.addRenderableWidget()"
        this.addWidget(this.stats);
        this.addWidget(this.radio);
        this.addWidget(this.inv);
        this.addWidget(this.options);
        this.addWidget(this.capture);
    }

    private int getStringWidth(String label) {
        Font font = Minecraft.getInstance().font;
        return font.width(label);
    }

    //@TODO: condense into one method - Loqor
    public void toStats(Screen currentScreen) {
        Screen nextScreen = new PipBoyStatsScreen(Component.translatable("Stats"), this.player, currentScreen, this.getGetPipColor());
        this.minecraft.getInstance().setScreen(nextScreen);
    }

    //This is a terrible way to do this, but it's 1 in the morning and I can fix it when I have more brain power - Loqor
    public void toRadio(Screen currentScreen) {
        Screen nextScreen = new PipBoyRadioScreen(Component.translatable("Radio"), this.player, currentScreen);
        this.minecraft.getInstance().setScreen(nextScreen);
    }

    public void openInventoryScreen() {
        this.minecraft.getInstance().setScreen(new InventoryScreen(this.player));
    }

    public void changePipBoyColor() {
       this.getPipColor = getNextPipColor(this.getPipColor);
       System.out.println(this.getPipColor);
        Network.sendToServer(new UpdatePipColorC2SPacket(this.getPipColor, this.player.getUUID()));
    }

    public EnumPipColor getGetPipColor() {
        return this.getPipColor;
    }

    public static EnumPipColor getNextPipColor(EnumPipColor pipColor) {
        switch(pipColor) {
            case GREEN:
                return EnumPipColor.AMBER;
            case AMBER:
                return EnumPipColor.RED;
            case RED:
                return EnumPipColor.ICE_BLUE;
            case ICE_BLUE:
                return EnumPipColor.INDIGO;
            case INDIGO:
                return EnumPipColor.GREEN;
            default:
                return EnumPipColor.GREEN;
        }
    }

    //This is temporary until I figure out a cool way to take "pictures" with the Pip-Boy - Loqor
    public void fakeout() {
        this.onClose();
        this.player.displayClientMessage(Component.translatable("Press F2, ya dingus."), true);
        System.out.println(getGetPipColor());
    }

    public void toOptions(Screen currentScreen) {
        Screen nextScreen = new PipBoyOptionsScreen(Component.translatable("Options"), this.player, currentScreen);
        this.minecraft.getInstance().setScreen(nextScreen);
    }

    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        this.renderBg(pPoseStack);
        int l = (this.height - this.imageHeight) / 2;
        int i = (this.width - this.imageWidth) / 2;
        super.render(pPoseStack, mouseX, mouseY, delta);
    }

    public void renderBg(PoseStack pPoseStack) {
        RenderSystem.setShaderTexture(0, this.texture);
        int i = (this.width - this.imageWidth) / 2;
        int l = (this.height - this.imageHeight) / 2;
        blit(pPoseStack, i, l, 0, 0, this.imageWidth, this.imageHeight);
        pPoseStack.pushPose();
        this.font.draw(pPoseStack,"HP " + (int) this.player.getHealth() + "/" + (int) this.player.getMaxHealth(), (i) + 34, (l) + 112,this.textColor);
        this.font.draw(pPoseStack,"LEVEL " + this.player.experienceLevel + " [ " + this.player.getXpNeededForNextLevel() + " ]", (i) + 83, (l) + 112,this.textColor);
        this.font.draw(pPoseStack,"AP " + this.player.getFoodData().getFoodLevel() + "/" + "20"/*"AP ∞/∞"*/, (i) + 174, (l) + 112,this.textColor);
        pPoseStack.popPose();
    }
}
