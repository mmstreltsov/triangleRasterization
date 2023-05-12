package ru.hse.project.RasterTest.State.Animation;

import ru.hse.project.RasterTest.State.Math.Angles;
import ru.hse.project.RasterTest.State.Math.Matrix4x4;

/**
 * Класс-обертка над Angles. Служит для вращения объектов в 3D
 */
public class Rotation {

    /**
     * Поле, хранящее Углы
     */
    private Angles rotation;


    /**
     * Конструктор от углов
     * @param x Относительно OX
     * @param y Относительно OY
     * @param z Относительно OZ
     */
    public Rotation(double x, double y, double z) {
        this.rotation = new Angles(x, y, z);
    }

    /**
     * Конструктор от объекта Angles
     */
    public Rotation(Angles rotation) {
        this.rotation = rotation;
    }

    /**
     * Дефолтный конструктор. Инициализация нулями
     */
    public Rotation() {
        this.rotation = new Angles(0., 0., 0.);
    }

    /**
     * Генерация матрицы поворота как комбинация трех вращений относительно OX, OY и OZ в заданном порядке.
     * @return матрица
     */
    public Matrix4x4 rotating() {
        Matrix4x4 matrix = new Matrix4x4(1.);
        matrix = matrix.multiplyOnMatrix(rotation.RAlpha());
        matrix = matrix.multiplyOnMatrix(rotation.RBeta());
        matrix = matrix.multiplyOnMatrix(rotation.RGamma());
        return matrix;
    }

    @Override
    public String toString() {
        return "Rotation{" +
                "rotation=" + rotation +
                '}';
    }

    public Angles getRotation() {
        return rotation;
    }

    public void setRotation(Angles rotation) {
        this.rotation = rotation;
    }
    public void setRotation(double x, double y, double z) {
        this.rotation = new Angles(x, y, z);
    }
}
