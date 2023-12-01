package View.SecondFrame;

import Controller.TaskController;
import Model.Task;
import View.Help.Dialog;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DetailTaskFrame extends JFrame {
    private TaskController taskController;
    private Font fontSystem = new Font("Cambria", Font.BOLD, 16);
    private int xOffset, yOffset;
    private GridBagConstraints constraints = new GridBagConstraints();
    private JTextField txtRegDate, txtDeadline;
    private JTextArea txtDescription;
    private JButton btnReciveTask, btnDoneTask;
    private Task task;
    public DetailTaskFrame(Task task) {
        this.task = task;

        taskController = new TaskController();

        createFrame();
    }
    private void createFrame() {
        setTitle("Chi tiết công việc");

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
        JLabel lblTitle = new JLabel(task.getName(), JLabel.CENTER);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(2, 0, 5, 0);
        add(lblTitle, constraints);
        lblTitle.setFont(new Font("Cambria", Font.BOLD, 25));
        lblTitle.setPreferredSize(new Dimension(300, 35));

        JLabel lblName = new JLabel("Ngày giao", JLabel.LEFT);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(2, 0, 0, 0);
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        add(lblName, constraints);
        lblName.setFont(fontSystem);
        lblName.setPreferredSize(new Dimension(300, 30));

        txtRegDate = new JTextField(task.getRegDate());
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(txtRegDate, constraints);
        txtRegDate.setFont(fontSystem);
        txtRegDate.setPreferredSize(new Dimension(300, 30));
        txtRegDate.setEditable(false);

        JLabel lblDeadline = new JLabel("Ngày hoàn thành");
        constraints.gridx = 0;
        constraints.gridy = 3;
        add(lblDeadline, constraints);
        lblDeadline.setFont(fontSystem);
        lblDeadline.setPreferredSize(new Dimension(300, 30));

        txtDeadline = new JTextField(task.getDeadline());
        constraints.gridx = 0;
        constraints.gridy = 4;
        add(txtDeadline, constraints);
        txtDeadline.setFont(fontSystem);
        txtDeadline.setPreferredSize(new Dimension(300, 30));
        txtDeadline.setEditable(false);

        JLabel lblDescription = new JLabel("Mô tả công việc");
        constraints.gridx = 0;
        constraints.gridy = 5;
        add(lblDescription, constraints);
        lblDescription.setFont(fontSystem);
        lblDescription.setPreferredSize(new Dimension(300, 30));

        txtDescription = new JTextArea(task.getDescription());
        JScrollPane pnlDescription = new JScrollPane(txtDescription);
        constraints.gridx = 0;
        constraints.gridy = 6;
        add(pnlDescription, constraints);
        txtDescription.setFont(fontSystem);
        pnlDescription.setPreferredSize(new Dimension(300, 200));
        txtDescription.setEditable(false);

        if (task.getStatus().equals("NOTDONE")) {
            btnReciveTask = new JButton("Nhận việc");
            constraints.gridx = 0;
            constraints.gridy = 7;
            constraints.insets = new Insets(20, 0, 5, 0);
            constraints.anchor = GridBagConstraints.FIRST_LINE_END;
            add(btnReciveTask, constraints);
            btnReciveTask.setFont(fontSystem);
            btnReciveTask.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    getBtnReciveTask();
                }
            });
        }

        if (task.getStatus().equals("DOING")) {
            btnDoneTask = new JButton("Xong việc");
            constraints.gridx = 0;
            constraints.gridy = 7;
            constraints.insets = new Insets(20, 0, 5, 0);
            constraints.anchor = GridBagConstraints.FIRST_LINE_END;
            add(btnDoneTask, constraints);
            btnDoneTask.setFont(fontSystem);
            btnDoneTask.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    getBtnDoneTask();
                }
            });
        }
    }
    private void getBtnReciveTask() {
        if (Dialog.showComfirmDialogWithAutoClose("Sẽ nhận công việc này?")) {
            task.setStatus("DOING");
            if (taskController.updateTaskS(task)) {
                Dialog.showMessageDialogWithAutoClose("Nhận thành công.", 1);
            } else {
                Dialog.showMessageDialogWithAutoClose("Nhận thất bại.", 0);
            }
        }
    }
    private void getBtnDoneTask() {
        if (Dialog.showComfirmDialogWithAutoClose("Đã hoàn thành công việc này?")) {
            task.setStatus("DONE");
            if (taskController.updateTaskS(task)) {
                Dialog.showMessageDialogWithAutoClose("Chúc mừng bạn đã hoàn thành.", 1);
            } else {
                Dialog.showMessageDialogWithAutoClose("Cập nhật thất bại, hãy thử lại..", 0);
            }
        }
    }
}
