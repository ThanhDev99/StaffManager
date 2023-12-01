package View.ContentPanel;

import Controller.StaffManagerController;
import Controller.StatisticalController;
import Model.Staff;
import Model.Statistical;
import View.Help.Dialog;
import View.MainFrame;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class StatisticalPanel extends JPanel {
    private StatisticalController controller;
    private List<Statistical> list = new ArrayList<>();
    private MainFrame mainFrame;
    private GridBagConstraints constraints = new GridBagConstraints();
    private Font fontSystem = new Font("Cambria", Font.BOLD, 16);
    private JTabbedPane tabs;
    private DefaultTableModel tblModelSalary, tblModelDateWork, tblModelViolation;
    private JTable tblSalary, tblDateWork, tblViolation;
    public StatisticalPanel() {
        controller = new StatisticalController();

        setBackground(new Color(126, 102, 215));

        setBorder(new MatteBorder(0, 2, 0, 0, Color.BLACK));

        setLayout(new GridBagLayout());

        setPreferredSize(new Dimension(1100, 700));

        createGUI();

        loadDataToTableSalary();
    }
    private void createGUI() {
        tabs = new JTabbedPane();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
        add(tabs, constraints);
        tabs.setPreferredSize(new Dimension(1050, 650));
        tabs.setFont(fontSystem);

        createTabSalary();
        createTabDateWork();
        createTabViolation();
    }
    private void createTabSalary() {
        constraints.insets = new Insets(0, 0, 0, 0);
        JPanel pnlSalary = new JPanel();
        tabs.addTab("Bảng lương", pnlSalary);
        pnlSalary.setLayout(new GridBagLayout());

        tblModelSalary = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblModelSalary.setColumnIdentifiers(new String[]{"Mã NV", "Họ tên", "Tháng", "Quý", "Năm", "Số lương", "Ghi chú"});

        tblSalary = new JTable(tblModelSalary);
        tblSalary.setFont(fontSystem);
        tblSalary.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JTableHeader header = tblSalary.getTableHeader();
        header.setFont(new Font("Cambria", Font.BOLD, 18));
        header.setForeground(new Color(63, 0, 97, 255));

        JScrollPane sclTbl = new JScrollPane(tblSalary);
        constraints.gridx = 0;
        constraints.gridy = 0;
        pnlSalary.add(sclTbl, constraints);
        sclTbl.setPreferredSize(new Dimension(950, 550));

        JButton btnExport = new JButton("Xuất bảng lương");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(15, 0, 5, 0);
        constraints.anchor = GridBagConstraints.LINE_END;
        pnlSalary.add(btnExport, constraints);
        btnExport.setFont(fontSystem);
        btnExport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setBtnExportSalary();
            }
        });
    }
    private void createTabDateWork() {
        constraints.insets = new Insets(0, 0, 0, 0);
        JPanel pnlDateWork = new JPanel();
        tabs.addTab("Ngày công", pnlDateWork);
        pnlDateWork.setLayout(new GridBagLayout());

        tblModelDateWork = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblModelDateWork.setColumnIdentifiers(new String[]{"Mã NV", "Họ tên", "Tháng", "Quý", "Năm", "Ngày công", "Ghi chú"});

        tblDateWork = new JTable(tblModelDateWork);
        tblDateWork.setFont(fontSystem);
        tblDateWork.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JTableHeader header = tblDateWork.getTableHeader();
        header.setFont(new Font("Cambria", Font.BOLD, 18));
        header.setForeground(new Color(63, 0, 97, 255));

        JScrollPane sclTbl = new JScrollPane(tblDateWork);
        constraints.gridx = 0;
        constraints.gridy = 0;
        pnlDateWork.add(sclTbl, constraints);
        sclTbl.setPreferredSize(new Dimension(950, 550));

        JButton btnExport = new JButton("Xuất ngày công");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(15, 0, 5, 0);
        constraints.anchor = GridBagConstraints.LINE_END;
        pnlDateWork.add(btnExport, constraints);
        btnExport.setFont(fontSystem);
    }
    private void createTabViolation() {
        constraints.insets = new Insets(0, 0, 0, 0);
        JPanel pnlViolation = new JPanel();
        tabs.addTab("Lỗi", pnlViolation);
        pnlViolation.setLayout(new GridBagLayout());

        tblModelViolation = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblModelViolation.setColumnIdentifiers(new String[]{"Mã NV", "Họ tên", "Tháng", "Quý", "Năm", "Ghi chú"});

        tblViolation = new JTable(tblModelViolation);
        tblViolation.setFont(fontSystem);
        tblViolation.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JTableHeader header = tblViolation.getTableHeader();
        header.setFont(new Font("Cambria", Font.BOLD, 18));
        header.setForeground(new Color(63, 0, 97, 255));

        JScrollPane sclTbl = new JScrollPane(tblViolation);
        constraints.gridx = 0;
        constraints.gridy = 0;
        pnlViolation.add(sclTbl, constraints);
        sclTbl.setPreferredSize(new Dimension(950, 550));

        JButton btnExport = new JButton("Xuất vi phạm");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(15, 0, 5, 0);
        constraints.anchor = GridBagConstraints.LINE_END;
        pnlViolation.add(btnExport, constraints);
        btnExport.setFont(fontSystem);
    }
    private void loadDataToTableSalary() {
        list.clear();

        while (tblModelSalary.getRowCount() > 0) {
            tblModelSalary.removeRow(0);
        }

        list = controller.getSalary();

        for (Statistical s : list) {
            tblModelSalary.addRow(new String[]{s.getStaffId(), s.getStaffName(), s.getMonth().toString(), s.getQuarter().toString()
                    , s.getYear().toString(), String.format("%.0f", s.getSalary()), s.getNote()});
        }
    }
    private void setBtnExportSalary() {
        JFileChooser chooser = new JFileChooser();

        int result = chooser.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            loadDataToTableSalary();
            File file = chooser.getSelectedFile();

            Boolean rs = controller.exportXls(file.getPath());

            if (rs) {
                Dialog.showMessageDialogWithAutoClose("Xuất file thành công", 1);
            }
        }
        else {
            return;
        }
    }
}
