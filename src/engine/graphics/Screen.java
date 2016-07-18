package engine.graphics;

/**
 * Created by Felix on 17.07.2016.
 */

import engine.input.Keyboard;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;


public class Screen implements Runnable {

    private int width;
    private int height;
    private int UPS = 60;
    private int FPS;

    private String title;

    private Thread thread;
    private Graphics graphics;

    private long window;

    private boolean resizeable = false;
    private boolean running = false;

    private GraphicsPanel graphicsPanel;

    public Screen(int width_, int height_, String title_, GraphicsPanel graphicsPanel_) {
        width = width_;
        height = height_;
        title = title_;
        graphicsPanel = graphicsPanel_;
        running = true;
        graphics = new Graphics();

        startThread("OpenGL Rendering");
    }

    @Override
    public void run() {
        init();
        graphicsPanel.setup();
        long lastTime = System.nanoTime();
        double ns = 1000000000 / UPS;
        long timer = System.currentTimeMillis();
        double delta = 0;
        int updates = 0;
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                updates++;
                delta--;
                glfwPollEvents();
                graphicsPanel.update();
            }
            frames++;
            if (System.currentTimeMillis() - timer >= 1000) {
                timer += 1000;
                System.out.println(updates + "ups, " + frames + "fps ");
                updates = 0;
                frames = 0;
            }
            graphicsPanel.render(graphics);
            glfwSwapBuffers(window);

            if (glfwWindowShouldClose(window)) {
                running = false;
                glfwDestroyWindow(window);
                glfwTerminate();
            }

        }
    }

    private void init() {
        if (!glfwInit()) {
            return;
        }

        if (resizeable) {
            glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
        }

        window = glfwCreateWindow(width, height, title, 0, 0);
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window, (vidmode.width() - width) / 2, (vidmode.height() - height) / 2);

        glfwSetKeyCallback(window, new Keyboard());
        glfwMakeContextCurrent(window);
        glfwShowWindow(window);

        GL.createCapabilities();
        System.out.println("OpenGL " +  glGetString(GL_VERSION));

        Shader.loadAll();

        //glEnable(GL_DEPTH_TEST);
        glActiveTexture(GL_TEXTURE1);

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

    }

    private synchronized void startThread(String name) {
        thread = new Thread(this, name);
        thread.start();
    }

    private synchronized void stopThread() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.err.println("Thread join failed");
        }
    }


}
