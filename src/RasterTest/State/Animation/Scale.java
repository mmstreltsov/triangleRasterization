package RasterTest.State.Animation;

import RasterTest.State.Math.Matrix4x4;
import RasterTest.State.Math.Vector3D;

/**
 * Класс-обертка над Vector3D. Служит для растяжения объектов в 3D
 */
public class Scale {
    /**
     * Поле, хранящее Вектор
     */
    private Vector3D scale;

    /**
     * Конструктор от элементов
     * @param x X-координата
     * @param y Y-координата
     * @param z Z-координата
     */
    public Scale(double x, double y, double z) {
        this.scale = new Vector3D(x, y, z);
    }

    /**
     * Конструктор от класса Вектор
     * @param scale Вектор
     */
    public Scale(Vector3D scale) {
        this.scale = scale;
    }

    /**
     * Дефолтный конструктор, инициализация (1, 1, 1)
     */
    public Scale() {
        scale = new Vector3D(1, 1, 1);
    }


    /**
     * Генерация матрицы растяжения.
     * @return матрица
     */
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
