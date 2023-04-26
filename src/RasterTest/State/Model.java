package RasterTest.State;


import RasterTest.State.Math.Coord3D;
import RasterTest.State.Math.Triangle3D;

import java.util.List;

public class Model {


    public Model() {}
    public Model(Model other) {
        this.vertexes = other.vertexes;
        this.triangulation = other.triangulation;
    }
    private List<Coord3D> vertexes;

    private List<Triangle3D> triangulation;

    public void setTriangulation(List<Triangle3D> triangulation) {
        this.triangulation = triangulation;
    }
    public List<Triangle3D> getTriangulation() {
        return triangulation;
    }
}
