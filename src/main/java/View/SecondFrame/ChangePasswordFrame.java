package View.SecondFrame;

import Controller.AccountController;
import View.Help.Dialog;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChangePasswordFrame {
    private AccountController controller;
    private JFrame frame;
    private Font fontSystem = new Font("Cambria", Font.BOLD, 16);
    private GridBagConstraints constraints = new GridBagConstraints();
    private JTextField txtStaffId;
    private JPasswordField txtPass, txtPass1, txtPass2;;
    private String staffId;
    private Boolean test;
    public ChangePasswordFrame(String staffId, Boolean test) {
        this.staffId = staffId;
        this.test = test;

        controller = new AccountController();

        createGUI();
    }

    public void createGUI(){
        frame = new JFrame();

        frame.setTitle("Đổi mật khẩu");
        frame.setLayout(new GridBagLayout());

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.setResizable(false);

        createComponent();

        frame.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((screenSize.width - frame.getWidth()) / 2, (screenSize.height - frame.getHeight()) / 2);
        frame.setVisible(true);
    }

    private void createComponent() {
        JLabel lblTitle = new JLabel("ĐỔI MẬT KHẨU", JLabel.CENTER);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        frame.add(lblTitle, constraints);
        lblTitle.setFont(new Font("Cambria", Font.BOLD, 22));
        lblTitle.setForeground(new Color(91, 45, 255));
        lblTitle.setPreferredSize(new Dimension(300, 50));

        JLabel lblMaNv = new JLabel("Tên đăng nhập", JLabel.LEFT);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        constraints.insets = new Insets(5, 10, 0, 10);
        frame.add(lblMaNv, constraints);
        lblMaNv.setFont(new Font("Cambria", Font.BOLD, 18));

        JLabel lblMk = new JLabel("Mật khẩu hiện tại");
        constraints.gridx = 0;
        constraints.gridy = 3;
        frame.add(lblMk, constraints);
        lblMk.setFont(new Font("Cambria", Font.BOLD, 18));
        if (test) {
            lblMk.setVisible(false);
        }

        JLabel lblMk1 = new JLabel("Mật khẩu mới");
        constraints.gridx = 0;
        constraints.gridy = 5;
        frame.add(lblMk1, constraints);
        lblMk1.setFont(new Font("Cambria", Font.BOLD, 18));

        JLabel lblMk2 = new JLabel("Xác nhận mật khẩu mới");
        constraints.gridx = 0;
        constraints.gridy = 7;
        frame.add(lblMk2, constraints);
        lblMk2.setFont(new Font("Cambria", Font.BOLD, 18));

        txtStaffId = new JTextField(staffId);
        constraints.gridx = 0;
        constraints.gridy = 2;
        frame.add(txtStaffId, constraints);
        txtStaffId.setFont(fontSystem);
        txtStaffId.setPreferredSize(new Dimension(300, 30));
        txtStaffId.setEditable(false);

        txtPass = new JPasswordField();
        constraints.gridx = 0;
        constraints.gridy = 4;
        frame.add(txtPass, constraints);
        txtPass.setFont(fontSystem);
        txtPass.setPreferredSize(new Dimension(300, 30));
        txtPass.setEchoChar('*');
        if (test) {
            txtPass.setVisible(false);
        }

        txtPass1 = new JPasswordField();
        constraints.gridx = 0;
        constraints.gridy = 6;
        frame.add(txtPass1, constraints);
        txtPass1.setFont(fontSystem);
        txtPass1.setPreferredSize(new Dimension(300, 30));
        txtPass1.setEchoChar('*');

        txtPass2 = new JPasswordField();
        constraints.gridx = 0;
        constraints.gridy = 8;
        frame.add(txtPass2, constraints);
        txtPass2.setFont(fontSystem);
        txtPass2.setPreferredSize(new Dimension(300, 30));
        txtPass2.setEchoChar('*');

        JLabel lblShow = new JLabel("Hiện mật khẩu", JLabel.CENTER);
        constraints.gridx = 0;
        constraints.gridy = 9;
        constraints.anchor = GridBagConstraints.CENTER;
        frame.add(lblShow, constraints);
        lblShow.setFont(fontSystem);
        lblShow.setPreferredSize(new Dimension(300, 30));
        lblShow.setBorder(new MatteBorder(1, 0 , 1, 0, Color.BLACK));
        lblShow.setForeground(new Color(44, 139, 255));
        lblShow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                txtPass.setEchoChar((char) 0);
                txtPass1.setEchoChar((char) 0);
                txtPass2.setEchoChar((char) 0);
                lblShow.setForeground(new Color(147, 0, 246));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                txtPass.setEchoChar('*');
                txtPass1.setEchoChar('*');
                txtPass2.setEchoChar('*');
                lblShow.setForeground(new Color(44, 139, 255));
            }
        });

        JButton btnDongY = new JButton("Đồng ý");
        constraints.gridx = 0;
        constraints.gridy = 10;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(5, 10, 5, 10);
        constraints.anchor = GridBagConstraints.FIRST_LINE_END;
        frame.add(btnDongY, constraints);
        ImageIcon done = new ImageIcon("icons/Done.png");
        ImageIcon doneResize = new ImageIcon(done.getImage().getScaledInstance(30, 30, 0));
        btnDongY.setIcon(doneResize);
        btnDongY.setFont(fontSystem);
        btnDongY.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doimatkhau();
            }
        });

        JButton btnHuyBo = new JButton("Hủy bỏ");
        constraints.gridx = 1;
        constraints.gridy = 10;
        frame.add(btnHuyBo, constraints);
        ImageIcon cancel = new ImageIcon("icons/Cancel.png");
        ImageIcon cancelResize = new ImageIcon(cancel.getImage().getScaledInstance(30, 30, 0));
        btnHuyBo.setIcon(cancelResize);
        btnHuyBo.setFont(fontSystem);
        btnHuyBo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
    }
    private void doimatkhau() {
        if (txtPass.getText().trim().isEmpty() && test) {
            View.Help.Dialog.showMessageDialogWithAutoClose("Chưa nhập mật khẩu cũ", 0);
            return;
        }
        if (txtPass1.getText().trim().isEmpty()) {
            View.Help.Dialog.showMessageDialogWithAutoClose("Chưa nhập mật khẩu mới", 0);
            return;
        }
        if (txtPass2.getText().trim().isEmpty()) {
            View.Help.Dialog.showMessageDialogWithAutoClose("Chưa nhập xác nhận mật khẩu", 0);
            return;
        }

        String pass = new String(txtPass.getPassword());
        String pass1 = new String(txtPass1.getPassword());
        String pass2 = new String(txtPass2.getPassword());

        if (!pass1.equalsIgnoreCase(pass1)) {
            View.Help.Dialog.showMessageDialogWithAutoClose("Mật khẩu xác nhận không trùng khớp", 0);
            return;
        }

        String result;

        if (test) {
            result = controller.changePass(staffId, pass1);
        } else {
            result = controller.changePass1(staffId, pass, pass1);
        }

        if (result.equals("NO_MK")) {
            View.Help.Dialog.showMessageDialogWithAutoClose("Mật khẩu hiện tại không đúng", 0);
        } else if (result.equals("YES")) {
            View.Help.Dialog.showMessageDialogWithAutoClose("Đổi mật khẩu thành công", 1);
            frame.setVisible(false);
        } else if (result.equals("FALSE")) {
            Dialog.showMessageDialogWithAutoClose("Đổi mật khẩu không thành công", 0);
        }
    }
}
