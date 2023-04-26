package RasterTest.State;

import RasterTest.State.Math.EulerAngles;
import RasterTest.State.Math.Matrix4x4;
import RasterTest.State.Math.Vector3D;

public class ModelInstance extends Model {



    private Vector3D scale;
    private EulerAngles rotation;
    private Vector3D translation;

    public ModelInstance(Vector3D scale, EulerAngles rotation, Vector3D translation) {
        super();
        this.scale = scale;
        this.rotation = rotation;
        this.translation = translation;
    }

    public ModelInstance(Model model, Vector3D scale, EulerAngles rotation, Vector3D translation) {
        super(model);
        this.scale = scale;
        this.rotation = rotation;
        this.translation = translation;
    }

    public Matrix4x4 transformation() {
        Matrix4x4 matrix = new Matrix4x4(1.);
        matrix = matrix.multiplyOnMatrix(translating());
        matrix = matrix.multiplyOnMatrix(rotating());
        matrix = matrix.multiplyOnMatrix(scaling());
        return matrix;
    }
    private Matrix4x4 scaling() {
        Matrix4x4 matrix = new Matrix4x4();
        matrix.setM11(scale.getX());
        matrix.setM22(scale.getY());
        matrix.setM33(scale.getZ());

        matrix.setM44(1.);

        return matrix;
    }

    private Matrix4x4 rotating() {
        Matrix4x4 matrix = new Matrix4x4(rotation.RAlpha());
        matrix = matrix.multiplyOnMatrix(rotation.RBeta());
        matrix = matrix.multiplyOnMatrix(rotation.RGamma());
        return matrix;
    }

    private Matrix4x4 translating() {
        Matrix4x4 matrix = new Matrix4x4();
        matrix.setM11(1.);
        matrix.setM22(1.);
        matrix.setM33(1.);
        matrix.setM44(1.);

        matrix.setM14(translation.getX());
        matrix.setM24(translation.getY());
        matrix.setM34(translation.getZ());
        return matrix;
    }

    public Vector3D getScale() {
        return scale;
    }

    public void setScale(Vector3D scale) {
        this.scale = scale;
    }

    public EulerAngles getRotation() {
        return rotation;
    }

    public void setRotation(EulerAngles rotation) {
        this.rotation = rotation;
    }

    public Vector3D getTranslation() {
        return translation;
    }

    public void setTranslation(Vector3D translation) {
        this.translation = translation;
    }



}
