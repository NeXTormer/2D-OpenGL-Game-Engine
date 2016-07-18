package engine.Maths;

import engine.util.BufferUtils;

import java.nio.FloatBuffer;

/**
 * Created by Felix on 18.07.2016.
 */
public class Matrix4f {

    public static final int SIZE = 4 * 4;
    public float[] elements = new float[SIZE];

    public Matrix4f() {

    }

    public static Matrix4f identity() {
        Matrix4f result = new Matrix4f();
        for (int i = 0; i < SIZE; i++) {
            result.elements[i] = 0.0f;
        }
        result.elements[0 + 0 * 4] = 1.0f;
        result.elements[1 + 1 * 4] = 1.0f;
        result.elements[2 + 2 * 4] = 1.0f;
        result.elements[3 + 3 * 4] = 1.0f;

        return result;
    }

    public static Matrix4f orthographic(float left, float right, float bottom, float top, float near, float far) {
        Matrix4f result = identity();

        result.elements[0 + 0 * 4] = 2.0f / (right - left);

        result.elements[1 + 1 * 4] = 2.0f / (top - bottom);

        result.elements[2 + 2 * 4] = 2.0f / (near - far);

        result.elements[0 + 3 * 4] = (left + right) / (left - right);
        result.elements[1 + 3 * 4] = (bottom + top) / (bottom - top);
        result.elements[2 + 3 * 4] = (far + near) / (far - near);

        return result;
    }

    public static Matrix4f translate(Vector3f vector) {
        Matrix4f result = identity();
        result.elements[0 + 3 * 4] = vector.x;
        result.elements[1 + 3 * 4] = vector.y;
        result.elements[2 + 3 * 4] = vector.z;
        return result;
    }

    public static Matrix4f translate(Vector2f vector) {
        Matrix4f result = identity();
        result.elements[0 + 3 * 4] = vector.x;
        result.elements[1 + 3 * 4] = vector.y;
        return result;
    }

    public static Matrix4f rotate(float angle) {
        Matrix4f result = identity();
        float r = (float) Math.toRadians(angle);
        float cos = (float) Math.cos(r);
        float sin = (float) Math.sin(r);

        result.elements[0 + 0 * 4] = cos;
        result.elements[1 + 0 * 4] = sin;

        result.elements[0 + 1 * 4] = -sin;
        result.elements[1 + 1 * 4] = cos;

        return result;
    }

    public static Matrix4f rotate(float angle, float x, float y, float z) {
        Matrix4f result = identity();
        float r = (float) Math.toRadians(angle);
        float cos = (float) Math.cos(r);
        float sin = (float) Math.sin(r);
        float omc = 1.0f - cos;

        result.elements[0 + 0 * 4] = x * omc +  cos;
        result.elements[1 + 0 * 4] = y * x * omc + z * sin;
        result.elements[2 + 0 * 4] = x * z * omc - y * sin;

        result.elements[0 + 1 * 4] = x * y * omc - z * sin;
        result.elements[1 + 1 * 4] = y * omc + cos;
        result.elements[2 + 1 * 4] = y * z * omc + x * sin;

        result.elements[0 + 2 * 4] = x * z * omc + y * sin;
        result.elements[1 + 2 * 4] = y * z * omc - x * sin;
        result.elements[2 + 2 * 4] = z * omc + cos;

        return result;
    }

    public Matrix4f multiply(Matrix4f matrix) {
        Matrix4f result = new Matrix4f();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                float sum = 0.0f;
                for (int e = 0; e < 4; e++) {
                    sum += this.elements[x + e * 4] * matrix.elements[e + y * 4];
                }
                result.elements[x + y * 4] = sum;
            }
        }
        return result;
    }

    public static Matrix4f createTransformationMatrix(Vector2f translation, Vector2f scale) {
        Matrix4f matrix = Matrix4f.identity();
        matrix.translate(translation);
        matrix.scale(new Vector3f(scale.x, scale.y, 1f));
        return matrix;
    }

    public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry, float rz, float scale) {
        Matrix4f matrix = Matrix4f.identity();
        matrix.translate(translation);
        matrix.rotate((float) Math.toRadians(rx), 1, 0, 0);
        matrix.rotate((float) Math.toRadians(ry), 0, 1, 0);
        matrix.rotate((float) Math.toRadians(rz), 0, 0, 1);
        matrix.scale(new Vector3f(scale, scale, scale));

        return matrix;
    }

    public void scale(Vector3f vec) {
        elements[0 + 0 * 4] *= vec.x;
        elements[0 + 1 * 4] *= vec.x;
        elements[0 + 2 * 4] *= vec.x;
        elements[0 + 3 * 4] *= vec.x;
        elements[1 + 0 * 4] *= vec.y;
        elements[1 + 1 * 4] *= vec.y;
        elements[1 + 2 * 4] *= vec.y;
        elements[1 + 3 * 4] *= vec.y;
        elements[2 + 0 * 4] *= vec.z;
        elements[2 + 1 * 4] *= vec.z;
        elements[2 + 2 * 4] *= vec.z;
        elements[2 + 3 * 4] *= vec.z;
    }


    public FloatBuffer toFloatBuffer() {
        return BufferUtils.createFloatBuffer(elements);
    }

}
