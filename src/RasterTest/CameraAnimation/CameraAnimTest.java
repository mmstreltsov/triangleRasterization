package RasterTest.CameraAnimation;

import RasterTest.State.Animation.ToRotate;
import RasterTest.State.Animation.ToTranslate;
import RasterTest.State.Camera;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class CameraAnimTest {


    public static void testing(char c) {
        switch (c) {
            case 'w' -> ToTranslate.translate(Camera.fabric().getOffset(), 0, 0, 1);
            case 'a' -> ToTranslate.translate(Camera.fabric().getOffset(), -1, 0, 0);
            case 's' -> ToTranslate.translate(Camera.fabric().getOffset(), 0, 0, -1);
            case 'd' -> ToTranslate.translate(Camera.fabric().getOffset(), 1, 0, 0);
            case ',' -> ToRotate.rotate(Camera.fabric().getRotation(), -1, 0, 0);
            case 'l' -> ToRotate.rotate(Camera.fabric().getRotation(), 1, 0, 0);
            case '.' -> ToRotate.rotate(Camera.fabric().getRotation(), 0, 1, 0);
            case ';' -> ToRotate.rotate(Camera.fabric().getRotation(), 0, -1, 0);
            case '/' -> ToRotate.rotate(Camera.fabric().getRotation(), 0, 0, 1);
            case '\'' -> ToRotate.rotate(Camera.fabric().getRotation(), 0, 0, -1);
            default -> {
            }
        }
    }
}
