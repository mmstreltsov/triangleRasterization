package RasterTest.State.Animation;

public class ToScale {
    public static void scale(Scale scale, double x, double y, double z) {
        scale.getScale().setX(scale.getScale().getX() + x);
        scale.getScale().setY(scale.getScale().getY() + y);
        scale.getScale().setZ(scale.getScale().getZ() + z);
    }
}
