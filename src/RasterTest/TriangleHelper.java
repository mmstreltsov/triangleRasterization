package RasterTest;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TriangleHelper {


    public List<Point> orderVertices(List<Point> vertices) {
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

    public boolean[] pointInRectangle(Triangle triangle, int canvas_width, int canvas_height) {
        List<Point> points = triangle.getTrianglePoints();
        Point r1 = new Point(0, 0);
        Point r2 = new Point(0, canvas_height);
        Point r3 = new Point(canvas_width, canvas_height);
        Point r4 = new Point(canvas_width, 0);
        boolean[] contains = new boolean[3];
        int i = 0;
        for (Point p : points) {
            if (vectorProduct(r1, r4, p) < 0) {
                contains[i] = false;
            } else if (vectorProduct(r4, r3, p) < 0) {
                contains[i] = false;
            } else if (vectorProduct(r3, r2, p) < 0) {
                contains[i] = false;
            } else contains[i] = vectorProduct(r2, r1, p) >= 0;
            i++;
        }
        return contains;
    }

    private static int vectorProduct(Point a, Point b, Point c) {
        return (b.x - a.x) * (c.y - a.y) - (c.x - a.x) * (b.y - a.y);
    }

    public List<Point> addTrianglePoints (Triangle triangle, boolean[] triangle_points_status, List<Point> points) {
        List<Point> triangle_points = triangle.getTrianglePoints();
        for (int i = 0; i < 3; i++) {
            if (triangle_points_status[i] && !points.contains(triangle_points.get(i))) {
                points.add(triangle_points.get(i));
            }
        }
        return points;
    }
}
