package RasterTest.State;

import RasterTest.State.Math.Matrix4x4;

public class PointView implements Transformation {
     private double d;
     private double cWidth;
     private double cHeight;

     private double vWidth;
     private double vHeight;

    public PointView(double d, double cWidth, double cHeight, double vWidth, double vHeight) {
        this.d = d;
        this.cWidth = cWidth;
        this.cHeight = cHeight;
        this.vWidth = vWidth;
        this.vHeight = vHeight;
    }

    public PointView() {
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


}
