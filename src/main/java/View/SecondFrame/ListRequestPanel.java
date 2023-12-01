package View.SecondFrame;

import Controller.RequestController;
import Model.Request;
import View.Help.Dialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ListRequestPanel extends JFrame {
    private RequestController controller;
    private List<Request> list = new ArrayList<>();
    private Font fontSystem = new Font("Cambria", Font.BOLD, 16);
    private GridBagConstraints constraints = new GridBagConstraints();
    private int xOffset, yOffset;
    private JButton btnAccept, btnCancle;
    private DefaultTableModel tblModelRequest;
    private JTable tblRequest;
    private JLabel lblDepartmentSend, lblDepartmentReceive, lblDateSend;
    private JTextArea txtContent;
    private int index;
    public ListRequestPanel() {
        controller = new RequestController();

        createFrame();

        loadDataToTable();
    }
    private void createFrame() {
        setTitle("Danh sách yêu cầu");

        setLayout(new GridBagLayout());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setSize(570, 470);

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
        createForm();

        createTable();
    }
    private void createForm() {
        JLabel lblTitle = new JLabel("CHI TIẾT YÊU CẦU", JLabel.CENTER);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(2, 0, 5, 0);
        add(lblTitle, constraints);
        lblTitle.setFont(new Font("Cambria", Font.BOLD, 25));
        lblTitle.setForeground(new Color(63, 0, 97, 255));
        lblTitle.setPreferredSize(new Dimension(250, 30));

        lblDepartmentSend = new JLabel("Phòng ban gửi: ");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(2, 0, 0, 0);
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        add(lblDepartmentSend, constraints);
        lblDepartmentSend.setFont(fontSystem);
        lblDepartmentSend.setPreferredSize(new Dimension(250, 30));

        lblDepartmentReceive = new JLabel("Phòng ban nhận: ");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        add(lblDepartmentReceive, constraints);
        lblDepartmentReceive.setFont(fontSystem);
        lblDepartmentReceive.setPreferredSize(new Dimension(250, 30));

        lblDateSend = new JLabel("Ngày gửi: ");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        add(lblDateSend, constraints);
        lblDateSend.setFont(fontSystem);
        lblDateSend.setPreferredSize(new Dimension(250, 30));

        JLabel lblContent = new JLabel("Nội dung");
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        add(lblContent, constraints);
        lblContent.setFont(fontSystem);
        lblContent.setPreferredSize(new Dimension(250, 30));

        txtContent = new JTextArea();
        JScrollPane pnlContent = new JScrollPane(txtContent);
        constraints.gridx = 0;
        constraints.gridy = 5;
        add(pnlContent, constraints);
        txtContent.setFont(fontSystem);
        pnlContent.setPreferredSize(new Dimension(250, 150));
        txtContent.setEditable(false);

        JPanel btn = new JPanel();
        constraints.gridx = 0;
        constraints.gridy = 6;
        add(btn, constraints);
        btn.setPreferredSize(new Dimension(250, 50));

        btnAccept = new JButton("Xác nhận");
        btn.add(btnAccept);
        btnAccept.setFont(fontSystem);
        btnAccept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getBtnAccept();
            }
        });

        btnCancle = new JButton("Từ chối");
        btn.add(btnCancle);
        btnCancle.setFont(fontSystem);
        btnCancle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getBtnCancle();
            }
        });
    }
    private void createTable() {
        JLabel lblTitle = new JLabel("DANH SÁCH YÊU CẦU");
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(2, 10, 5, 0);
        add(lblTitle, constraints);
        lblTitle.setFont(new Font("Cambria", Font.BOLD, 25));
        lblTitle.setForeground(new Color(63, 0, 97, 255));
        lblTitle.setPreferredSize(new Dimension(250, 30));

        tblModelRequest = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblModelRequest.setColumnIdentifiers(new String[]{"Nội dung"});

        tblRequest = new JTable(tblModelRequest);
        tblRequest.setFont(fontSystem);
        tblRequest.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblRequest.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showDetail(tblRequest.getSelectedRow());
            }
        });

        JTableHeader header = tblRequest.getTableHeader();
        header.setFont(new Font("Cambria", Font.BOLD, 18));
        header.setForeground(new Color(63, 0, 97, 255));

        JScrollPane sclTbl = new JScrollPane(tblRequest);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridheight = 6;
        constraints.insets = new Insets(0, 10, 0, 0);
        add(sclTbl, constraints);
        sclTbl.setPreferredSize(new Dimension(280, 300));
    }
    private void loadDataToTable() {
        list.clear();

        list = controller.getRequestByManager();

        while (tblModelRequest.getRowCount() > 0) {
            tblModelRequest.removeRow(0);
        }

        for (Request r : list) {
            tblModelRequest.addRow(new String[]{r.getContent()});
        }
    }
    private void showDetail(int i) {
        index = i;
        Request r = list.get(index);
        lblDepartmentSend.setText("Phòng ban gửi: " + r.getDepartmentSend());
        lblDepartmentReceive.setText("Phòng ban nhận: " + r.getDepartmentReceive());
        lblDateSend.setText("Ngày gửi: " + r.getDateSend());
        txtContent.setText(r.getContent());
    }
    private void getBtnAccept() {
        Request request = list.get(index);
        request.setStatus("RECEIVED");
        if (controller.updateStatus(request)) {
            Dialog.showMessageDialogWithAutoClose("Đã chấp nhận yêu cầu.", 1);
            loadDataToTable();
        } else {
            Dialog.showMessageDialogWithAutoClose("Chấp nhận yêu cầu thất bại.", 0);
        }
    }
    private void getBtnCancle() {
        Request request = list.get(index);
        request.setStatus("CANCELED");
        if (controller.updateStatus(request)) {
            Dialog.showMessageDialogWithAutoClose("Đã từ chối yêu cầu.", 1);
            loadDataToTable();
        } else {
            Dialog.showMessageDialogWithAutoClose("Từ chối yêu cầu thất bại.", 0);
        }
    }
}
