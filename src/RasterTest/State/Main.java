package RasterTest.State;

import RasterTest.State.Animation.ToRotate;
import RasterTest.State.Animation.Rotation;
import RasterTest.State.Animation.Scale;
import RasterTest.State.Animation.Translation;
import RasterTest.State.Math.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final Object lock = new Object();

//    public static void main(String[] args) {
//        Model model = new Model();
//        Coord3D x = new Coord3D(1., 0., 0.);
//        Coord3D y = new Coord3D(0., 1., 0.);
//        Coord3D z = new Coord3D(0., 0., 1.);
//
//        model.setTriangulation(new ArrayList<>(List.of(new Triangle3D(x, y, z))));
//
//
//        Scale scale = new Scale();
//        Rotation rotation = new Rotation();
//        Translation translation = new Translation();
//
//        ModelInstance instance = new ModelInstance(model, scale, rotation, translation);
//
//        while (true) {
//            ToRotate.rotate(instance.getRotation(), 0, 1, 0);
//            Matrix4x4 transformation = instance.transformation();
//
//            instance.getTriangulation().stream().map(it -> it.transformation(transformation)).forEach(System.out::println);
//        }
//    }
}
