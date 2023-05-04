package RasterTest.State.Math;

import java.lang.Math;

/**
 * Класс, хранящий в себе три угла для поворотов относительно осей OX, OY и OZ соответственно
 */
public class Angles {


    /**
     * Дефолтный конструктор, инициализация нулями
     */
    public Angles() {
        alpha = 0.;
        beta = 0.;
        gamma = 0.;
    }

    /**
     * Конструктор от параметров
     * @param alpha относительно OX
     * @param beta относительно OY
     * @param gamma относительно OZ
     */
    public Angles(double alpha, double beta, double gamma) {
        this.alpha = alpha;
        this.beta = beta;
        this.gamma = gamma;
    }

    /**
     * Относительно OX
     */
    private double alpha;
    /**
     * Относительно OY
     */
    private double beta;
    /**
     * Относительно OZ
     */
    private double gamma;


    /**
     * Геттер
     */
    public double getAlpha() {
        return alpha;
    }

    /**
     * Сеттер
     */
    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    /**
     * Геттер
     */
    public double getBeta() {
        return beta;
    }
    /**
     * Сеттер
     */
    public void setBeta(double beta) {
        this.beta = beta;
    }

    /**
     * Геттер
     */
    public double getGamma() {
        return gamma;
    }
    /**
     * Сеттер
     */
    public void setGamma(double gamma) {
        this.gamma = gamma;
    }


    /**
     * Метод-переход для положительного угла
     * @return матрицу поворота относительно OX
     */
    public Matrix4x4 RAlpha() {
        return alphaMatrix(alpha);
    }

    /**
     * Метод-переход для отрицательного угла
     * @return матрицу поворота относительно OX
     */
    public Matrix4x4 RAlphaNegative() {
        return alphaMatrix(-alpha);
    }

    /**
     * Приватный метод для подсчета матрицы
     * @param alpha
     * @return
     */
    private Matrix4x4 alphaMatrix(double alpha) {
        Matrix4x4 matrix = new Matrix4x4();
        matrix.setM22(Math.cos(alpha));
        matrix.setM33(Math.cos(alpha));
        matrix.setM23(-Math.sin(alpha));
        matrix.setM32(Math.sin(alpha));

        matrix.setM11(1.);
        matrix.setM44(1.);
        return matrix;
    }

    /**
     * Метод-переход для положительного угла
     * @return матрицу поворота относительно OY
     */
    public Matrix4x4 RBeta() {
        return betaMatrix(beta);
    }

    /**
     * Метод-переход для отрицательного угла
     * @return матрицу поворота относительно OY
     */
    public Matrix4x4 RBetaNegative(){
        return betaMatrix(-beta);
    }

    /**
     * Приватный метод для подсчета матрицы
     * @param beta
     * @return
     */
    private Matrix4x4 betaMatrix( double beta) {
        Matrix4x4 matrix = new Matrix4x4();
        matrix.setM11(Math.cos(beta));
        matrix.setM33(Math.cos(beta));
        matrix.setM13(Math.sin(beta));
        matrix.setM31(-Math.sin(beta));

        matrix.setM22(1.);
        matrix.setM44(1.);
        return matrix;
    }

    /**
     * Метод-переход для положительного угла
     * @return матрицу поворота относительно OZ
     */
    public Matrix4x4 RGamma() {
        return gammaMatrix(gamma);
    }

    /**
     * Метод-переход для отрицательного угла
     * @return матрицу поворота относительно OZ
     */
    public Matrix4x4 RGammaNegative() {
        return gammaMatrix(-gamma);
    }

    /**
     * Приватный метод для подсчета матрицы
     * @param gamma
     * @return
     */
    private Matrix4x4 gammaMatrix(double gamma) {
        Matrix4x4 matrix = new Matrix4x4();
        matrix.setM11(Math.cos(gamma));
        matrix.setM22(Math.cos(gamma));
        matrix.setM12(-Math.sin(gamma));
        matrix.setM21(Math.sin(gamma));

        matrix.setM33(1.);
        matrix.setM44(1.);
        return matrix;
    }

    /**
     * Преобразование класса в строку
     * @return
     */
    @Override
    public String toString() {
        return "EulerAngles{" +
                "alpha=" + alpha +
                ", beta=" + beta +
                ", gamma=" + gamma +
                '}';
    }
}
