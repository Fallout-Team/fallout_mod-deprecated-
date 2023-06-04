package net.Monsterwaill.falloutmod.animation;


import net.Monsterwaill.falloutmod.network.Network;
import net.Monsterwaill.falloutmod.network.packets.UpdateExteriorAnimationS2CPacket;
import net.Monsterwaill.falloutmod.block.entities.TARDISBlockEntity;

public class ExteriorClassicAnimation extends ExteriorAnimation {
    private boolean firstRun;

    public ExteriorClassicAnimation(TARDISBlockEntity exterior) {
        super(exterior);
    }

    @Override
    public void tick() {
        if (!this.started) {
            if (this.alpha != 1f) {
                this.setupAnimation();
            }
            return;
        };
        this.updateClient();
        alpha = alpha - alphaChangeAmount;
        timeLeft--;
    }

    @Override
    public void setupAnimation() {
        alpha = 1f;
        timeLeft = 150;
        firstRun = true;

        this.updateClient();
    }

    public void setFirstRun(boolean firstRun) {
        this.firstRun = firstRun;
    }

    @Override
    public void updateClient() {
        if (this.exterior.getLevel().isClientSide) {return;}

        Network.sendToAll(new UpdateExteriorAnimationS2CPacket(exterior.getBlockPos(),this.getAlpha(),this.isStarted(),this.firstRun));
    }
}
