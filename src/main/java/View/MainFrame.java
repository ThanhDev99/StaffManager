package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    private int xOffset, yOffset;
    public MainFrame() {
        createFrame();
    }
    private void createFrame() {
        setTitle("FengYang");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1400, 710);

        ImageIcon icon = new ImageIcon("");
        setIconImage(icon.getImage());

        LoginPanel loginPanel = new LoginPanel(this);
        add(loginPanel);

//        MainPanel mainPanel = new MainPanel(this, "NV001");
//        add(mainPanel);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xOffset = e.getX();
                yOffset = e.getY();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen() - xOffset;
                int y = e.getYOnScreen() - yOffset;

                setLocation(x, y);
            }
        });

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);

        setVisible(true);
    }
}
