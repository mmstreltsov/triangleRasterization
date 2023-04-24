package RasterTest.State;

import RasterTest.State.Math.Coord3D;
import RasterTest.State.Math.Vector3D;

import java.util.Objects;

public class Camera {
    private Coord3D point;

    private Vector3D viewPoint;

    public Camera(Coord3D point, Vector3D viewPoint) {
        this.point = point;
        this.viewPoint = viewPoint;
    }

    public Camera() {
        this.point = new Coord3D(0, 0, -10);
        this.viewPoint = new Vector3D(0, 0, 1).normalized();
    }

    public Camera(Coord3D point) {
        this.point = point;
    }

    public Coord3D getPoint() {
        return point;
    }

    public void setPoint(Coord3D point) {
        this.point = point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Camera camera = (Camera) o;
        return Objects.equals(point, camera.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point);
    }

    public Vector3D getViewPoint() {
        return viewPoint;
    }

    public void setViewPoint(Vector3D viewPoint) {
        this.viewPoint = viewPoint;
    }
}
