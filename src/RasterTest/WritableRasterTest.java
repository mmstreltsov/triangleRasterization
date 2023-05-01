package RasterTest;

import RasterTest.CameraAnimation.KeyChecker;
import RasterTest.State.Animation.ToTranslate;
import RasterTest.State.Animation.Translation;
import RasterTest.State.Camera;
import RasterTest.State.RenderObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;


public class WritableRasterTest extends JFrame {

    public static final int DEFAULT_WIDTH = PixelScreen.getResolutionX();
    public static final int DEFAULT_HEIGHT = PixelScreen.getResolutionY();
    public static final int DEFAULT_WINDOW_WIDTH = PixelScreen.getResolutionX();
    public static final int DEFAULT_WINDOW_HEIGHT = DEFAULT_HEIGHT + 70;
    private JLabel fps;
    private PixelScreen canvas;

    public static void main(String[] args) throws IOException {
        JFrame f = new WritableRasterTest();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setVisible(true);
    }

    public WritableRasterTest(){
        setTitle("WritableRasterTest");
        setSize(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
        canvas = new PixelScreen();
        add(canvas, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        ButtonGroup group = new ButtonGroup();
        JRadioButton pixelButton = new JRadioButton("Pixel-by-Pixel", false);
        JRadioButton arrayButton = new JRadioButton("Buffered Array", false);
        fps = new JLabel("FPS: 0");
        panel.add(fps);
        panel.add(pixelButton);
        panel.add(arrayButton);

        JTextField textField = new JTextField();
        textField.addKeyListener(new KeyChecker());
        panel.add(textField);

        group.add(pixelButton);
        group.add(arrayButton);

        final BackgroundWorker backgroundWorker = new BackgroundWorker(canvas);
        final Thread background = new Thread(backgroundWorker);
        pixelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!background.isAlive()) {
                    background.setDaemon(true);
                    background.start();
                }
            }
        });


        arrayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!background.isAlive()) {
                    background.setDaemon(true);
                    background.start();
                }
            }
        });
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
            Test test = new Test();

            while(true) {
                RenderObject renderObject = test.makeAnimation();
                Triangle triangle = test.convert(renderObject).get(0);
                //пример 1
                //Triangle triangle = new Triangle(200, -200, 100, 200, 500, 200);
                //пример 2
//                Triangle triangle = new Triangle(200, -200, 100, 200, 800, 200);
                //пример 3 обычный треугольник
                //Triangle triangle = new Triangle(200, 0, 100, 200, 500, 200);
                //пример 4 канвас помещается в треугольник
                //Triangle triangle = new Triangle(-1000, 1000, 300, -1000, 1000, 1000);
                triangle.normalize();
                TriangleHelper helper = new TriangleHelper();
                List<Point> points = triangle.findTriangleCanvasIntersection(screen.getWidth(), screen.getHeight());
                boolean[] triangle_points_status = helper.pointInRectangle(triangle, screen.getWidth(), screen.getHeight());
                if (triangle_points_status[0] && triangle_points_status[1] && triangle_points_status[2] && points.isEmpty()) {
                    //нет пересечений треугольника с канвасом, треугольник помещается в канвас
                    screen.drawTriangle(triangle, blueColorPixel);
                    Thread.sleep(5);
                    canvas.Clear();
                } else if (!(triangle_points_status[0] && triangle_points_status[1] && triangle_points_status[2]) && points.isEmpty()) {
                    //нет пересечений треугольника с канвасом, канвас помещается в треугольник, рисуем канвас
                    screen.fillPixels(blackColorPixel);
                    Thread.sleep(5);
                    canvas.Clear();
                } else {
                    //есть точки пересечения с канвасом
                    //добавляем в points точки треугольника если они лежат в канвасе
                    points = helper.addTrianglePoints(triangle, triangle_points_status, points);
                    List<Point> ordered_points = helper.orderVertices(points);
                    List<Triangle> triangles = helper.triangulateConvexPolygon(ordered_points);
                    if (triangles != null) {
                        for (Triangle i : triangles) {
                            i.normalize();
                            screen.drawTriangle(i, blueColorPixel);
                        }
                        Thread.sleep(5);
                        canvas.Clear();
                    }
                }

            }
        }
    }
}