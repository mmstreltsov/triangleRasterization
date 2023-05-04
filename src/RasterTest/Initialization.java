package RasterTest;

import RasterTest.State.Animation.Animate;
import RasterTest.State.Math.Coord3D;
import RasterTest.State.Math.Angles;
import RasterTest.State.Math.Triangle3D;
import RasterTest.State.Math.Vector3D;
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

//        //УДАЛИТЬ ЭТО
//        Coord3D v12 = new Coord3D(0., 30., -10.);
//        Coord3D v22 = new Coord3D(0., -30., 0.);
//        Coord3D v32 = new Coord3D(25., 0, 10.);
//        model.getTriangulation().add(new Triangle3D(v12, v22, v32));
//        //УДАЛИТЬ ЭТО

        Scene scene = new Scene();
        Animate.animObjectTrans(scene.getModelInstance(), new Vector3D(0, 0, 20));

        Animate.animObjectRotate(scene.getAnimationStep(), new Angles(0, 4, 2));
        RenderObject renderObject = new RenderObject(scene, model);
        this.render.insert(renderObject);
    }

    public Render getRender() {
        return render;
    }
}
