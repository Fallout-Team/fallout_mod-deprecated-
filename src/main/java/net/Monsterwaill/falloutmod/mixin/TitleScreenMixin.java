package net.Monsterwaill.falloutmod.mixin;


import net.Monsterwaill.falloutmod.FalloutMod;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.renderer.CubeMap;
import net.minecraft.client.renderer.PanoramaRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TitleScreen.class)
@OnlyIn(Dist.CLIENT)

public class TitleScreenMixin {

    private static final PanoramaRenderer MOD_PANORAMA = new PanoramaRenderer(new CubeMap(new ResourceLocation(FalloutMod.MOD_ID, "textures/gui/title/background/panorama")));

    /*@Redirect(method = "Lnet/minecraft/client/gui/screen/TitleScreen;render(Lcom/mojang/blaze3d/matrix/MatrixStack;IIF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderSkybox;render(FF)V", ordinal = 0))
    private void replacePanorama(PanoramaRenderer oldPanorama, float pDeltaT, float pAlpha) {
        boolean isConfigEnabled = true; // Change this when you get a configuration file.
        if (isConfigEnabled) MOD_PANORAMA.render(pDeltaT, pAlpha);
        else oldPanorama.render(pDeltaT, pAlpha);
    }*/
}
