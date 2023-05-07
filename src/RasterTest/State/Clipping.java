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

//        System.out.println(viewCenter);

        Coord3D leftUp = viewCenter.additional(up.multiplyOnScalar(heightHalf))
                .additional(right.multiplyOnScalar(-widthHalf)).toPoint();
        Coord3D rightUp = viewCenter.additional(up.multiplyOnScalar(heightHalf))
                .additional(right.multiplyOnScalar(widthHalf)).toPoint();
        Coord3D leftDown = viewCenter.additional(up.multiplyOnScalar(-heightHalf))
                .additional(right.multiplyOnScalar(-widthHalf)).toPoint();
        Coord3D rightDown = viewCenter.additional(up.multiplyOnScalar(-heightHalf))
                .additional(right.multiplyOnScalar(widthHalf)).toPoint();

//        System.out.println(leftUp);
//        System.out.println(rightUp);
//        System.out.println(rightDown);
//        System.out.println(leftDown);


        List<Plane> ret = new ArrayList<>();
        ret.add(new Plane(leftUp, rightUp, point));
        ret.add(new Plane(rightUp, rightDown, point));
        ret.add(new Plane(rightDown, leftDown, point));
        ret.add(new Plane(leftDown, leftUp, point));

        ret.add(new Plane(leftUp, rightUp, rightDown));

        ret.forEach(System.out::println);
        System.out.println();

        return ret;
    }

    public void init() {
        // camera-point always on plane
        Coord3D planePoint = Camera.fabric().getOffset().getTransl().toPoint();

        for (Plane plane : planes) {
            List<Triangle3D> newList = new ArrayList<>();

            Vector3D normal = plane.getNormal();
            for (Triangle3D it : listTriangle3D) {

                Coord3D p1 = it.getVertex1();
                Vector3D v1 = p1.subtracting(planePoint).toVector();

                Coord3D p2 = it.getVertex2();
                Vector3D v2 = p2.subtracting(planePoint).toVector();

                Coord3D p3 = it.getVertex3();
                Vector3D v3 = p3.subtracting(planePoint).toVector();

                int badGuys = 0;
                badGuys = Vector3D.cosAngleBetweenVectors(normal, v1) > 0 ? badGuys + 1 : badGuys;
                badGuys = Vector3D.cosAngleBetweenVectors(normal, v2) > 0 ? badGuys + 1 : badGuys;
                badGuys = Vector3D.cosAngleBetweenVectors(normal, v3) > 0 ? badGuys + 1 : badGuys;

                if (badGuys > 0) {
                    System.out.println(badGuys);
                }
                if (badGuys == 0) {
                    newList.add(it);
                } else if (badGuys == 3) {
                    //skip this guy
                    continue;
                } else if (badGuys == 1) {
                    //reordering
                    if (Vector3D.cosAngleBetweenVectors(normal, v1) > 0) {
                        p1 = p3;
                        p3 = it.getVertex1();
                    } else if (Vector3D.cosAngleBetweenVectors(normal, v2) > 0) {
                        p2 = p3;
                        p3 = it.getVertex2();
                    }

                    Coord3D p11 = plane.intersectionWithLine(p3.subtracting(p1).toVector(), p1);
                    Coord3D p12 = plane.intersectionWithLine(p3.subtracting(p2).toVector(), p2);

                    newList.add(new Triangle3D(p1, p2, p12));
                    newList.add(new Triangle3D(p1, p11, p12));
                } else if (badGuys == 2) {
                    if (Vector3D.cosAngleBetweenVectors(normal, v2) <= 0) {
                        p1 = p2;
                        p2 = it.getVertex1();
                    } else if (Vector3D.cosAngleBetweenVectors(normal, v3) <= 0) {
                        p1 = p3;
                        p3 = it.getVertex1();
                    }

                    Coord3D p11 = plane.intersectionWithLine(p1.subtracting(p3).toVector(), p1);
                    Coord3D p12 = plane.intersectionWithLine(p1.subtracting(p2).toVector(), p1);

                    newList.add(new Triangle3D(p1, p11, p12));
                }
            }

            listTriangle3D = newList;
        }
        listAfterClipping.addAll(listTriangle3D);
    }

    public List<Triangle3D> getListAfterClipping() {
        return listAfterClipping;
    }
}
