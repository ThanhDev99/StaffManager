package View.ContentPanel;

import Controller.StaffManagerController;
import Model.Staff;
import View.Help.Dialog;
import View.MainFrame;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StaffManagerPanel extends JPanel {
    private StaffManagerController controller;
    private List<Staff> staffList = new ArrayList<>();
    private MainFrame mainFrame;
    private GridBagConstraints constraints = new GridBagConstraints();
    private Font fontSystem = new Font("Cambria", Font.BOLD, 16);
    private JLabel lblImage;
    private JTextField txtId, txtName, txtPhone, txtEmail, txtAddress, txtCountry;
    private JDateChooser dcDayStart, dcBirthday;
    private DefaultTableModel tblModelStaff;
    private JTable tblStaff;
    private JButton btnUpdate, btnDelete;
    private String urlImage;
    private JComboBox cbxDepartment, cbxSex;
    public StaffManagerPanel() {
        controller = new StaffManagerController();

        setBackground(new Color(126, 102, 215));

        setBorder(new MatteBorder(0, 2, 0, 0, Color.BLACK));

        setLayout(new GridBagLayout());

        setPreferredSize(new Dimension(1100, 700));

        createGUI();

        loadDataToTable();
    }
    private void createGUI() {
        createForm();

        createTabel();
    }
    private void createForm() {
        JPanel pnlForm = new JPanel();
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(pnlForm, constraints);
        pnlForm.setPreferredSize(new Dimension(550, 650));
        pnlForm.setBorder(new MatteBorder(0, 0, 0, 2, Color.BLACK));
        pnlForm.setLayout(new GridBagLayout());

        JLabel lblTitle = new JLabel("THÔNG TIN NHÂN VIÊN");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(5, 0, 10, 0);
        pnlForm.add(lblTitle, constraints);
        lblTitle.setFont(new Font("Cambria", Font.BOLD, 25));
        lblTitle.setForeground(new Color(63, 0, 97, 255));

        JPopupMenu optionInmage = new JPopupMenu();

        JMenuItem seenImage = new JMenuItem("Xem ảnh");
        seenImage.setFont(fontSystem);
        optionInmage.add(seenImage);
        seenImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getSeenImage(txtId.getText().trim() + ".png");
            }
        });

        JMenuItem uploadImage = new JMenuItem("Sửa ảnh");
        uploadImage.setFont(fontSystem);
        optionInmage.add(uploadImage);
        uploadImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getUploadImage();
            }
        });

        JMenuItem deleteImage = new JMenuItem("Xóa ảnh");
        deleteImage.setFont(fontSystem);
        optionInmage.add(deleteImage);
        deleteImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getDeleteImage();
            }
        });

        lblImage = new JLabel();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 6;
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        pnlForm.add(lblImage, constraints);
        lblImage.setPreferredSize(new Dimension(150, 200));
        lblImage.setBorder(new CompoundBorder(new BevelBorder(1), new BevelBorder(0)));
        lblImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                optionInmage.show(lblImage, e.getX(), e.getY());
            }
        });

        JLabel lblId = new JLabel("Mã nhân viên: ", JLabel.LEFT);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridheight = 1;
        constraints.insets = new Insets(0, 2, 2, 2);
        pnlForm.add(lblId, constraints);
        lblId.setFont(fontSystem);
        lblId.setPreferredSize(new Dimension(150, 30));

        JLabel lblName = new JLabel("Tên nhân viên: ", JLabel.LEFT);
        constraints.gridx = 1;
        constraints.gridy = 3;
        pnlForm.add(lblName, constraints);
        lblName.setFont(fontSystem);
        lblName.setPreferredSize(new Dimension(150, 30));

        JLabel lblBirthday = new JLabel("Ngày sinh: ", JLabel.LEFT);
        constraints.gridx = 1;
        constraints.gridy = 5;
        pnlForm.add(lblBirthday, constraints);
        lblBirthday.setFont(fontSystem);
        lblBirthday.setPreferredSize(new Dimension(150, 30));

        txtId = new JTextField("");
        constraints.gridx = 1;
        constraints.gridy = 2;
        pnlForm.add(txtId, constraints);
        txtId.setFont(fontSystem);
        txtId.setPreferredSize(new Dimension(350, 30));

        txtName = new JTextField("");
        constraints.gridx = 1;
        constraints.gridy = 4;
        pnlForm.add(txtName, constraints);
        txtName.setFont(fontSystem);
        txtName.setPreferredSize(new Dimension(350, 30));

        dcBirthday = new JDateChooser();
        constraints.gridx = 1;
        constraints.gridy = 6;
        pnlForm.add(dcBirthday, constraints);
        dcBirthday.setFont(fontSystem);
        dcBirthday.setPreferredSize(new Dimension(350, 30));
        dcBirthday.setDateFormatString("dd-MM-yyyy");

        JLabel lblSex = new JLabel("Giới tính:", JLabel.LEFT);
        constraints.gridx = 0;
        constraints.gridy = 7;
        pnlForm.add(lblSex, constraints);
        lblSex.setFont(fontSystem);
        lblSex.setPreferredSize(new Dimension(150 ,30));

        cbxSex = new JComboBox(new String[]{"Nam", "Nữ"});
        constraints.gridx = 0;
        constraints.gridy = 8;
        pnlForm.add(cbxSex, constraints);
        cbxSex.setFont(fontSystem);
        cbxSex.setPreferredSize(new Dimension(150, 30));

        JLabel lblPhone = new JLabel("Số điện thoại:", JLabel.LEFT);
        constraints.gridx = 1;
        constraints.gridy = 7;
        constraints.gridwidth = 1;
        pnlForm.add(lblPhone, constraints);
        lblPhone.setFont(fontSystem);
        lblPhone.setPreferredSize(new Dimension(350 ,30));

        txtPhone = new JTextField("");
        constraints.gridx = 1;
        constraints.gridy = 8;
        pnlForm.add(txtPhone, constraints);
        txtPhone.setFont(fontSystem);
        txtPhone.setPreferredSize(new Dimension(350, 30));

        JLabel lblEmail = new JLabel("Email:", JLabel.LEFT);
        constraints.gridx = 0;
        constraints.gridy = 9;
        constraints.gridwidth = 2;
        pnlForm.add(lblEmail, constraints);
        lblEmail.setFont(fontSystem);
        lblEmail.setPreferredSize(new Dimension(500 ,30));

        txtEmail = new JTextField("");
        constraints.gridx = 0;
        constraints.gridy = 10;
        pnlForm.add(txtEmail, constraints);
        txtEmail.setFont(fontSystem);
        txtEmail.setPreferredSize(new Dimension(500, 30));

        JLabel lblAddress = new JLabel("Địa chỉ:", JLabel.LEFT);
        constraints.gridx = 0;
        constraints.gridy = 11;
        pnlForm.add(lblAddress, constraints);
        lblAddress.setFont(fontSystem);
        lblAddress.setPreferredSize(new Dimension(500 ,30));

        txtAddress = new JTextField("");
        constraints.gridx = 0;
        constraints.gridy = 12;
        pnlForm.add(txtAddress, constraints);
        txtAddress.setFont(fontSystem);
        txtAddress.setPreferredSize(new Dimension(500, 30));

        JLabel lblCountry = new JLabel("Quê quán:", JLabel.LEFT);
        constraints.gridx = 0;
        constraints.gridy = 13;
        pnlForm.add(lblCountry, constraints);
        lblCountry.setFont(fontSystem);
        lblCountry.setPreferredSize(new Dimension(500 ,30));

        txtCountry = new JTextField("");
        constraints.gridx = 0;
        constraints.gridy = 14;
        pnlForm.add(txtCountry, constraints);
        txtCountry.setFont(fontSystem);
        txtCountry.setPreferredSize(new Dimension(500, 30));

        JLabel lblDateStart = new JLabel("Ngày vào làm:", JLabel.LEFT);
        constraints.gridx = 0;
        constraints.gridy = 15;
        pnlForm.add(lblDateStart, constraints);
        lblDateStart.setFont(fontSystem);
        lblDateStart.setPreferredSize(new Dimension(500 ,30));

        dcDayStart = new JDateChooser();
        constraints.gridx = 0;
        constraints.gridy = 16;
        pnlForm.add(dcDayStart, constraints);
        dcDayStart.setFont(fontSystem);
        dcDayStart.setPreferredSize(new Dimension(500, 30));
        dcDayStart.setDateFormatString("dd-MM-yyyy");

        JLabel lblDepartment = new JLabel("Phòng ban:", JLabel.LEFT);
        constraints.gridx = 0;
        constraints.gridy = 17;
        pnlForm.add(lblDepartment, constraints);
        lblDepartment.setFont(fontSystem);
        lblDepartment.setPreferredSize(new Dimension(500 ,30));

        cbxDepartment = new JComboBox(new String[]{"1", "2", "3"});
        constraints.gridx = 0;
        constraints.gridy = 18;
        pnlForm.add(cbxDepartment, constraints);
        cbxDepartment.setFont(fontSystem);
        cbxDepartment.setPreferredSize(new Dimension(500, 30));
    }
    private void createTabel() {
        JPanel pnlTable = new JPanel();
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(pnlTable, constraints);
        pnlTable.setPreferredSize(new Dimension(650, 650));
        pnlTable.setLayout(new GridBagLayout());

        JLabel lblTitle = new JLabel("DANH SÁCH NHÂN VIÊN");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 0, 10, 0);
        pnlTable.add(lblTitle, constraints);
        lblTitle.setFont(new Font("Cambria", Font.BOLD, 25));
        lblTitle.setForeground(new Color(63, 0, 97, 255));

        tblModelStaff = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblModelStaff.setColumnIdentifiers(new String[]{"Mã nhân viên", "Tên", "Ngày sinh", "Phòng ban"});

        tblStaff = new JTable(tblModelStaff);
        tblStaff.setFont(fontSystem);
        tblStaff.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblStaff.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showDetail();
            }
        });

        JTableHeader header = tblStaff.getTableHeader();
        header.setFont(new Font("Cambria", Font.BOLD, 18));
        header.setForeground(new Color(63, 0, 97, 255));

        JScrollPane sclTbl = new JScrollPane(tblStaff);
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
        staffList.clear();

        while (tblModelStaff.getRowCount() > 0) {
            tblModelStaff.removeRow(0);
        }

        staffList = controller.getAllStaff();

        for (Staff s : staffList) {
            tblModelStaff.addRow(new String[]{s.getId(), s.getName(), s.getBirthday(), s.getDepartment()});
        }
    }
    private void showDetail() {
        Staff s = staffList.get(tblStaff.getSelectedRow());

        txtId.setText(s.getId());
        txtName.setText(s.getName());
        cbxDepartment.setSelectedItem(s.getDepartment());
        txtCountry.setText(s.getCountry());
        txtAddress.setText(s.getAddress());
        txtEmail.setText(s.getEmail());
        txtPhone.setText(s.getPhone());
        cbxSex.setSelectedItem(s.getSex() ? "Nam" : "Nữ");

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            dcDayStart.setDate(dateFormat.parse(s.getDateStart()));
            dcBirthday.setDate(dateFormat.parse(s.getBirthday()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        showImage(s.getImage());
    }
    private void showImage(String url) {
        urlImage = url;
        ImageIcon imageIcon = new ImageIcon("images/staff/" + urlImage);
        ImageIcon imageIconResize = new ImageIcon(imageIcon.getImage().getScaledInstance(210, 280, 0));
        lblImage.setIcon(imageIconResize);
    }
    private void getSeenImage(String urlImage) {
        ImageIcon imageIcon = new ImageIcon("images/staff/" + urlImage);

        JFrame fullscreenFrame = new JFrame("Xem ảnh");
        fullscreenFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fullscreenFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JLabel fullscreenLabel = new JLabel(imageIcon);
        fullscreenLabel.setHorizontalAlignment(SwingConstants.CENTER);
        fullscreenLabel.setVerticalAlignment(SwingConstants.CENTER);

        fullscreenFrame.getContentPane().setLayout(new BorderLayout());
        fullscreenFrame.getContentPane().add(fullscreenLabel, BorderLayout.CENTER);
        fullscreenFrame.setBackground(Color.BLACK);

        fullscreenFrame.setVisible(true);
    }
    private void getUploadImage() {
        JFileChooser fileChooser = new JFileChooser();

        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            File copy = new File("images/staff/" + txtId.getText().trim() + ".png");
            try {
                Files.copy(selectedFile.toPath(), copy.toPath(), StandardCopyOption.REPLACE_EXISTING);
                urlImage = txtId.getText().trim() + ".png";
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        showImage(urlImage);
    }
    private void getDeleteImage() {
        if (Dialog.showComfirmDialogWithAutoClose("Bạn chắc muốn xóa ảnh này?")) {
            File file = new File("images/staff/" + urlImage);
            file.delete();

            urlImage = "";

            showImage("");
        }
    }
    private void getBtnUpdate() {
        if (txtId.getText().trim().isEmpty()) {
            Dialog.showMessageDialogWithAutoClose("Mã nhân viên còn trống.", 0);
            return;
        }
        if (txtName.getText().trim().isEmpty()) {
            Dialog.showMessageDialogWithAutoClose("Tên nhân viên còn trống.", 0);
            return;
        }
        Date birthDay = dcBirthday.getDate();
        Date workStart = dcDayStart.getDate();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (simpleDateFormat.format(birthDay).trim().isEmpty()) {
            Dialog.showMessageDialogWithAutoClose("Chưa chọn ngày sinh.", 0);
            return;
        }
        if (simpleDateFormat.format(workStart).trim().isEmpty()) {
            Dialog.showMessageDialogWithAutoClose("Chưa chọn ngày bắt đầu làm việc.", 0);
            return;
        }
        if (txtPhone.getText().trim().isEmpty()) {
            Dialog.showMessageDialogWithAutoClose("Chưa nhập số điện thoại", 0);
            return;
        }
        if (txtEmail.getText().trim().isEmpty()) {
            Dialog.showMessageDialogWithAutoClose("Chưa nhập sồ email", 0);
            return;
        }
        if (txtAddress.getText().trim().isEmpty()) {
            Dialog.showMessageDialogWithAutoClose("Chưa nhập địa chỉ", 0);
            return;
        }
        if (txtCountry.getText().trim().isEmpty()) {
            Dialog.showMessageDialogWithAutoClose("Chưa nhập quê quán", 0);
            return;
        }
        Staff s = new Staff().setId(txtId.getText().trim())
                .setEmail(txtEmail.getText().trim())
                .setName(txtName.getText().trim())
                .setDepartment(cbxDepartment.getSelectedItem().toString())
                .setSex(cbxSex.getSelectedItem().toString().equals("Nam") ? true : false)
                .setImage(urlImage)
                .setBirthday(simpleDateFormat.format(birthDay).trim())
                .setDateStart(simpleDateFormat.format(workStart).trim())
                .setAddress(txtAddress.getText().trim())
                .setCountry(txtCountry.getText().trim())
                .setPhone(txtPhone.getText().trim());

        String result = controller.updateStaff(s);
        if (result.equals("FALSE")) {
            Dialog.showMessageDialogWithAutoClose("Cập nhật thất bại.", 0);
        } else if (result.equals("UPDATE")) {
            Dialog.showMessageDialogWithAutoClose("Cập nhật thông tin thành công.", 1);
            loadDataToTable();
        } else if (result.equals("ADD")) {
            Dialog.showMessageDialogWithAutoClose("Thêm nhân viên mới thành công.", 1);
            loadDataToTable();
        }
    }
    private void getBtnDelete() {
        if (txtId.getText().trim().equals("")) {
            Dialog.showMessageDialogWithAutoClose("Mã nhân viên còn trống.", 0);
            return;
        }
        String id = txtId.getText().trim();
        String result = controller.deleteStaffById(id);
        if (result.equals("NOID")) {
            Dialog.showMessageDialogWithAutoClose("Không tồn tại mã nhân viên này", 0);
        } else if (result.equals("FALSE")) {
            Dialog.showMessageDialogWithAutoClose("Xóa không thành công", 0);
        } else if (result.equals("TRUE")) {
            Dialog.showMessageDialogWithAutoClose("Xóa thành công", 1);
            loadDataToTable();
        }
    }
}
