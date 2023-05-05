package RasterTest.State.Math;


/**
 * Класс точки в 3D. Наследуется от Однородной системы координат.
 */
public class Coord3D extends HomogeneousCoord {
    /**
     * Конструктор, определяющий точку в однородной системе координат (4 элемент равен 1)
     * @param x
     * @param y
     * @param z
     */
    public Coord3D(double x, double y, double z) {
        super(x, y, z, 1);
    }
}
