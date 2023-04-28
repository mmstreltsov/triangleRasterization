package RasterTest.State;

import RasterTest.Render;
import RasterTest.State.Animation.ToRotate;
import RasterTest.State.Animation.Rotation;
import RasterTest.State.Animation.Scale;
import RasterTest.State.Animation.Translation;
import RasterTest.State.Math.*;
import RasterTest.Test;
import RasterTest.Triangle;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Test test = new Test();
        while(true) {
            RenderObject renderObject = test.makeAnimation();
            Triangle triangle = test.convert(renderObject).get(0);
            System.out.println(triangle);
            Thread.sleep(200);
        }

    }
}
