package RasterTest;

import RasterTest.State.Animation.ToTranslate;
import RasterTest.State.Camera;

import java.io.IOException;
import java.io.InputStreamReader;

public class CameraAnimTest {
    public static void testing() {
        try (InputStreamReader reader = new InputStreamReader(System.in);) {
            char str;
            while (true) {
                str = (char) reader.read();
                switch (str) {
                    case 'w':
                        ToTranslate.translate(Camera.fabric().getOffset(), 0, 0, 1);
                        break;
                    case 'a':
                        ToTranslate.translate(Camera.fabric().getOffset(),  -1, 0, 0);
                        break;
                    case 's':
                        ToTranslate.translate(Camera.fabric().getOffset(), 0, 0, -1);
                        break;
                    case 'd':
                        ToTranslate.translate(Camera.fabric().getOffset(), 1, 0, 0);
                        break;
                    default:
                        break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
