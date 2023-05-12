package ru.hse.project.RasterTest;

import ru.hse.project.RasterTest.Triangle;

public class BufferRender {
    public static final int resolutionX = 640;
    public static final int resolutionY = 480;
    private final int[] blackColorPixel = new int[]{0, 0, 0};
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
    public int[][][] clearBuffer(int[][][] buffer) {
        for (int x = 0; x < resolutionX; x++) {
            for (int y = 0; y < resolutionY; y++) {
                buffer[x][y] = blackColorPixel;
            }
        }
        return buffer;
    }

    public int[][][] cloneBuffer(int[][][] buffer, int[][][] bufferClone) {
        for (int x = 0; x < resolutionX; x++) {
            for (int y = 0; y < resolutionY; y++) {
                for (int k = 0; k < 3; k++) {
                    bufferClone[x][y][k] = buffer[x][y][k];
                }
            }
        }
        return bufferClone;
    }
}
