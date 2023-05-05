package RasterTest;

public class Animation {
    public static void setIsAnimateTrue() {
        Animation.isAnimate = true;
    }
    public static void setIsAnimateFalse() {
        Animation.isAnimate = false;
    }

    public static boolean isIsAnimate() {
        return isAnimate;
    }
    private static boolean isAnimate = false;
}
