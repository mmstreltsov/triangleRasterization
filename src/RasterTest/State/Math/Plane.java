package RasterTest.State.Math;

/**
 * Класс для задания плоскости в 3D. Задается уравнение плоскости.
 */
public class Plane {
    /**
     * Коэффициент при x
     */
    private final double A;
    /**
     * Коэффициент при y
     */
    private final double B;
    /**
     * Коэффициент при z
     */
    private final double C;
    /**
     * Свободный коэффициент
     */
    private final double D;
    /**
     * Храним отдельно вектор нормали
     */
    private final Vector3D normal;

    /**
     * Конструктор от двух векторов и точки
     */
    public Plane(Vector3D v1, Vector3D v2, Coord3D point) {
        v1 = v1.normalized();
        v2 = v2.normalized();

        this.normal = Vector3D.crossProduct(v1, v2).normalized();

        this.A = normal.getX();
        this.B = normal.getY();
        this.C = normal.getZ();

        this.D = -(this.A * point.getX() + this.B * point.getY() + this.C * point.getZ());
    }

    /**
     * Конструктор от трех точек
     */
    public Plane(Coord3D point1, Coord3D point2, Coord3D point3) {
        this(point1.subtracting(point3).toVector(), point2.subtracting(point3).toVector(), point3);
    }

    private final static double EPS = 1e-7;

    /**
     * Метод для поиска пересечения прямой с данной плоскостью. Прямая задается вектором направления и точкой на прямой.
     */
    public Coord3D intersectionWithLine(Vector3D vector, Coord3D point) {
        vector = vector.normalized();
        double rightPart = - (this.D + (this.A * point.getX() + this.B * point.getY() + this.C * point.getZ()));
        double leftPart = this.A * vector.getX() + this.B * vector.getY() + this.C * vector.getZ();
        if (Math.abs(leftPart) < EPS) {
            return point;
        }
        double t = rightPart / leftPart;
        Coord3D intersect = new Coord3D(point.getX() + vector.getX() * t, point.getY() + vector.getY() * t, point.getZ() + vector.getZ() * t);
        return intersect;
    }


    /**
     * Поиск проекции точки на плоскость
     */
    public Coord3D findPointProjection(Coord3D point) {
       return intersectionWithLine(this.normal, point);
    }

    @Override
    public String toString() {
        return A + "x + " + B + "y +" + C + "z + " + D;
    }

    public double getA() {
        return A;
    }

    public double getB() {
        return B;
    }

    public double getC() {
        return C;
    }

    public double getD() {
        return D;
    }

    public Vector3D getNormal() {
        return normal;
    }

}
