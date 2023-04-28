package RasterTest.State;

import RasterTest.State.Math.*;

import java.util.ArrayList;
import java.util.List;

public class RenderObject {
    private Scene scene;
    private Model models;

    public List<Triangle2D> getTriangles() {
        return triangles;
    }

    private List<Triangle2D> triangles;
    public RenderObject(Scene scene, Model models) {
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

            Coord2D a = Scene.getting2DCoordinate(v1);
            Coord2D b = Scene.getting2DCoordinate(v2);
            Coord2D c = Scene.getting2DCoordinate(v3);

            Triangle2D tmp = new Triangle2D(a, b, c);
            tmp.setLightCoefficient(it.getLightCoefficient());

            triangles.add(tmp);
        });
    }
}
