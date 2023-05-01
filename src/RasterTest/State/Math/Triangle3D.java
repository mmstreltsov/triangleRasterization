package RasterTest.State.Math;

import RasterTest.State.Light;

import java.util.Objects;

public class Triangle3D {
    private Coord3D vertex1;
    private Coord3D vertex2;
    private Coord3D vertex3;


    public Triangle3D(Coord3D vertex1, Coord3D vertex2, Coord3D vertex3) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.vertex3 = vertex3;
    }

    public static Vector3D normal(Coord3D vertex1, Coord3D vertex2, Coord3D vertex3) {
        double a1 = vertex2.getX() - vertex1.getX();
        double b1 = vertex2.getY() - vertex1.getY();
        double c1 = vertex2.getZ() - vertex1.getZ();
        double a2 = vertex3.getX() - vertex1.getX();
        double b2 = vertex3.getY() - vertex1.getY();
        double c2 = vertex3.getZ() - vertex1.getZ();

        Vector3D PQ = new Vector3D(a1, b1, c1);
        Vector3D PR = new Vector3D(a2, b2, c2);

        return Vector3D.crossProduct(PQ, PR);
    }


    public Triangle3D transformation(Matrix4x4 matrix) {
        HomogeneousCoord v1 = matrix.multiplyOnHomo(vertex1);
        HomogeneousCoord v2 = matrix.multiplyOnHomo(vertex2);
        HomogeneousCoord v3 = matrix.multiplyOnHomo(vertex3);
        Coord3D x = new Coord3D(v1.getX(), v1.getY(), v1.getZ());
        Coord3D y = new Coord3D(v2.getX(), v2.getY(), v2.getZ());
        Coord3D z = new Coord3D(v3.getX(), v3.getY(), v3.getZ());

        return new Triangle3D(x, y, z);
    }

    @Override
    public String toString() {
        return "Triangle3D{" +
                "vertex1=" + vertex1 +
                ", vertex2=" + vertex2 +
                ", vertex3=" + vertex3 +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle3D triangle = (Triangle3D) o;
        return Objects.equals(vertex1, triangle.vertex1) && Objects.equals(vertex2, triangle.vertex2) && Objects.equals(vertex3, triangle.vertex3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertex1, vertex2, vertex3);
    }

    public Coord3D getVertex1() {
        return vertex1;
    }

    public void setVertex1(Coord3D vertex1) {
        this.vertex1 = vertex1;
    }

    public Coord3D getVertex2() {
        return vertex2;
    }

    public void setVertex2(Coord3D vertex2) {
        this.vertex2 = vertex2;
    }

    public Coord3D getVertex3() {
        return vertex3;
    }

    public void setVertex3(Coord3D vertex3) {
        this.vertex3 = vertex3;
    }
}
