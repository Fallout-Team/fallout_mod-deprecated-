package net.Monsterwaill.falloutmod.data.server;

import net.Monsterwaill.falloutmod.FalloutMod;
import net.Monsterwaill.falloutmod.block.FalloutBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class FalloutBlockTags extends BlockTagsProvider {

    public FalloutBlockTags(DataGenerator dataGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, FalloutMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        add(BlockTags.MINEABLE_WITH_AXE, FalloutBlocks.RADIO.get());
    }

    public void add(TagKey<Block> branch, Block block) {
        this.tag(branch).add(block);
    }

    public void add(TagKey<Block> branch, Block... blocks) {
        this.tag(branch).add(blocks);
    }
}