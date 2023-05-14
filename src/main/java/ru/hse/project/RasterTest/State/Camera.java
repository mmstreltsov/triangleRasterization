package ru.hse.project.RasterTest.State;

import ru.hse.project.RasterTest.State.Animation.Rotation;
import ru.hse.project.RasterTest.State.Animation.Translation;
import ru.hse.project.RasterTest.State.Math.BasisChange;
import ru.hse.project.RasterTest.State.Math.Matrix4x4;
import ru.hse.project.RasterTest.State.Math.Vector3D;


/**
 * Класс для инициализации камеры с помощью идей из GluLookAt.
 * Создается лишь 1 экземпляр класса
 */
public class Camera implements Transformation {
    /**
     * Поле, хранящее единственный экземпляр
     */
    private static Camera camera;
    /**
     * Класс Перемещения. Определяет местоположения точки камеры
     */
    private Translation offset;

    /**
     * Класс Вращения. Определяет вращение аттрибутов камеры
     */
    private Rotation rotation;
    /**
     * Вектор, указывающий точку, куда смотрит камера. "Точка взгляда"
     */
    private Vector3D center;
    /**
     * Вектор, указывающий, где "вверх" камеры
     */
    private Vector3D up;

    /**
     * Фабричный метод для инициализации класса.
     * @return ссылку на единственный инстанс
     */
    public static Camera fabric() {
        if (camera == null) {
            camera = new Camera();
        }
        return camera;
    }

    /**
     * Приватный дефолтный конструктор. Делегирует инициализацию другому методу (см. makeDefaultCamera)
     */
    private Camera() {
        makeDefaultCamera();
    }
    /**
     * Применяет вращение на аттрибут камеры "Точка взгляда"
     * @return Новый вектор
     */
    private Vector3D rotateCenter(){
        return rotation.rotating().multiplyOnHomo(this.center).toVector();
    }

    /**
     * Применяет вращение на аттрибут камеры "Вверх"
     * @return Новый вектор
     */
    private Vector3D rotateUp() {
        return rotation.rotating().multiplyOnHomo(this.up).toVector();
    }

    /**
     * Инициализирует каждое поле дефолтным значением
     * (см. getDefault*, * in [Offset, Rotation, Center, Up])
     * Может вызываться снаружи.
     */
    public void makeDefaultCamera() {
        this.offset = getDefaultOffset();
        this.rotation = getDefaultRotation();
        this.center = getDefaultCenter();
        this.up = getDefaultUp();
    }

    /**
     * Дефолтная инициализация. Смещение (0, 0, -15), Флаг обратного отображения включен (true)
     */
    private Translation getDefaultOffset() {
        return new Translation(new Vector3D(0, 0, -15), true);
    }

    /**
     * Дефолтная инициализация. Вращение (0, 0, 0)
     */
    private Rotation getDefaultRotation() {
        return new Rotation(0, 0, 0);
    }

    /**
     * Дефолтная инициализация. Вектор (0, 0, 1)
     */
    private Vector3D getDefaultCenter() {
        return new Vector3D(0, 0, 1);
    }

    /**
     * Дефолтная инициализация. Вектор (0, 1, 0)
     */
    private Vector3D getDefaultUp() {
        return new Vector3D(0, 1, 0);
    }


    /**
     * Генерируется матрица перевода координат в поле зрения камеры.
     * 1 этап: смещение камеры
     * 2 этап: строится новый базис по аттрибутам камеры. Генерируется матрица перехода в новый базис
     * @return Матрица преобразования
     */
    @Override
    public Matrix4x4 transformation() {
        Vector3D center = getActualCenter();
        Vector3D up = getActualUp();

        Vector3D right = Vector3D.crossProduct(center, up).multiplyOnScalar(-1).normalized();

        Matrix4x4 matrix = BasisChange.matrix(right, up, center);

        matrix = matrix.multiplyOnMatrix(offset.translating());
        return matrix;
    }

    public Translation getOffset() {
        return offset;
    }

    public void setOffset(Translation offset) {
        this.offset = new Translation(offset.getTransl(), true);
    }

    public Rotation getRotation() {
        return rotation;
    }

    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }

    public Vector3D getActualCenter() {
        Vector3D center = rotateCenter();
        return center.normalized();
    }

    public Vector3D getActualUp() {
        Vector3D up = rotateUp();
        return up.normalized();
    }

}
