package net.Monsterwaill.falloutmod.sounds;

import net.Monsterwaill.falloutmod.FalloutMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FalloutSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, FalloutMod.MOD_ID);

    public static final RegistryObject<SoundEvent> DEMATERIALISE = SOUNDS.register("dematerialise", () -> new SoundEvent(new ResourceLocation(FalloutMod.MOD_ID, "dematerialise")));
    public static final RegistryObject<SoundEvent> MAIN_MENU_MUSIC = SOUNDS.register("main_menu_music", () -> new SoundEvent(new ResourceLocation(FalloutMod.MOD_ID, "main_menu_music")));
}
