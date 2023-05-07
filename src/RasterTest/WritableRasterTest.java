package RasterTest;

import RasterTest.CameraAnimation.KeyChecker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class WritableRasterTest extends JFrame {

    public static final int DEFAULT_WIDTH = PixelScreen.getResolutionX();
    public static final int DEFAULT_HEIGHT = PixelScreen.getResolutionY();
    public static final int DEFAULT_WINDOW_WIDTH = PixelScreen.getResolutionX();
    public static final int DEFAULT_WINDOW_HEIGHT = DEFAULT_HEIGHT + 70;
    private JLabel fps;
    private static PixelScreen canvas;
    private final TriangleHelper helper = new TriangleHelper();
    private static boolean bufferReady = false;
    private static boolean drawReady = true;

    private static long lastFpsCheck = 0;
    private static int currentFps = 0;
    private static int totalFrames = 0;


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
        fps = new JLabel("FPS:" );
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

        BlockingQueue<int[][][]> queue = new LinkedBlockingQueue<>(2);
        final Thread buffer = new Thread(new GetNextBuffer(queue));
        final Thread background = new Thread(new BackgroundWorker(queue));
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!background.isAlive() && !buffer.isAlive()) {
                    background.start();
                    buffer.start();
                }
            }
        });
        startAnimation.addActionListener(e -> Animation.setIsAnimateTrue());
        stopAnimation.addActionListener(e -> Animation.setIsAnimateFalse());


        add(panel, BorderLayout.NORTH);
    }



    class GetNextBuffer implements Runnable {
        private final BlockingQueue<int[][][]> queue;

        GetNextBuffer(BlockingQueue<int[][][]> queue) {
            this.queue = queue;
        }
        @Override
        public void run() {
            try {
                calculateBuffer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        public void calculateBuffer() throws InterruptedException {
            Initialization initialization = new Initialization();
            Render render = initialization.getRender();
            PixelScreen screen = new PixelScreen();
            int[] pixelColor;
            while(true) {
                int [][][] buffer = new int[DEFAULT_WIDTH][DEFAULT_HEIGHT][3];
                List<Triangle> triangles = render.render();
                for (Triangle triangle : triangles) {
                    List<Triangle> clipping_triangles = List.of(triangle); // delete this
//                    List<Triangle> clipping_triangles = helper.triangleProcessing(triangle, screen.getWidth(), screen.getHeight());
                    if (clipping_triangles != null) {
                        pixelColor = triangle.getPixelColor();
                        for (Triangle i : clipping_triangles) {
                            buffer = screen.addTriangleToBuffer(i, pixelColor, buffer);
                        }
                    }
                }
                queue.put(buffer);
            }
        }
    }

    static class BackgroundWorker implements  Runnable {
        private final BlockingQueue<int[][][]> queue;
        BackgroundWorker(BlockingQueue<int[][][]> queue) {
            this.queue = queue;
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
            int [][][] buffer;
            while (true) {
                buffer = queue.take();
                canvas.addBufferToCanvas(buffer);
                canvas.drawCanvas();
            }
        }
    }
}