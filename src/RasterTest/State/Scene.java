package RasterTest.State;

import RasterTest.State.Animation.ToTranslate;
import RasterTest.State.Math.Coord2D;
import RasterTest.State.Math.HomogeneousCoord;
import RasterTest.State.Math.Matrix4x4;

public class Scene implements Transformation {


    private ModelInstance modelInstance;
    private final Camera camera;
    private final PointView pointView;

    private final Light light;

    public Scene(ModelInstance modelInstance) {
        this.modelInstance = modelInstance;
        this.camera = Camera.fabric();
        this.pointView = PointView.fabric();
        this.light = Light.fabric();
    }

    public Scene() {
        this.modelInstance = new ModelInstance();
        this.camera = Camera.fabric();
        this.light = Light.fabric();
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
     * TODO: до сих пор есть ошибка с логикой
     */
    public static Coord2D getting2DCoordinate(HomogeneousCoord coord) {
        double z = coord.getZ();
        if (z <= PointView.fabric().getD()) {
            ToTranslate.translate(Camera.fabric().getOffset(), 0, 0, -(PointView.fabric().getD() - z + 1));
            throw new RuntimeException("Camera Translation");
        }
        Coord2D coord2D = new Coord2D(coord.getX() / z, coord.getY() / z, coord.getZ());
        return coord2D;
    }

    public ModelInstance getModelInstance() {
        return modelInstance;
    }

    public void setModelInstance(ModelInstance modelInstance) {
        this.modelInstance = modelInstance;
    }
}
