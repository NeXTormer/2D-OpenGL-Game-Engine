package test;


import engine.graphics.Graphics;
import engine.graphics.GraphicsPanel;
import engine.graphics.Shader;
import engine.input.Keyboard;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by Felix on 17.07.2016.
 */
public class Panel extends GraphicsPanel {

    int x = 1;

    private Bird b;

    public void setup() {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, 600, 600, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);

        glClearColor(0.3f, 0, 0.3f, 1);

        b = new Bird(200, 200);
    }

    public void update() {
        if(Keyboard.isPressed(GLFW_KEY_A)) {
            x++;
        }
        if(Keyboard.isPressed(GLFW_KEY_S)) {
            x--;
        }
    }

    public void render(Graphics g) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

//        g.setColor(0.2f, 1, 0);
//        g.drawQuad(50, 50, 100, 100);
//        g.setColor(1, 0.1f, 0.4f);
//        g.drawQuad(100, 100, 200, 200);
//        g.setColor(1, 0.5f, 0.5f);
//        g.drawCircle(300, 300, x);

        b.draw();

    }



}
