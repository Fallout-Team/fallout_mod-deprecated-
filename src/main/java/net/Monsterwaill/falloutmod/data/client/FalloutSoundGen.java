package net.Monsterwaill.falloutmod.data.client;

import net.Monsterwaill.falloutmod.FalloutMod;
import net.Monsterwaill.falloutmod.sounds.FalloutSounds;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

import java.util.function.Supplier;

public class FalloutSoundGen extends SoundDefinitionsProvider {
    public FalloutSoundGen(DataGenerator generator,ExistingFileHelper helper) {
        super(generator, FalloutMod.MOD_ID, helper);
    }

    @Override
    public void registerSounds() {
        this.addBasicSound(FalloutSounds.DEMATERIALISE,"dematerialise");
        this.addBasicSound(FalloutSounds.BLAST_DOOR_OPEN,"blast_door_open");
    }

    private void addBasicSound(Supplier<SoundEvent> soundEvent, String id) {
        this.add(soundEvent,definition()
                .subtitle("subtitle." + FalloutMod.MOD_ID + "." + id)
                .with(
                        sound(new ResourceLocation(FalloutMod.MOD_ID, id))
                )
        );
    }
}
