package RasterTest.State;

import RasterTest.State.Math.*;

import java.util.ArrayList;
import java.util.List;

public class RenderObject {


    private final Scene scene;
    private final Model models;

    public List<Triangle2D> getTriangles() {
        return triangles;
    }

    private List<Triangle2D> triangles = new ArrayList<>();

    public RenderObject(Scene scene, Model models) {
        this.scene = scene;
        this.models = models;
    }

    public void init() {
        try {
            triangles = new ArrayList<>();
            models.getTriangulation().forEach(it -> {
                Matrix4x4 transform = scene.transformation();
                Coord3D v1 = transform.multiplyOnHomo(it.getVertex1()).toPoint();
                Coord3D v2 = transform.multiplyOnHomo(it.getVertex2()).toPoint();
                Coord3D v3 = transform.multiplyOnHomo(it.getVertex3()).toPoint();

                Coord2D a = Scene.getting2DCoordinate(v1);
                Coord2D b = Scene.getting2DCoordinate(v2);
                Coord2D c = Scene.getting2DCoordinate(v3);

                Triangle2D tmp = new Triangle2D(a, b, c);
                tmp.setLightCoefficient(it.getLightCoefficient());

                triangles.add(tmp);
            });
        } catch (RuntimeException e) {
            if (!e.getMessage().equals("Camera Translation")) {
                throw new RuntimeException(e);
            }
            init();
        }
    }

    public Scene getScene() {
        return scene;
    }
}
