package engine.graphics;

/**
 * Created by Felix on 17.07.2016.
 */

import static org.lwjgl.opengl.GL11.*;

public class Graphics {

    private boolean usingVAOs = false;

    public Graphics() {

    }

    public void drawQuad(int x, int y, int width, int height) {
        if(usingVAOs == false) {
            glBegin(GL_QUADS);
            glVertex2i(x, y);
            glVertex2i(x + width, y);
            glVertex2i(x + width, y + height);
            glVertex2i(x, y + height);
            glEnd();
        } else {

        }
    }

    public void drawCircle(float cx, float cy, float r) {
        int num_segments = (int) (r * 1.5);
        float theta = (float) (2 * 3.1415926 / (float) num_segments);
        float c = (float) Math.cos(theta);//precalculate the sine and cosine
        float s = (float) Math.sin(theta);
        float t;

        float x = r;
        float y = 0;

        glBegin(GL_LINE_LOOP);
        for(int i = 0; i < num_segments; i++) {
            glVertex2f(x + cx, y + cy);
            t = x;
            x = c * x - s * y;
            y = s * t + c * y;
        }
        glEnd();
    }

    public void setColor(float r, float g, float b) {
        glColor3f(r, g, b);
    }




}
