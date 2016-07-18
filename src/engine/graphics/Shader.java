package engine.graphics;

import engine.Maths.Matrix4f;
import engine.Maths.Vector3f;
import engine.util.ShaderUtils;

import java.awt.font.ShapeGraphicAttribute;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL20.*;

/**
 * Created by Felix on 18.07.2016.
 */
public class Shader {


    public static final int VERTEX_ATTRIB = 0;
    public static final int TCOORD_ATTRIB = 1;

    public static Shader STATICSHADER;

    private String vertPath;
    private String programName;

    private boolean enabled = false;

    private final int id;
    private Map<String, Integer> locationCache = new HashMap<>();

    public Shader(String vertexPath_, String fragmentPath_) {
        id = ShaderUtils.load(vertexPath_, fragmentPath_);
        vertPath = vertexPath_;
        programName = vertPath.split("/")[vertPath.split("/").length - 1];
    }

    public static void loadAll() {
        STATICSHADER = new Shader("shaders/static.vert", "shaders/static.frag");
    }

    private int getUniform(String name) {
        if(locationCache.containsKey(name)) return locationCache.get(name);

        int result = glGetUniformLocation(id, name);
        if(result == -1) {
            System.err.println("Could not locate uniform var: " + name + " in " + programName + "!");
        }
        locationCache.put(name, result);
        return result;
    }

    public void setUniform1i(String name, int value) {
        if(!enabled) enable();
        glUniform1i(getUniform(name), value);
    }

    public void setUniform1f(String name, float value) {
        if(!enabled) enable();
        glUniform1f(getUniform(name), value);
    }

    public void setUniform2f(String name, float x, float y) {
        if(!enabled) enable();
        glUniform2f(getUniform(name), x, y);
    }

    public void setUniform3f(String name, Vector3f value) {
        if(!enabled) enable();
        glUniform3f(getUniform(name), value.x, value.y, value.z);
    }

    public void setUniformMat4f(String name, Matrix4f matrix) {
        if(!enabled) enable();
        glUniformMatrix4fv(getUniform(name), false, matrix.toFloatBuffer());
    }

    public void enable() {
        glUseProgram(id);
        enabled = true;

    }

    public void disable() {
        glUseProgram(0);
        enabled = false;
    }

}
