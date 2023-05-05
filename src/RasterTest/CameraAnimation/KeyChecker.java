package RasterTest.CameraAnimation;

import javax.swing.*;
import java.awt.event.ActionEvent;


/**
 * Класс служит для инициализации действий в ответ на нажатие клавиши
 */
public class KeyChecker {


    /**
     * Для клавиш, заданных в CameraAnim в соответствие ставится действие, прописанное в CameraAnim
     * @param inputMap аттрибут JPanel. Коллекция вводных данных
     * @param actionMap аттрибут JPanel. Коллекция действий
     */
    public static void init(InputMap inputMap, ActionMap actionMap) {
        char[] chars = CameraAnim.getChars();
        for (char c : chars) {
            inputMap.put(KeyStroke.getKeyStroke(c), String.valueOf(c));
            actionMap.put(String.valueOf(c), new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    CameraAnim.testing(c);
                }
            });
        }
    }
}

