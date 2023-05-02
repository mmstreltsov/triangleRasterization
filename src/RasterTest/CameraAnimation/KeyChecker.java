package RasterTest.CameraAnimation;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class KeyChecker {
    public static void init(InputMap inputMap, ActionMap actionMap) {
        char[] chars = CameraAnimTest.getChars();
        for (char c : chars) {
            inputMap.put(KeyStroke.getKeyStroke(c), String.valueOf(c));
            actionMap.put(String.valueOf(c), new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    CameraAnimTest.testing(c);
                }
            });
        }
    }
}

