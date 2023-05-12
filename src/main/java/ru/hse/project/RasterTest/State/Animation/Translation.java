package ru.hse.project.RasterTest.State.Animation;

import ru.hse.project.RasterTest.State.Math.Matrix4x4;
import ru.hse.project.RasterTest.State.Math.Vector3D;

/**
 * Класс-обертка над Vector3D c учетом Reversed операций. Служит для перемещения объектов в 3D
 */
public class Translation {
    /**
     * Поле, хранящее Вектор
     */
    private Vector3D transl;
    /**
     * Поле, хранящее флаг, если объект требует обратного перемещения (Для камеры, например)
     */
    private final boolean isReversed;

    /**
     * Конструктор от параметров. Флаг Reversed устанавливается как false
     * @param x X-координата
     * @param y Y-координата
     * @param z Z-координата
     */
    public Translation(double x, double y, double z) {
        this.transl = new Vector3D(x, y, z);
        this.isReversed = false;
    }

    /**
     * Конструктор от вектора. Флаг Reversed устанавливается как false
     * @param transl Вектор
     */
    public Translation(Vector3D transl) {
        this.transl = transl;
        this.isReversed = false;
    }


    /**
     * Конструктор от вектора и от заданного флага Reversed
     * @param transl Вектор
     * @param isReversed Установить флаг, если требуется обратное перемещения (Для камеры, например)
     */
    public Translation(Vector3D transl, boolean isReversed) {
        this.isReversed = isReversed;
        this.transl = transl;
    }

    /**
     * Дефолтный конструктор, инициализация нулями. Флаг -- false
     */
    public Translation() {
        this.transl = new Vector3D(0, 0, 0);
        this.isReversed = false;
    }
    /**
     * Генерация матрицы перемещения с учетом флага Reversed (тогда выполнится обратное перемещение)
     * @return матрица
     */
    public Matrix4x4 translating() {
        Matrix4x4 matrix = new Matrix4x4();
        matrix.setM11(1.);
        matrix.setM22(1.);
        matrix.setM33(1.);
        matrix.setM44(1.);

        matrix.setM14(transl.getX());
        matrix.setM24(transl.getY());
        matrix.setM34(transl.getZ());

        if (isReversed) {
            matrix.setM14(matrix.getM14() * -1);
            matrix.setM24(matrix.getM24() * -1);
            matrix.setM34(matrix.getM34() * -1);
        }
        return matrix;
    }

    @Override
    public String toString() {
        return "Translation{" +
                "transl=" + transl;
    }

    public Vector3D getTransl() {
        return transl;
    }

    public void setTransl(Vector3D transl) {
        this.transl = transl;
    }
    public void setTransl(double x, double y, double z) {
        this.transl = new Vector3D(x, y, z);
    }
}
