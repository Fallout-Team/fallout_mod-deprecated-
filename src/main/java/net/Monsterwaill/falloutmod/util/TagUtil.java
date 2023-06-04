package net.Monsterwaill.falloutmod.util;

import net.Monsterwaill.falloutmod.FalloutMod;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class TagUtil {

    public static TagKey<Block> makeBlock(String domain, String path) {
        return TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(domain, path));
    }

    private static TagKey<Biome> makeBiome(String name) {
        return TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(FalloutMod.MOD_ID, name));
    }

    private static TagKey<MobEffect> makeMobEffects(String name) {
        return TagKey.create(Registry.MOB_EFFECT_REGISTRY, new ResourceLocation(FalloutMod.MOD_ID, name));
    }

    public static TagKey<Item> makeItem(String domain, String path) {
        return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(domain, path));
    }

}
