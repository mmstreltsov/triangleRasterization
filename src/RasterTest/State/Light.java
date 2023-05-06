package RasterTest.State;

import RasterTest.State.Math.Vector3D;

/**
 * Класс для инициализации света на сцене. Используется направленный свет.
 * Создается лишь 1 экземпляр класса
 */
public class Light {
    /**
     * Поле, хранящее единственный экземпляр
     */
    private static Light light;

    /**
     * Вектор-направление света
     */
    private Vector3D direction;

    /**
     * Фабричный метод для инициализации класса.
     * @return ссылку на единственный инстанс
     */
    public static Light fabric() {
        if (light == null) {
            light = new Light();
        }
        return light;
    }

    /**
     * Приватный дефолтный конструктор. Инициализация нулями
     */
    private Light() {
        direction = new Vector3D(0, 0, 1);
    }


    public Vector3D getDirection() {
        return direction;
    }

    public void setDirection(Vector3D direction) {
        this.direction = direction;
    }
}
