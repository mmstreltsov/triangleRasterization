package RasterTest.State;

import RasterTest.State.Math.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, объединяющий модель и правила ее преобразования в поле зрения камеры
 */
public class RenderObject {
    /**
     * Поле, хранящее сцену объекта
     */
    private final Scene scene;
    /**
     * Поле, хранящее объект
     */
    private final Model models;

    /**
     * Поле, хранящее коллекцию сгенерированных 2D треугольников данной модели в данной сцене
     */
    private List<Triangle2D> triangles = new ArrayList<>();
    public List<Triangle2D> getTriangles() {
        return triangles;
    }

    /**
     * Конструктор от двух параметров
     * @param scene сцена фигуры
     * @param models фигура
     */
    public RenderObject(Scene scene, Model models) {
        this.scene = scene;
        this.models = models;
    }

    /**
     * Метод для инициализации поля triangles.
     */
    public void init() {
        triangles = new ArrayList<>();
        Matrix4x4 transform = scene.transformation();
        models.getTriangulation().forEach(it -> {

            Triangle3D transformTriangle = it.transformation(transform);

            Coord2D a = Scene.getting2DCoordinate(transformTriangle.getVertex1());
            Coord2D b = Scene.getting2DCoordinate(transformTriangle.getVertex2());
            Coord2D c = Scene.getting2DCoordinate(transformTriangle.getVertex3());

            Triangle2D tmp = new Triangle2D(a, b, c);
            tmp.setLightCoefficient(lightning(it));

            triangles.add(tmp);
        });
    }


    // Минусы решения: второй считается матрица для преобразования модели
    /**
     * Метод, считающий и выставляющий коэффициент света для заданного 3D треугольника.
     * Договоренность: коэффициент света = abs(0.6 * cosA + 0.4), модуль от формулы.
     * Стоит модуль, так как треугольник двусторонний (отрицательный косинус показывает обратную сторону, но для нас это одно и то же)
     * Формула подобрана так, чтобы переходы были не такими резкими
     * @param it 3D треугольник
     * @return коэффициент света
     */
    private double lightning(Triangle3D it) {
        Matrix4x4 modelTransformation = scene.getModelInstance().transformation();

        Coord3D v1 = modelTransformation.multiplyOnHomo(it.getVertex1()).toPoint();
        Coord3D v2 = modelTransformation.multiplyOnHomo(it.getVertex2()).toPoint();
        Coord3D v3 = modelTransformation.multiplyOnHomo(it.getVertex3()).toPoint();


        // Договоримся, тут модуль косинуса
        double lightCoefficient = Math.abs(Vector3D.cosAngleBetweenVectors(Light.fabric().getDirection(), Triangle3D.normal(v1, v2, v3)));
        lightCoefficient = (lightCoefficient * 0.6 + 0.4);
        return lightCoefficient;
    }

    public Scene getScene() {
        return scene;
    }
}
