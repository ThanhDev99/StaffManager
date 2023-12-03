package View;

import Controller.CheckInController;
import Model.ShareData;
import View.Help.Dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    private CheckInController controller;
    private int xOffset, yOffset;
    public MainFrame() {
        controller = new CheckInController();

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

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (Dialog.showComfirmDialogWithAutoClose("Bạn chắc muốn thoát chương trình?")) {
                    controller.setCheckOut(ShareData.account.getStaffId());

                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);

        setVisible(true);
    }
}
