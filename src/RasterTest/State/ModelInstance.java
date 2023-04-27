package RasterTest.State;

import RasterTest.State.Animation.Rotation;
import RasterTest.State.Animation.Scale;
import RasterTest.State.Animation.Translation;
import RasterTest.State.Math.Matrix4x4;

public class ModelInstance implements Transformation {
    private Scale scale;
    private Rotation rotation;
    private Translation translation;



    public ModelInstance(Scale scale, Rotation rotation, Translation translation) {
        this.scale = scale;
        this.rotation = rotation;
        this.translation = translation;
    }

    public ModelInstance() {
        this.scale = new Scale();
        this.rotation = new Rotation();
        this.translation = new Translation();
    }

    @Override
    public Matrix4x4 transformation() {
        Matrix4x4 matrix = new Matrix4x4(1.);
        matrix = matrix.multiplyOnMatrix(translation.translating());
        matrix = matrix.multiplyOnMatrix(rotation.rotating());
        matrix = matrix.multiplyOnMatrix(scale.scaling());
        return matrix;
    }

    public Scale getScale() {
        return scale;
    }

    public void setScale(Scale scale) {
        this.scale = scale;
    }

    public Rotation getRotation() {
        return rotation;
    }

    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }

    public Translation getTranslation() {
        return translation;
    }

    public void setTranslation(Translation translation) {
        this.translation = translation;
    }
}
