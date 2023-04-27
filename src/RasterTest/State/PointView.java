package RasterTest.State;

import RasterTest.State.Math.Matrix4x4;

public class PointView implements Transformation {

    public static PointView pointView;
    private double d;
    private double cWidth;
    private double cHeight;

    private double vWidth;

    public double getD() {
        return d;
    }

    private double vHeight;

    public static PointView fabric() {
        if (pointView == null) {
            pointView = new PointView();
        }
        return pointView;
    }

    public static PointView fabric(double d, double cWidth, double cHeight, double vWidth, double vHeight) {
        if (pointView == null) {
            pointView = new PointView(d, cWidth, cHeight, vWidth, vHeight);
        }
        else {
            pointView.d = d;
            pointView.vHeight = vHeight;
            pointView.vWidth = vWidth;
            pointView.cWidth = cWidth;
            pointView.cHeight = cHeight;
        }
        return pointView;
    }

    private PointView(double d, double cWidth, double cHeight, double vWidth, double vHeight) {
        this.d = d;
        this.cWidth = cWidth;
        this.cHeight = cHeight;
        this.vWidth = vWidth;
        this.vHeight = vHeight;
    }

    private PointView() {
        this.d = 10.;
        this.cWidth = 600.;
        this.cHeight = 400;
        this.vWidth = 600.;
        this.vHeight = 400;

    }

    @Override
    public Matrix4x4 transformation() {
        return viewpointToCanvas().multiplyOnMatrix(projection());
    }

    private Matrix4x4 projection() {
        Matrix4x4 matrix = new Matrix4x4(1.);
        matrix.setM11(d);
        matrix.setM22(d);
        return matrix;
    }

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


}
