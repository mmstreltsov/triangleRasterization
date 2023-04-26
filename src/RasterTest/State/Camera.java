package RasterTest.State;

import RasterTest.State.Animation.Rotation;
import RasterTest.State.Animation.Translation;
import RasterTest.State.Math.EulerAngles;
import RasterTest.State.Math.Vector3D;

public class Camera {
    private Translation offset;
    private Rotation rotation;
    private Vector3D direction;

    public Camera(Translation offset, Rotation rotation, Vector3D direction) {
        this.offset = new Translation(offset, true);
        this.rotation = rotation;
        this.direction = direction;
    }

    public Camera() {
        offset =  new Translation(new Vector3D(0, 0, -10), true);
        rotation = new Rotation(new EulerAngles(0., 0., 0.), true);
        direction = new Vector3D(0, 0, 1);
    }

    public Translation getOffset() {
        return offset;
    }

    public void setOffset(Translation offset) {
        this.offset = offset;
    }

    public Rotation getRotation() {
        return rotation;
    }

    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }

    public Vector3D getDirection() {
        return direction;
    }

    public void setDirection(Vector3D direction) {
        this.direction = direction;
    }
}
