package RasterTest;

import RasterTest.State.Model;
import RasterTest.State.RenderObject;
import RasterTest.State.RenderState;
import RasterTest.State.Scene;

import java.util.ArrayList;
import java.util.List;

public class Render {


    private final RenderState renderState = new RenderState();

    public void insert(RenderObject renderObject) {
        renderState.getRenderObjects().add(renderObject);
    }


    /**
     * Deprecated
     */
    public void insert(Scene scene, Model model) {
        renderState.getRenderObjects().add(new RenderObject(scene, model));
    }


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
