package RasterTest.State;

import RasterTest.State.Math.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Main {

    private static final Object lock = new Object();
    public static void main(String[] args) {
        Model model = new Model();
        Coord3D x = new Coord3D(1., 0., 0.);
        Coord3D y = new Coord3D(0., 1., 0.);
        Coord3D z = new Coord3D(0., 0., 1.);

        model.setTriangulation(new ArrayList<>(List.of(new Triangle3D(x, y, z))));
        Vector3D scale = new Vector3D(1, 1, 1);
        EulerAngles eulerAngles = new EulerAngles(0, 120, 0);
        Vector3D translation = new Vector3D(5, 5, 5);

        ModelInstance instance = new ModelInstance(model, scale, eulerAngles, translation);

        while(true) {
            instance.getRotation().setBeta(instance.getRotation().getBeta() + 1);
            instance.getRotation().setAlpha(instance.getRotation().getAlpha() + 5);
            instance.getRotation().setGamma(instance.getRotation().getGamma() - 10);

            Matrix4x4 transformation = instance.transformation();

            instance.getTriangulation().stream().map(it -> it.transformation(transformation)).forEach(System.out::println);
        }
    }
}
