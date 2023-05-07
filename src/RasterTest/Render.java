package RasterTest;

import RasterTest.State.Model;
import RasterTest.State.RenderObject;
import RasterTest.State.RenderState;
import RasterTest.State.Scene;

import java.util.ArrayList;
import java.util.List;

/**
 * Обертка над RenderState с возможностью рендерить сцену
 */
public class Render {

    /**
     * Все объекты лежат здесь
     */
    private final RenderState renderState = new RenderState();

    /**
     * Функция вставляет в коллекцию объектов для рендера
     * @param renderObject объект класса RenderObject
     */
    public void insert(RenderObject renderObject) {
        renderState.getRenderObjects().add(renderObject);
    }

    /**
     * Функция генерирует RenderObject и вставляет в коллекцию объектов для рендера
     * @param scene Сцена
     * @param model Модель
     */
    public void insert(Scene scene, Model model) {
        renderState.getRenderObjects().add(new RenderObject(scene, model));
    }

    /**
     * Рендер кадра. Вызывает init для всех объектов коллекции. Собирает Треугольники для канваса
     * @return коллекцию треугольников
     */
    public List<Triangle> render() {
        List<Triangle> ret = new ArrayList<>();
        renderState.getRenderObjects().forEach(it -> {

            it.init();
            it.getTriangles().forEach(triangle2D -> {
                ret.add(Converter.convert(triangle2D));
            });
        });
        return ret;
    }

    public RenderState getRenderState() {
        return renderState;
    }
}
