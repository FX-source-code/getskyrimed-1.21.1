package ch.fx.getskyrimed.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class ModKeys {
    public static final KeyMapping LEFT_HAND_MAPPING = new KeyMapping("key.getskyrimed.left_hand", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_H, "key.getskyrimed");
}
