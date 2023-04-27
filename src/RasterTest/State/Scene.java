package RasterTest.State;

import RasterTest.State.Math.Matrix4x4;

public class Scene implements Transformation{

    private ModelInstance modelInstance;
    private Camera camera;
    private PointView pointView;

    public Scene(ModelInstance modelInstance, Camera camera, PointView pointView) {
        this.modelInstance = modelInstance;
        this.camera = camera;
        this.pointView = pointView;
    }

    public Scene() {
        this.modelInstance = new ModelInstance();
        this.camera = new Camera();
        this.pointView = new PointView();
    }

    private Matrix4x4 M_proj() {
        return pointView.transformation();
    }
    private Matrix4x4 M_cam() {
        return camera.transformation();
    }
    private Matrix4x4 M_mod () {
        return modelInstance.transformation();
    }

    @Override
    public Matrix4x4 transformation() {
        return M_proj().multiplyOnMatrix(M_cam()).multiplyOnMatrix(M_mod());
    }
}
