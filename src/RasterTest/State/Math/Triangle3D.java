package RasterTest.State.Math;

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
