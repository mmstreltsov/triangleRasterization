package RasterTest.State.Animation;

public class ToTranslate {
    public static void translate(Translation translation, double x, double y, double z) {
        translation.getTranslation().setX(translation.getTranslation().getX() + x);
        translation.getTranslation().setY(translation.getTranslation().getY() + y);
        translation.getTranslation().setZ(translation.getTranslation().getZ() + z);
    }
}
