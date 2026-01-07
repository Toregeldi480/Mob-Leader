package com.toregeldi.keybinds;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ModKeyBinds {
    public static KeyBinding DESCEND_KEY;
    public static KeyBinding ASCEND_KEY;

    public static void initialize() {
        DESCEND_KEY = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.yourmod.descend",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_LEFT_ALT,
                "category.yourmod.movement"
        ));

        ASCEND_KEY = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.yourmod.ascend",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_SPACE,
                "category.yourmod.movement"
        ));
    }
}
