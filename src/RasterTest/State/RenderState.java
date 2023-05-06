package RasterTest.State;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для коллекции RenderObject - держит много объектов с их правилами преобразования
 */
public class RenderState {
    /**
     * Поле, хранящее необходимую коллекцию
     */
    private final List<RenderObject> renderObjects;

    /**
     * Дефолтный конструктор
     */
    public RenderState() {
        this.renderObjects = new ArrayList<>();
    }

    public List<RenderObject> getRenderObjects() {
        return renderObjects;
    }

}
