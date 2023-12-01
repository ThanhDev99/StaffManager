package View.SecondFrame;

import Controller.TaskController;
import Model.ShareData;
import Model.Staff;
import Model.Task;
import View.Help.Dialog;
import View.MainPanel;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class AddTaskFrame extends JFrame {
    private TaskController controller;
    private Map<String, String> staffMap = new HashMap<>();
    private List<String> staffList = new LinkedList<>();
    private Font fontSystem = new Font("Cambria", Font.BOLD, 16);
    private int xOffset, yOffset;
    private GridBagConstraints constraints = new GridBagConstraints();
    private JTextField txtName, txtStaff;
    private JTextArea txtDescription;
    private JButton btnSendTask;
    private JDateChooser dcDeadline;
    private JComboBox cbxStaff;
    private String staff;
    public AddTaskFrame() {
        controller = new TaskController();

        staff = "";

        createFrame();

        loadDataToCbx();
    }
    private void createFrame() {
        setTitle("Thêm công việc");

        setLayout(new GridBagLayout());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setSize(350, 510);

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
        JLabel lblTitle = new JLabel("CÔNG VIỆC MỚI", JLabel.CENTER);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(2, 0, 5, 0);
        add(lblTitle, constraints);
        lblTitle.setFont(new Font("Cambria", Font.BOLD, 25));
        lblTitle.setPreferredSize(new Dimension(300, 35));

        JLabel lblName = new JLabel("Tên công việc", JLabel.LEFT);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(2, 0, 0, 0);
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        add(lblName, constraints);
        lblName.setFont(fontSystem);
        lblName.setPreferredSize(new Dimension(300, 30));

        txtName = new JTextField();
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(txtName, constraints);
        txtName.setFont(fontSystem);
        txtName.setPreferredSize(new Dimension(300, 30));

        JLabel lblDeadline = new JLabel("Ngày hoàn thành");
        constraints.gridx = 0;
        constraints.gridy = 3;
        add(lblDeadline, constraints);
        lblDeadline.setFont(fontSystem);
        lblDeadline.setPreferredSize(new Dimension(300, 30));

        dcDeadline = new JDateChooser();
        constraints.gridx = 0;
        constraints.gridy = 4;
        add(dcDeadline, constraints);
        dcDeadline.setFont(fontSystem);
        dcDeadline.setPreferredSize(new Dimension(300, 30));
        dcDeadline.setDateFormatString("dd-MM-yyyy");

        JLabel lblStaff = new JLabel("Nhân viên thực hiện");
        constraints.gridx = 0;
        constraints.gridy = 5;
        add(lblStaff, constraints);
        lblStaff.setFont(fontSystem);
        lblStaff.setPreferredSize(new Dimension(300, 30));

        txtStaff = new JTextField();
        constraints.gridx = 0;
        constraints.gridy = 6;
        add(txtStaff, constraints);
        txtStaff.setFont(fontSystem);
        txtStaff.setPreferredSize(new Dimension(300, 30));

        cbxStaff = new JComboBox(new String[]{});
        constraints.gridx = 0;
        constraints.gridy = 7;
        add(cbxStaff, constraints);
        cbxStaff.setFont(fontSystem);
        cbxStaff.setPreferredSize(new Dimension(300, 30));
        cbxStaff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStaff(cbxStaff.getSelectedItem().toString());
            }
        });

        JLabel lblDescription = new JLabel("Mô tả công việc");
        constraints.gridx = 0;
        constraints.gridy = 8;
        add(lblDescription, constraints);
        lblDescription.setFont(fontSystem);
        lblDescription.setPreferredSize(new Dimension(300, 30));

        txtDescription = new JTextArea();
        JScrollPane pnlDescription = new JScrollPane(txtDescription);
        constraints.gridx = 0;
        constraints.gridy = 9;
        add(pnlDescription, constraints);
        txtDescription.setFont(fontSystem);
        pnlDescription.setPreferredSize(new Dimension(300, 130));

        btnSendTask = new JButton("Gửi công việc");
        constraints.gridx = 0;
        constraints.gridy = 10;
        constraints.anchor = GridBagConstraints.FIRST_LINE_END;
        add(btnSendTask, constraints);
        btnSendTask.setFont(fontSystem);
        btnSendTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getBtnSendTask();
            }
        });
    }
    private void loadDataToCbx() {
        staffMap = controller.getStaffByManager(ShareData.account.getStaffId());

        List<String> list = new ArrayList<>();
        for (String s : staffMap.values()) {
            list.add(s);
        }
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(list.toArray(new String[0]));
        cbxStaff.setModel(model);
    }
    private void addStaff(String name) {
        System.out.println(name);
        for (Map.Entry<String, String> entry : staffMap.entrySet()) {
            if (staffList.size() == 0) {
                if (entry.getValue().equals(name)) {
                    staffList.add(entry.getKey());
                    staff = staff + entry.getKey();
                }
            } else {
                if (entry.getValue().equals(name)) {
                    staffList.add(entry.getKey());
                    staff = staff + ", " + entry.getKey();
                }
            }
        }
        txtStaff.setText(staff);
    }
    private void getBtnSendTask() {
        if (txtName.getText().trim().isEmpty()) {
            Dialog.showMessageDialogWithAutoClose("Tên công việc còn trống.", 1);
            return;
        }
        if (txtStaff.getText().trim().isEmpty()) {
            Dialog.showMessageDialogWithAutoClose("Chưa chọn nhân viên.", 1);
            return;
        }
        if (txtDescription.getText().trim().isEmpty()) {
            Dialog.showMessageDialogWithAutoClose("Chưa nhập mô tả công việc.", 1);
            return;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (simpleDateFormat.format(dcDeadline.getDate()).isEmpty()) {
            Dialog.showMessageDialogWithAutoClose("Chưa chọn ngày hoàn thành.", 0);
            return;
        }
        Task task = new Task().setName(txtName.getText().trim())
                .setDeadline(simpleDateFormat.format(dcDeadline.getDate()))
                .setDescription(txtDescription.getText().trim())
                .setRegDate(simpleDateFormat.format(new Date()))
                .setStatus("NOTDONE");
        if (controller.addTask(task, staffList)) {
            Dialog.showMessageDialogWithAutoClose("Gửi thành công", 1);
            setVisible(false);
        } else {
            Dialog.showMessageDialogWithAutoClose("Gửi không thành công", 0);
        }

    }
}
