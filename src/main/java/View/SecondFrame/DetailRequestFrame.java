package View.SecondFrame;

import Controller.TaskController;
import Model.Request;
import Model.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DetailRequestFrame extends JFrame {
    private Font fontSystem = new Font("Cambria", Font.BOLD, 16);
    private GridBagConstraints constraints = new GridBagConstraints();
    private int xOffset, yOffset;
    private Request request;
    public DetailRequestFrame(Request request) {
        this.request = request;

        createFrame();
    }
    private void createFrame() {
        setTitle("Chi tiết yêu cầu");

        setLayout(new GridBagLayout());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setSize(350, 500);

        ImageIcon icon = new ImageIcon("");
        setIconImage(icon.getImage());

        createGUI();

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
    private void createGUI() {
        JLabel lblTitle = new JLabel("CHI TIẾT YÊU CẦU", JLabel.CENTER);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(2, 0, 5, 0);
        add(lblTitle, constraints);
        lblTitle.setFont(new Font("Cambria", Font.BOLD, 25));
        lblTitle.setPreferredSize(new Dimension(300, 35));

        JLabel lblDepartmentSend = new JLabel("Phòng ban gửi: " + request.getDepartmentSend());
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(2, 0, 0, 0);
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        add(lblDepartmentSend, constraints);
        lblDepartmentSend.setFont(fontSystem);
        lblDepartmentSend.setPreferredSize(new Dimension(300, 30));

        JLabel lblDepartmentReceive = new JLabel("Phòng ban nhận: " + request.getDepartmentReceive());
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        add(lblDepartmentReceive, constraints);
        lblDepartmentReceive.setFont(fontSystem);
        lblDepartmentReceive.setPreferredSize(new Dimension(300, 30));

        JLabel lblDateSend = new JLabel("Ngày gửi: " + request.getDateSend());
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        add(lblDateSend, constraints);
        lblDateSend.setFont(fontSystem);
        lblDateSend.setPreferredSize(new Dimension(300, 30));

        JLabel lblStatus = new JLabel("Tình trạng: " + request.getStatus());
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        add(lblStatus, constraints);
        lblStatus.setFont(fontSystem);
        lblStatus.setPreferredSize(new Dimension(300, 30));

        JLabel lblContent = new JLabel("Nội dung:");
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        add(lblContent, constraints);
        lblContent.setFont(fontSystem);
        lblContent.setPreferredSize(new Dimension(300, 30));

        JTextArea txtContent = new JTextArea(request.getContent());
        JScrollPane pnlContent = new JScrollPane(txtContent);
        constraints.gridx = 0;
        constraints.gridy = 6;
        add(pnlContent, constraints);
        txtContent.setFont(fontSystem);
        pnlContent.setPreferredSize(new Dimension(300, 200));
        txtContent.setEditable(false);
    }
}
