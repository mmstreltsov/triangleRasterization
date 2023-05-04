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
            Matrix4x4 transform = scene.transformation();
            models.getTriangulation().forEach(it -> {
                Coord3D v1 = transform.multiplyOnHomo(it.getVertex1()).toPoint();
                Coord3D v2 = transform.multiplyOnHomo(it.getVertex2()).toPoint();
                Coord3D v3 = transform.multiplyOnHomo(it.getVertex3()).toPoint();

                Coord2D a = Scene.getting2DCoordinate(v1);
                Coord2D b = Scene.getting2DCoordinate(v2);
                Coord2D c = Scene.getting2DCoordinate(v3);

                Triangle2D tmp = new Triangle2D(a, b, c);
                tmp.setLightCoefficient(lightning(it));

                triangles.add(tmp);
            });
        } catch (RuntimeException e) {
            if (!e.getMessage().equals("Camera Translation")) {
                throw new RuntimeException(e);
            }
            init();
        }
    }



    // ВРЕМЕННОЕ РЕШЕНИЕ. Лучше переделать, так как второй применяется матрица для преобразования модели
    private double lightning(Triangle3D it) {
        Matrix4x4 modelTransformation = scene.getModelInstance().transformation();

        Coord3D v1 = modelTransformation.multiplyOnHomo(it.getVertex1()).toPoint();
        Coord3D v2 = modelTransformation.multiplyOnHomo(it.getVertex2()).toPoint();
        Coord3D v3 = modelTransformation.multiplyOnHomo(it.getVertex3()).toPoint();


        double lightCoefficient = Vector3D.cosAngleBetweenVectors(Light.fabric().getDirection(), Triangle3D.normal(v1, v2, v3));
        return lightCoefficient;
    }

    public Scene getScene() {
        return scene;
    }
}
