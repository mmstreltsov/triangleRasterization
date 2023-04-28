package RasterTest.State;

import RasterTest.PixelScreen;
import RasterTest.State.Math.Matrix4x4;

public class PointView implements Transformation {

    public static PointView pointView;
    private double d;
    private double cWidth = PixelScreen.resolutionX;
    private double cHeight = PixelScreen.resolutionY;

    private double vWidth;


    private double vHeight;

    public static PointView fabric() {
        if (pointView == null) {
            pointView = new PointView();
        }
        return pointView;
    }

    public static PointView fabric(double d, double vWidth, double vHeight) {
        if (pointView == null) {
            pointView = new PointView(d, vWidth, vHeight);
        }
        else {
            pointView.d = d;
            pointView.vHeight = vHeight;
            pointView.vWidth = vWidth;
        }
        return pointView;
    }

    private PointView(double d, double vWidth, double vHeight) {
        this.d = d;
        this.vWidth = vWidth;
        this.vHeight = vHeight;
    }

    private PointView() {
        this.d = 10.;
        this.vWidth = 640.;
        this.vHeight = 480.;
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

    public double getD() {
        return d;
    }

}
