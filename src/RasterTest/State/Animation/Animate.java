package RasterTest.State.Animation;

import RasterTest.State.Camera;
import RasterTest.State.Math.Angles;
import RasterTest.State.Math.Vector3D;
import RasterTest.State.ModelInstance;
import RasterTest.State.RenderObject;

public class Animate {
    public static void animCameraRotate(Angles angles) {
        ToRotate.rotate(Camera.fabric().getRotation(), angles.getAlpha(), angles.getBeta(), angles.getGamma());
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



    public static void animObjectRotate(RenderObject renderObject, Angles angles) {
        ToRotate.rotate(renderObject.getScene().getAnimationStep().getRotation(), angles.getAlpha(), angles.getBeta(), angles.getGamma());
    }

    public static void animObjectRotate(ModelInstance modelInstance, Angles angles) {
        ToRotate.rotate(modelInstance.getRotation(), angles.getAlpha(), angles.getBeta(), angles.getGamma());
    }

    public static void animObjectTrans(RenderObject renderObject, Vector3D vec) {
        ToTranslate.translate(renderObject.getScene().getAnimationStep().getTranslation(), vec.getX(), vec.getY(), vec.getZ());
    }

    public static void animObjectTrans(ModelInstance modelInstance, Vector3D vec) {
        ToTranslate.translate(modelInstance.getTranslation(), vec.getX(), vec.getY(), vec.getZ());
    }

    public static void animObjectScale(RenderObject renderObject, Vector3D vec) {
        ToScale.scale(renderObject.getScene().getAnimationStep().getScale(), vec.getX(), vec.getY(), vec.getZ());
    }

    public static void animObjectScale(ModelInstance modelInstance, Vector3D vec) {
        ToScale.scale(modelInstance.getScale(), vec.getX(), vec.getY(), vec.getZ());
    }
}
