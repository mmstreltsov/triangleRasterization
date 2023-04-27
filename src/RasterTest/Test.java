package RasterTest;

import RasterTest.State.Animation.ToRotate;
import RasterTest.State.Animation.ToTranslate;
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

    private static final int DEFAULT_WIDTH = 640;
    private static final int DEFAULT_HEIGHT = 480;

    Test() {
        this.model = new Model();
        Coord3D x = new Coord3D(100., 0., 0.);
        Coord3D y = new Coord3D(-100., 0., 0.);
        Coord3D z = new Coord3D(0., 100., 0.);

        this.model.setTriangulation(new ArrayList<>(List.of(new Triangle3D(x, y, z))));


        this.scene = new Scene();
        ToTranslate.translate(scene.getModelInstance().getTranslation(), 0, 0, 0);

    }

    public List<Triangle> convert(RenderObject renderObject) {
        List<Triangle> triangles = new ArrayList<>();
        renderObject.getTriangles().forEach(it -> {
            Triangle triangle = new Triangle((int) it.getVertex1().getX() + DEFAULT_WIDTH / 2, (int) it.getVertex1().getY() + DEFAULT_HEIGHT / 2,
                    (int) it.getVertex2().getX() + DEFAULT_WIDTH / 2, (int) it.getVertex2().getY() + DEFAULT_HEIGHT / 2,
                    (int) it.getVertex3().getX() + DEFAULT_WIDTH / 2, (int) it.getVertex3().getY() + DEFAULT_HEIGHT / 2);
            triangles.add(triangle);
        });
        return triangles;
    }

        private double[] off = new double[] {1, 2, 3, 4, 3, 2, 1, 0, -1, -2, -3, -4, -3, -2, -1, 0};
    private int i = 0;
    public RenderObject makeAnimation() {
        ToRotate.rotate(this.scene.getModelInstance().getRotation(), 0, 0, 0.01);
        ToTranslate.translate(this.scene.getModelInstance().getTranslation(), 0, 0, off[i++ % off.length]);
        RenderObject renderObject = new RenderObject(this.scene, this.model);
        renderObject.init();
        return renderObject;
    }
}
