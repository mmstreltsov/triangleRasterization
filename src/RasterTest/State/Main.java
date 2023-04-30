package RasterTest.State;

import RasterTest.CameraAnimation.CameraAnimTest;
import RasterTest.State.Animation.ToTranslate;
import RasterTest.State.Math.*;
import RasterTest.Test;
import RasterTest.Triangle;

public class Main {

    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Test test = new Test();
        Matrix4x4 matrix4x4 = Camera.fabric().transformation();
        while(true) {

            RenderObject renderObject = test.makeAnimation();
            Triangle triangle = test.convert(renderObject).get(0);
            System.out.println(triangle);
            Thread.sleep(200);
        }

    }
}
