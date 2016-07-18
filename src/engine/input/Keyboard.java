package engine.input;

import org.lwjgl.glfw.GLFWKeyCallback;

/**
 * Created by Felix on 17.07.2016.
 */

import static org.lwjgl.glfw.GLFW.*;

public class Keyboard extends GLFWKeyCallback {



    public static boolean[] keys = new boolean[65536];


    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        keys[key] = action == GLFW_RELEASE ? false : true;
    }

    public static boolean isPressed(int key) {
        return keys[key];
    }
}
