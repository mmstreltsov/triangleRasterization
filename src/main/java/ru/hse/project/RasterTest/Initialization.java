package ru.hse.project.RasterTest;

import ru.hse.project.RasterTest.State.Animation.Animate;
import ru.hse.project.RasterTest.State.Animation.Rotation;
import ru.hse.project.RasterTest.State.Math.Coord3D;
import ru.hse.project.RasterTest.State.Math.Triangle3D;
import ru.hse.project.RasterTest.State.Math.Vector3D;
import ru.hse.project.RasterTest.State.Model;
import ru.hse.project.RasterTest.State.RenderObject;
import ru.hse.project.RasterTest.State.Scene;

/**
 * Класс-инициализатор. Создает модель и сцену по авторским настройкам
 */
public class Initialization {
    /**
     * Рендер всех объектов
     */
    private final Render render = new Render();

    /**
     * Вся инициализация происходит в конструкторе
     */
    public Initialization() {
        Model model = new Model();

//        Coord3D v1 = new Coord3D(50., 0., 0.);
//        Coord3D v2 = new Coord3D(-30., 0., 0.);
//        Coord3D v3 = new Coord3D(0., 25., 0.);
//        model.getTriangulation().add(new Triangle3D(v1, v2, v3));


        // CUBE
        model.getTriangulation().add(new Triangle3D(
           new Coord3D(1, 1, 1), new Coord3D(1, 1, -1), new Coord3D(-1, 1, 1)
        ));
        model.getTriangulation().add(new Triangle3D(
                new Coord3D(-1, 1, -1), new Coord3D(1, 1, -1), new Coord3D(-1, 1, 1)
        ));

        model.getTriangulation().add(new Triangle3D(
                new Coord3D(1, 1, 1), new Coord3D(1, 1, -1), new Coord3D(1, -1, 1)
        ));
        model.getTriangulation().add(new Triangle3D(
                new Coord3D(1, -1, -1), new Coord3D(1, 1, -1), new Coord3D(1, -1, 1)
        ));

        model.getTriangulation().add(new Triangle3D(
                new Coord3D(1, 1, 1), new Coord3D(-1, 1, 1), new Coord3D(1, -1, 1)
        ));
        model.getTriangulation().add(new Triangle3D(
                new Coord3D(-1, -1, 1), new Coord3D(-1, 1, 1), new Coord3D(1, -1, 1)
        ));

        model.getTriangulation().add(new Triangle3D(
                new Coord3D(-1, -1, -1), new Coord3D(-1, 1, -1), new Coord3D(1, -1, -1)
        ));
        model.getTriangulation().add(new Triangle3D(
                new Coord3D(1, 1,-1), new Coord3D(-1, 1, -1), new Coord3D(1, -1, -1)
        ));

        model.getTriangulation().add(new Triangle3D(
                new Coord3D(-1, -1, -1), new Coord3D(1, -1, -1), new Coord3D(-1, -1, 1)
        ));
        model.getTriangulation().add(new Triangle3D(
                new Coord3D(1, -1, 1), new Coord3D(1, -1, -1), new Coord3D(-1, -1, 1)
        ));

        model.getTriangulation().add(new Triangle3D(
                new Coord3D(-1, -1, -1), new Coord3D(-1, -1, 1), new Coord3D(-1, 1, -1)
        ));
        model.getTriangulation().add(new Triangle3D(
                new Coord3D(-1, 1, 1), new Coord3D(-1, -1, 1), new Coord3D(-1, 1, -1)
        ));

        //POINT_VIEW
        model.getTriangulation().add(new Triangle3D(
                new Coord3D(-10, -10, 7), new Coord3D(-10, 10, 7), new Coord3D(10, -10, 7)
        ));
        model.getTriangulation().add(new Triangle3D(
                new Coord3D(10, 10, 7), new Coord3D(-10, 10, 7), new Coord3D(10, -10, 7)
        ));


        Scene scene = new Scene();
        Animate.animObjectTrans(scene.getModelInstance(), new Vector3D(0, 0, 55));
        Animate.animObjectScale(scene.getModelInstance(), new Vector3D(3, 3, 3));


        // Set animation
        scene.getAnimationStep().setRotation(new Rotation(0.3, 0, 0));

        RenderObject renderObject = new RenderObject(scene, model);

        this.render.insert(renderObject);
    }

    public Render getRender() {
        return render;
    }
}
