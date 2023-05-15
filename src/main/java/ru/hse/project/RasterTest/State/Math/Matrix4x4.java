package ru.hse.project.RasterTest.State.Math;

import java.util.Arrays;

/**
 * Матрица размера 4х4
 */
public class Matrix4x4 {

    /**
     * Инициализация нулевой матрицы
     */
    public Matrix4x4() {
    }

    /**
     * Конструктор от 16ти параметров -- все элементы матрицы задаются построчно
     */
    public Matrix4x4(double m11, double m12, double m13, double m14,
                     double m21, double m22, double m23, double m24,
                     double m31, double m32, double m33, double m34,
                     double m41, double m42, double m43, double m44) {
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;
        this.m14 = m14;
        this.m21 = m21;
        this.m22 = m22;
        this.m23 = m23;
        this.m24 = m24;
        this.m31 = m31;
        this.m32 = m32;
        this.m33 = m33;
        this.m34 = m34;
        this.m41 = m41;
        this.m42 = m42;
        this.m43 = m43;
        this.m44 = m44;
    }

    /**
     * Конструктор возвращает единичную матрицу, помноженную на Лямбду
     * @param lambda число
     */
    public Matrix4x4(double lambda) {
        this.m11 = lambda;
        this.m22 = lambda;
        this.m33 = lambda;
        this.m44 = lambda;
    }

    /**
     * Копи-конструктор
     * @param other Другая матрица 4х4
     */
    public Matrix4x4(Matrix4x4 other) {
        this.m11 = other.m11;
        this.m12 = other.m12;
        this.m13 = other.m13;
        this.m14 = other.m14;
        this.m21 = other.m21;
        this.m22 = other.m22;
        this.m23 = other.m23;
        this.m24 = other.m24;
        this.m31 = other.m31;
        this.m32 = other.m32;
        this.m33 = other.m33;
        this.m34 = other.m34;
        this.m41 = other.m41;
        this.m42 = other.m42;
        this.m43 = other.m43;
        this.m44 = other.m44;
    }

    /**
     * Перемножение матриц 4х4 по правилам математики. Умножает матрицу на данную.
     * @param otherMatrix второй множитель
     * @return Новая матрица
     */

    public Matrix4x4 multiplyOnMatrix(Matrix4x4 otherMatrix) {
        double resultM11 = (m11 * otherMatrix.m11) + (m12 * otherMatrix.m21) + (m13 * otherMatrix.m31) + (m14 * otherMatrix.m41);
        double resultM12 = (m11 * otherMatrix.m12) + (m12 * otherMatrix.m22) + (m13 * otherMatrix.m32) + (m14 * otherMatrix.m42);
        double resultM13 = (m11 * otherMatrix.m13) + (m12 * otherMatrix.m23) + (m13 * otherMatrix.m33) + (m14 * otherMatrix.m43);
        double resultM14 = (m11 * otherMatrix.m14) + (m12 * otherMatrix.m24) + (m13 * otherMatrix.m34) + (m14 * otherMatrix.m44);
        double resultM21 = (m21 * otherMatrix.m11) + (m22 * otherMatrix.m21) + (m23 * otherMatrix.m31) + (m24 * otherMatrix.m41);
        double resultM22 = (m21 * otherMatrix.m12) + (m22 * otherMatrix.m22) + (m23 * otherMatrix.m32) + (m24 * otherMatrix.m42);
        double resultM23 = (m21 * otherMatrix.m13) + (m22 * otherMatrix.m23) + (m23 * otherMatrix.m33) + (m24 * otherMatrix.m43);
        double resultM24 = (m21 * otherMatrix.m14) + (m22 * otherMatrix.m24) + (m23 * otherMatrix.m34) + (m24 * otherMatrix.m44);
        double resultM31 = (m31 * otherMatrix.m11) + (m32 * otherMatrix.m21) + (m33 * otherMatrix.m31) + (m34 * otherMatrix.m41);
        double resultM32 = (m31 * otherMatrix.m12) + (m32 * otherMatrix.m22) + (m33 * otherMatrix.m32) + (m34 * otherMatrix.m42);
        double resultM33 = (m31 * otherMatrix.m13) + (m32 * otherMatrix.m23) + (m33 * otherMatrix.m33) + (m34 * otherMatrix.m43);
        double resultM34 = (m31 * otherMatrix.m14) + (m32 * otherMatrix.m24) + (m33 * otherMatrix.m34) + (m34 * otherMatrix.m44);
        double resultM41 = (m41 * otherMatrix.m11) + (m42 * otherMatrix.m21) + (m43 * otherMatrix.m31) + (m44 * otherMatrix.m41);
        double resultM42 = (m41 * otherMatrix.m12) + (m42 * otherMatrix.m22) + (m43 * otherMatrix.m32) + (m44 * otherMatrix.m42);
        double resultM43 = (m41 * otherMatrix.m13) + (m42 * otherMatrix.m23) + (m43 * otherMatrix.m33) + (m44 * otherMatrix.m43);
        double resultM44 = (m41 * otherMatrix.m14) + (m42 * otherMatrix.m24) + (m43 * otherMatrix.m34) + (m44 * otherMatrix.m44);


        return new Matrix4x4(resultM11, resultM12, resultM13, resultM14,
                resultM21, resultM22, resultM23, resultM24,
                resultM31, resultM32, resultM33, resultM34,
                resultM41, resultM42, resultM43, resultM44);
    }

    /**
     * Умножение матрицы на Однородную систему координат по правилам математики.
     * @param row Однородная система координат
     * @return Новая однородная система координат
     */
    public HomogeneousCoord multiplyOnHomo(HomogeneousCoord row) {
        double x = row.getX();
        double y = row.getY();
        double z = row.getZ();
        double w = row.getW();

        double newX = m11 * x + m12 * y + m13 * z + m14 * w;
        double newY = m21 * x + m22 * y + m23 * z + m24 * w;
        double newZ = m31 * x + m32 * y + m33 * z + m34 * w;
        double newW = m41 * x + m42 * y + m43 * z + m44 * w;
        return new HomogeneousCoord(newX, newY, newZ, newW);
    }

    public Matrix4x4 inverse() {
        System.out.println(this);
        initAlgebraicComplements();
        double det = this.det();
        System.out.println(det);
        if (Math.abs(det) < 1e-7) {
            throw new RuntimeException("det == 0");
        }
        return this.enterMatrix().multiplyOnScalar(det);
    }


    private Matrix4x4 multiplyOnScalar(double scalar) {
        return new Matrix4x4(
                m11/scalar, m12/scalar, m13/scalar, m14/scalar,
                m21/scalar, m22/scalar, m23/scalar, m24/scalar,
                m31/scalar, m32/scalar, m33/scalar, m34/scalar,
                m41/scalar, m42/scalar, m43/scalar, m44/scalar
        );
    }

    private void initAlgebraicComplements() {
        cofactors[0][0] = m22 * (m33 * m44 - m43 * m34) - m23 * (m32 * m44 - m42 * m34) + m24 * (m32 * m43 - m42 * m33);
        cofactors[0][1] = -(m21 * (m33 * m44 - m43 * m34) - m23 * (m31 * m44 - m41 * m34) + m24 * (m31 * m43 - m41 * m33));
        cofactors[0][2] = m21 * (m32 * m44 - m42 * m34) - m22 * (m31 * m44 - m41 * m34) + m24 * (m31 * m42 - m41 * m32);
        cofactors[0][3] = -(m21 * (m32 * m43 - m42 * m33) - m22 * (m31 * m43 - m41 * m33) + m23 * (m31 * m42 - m41 * m32));
        cofactors[1][0] = -(m12 * (m33 * m44 - m43 * m34) - m13 * (m32 * m44 - m42 * m34) + m14 * (m32 * m43 - m42 * m33));
        cofactors[1][1] = m11 * (m33 * m44 - m43 * m34) - m13 * (m31 * m44 - m41 * m34) + m14 * (m31 * m43 - m41 * m33);
        cofactors[1][2] = -(m11 * (m32 * m44 - m42 * m34) - m12 * (m31 * m44 - m41 * m34) + m14 * (m31 * m42 - m41 * m32));
        cofactors[1][3] = m11 * (m32 * m43 - m42 * m33) - m12 * (m31 * m43 - m41 * m33) + m13 * (m31 * m42 - m41 * m32);
        cofactors[2][0] = m12 * (m23 * m44 - m43 * m24) - m13 * (m22 * m44 - m42 * m24) + m14 * (m22 * m43 - m42 * m23);
        cofactors[2][1] = -(m11 * (m23 * m44 - m43 * m24) - m13 * (m21 * m44 - m41 * m24) + m14 * (m21 * m43 - m41 * m23));
        cofactors[2][2] = m11 * (m22 * m44 - m42 * m24) - m12 * (m21 * m44 - m41 * m24) + m14 * (m21 * m42 - m41 * m22);
        cofactors[2][3] = -(m11 * (m22 * m43 - m42 * m23) - m12 * (m21 * m43 - m41 * m23) + m13 * (m21 * m42 - m41 * m22));
        cofactors[3][0] = -(m12 * (m23 * m34 - m33 * m24) - m13 * (m22 * m34 - m32 * m24) + m14 * (m22 * m33 - m32 * m23));
        cofactors[3][1] = m11 * (m23 * m34 - m33 * m24) - m13 * (m21 * m34 - m31 * m24) + m14 * (m21 * m33 - m31 * m23);
        cofactors[3][2] = -(m11 * (m22 * m34 - m32 * m24) - m12 * (m21 * m34 - m31 * m24) + m14 * (m21 * m32 - m31 * m22));
        cofactors[3][3] = m11 * (m22 * m33 - m32 * m23) - m12 * (m21 * m33 - m31 * m23) + m13 * (m21 * m32 - m31 * m22);
    }

    private double det() {
        return m11 * cofactors[0][0] + m12 * cofactors[0][1] + m13 * cofactors[0][2] + m14 * cofactors[0][3];
    }

    private Matrix4x4 enterMatrix() {
        return new Matrix4x4(
                cofactors[0][0], cofactors[0][1], cofactors[0][2], cofactors[0][3],
                cofactors[1][0], cofactors[1][1], cofactors[1][2], cofactors[1][3],
                cofactors[2][0], cofactors[2][1], cofactors[2][2], cofactors[2][3],
                cofactors[3][0], cofactors[3][1], cofactors[3][2], cofactors[3][3]
        ).t();
    }

    private Matrix4x4 t() {
        return new Matrix4x4(
                m11, m21, m31, m41,
                m12, m22, m32, m42,
                m13, m23, m33, m43,
                m14, m24, m34, m44
        );
    }



    public double getM11() {
        return m11;
    }

    public void setM11(double m11) {
        this.m11 = m11;
    }

    public double getM12() {
        return m12;
    }

    public void setM12(double m12) {
        this.m12 = m12;
    }

    public double getM13() {
        return m13;
    }

    public void setM13(double m13) {
        this.m13 = m13;
    }

    public double getM14() {
        return m14;
    }

    public void setM14(double m14) {
        this.m14 = m14;
    }

    public double getM21() {
        return m21;
    }

    public void setM21(double m21) {
        this.m21 = m21;
    }

    public double getM22() {
        return m22;
    }

    public void setM22(double m22) {
        this.m22 = m22;
    }

    public double getM23() {
        return m23;
    }

    public void setM23(double m23) {
        this.m23 = m23;
    }

    public double getM24() {
        return m24;
    }

    public void setM24(double m24) {
        this.m24 = m24;
    }

    public double getM31() {
        return m31;
    }

    public void setM31(double m31) {
        this.m31 = m31;
    }

    public double getM32() {
        return m32;
    }

    public void setM32(double m32) {
        this.m32 = m32;
    }

    public double getM33() {
        return m33;
    }

    public void setM33(double m33) {
        this.m33 = m33;
    }

    public double getM34() {
        return m34;
    }

    public void setM34(double m34) {
        this.m34 = m34;
    }

    public double getM41() {
        return m41;
    }

    public void setM41(double m41) {
        this.m41 = m41;
    }

    public double getM42() {
        return m42;
    }

    public void setM42(double m42) {
        this.m42 = m42;
    }

    public double getM43() {
        return m43;
    }

    public void setM43(double m43) {
        this.m43 = m43;
    }

    public double getM44() {
        return m44;
    }

    public void setM44(double m44) {
        this.m44 = m44;
    }

    @Override
    public String toString() {
        return  m11 + " " +
                m12 + " " +
                m13 + " " +
                m14 + "\n" +
                m21 + " " +
                m22 + " " +
                m23 + " " +
                m24 +"\n" +
                m31 + " " +
                m32 + " " +
                m33 + " " +
                m34 + "\n" +
                m41 + " " +
                m42 + " " +
                m43 + " " +
                m44;
    }

    private double m11;
    private double m12;
    private double m13;
    private double m14;
    private double m21;
    private double m22;
    private double m23;
    private double m24;
    private double m31;
    private double m32;
    private double m33;
    private double m34;
    private double m41;
    private double m42;
    private double m43;
    private double m44;

    private final double[][] cofactors = new double[4][4];

}
