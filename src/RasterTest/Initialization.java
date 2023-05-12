package RasterTest;

import RasterTest.State.Animation.Animate;
import RasterTest.State.Animation.Rotation;
import RasterTest.State.Math.Coord3D;
import RasterTest.State.Math.Triangle3D;
import RasterTest.State.Math.Vector3D;
import RasterTest.State.Model;
import RasterTest.State.RenderObject;
import RasterTest.State.Scene;

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

        Coord3D v1 = new Coord3D(30., 0., 0.);
        Coord3D v2 = new Coord3D(-30., 0., 0.);
        Coord3D v3 = new Coord3D(0., 25., 0.);
        model.getTriangulation().add(new Triangle3D(v1, v2, v3));

        Scene scene = new Scene();
        Animate.animObjectTrans(scene.getModelInstance(), new Vector3D(0, 0, 25));

        // Set animation
        scene.getAnimationStep().setRotation(new Rotation(0, 0.2, 0.1));

        RenderObject renderObject = new RenderObject(scene, model);

        this.render.insert(renderObject);
    }

    public Render getRender() {
        return render;
    }
}
