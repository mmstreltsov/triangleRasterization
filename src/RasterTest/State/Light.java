package RasterTest.State;

import RasterTest.State.Math.Vector3D;

public class Light {

    private static Light light;
    private Vector3D direction;

    public static Light fabric() {
        if (light == null) {
            light = new Light();
        }
        return light;
    }

    public static Light fabric(Vector3D direction) {
        if (light == null) {
            light = new Light(direction);
        }
        else {
            light.direction = direction;
        }
        return light;
    }

    private Light() {
        direction = new Vector3D(0, 0, 1);
    }

    private Light(Vector3D vec) {
        this.direction = vec;
    }
}
