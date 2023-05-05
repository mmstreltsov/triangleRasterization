package RasterTest.State.Animation;

import RasterTest.State.Camera;
import RasterTest.State.Math.Angles;
import RasterTest.State.Math.Vector3D;
import RasterTest.State.ModelInstance;
import RasterTest.State.RenderObject;

/**
 * Класс для in-place анимации. Все методы статические
 */
public class Animate {

    /**
     * Поворот камеры на углы, заданные в Angles
     */
    public static void animCameraRotate(Angles angles) {
        ToRotate.rotate(Camera.fabric().getRotation(), angles.getAlpha(), angles.getBeta(), angles.getGamma());
    }
    /**
     * Поворот камеры на углы, заданные в параметрах
     * @param x Относительно OX
     * @param y Относительно OY
     * @param z Относительно OZ
     */
    public static void animCameraRotate(double x, double y, double z) {
        ToRotate.rotate(Camera.fabric().getRotation(), x, y, z);
    }

    /**
     * Перемещение точки камеры на заданный вектор
     * @param vec Вектор
     */
    public static void animCameraTransl(Vector3D vec) {
        ToTranslate.translate(Camera.fabric().getOffset(), vec.getX(), vec.getY(), vec.getZ());
    }

    /**
     * Перемещение точки камеры на вектор, заданный в параметрах
     * @param x X-координата
     * @param y Y-координата
     * @param z Z-координата
     */
    public static void animCameraTransl(double x, double y, double z) {
        ToTranslate.translate(Camera.fabric().getOffset(), x, y, z);
    }

    /**
     * Поворот заданного объекта на заданный угол. Объект задается через RenderObject
     */
    public static void animObjectRotate(RenderObject renderObject, Angles angles) {
        Animate.animObjectRotate(renderObject.getScene().getModelInstance(), angles);
    }

    /**
     * Поворот заданного объекта на заданный угол. Объект задается через ModelInstance
     */
    public static void animObjectRotate(ModelInstance modelInstance, Angles angles) {
        ToRotate.rotate(modelInstance.getRotation(), angles.getAlpha(), angles.getBeta(), angles.getGamma());
    }

    /**
     * Перемещение заданного объекта на заданный вектор. Объект задается через RenderObject
     */
    public static void animObjectTrans(RenderObject renderObject, Vector3D vec) {
        Animate.animObjectTrans(renderObject.getScene().getModelInstance(), vec);
    }

    /**
     * Перемещение заданного объекта на заданный вектор. Объект задается через ModelInstance
     */
    public static void animObjectTrans(ModelInstance modelInstance, Vector3D vec) {
        ToTranslate.translate(modelInstance.getTranslation(), vec.getX(), vec.getY(), vec.getZ());
    }

    /**
     * Растяжение заданного объекта на заданный вектор. Объект задается через RenderObject
     */
    public static void animObjectScale(RenderObject renderObject, Vector3D vec) {
        Animate.animObjectScale(renderObject.getScene().getModelInstance(), vec);
    }

    /**
     * Растяжение заданного объекта на заданный вектор. Объект задается через ModelInstance
     */
    public static void animObjectScale(ModelInstance modelInstance, Vector3D vec) {
        ToScale.scale(modelInstance.getScale(), vec.getX(), vec.getY(), vec.getZ());
    }
}
