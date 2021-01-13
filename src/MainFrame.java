import javax.swing.*;
import java.awt.*;

public class MainFrame {
    private JPanel panel1;
    private JPanel DrawPanel;

    private DrawablePanel drawablePanel;

    public MainFrame() {

        JFrame mainWin = new JFrame("TEST");
        mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWin.setContentPane(panel1);
        mainWin.setVisible(true);
        mainWin.pack();

        DrawPanel.repaint();
    }

    private void createUIComponents() {
        Main m = new Main();
        DrawPanel = new DrawablePanel(m);
        drawablePanel = (DrawablePanel) DrawPanel;
        m.drawablePanel = drawablePanel;
    }
}
