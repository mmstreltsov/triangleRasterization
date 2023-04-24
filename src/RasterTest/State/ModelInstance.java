package RasterTest.State;

import RasterTest.State.Math.EulerAngles;
import RasterTest.State.Math.Vector3D;

public class ModelInstance extends Model {

    private double scale;
    private EulerAngles rotation;
    private Vector3D translation;

    public ModelInstance(double scale, EulerAngles rotation, Vector3D translation) {
        super();
        this.scale = scale;
        this.rotation = rotation;
        this.translation = translation;
    }

    // Нужны методы для матриц преобразования
}
