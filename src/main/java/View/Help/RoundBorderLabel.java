package View.Help;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class RoundBorderLabel extends JLabel {
    public RoundBorderLabel(String text, ImageIcon icon) {
        super(text, icon, 0);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Vẽ hình tròn viền cho JLabel
        int width = getWidth();
        int height = getHeight();

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.BLACK); // Màu viền
        g2d.setStroke(new BasicStroke(2)); // Độ rộng viền
        g2d.draw(new Ellipse2D.Double(1, 1, width - 3, height - 3));

        g2d.dispose();
    }
}
