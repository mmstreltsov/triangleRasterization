package RasterTest.State.Animation;

public class ToRotate {
    public static void rotate(Rotation rotation, double alpha, double beta, double gamma) {
        rotation.getRotation().setAlpha(rotation.getRotation().getAlpha() + alpha);
        rotation.getRotation().setBeta(rotation.getRotation().getBeta() + beta);
        rotation.getRotation().setGamma(rotation.getRotation().getGamma() + gamma);
    }
}
