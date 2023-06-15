package net.Monsterwaill.falloutmod.block;

import net.Monsterwaill.falloutmod.FalloutMod;
import net.Monsterwaill.falloutmod.item.FalloutTabs;
import net.Monsterwaill.falloutmod.item.FalloutItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class FalloutBlocks {



    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, FalloutMod.MOD_ID);
    public static final RegistryObject<Block> RADIO = register("radio", () -> new RadioBlock(BlockBehaviour.Properties.of(Material.METAL).noOcclusion()));
    public static final RegistryObject<TARDISBlock> TARDIS_BLOCK = registerBlockOnly("tardis", () -> new TARDISBlock(BlockBehaviour.Properties.of(Material.WOOD).noOcclusion()));
    public static final RegistryObject<Block> TYPEWRITER = register("typewriter", () -> new TypewriterBlock(BlockBehaviour.Properties.of(Material.WOOD).noOcclusion()));
    public static final RegistryObject<Block> STAND = register("stand", () -> new StandBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL).noOcclusion()));
    public static final RegistryObject<Block> BLAST_DOOR = register("blast_door", () -> new BlastDoorBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL).noOcclusion()));
    /**
     * Registers a Block and BlockItem to the ItemGroup of your choice
     */
    private static <T extends Block> RegistryObject<T> register(String id, Supplier<T> blockSupplier, CreativeModeTab itemGroup) {
        RegistryObject<T> registryObject = BLOCKS.register(id, blockSupplier);
        FalloutItems.ITEMS.register(id, () -> new BlockItem(registryObject.get(), new Item.Properties().tab(itemGroup)));
        return registryObject;
    }

    /**
     * Registers a Block without a BlockItem
     * <br> Use when you need a special BlockItem. The BlockItem should be registered in FalloutItems with the same registry name as the block
     */
    private static <T extends Block> RegistryObject<T> registerBlockOnly(String id, Supplier<T> blockSupplier) {
        return BLOCKS.register(id, blockSupplier);
    }

    /**
     * Registers a Block and BlockItem into the Main ItemGroup
     */
    private static <T extends Block> RegistryObject<T> register(String id, Supplier<T> blockSupplier) {
        RegistryObject<T> registryObject = BLOCKS.register(id, blockSupplier);
        FalloutItems.ITEMS.register(id, () -> new BlockItem(registryObject.get(), new Item.Properties().tab(FalloutTabs.FALLOUT_TAB)));
        return registryObject;
    }

}
