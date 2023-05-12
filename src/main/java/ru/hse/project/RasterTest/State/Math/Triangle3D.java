package ru.hse.project.RasterTest.State.Math;

/**
 * Определение треугольника в 3D пространстве.
 */
public class Triangle3D {
    /**
     * Вершина 1
     */
    private Coord3D vertex1;
    /**
     * Вершина 2
     */
    private Coord3D vertex2;
    /**
     * Вершина 3
     */
    private Coord3D vertex3;


    /**
     * Конструктор от трех 3D вершин
     */
    public Triangle3D(Coord3D vertex1, Coord3D vertex2, Coord3D vertex3) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.vertex3 = vertex3;
    }

    /**
     * По трем неколлинеарным точкам можно восстановить плоскость.
     * Метод возвращает нормаль к этой плоскости.
     * @return Вектор нормали
     */
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

    /**
     * Применение матрицы преобразования к текущему 3D треугольнику
     * @param matrix матрица преобразования
     * @return Новый 3D треугольник
     */
    public Triangle3D transformation(Matrix4x4 matrix) {
        Coord3D v1 = matrix.multiplyOnHomo(vertex1).toPoint();
        Coord3D v2 = matrix.multiplyOnHomo(vertex2).toPoint();
        Coord3D v3 = matrix.multiplyOnHomo(vertex3).toPoint();

        return new Triangle3D(v1, v2, v3);
    }

    @Override
    public String toString() {
        return "Triangle3D{" +
                "vertex1=" + vertex1 +
                ", vertex2=" + vertex2 +
                ", vertex3=" + vertex3 +
                '}';
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
