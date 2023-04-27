package RasterTest.State;

import RasterTest.State.Math.*;

import java.util.ArrayList;
import java.util.List;

public class RenderScene {
    private Scene scene;
    private Model models;

    List<Triangle2D> triangles;
    public RenderScene(Scene scene, Model models) {
        this.scene = scene;
        this.models = models;
        triangles = new ArrayList<>();
    }

    public void init() {
        models.getTriangulation().forEach(it -> {
            Matrix4x4 transform = scene.transformation();
            HomogeneousCoord v1 = transform.multiplyOnHomo(it.getVertex1());
            HomogeneousCoord v2 = transform.multiplyOnHomo(it.getVertex2());
            HomogeneousCoord v3 = transform.multiplyOnHomo(it.getVertex3());

            Coord2D a = new Coord2D(v1.getX(), v1.getY(), v1.getZ());
            Coord2D b = new Coord2D(v2.getX(), v2.getY(), v2.getZ());
            Coord2D c = new Coord2D(v3.getX(), v3.getY(), v3.getZ());

            triangles.add(new Triangle2D(a, b, c));
        });
    }
}
