package RasterTest.State;

import RasterTest.State.Math.*;

public class Main {

    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
//        Test test = new Test();
//        while(true) {
//            RenderObject renderObject = test.makeAnimation();
//            Triangle triangle = test.convert(renderObject).get(0);
//            System.out.println(triangle);
//            Thread.sleep(200);
//        }

        Coord3D v1 = new Coord3D(12, 32, -13);
        Coord3D v2 = new Coord3D(3, -134, 12);
        Coord3D v3 = new Coord3D(-12, 123, 2314);

        Triangle3D triangle3D = new Triangle3D(v1, v2, v3);

        System.out.println(triangle3D.normal());
    }
}
