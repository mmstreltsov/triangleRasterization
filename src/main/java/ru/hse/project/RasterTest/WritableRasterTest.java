package ru.hse.project.RasterTest;

import ru.hse.project.RasterTest.CameraAnimation.KeyChecker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class WritableRasterTest extends JFrame {

    public static final int DEFAULT_WIDTH = PixelScreen.getResolutionX();
    public static final int DEFAULT_HEIGHT = PixelScreen.getResolutionY();
    public static final int DEFAULT_WINDOW_WIDTH = PixelScreen.getResolutionX();
    public static final int DEFAULT_WINDOW_HEIGHT = DEFAULT_HEIGHT + 70;
    private static JLabel fps;
    private static PixelScreen canvas;
    private static final TriangleHelper helper = new TriangleHelper();
    private static boolean isDoubleBuffering = false;

    private static boolean is2DClipping = false;
    private static boolean isStarted = false;

    public static boolean isIs2DClipping() {
        return is2DClipping;
    }


    public static void main(String[] args) {
        JFrame f = new WritableRasterTest();

        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setVisible(true);
    }

    public WritableRasterTest() {
        setTitle("Rasterization");
        setSize(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
        canvas = new PixelScreen();
        add(canvas, BorderLayout.CENTER);
        JPanel panel = new JPanel();
        ButtonGroup start = new ButtonGroup();
        JRadioButton startButton = new JRadioButton("Start");
        start.add(startButton);
        JCheckBox animationButton = new JCheckBox("Animation");
        fps = new JLabel("FPS:" );
        JMenuBar menu = new JMenuBar();
        JMenu settings = new JMenu("<html><p style='margin-top:2'>Settings");
        JCheckBoxMenuItem clippingButton  = new JCheckBoxMenuItem("2D clipping");
        JCheckBoxMenuItem doubleBufferingButton  = new JCheckBoxMenuItem("Double buffering");
        settings.add(clippingButton);
        settings.add(doubleBufferingButton);
        menu.add(settings);
        panel.add(fps);
        panel.add(startButton);
        panel.add(animationButton);
        panel.add(menu);


        /// For animation
        int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
        InputMap inputMap = panel.getInputMap(condition);
        ActionMap actionMap = panel.getActionMap();

        KeyChecker.init(inputMap, actionMap);
        /// For animation


        animationButton.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                Animation.setIsAnimateTrue();
            } else {
                Animation.setIsAnimateFalse();
            }
        });

        clippingButton.addItemListener(e -> is2DClipping = e.getStateChange() == ItemEvent.SELECTED);



        doubleBufferingButton.addItemListener(e -> {
            BlockingQueue<int[][][]> queue = new LinkedBlockingQueue<>(2);
            Thread buffer = new Thread(new GetNextBuffer(queue));
            Thread background = new Thread(new BackgroundWorker(queue));
            Thread defaultMode;
            if (e.getStateChange() == ItemEvent.SELECTED) {
                isDoubleBuffering = true;
                if (!background.isAlive()) {
                    background = new Thread(new BackgroundWorker(queue));
                    background.start();
                }
                if (!buffer.isAlive()) {
                    buffer.start();
                }
            } else {
                defaultMode = new Thread(new DefaultMode());
                defaultMode.start();
                isDoubleBuffering = false;
            }
        });

        startButton.addActionListener(e -> {
            if (!isStarted) {
                Thread defaultMode = new Thread(new DefaultMode());
                defaultMode.start();
                isStarted = true;
            }
        });


        add(panel, BorderLayout.NORTH);
    }



    static class GetNextBuffer implements Runnable {
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
            int[] pixelColor;
            List<Triangle> clipping_triangles;
            int [][][] buffer = new int[DEFAULT_WIDTH][DEFAULT_HEIGHT][3];
            int [][][] nextBuffer = new int[DEFAULT_WIDTH][DEFAULT_HEIGHT][3];
            BufferRender bufferRender = new BufferRender();
            while(isDoubleBuffering) {
                nextBuffer = bufferRender.clearBuffer(nextBuffer);
                if (!is2DClipping) {
                    List<Triangle> triangles = render.render();
                    for (Triangle triangle : triangles) {
                        clipping_triangles = List.of(triangle); // delete this
                        pixelColor = triangle.getPixelColor();
                        for (Triangle i : clipping_triangles) {
                            i.normalize();
                            nextBuffer = bufferRender.addTriangleToBuffer(i, pixelColor, nextBuffer);
                        }
                    }
                } else {
                    List<Triangle> triangles = render.render();
                    for (Triangle triangle : triangles) {
                        clipping_triangles = helper.triangleProcessing(triangle, canvas.getWidth(), canvas.getHeight());
                        if (clipping_triangles != null) {
                            pixelColor = triangle.getPixelColor();
                            for (Triangle i : clipping_triangles) {
                                i.normalize();
                                nextBuffer = bufferRender.addTriangleToBuffer(i, pixelColor, nextBuffer);
                            }
                        }
                    }
                }
                buffer = bufferRender.cloneBuffer(nextBuffer, buffer);
                queue.put(buffer);
            }
        }
    }

    static class BackgroundWorker implements  Runnable {
        private final BlockingQueue<int[][][]> queue;
        Fps fpsCounter = new Fps();
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
            while (isDoubleBuffering) {
                buffer = queue.take();
                canvas.addBufferToCanvas(buffer);
                canvas.drawCanvas();
                int fpsResult = fpsCounter.fps();
                if (fpsResult != -1) {
                    fps.setText("FPS " + fpsResult);
                }
            }
        }
    }
    static class DefaultMode implements  Runnable {
        @Override
        public void run() {
            try {
                draw();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        private void draw() throws InterruptedException {
            Fps fpsCounter = new Fps();
            Initialization initialization = new Initialization();
            Render render = initialization.getRender();
            int[] pixelColor;
            List<Triangle> clipping_triangles;
            while (!isDoubleBuffering) {
                canvas.Clear();
                if (!is2DClipping) {
                    List<Triangle> triangles = render.render();
                    for (Triangle triangle : triangles) {
                        clipping_triangles = List.of(triangle); // delete this
                        pixelColor = triangle.getPixelColor();
                        for (Triangle i : clipping_triangles) {
                            i.normalize();
                            canvas.drawTriangle(i, pixelColor);
                        }
                    }
                } else {
                    List<Triangle> triangles = render.render();
                    for (Triangle triangle : triangles) {
                        clipping_triangles = helper.triangleProcessing(triangle, canvas.getWidth(), canvas.getHeight());
                        if (clipping_triangles != null) {
                            pixelColor = triangle.getPixelColor();
                            for (Triangle i : clipping_triangles) {
                                i.normalize();
                                canvas.drawTriangle(i, pixelColor);
                            }
                        }
                    }
                }
                canvas.drawCanvas();
                int fpsResult = fpsCounter.fps();
                if (fpsResult != -1) {
                    fps.setText("FPS " + fpsResult);
                }
            }
        }
    }
}