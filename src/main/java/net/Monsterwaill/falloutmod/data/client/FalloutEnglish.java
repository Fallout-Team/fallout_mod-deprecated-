package net.Monsterwaill.falloutmod.data.client;

import net.Monsterwaill.falloutmod.FalloutMod;
import net.Monsterwaill.falloutmod.block.FalloutBlocks;
import net.Monsterwaill.falloutmod.data.FalloutConstants;
import net.Monsterwaill.falloutmod.item.FalloutItems;
import net.minecraft.ChatFormatting;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class FalloutEnglish extends LanguageProvider {

    public FalloutEnglish(DataGenerator gen) {
        super(gen, FalloutMod.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        /*Items*/
        add(FalloutItems.CREATIVETAB.get(), "Creative Tab");
        add(FalloutItems.HOLOTAPE.get(), "Holotape");
        add(FalloutItems.PIPBOY.get(), "Pip-Boy 2500");
        add(FalloutItems.CAP.get(), "Bottle Cap");

        /*Blocks*/
        add(FalloutBlocks.TARDIS_BLOCK.get(), "TARDIS");
        add(FalloutBlocks.RADIO.get(), "Radiation King Radio");

        /*Messages*/
        add(FalloutConstants.PIPBOY_USAGE, ChatFormatting.DARK_GREEN + "The Pip-Boy 2500 is only operational when held in the offhand slot. For more info please refer to your Pip-Boy manual provided by vault tech.");
        add(FalloutConstants.ITEM_SHIFT, ChatFormatting.DARK_GREEN + "Press Shift");
        add(FalloutConstants.NUMBER, "Number");

        /*Tabs*/
        add("itemGroup.fallouttab", "Fallout Mod");
    }
}
