package RasterTest;
import RasterTest.State.Animation.ToRotate;
import RasterTest.State.Math.Coord3D;
import RasterTest.State.Math.Triangle3D;
import RasterTest.State.Math.Vector3D;
import RasterTest.State.Model;
import RasterTest.State.RenderObject;
import RasterTest.State.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;


public class WritableRasterTest extends JFrame {

    private static final int DEFAULT_WIDTH = 640;
    private static final int DEFAULT_HEIGHT = 480;
    private JLabel fps;
    private PixelScreen canvas;

    public static void main(String[] args){
        JFrame f = new WritableRasterTest();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public WritableRasterTest(){
        setTitle("WritableRasterTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

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
        private int[] blueColorArray;
        private final PixelScreen screen;

        BackgroundWorker(PixelScreen screen) {
            this.screen = screen;
            int w = screen.getResolutionY();
            int h = screen.getResolutionX();
            blueColorArray = new int[w * h * 3];
            for (int i = 2; i < w * h * 3; i += 3) {
                blueColorArray[i] = 255;
            }
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
            Model model = new Model();
            Coord3D x = new Coord3D(5., 0., 0.);
            Coord3D y = new Coord3D(-5., 0., 0.);
            Coord3D z = new Coord3D(0., 5., 0.);

            model.setTriangulation(new ArrayList<>(List.of(new Triangle3D(x, y, z))));

            Scene scene = new Scene();
            scene.getModelInstance().getTranslation().setTranslation(new Vector3D(0, 0, 20));

            while(true) {

                ToRotate.rotate(scene.getModelInstance().getRotation(), 0 ,0, 0.01);
                RenderObject renderObject = new RenderObject(scene, model);
                renderObject.init();

                List<Triangle> triangles = new ArrayList<>();
                renderObject.getTriangles().forEach(it -> {
                    Triangle triangle = new Triangle((int)it.getVertex1().getX() + DEFAULT_WIDTH/2, (int)it.getVertex1().getY() + DEFAULT_HEIGHT / 2,
                            (int)it.getVertex2().getX() + DEFAULT_WIDTH / 2, (int)it.getVertex2().getY() + DEFAULT_HEIGHT / 2,
                            (int)it.getVertex3().getX() + DEFAULT_WIDTH / 2, (int) it.getVertex3().getY() + DEFAULT_HEIGHT / 2);
                    triangles.add(triangle);
                });
                if (triangles.isEmpty()) {
                    return;
                }
                Triangle triangle = triangles.get(0);
                triangle.normalize();
                //screen.fillPixels(blueColorPixel);
                screen.drawTriangle(triangle, blueColorPixel);
                canvas.Clear();
                Thread.sleep(5);
            }
        }
    }
}