package RasterTest.State;

import RasterTest.State.Math.Coord3D;
import RasterTest.State.Math.Plane;
import RasterTest.State.Math.Triangle3D;
import RasterTest.State.Math.Vector3D;

import java.util.ArrayList;
import java.util.List;

public class Clipping {
    private final List<Plane> planes;
    private List<Triangle3D> listTriangle3D;

    private Coord3D insidePoint;
    private final List<Triangle3D> listAfterClipping = new ArrayList<>();


    public Clipping(Camera camera, PointView pointView, List<Triangle3D> listTriangle3D) {
        this.planes = generatePlanes(camera, pointView);
        this.listTriangle3D = listTriangle3D;
    }

    private List<Plane> generatePlanes(Camera camera, PointView pointView) {

        Coord3D point = camera.getOffset().getTransl().toPoint();

        Vector3D eye = camera.getActualCenter().normalized();
        Vector3D up = camera.getActualUp().normalized();
        Vector3D right = Vector3D.crossProduct(eye, up).multiplyOnScalar(-1).normalized();

        double heightHalf = pointView.getvHeight() / 2;
        double widthHalf = pointView.getvWidth() / 2;

        double d = pointView.getD();

        Coord3D viewCenter = point.additional(eye.multiplyOnScalar(d)).toPoint();

        insidePoint = viewCenter;

        Coord3D leftUp = viewCenter.additional(up.multiplyOnScalar(heightHalf))
                .additional(right.multiplyOnScalar(-widthHalf)).toPoint();
        Coord3D rightUp = viewCenter.additional(up.multiplyOnScalar(heightHalf))
                .additional(right.multiplyOnScalar(widthHalf)).toPoint();
        Coord3D leftDown = viewCenter.additional(up.multiplyOnScalar(-heightHalf))
                .additional(right.multiplyOnScalar(-widthHalf)).toPoint();
        Coord3D rightDown = viewCenter.additional(up.multiplyOnScalar(-heightHalf))
                .additional(right.multiplyOnScalar(widthHalf)).toPoint();


        List<Plane> ret = new ArrayList<>();
        ret.add(new Plane(leftUp, rightUp, point));
        ret.add(new Plane(rightUp, rightDown, point));
        ret.add(new Plane(rightDown, leftDown, point));
        ret.add(new Plane(leftDown, leftUp, point));

        ret.add(new Plane(leftUp, rightUp, rightDown));

        return ret;
    }

    public void init() {
        for (Plane plane : planes) {
            List<Triangle3D> newList = new ArrayList<>();
            Vector3D normal = plane.getNormal();

            Coord3D insidePointProj = plane.findPointProjection(this.insidePoint);
            boolean correctSideOfPlane = Vector3D.cosAngleBetweenVectors(normal, insidePoint.subtracting(insidePointProj).toVector()) > 0;

            for (Triangle3D it : listTriangle3D) {

                boolean[] isBadGuy = new boolean[3];
                Coord3D[] points = new Coord3D[]{it.getVertex1(), it.getVertex2(), it.getVertex3()};
                Coord3D[] projections = new Coord3D[3];

                for (int i = 0; i < points.length; ++i) {
                    projections[i] = plane.findPointProjection(points[i]);
                }

                int badGuys = 0;
                for (int i = 0; i < points.length; ++i) {
                    Vector3D leg = points[i].subtracting(projections[i]).toVector();
                    double cos = Vector3D.cosAngleBetweenVectors(normal, leg);
                    if ((cos > 0) != correctSideOfPlane && Math.abs(cos) > 1e-7) {
                        isBadGuy[i] = true;
                        badGuys++;
                    }
                }

                reordering(isBadGuy, points, 0, 2);
                Coord3D p1 = points[0];
                Coord3D p2 = points[1];
                Coord3D p3 = points[2];

                if (badGuys == 0) {
                    newList.add(it);
                } else if (badGuys == 3) {
                    //skip this guy
                    continue;
                } else if (badGuys == 1) {
                    //p3 is bad guy

                    Coord3D p11 = plane.intersectionWithLine(p3.subtracting(p1).toVector(), p1);
                    Coord3D p12 = plane.intersectionWithLine(p3.subtracting(p2).toVector(), p2);

                    newList.add(new Triangle3D(p1, p2, p12));
                    newList.add(new Triangle3D(p1, p11, p12));
                } else if (badGuys == 2) {
                    //p2 and p3 is bad

                    Coord3D p11 = plane.intersectionWithLine(p1.subtracting(p3).toVector(), p1);
                    Coord3D p12 = plane.intersectionWithLine(p1.subtracting(p2).toVector(), p1);

                    newList.add(new Triangle3D(p1, p11, p12));
                }
            }

            listTriangle3D = newList;
        }
        listAfterClipping.addAll(listTriangle3D);
    }

    private void reordering(boolean[] isBad, Coord3D[] points, int first, int last) {
        if (first >= last) {
            return;
        }

        if (isBad[first]) {
            Coord3D tmp = points[first];
            points[first] = points[last];
            points[last] = tmp;

            isBad[first] = isBad[last];
            isBad[last] = true;
            reordering(isBad, points, first, last - 1);
        } else {
            reordering(isBad, points, first + 1, last);
        }
    }

    public List<Triangle3D> getListAfterClipping() {
        return listAfterClipping;
    }

}
