package RasterTest.State;

import RasterTest.Render;
import RasterTest.State.Animation.ToRotate;
import RasterTest.State.Animation.Rotation;
import RasterTest.State.Animation.Scale;
import RasterTest.State.Animation.Translation;
import RasterTest.State.Math.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final Object lock = new Object();

    public static void main(String[] args) {
        Model model = new Model();
        Coord3D x = new Coord3D(1., 0., 0.);
        Coord3D y = new Coord3D(0., 1., 0.);
        Coord3D z = new Coord3D(0., 0., 1.);

        model.setTriangulation(new ArrayList<>(List.of(new Triangle3D(x, y, z))));

        Scene scene = new Scene();
        scene.getModelInstance().getTranslation().setTranslation(new Vector3D(1, 5, 12));

        while (true) {
            ToRotate.rotate(scene.getModelInstance().getRotation(), 1, 1, 1);
            RenderObject renderObject = new RenderObject(scene, model);
            renderObject.init();
            renderObject.getTriangles().forEach(System.out::println);
        }
    }
}
