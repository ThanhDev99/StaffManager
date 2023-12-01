package View.ContentPanel;

import Controller.ChatController;
import Model.ShareData;
import View.MainFrame;

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
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StaffPanel extends JPanel {
    private Thread thread;
    private BufferedReader is;
    private BufferedWriter os;
    private Socket socketClient;
    private ChatController controller;
    private String id;
    private List<String> onlineList;
    private MainFrame mainFrame;
    private GridBagConstraints constraints = new GridBagConstraints();
    private Font fontSystem = new Font("Cambria", Font.BOLD, 16);
    private JLabel lblStaffId, lblReceiver;
    private JTextArea txtHistory, txtMessage;
    private DefaultTableModel tblModelOnline;
    private JTable tblOnline;
    public StaffPanel() {
        onlineList = new LinkedList<>();

        onlineList.add("Tất cả");

        this.id = ShareData.account.getStaffId();

        controller = new ChatController();

        setBackground(new Color(126, 102, 215));

        setBorder(new MatteBorder(0, 2, 0, 0, Color.BLACK));

        setLayout(new GridBagLayout());

        setPreferredSize(new Dimension(1100, 700));

        createGUI();

        setUpSocket();

        updateTable();
    }
    private void createGUI() {
        createListPanel();

        createChatPanel();
    }
    private void createListPanel() {
        JLabel lblTitle = new JLabel("DANH SÁCH NHÂN VIÊN ONLINE");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 0, 10, 0);
        add(lblTitle, constraints);
        lblTitle.setFont(new Font("Cambria", Font.BOLD, 20));
        lblTitle.setForeground(new Color(63, 0, 97, 255));
        lblTitle.setPreferredSize(new Dimension(300, 50));

        tblModelOnline = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblModelOnline.setColumnIdentifiers(new String[]{"Mã nhân viên"});

        tblOnline = new JTable(tblModelOnline);
        tblOnline.setFont(fontSystem);
        tblOnline.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblOnline.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lblReceiver.setText("Người nhận: " + tblModelOnline.getValueAt(tblOnline.getSelectedRow(), 0));
            }
        });

        JTableHeader header = tblOnline.getTableHeader();
        header.setFont(new Font("Cambria", Font.BOLD, 18));
        header.setForeground(new Color(63, 0, 97, 255));

        JScrollPane sclStaffOnline = new JScrollPane(tblOnline);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(0, 0, 10, 0);
        add(sclStaffOnline, constraints);
        sclStaffOnline.setPreferredSize(new Dimension(300, 550));
        sclStaffOnline.setBorder(new CompoundBorder(new BevelBorder(1), new BevelBorder(0)));
    }
    private void createChatPanel() {
        JPanel pnlChat = new JPanel();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridheight = 2;
        constraints.insets = new Insets(5, 40, 10, 0);
        add(pnlChat, constraints);
        pnlChat.setLayout(new GridBagLayout());
        pnlChat.setPreferredSize(new Dimension(500, 600));
        pnlChat.setBackground(new Color(126, 102, 215));

        lblStaffId = new JLabel(ShareData.account.getStaffId(), JLabel.CENTER);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.insets = new Insets(2, 40, 30, 0);
        pnlChat.add(lblStaffId, constraints);
        lblStaffId.setFont(new Font("Cambria", Font.BOLD, 25));
        lblStaffId.setForeground(new Color(63, 0, 97, 255));

        txtHistory = new JTextArea();
        JScrollPane sclHistory = new JScrollPane(txtHistory);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(2, 0, 0, 0);
        pnlChat.add(sclHistory, constraints);
        sclHistory.setPreferredSize(new Dimension(450, 300));
        txtHistory.setFont(fontSystem);
        txtHistory.setEditable(false);

        lblReceiver = new JLabel("Người nhận: Tất cả");
        constraints.gridx = 0;
        constraints.gridy = 2;
        pnlChat.add(lblReceiver, constraints);
        lblReceiver.setPreferredSize(new Dimension(450, 30));
        lblReceiver.setFont(fontSystem);

        txtMessage = new JTextArea();
        JScrollPane sclMessage = new JScrollPane(txtMessage);
        constraints.gridx = 0;
        constraints.gridy = 3;
        pnlChat.add(sclMessage, constraints);
        sclMessage.setPreferredSize(new Dimension(450, 50));
        txtMessage.setFont(fontSystem);

        JButton btnSend = new JButton("Gửi");
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.insets = new Insets(15, 0, 0, 0);
        pnlChat.add(btnSend, constraints);
        btnSend.setFont(fontSystem);
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setBtnSend();
            }
        });
    }
    private void setBtnSend() {
        String mes = txtMessage.getText().trim();
        if (mes.isEmpty()) {
            return;
        }
        String receiver = lblReceiver.getText().substring(12);
        System.out.println(receiver);
        if (receiver.equals(this.id)) {
            receiver = "Tất cả";
        }
        sendMessage(this.id, receiver, mes);
    }
    private void sendMessage(String sender, String receiver, String mes) {
        if (receiver.equals("Tất cả")) {
            try {
                write("send-to-global" + "," + mes + "," + sender);
                txtHistory.setText(txtHistory.getText() + "Bạn: " + mes + "\n");
                txtHistory.setCaretPosition(txtHistory.getDocument().getLength());

            } catch (IOException e) {
                System.out.println("Có lỗi.");
            }
        } else {
            try {
                write("send-to-person" + "," + mes + "," + sender + "," + receiver);
                txtHistory.setText(txtHistory.getText() + "Bạn - " + receiver + ": " + mes + "\n");
                txtHistory.setCaretPosition(txtHistory.getDocument().getLength());

            } catch (IOException ex) {
                System.out.println("Có lỗi.");
            }
        }
        txtMessage.setText("");

    }
    private void write (String mes) throws IOException {
        os.write(mes);
        os.newLine();
        os.flush();
    }
    private void setUpSocket() {
        try {
            thread = new Thread() {
                @Override
                public void run() {
                    try {
                        socketClient = new Socket("localhost", 7777);
                        System.out.println("Connected");

                        os = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));

                        is = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));

                        String mes;

                        write("send-id," + id);

                        while (true) {
                            mes = is.readLine();

                            if (mes == null) {
                                break;
                            }

                            String[] messageSplit = mes.split(",");

                            if (messageSplit[0].equals("update-online-list")) {
                                onlineList.clear();
                                String[] online = messageSplit[1].split("-");
                                for (int i = 0; i < online.length; i++) {
                                    onlineList.add(online[i]);
                                }
                                updateTable();
                            }
                            if (messageSplit[0].equals("global-message")) {
                                txtHistory.setText(txtHistory.getText() + messageSplit[1] + "\n");
                                txtHistory.setCaretPosition(txtHistory.getDocument().getLength());
                            }
                        }
                    } catch (UnknownHostException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            };
            thread.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void updateTable() {
        while (tblModelOnline.getRowCount() > 0) {
            tblModelOnline.removeRow(0);
        }
        for (String s : onlineList) {
            tblModelOnline.addRow(new String[]{s});
        }
    }
}

