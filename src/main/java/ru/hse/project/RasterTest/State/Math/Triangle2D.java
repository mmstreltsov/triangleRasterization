package ru.hse.project.RasterTest.State.Math;

import java.util.Objects;

/**
 * Определение треугольника в 2D пространстве.
 */
public class Triangle2D {
    /**
     * Вершина 1
     */
    private Coord2D vertex1;
    /**
     * Вершина 2
     */
    private Coord2D vertex2;
    /**
     * Вершина 3
     */
    private Coord2D vertex3;

    /**
     * Храним коэффициент света.
     */
    private double lightCoefficient = 0;

    public double getLightCoefficient() {
        return lightCoefficient;
    }
    public void setLightCoefficient(double lightCoefficient) {
        this.lightCoefficient = lightCoefficient;
    }

    /**
     * Конструктор от трех 2D вершин
     */
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

}
