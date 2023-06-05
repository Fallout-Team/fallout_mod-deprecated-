package net.Monsterwaill.falloutmod.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.Monsterwaill.falloutmod.FalloutMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Widget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.OptionsScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class PipBoyStatsScreen extends PipBoyScreen {

    //Green set
    private static final ResourceLocation STAT = new ResourceLocation(FalloutMod.MOD_ID,"textures/gui/pipboy_stat.png");

    private Player player;
    private int textColor;
    protected int imageWidth = 256;
    protected int imageHeight = 131;
    private ResourceLocation texture;
    private Button main, radio, inv, options, capture;
    private final Screen oldScreen;

    public PipBoyStatsScreen(Component component, Player player, Screen currentScreen, EnumPipColor color) {
        super(component, player);
        this.player = player;
        this.oldScreen = currentScreen;
        if(getGetPipColor() == EnumPipColor.GREEN) {
            this.textColor = 0x4CFF00;
            this.texture = STAT;
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
        this.main = new Button((i) + (this.imageWidth/2) + -85,l + 10,25,10, Component.nullToEmpty("[[]]"), (p_96786_) -> {
            this.backToMain(oldScreen);
        });
        radio = new Button((i) + (this.imageWidth/2) + 57,l + 10,25,10, Component.nullToEmpty("[[]]"), (p_96786_) -> {
            toRadio(this);
        });
        inv = new Button((i) + (this.imageWidth/2) - 50,l + 10,25,10, Component.nullToEmpty("[[]]"), (p_96786_) -> {
            openInventoryScreen();
        });

        //Top buttons
        options = new Button((i) + (this.imageWidth/2) + -113,l + 10,10,10, Component.nullToEmpty("[]"), (p_96786_) -> {
            toOptions(this);
        });
        capture = new Button((i) + (this.imageWidth/2) + 100,l + 10,10,10, Component.nullToEmpty("[]"), (p_96786_) -> {
            fakeout();
        });

        //Adding widgets: If you need to see the widget, add "Renderable" in front of Widget to get "this.addRenderableWidget()"
        this.addWidget(this.main);
        this.addWidget(radio);
        this.addWidget(inv);
        this.addWidget(options);
        this.addWidget(capture);
    }

    private int getStringWidth(String label) {
        Font font = Minecraft.getInstance().font;
        return font.width(label);
    }

    private void backToMain(Screen screen) {
        Minecraft.getInstance().setScreen(screen);
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
        this.font.draw(pPoseStack, "STIMPAK" + "[" + this.player.getInventory().countItem(Items.GOLDEN_APPLE) + "]",(i) + 34, (l) + 99, 0x1A5900);
        this.font.draw(pPoseStack, "RADAWAY" + "[" + this.player.getInventory().countItem(Items.ENCHANTED_GOLDEN_APPLE) + "]",(i) + 165, (l) + 99, 0x1A5900);
        pPoseStack.popPose();
    }
}
