package RasterTest.State.Animation;

import RasterTest.State.Camera;
import RasterTest.State.Math.EulerAngles;
import RasterTest.State.Math.Vector3D;
import RasterTest.State.RenderObject;

public class Animate {

    public static void defaultAnim(RenderObject renderObject) {
        Animate.animObjectRotate(renderObject, new EulerAngles(0, 0.2, 0.1));
    }


    public static void animCameraRotate(EulerAngles eulerAngles) {
        ToRotate.rotate(Camera.fabric().getRotation(), eulerAngles.getAlpha(), eulerAngles.getBeta(), eulerAngles.getGamma());
    }

    public static void animCameraRotate(double x, double y, double z) {
        ToRotate.rotate(Camera.fabric().getRotation(), x, y, z);
    }

    public static void animCameraTransl(Vector3D vec) {
        ToTranslate.translate(Camera.fabric().getOffset(), vec.getX(), vec.getY(), vec.getZ());
    }

    public static void animCameraTransl(double x, double y, double z) {
        ToTranslate.translate(Camera.fabric().getOffset(), x, y, z);
    }
    public static void animObjectRotate(RenderObject renderObject, EulerAngles eulerAngles) {
        ToRotate.rotate(renderObject.getScene().getModelInstance().getRotation(), eulerAngles.getAlpha(), eulerAngles.getBeta(), eulerAngles.getGamma());
    }
    public static void animObjectRotate(RenderObject renderObject, double x, double y, double z) {
        ToRotate.rotate(renderObject.getScene().getModelInstance().getRotation(), x, y, z);
    }

    public static void animObjectTrans(RenderObject renderObject, Vector3D vec) {
        ToTranslate.translate(renderObject.getScene().getModelInstance().getTranslation(), vec.getX(), vec.getY(), vec.getZ());
    }

    public static void animObjectTrans(RenderObject renderObject, double x, double y, double z) {
        ToTranslate.translate(renderObject.getScene().getModelInstance().getTranslation(), x, y, z);
    }
    public static void animObjectScale(RenderObject renderObject, Vector3D vec) {
        ToScale.scale(renderObject.getScene().getModelInstance().getScale(), vec.getX(), vec.getY(), vec.getZ());
    }
    public static void animObjectScale(RenderObject renderObject, double x, double y, double z){
        ToScale.scale(renderObject.getScene().getModelInstance().getScale(),  x, y, z);
    }
}
