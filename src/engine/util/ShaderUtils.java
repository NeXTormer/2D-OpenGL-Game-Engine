package engine.util;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glDeleteShader;

/**
 * Created by Felix on 18.07.2016.
 */
public class ShaderUtils {

    private ShaderUtils() {
    }

    public static int load(String vertPath, String fragPath) {
        String vert = FileUtils.loadAsString(vertPath);
        String frag = FileUtils.loadAsString(fragPath);
        return create(vert, frag, vertPath, fragPath);
    }

    public static int create(String vert, String frag, String vertPath, String fragPath) {
        int program = glCreateProgram();
        int vertID = glCreateShader(GL_VERTEX_SHADER);
        int fragID = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(vertID, vert);
        glShaderSource(fragID, frag);

        glCompileShader(vertID);
        if (glGetShaderi(vertID, GL_COMPILE_STATUS) == GL_FALSE) {
            System.err.println("Failed to compile vertex shader! (" + vertPath + ")");
            System.err.println(glGetShaderInfoLog(vertID));
            return -1;
        }

        glCompileShader(fragID);
        if (glGetShaderi(fragID, GL_COMPILE_STATUS) == GL_FALSE) {
            System.err.println("Failed to compile fragment shader! (" + fragPath + ")");
            System.err.println(glGetShaderInfoLog(fragID));
            return -1;
        }

        glAttachShader(program, vertID);
        glAttachShader(program, fragID);
        glLinkProgram(program);
        glValidateProgram(program);

        glDeleteShader(vertID);
        glDeleteShader(fragID);

        System.out.println("Shader compiled: " + program);

        return program;
    }
}
