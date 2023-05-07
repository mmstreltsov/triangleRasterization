package RasterTest.State.Math;

/**
 * Класс вектора в 3D. Наследуется от Однородной системы координат.
 */
public class Vector3D extends HomogeneousCoord {

    /**
     * Конструктор, определяющий вектор в однородной системе координат (4 элемент равен 0)
     * @param x
     * @param y
     * @param z
     */
    public Vector3D(double x, double y, double z) {
        super(x, y, z, 0);
    }

    /**
     * Нормализации вектора
     * @param a Вектор, который надо отнормировать
     * @return Отнормированный вектор
     */
    public static Vector3D normalized(Vector3D a) {
        double val = magnitude(a);
        if (val < 1e-7) {
            return a;
        }
        return new Vector3D(a.getX() / val, a.getY() / val, a.getZ() / val);
    }

    public Vector3D multiplyOnScalar(double lambda) {
        return new Vector3D(this.getX() * lambda, this.getY() * lambda, this.getZ() * lambda);
    }

    /**
     * Нормализация вектора
     * @return Отнормированный вектор
     */
    public Vector3D normalized() {
        return Vector3D.normalized(this);
    }


    /**
     * Векторное произведение в правом ортонормированном базисе
     * @param a Вектор1
     * @param b Вектор2
     * @return a x b
     */
    public static Vector3D crossProduct(Vector3D a, Vector3D b) {
        double s1 = a.getY() * b.getZ() - a.getZ() * b.getY();
        double s2 = a.getZ() * b.getX() - a.getX() * b.getZ();
        double s3 = a.getX() * b.getY() - a.getY() * b.getX();
        return new Vector3D(s1, s2, s3);
    }

    /**
     * Скалярное произведение векторов
     * @param a Вектор1
     * @param b Вектор2
     * @return (a, b)
     */
    public static double dotProduct(Vector3D a, Vector3D b) {
        return a.getX() * b.getX() + a.getY() * b.getY() + a.getZ() * b.getZ();
    }

    /**
     * Евклидова норма
     * @param a вектор
     * @return sqrt( sum( (X_i) ^2) )
     */
    public static double magnitude(Vector3D a) {
        double val = Math.pow(a.getX(), 2) + Math.pow(a.getY(), 2) + Math.pow(a.getZ(), 2);
        val = Math.sqrt(val);
        return val;
    }
    /**
     * Евклидова норма
     * @return sqrt( sum( (X_i) ^2) )
     */
    public double magnitude() {
        return Vector3D.magnitude(this);
    }

    /**
     * Эпсилон для точности
     */
    private static final double EPS = 1e-7;

    /**
     * Косинус угла между двумя заданными векторами.
     * Если один из векторов равен 0 (в заданной точности 1e-7), то метод вернет 0.
     * @param a1 Вектор1
     * @param a2 Вектор2
     * @return косинус
     */
    public static double cosAngleBetweenVectors(Vector3D a1, Vector3D a2) {
        double m1 = magnitude(a1);
        double m2 = magnitude(a2);
        if (m1 < EPS || m2 < EPS) {
            return 0.;
        }
        double cos = dotProduct(a1, a2) / (m1 * m2);
        return cos;
    }
}
