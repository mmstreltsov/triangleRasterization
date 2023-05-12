package ru.hse.project.RasterTest.State.Animation;

/**
 * Класс с одним статическим методом для in-place перемещения в 3D
 */
public class ToTranslate {
    /**
     * Изменяет заданный класс на заданные далее величины.
     * @param translation Класс Перемещения
     * @param x дельта для x
     * @param y дельта для y
     * @param z дельта для z
     */
    public static void translate(Translation translation, double x, double y, double z) {
        translation.getTransl().setX(translation.getTransl().getX() + x);
        translation.getTransl().setY(translation.getTransl().getY() + y);
        translation.getTransl().setZ(translation.getTransl().getZ() + z);
    }
}
