package View.ContentPanel;

import Controller.TaskController;
import Model.ShareData;
import Model.Task;
import View.Help.Dialog;
import View.MainFrame;
import View.SecondFrame.AddTaskFrame;
import View.SecondFrame.DetailTaskFrame;
import lombok.Builder;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class TaskPanel extends JPanel {
    private TaskController taskController;
    private List<Task> listTask = new ArrayList<>();
    private MainFrame mainFrame;
    private GridBagConstraints constraints = new GridBagConstraints();
    private Font fontSystem = new Font("Cambria", Font.BOLD, 16);
    private JButton btnDeleteTask, btnAddTask;
    private DefaultTableModel tblModelTaskNotDone, tblModelTaskDoing, tblModelTaskDone;
    private JTable tblTaskNotDone, tblTaskDoing, tblTaskDone;
    public TaskPanel() {
        taskController = new TaskController();

        setBackground(new Color(126, 102, 215));

        setBorder(new MatteBorder(0, 2, 0, 0, Color.BLACK));

        setLayout(new GridBagLayout());

        setPreferredSize(new Dimension(1100, 700));

        createGUI();

        loadTask();
    }
    private void createGUI() {
        tblModelTaskNotDone = new DefaultTableModel();
        tblModelTaskNotDone.setColumnIdentifiers(new String[]{"Mã", "Tên công việc"});
        tblTaskNotDone = new JTable(tblModelTaskNotDone);
        tblTaskNotDone.setFont(fontSystem);
        JScrollPane pnlNotDone = new JScrollPane(tblTaskNotDone);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
        add(pnlNotDone, constraints);
        pnlNotDone.setPreferredSize(new Dimension(250, 400));
        pnlNotDone.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1), "Chưa làm", 2, 1,
                new Font("Cambria", Font.BOLD, 25)));
        JTableHeader headerNotDone = tblTaskNotDone.getTableHeader();
        headerNotDone.setFont(new Font("Cambria", Font.BOLD, 18));
        headerNotDone.setForeground(new Color(63, 0, 97, 255));
        tblTaskNotDone.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tblTaskDoing.clearSelection();
                tblTaskDone.clearSelection();
                showDetail(1);
            }
        });

        tblModelTaskDoing = new DefaultTableModel();
        tblModelTaskDoing.setColumnIdentifiers(new String[]{"Mã", "Tên công việc"});
        tblTaskDoing = new JTable(tblModelTaskDoing);
        tblTaskDoing.setFont(fontSystem);
        JScrollPane pnlDoing = new JScrollPane(tblTaskDoing);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
        add(pnlDoing, constraints);
        pnlDoing.setPreferredSize(new Dimension(250, 400));
        pnlDoing.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1), "Đang làm", 2, 1,
                new Font("Cambria", Font.BOLD, 25)));
        JTableHeader headerDoing = tblTaskDoing.getTableHeader();
        headerDoing.setFont(new Font("Cambria", Font.BOLD, 18));
        headerDoing.setForeground(new Color(63, 0, 97, 255));
        tblTaskDoing.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tblTaskNotDone.clearSelection();
                tblTaskDone.clearSelection();
                showDetail(2);
            }
        });

        tblModelTaskDone = new DefaultTableModel();
        tblModelTaskDone.setColumnIdentifiers(new String[]{"Mã", "Tên công việc"});
        tblTaskDone = new JTable(tblModelTaskDone);
        tblTaskDone.setFont(fontSystem);
        JScrollPane pnlDone = new JScrollPane(tblTaskDone);
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
        add(pnlDone, constraints);
        pnlDone.setPreferredSize(new Dimension(250, 400));
        pnlDone.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1), "Đã xong", 2, 1,
                new Font("Cambria", Font.BOLD, 25)));
        JTableHeader headerDone = tblTaskDone.getTableHeader();
        headerDone.setFont(new Font("Cambria", Font.BOLD, 18));
        headerDone.setForeground(new Color(63, 0, 97, 255));
        tblTaskDone.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tblTaskNotDone.clearSelection();
                tblTaskDoing.clearSelection();
                showDetail(3);
            }
        });

        if (ShareData.account.getIsManager()) {
            JPanel pnlBtn = new JPanel();
            constraints.gridx = 0;
            constraints.gridy = 1;
            constraints.gridwidth = 3;
            constraints.anchor = GridBagConstraints.LINE_END;
            constraints.insets = new Insets(20, 5, 5, 5);
            add(pnlBtn, constraints);
            pnlBtn.setBackground(new Color(126, 102, 215));

            btnDeleteTask = new JButton("Xóa công việc");
            pnlBtn.add(btnDeleteTask);
            btnDeleteTask.setFont(fontSystem);
            btnDeleteTask.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    getBtnDeleteTask();
                }
            });

            btnAddTask = new JButton("Thêm công việc");
            pnlBtn.add(btnAddTask);
            btnAddTask.setFont(fontSystem);
            btnAddTask.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    getBtnAddTask();
                }
            });
        }
    }
    private void loadTask() {
        listTask.clear();

        if (!ShareData.account.getIsManager()) {
            listTask = taskController.getTaskByStaffId();
        } else {
            listTask = taskController.getTaskByManager();
        }

        while (tblModelTaskNotDone.getRowCount() > 0) {
            tblModelTaskNotDone.removeRow(0);
        }
        while (tblModelTaskDoing.getRowCount() > 0) {
            tblModelTaskNotDone.removeRow(0);
        }
        while (tblModelTaskDone.getRowCount() > 0) {
            tblModelTaskNotDone.removeRow(0);
        }

        for (Task t : listTask) {
            if (t.getStatus().equals("NOTDONE")) {
                tblModelTaskNotDone.addRow(new String[]{t.getId().toString(), t.getName()});
            }
            if (t.getStatus().equals("DOING")) {
                tblModelTaskDoing.addRow(new String[]{t.getId().toString(), t.getName()});
            }
            if (t.getStatus().equals("DONE")) {
                tblModelTaskDone.addRow(new String[]{t.getId().toString(), t.getName()});
            }
        }
    }
    private void getBtnDeleteTask() {
        if (Dialog.showComfirmDialogWithAutoClose("Sẽ xóa công việc này?")) {
            if (tblTaskNotDone.getSelectedRow() != 0) {
                for (Task t : listTask) {
                    if (t.getId().equals(tblTaskNotDone.getValueAt(tblTaskNotDone.getSelectedRow(), 0))) {
                        if (taskController.deleteTaskById(t.getId())) {
                            Dialog.showMessageDialogWithAutoClose("Xóa công việc thành công.", 1);
                            loadTask();
                        } else {
                            Dialog.showMessageDialogWithAutoClose("Xóa công việc không thành công.", 0);
                        }
                    }
                }
            }
            if (tblTaskDoing.getSelectedRow() != 0) {
                for (Task t : listTask) {
                    if (t.getId().equals(tblTaskDoing.getValueAt(tblTaskDoing.getSelectedRow(), 0))) {
                        if (taskController.deleteTaskById(t.getId())) {
                            Dialog.showMessageDialogWithAutoClose("Xóa công việc thành công.", 1);
                            loadTask();
                        } else {
                            Dialog.showMessageDialogWithAutoClose("Xóa công việc không thành công.", 0);
                        }
                    }
                }
            }
            if (tblTaskDone.getSelectedRow() != 0) {
                for (Task t : listTask) {
                    if (t.getId().equals(tblTaskDone.getValueAt(tblTaskDone.getSelectedRow(), 0))) {
                        if (taskController.deleteTaskById(t.getId())) {
                            Dialog.showMessageDialogWithAutoClose("Xóa công việc thành công.", 1);
                            loadTask();
                        } else {
                            Dialog.showMessageDialogWithAutoClose("Xóa công việc không thành công.", 0);
                        }
                    }
                }
            }
        }
    }
    private void getBtnAddTask() {
        AddTaskFrame addTaskFrame = new AddTaskFrame();
    }
    private void showDetail(int indexTable) {
        if (indexTable == 1) {
            for (Task t : listTask) {
                if (t.getId().equals(tblTaskNotDone.getValueAt(tblTaskNotDone.getSelectedRow(), 0))) {
                    DetailTaskFrame detailTaskFrame = new DetailTaskFrame(t);
                }
            }
        }
        if (indexTable == 2) {
            for (Task t : listTask) {
                if (t.getId().equals(tblTaskDoing.getValueAt(tblTaskDoing.getSelectedRow(), 0))) {
                    DetailTaskFrame detailTaskFrame = new DetailTaskFrame(t);
                }
            }
        }
        if (indexTable == 3) {
            for (Task t : listTask) {
                if (t.getId().equals(tblTaskDone.getValueAt(tblTaskDone.getSelectedRow(), 0))) {
                    DetailTaskFrame detailTaskFrame = new DetailTaskFrame(t);
                }
            }
        }
    }
}
