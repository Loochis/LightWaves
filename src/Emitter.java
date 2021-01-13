import java.awt.*;

public class Emitter {

    public double x;
    public double y;

    public double wavelength = 400;
    public double amplitude = 1;
    public double phase = 0;

    private double timeSinceShift = System.nanoTime();

    public double SampleAt(double x, double y) {
        double length = Math.sqrt(Math.pow(x-this.x, 2) + Math.pow(y-this.y, 2));
        length += phase;
        length *= Main.NANOS_TO_PIXEL;
        length %= wavelength;
        length /= wavelength;
        length *= Math.PI*2;
        length = Math.sin(length);
        length *= amplitude;
        return length;
    }

    public void PhaseShift(Main main) {
        double time = (System.nanoTime() - timeSinceShift) * main.timeMultiplier / Main.NANO_CONST;
        phase -= time * (Main.C / wavelength);
        timeSinceShift = System.nanoTime();
    }

    public Color getColor() {
        Color out = new Color(0, 0, 0);
        if (wavelength > 380 && wavelength < 440)
            out = new Color((float) (-1.0 *(wavelength-440)/(440-380)), 0f, 1.0f);
        else if (wavelength > 440 && wavelength < 490)
            out = new Color(0f, (float) ((wavelength-440)/(490-440)), 1.0f);
        else if (wavelength > 490 && wavelength < 510)
            out = new Color(0f, 1.0f, (float) (-1.0 *(wavelength-510)/(510-490)));
        else if (wavelength > 510 && wavelength < 580)
            out = new Color((float) ((wavelength-510)/(580-510)), 1.0f, 0f);
        else if (wavelength > 580 && wavelength < 645)
            out = new Color(1f, (float) (-1.0 *(wavelength-645)/(645-580)), 0f);
        else if (wavelength > 645 && wavelength < 780)
            out = new Color(1f, 0f, 0f);

        if (wavelength > 700)
            out = new Color(out.getRed(), out.getGreen(), out.getBlue(), (int) ((0.3+.07*(780-wavelength)/(780-700))*255.0));
        else if (wavelength < 420)
            out = new Color(out.getRed(), out.getGreen(), out.getBlue(), (int) ((0.3+.07*(wavelength-380)/(420-380))*255.0));

        return out;
    }

    public void DrawWaveSimple(Graphics g) {
        for (int i = 0; i < 20; i++) {
            double rad = (i * wavelength / Main.NANOS_TO_PIXEL);
            rad -= phase*Main.NANOS_TO_PIXEL;
            g.drawOval((int)(x-rad),(int)(y-rad),(int)rad*2,(int)rad*2);
        }
    }
}
