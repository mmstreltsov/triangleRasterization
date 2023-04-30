package RasterTest.State;

import RasterTest.CameraAnimation.CameraAnimTest;
import RasterTest.State.Animation.ToTranslate;
import RasterTest.State.Math.*;
import RasterTest.Test;
import RasterTest.Triangle;

public class Main {

    private static final Object lock = new Object();

    public static void main(String[] args) {
        Test test = new Test();
        Matrix4x4 matrix4x4 = Camera.fabric().transformation();
        while(true) {

            RenderObject renderObject = test.makeAnimation();
            Triangle triangle = test.convert(renderObject).get(0);
            System.out.println(triangle);
            System.out.println(Camera.fabric().getOffset());
            System.out.println(renderObject.getTriangles().get(0).getVertex1().getzCoord());
            System.out.println(renderObject.getTriangles().get(0).getVertex2().getzCoord());
            System.out.println(renderObject.getTriangles().get(0).getVertex3().getzCoord());

            System.out.println();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
