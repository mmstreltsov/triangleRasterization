package RasterTest.State;

import RasterTest.State.Animation.Animate;
import RasterTest.State.Animation.Rotation;
import RasterTest.State.Animation.Scale;
import RasterTest.State.Animation.Translation;
import RasterTest.State.Math.Matrix4x4;

/**
 * Класс для хранения правил преобразования модели в глобальную систему координат
 */
public class ModelInstance implements Transformation {
    /**
     * Растяжение
     */
    private Scale scale;
    /**
     * Вращение
     */
    private Rotation rotation;
    /**
     * Перемещение
     */
    private Translation translation;

    /**
     * Конструктор от параметров
     */
    public ModelInstance(Scale scale, Rotation rotation, Translation translation) {
        this.scale = scale;
        this.rotation = rotation;
        this.translation = translation;
    }

    /**
     * Дефолтный конструктор. Все классы инициализируются дефолтно
     */
    public ModelInstance() {
        this.scale = new Scale();
        this.rotation = new Rotation();
        this.translation = new Translation();
    }

    /**
     * Генерация матрицы от трех параметров в порядке: M_trans * M_rot * M_scale
     * @return Матрица
     */
    @Override
    public Matrix4x4 transformation() {
        Matrix4x4 matrix = new Matrix4x4(1.);
        matrix = matrix.multiplyOnMatrix(translation.translating());
        matrix = matrix.multiplyOnMatrix(rotation.rotating());
        matrix = matrix.multiplyOnMatrix(scale.scaling());
        return matrix;
    }

    /**
     * Метод для применения шага анимации
     * @param other Класс с шагом анимации
     */
    public void animationStep(AnimateModelInstance other) {
        Animate.animObjectScale(this, other.getScale().getScale());
        Animate.animObjectRotate(this, other.getRotation().getRotation());
        Animate.animObjectTrans(this, other.getTranslation().getTransl());
    }

    public Scale getScale() {
        return scale;
    }

    public void setScale(Scale scale) {
        this.scale = scale;
    }

    public Rotation getRotation() {
        return rotation;
    }

    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }

    public Translation getTranslation() {
        return translation;
    }

    public void setTranslation(Translation translation) {
        this.translation = translation;
    }
}
