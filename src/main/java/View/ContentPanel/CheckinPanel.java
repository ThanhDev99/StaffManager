package View.ContentPanel;

import Controller.CheckInController;
import Model.ShareData;
import View.Help.RoundBorderLabel;
import View.MainFrame;
import View.MainPanel;
import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckinPanel extends JPanel {
    private BufferedWriter os;
    private Socket socketClient;
    private CheckInController controller;
    private MainPanel mainPanel;
    private GridBagConstraints constraints = new GridBagConstraints();
    private Font fontSystem = new Font("Cambria", Font.BOLD, 16);
    private RoundBorderLabel lblTime;
    private JButton btnCheckIn, btnCheckOut;
    private JLabel lblCheckIn, lblCheckOut, lblDate;
    public CheckinPanel(MainPanel mainPanel) {
        controller = new CheckInController();

        this.mainPanel = mainPanel;

        setBackground(new Color(126, 102, 215));

        setBorder(new MatteBorder(0, 2, 0, 0, Color.BLACK));

        setLayout(new GridBagLayout());

        setPreferredSize(new Dimension(1100, 700));

        createGUI();
    }
    private void createGUI() {
        lblTime = new RoundBorderLabel("", null);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(20, 0, 0, 0);
        add(lblTime, constraints);
        lblTime.setPreferredSize(new Dimension(200, 200));
        lblTime.setFont(new Font("Cambria", Font.BOLD, 30));
        lblTime.setForeground(new Color(0, 255, 20, 255));

        lblDate = new JLabel("");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(5, 0, 0, 0);
        add(lblDate, constraints);
        lblDate.setFont(new Font("Cambria", Font.BOLD, 20));
        lblDate.setForeground(new Color(0, 255, 20, 255));
        time();

        JPanel pnlCheckIn = new JPanel();
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(0, 0, 0, 0);
        add(pnlCheckIn, constraints);
        pnlCheckIn.setLayout(new GridBagLayout());
        pnlCheckIn.setPreferredSize(new Dimension(450, 400));
        pnlCheckIn.setBackground(new Color(126, 102, 215));

        lblCheckIn = new JLabel("Time check in");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 0, 50, 0);
        pnlCheckIn.add(lblCheckIn, constraints);
        lblCheckIn.setFont(fontSystem);

        btnCheckIn = new JButton("Check In");
        constraints.gridx = 0;
        constraints.gridy = 1;
        pnlCheckIn.add(btnCheckIn, constraints);
        btnCheckIn.setFont(fontSystem);
        btnCheckIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getBtnCheckIn();
            }
        });

        JPanel pnlCheckOut = new JPanel();
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.insets = new Insets(0, 0, 0, 0);
        add(pnlCheckOut, constraints);
        pnlCheckOut.setLayout(new GridBagLayout());
        pnlCheckOut.setPreferredSize(new Dimension(450, 400));
        pnlCheckOut.setBackground(new Color(126, 102, 215));

        lblCheckOut = new JLabel("Time check out");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 0, 50, 0);
        pnlCheckOut.add(lblCheckOut, constraints);
        lblCheckOut.setFont(fontSystem);

        btnCheckOut = new JButton("Check out");
        constraints.gridx = 0;
        constraints.gridy = 1;
        pnlCheckOut.add(btnCheckOut, constraints);
        btnCheckOut.setFont(fontSystem);
        btnCheckOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getBtnCheckOut();
            }
        });
    }
    private void time() {
        new Timer(1000, new ActionListener() {
            SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm:ss a");
            SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
            @Override
            public void actionPerformed(ActionEvent e) {
                lblTime.setText(formatTime.format(new Date()));
                lblDate.setText(formatDate.format(new Date()));
            }
        }).start();
    }
    private void getBtnCheckIn() {
        SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm:ss a");
        lblCheckIn.setText(formatTime.format(new Date()));

        mainPanel.lblTask.setEnabled(true);
        mainPanel.lblRequest.setEnabled(true);
        mainPanel.lblStaff.setEnabled(true);
        if (ShareData.account.getStaffManager()) {
            mainPanel.lblStaffManager.setEnabled(true);
        }
        
        if (ShareData.account.getStatistical()) {
            mainPanel.lblStatistical.setEnabled(true);
        }
        
        if (ShareData.account.getAccount()) {
            mainPanel.lblAccount.setEnabled(true);
        }
        
        if (ShareData.account.getAdmin()) {
            mainPanel.lblAdmin.setEnabled(true);
        }

        controller.setCheckIn(ShareData.account.getStaffId());

        try {
            socketClient = new Socket("localhost", 7777);

            os = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));

            write("checkin," + ShareData.account.getStaffId());
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void getBtnCheckOut() {
        SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm:ss a");
        lblCheckOut.setText(formatTime.format(new Date()));
    }
    private void write (String mes) throws IOException {
        os.write(mes);
        os.newLine();
        os.flush();
    }
}
