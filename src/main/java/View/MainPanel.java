package View;

import Controller.AccountController;
import Model.Account;
import Model.ShareData;
import View.ContentPanel.*;
import View.Help.Dialog;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class MainPanel extends JPanel {
    private AccountController controller;
    private MainFrame mainFrame;
    private GridBagConstraints constraints = new GridBagConstraints();
    private Font fontSystem = new Font("Cambria", Font.BOLD, 16);
    private CardLayout cardLayout = new CardLayout();
    private JLabel lblName, lblCheckin, lblTask, lblRequest, lblStaff, lblStaffManager
            , lblStatistical, lblAccount, lblAdmin, lblChat;
    private JPanel contentPanel;
    private String account;
    private int number = 0;
    private JLabel roundBorderLabel;
    public MainPanel(MainFrame mainFrame, String account) {
        this.mainFrame = mainFrame;
        this.account = account;

        controller = new AccountController();

        loadDataAccount();

        createPanel();
    }
    private void createPanel() {
        setPreferredSize(new Dimension(1400, 700));

        setLayout(new GridBagLayout());

        setBackground(mainFrame.getBackground());

        createGUI();
    }
    private void createGUI() {
        createContentPanel();

        createServicePanel();

        showImage(ShareData.account.getStaffId());
    }
    private void createServicePanel() {
        JPanel servicePanel = new JPanel();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 0, 0, 0);
        add(servicePanel, constraints);
        servicePanel.setLayout(new GridBagLayout());
        servicePanel.setPreferredSize(new Dimension(150, 670));
        servicePanel.setBackground(mainFrame.getBackground());

        ImageIcon peopleIcon = new ImageIcon("icons/User.png");
        ImageIcon peopleIconResize = new ImageIcon(peopleIcon.getImage().getScaledInstance(70, 70, 0));
        roundBorderLabel = new JLabel(peopleIconResize);
        constraints.gridx = 0;
        constraints.gridy = 0;
        servicePanel.add(roundBorderLabel, constraints);
        roundBorderLabel.setPreferredSize(new Dimension(90, 90));

        lblName = new JLabel(ShareData.account.getStaffId(), JLabel.CENTER);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(0, 0, 20, 0);
        servicePanel.add(lblName, constraints);
        lblName.setFont(fontSystem);

        if (ShareData.account.getCheckIn()) {
            CheckinPanel checkIn = new CheckinPanel();
            contentPanel.add(checkIn, "CheckIn");

            lblCheckin = new JLabel("Điểm danh", JLabel.CENTER);
            constraints.gridx = 0;
            constraints.gridy = 2;
            constraints.insets = new Insets(0, 0, 2, 0);
            servicePanel.add(lblCheckin, constraints);
            lblCheckin.setFont(fontSystem);
            lblCheckin.setPreferredSize(new Dimension(150, 40));
            lblCheckin.setBorder(new CompoundBorder(new BevelBorder(1), new BevelBorder(0)));
            lblCheckin.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    lblCheckin.setForeground(new Color(0, 16, 255));
                    lblCheckin.setBackground(new Color(0,0,0));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    lblCheckin.setForeground(new Color(0,0,0));
                    lblCheckin.setBackground(new Color(255, 255, 255));
                }
                @Override
                public void mouseClicked(MouseEvent e) {
                    cardLayout.show(contentPanel, "CheckIn");
                }
            });
        } else {
            number += 1;
        }

        if (ShareData.account.getTask()) {
            TaskPanel taskPanel = new TaskPanel();
            contentPanel.add(taskPanel, "Task");

            lblTask = new JLabel("Công việc", JLabel.CENTER);
            constraints.gridx = 0;
            constraints.gridy = 3;
            servicePanel.add(lblTask, constraints);
            lblTask.setFont(fontSystem);
            lblTask.setPreferredSize(new Dimension(150, 40));
            lblTask.setBorder(new CompoundBorder(new BevelBorder(1), new BevelBorder(0)));
            lblTask.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    cardLayout.show(contentPanel, "Task");
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    lblTask.setForeground(new Color(0, 16, 255));
                    lblTask.setBackground(new Color(0,0,0));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    lblTask.setForeground(new Color(0,0,0));
                    lblTask.setBackground(new Color(255, 255, 255));
                }
            });
        } else {
            number += 1;
        }

        if (ShareData.account.getRequest()) {
            RequestPanel requestPanel = new RequestPanel();
            contentPanel.add(requestPanel, "Request");

            lblRequest = new JLabel("Yêu cầu", JLabel.CENTER);
            constraints.gridx = 0;
            constraints.gridy = 4;
            servicePanel.add(lblRequest, constraints);
            lblRequest.setFont(fontSystem);
            lblRequest.setPreferredSize(new Dimension(150, 40));
            lblRequest.setBorder(new CompoundBorder(new BevelBorder(1), new BevelBorder(0)));
            lblRequest.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    cardLayout.show(contentPanel, "Request");
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    lblRequest.setForeground(new Color(0, 16, 255));
                    lblRequest.setBackground(new Color(0,0,0));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    lblRequest.setForeground(new Color(0,0,0));
                    lblRequest.setBackground(new Color(255, 255, 255));
                }
            });
        } else {
            number += 1;
        }

        if (ShareData.account.getStaff()) {
            StaffPanel staffPanel = new StaffPanel();
            contentPanel.add(staffPanel, "Staff");

            lblStaff = new JLabel("Nhân viên", JLabel.CENTER);
            constraints.gridx = 0;
            constraints.gridy = 5;
            servicePanel.add(lblStaff, constraints);
            lblStaff.setFont(fontSystem);
            lblStaff.setPreferredSize(new Dimension(150, 40));
            lblStaff.setBorder(new CompoundBorder(new BevelBorder(1), new BevelBorder(0)));
            lblStaff.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    cardLayout.show(contentPanel, "Staff");
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    lblStaff.setForeground(new Color(0, 16, 255));
                    lblStaff.setBackground(new Color(0,0,0));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    lblStaff.setForeground(new Color(0,0,0));
                    lblStaff.setBackground(new Color(255, 255, 255));
                }
            });
        } else {
            number += 1;
        }

        if (ShareData.account.getStaffManager()) {
            StaffManagerPanel staffManagerPanel = new StaffManagerPanel();
            contentPanel.add(staffManagerPanel, "StaffManager");

            lblStaffManager = new JLabel("Quản lý NV", JLabel.CENTER);
            constraints.gridx = 0;
            constraints.gridy = 6;
            servicePanel.add(lblStaffManager, constraints);
            lblStaffManager.setFont(fontSystem);
            lblStaffManager.setPreferredSize(new Dimension(150, 40));
            lblStaffManager.setBorder(new CompoundBorder(new BevelBorder(1), new BevelBorder(0)));
            lblStaffManager.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    cardLayout.show(contentPanel, "StaffManager");
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    lblStaffManager.setForeground(new Color(0, 16, 255));
                    lblStaffManager.setBackground(new Color(0,0,0));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    lblStaffManager.setForeground(new Color(0,0,0));
                    lblStaffManager.setBackground(new Color(255, 255, 255));
                }
            });
        } else {
            number += 1;
        }

        if (ShareData.account.getStatistical()) {
            StatisticalPanel statisticalPanel = new StatisticalPanel();
            contentPanel.add(statisticalPanel, "Statistical");

            lblStatistical = new JLabel("Thống kê", JLabel.CENTER);
            constraints.gridx = 0;
            constraints.gridy = 7;
            servicePanel.add(lblStatistical, constraints);
            lblStatistical.setFont(fontSystem);
            lblStatistical.setPreferredSize(new Dimension(150, 40));
            lblStatistical.setBorder(new CompoundBorder(new BevelBorder(1), new BevelBorder(0)));
            lblStatistical.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    cardLayout.show(contentPanel, "Statistical");
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    lblStatistical.setForeground(new Color(0, 16, 255));
                    lblStatistical.setBackground(new Color(0,0,0));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    lblStatistical.setForeground(new Color(0,0,0));
                    lblStatistical.setBackground(new Color(255, 255, 255));
                }
            });
        } else {
            number += 1;
        }

        if (ShareData.account.getAccount()) {
            AccountPanel accountPanel = new AccountPanel();
            contentPanel.add(accountPanel, "Account");

            lblAccount = new JLabel("Tài khoản", JLabel.CENTER);
            constraints.gridx = 0;
            constraints.gridy = 8;
            servicePanel.add(lblAccount, constraints);
            lblAccount.setFont(fontSystem);
            lblAccount.setPreferredSize(new Dimension(150, 40));
            lblAccount.setBorder(new CompoundBorder(new BevelBorder(1), new BevelBorder(0)));
            lblAccount.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    cardLayout.show(contentPanel, "Account");
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    lblAccount.setForeground(new Color(0, 16, 255));
                    lblAccount.setBackground(new Color(0,0,0));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    lblAccount.setForeground(new Color(0,0,0));
                    lblAccount.setBackground(new Color(255, 255, 255));
                }
            });
        } else {
            number += 1;
        }

        if (ShareData.account.getAdmin()) {
            AdminPanel adminPanel = new AdminPanel();
            contentPanel.add(adminPanel, "Admin");

            lblAdmin = new JLabel("Admin", JLabel.CENTER);
            constraints.gridx = 0;
            constraints.gridy = 9;
            servicePanel.add(lblAdmin, constraints);
            lblAdmin.setFont(fontSystem);
            lblAdmin.setPreferredSize(new Dimension(150, 40));
            lblAdmin.setBorder(new CompoundBorder(new BevelBorder(1), new BevelBorder(0)));
            lblAdmin.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    cardLayout.show(contentPanel, "Admin");
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    lblAdmin.setForeground(new Color(0, 16, 255));
                    lblAdmin.setBackground(new Color(0,0,0));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    lblAdmin.setForeground(new Color(0,0,0));
                    lblAdmin.setBackground(new Color(255, 255, 255));
                }
            });
        } else {
            number += 1;
        }

        if (ShareData.account.getChat()) {
//            ChatPanel chatPanel = new ChatPanel(null, null, null);
//            contentPanel.add(chatPanel, "Chat");

            lblChat = new JLabel("Tin nhắn", JLabel.CENTER);
            constraints.gridx = 0;
            constraints.gridy = 10;
            servicePanel.add(lblChat, constraints);
            lblChat.setFont(fontSystem);
            lblChat.setPreferredSize(new Dimension(150, 40));
            lblChat.setBorder(new CompoundBorder(new BevelBorder(1), new BevelBorder(0)));
            lblChat.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    cardLayout.show(contentPanel, "Chat");
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    lblChat.setForeground(new Color(0, 16, 255));
                    lblChat.setBackground(new Color(0,0,0));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    lblChat.setForeground(new Color(0,0,0));
                    lblChat.setBackground(new Color(255, 255, 255));
                }
            });
        } else {
            number += 1;
        }

        JLabel lblLogout = new JLabel("Đăng xuất", JLabel.CENTER);
        constraints.gridx = 0;
        constraints.gridy = 11;
        constraints.insets = new Insets(120 + number * 40, 0, 0, 0);
        servicePanel.add(lblLogout, constraints);
        lblLogout.setFont(fontSystem);
        lblLogout.setPreferredSize(new Dimension(150, 40));
        lblLogout.setBorder(new CompoundBorder(new BevelBorder(1), new BevelBorder(0)));
        lblLogout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getLblLogout();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                lblLogout.setForeground(new Color(0, 16, 255));
                lblLogout.setBackground(new Color(0,0,0));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                lblLogout.setForeground(new Color(0,0,0));
                lblLogout.setBackground(new Color(255, 255, 255));
            }
        });

    }
    private void createContentPanel() {
        contentPanel = new JPanel();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 0, 0, 0);
        add(contentPanel, constraints);
        contentPanel.setPreferredSize(new Dimension(1230, 670));
        contentPanel.setBackground(new Color(126, 102, 215));
        contentPanel.setLayout(cardLayout);
    }
    private void loadDataAccount() {
        Account acc = controller.loadDataAccount(account);

        ShareData.account.setStaffId(acc.getStaffId());
        ShareData.account.setPassword(acc.getPassword());
        ShareData.account.setDepartment(acc.getDepartment());
        ShareData.account.setCheckIn(acc.getCheckIn());
        ShareData.account.setTask(acc.getTask());
        ShareData.account.setRequest(acc.getRequest());
        ShareData.account.setStaff(acc.getStaff());
        ShareData.account.setStaffManager(acc.getStaffManager());
        ShareData.account.setStatistical(acc.getStatistical());
        ShareData.account.setAccount(acc.getAccount());
        ShareData.account.setAdmin(acc.getAdmin());
        ShareData.account.setIsManager(acc.getIsManager());
        ShareData.account.setChat((acc.getChat()));
    }
    private void getLblLogout() {
        if (Dialog.showComfirmDialogWithAutoClose("Bạn có chắc muốn đăng xuất?")) {
            try {
                FileOutputStream fos = new FileOutputStream("data/Account.dat");
                ObjectOutputStream oos = new ObjectOutputStream(fos);

                Account acc = new Account();
                acc.setStaffId("");
                acc.setPassword("");

                oos.writeObject(acc);

                System.out.println("Save done.");
                oos.close();
                fos.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            mainFrame.remove(this);
            LoginPanel loginPanel = new LoginPanel(mainFrame);
            mainFrame.add(loginPanel);
            mainFrame.revalidate();
            mainFrame.repaint();
        }
    }
    private void showImage(String url) {
        ImageIcon imageIcon = new ImageIcon("images/staff/" + url +".png");
        ImageIcon imageIconResize = new ImageIcon(imageIcon.getImage().getScaledInstance(70, 70, 0));
        roundBorderLabel.setIcon(imageIconResize);
    }
}
