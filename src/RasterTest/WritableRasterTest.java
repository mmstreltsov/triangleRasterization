package RasterTest;

import RasterTest.State.RenderObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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
            int w = DEFAULT_WIDTH;
            int h = DEFAULT_HEIGHT;
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
            Test test = new Test();

            while(true) {
                RenderObject renderObject = test.makeAnimation();
                Triangle triangle = test.convert(renderObject).get(0);
                triangle.normalize();
                //screen.fillPixels(blueColorPixel);
                screen.drawTriangle(triangle, blueColorPixel);
                Thread.sleep(5);
                canvas.Clear();
            }
        }
    }
}