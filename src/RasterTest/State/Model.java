package RasterTest.State;


import RasterTest.State.Math.Triangle3D;

import java.util.ArrayList;
import java.util.List;

public class Model {


    public Model() {
        triangulation = new ArrayList<>();
    }

    public Model(Model other) {
        this.triangulation = other.triangulation;
    }

    private List<Triangle3D> triangulation;

    public void setTriangulation(List<Triangle3D> triangulation) {
        this.triangulation = triangulation;
    }

    public List<Triangle3D> getTriangulation() {
        return triangulation;
    }
}
