package RasterTest;

public class Triangle {
    private int x1, y1, x2, y2, x3, y3;
    public Triangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }
    public void normalize() {
        if (y1 > y2) {
            int tmp = y1;
            y1 = y2;
            y2 = tmp;
            tmp = x1;
            x1 = x2;
            x2 = tmp;
        }
        if (y1 > y3) {
            int tmp = y1;
            y1 = y3;
            y3 = tmp;
            tmp = x1;
            x1 = x3;
            x3 = tmp;
        }
        if (y2 > y3) {
            int tmp = y2;
            y2 = y3;
            y3 = tmp;
            tmp = x2;
            x2 = x3;
            x3 = tmp;
        }
    }
    public int[][] findCoordinates() {
        int [][] a = new int[y3 - y1][3];
        int x1_cross, x2_cross;
        int i = 0;
        for (int y = y1; y < y2; y++) {
            x1_cross = x1 + (x2 - x1) * (y - y1) / (y2 - y1);           //по подобию треугольников
            x2_cross = x1 + (x3 - x1) * (y - y1) / (y3 - y1);
            if (x1_cross < x2_cross) {
                a[i][0] = x1_cross;
                a[i][1] = x2_cross;
            } else {
                a[i][0] = x2_cross;
                a[i][1] = x1_cross;
            }
            a[i][2] = y;
            i++;
        }
        for (int y = y2; y < y3; y++) {
            x1_cross = x2 + (x3 - x2) * (y - y2) / (y3 - y2);           //по подобию треугольников
            x2_cross = x1 + (x3 - x1) * (y - y1) / (y3 - y1);
            if (x1_cross < x2_cross) {
                a[i][0] = x1_cross;
                a[i][1] = x2_cross;
            } else {
                a[i][0] = x2_cross;
                a[i][1] = x1_cross;
            }
            a[i][2] = y;
            i++;
        }
        return a;
    }

    @Override
    public String toString() {
        return "(" + x1 + ", " + y1 + ") , (" + x2 + ", " + y2 + "), (" + x3 + ", " + y3 + ")";
    }
}
