package RasterTest;

import RasterTest.CameraAnimation.KeyChecker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class WritableRasterTest extends JFrame {

    public static final int DEFAULT_WIDTH = PixelScreen.getResolutionX();
    public static final int DEFAULT_HEIGHT = PixelScreen.getResolutionY();
    public static final int DEFAULT_WINDOW_WIDTH = PixelScreen.getResolutionX();
    public static final int DEFAULT_WINDOW_HEIGHT = DEFAULT_HEIGHT + 70;
    private JLabel fps;
    private PixelScreen canvas;
    private final TriangleHelper helper = new TriangleHelper();

    public static void main(String[] args) {
        JFrame f = new WritableRasterTest();

        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setVisible(true);
    }

    public WritableRasterTest() {
        setTitle("WritableRasterTest");
        setSize(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
        canvas = new PixelScreen();
        add(canvas, BorderLayout.CENTER);
        JPanel panel = new JPanel();
//        panel.setDoubleBuffered(true);
        ButtonGroup group = new ButtonGroup();
        JRadioButton startButton = new JRadioButton("Start", false);
        JRadioButton startAnimation = new JRadioButton("Start Animation", false);
        JRadioButton stopAnimation = new JRadioButton("Stop Animation", false);
        fps = new JLabel("FPS: 0");
        panel.add(fps);
        panel.add(startButton);
        panel.add(startAnimation);
        panel.add(stopAnimation);

        /// For animation
        int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
        InputMap inputMap = panel.getInputMap(condition);
        ActionMap actionMap = panel.getActionMap();

        KeyChecker.init(inputMap, actionMap);
        /// For animation


        group.add(startButton);
        group.add(startAnimation);
        group.add(stopAnimation);

        final BackgroundWorker backgroundWorker = new BackgroundWorker(canvas);
        final Thread background = new Thread(backgroundWorker);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!background.isAlive()) {
                    background.setDaemon(true);
                    background.start();
                }
            }
        });
        startAnimation.addActionListener(e -> Animation.setIsAnimateTrue());
        stopAnimation.addActionListener(e -> Animation.setIsAnimateFalse());


        add(panel, BorderLayout.NORTH);
    }

    class BackgroundWorker implements  Runnable {
        private final int[] blueColorPixel = new int[]{155, 0, 255};
        private final int[] blackColorPixel = new int[]{0, 0, 0};
        //private int[] blueColorArray;
        private final PixelScreen screen;

        BackgroundWorker(PixelScreen screen) {
            this.screen = screen;
        }

        @Override
        public void run() {
            try {
                draw();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


        private void draw() throws InterruptedException {
            Initialization initialization = new Initialization();
            Render render = initialization.getRender();
            int[] pixelColor;


            while(true) {
                List<Triangle> triangles = render.render();

                for (Triangle triangle : triangles) {
                    List<Triangle> clipping_triangles = helper.triangleProcessing(triangle, screen.getWidth(), screen.getHeight());
                    if (clipping_triangles != null) {
                        pixelColor = triangle.getPixelColor();
                        for (Triangle i : clipping_triangles) {
                            screen.drawTriangle(i, pixelColor);
                        }
                    }
                }
                screen.drawCanvas();
                canvas.Clear();
            }
        }
    }
}