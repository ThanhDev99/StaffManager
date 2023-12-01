package View.SecondFrame;

import Controller.RequestController;
import Model.Request;
import Model.ShareData;
import View.Help.Dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddRequestFrame extends JFrame {
    private RequestController controller;
    private Font fontSystem = new Font("Cambria", Font.BOLD, 16);
    private int xOffset, yOffset;
    private GridBagConstraints constraints = new GridBagConstraints();
    private JComboBox cbxDepartmentReceive;
    private JTextArea txtContent;
    private JButton btnSendRequest;
    public AddRequestFrame() {
        controller = new RequestController();
        createFrame();
    }
    private void createFrame() {
        setTitle("Gửi yêu cầu");

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
        JLabel lblTitle = new JLabel("GỬI YÊU CẦU", JLabel.CENTER);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(2, 0, 5, 0);
        add(lblTitle, constraints);
        lblTitle.setFont(new Font("Cambria", Font.BOLD, 25));
        lblTitle.setPreferredSize(new Dimension(300, 35));

        JLabel lblName = new JLabel("Phòng ban nhận: ", JLabel.LEFT);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(2, 0, 0, 0);
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        add(lblName, constraints);
        lblName.setFont(fontSystem);
        lblName.setPreferredSize(new Dimension(300, 30));

        cbxDepartmentReceive = new JComboBox(new String[]{"1", "2", "3"});
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(cbxDepartmentReceive, constraints);
        cbxDepartmentReceive.setFont(fontSystem);
        cbxDepartmentReceive.setPreferredSize(new Dimension(300, 30));

        JLabel lblDeadline = new JLabel("Nội dung yêu cầu: ");
        constraints.gridx = 0;
        constraints.gridy = 3;
        add(lblDeadline, constraints);
        lblDeadline.setFont(fontSystem);
        lblDeadline.setPreferredSize(new Dimension(300, 30));

        txtContent = new JTextArea();
        JScrollPane pnlContent = new JScrollPane(txtContent);
        constraints.gridx = 0;
        constraints.gridy = 9;
        add(pnlContent, constraints);
        txtContent.setFont(fontSystem);
        pnlContent.setPreferredSize(new Dimension(300, 70));

        btnSendRequest = new JButton("Gửi");
        constraints.gridx = 0;
        constraints.gridy = 10;
        constraints.insets = new Insets(10, 0, 5, 0);
        constraints.anchor = GridBagConstraints.FIRST_LINE_END;
        add(btnSendRequest, constraints);
        btnSendRequest.setFont(fontSystem);
        btnSendRequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getBtnSendRequest();
            }
        });
    }
    private void getBtnSendRequest() {
        if (txtContent.getText().trim().equals("")) {
            Dialog.showMessageDialogWithAutoClose("Chưa nhập nội dung yêu cầu.", 0);
            return;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Request request = new Request().setStaff(ShareData.account.getStaffId())
                .setDepartmentSend(ShareData.account.getDepartment())
                .setDepartmentReceive(cbxDepartmentReceive.getSelectedItem().toString())
                .setDateSend(dateFormat.format(new Date()))
                .setStatus("SENT")
                .setContent(txtContent.getText().trim());

        if (controller.addRequest(request)) {
            Dialog.showMessageDialogWithAutoClose("Gửi thành công.", 1);
            setVisible(false);
        } else {
            Dialog.showMessageDialogWithAutoClose("Gửi không thành công.", 0);
        }
    }
}
