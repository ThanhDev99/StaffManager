package View.ContentPanel;

import Controller.RequestController;
import Model.Request;
import Model.ShareData;
import View.MainFrame;
import View.SecondFrame.AddRequestFrame;
import View.SecondFrame.DetailRequestFrame;
import View.SecondFrame.ListRequestPanel;

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

public class RequestPanel extends JPanel {
    private RequestController controller;
    private List<Request> list = new ArrayList<>();
    private MainFrame mainFrame;
    private GridBagConstraints constraints = new GridBagConstraints();
    private Font fontSystem = new Font("Cambria", Font.BOLD, 16);
    private JTable tblRequest;
    private DefaultTableModel tblModelRequest;
    public RequestPanel() {
        controller = new RequestController();

        setBackground(new Color(126, 102, 215));

        setBorder(new MatteBorder(0, 2, 0, 0, Color.BLACK));

        setLayout(new GridBagLayout());

        setPreferredSize(new Dimension(1100, 700));

        createGUI();

        loadDataToTable();
    }
    private void createGUI() {
        JLabel lblTitle = new JLabel("DANH SÁCH YÊU CẦU", JLabel.CENTER);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 0, 5, 0);
        add(lblTitle, constraints);
        lblTitle.setFont(new Font("Cambria", Font.BOLD, 30));
        lblTitle.setForeground(new Color(63, 0, 97, 255));
        lblTitle.setPreferredSize(new Dimension(1100, 50));

        tblModelRequest = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblModelRequest.setColumnIdentifiers(new String[]{"Mã yêu cầu", "Ngày gửi", "Phòng ban", "Nội dung", "Tình trạng"});

        tblRequest = new JTable(tblModelRequest);
        tblRequest.setFont(fontSystem);
        tblRequest.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblRequest.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DetailRequestFrame detailRequestFrame = new DetailRequestFrame(list.get(tblRequest.getSelectedRow()));
            }
        });

        JTableHeader header = tblRequest.getTableHeader();
        header.setFont(new Font("Cambria", Font.BOLD, 18));
        header.setForeground(new Color(63, 0, 97, 255));

        JScrollPane sclTbl = new JScrollPane(tblRequest);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(0, 0, 10, 0);
        add(sclTbl, constraints);
        sclTbl.setPreferredSize(new Dimension(1100, 450));

        JPanel pnlBtn = new JPanel();
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(15, 0, 5, 0);
        constraints.anchor = GridBagConstraints.LINE_END;
        pnlBtn.setPreferredSize(new Dimension(1100, 50));
        add(pnlBtn, constraints);

        JButton btnAddRequest = new JButton("Tạo yêu cầu");
        pnlBtn.add(btnAddRequest);
        pnlBtn.setBackground(this.getBackground());
        btnAddRequest.setFont(fontSystem);
        btnAddRequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddRequestFrame addRequestFrame = new AddRequestFrame();
            }
        });

        if (ShareData.account.getIsManager()) {
            JButton btnSeenRequest = new JButton("Yêu cầu chưa xem");
            pnlBtn.add(btnSeenRequest);
            btnSeenRequest.setFont(fontSystem);
            btnSeenRequest.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ListRequestPanel listRequestPanel = new ListRequestPanel();
                }
            });
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadDataToTable();
            }
        });
    }
    private void loadDataToTable() {
        list.clear();

        while (tblModelRequest.getRowCount() > 0) {
            tblModelRequest.removeRow(0);
        }

        list = controller.getRequestById();

        for (Request r : list) {
            tblModelRequest.addRow(new String[]{r.getId().toString(), r.getDateSend(), r.getDepartmentReceive(), r.getContent(), r.getStatus()});
        }
    }
}
