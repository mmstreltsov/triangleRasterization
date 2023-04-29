package RasterTest;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TriangleHelper {


    public static List<Point> orderVertices(List<Point> vertices) {
        List<Point> orderedVertices = new ArrayList<>();
        int n = vertices.size();
        if (n < 3) {
            return orderedVertices;
        }

        Point leftmostVertex = vertices.get(0);
        for (int i = 1; i < n; i++) {
            Point vertex = vertices.get(i);
            if (vertex.x < leftmostVertex.x) {
                leftmostVertex = vertex;
            }
        }

        orderedVertices.add(leftmostVertex);
        vertices.remove(leftmostVertex);

        Point referencePoint = new Point(leftmostVertex.x + 1, leftmostVertex.y);
        Point finalLeftmostVertex = leftmostVertex;
        vertices.sort((a, b) -> {
            double angleA = angle(finalLeftmostVertex, referencePoint, a);
            double angleB = angle(finalLeftmostVertex, referencePoint, b);
            return Double.compare(angleA, angleB);
        });

        orderedVertices.addAll(vertices);
        return orderedVertices;
    }

    private static double angle(Point a, Point b, Point c) {
        double dx1 = a.x - b.x;
        double dy1 = a.y - b.y;
        double dx2 = c.x - b.x;
        double dy2 = c.y - b.y;
        return Math.atan2(dy1, dx1) - Math.atan2(dy2, dx2);
    }

    public List<Triangle> triangulateConvexPolygon(List<Point> points) {
        int n = points.size();
        if (n < 3) {
            return null;
        }
        Point firstPoint = points.get(0);
        List<Triangle> triangles = new ArrayList<>();
        for (int i = 1; i < n - 1; i++) {
            Point secondPoint = points.get(i);
            Point thirdPoint = points.get(i + 1);
            Triangle triangle = new Triangle(firstPoint.x, firstPoint.y, secondPoint.x, secondPoint.y, thirdPoint.x, thirdPoint.y);
            triangles.add(triangle);
        }
        return triangles;
    }

}
