package RasterTest.State;

import RasterTest.State.Math.Matrix4x4;


/**
 * Функциональный интерфейс. Гарантирует наличие метода, генерирующего матрицу преобразования.
 */
public interface Transformation {
    Matrix4x4 transformation();
}
