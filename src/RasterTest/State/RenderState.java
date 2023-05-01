package RasterTest.State;

import java.util.ArrayList;
import java.util.List;

public class RenderState {
    public RenderState() {
        this.renderObjects = new ArrayList<>();
    }

    public List<RenderObject> getRenderObjects() {
        return renderObjects;
    }

    private final List<RenderObject> renderObjects;
}
