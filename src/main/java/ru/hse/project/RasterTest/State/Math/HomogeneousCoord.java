package ru.hse.project.RasterTest.State.Math;

/**
 * Однородная система координат в 3D пространстве
 */
public class HomogeneousCoord {
    /**
     * X-координата
     */
    private double x;
    /**
     * Y-координата
     */
    private double y;
    /**
     * Z-координата
     */
    private double z;
    /**
     * Флаг для определения. 0 - вектор. иначе - точка
     */
    private double w;

    /**
     *
     * @param x X-координата
     * @param y Y-координата
     * @param z Z-координата
     * @param w Флаг для определения. 0 - вектор. иначе - точка
     */
    public HomogeneousCoord(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    /**
     * Генерация Вектора по данным координатам
     * @return Вектор
     */
    public Vector3D toVector() {
        return new Vector3D(x, y, z);
    }

    /**
     * Генерация Точки по данным координатам
     * @return Точка
     */
    public Coord3D toPoint() {
        return new Coord3D(x, y, z);
    }
    @Override
    public String toString() {
        return "(" + x + ") + (" + y + ") + (" + z + ")";
    }

    /**
     * Сумма однородных систем координат. Суммируется с данным
     * @param other второе слагаемое
     * @return Новую однородную систему координат
     */
    public HomogeneousCoord additional(HomogeneousCoord other) {
        return new HomogeneousCoord(x + other.x, y + other.y, z + other.z, w + other.w);
    }

    /**
     * Разность однородных систем координат. Вычитается из данной
     * @param other вычитаемое
     * @return Новую однородную систему координат
     */
    public HomogeneousCoord subtracting(HomogeneousCoord other) {
        return new HomogeneousCoord(x - other.x, y - other.y, z - other.z, w - other.w);
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

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }
}
