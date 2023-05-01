package RasterTest.CameraAnimation;

import RasterTest.State.Animation.Animate;

public class CameraAnimTest {
    public static void testing(char c) {
        switch (c) {
            case 'w' -> Animate.animCameraTransl(0, 0, 1);
            case 'a' -> Animate.animCameraTransl(-1, 0, 0);
            case 's' -> Animate.animCameraTransl(0, 0, -1);
            case 'd' -> Animate.animCameraTransl(1, 0, 0);
            case ',' -> Animate.animCameraRotate(-1, 0, 0);
            case 'l' -> Animate.animCameraRotate(1, 0, 0);
            case '.' -> Animate.animCameraRotate(0, -1, 0);
            case ';' -> Animate.animCameraRotate(0, 1, 0);
            case '/' -> Animate.animCameraRotate(0, 0, -1);
            case '\'' -> Animate.animCameraRotate(0, 0, 1);
            default -> {
            }
        }
    }
}
