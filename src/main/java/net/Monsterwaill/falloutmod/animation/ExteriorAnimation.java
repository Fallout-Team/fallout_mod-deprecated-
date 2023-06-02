package net.Monsterwaill.falloutmod.animation;

import net.Monsterwaill.falloutmod.block.entities.TARDISBlockEntity;
import net.Monsterwaill.falloutmod.network.Network;
import net.Monsterwaill.falloutmod.network.packets.UpdateExteriorAnimationS2CPacket;
import net.minecraft.util.Mth;

public abstract class ExteriorAnimation {
    protected float alpha;
    protected TARDISBlockEntity exterior;
    protected int timeLeft;
    protected float alphaChangeAmount = 0.005f;
    protected boolean started = false;

    public ExteriorAnimation(TARDISBlockEntity exterior) {
        this.exterior = exterior;
    }

    public float getAlpha() {
        return Mth.clamp(this.alpha,0.0F,1.0F);
    }
    public boolean isStarted() {return this.started;}
    public void setStarted(boolean val) {this.started = val;}
    public void start() {this.started = true;}
    public abstract void tick();
    public abstract void setupAnimation();

    public void setAlpha(float alpha) {
        this.alpha = Mth.clamp(alpha,0.0F,1.0F);
//        System.out.println("set alpha to " + alpha);
    }

    public void setAlphaChangeAmount(float amount) {
        this.alphaChangeAmount = amount;
    }

    public void updateClient() {
        if (this.exterior.getLevel().isClientSide) {return;}

        Network.sendToAll(new UpdateExteriorAnimationS2CPacket(exterior.getBlockPos(),this.getAlpha(),this.isStarted()));
    }
}
