package RasterTest.State;

import RasterTest.State.Math.Matrix4x4;

public class Scene implements Transformation {


    private ModelInstance modelInstance;
    private final Camera camera;
    private final PointView pointView;

    public Scene(ModelInstance modelInstance) {
        this.modelInstance = modelInstance;
        this.camera = Camera.fabric();
        this.pointView = PointView.fabric();
    }

    public Scene() {
        this.modelInstance = new ModelInstance();
        this.camera = Camera.fabric();
        this.pointView = PointView.fabric();
    }

    private Matrix4x4 M_proj() {
        return pointView.transformation();
    }

    private Matrix4x4 M_cam() {
        return camera.transformation();
    }

    private Matrix4x4 M_mod() {
        return modelInstance.transformation();
    }

    @Override
    public Matrix4x4 transformation() {
        return M_proj().multiplyOnMatrix(M_cam()).multiplyOnMatrix(M_mod());
    }

    public ModelInstance getModelInstance() {
        return modelInstance;
    }

    public void setModelInstance(ModelInstance modelInstance) {
        this.modelInstance = modelInstance;
    }
}
