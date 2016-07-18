package test;

import engine.graphics.Shader;
import engine.graphics.VertexArray;
import engine.textures.Texture;

/**
 * Created by Felix on 18.07.2016.
 */
public class Bird {

    private Texture texture;
    private VertexArray mesh;

    private int x, y;
    private int width, height;

    private float SIZE = 1f;

    float[] vertices = new float[] {
            -1 / 2.0f, -1 / 2.0f, 1,
            -1 / 2.0f,  1 / 2.0f, 1,
            1 / 2.0f,  1 / 2.0f, 1,
            1 / 2.0f, -1 / 2.0f, 1
    };

    byte[] indices = new byte[] {
            0, 1, 2,
            2, 3, 0
    };

    float[] tcs = new float[] {
            0, 1,
            0, 0,
            1, 0,
            1, 1
    };

    public Bird(int x_, int y_) {
        x = x_;
        y = y_;
        mesh = new VertexArray(vertices, indices, tcs);
        texture = new Texture("res/bird.png");
    }

    public void draw() {
        Shader.STATICSHADER.enable();
        texture.bind();
        mesh.render();
        Shader.STATICSHADER.disable();
        texture.unbind();
    }

}
