package RasterTest;

import RasterTest.State.Animation.ToTranslate;
import RasterTest.State.Math.Coord3D;
import RasterTest.State.Math.Triangle3D;
import RasterTest.State.Model;
import RasterTest.State.RenderObject;
import RasterTest.State.Scene;

public class Initialization {
    private final Render render = new Render();

    public Initialization() {
        Model model = new Model();

        Coord3D v1 = new Coord3D(30., 0., 0.);
        Coord3D v2 = new Coord3D(-30., 0., 0.);
        Coord3D v3 = new Coord3D(0., 25., 0.);
        model.getTriangulation().add(new Triangle3D(v1, v2, v3));

        Scene scene = new Scene();
        ToTranslate.translate(scene.getModelInstance().getTranslation(), 0, 0, 20);

        RenderObject renderObject = new RenderObject(scene, model);
        this.render.insert(renderObject);
    }

    public Render getRender() {
        return render;
    }
}
