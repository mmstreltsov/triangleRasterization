package RasterTest;

import RasterTest.PixelScreen;
import RasterTest.State.Math.Triangle2D;
import RasterTest.Triangle;

public class Converter {

    private static final int DEFAULT_WIDTH = PixelScreen.resolutionX;
    private static final int DEFAULT_HEIGHT = PixelScreen.resolutionY;

    public static Triangle convert(Triangle2D it) {
        return new Triangle((int) it.getVertex1().getX() + DEFAULT_WIDTH / 2, (int) it.getVertex1().getY() + DEFAULT_HEIGHT / 2,
                (int) it.getVertex2().getX() + DEFAULT_WIDTH / 2, (int) it.getVertex2().getY() + DEFAULT_HEIGHT / 2,
                (int) it.getVertex3().getX() + DEFAULT_WIDTH / 2, (int) it.getVertex3().getY() + DEFAULT_HEIGHT / 2);
    }
}
