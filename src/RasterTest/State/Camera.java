package RasterTest.State;

import RasterTest.State.Animation.Rotation;
import RasterTest.State.Animation.Translation;
import RasterTest.State.Math.BasisChange;
import RasterTest.State.Math.Matrix4x4;
import RasterTest.State.Math.Vector3D;



//GluLookAt
public class Camera implements Transformation {

    private static Camera camera;
    private Translation offset;


    private Rotation rotation;
    private Vector3D center;
    private Vector3D up;

    public static Camera fabric() {
        if (camera == null) {
            camera = new Camera();
        }
        return camera;
    }

    private Vector3D rotateCenter(){
        return rotation.rotating().multiplyOnHomo(this.center).toVector();
    }

    private Vector3D rotateUp() {
        return rotation.rotating().multiplyOnHomo(this.up).toVector();
    }

    private Camera() {
        this.offset = new Translation(new Vector3D(0, 0, -15), true);
        this.rotation = new Rotation(0, 0, 0);
        this.center = new Vector3D(0, 0, 1);
        this.up = new Vector3D(0, 1, 0);
    }

    private Vector3D makeCenterDefault() {
        Vector3D center = rotateCenter();
        return this.offset.getTransl().additional(center.normalized()).toVector();
    }

    private Vector3D makeUpDefault(){
        Vector3D up = rotateUp();
        return up.normalized();
    }


    /**
     * TODO: С поворотом до сих пор что-то не так (с поворотом относительно OY)
     */
    @Override
    public Matrix4x4 transformation() {
        Vector3D center = makeCenterDefault();
        Vector3D up = makeUpDefault();

        Vector3D eye = offset.getTransl();
        Matrix4x4 matrix;
        Vector3D z = center.subtracting(eye).toVector().normalized();
        if (Vector3D.crossProduct(z, up).magnitude() < 1e-7) {
            matrix = new Matrix4x4(1);
        }
        else {
            Vector3D x = Vector3D.crossProduct(up, z).normalized();
            Vector3D y = Vector3D.crossProduct(z, x).normalized();
            matrix = BasisChange.matrix(x, y, z);
        }

        matrix = matrix.multiplyOnMatrix(offset.translating());
        return matrix;
    }

    public Translation getOffset() {
        return offset;
    }

    public void setOffset(Translation offset) {
        this.offset = new Translation(offset, true);
    }

    public Rotation getRotation() {
        return rotation;
    }

    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }


}
