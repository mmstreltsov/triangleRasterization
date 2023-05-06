package RasterTest;

import RasterTest.State.Math.Triangle2D;

/**
 * Класс для корректировки индексов для отображения содержимого на экране в канвас
 */
public class Converter {
    /**
     * Ширина Канваса - приходит Снаружи
     */
    private static final int DEFAULT_WIDTH = PixelScreen.resolutionX;
    /**
     * Высота Канваса - приходит Снаружи
     */
    private static final int DEFAULT_HEIGHT = PixelScreen.resolutionY;

    /**
     * Конвертор. Переходной мостик между классами Triangle2D и Triangle.
     * По определенным формулам изменяет индексы координат для корректного отображения в канвасе.
     * @param it Треугольник на плоскости
     * @return Треугольник в Канвасе
     */
    public static Triangle convert(Triangle2D it) {
        Triangle triangle = new Triangle((int) it.getVertex1().getX() + DEFAULT_WIDTH / 2, -(int) it.getVertex1().getY() + DEFAULT_HEIGHT / 2,
                (int) it.getVertex2().getX() + DEFAULT_WIDTH / 2, -(int) it.getVertex2().getY() + DEFAULT_HEIGHT / 2,
                (int) it.getVertex3().getX() + DEFAULT_WIDTH / 2, -(int) it.getVertex3().getY() + DEFAULT_HEIGHT / 2);
        triangle.setLightCoefficient(it.getLightCoefficient());
        return triangle;
    }
}
