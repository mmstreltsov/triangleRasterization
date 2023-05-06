package RasterTest.CameraAnimation;

import RasterTest.State.Animation.Animate;
import RasterTest.State.Camera;

/**
 * Класс служит для анимации камеры. Задает кнопки и действия на них
 */
public class CameraAnim {

    /**
     * Поддерживает список доступных кнопок
     * @return массив чаров
     */
    public static char[] getChars() {
        return new char[]{'w', 'a', 's', 'd', 'q', 'e', ',', 'l', '.', ';', '/', '\'', ' '};
    }

    /**
     * Выполнение действия в зависимости от переданного значения char
     * @param c нажатая клавиша
     */
    public static void testing(char c) {
        switch (c) {
            case 'w' -> Animate.animCameraTransl(0, 0, 1);
            case 'a' -> Animate.animCameraTransl(-1, 0, 0);
            case 's' -> Animate.animCameraTransl(0, 0, -1);
            case 'd' -> Animate.animCameraTransl(1, 0, 0);
            case 'q' -> Animate.animCameraTransl(0, 1, 0);
            case 'e' -> Animate.animCameraTransl(0, -1, 0);
            case ',' -> Animate.animCameraRotate(-1, 0, 0);
            case 'l' -> Animate.animCameraRotate(1, 0, 0);
            case '.' -> Animate.animCameraRotate(0, -1, 0);
            case ';' -> Animate.animCameraRotate(0, 1, 0);
            case '/' -> Animate.animCameraRotate(0, 0, -1);
            case '\'' -> Animate.animCameraRotate(0, 0, 1);
            case ' ' -> Camera.fabric().makeDefaultCamera();
            default -> {
            }
        }
    }
}
