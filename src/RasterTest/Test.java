package RasterTest;

import RasterTest.State.Animation.ToRotate;
import RasterTest.State.Animation.ToTranslate;
import RasterTest.State.Camera;
import RasterTest.State.Math.Coord3D;
import RasterTest.State.Math.Triangle3D;
import RasterTest.State.Model;
import RasterTest.State.RenderObject;
import RasterTest.State.Scene;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public Scene scene;
    public Model model;

    public Test() {
        this.model = new Model();
        Coord3D x = new Coord3D(30., 0., 0.);
        Coord3D y = new Coord3D(-30., 0., 0.);
        Coord3D z = new Coord3D(0., 25., 0.);

        this.model.setTriangulation(new ArrayList<>(List.of(new Triangle3D(x, y, z))));


        this.scene = new Scene();
        ToTranslate.translate(scene.getModelInstance().getTranslation(), -0, 10, 35);
    }

    public List<Triangle> convert(RenderObject renderObject) {
        List<Triangle> triangles = new ArrayList<>();
        renderObject.getTriangles().forEach(it -> {
            Triangle triangle = Converter.convert(it);
            triangles.add(triangle);
        });
        return triangles;
    }



    public RenderObject makeAnimation() {
        ToRotate.rotate(this.scene.getModelInstance().getRotation(), 0, 0, 1);
        RenderObject renderObject = new RenderObject(this.scene, this.model);
        renderObject.init();
        return renderObject;
    }
}
