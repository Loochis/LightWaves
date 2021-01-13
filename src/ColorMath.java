import java.awt.*;
import java.util.List;

public class ColorMath {
    public static Color MultiplyAlpha(Color c, double a) {
        if (a < 0)
            return new Color(0, 0, 0, 0);
        if (a > 1)
            return c;

        return new Color(c.getRed(), c.getGreen(), c.getBlue(), (int) (c.getAlpha()*a));
    }

    public static Color Average(List<Color> colors) {
        int r = 0, g = 0, b = 0, a = 0;
        int numCols = 0;
        for (Color c : colors) {
            if (c == null)
                continue;
            numCols++;
            r += c.getRed();
            g += c.getGreen();
            b += c.getBlue();
            a += c.getAlpha();
        }
        if (r > 255)
            r = 255;
        if (g > 255)
            g = 255;
        if (b > 255)
            b = 255;
        if (a > 255)
            a = 255;
        return new Color(r, g, b, a);
    }
}