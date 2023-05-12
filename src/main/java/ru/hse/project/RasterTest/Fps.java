package ru.hse.project.RasterTest;

public class Fps {
    private long lastFpsCheck = 0;
    private int totalFrames = 0;

    Fps() {
        this.lastFpsCheck = 0;
        this.totalFrames = 0;
    }

    public int fps() {
        if (System.nanoTime() - lastFpsCheck > 1_000_000_000) {
            lastFpsCheck = System.nanoTime();
            int currentFps = totalFrames;
            totalFrames = 0;
            return currentFps;
        }
        totalFrames++;
        return -1;
    }
}
