package ru.hse.project.RasterTest.State;

import ru.hse.project.RasterTest.State.Math.*;
import ru.hse.project.RasterTest.WritableRasterTest;

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
     * Анимация происходит здесь: для треугольников применяются параметры с текущего кадра.
     * Также применяется клиппинг (в зависимости от флага 2D клиппинга)
     */
    public void init() {
        triangles = new ArrayList<>();

        scene.makeAnimation();
        List<Triangle3D> afterFirstTransformation = makeTransform(models.getTriangulation(), scene.transformationStep1());

        ///APPEND 2D CLIPPING
        List<Triangle3D> afterClipping;
        if (WritableRasterTest.isIs2DClipping()) {
            afterClipping = afterFirstTransformation;   /// Nothing to do
        }
        else {
            afterClipping = clipping(afterFirstTransformation);
        }
        ///

        setLightning(afterClipping);
        List<Triangle3D> afterSecondTransform = makeTransform(afterClipping, scene.transformationStep2());

        afterSecondTransform.forEach(it -> {

            Coord2D a = Scene.getting2DCoordinate(it.getVertex1());
            Coord2D b = Scene.getting2DCoordinate(it.getVertex2());
            Coord2D c = Scene.getting2DCoordinate(it.getVertex3());

            Triangle2D tmp = new Triangle2D(a, b, c);
            tmp.setLightCoefficient(it.getLightCoefficient());
            triangles.add(tmp);
        });
    }

    /**
     * Применение матрицы трансформации к заданной коллекции
     * @param list Коллекция треугольников в 3D
     * @param transform Матрица преобразования
     * @return Новая коллекция
     */
    private List<Triangle3D> makeTransform(List<Triangle3D> list,  Matrix4x4 transform) {
        return list.stream().map(it -> it.transformation(transform)).toList();
    }

    /**
     * Применение клиппинга в данный кадр для заданной коллекции треугольников
     * @param list Коллекция треугольников в 3D
     * @return обработанная коллекция треугольников
     */
    private List<Triangle3D> clipping(List<Triangle3D> list) {
        Clipping clipping = new Clipping(list);
        clipping.init();
        return clipping.getListAfterClipping();
    }


    /**
     * Метод, считающий и выставляющий коэффициент света для заданного 3D треугольника.
     * Договоренность: коэффициент света = abs(0.6 * cosA + 0.4), модуль от формулы.
     * Стоит модуль, так как треугольник двусторонний (отрицательный косинус показывает обратную сторону, но для нас это одно и то же)
     * Формула подобрана так, чтобы переходы были не такими резкими
     * @param it 3D треугольник
     * @return коэффициент света
     */
    private double lightning(Triangle3D it) {

        Coord3D v1 = it.getVertex1().toPoint();
        Coord3D v2 = it.getVertex2().toPoint();
        Coord3D v3 = it.getVertex3().toPoint();

        // Договоримся, тут модуль косинуса
        double lightCoefficient = Math.abs(Vector3D.cosAngleBetweenVectors(Light.fabric().getDirection(), Triangle3D.normal(v1, v2, v3)));
        lightCoefficient = (lightCoefficient * 0.6 + 0.4);
        return lightCoefficient;
    }

    private void setLightning(List<Triangle3D> list) {
        list.forEach(it -> it.setLightCoefficient(lightning(it)));
    }

    public Scene getScene() {
        return scene;
    }
}
