package View.SecondFrame;

import Controller.AccountController;
import View.Help.Dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ForgetPasswordFrame extends JFrame {
    private int xOffset, yOffset;
    private AccountController controller;
    private GridBagConstraints constraints = new GridBagConstraints();
    private Font fontSystem = new Font("Cambria", Font.BOLD, 16);
    private JTextField txtUsername, txtCode;
    private JButton btnSendCode, btnAccept;
    private JLabel lblClock;
    private String code;
    public ForgetPasswordFrame() {
        controller = new AccountController();

        createFrame();
    }
    private void createFrame() {
        setTitle("Quên mật khẩu");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setSize(600, 300);

        setLayout(new GridBagLayout());

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

        createGUI();
    }
    private void createGUI() {
        JLabel lblTitle = new JLabel("QUÊN MẬT KHẨU");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(5, 0, 20, 0);
        add(lblTitle, constraints);
        lblTitle.setFont(new Font("Cambria", Font.BOLD, 40));
        lblTitle.setForeground(new Color(63, 0, 97, 255));

        JLabel lblUser = new JLabel("Tài khoản", JLabel.LEFT);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        constraints.insets = new Insets(5, 0, 5, 10);
        add(lblUser, constraints);
        lblUser.setFont(fontSystem);
        lblUser.setPreferredSize(new Dimension(150, 30));

        txtUsername = new JTextField();
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.insets = new Insets(2, 5, 2, 0);
        add(txtUsername, constraints);
        txtUsername.setFont(fontSystem);
        txtUsername.setPreferredSize(new Dimension(300, 30));

        lblClock = new JLabel();
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(lblClock, constraints);
        lblClock.setFont(fontSystem);
        lblClock.setPreferredSize(new Dimension(150, 30));

        btnSendCode = new JButton("Gửi mã xác nhận");
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.FIRST_LINE_END;
        constraints.insets = new Insets(2, 2, 2, 0);
        add(btnSendCode, constraints);
        btnSendCode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setBtnSendCode();
            }
        });
        btnSendCode.setFont(fontSystem);

        JLabel lblPass = new JLabel("Mã xác nhận", JLabel.LEFT);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        add(lblPass, constraints);
        lblPass.setFont(fontSystem);
        lblPass.setPreferredSize(new Dimension(150, 30));

        txtCode = new JPasswordField();
        constraints.gridx = 1;
        constraints.gridy = 3;
        add(txtCode, constraints);
        txtCode.setFont(fontSystem);
        txtCode.setPreferredSize(new Dimension(300, 30));

        btnAccept = new JButton("Xác nhận");
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(10, 0, 5, 0);
        constraints.anchor = GridBagConstraints.CENTER;
        add(btnAccept, constraints);
        btnAccept.setFont(fontSystem);
        btnAccept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setBtnAccept();
            }
        });
    }
    private void setBtnSendCode() {
        if (txtUsername.getText().trim().isEmpty()) {
            Dialog.showMessageDialogWithAutoClose("Chưa nhập mã nhân viên.", 0);
            return;
        }
        String result = controller.checkStaffEmail(txtUsername.getText().trim());

        if (result.equals("NO_ID")) {
            Dialog.showMessageDialogWithAutoClose("Không tồn tại tài khoản này.", 0);
            return;
        } else if (result.equals("FALSE")) {
            Dialog.showMessageDialogWithAutoClose("Gửi không thành công.", 0);
            return;
        }
        String Email = result.substring(0, result.indexOf(" "));

        code = result.substring(result.indexOf(" ") + 1, result.length());

        btnSendCode.setEnabled(false);

        Dialog.showMessageDialogWithAutoClose("Đã gửi mã đến Email: "
                + Email.substring(0, 2) + "*****" + Email.substring(Email.indexOf("@") - 2, Email.length()), 1);

        time();
    }
    private void time() {
        Timer timer = new Timer(1000, new ActionListener() {
            int secondsLeft = 120; // 2 minutes
            @Override
            public void actionPerformed(ActionEvent e) {
                if (secondsLeft > 0) {
                    int minutes = secondsLeft / 60;
                    int seconds = secondsLeft % 60;
                    lblClock.setText(String.format("%d:%02d", minutes, seconds));
                    secondsLeft--;
                } else {
                    ((Timer) e.getSource()).stop();
                    lblClock.setText("Mã đã bị hủy.");
                    btnSendCode.setEnabled(true);
                    code = "";
                }
            }
        });
        timer.start();
    }
    private void setBtnAccept() {
        if (txtCode.getText().trim().equals(code)) {
            Dialog.showMessageDialogWithAutoClose("Thành công", 1);
            setVisible(false);
            ChangePasswordFrame changePasswordFrame = new ChangePasswordFrame(txtUsername.getText().trim(), true);
        } else {
            Dialog.showMessageDialogWithAutoClose("Sai mã xác nhận", 0);
        }
    }
}
