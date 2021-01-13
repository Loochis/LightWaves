import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static final double C = 299792458.0;
    public static final double NANO_CONST = 1000000000.0;

    public static final boolean DEBUG = true;

    public static int VRES = 5;
    public double timeMultiplier = 100000 / NANO_CONST;
    public static int NANOS_TO_PIXEL = 5;

    public static final int TARGET_FPS = 60;
    public static final double NANO_PER_FRAME = NANO_CONST / TARGET_FPS;

    public DrawablePanel drawablePanel;

    public List<Emitter> emitters = new ArrayList();

    public static void main(String args[]) {
        MainFrame frame = new MainFrame();
    }

    public Main() {
        Random rand = new Random(1);
        for (int i = 0; i < 20; i++) {
            Emitter e = new Emitter();
            e.x = rand.nextInt(2000);
            e.y = rand.nextInt(1000);
            e.amplitude = rand.nextDouble() * 1.5;
            e.wavelength = rand.nextInt(300) + 400;
            emitters.add(e);
        }
    }

    public void Run() {
        (new Thread() {
            public void run() {
                double timeSince = System.nanoTime();
                while(true) {
                    if (System.nanoTime() - timeSince > NANO_PER_FRAME) {
                        for (Emitter e : emitters) {
                            e.PhaseShift(drawablePanel.main);
                        }
                        drawablePanel.paintImmediately(0,0, drawablePanel.getWidth(), drawablePanel.getHeight());
                        timeSince = System.nanoTime();
                    }
                }
            }
        }).start();
    }
}
