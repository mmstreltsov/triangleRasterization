package RasterTest.State;

import RasterTest.Animation;
import RasterTest.State.Math.Coord2D;
import RasterTest.State.Math.HomogeneousCoord;
import RasterTest.State.Math.Matrix4x4;

/**
 *  Правила ее преобразования в поле зрения камеры
 */
public class Scene implements Transformation {

    /**
     * Поле, хранящее правила преобразования модели в глобальную систему координат
     */
    private ModelInstance modelInstance;
    /**
     * Поле, хранящее класс, показывающий шаг анимации
     */
    private AnimateModelInstance animationStep;

    /**
     * Камера
     */
    private final Camera camera;

    /**
     * Экран
     */
    private final PointView pointView;

    /**
     * Свет
     */
    private final Light light;

    /**
     * Конструктор от заданный правил.
     * Делегируется дефолтный конструктор (все остальные поля)
     * @param modelInstance класс правил
     */
    public Scene(ModelInstance modelInstance) {
        this();
        this.modelInstance = modelInstance;
    }

    /**
     * Дефолтный конструктор. Все поля (что можно), создаются дефолтно. Остальные вызывают единственный экземпляр
     */
    public Scene() {
        this.modelInstance = new ModelInstance();
        this.animationStep = new AnimateModelInstance();
        this.camera = Camera.fabric();
        this.light = Light.fabric();
        this.pointView = PointView.fabric();
    }

    /**
     * Матрица преобразования Проекции
     * @return Матрица
     */
    private Matrix4x4 M_proj() {
        return pointView.transformation();
    }

    /**
     * Матрица преобразования Камеры
     * @return Матрица
     */
    private Matrix4x4 M_cam() {
        return camera.transformation();
    }

    /**
     * Матрица преобразования объекта в глобальную систему координат. Если стоит флаг анимации, то применяется шаг анимации
     * @return Матрица
     */
    private Matrix4x4 M_mod() {
        return modelInstance.transformation();
    }

    public void makeAnimation() {
        if (Animation.isIsAnimate()) {
            modelInstance.animationStep(this.animationStep);
        }
    }

    /**
     * Итоговая матрица преобразования M = M_proj * M_cam * M_trans в заданном порядке
     * @return Матрица
     */
    @Override
    public Matrix4x4 transformation() {
        return transformationStep2().multiplyOnMatrix(transformationStep1());
    }

    /**
     * Анимация разбита на 2 шага. Первый шаг - преобразование модели в глобальную систему координат
     * @return матрица преобразования
     */
    public Matrix4x4 transformationStep1() {
        return M_mod();
    }

    /**
     * Анимация разбита на 2 шага. Второй шаг - применение преобразований Камеры и Проекции на канвас.
     * @return матрица преобразования
     */
    public Matrix4x4 transformationStep2() {
        return M_proj().multiplyOnMatrix(M_cam());
    }

    /**
     * Получение 2D координат из однородной системы координат.
     * @param coord Однородная система координат
     * @return 2D координату
     */
    public static Coord2D getting2DCoordinate(HomogeneousCoord coord) {
        double z = coord.getZ();
        Coord2D coord2D = new Coord2D(coord.getX() / z, coord.getY() / z, coord.getZ());
        return coord2D;
    }

    public ModelInstance getModelInstance() {
        return modelInstance;
    }

    public void setModelInstance(ModelInstance modelInstance) {
        this.modelInstance = modelInstance;
    }

    public ModelInstance getAnimationStep() {
        return animationStep;
    }

    public void setAnimationStep(AnimateModelInstance animationStep) {
        this.animationStep = animationStep;
    }

}
