package RasterTest;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;


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
        private final int[] greenColorPixel = new int[]{0, 255, 0};
        private int[] blueColorArray;
        private int[] greenColorArray;
        private final PixelScreen screen;

        BackgroundWorker(PixelScreen screen) {
            this.screen = screen;
            int w = screen.getResolutionY();
            int h = screen.getResolutionX();
            blueColorArray = new int[w * h * 3];
            for (int i = 2; i < w * h * 3; i += 3) {
                blueColorArray[i] = 255;
            }
            greenColorArray = new int[w * h * 3];
            for (int i = 1; i < w * h * 3; i += 3) {
                greenColorArray[i] = 255;
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
            long time;
            int x1, y1, x2, y2, x3, y3;

            while(true) {
                Random random = new Random();
                x1 = random.nextInt(1800);
                y1 = random.nextInt(900);
                x2 = random.nextInt(1800);
                y2 = random.nextInt(900);
                x3 = random.nextInt(1800);
                y3 = random.nextInt(900);
                Triangle triangle = new Triangle(x1, y1, x2, y2, x3, y3);
                triangle.normalize();
                time = System.nanoTime();
                //screen.fillPixels(blueColorPixel);
                screen.drawTriangle(triangle, blueColorPixel);
                fps.setText("FPS: " + (int) (100000000000. / (System.nanoTime() - time)) / 50.);
                Thread.sleep(500);
                canvas.Clear();
            }
        }
    }
}