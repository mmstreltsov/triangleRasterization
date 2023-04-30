package RasterTest.CameraAnimation;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyChecker extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
        CameraAnimTest.testing(e.getKeyChar());
    }
}