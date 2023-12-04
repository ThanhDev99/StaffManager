package View;

import Controller.AccountController;
import Model.Account;
import Model.ShareData;
import View.Help.Dialog;
import View.SecondFrame.ForgetPasswordFrame;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LoginPanel extends JPanel {
    private AccountController controller;
    private MainFrame mainFrame;
    private GridBagConstraints constraints = new GridBagConstraints();
    private Font fontSystem = new Font("Cambria", Font.BOLD, 16);
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnShow;
    private JCheckBox cbxRemember;
    public LoginPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        controller = new AccountController();

        createPanel();

        checkDataRemember();
    }
    private void createPanel() {
        setPreferredSize(new Dimension(1400, 700));

        setLayout(new GridBagLayout());

        setBackground(new Color(126, 102, 215));

        createGUI();
    }
    private void createGUI() {
        JLabel lblTitle = new JLabel("ĐĂNG NHẬP");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.insets = new Insets(5, 0, 50, 0);
        add(lblTitle, constraints);
        lblTitle.setFont(new Font("Cambria", Font.BOLD, 40));
        lblTitle.setForeground(new Color(63, 0, 97, 255));

        JLabel lblUser = new JLabel("Tài khoản", JLabel.RIGHT);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(5, 0, 5, 10);
        add(lblUser, constraints);
        lblUser.setFont(fontSystem);
        lblUser.setPreferredSize(new Dimension(150, 30));

        JLabel lblPass = new JLabel("Mật khẩu", JLabel.RIGHT);
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(lblPass, constraints);
        lblPass.setFont(fontSystem);
        lblPass.setPreferredSize(new Dimension(150, 30));

        txtUsername = new JTextField();
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(2, 5, 2, 0);
        add(txtUsername, constraints);
        txtUsername.setFont(fontSystem);
        txtUsername.setPreferredSize(new Dimension(400, 30));

        txtPassword = new JPasswordField();
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        add(txtPassword, constraints);
        txtPassword.setFont(fontSystem);
        txtPassword.setPreferredSize(new Dimension(368, 30));
        txtPassword.setEchoChar('*');

        ImageIcon eyeIcon = new ImageIcon("icons/Eye.png");
        ImageIcon eyeIconResize = new ImageIcon(eyeIcon.getImage().getScaledInstance(30, 30, 0));
        ImageIcon hideIcon = new ImageIcon("icons/Hide.png");
        ImageIcon hideIconResize = new ImageIcon(hideIcon.getImage().getScaledInstance(30, 30, 0));
        btnShow = new JButton(eyeIconResize);
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.insets = new Insets(2, 2, 2, 0);
        add(btnShow, constraints);
        btnShow.setPreferredSize(new Dimension(30, 30));
        btnShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtPassword.getEchoChar() == '*') {
                    txtPassword.setEchoChar((char) 0);
                    btnShow.setIcon(hideIconResize);
                } else {
                    txtPassword.setEchoChar('*');
                    btnShow.setIcon(eyeIconResize);
                }
            }
        });

        JPanel jPanel = new JPanel();
        jPanel.setPreferredSize(new Dimension(400, 30));
        jPanel.setLayout(new GridLayout(1,2));
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(2, 0, 2, 0);
        add(jPanel, constraints);
        jPanel.setBackground(new Color(126, 102, 215));

        cbxRemember = new JCheckBox("Ghi nhớ đăng nhập");
        jPanel.add(cbxRemember);
        cbxRemember.setFont(fontSystem);
        cbxRemember.setPreferredSize(new Dimension(150, 30));
        cbxRemember.setBackground(new Color(126, 102, 215));

        JLabel lblForgot = new JLabel("Quên mật khẩu", JLabel.RIGHT);
        jPanel.add(lblForgot);
        lblForgot.setFont(fontSystem);
        lblForgot.setPreferredSize(new Dimension(300, 30));
        lblForgot.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lblForgot.setForeground(new Color(246, 178, 255));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                lblForgot.setForeground(new Color(0, 0, 0));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                ForgetPasswordFrame forgetPasswordFrame = new ForgetPasswordFrame();
            }
        });

        btnLogin = new JButton("Đăng nhập");
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 3;
        constraints.insets = new Insets(20, 0, 30, 0);
        constraints.anchor = GridBagConstraints.CENTER;
        add(btnLogin, constraints);
        btnLogin.setFont(fontSystem);
        btnLogin.setPreferredSize(new Dimension(150, 30));
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setBtnLogin();
            }
        });
    }
    private void setBtnLogin() {
        if (txtUsername.getText().trim().equals("")) {
            Dialog.showMessageDialogWithAutoClose("Chưa nhập tài khoản.", 0);
            return;
        }
        if (txtPassword.getText().trim().equals("")) {
            Dialog.showMessageDialogWithAutoClose("Chưa nhập mật khẩu.", 0);
            return;
        }

        String account = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        String result = controller.login(account, password);
        if (result.equals("NO_ACCOUNT")) {
            Dialog.showMessageDialogWithAutoClose("Không tồn tại tài khoản này.", 0);
        } else if (result.equals("NO_PASS")) {
            Dialog.showMessageDialogWithAutoClose("Mật khẩu bạn nhập không đúng.", 0);
        } else if (result.equals("YES")) {
            Dialog.showMessageDialogWithAutoClose("Đăng nhập thành công.", 1);
            ShareData.account.setStaffId(account);

            checkRememberPassword(account, password);

            mainFrame.remove(this);
            MainPanel mainPanel = new MainPanel(mainFrame, account);
            mainFrame.add(mainPanel);
            mainFrame.revalidate();
            mainFrame.repaint();
        }
    }
    private void checkRememberPassword(String account, String password) {
        if (!cbxRemember.isSelected()) {
            account = "";
            password = "";
        }
        try {
            FileOutputStream fos = new FileOutputStream("data/Account.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            Account acc = new Account();
            acc.setStaffId(account);
            acc.setPassword(password);

            oos.writeObject(acc);

            System.out.println("Save done.");
            oos.close();
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void checkDataRemember() {
        try {
            FileInputStream fis = new FileInputStream("data/Account.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);

            Account acc = (Account) ois.readObject();

            if (!acc.getStaffId().equals("")) {
                txtUsername.setText(acc.getStaffId());
                txtPassword.setText(acc.getPassword());
                cbxRemember.setSelected(true);
            }

            ois.close();
            fis.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
