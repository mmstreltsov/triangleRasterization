package RasterTest.State.Animation;

import RasterTest.State.Math.Matrix4x4;
import RasterTest.State.Math.Vector3D;

public class Scale {


    private Vector3D scale;


    public Scale(double x, double y, double z) {
        this.scale = new Vector3D(x, y, z);
    }
    public Scale(Vector3D scale) {
        this.scale = scale;
    }

    // default = (1, -1, 1) because of implementation (canvas 0,0 at up-left. ViewPoint (0, 0) at left-down)
    public Scale() {
        scale = new Vector3D(1, -1, 1);
    }

    public Matrix4x4 scaling() {
        Matrix4x4 matrix = new Matrix4x4();
        matrix.setM11(scale.getX());
        matrix.setM22(scale.getY());
        matrix.setM33(scale.getZ());

        matrix.setM44(1.);

        return matrix;
    }

    public Vector3D getScale() {
        return scale;
    }

    public void setScale(Vector3D scale) {
        this.scale = scale;
    }
    public void setScale(double x, double y, double z) {
        this.scale = new Vector3D(x, y, z);
    }

}
