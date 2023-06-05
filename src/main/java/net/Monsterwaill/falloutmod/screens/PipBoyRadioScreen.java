package net.Monsterwaill.falloutmod.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.Monsterwaill.falloutmod.FalloutMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;

public class PipBoyRadioScreen extends PipBoyScreen {

    //Green set
    private static final ResourceLocation RADIO = new ResourceLocation(FalloutMod.MOD_ID,"textures/gui/pipboy_radio.png");

    private Player player;
    private EnumPipColor getPipColor = EnumPipColor.GREEN;
    private int textColor;
    protected int imageWidth = 256;
    protected int imageHeight = 131;
    private ResourceLocation texture;
    private Button main, stats, inv, options, capture;
    private Button thirteen, cat, blocks, chirp, far, mall, mellohi, stal, strad, ward, eleven, wait, otherside, five, pigstep;
    private final Screen oldScreen;
    private SimpleSoundInstance sound;

    public PipBoyRadioScreen(Component component, Player player, Screen currentScreen) {
        super(component, player);
        this.player = player;
        this.oldScreen = currentScreen;
        if(this.getPipColor == EnumPipColor.GREEN) {
            this.textColor = 0x4CFF00;
            this.texture = RADIO;
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
        stats = new Button((i) + (this.imageWidth/2) + -85,l + 10,25,10, Component.nullToEmpty("[[]]"), (p_96786_) -> {
            toStats(this);
        });
        inv = new Button((i) + (this.imageWidth/2) - 50,l + 10,25,10, Component.nullToEmpty("[[]]"), (p_96786_) -> {
            openInventoryScreen();
        });
        this.main = new Button((i) + (this.imageWidth/2) + 57,l + 10,25,10, Component.nullToEmpty("[[]]"), (p_96786_) -> {
            this.backToMain(oldScreen);
        });

        //Top buttons
        options = new Button((i) + (this.imageWidth/2) + -112,l + 10,10,10, Component.nullToEmpty("[]"), (p_96786_) -> {
            toOptions(this);
        });
        capture = new Button((i) + (this.imageWidth/2) + 100,l + 10,10,10, Component.nullToEmpty("[]"), (p_96786_) -> {
            fakeout();
        });

        //Adding widgets: If you need to see the widget, add "Renderable" in front of Widget to get "this.addRenderableWidget()"
        this.addWidget(this.main);
        this.addWidget(stats);
        this.addWidget(inv);
        this.addWidget(options);
        this.addWidget(capture);

        //Screen specific buttons
        this.thirteen = new Button((i) + 20, (l) + 25,40,10, Component.nullToEmpty("[]"), (p_96786_) -> {
            this.playRecord(1);
        });
        this.cat = new Button((i) + 20, (l) + 35,40,10, Component.nullToEmpty("[]"), (p_96786_) -> {
            this.playRecord(2);
        });
        this.blocks = new Button((i) + 20, (l) + 45,50,10, Component.nullToEmpty("[]"), (p_96786_) -> {
            this.playRecord(3);
        });
        this.chirp = new Button((i) + 20, (l) + 55,40,10, Component.nullToEmpty("[]"), (p_96786_) -> {
            this.playRecord(4);
        });
        this.far = new Button((i) + 20, (l) + 65,30,10, Component.nullToEmpty("[]"), (p_96786_) -> {
            this.playRecord(5);
        });
        this.mall = new Button((i) + 20, (l) + 75,50,10, Component.nullToEmpty("[]"), (p_96786_) -> {
            this.playRecord(6);
        });
        this.mellohi = new Button((i) + 20, (l) + 85,40,10, Component.nullToEmpty("[]"), (p_96786_) -> {
            this.playRecord(7);
        });
        this.stal = new Button((i) + 20, (l) + 95,50,10, Component.nullToEmpty("[]"), (p_96786_) -> {
            this.playRecord(8);
        });
        this.strad = new Button((i) + 70, (l) + 25,60,10, Component.nullToEmpty("[]"), (p_96786_) -> {
            this.playRecord(9);
        });
        this.ward = new Button((i) + 70, (l) + 35,50,10, Component.nullToEmpty("[]"), (p_96786_) -> {
            this.playRecord(10);
        });
        this.eleven = new Button((i) + 70, (l) + 45,70,10, Component.nullToEmpty("[]"), (p_96786_) -> {
            this.playRecord(11);
        });
        this.wait = new Button((i) + 70, (l) + 55,50,10, Component.nullToEmpty("[]"), (p_96786_) -> {
            this.playRecord(12);
        });
        this.otherside = new Button((i) + 70, (l) + 65,70,10, Component.nullToEmpty("[]"), (p_96786_) -> {
            this.playRecord(13);
        });
        this.five = new Button((i) + 70, (l) + 75,20,10, Component.nullToEmpty("[]"), (p_96786_) -> {
            this.playRecord(14);
        });
        this.pigstep = new Button((i) + 70, (l) + 85,70,10, Component.nullToEmpty("[]"), (p_96786_) -> {
            this.playRecord(15);
        });

        this.addWidget(this.thirteen);
        this.addWidget(this.cat);
        this.addWidget(this.blocks);
        this.addWidget(this.chirp);
        this.addWidget(this.far);
        this.addWidget(this.mall);
        this.addWidget(this.mellohi);
        this.addWidget(this.stal);
        this.addWidget(this.strad);
        this.addWidget(this.ward);
        this.addWidget(this.eleven);
        this.addWidget(this.wait);
        this.addWidget(this.otherside);
        this.addWidget(this.five);
        this.addWidget(this.pigstep);
    }

    private int getStringWidth(String label) {
        Font font = Minecraft.getInstance().font;
        return font.width(label);
    }

    private void backToMain(Screen screen) {
        Minecraft.getInstance().setScreen(screen);
    }

    public SoundEvent chooseRecord(int number) {
        return switch (number) {
            case 1 -> SoundEvents.MUSIC_DISC_13;
            case 2 -> SoundEvents.MUSIC_DISC_CAT;
            case 3 -> SoundEvents.MUSIC_DISC_BLOCKS;
            case 4 -> SoundEvents.MUSIC_DISC_CHIRP;
            case 5 -> SoundEvents.MUSIC_DISC_FAR;
            case 6 -> SoundEvents.MUSIC_DISC_MALL;
            case 7 -> SoundEvents.MUSIC_DISC_MELLOHI;
            case 8 -> SoundEvents.MUSIC_DISC_STAL;
            case 9 -> SoundEvents.MUSIC_DISC_STRAD;
            case 10 -> SoundEvents.MUSIC_DISC_WARD;
            case 11 -> SoundEvents.MUSIC_DISC_11;
            case 12 -> SoundEvents.MUSIC_DISC_WAIT;
            case 13 -> SoundEvents.MUSIC_DISC_OTHERSIDE;
            case 14 -> SoundEvents.MUSIC_DISC_5;
            case 15 -> SoundEvents.MUSIC_DISC_PIGSTEP;
            default -> SoundEvents.ANVIL_HIT;
        };
    }

    public void playRecord(int number) {
        /*this.sound = SimpleSoundInstance.forRecord(this.chooseRecord(number), this.player.getX(), this.player.getY(), this.player.getZ());
        if(this.sound.getSound() == null) {
            return;
        } else {
            this.minecraft.getSoundManager().play(this.sound);
        }*/
        //this.player.playSound(this.chooseRecord(number));
        this.player.getLevel().playSound(this.player, this.player.blockPosition(), chooseRecord(number), SoundSource.RECORDS, 5, 1);
    }

    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        int l = (this.height - this.imageHeight) / 2;
        int i = (this.width - this.imageWidth) / 2;
        this.renderBg(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
    }

    public void renderBg(PoseStack pPoseStack) {
        RenderSystem.setShaderTexture(0, this.texture);
        int i = (this.width - this.imageWidth) / 2;
        int l = (this.height - this.imageHeight) / 2;
        blit(pPoseStack, i, l, 0, 0, this.imageWidth, this.imageHeight);
        pPoseStack.pushPose();
        this.font.draw(pPoseStack,"> 13", (i) + 20, (l) + 25,this.textColor);
        this.font.draw(pPoseStack,"> cat", (i) + 20, (l) + 35,this.textColor);
        this.font.draw(pPoseStack,"> blocks", (i) + 20, (l) + 45,this.textColor);
        this.font.draw(pPoseStack,"> chirp", (i) + 20, (l) + 55,this.textColor);
        this.font.draw(pPoseStack,"> far", (i) + 20, (l) + 65,this.textColor);
        this.font.draw(pPoseStack,"> mall", (i) + 20, (l) + 75,this.textColor);
        this.font.draw(pPoseStack,"> mellohi", (i) + 20, (l) + 85,this.textColor);
        this.font.draw(pPoseStack,"> stal", (i) + 20, (l) + 95,this.textColor);
        this.font.draw(pPoseStack,"> strad", (i) + 70, (l) + 25,this.textColor);
        this.font.draw(pPoseStack,"> ward", (i) + 70, (l) + 35,this.textColor);
        this.font.draw(pPoseStack,"> 11", (i) + 70, (l) + 45,this.textColor);
        this.font.draw(pPoseStack,"> wait", (i) + 70, (l) + 55,this.textColor);
        this.font.draw(pPoseStack,"> otherside", (i) + 70, (l) + 65,this.textColor);
        this.font.draw(pPoseStack,"> 5", (i) + 70, (l) + 75,this.textColor);
        this.font.draw(pPoseStack,"> pigstep", (i) + 70, (l) + 85,this.textColor);
        this.font.draw(pPoseStack,"HP " + (int) this.player.getHealth() + "/" + (int) this.player.getMaxHealth(), (i) + 34, (l) + 112,this.textColor);
        this.font.draw(pPoseStack,"LEVEL " + this.player.experienceLevel + " [ " + this.player.getXpNeededForNextLevel() + " ]", (i) + 83, (l) + 112,this.textColor);
        this.font.draw(pPoseStack,"AP " + this.player.getFoodData().getFoodLevel() + "/" + "20"/*"AP ∞/∞"*/, (i) + 174, (l) + 112,this.textColor);
        pPoseStack.popPose();
    }
}
