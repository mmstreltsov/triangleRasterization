package RasterTest.State;

import RasterTest.Initialization;
import RasterTest.Render;
import RasterTest.Animate;
import RasterTest.State.Math.EulerAngles;
import RasterTest.Triangle;

import java.util.List;

public class Main {

    private static final Object lock = new Object();

    public static void main(String[] args) {
        Render render = new Initialization().getRender();
        RenderObject renderObject = render.getRenderState().getRenderObjects().get(0);

        while(true) {
            Animate.animObjectRotate(renderObject, new EulerAngles(0, 2, 1));
            List<Triangle> triangleRendered = render.render();
            Triangle triangle = triangleRendered.get(0);

            System.out.println(triangle);

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
