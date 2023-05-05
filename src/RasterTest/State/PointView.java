package RasterTest.State;

import RasterTest.PixelScreen;
import RasterTest.State.Math.Matrix4x4;

/**
 * Класс для инициализации экрана на заданном фокусном расстоянии от камеры для проекции 3D координат на 2D.
 * Генерирует матрицу проекции.
 * Создается лишь 1 экземпляр класса
 */
public class PointView implements Transformation {

    /**
     * Поле, хранящее единственный экземпляр
     */
    private static PointView pointView;
    /**
     * Поле, отвечающее за фокусное расстояние
     */
    private double d;
    /**
     * Ширина Канваса -- инициализируется снаружи
     */
    private double cWidth = PixelScreen.resolutionX;
    /**
     * Высота Канваса -- инициализируется снаружи
     */
    private double cHeight = PixelScreen.resolutionY;

    /**
     * Ширина экрана
     */
    private double vWidth;

    /**
     * Высота экрана
     */
    private double vHeight;

    /**
     * Константа, отвечающая за дефолтную инициализацию. Высота Экрана
     */
    private static final double vHeight_DEFAULT = 50.;

    /**
     * Константа, отвечающая за дефолтную инициализацию. Фокусное расстояние
     */
    private static final double d_DEFAULT = 10.;
    /**
     * Фабричный метод для инициализации класса.
     * @return ссылку на единственный инстанс
     */
    public static PointView fabric() {
        if (pointView == null) {
            pointView = new PointView();
        }
        return pointView;
    }

    /**
     * Дефолтный конструктор.
     * Фокусное расстояние = дефолтному значению = 10
     * Высота = дефолтному значению = 50
     * Ширина = В пропорции канваса от дефолтной высоты
     */
    private PointView() {
        this.d = 10.;
        this.vHeight = vHeight_DEFAULT;
        this.vWidth = vHeight * PixelScreen.resolutionX / PixelScreen.resolutionY;
    }

    /**
     * Генерация матрицу проекции в 2 этапа: нахождение проекции, соблюдение отношения между высотой и шириной
     * @return Матрица = Произведение матриц с обоих этапов
     */
    @Override
    public Matrix4x4 transformation() {
        return viewpointToCanvas().multiplyOnMatrix(projection());
    }

    /**
     * 1 этап: нахождение проекции
     * @return Матрица
     */
    private Matrix4x4 projection() {
        Matrix4x4 matrix = new Matrix4x4(1.);
        matrix.setM11(d);
        matrix.setM22(d);
        return matrix;
    }

    /**
     * 2 этап: соблюдение отношения между высотой и шириной
     * @return матрица
     */
    private Matrix4x4 viewpointToCanvas() {
        Matrix4x4 matrix = new Matrix4x4(1.);
        matrix.setM11(cWidth / vWidth);
        matrix.setM22(cHeight / vHeight);
        return matrix;
    }

    public void setD(double d) {
        this.d = d;
    }

    public double getcWidth() {
        return cWidth;
    }

    public void setcWidth(double cWidth) {
        this.cWidth = cWidth;
    }

    public double getcHeight() {
        return cHeight;
    }

    public void setcHeight(double cHeight) {
        this.cHeight = cHeight;
    }

    public double getvWidth() {
        return vWidth;
    }

    public void setvWidth(double vWidth) {
        this.vWidth = vWidth;
    }

    public double getvHeight() {
        return vHeight;
    }

    public void setvHeight(double vHeight) {
        this.vHeight = vHeight;
    }

    public double getD() {
        return d;
    }

}
