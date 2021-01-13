import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class DrawablePanel extends JPanel {
    Main main;
    public DrawablePanel(Main main) {
        this.main = main;
        main.drawablePanel = this;
        main.Run();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0,0, getWidth(), getHeight());
        DrawWaves(g);
        DrawEmitters(g);
    }

    private void DrawWaves(Graphics g) {
        for (int x = 0; x < getWidth(); x+=Main.VRES) {
            for (int y = 0; y < getHeight(); y+=Main.VRES) {
                List<Color> cols = new ArrayList<>();
                double strength = 0;
                for(Emitter e : main.emitters) {
                    strength += e.SampleAt(x, y);
                    cols.add(e.getColor());
                }
                g.setColor(ColorMath.MultiplyAlpha(ColorMath.Average(cols), strength));
                g.fillRect(x, y, Main.VRES, Main.VRES);
            }
        }
    }

    private void DrawEmitters(Graphics g) {
        for(Emitter e : main.emitters) {
            g.setColor(e.getColor());
            g.fillOval((int) e.x - 5, (int) e.y - 5, 10, 10);
            if (Main.DEBUG)
                e.DrawWaveSimple(g);
        }
    }
}
