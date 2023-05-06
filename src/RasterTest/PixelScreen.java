package RasterTest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.Arrays;

public class PixelScreen extends Canvas {
    public static final int resolutionX = 640;
    public static final int resolutionY = 480;
    private final BufferedImage bi;
    private final WritableRaster wr;
    private final int[] blackColorPixel = new int[]{0, 0, 0};



    public PixelScreen() {
        this.bi = new BufferedImage(resolutionX, resolutionY, BufferedImage.TYPE_INT_RGB);
        this.wr = this.bi.getRaster();
        this.setSize(new Dimension(resolutionX, resolutionY));
    }


    public void ClearBuffer(int[][][] buffer) {
        for(int x = 0; x < resolutionX; x++) {
            for (int y = 0; y < resolutionY; y++) {
                buffer[x][y] = blackColorPixel;
            }
        }
    }

//    public void drawTriangle(Triangle triangle, int[] color) {
//        int[][] a = triangle.findCoordinates();
//        for (int[] ints : a) {
//            drawLine(ints[0], ints[1], ints[2], color);
//        }
//    }

//    public void drawLine(int x1, int x2, int y, int[] color) {
//        for (int x = x1; x < x2; x++) {
//            this.wr.setPixel(x, y, color);
//        }
//    }

    public int[][][] addTriangleToBuffer(Triangle triangle, int[] color, int[][][] buffer) {
        int[][] a = triangle.findCoordinates();
        for (int[] ints : a) {
            buffer = addLineToBuffer(ints[0], ints[1], ints[2], color, buffer);
        }
        return buffer;
    }

    public int[][][] addLineToBuffer(int x1, int x2, int y, int[] color, int[][][] buffer) {
        for (int x = x1; x < x2; x++) {
            buffer[x][y] = color;
        }
        return buffer;
    }

    public void addBufferToCanvas(int[][][] buffer) {
        for(int x = 0; x < resolutionX; x++) {
            for(int y = 0; y < resolutionY; y++) {
                this.wr.setPixel(x, y, buffer[x][y]);
            }
        }
    }


    public void drawCanvas() {
        this.paint(this.getGraphics());
    }

    public void paint(Graphics g) {
        g.drawImage(this.bi, 0, 0, null);
    }

    public static int getResolutionX() {
        return resolutionX;
    }

    public static int getResolutionY() {
        return resolutionY;
    }

}