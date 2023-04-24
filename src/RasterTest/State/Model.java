package RasterTest.State;


import RasterTest.State.Math.Coord3D;
import RasterTest.State.Math.Triangle3D;

import java.util.ArrayList;
import java.util.List;

public class Model {


    private List<Coord3D> Vertexes;
    private List<Triangle3D> triangulation;


    public List<Triangle3D> getTriangulation() {
        return new ArrayList<>();
    }
}
