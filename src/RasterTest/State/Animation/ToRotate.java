package RasterTest.State.Animation;

public class ToRotate {
    public static void rotate(Rotation rotation, double alpha, double beta, double gamma) {
        rotation.getRotation().setAlpha(rotation.getRotation().getAlpha() + alpha * Math.PI / 180);
        rotation.getRotation().setBeta(rotation.getRotation().getBeta() + beta * Math.PI / 180);
        rotation.getRotation().setGamma(rotation.getRotation().getGamma() + gamma * Math.PI / 180);
    }
}
