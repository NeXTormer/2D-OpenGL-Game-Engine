package engine.Maths;

/**
 * Created by Felix on 18.07.2016.
 */

public class Vector2f {
    
    float x, y;
    
    public Vector2f(float x_, float y_) {
        x = x_;
        y = y_;
    }
    
    public Vector2f() {
        x = 0f;
        y = 0f;
    }

    public Vector2f normalise(Vector2f dest) {
        float l = this.length();
        if (dest == null) {
            dest = new Vector2f(this.x / l, this.y / l);
        } else {
            dest.set(this.x / l, this.y / l);
        }

        return dest;
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public static float dot(Vector2f left, Vector2f right) {
        return left.x * right.x + left.y * right.y;
    }

    public static float angle(Vector2f a, Vector2f b) {
        float dls = dot(a, b) / (a.length() * b.length());
        if (dls < -1.0F) {
            dls = -1.0F;
        } else if (dls > 1.0F) {
            dls = 1.0F;
        }

        return (float) Math.acos((double) dls);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append("Vector2f[");
        sb.append(this.x);
        sb.append(", ");
        sb.append(this.y);
        sb.append(']');
        return sb.toString();
    }

    public final float length() {
        return (float) Math.sqrt((double) this.lengthSquared());
    }

    public float lengthSquared() {
        return this.x * this.x + this.y * this.y;
    }
}
