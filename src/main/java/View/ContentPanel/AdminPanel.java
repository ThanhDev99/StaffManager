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

public class AdminPanel extends JPanel {
    private List<Account> accountList = new ArrayList<>();
    private AccountController controller;
    private MainFrame mainFrame;
    private GridBagConstraints constraints = new GridBagConstraints();
    private Font fontSystem = new Font("Cambria", Font.BOLD, 16);
    private JTextField txtAccount;
    private JCheckBox chxCheckIn, chxTask, chxRequest, chxStaff, chxStaffManager, chxStatistical, chxAccount
            , chxAdmin, chxManager, chxChat;
    private DefaultTableModel tblModelAccount;
    private JTable tblAccount;
    private JButton btnUpdate;
    public AdminPanel() {
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
        pnlForm.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                loadDataToTable();
            }
        });

        JLabel lblTitle = new JLabel("DANH SÁCH CHỨC NĂNG");
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

        txtAccount = new JTextField("");
        constraints.gridx = 0;
        constraints.gridy = 2;
        pnlForm.add(txtAccount, constraints);
        txtAccount.setFont(fontSystem);
        txtAccount.setPreferredSize(new Dimension(350, 30));

        chxCheckIn = new JCheckBox("Điểm danh");
        constraints.gridx = 0;
        constraints.gridy = 3;
        pnlForm.add(chxCheckIn, constraints);
        chxCheckIn.setFont(fontSystem);
        chxCheckIn.setPreferredSize(new Dimension(350, 30));

        chxTask = new JCheckBox("Công việc");
        constraints.gridx = 0;
        constraints.gridy = 4;
        pnlForm.add(chxTask, constraints);
        chxTask.setFont(fontSystem);
        chxTask.setPreferredSize(new Dimension(350, 30));

        chxRequest = new JCheckBox("Yêu cầu");
        constraints.gridx = 0;
        constraints.gridy = 5;
        pnlForm.add(chxRequest, constraints);
        chxRequest.setFont(fontSystem);
        chxRequest.setPreferredSize(new Dimension(350, 30));

        chxStaff = new JCheckBox("Nhân viên");
        constraints.gridx = 0;
        constraints.gridy = 6;
        pnlForm.add(chxStaff, constraints);
        chxStaff.setFont(fontSystem);
        chxStaff.setPreferredSize(new Dimension(350, 30));

        chxStaffManager = new JCheckBox("Quản lý nhân viên");
        constraints.gridx = 0;
        constraints.gridy = 7;
        pnlForm.add(chxStaffManager, constraints);
        chxStaffManager.setFont(fontSystem);
        chxStaffManager.setPreferredSize(new Dimension(350, 30));

        chxStatistical = new JCheckBox("Thống kê");
        constraints.gridx = 0;
        constraints.gridy = 8;
        pnlForm.add(chxStatistical, constraints);
        chxStatistical.setFont(fontSystem);
        chxStatistical.setPreferredSize(new Dimension(350, 30));

        chxAccount = new JCheckBox("Tài khoản");
        constraints.gridx = 0;
        constraints.gridy = 9;
        pnlForm.add(chxAccount, constraints);
        chxAccount.setFont(fontSystem);
        chxAccount.setPreferredSize(new Dimension(350, 30));

        chxAdmin = new JCheckBox("Admin");
        constraints.gridx = 0;
        constraints.gridy = 10;
        pnlForm.add(chxAdmin, constraints);
        chxAdmin.setFont(fontSystem);
        chxAdmin.setPreferredSize(new Dimension(350, 30));

        chxManager = new JCheckBox("Trưởng phòng");
        constraints.gridx = 0;
        constraints.gridy = 11;
        pnlForm.add(chxManager, constraints);
        chxManager.setFont(fontSystem);
        chxManager.setPreferredSize(new Dimension(350, 30));

        chxChat = new JCheckBox("Trưởng phòng");
        constraints.gridx = 0;
        constraints.gridy = 12;
        pnlForm.add(chxChat, constraints);
        chxChat.setFont(fontSystem);
        chxChat.setPreferredSize(new Dimension(350, 30));

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
        sclTbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                loadDataToTable();
            }
        });

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
        chxCheckIn.setSelected(acc.getCheckIn());
        chxRequest.setSelected(acc.getRequest());
        chxAccount.setSelected(acc.getAccount());
        chxTask.setSelected(acc.getTask());
        chxStatistical.setSelected(acc.getStatistical());
        chxStaffManager.setSelected(acc.getStaffManager());
        chxStaff.setSelected(acc.getStaff());
        chxManager.setSelected(acc.getIsManager());
        chxManager.setSelected(acc.getChat());
    }
    private void getBtnUpdate() {
        if (txtAccount.getText().trim().equals("")) {
            Dialog.showMessageDialogWithAutoClose("Tên tài khoản còn trống", 0);
            return;
        }
        Account acc = new Account()
                .setStaffId(txtAccount.getText().trim())
                .setCheckIn(chxCheckIn.isSelected())
                .setRequest(chxRequest.isSelected())
                .setAccount(chxAccount.isSelected())
                .setTask(chxTask.isSelected())
                .setStatistical(chxStatistical.isSelected())
                .setStaffManager(chxStaffManager.isSelected())
                .setStaff(chxStaff.isSelected())
                .setAdmin(chxAdmin.isSelected())
                .setIsManager(chxManager.isSelected())
                .setChat(chxChat.isSelected());

        if (controller.updateAccount(acc)) {
            Dialog.showMessageDialogWithAutoClose("Cập nhật thành công.", 1);
            loadDataToTable();
        } else {
            Dialog.showMessageDialogWithAutoClose("Cập nhật không thành công.", 0);
        }
    }
}
