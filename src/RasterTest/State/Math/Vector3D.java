package RasterTest.State.Math;

import RasterTest.State.Math.Coord3D;

public class Vector3D extends Coord3D {
    public Vector3D(double x, double y, double z) {
        super(x, y, z);
    }

    public Vector3D normalized() {
        double val = Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2) + Math.pow(this.getZ(), 2);
        val = Math.sqrt(val);
        return new Vector3D(this.getX() / val, this.getY() / val, this.getZ() / val);
    }
}
