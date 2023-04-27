package RasterTest.State.Math;

import java.lang.Math;

public class EulerAngles {

    public EulerAngles() {
        alpha = 0.;
        beta = 0.;
        gamma = 0.;
    }
    public EulerAngles(double alpha, double beta, double gamma) {
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
        Matrix4x4 matrix = new Matrix4x4();
        matrix.setM11(Math.cos(alpha));
        matrix.setM22(Math.cos(alpha));
        matrix.setM12(-Math.sin(alpha));
        matrix.setM21(Math.sin(alpha));

        matrix.setM33(1.);
        matrix.setM44(1.);
        return matrix;
    }

    public Matrix4x4 RBeta() {
        Matrix4x4 matrix = new Matrix4x4();
        matrix.setM22(Math.cos(beta));
        matrix.setM33(Math.cos(beta));
        matrix.setM23(-Math.sin(beta));
        matrix.setM32(Math.sin(beta));

        matrix.setM11(1.);
        matrix.setM44(1.);
        return matrix;
    }

    public Matrix4x4 RGamma() {
        Matrix4x4 matrix = new Matrix4x4();
        matrix.setM11(Math.cos(gamma));
        matrix.setM22(Math.cos(gamma));
        matrix.setM12(-Math.sin(gamma));
        matrix.setM21(Math.sin(gamma));

        matrix.setM33(1.);
        matrix.setM44(1.);
        return matrix;
    }



}
