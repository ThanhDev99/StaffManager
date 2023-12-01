package View.ContentPanel;

import Controller.AccountController;
import Model.Account;
import View.Help.Dialog;
import View.MainFrame;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class AccountPanel extends JPanel {
    private List<Account> accountList = new ArrayList<>();
    private AccountController controller;
    private MainFrame mainFrame;
    private GridBagConstraints constraints = new GridBagConstraints();
    private Font fontSystem = new Font("Cambria", Font.BOLD, 16);
    private JTextField txtAccount;
    private JPasswordField txtPassword, txtPassword2;
    private DefaultTableModel tblModelAccount;
    private JTable tblAccount;
    private JButton btnUpdate, btnDelete;
    public AccountPanel() {
        controller = new AccountController();

        setBackground(new Color(126, 102, 215));

        setBorder(new MatteBorder(0, 2, 0, 0, Color.BLACK));

        setLayout(new GridBagLayout());

        setPreferredSize(new Dimension(1100, 700));

        createGUI();

        loadDataToTable();
    }
    private void createGUI() {
        JPanel pnlForm = new JPanel();
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(pnlForm, constraints);
        pnlForm.setPreferredSize(new Dimension(550, 650));
        pnlForm.setBorder(new MatteBorder(0, 0, 0, 2, Color.BLACK));
        pnlForm.setLayout(new GridBagLayout());

        JLabel lblTitle = new JLabel("THÔNG TIN TÀI KHOẢN");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 0, 10, 0);
        pnlForm.add(lblTitle, constraints);
        lblTitle.setFont(new Font("Cambria", Font.BOLD, 25));
        lblTitle.setForeground(new Color(63, 0, 97, 255));

        JLabel lblAccount = new JLabel("Tên tài khoản: ", JLabel.LEFT);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        constraints.insets = new Insets(0, 2, 2, 2);
        pnlForm.add(lblAccount, constraints);
        lblAccount.setFont(fontSystem);
        lblAccount.setPreferredSize(new Dimension(150, 30));

        JLabel lblPassword = new JLabel("Mật khẩu: ", JLabel.LEFT);
        constraints.gridx = 0;
        constraints.gridy = 3;
        pnlForm.add(lblPassword, constraints);
        lblPassword.setFont(fontSystem);
        lblPassword.setPreferredSize(new Dimension(150, 30));

        JLabel lblPassword2 = new JLabel("Nhập lại mật khẩu: ", JLabel.LEFT);
        constraints.gridx = 0;
        constraints.gridy = 5;
        pnlForm.add(lblPassword2, constraints);
        lblPassword2.setFont(fontSystem);
        lblPassword2.setPreferredSize(new Dimension(150, 30));

        txtAccount = new JTextField("");
        constraints.gridx = 0;
        constraints.gridy = 2;
        pnlForm.add(txtAccount, constraints);
        txtAccount.setFont(fontSystem);
        txtAccount.setPreferredSize(new Dimension(350, 30));

        txtPassword = new JPasswordField("");
        constraints.gridx = 0;
        constraints.gridy = 4;
        pnlForm.add(txtPassword, constraints);
        txtPassword.setFont(fontSystem);
        txtPassword.setPreferredSize(new Dimension(350, 30));

        txtPassword2 = new JPasswordField("");
        constraints.gridx = 0;
        constraints.gridy = 6;
        pnlForm.add(txtPassword2, constraints);
        txtPassword2.setFont(fontSystem);
        txtPassword2.setPreferredSize(new Dimension(350, 30));

        createTabel();
    }
    private void createTabel() {
        JPanel pnlTable = new JPanel();
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(pnlTable, constraints);
        pnlTable.setPreferredSize(new Dimension(650, 650));
        pnlTable.setLayout(new GridBagLayout());

        JLabel lblTitle = new JLabel("DANH SÁCH TÀI KHOẢN");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 0, 10, 0);
        pnlTable.add(lblTitle, constraints);
        lblTitle.setFont(new Font("Cambria", Font.BOLD, 25));
        lblTitle.setForeground(new Color(63, 0, 97, 255));

        tblModelAccount = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblModelAccount.setColumnIdentifiers(new String[]{"Phòng ban", "Tài khoản"});

        tblAccount = new JTable(tblModelAccount);
        tblAccount.setFont(fontSystem);
        tblAccount.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblAccount.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showDetail();
            }
        });

        JTableHeader header = tblAccount.getTableHeader();
        header.setFont(new Font("Cambria", Font.BOLD, 18));
        header.setForeground(new Color(63, 0, 97, 255));

        JScrollPane sclTbl = new JScrollPane(tblAccount);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(0, 0, 0, 0);
        pnlTable.add(sclTbl, constraints);
        sclTbl.setPreferredSize(new Dimension(650, 550));

        JPanel pnlBtn = new JPanel();
        constraints.gridx = 0;
        constraints.gridy = 2;
        pnlTable.add(pnlBtn, constraints);
        pnlBtn.setPreferredSize(new Dimension(650, 50));

        btnUpdate = new JButton("Cập nhật");
        pnlBtn.add(btnUpdate);
        btnUpdate.setFont(fontSystem);
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getBtnUpdate();
            }
        });

        btnDelete = new JButton("Xóa");
        pnlBtn.add(btnDelete);
        btnDelete.setFont(fontSystem);
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getBtnDelete();
            }
        });
    }
    private void loadDataToTable() {
        accountList.clear();

        accountList = controller.loadDataAllAccount();

        while (tblModelAccount.getRowCount() > 0) {
            tblModelAccount.removeRow(0);
        }

        for (Account acc : accountList) {
            tblModelAccount.addRow(new String[]{acc.getDepartment(), acc.getStaffId()});
        }
    }
    private void showDetail() {
        Account acc = accountList.get(tblAccount.getSelectedRow());

        txtAccount.setText(acc.getStaffId());
        txtPassword.setText(acc.getPassword());
        txtPassword2.setText(acc.getPassword());
    }
    private void getBtnDelete() {
        if (txtAccount.getText().trim().equals("")) {
            Dialog.showMessageDialogWithAutoClose("Tài khoản còn trống.", 0);
            return;
        }
        if (controller.deleteAccount(txtAccount.getText().trim())) {
            Dialog.showMessageDialogWithAutoClose("Xóa thành công.", 1);
            loadDataToTable();
            clearForm();
        } else {
            Dialog.showMessageDialogWithAutoClose("Xóa không thành công.", 0);
        }
    }
    private void getBtnUpdate() {
        if (txtAccount.getText().trim().equals("")) {
            Dialog.showMessageDialogWithAutoClose("Tài khoản còn trống.", 0);
            return;
        }
        if (txtPassword.getText().trim().equals("")) {
            Dialog.showMessageDialogWithAutoClose("Chưa nhập mật khẩu.", 0);
            return;
        }
        if (txtPassword2.getText().trim().equals("")) {
            Dialog.showMessageDialogWithAutoClose("Chưa nhập mật khẩu 2.", 0);
            return;
        }
        String password = new String(txtPassword.getPassword());
        String password2 = new String(txtPassword2.getPassword());
        if (!password.equals(password2)) {
            Dialog.showMessageDialogWithAutoClose("Mật khẩu không trùng khớp.", 0);
            return;
        }
        Account acc = new Account().setStaffId(txtAccount.getText().trim())
                                .setPassword(new String(txtPassword.getPassword()));
        if (controller.checkStaffId(acc.getStaffId())) {
            String result = controller.updateInfoAccount(acc);
            if (result.equals("ADD")) {
                Dialog.showMessageDialogWithAutoClose("Thêm thành công", 1);
                loadDataToTable();
            } else if (result.equals("UPDATE")) {
                Dialog.showMessageDialogWithAutoClose("Cập nhật thành công", 1);
                loadDataToTable();
            } else {
                Dialog.showMessageDialogWithAutoClose("Cập nhật thất bại", 0);
            }
        } else {
            Dialog.showMessageDialogWithAutoClose("Chưa có mã nhân viên này", 0);
            return;
        }
    }
    private void clearForm() {
        txtAccount.setText("");
        txtPassword.setText("");
        txtPassword2.setText("");
    }
}
