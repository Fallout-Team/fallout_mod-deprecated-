package net.Monsterwaill.falloutmod.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.Monsterwaill.falloutmod.FalloutMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class PipBoySettingsScreen extends PipBoyScreen {

    //Green set
    private static final ResourceLocation OPTIONS = new ResourceLocation(FalloutMod.MOD_ID,"textures/gui/pipboy_options.png");

    private int textColor;
    protected int imageWidth = 256;
    protected int imageHeight = 131;
    private ResourceLocation texture;
    private Button pipBoyColoring, quit;
    private final Screen oldScreen;
    private Player player;

    public PipBoySettingsScreen(Component component, Player player, Screen currentScreen) {
        super(component, player);
        this.player = player;
        this.oldScreen = currentScreen;
        if(getGetPipColor() == EnumPipColor.GREEN) {
            this.textColor = 0x4CFF00;
            this.texture = OPTIONS;
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

        this.pipBoyColoring = new Button((i) + 80, (l) + 23,130,10, Component.nullToEmpty("[]"), (p_96786_) -> {
            changePipBoyColor();
        });
        this.quit = new Button((i) + 20, (l) + 55,50,10, Component.nullToEmpty("[]"), (p_96786_) -> {
            this.backToMain(oldScreen);
        });

        //Adding widgets: If you need to see the widget, add "Renderable" in front of Widget to get "this.addRenderableWidget()"
        this.addWidget(this.pipBoyColoring);
        this.addWidget(this.quit);
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
        this.font.draw(pPoseStack,"NEW", (i) + 20, (l) + 25,0x1A5900);
        this.font.draw(pPoseStack,"LOAD", (i) + 20, (l) + 35,0x1A5900);
        this.font.draw(pPoseStack,"██████", (i)+20, (l) + 45, 0x1A5900);
        this.font.draw(pPoseStack,"SETTINGS", (i) + 20, (l) + 45,this.textColor);
        this.font.draw(pPoseStack,"RETURN", (i) + 20, (l) + 55,this.textColor);
        String __colorNames = getGetPipColor().toString();
        String pipColorNames = __colorNames.replaceAll("_", " ");
        this.font.draw(pPoseStack,"Pip-Boy Colouring: " + pipColorNames, (i) + 80, (l) + 25,this.textColor);
        pPoseStack.popPose();
    }
}
