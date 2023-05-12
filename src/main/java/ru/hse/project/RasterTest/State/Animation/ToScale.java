package ru.hse.project.RasterTest.State.Animation;

/**
 * Класс с одним статическим методом для in-place растяжения в 3D
 */
public class ToScale {

    /**
     * Изменяет заданный класс на заданные далее величины.
     * @param scale Класс Растяжения
     * @param x лямбда для x
     * @param y лямбда для y
     * @param z лямбда для z
     */
    public static void scale(Scale scale, double x, double y, double z) {
        scale.getScale().setX(scale.getScale().getX() * x);
        scale.getScale().setY(scale.getScale().getY() * y);
        scale.getScale().setZ(scale.getScale().getZ() * z);
    }
}
