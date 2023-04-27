package RasterTest;

import RasterTest.State.Animation.ToRotate;
import RasterTest.State.Animation.Translation;
import RasterTest.State.Camera;
import RasterTest.State.Math.Coord3D;
import RasterTest.State.Math.Triangle3D;
import RasterTest.State.Math.Vector3D;
import RasterTest.State.Model;
import RasterTest.State.RenderObject;
import RasterTest.State.Scene;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public Scene scene;
    public Model model;

    private static final int DEFAULT_WIDTH = 640;
    private static final int DEFAULT_HEIGHT = 480;

    Test() {
        this.model = new Model();
        Coord3D x = new Coord3D(10., 0., 0.);
        Coord3D y = new Coord3D(-10., 0., 0.);
        Coord3D z = new Coord3D(0., 10., 0.);

        this.model.setTriangulation(new ArrayList<>(List.of(new Triangle3D(x, y, z))));



        this.scene = new Scene();
        this.scene.getModelInstance().getTranslation().setTranslation(new Vector3D(0, 0, 20));
        Camera camera = Camera.fabric();
        camera.setOffset(new Translation(new Vector3D(5., 0,  0)));

    }

    public List<Triangle> convert(RenderObject renderObject) {
        List<Triangle> triangles = new ArrayList<>();
        renderObject.getTriangles().forEach(it -> {
            Triangle triangle = new Triangle((int)it.getVertex1().getX() + DEFAULT_WIDTH/2, (int)it.getVertex1().getY() + DEFAULT_HEIGHT / 2,
                    (int)it.getVertex2().getX() + DEFAULT_WIDTH / 2, (int)it.getVertex2().getY() + DEFAULT_HEIGHT / 2,
                    (int)it.getVertex3().getX() + DEFAULT_WIDTH / 2, (int) it.getVertex3().getY() + DEFAULT_HEIGHT / 2);
            triangles.add(triangle);
        });
        return triangles;
    }

    public RenderObject makeAnimation() {
        ToRotate.rotate(this.scene.getModelInstance().getRotation(), 0.01, 0.01, 0.01);
        RenderObject renderObject = new RenderObject(this.scene, this.model);
        renderObject.init();
        return renderObject;
    }
}
