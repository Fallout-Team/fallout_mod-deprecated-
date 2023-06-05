package net.Monsterwaill.falloutmod.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.Monsterwaill.falloutmod.FalloutMod;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class Keybinding {

    public static final String KEY_CATEGORY_FALLOUT = "key.category." + FalloutMod.MOD_ID + ".fallout";

    public static final String KEY_OPEN_PIP_BOY = "key." + FalloutMod.MOD_ID + ".open_pip_boy";

    public static final KeyMapping PIPBOY_KEY = new KeyMapping(KEY_OPEN_PIP_BOY, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_B, KEY_CATEGORY_FALLOUT);

}
