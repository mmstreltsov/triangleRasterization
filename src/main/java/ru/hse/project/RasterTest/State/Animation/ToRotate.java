package ru.hse.project.RasterTest.State.Animation;

/**
 * Класс с одним статическим методом для in-place вращения в 3D
 */
public class ToRotate {
    /**
     * Изменяет заданный класс на заданные далее величины.
     * @param rotation Класс вращения
     * @param alpha Относительно OX
     * @param beta Относительно OY
     * @param gamma Относительно OZ
     */
    public static void rotate(Rotation rotation, double alpha, double beta, double gamma) {
        rotation.getRotation().setAlpha(rotation.getRotation().getAlpha() + alpha * Math.PI / 180);
        rotation.getRotation().setBeta(rotation.getRotation().getBeta() + beta * Math.PI / 180);
        rotation.getRotation().setGamma(rotation.getRotation().getGamma() + gamma * Math.PI / 180);
    }
}
