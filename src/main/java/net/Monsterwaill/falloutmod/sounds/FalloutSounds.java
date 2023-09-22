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
    public static final RegistryObject<SoundEvent> BLAST_DOOR_OPEN = SOUNDS.register("blast_door_open", () -> new SoundEvent(new ResourceLocation(FalloutMod.MOD_ID, "blast_door_open")));
    public static final RegistryObject<SoundEvent> TITLE_MUSIC = SOUNDS.register("title_music", () -> new SoundEvent(new ResourceLocation(FalloutMod.MOD_ID, "title_music")));
    public static final RegistryObject<SoundEvent> STIMPACK_USE = SOUNDS.register("stimpack_use", () -> new SoundEvent(new ResourceLocation(FalloutMod.MOD_ID, "stimpack_use")));
}
