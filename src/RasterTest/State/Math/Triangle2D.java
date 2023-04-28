package RasterTest.State.Math;

import java.util.Objects;

public class Triangle2D {
    private Coord2D vertex1;
    private Coord2D vertex2;
    private Coord2D vertex3;

    private double lightCoefficient = 0;

    public double getLightCoefficient() {
        return lightCoefficient;
    }
    public void setLightCoefficient(double lightCoefficient) {
        this.lightCoefficient = lightCoefficient;
    }
    public Triangle2D(Coord2D vertex1, Coord2D vertex2, Coord2D vertex3) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.vertex3 = vertex3;
    }

    public Coord2D getVertex1() {
        return vertex1;
    }

    public void setVertex1(Coord2D vertex1) {
        this.vertex1 = vertex1;
    }

    public Coord2D getVertex2() {
        return vertex2;
    }

    public void setVertex2(Coord2D vertex2) {
        this.vertex2 = vertex2;
    }

    public Coord2D getVertex3() {
        return vertex3;
    }

    public void setVertex3(Coord2D vertex3) {
        this.vertex3 = vertex3;
    }

    @Override
    public String toString() {
        return "Triangle2D{" +
                "vertex1=" + vertex1 +
                ", vertex2=" + vertex2 +
                ", vertex3=" + vertex3 +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle2D that = (Triangle2D) o;
        return Objects.equals(vertex1, that.vertex1) && Objects.equals(vertex2, that.vertex2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertex1, vertex2);
    }
}
