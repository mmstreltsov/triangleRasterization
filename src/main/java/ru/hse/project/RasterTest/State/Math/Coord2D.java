package ru.hse.project.RasterTest.State.Math;



/**
 * Класс для хранения 2D координат вершины
 */
public class Coord2D {

    /**
     * X-координата
     */
    private double x;
    /**
     * Y-координата
     */
    private double y;



    @Override
    public String toString() {
        return "(" + x  + ", " + + y + ')';
    }


    /**
     * Z-координата, нужна для формирования Z-буфера
     */
    private double zCoord;


    /**
     * Конструктор от двух координат
     * @param x X-координата
     * @param y Y-координата
     */
    public Coord2D(double x, double y) {
        this.x = x;
        this.y = y;
        this.zCoord = 0;
    }

    /**
     * Конструктор от трех координат
     * @param x X-координата
     * @param y Y-координата
     * @param zCoord Z-координата для Z-буфера
     */
    public Coord2D(double x, double y, double zCoord) {
        this.x = x;
        this.y = y;
        this.zCoord = zCoord;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getzCoord() {
        return zCoord;
    }

    public void setzCoord(double zCoord) {
        this.zCoord = zCoord;
    }
}
