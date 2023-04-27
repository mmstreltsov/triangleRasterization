package RasterTest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import javax.swing.JFrame;

/**
 * Kudryashov Evgeny 271 SE
 */

public class PixelScreen extends Canvas{
    private final int resolutionX = 1920;
    private final int resolutionY = 1080;
    private final BufferedImage bi;
    private final WritableRaster wr;

    public PixelScreen() {
        this.setBackground(Color.WHITE);
        this.bi = new BufferedImage(resolutionX,resolutionY,BufferedImage.TYPE_INT_RGB);
        this.wr = this.bi.getRaster();
    }

    public void fillPixels(int[] color) {
        for(int x = 0; x < this.getWidth(); x++) {
            for(int y = 0; y < this.getHeight(); y++) {
                this.wr.setPixel(x, y, color);
            }
        }
        this.paint(this.getGraphics());
    }

    public void drawLine(int x1, int x2, int y, int[] color) {
        for (int x = x1; x < x2; x++) {
            this.wr.setPixel(x, y, color);
        }
    }

    public void Clear() {
        for(int x = 0; x < 640; x++) {
            for(int y = 0; y < 480; y++) {
                this.wr.setPixel(x, y, new int[]{0, 0, 0});
            }
        }
    }

    public void drawTriangle(Triangle triangle, int[] color) {
        int[][] a = triangle.findCoordinates();
        for (int[] ints : a) {
            drawLine(ints[0], ints[1], ints[2], color);
        }
        this.paint(this.getGraphics());
    }

    public void paint(Graphics g) {
        g.drawImage(this.bi, 0, 0, null);
    }

    public int getResolutionX() {
        return resolutionX;
    }

    public int getResolutionY() {
        return resolutionY;
    }

}