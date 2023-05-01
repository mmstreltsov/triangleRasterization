package RasterTest;

import RasterTest.State.*;
import RasterTest.State.Animation.ToRotate;
import RasterTest.State.Animation.ToScale;
import RasterTest.State.Animation.ToTranslate;
import RasterTest.State.Math.EulerAngles;
import RasterTest.State.Math.Vector3D;

import java.util.ArrayList;
import java.util.List;

public class Render {


    private final RenderState renderState = new RenderState();

    public void insert(RenderObject renderObject) {
        renderState.getRenderObjects().add(renderObject);
    }


    /**
     * Deprecated
     */
    public void insert(Scene scene, Model model) {
        renderState.getRenderObjects().add(new RenderObject(scene, model));
    }


    public List<Triangle> render() {
        List<Triangle> ret = new ArrayList<>();
        renderState.getRenderObjects().forEach(it -> {
            it.init();
            it.getTriangles().forEach(triangle2D -> {
                ret.add(Converter.convert(triangle2D));
            });
        });
        return ret;
    }
    public RenderState getRenderState() {
        return renderState;
    }


    public void animCameraRotate(EulerAngles eulerAngles) {
        ToRotate.rotate(Camera.fabric().getRotation(), eulerAngles.getAlpha(), eulerAngles.getBeta(), eulerAngles.getGamma());
    }

    public void animCameraTransl(Vector3D vec) {
        ToTranslate.translate(Camera.fabric().getOffset(), vec.getX(), vec.getY(), vec.getZ());
    }

    public void animObjectRotate(RenderObject renderObject, EulerAngles eulerAngles) {
        ToRotate.rotate(renderObject.getScene().getModelInstance().getRotation(), eulerAngles.getAlpha(), eulerAngles.getBeta(), eulerAngles.getGamma());
    }

    public void animObjectTrans(RenderObject renderObject, Vector3D vec) {
        ToTranslate.translate(renderObject.getScene().getModelInstance().getTranslation(), vec.getX(), vec.getY(), vec.getZ());
    }

    public void animObjectScale(RenderObject renderObject, Vector3D vec) {
        ToScale.scale(renderObject.getScene().getModelInstance().getScale(), vec.getX(), vec.getY(), vec.getZ());
    }
}
