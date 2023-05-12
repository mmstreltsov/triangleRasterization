package ru.hse.project.RasterTest;

/**
 * Класс для допуска анимации фигур в этом мире. Либо все, либо никто
 */
public class Animation {
    /**
     * Выставить допускающий флаг
     */
    public static void setIsAnimateTrue() {
        Animation.isAnimate = true;
    }

    /**
     * Выставить запрещающий флаг
     */
    public static void setIsAnimateFalse() {
        Animation.isAnimate = false;
    }

    /**
     * Получить значение флага
     */
    public static boolean isIsAnimate() {
        return isAnimate;
    }

    /**
     * Флаг анимации. Делать любую анимацию фигур только в зависимости от флага
     */
    private static boolean isAnimate = false;
}
