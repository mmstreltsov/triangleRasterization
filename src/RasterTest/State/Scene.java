package RasterTest.State;

import RasterTest.State.Math.Coord2D;
import RasterTest.State.Math.HomogeneousCoord;
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


    /**
     * Нужно добавить обработку случаев z == 0, z < 0 (когда камера слишком близко)
     */
    public static Coord2D getting2DCoordinate(HomogeneousCoord coord) {
        Coord2D coord2D = new Coord2D(coord.getX() / coord.getZ(), coord.getY() / coord.getZ(), coord.getZ());
        return coord2D;
    }

    public ModelInstance getModelInstance() {
        return modelInstance;
    }

    public void setModelInstance(ModelInstance modelInstance) {
        this.modelInstance = modelInstance;
    }
}
