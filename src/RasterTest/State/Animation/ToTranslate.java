package RasterTest.State.Animation;

public class ToTranslate {
    public static void translate(Translation translation, double x, double y, double z) {
        translation.getTransl().setX(translation.getTransl().getX() + x);
        translation.getTransl().setY(translation.getTransl().getY() + y);
        translation.getTransl().setZ(translation.getTransl().getZ() + z);
    }
}
