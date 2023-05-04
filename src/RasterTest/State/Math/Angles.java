package RasterTest.State.Math;

import java.lang.Math;

public class Angles {

    public Angles() {
        alpha = 0.;
        beta = 0.;
        gamma = 0.;
    }
    public Angles(double alpha, double beta, double gamma) {
        this.alpha = alpha;
        this.beta = beta;
        this.gamma = gamma;
    }

    private double alpha;
    private double beta;
    private double gamma;

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }

    public double getGamma() {
        return gamma;
    }

    public void setGamma(double gamma) {
        this.gamma = gamma;
    }

    public Matrix4x4 RAlpha() {
        return alphaMatrix(alpha);
    }

    public Matrix4x4 RAlphaNegative() {
        return alphaMatrix(-alpha);
    }

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

    public Matrix4x4 RBeta() {
        return betaMatrix(beta);
    }

    public Matrix4x4 RBetaNegative(){
        return betaMatrix(-beta);
    }

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

    public Matrix4x4 RGamma() {
        return gammaMatrix(gamma);
    }

    public Matrix4x4 RGammaNegative() {
        return gammaMatrix(-gamma);
    }

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


    @Override
    public String toString() {
        return "EulerAngles{" +
                "alpha=" + alpha +
                ", beta=" + beta +
                ", gamma=" + gamma +
                '}';
    }
}
