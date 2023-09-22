package net.Monsterwaill.falloutmod.data.client;

import net.Monsterwaill.falloutmod.FalloutMod;
import net.Monsterwaill.falloutmod.block.FalloutBlocks;
import net.Monsterwaill.falloutmod.data.FalloutConstants;
import net.Monsterwaill.falloutmod.entities.FalloutEntities;
import net.Monsterwaill.falloutmod.item.FalloutItems;
import net.Monsterwaill.falloutmod.util.Keybinding;
import net.minecraft.ChatFormatting;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class FalloutTokiPona extends LanguageProvider {
    public FalloutTokiPona(DataGenerator gen) {
        super(gen, FalloutMod.MOD_ID, "en_tok");
    }

    @Override
    protected void addTranslations() {
        /*Items*/
        add(FalloutItems.CREATIVETAB.get(), "kulupu Fallout Mod");
        add(FalloutItems.HOLOTAPE.get(), "ilo pi jo kalama");
        add(FalloutItems.PIPBOY.get(), "ilo Pip-Boy 2500");
        add(FalloutItems.STIMPACKITEM.get(), "Stimpack");
        add(FalloutItems.CAP.get(), "mani");


        /*Blocks*/
        add(FalloutBlocks.TARDIS_BLOCK.get(), "TARDIS");
        add(FalloutBlocks.RADIO.get(), "jan lawa Radio pi wawa jaki");
        add(FalloutBlocks.TYPEWRITER.get(), "ilo sitelen");
        add(FalloutBlocks.STAND.get(), "jo Pip-Boy 2500");
        add(FalloutBlocks.BLAST_DOOR.get(), "lupa insa Vault");
        add(FalloutBlocks.URANIUM_ORE.get(), "Uranium Ore");

        /*Messages*/
        add(FalloutConstants.PIPBOY_USAGE, ChatFormatting.DARK_GREEN + "sina wile kepeken ilo Pip-Boy 2500 la o luka e ona lon luka pi wawa lili. sina wile e sona mute la o lukin e lipu sona Pip-Boy tan kulupu Valut Tech");
        add(FalloutConstants.ITEM_SHIFT, ChatFormatting.DARK_GREEN + "o luka e nena");
        add(FalloutConstants.NUMBER, "Number");

        /*Titles*/
        add(FalloutConstants.PIPBOY_SCREEN, ChatFormatting.GREEN + "ilo Pip-Boy 2500");

        /*Tabs*/
        add("itemGroup.fallouttab", "Fallout Mod");

        /*Keybinds*/
        add(Keybinding.KEY_CATEGORY_FALLOUT, "Fallout Mod");
        add(Keybinding.KEY_OPEN_PIP_BOY, "mi lukin e sitelen Pip-Boy 2500");

        /*Entities*/
        add(FalloutEntities.POWER_ARMOR.get(),"len wawa");
    }
}
