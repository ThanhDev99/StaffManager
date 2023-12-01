package View.Help;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dialog {
    private static Font fontSystem = new Font("Cambria", Font.BOLD, 16);
    public static void showMessageDialogWithAutoClose(String mes, int type) {
        final JOptionPane optionPane = new JOptionPane(mes, JOptionPane.INFORMATION_MESSAGE);
        optionPane.setPreferredSize(new Dimension(300, 100));

        optionPane.setFont(fontSystem);

        final JDialog dialog = optionPane.createDialog("Thông báo");

        if (type == 1) {
            ImageIcon imageIcon = new ImageIcon("icons/Done.png");
            optionPane.setIcon(imageIcon);
        } else if (type == 0) {
            ImageIcon imageIcon = new ImageIcon("icons/Error.png");
            optionPane.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(50, 50, 0)));
        }
        else if (type == 2) {
            ImageIcon imageIcon = new ImageIcon("icons/High Priority.png");
            optionPane.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(50, 50, 0)));
        }
        // Sử dụng Timer để đóng dialog sau một khoảng thời gian
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose(); // Đóng dialog sau 3 giây
            }
        });
        timer.setRepeats(false); // Chỉ thực hiện một lần

        // Bắt đầu đếm thời gian và hiển thị dialog
        timer.start();
        dialog.setVisible(true);
    }

    public static Boolean showComfirmDialogWithAutoClose(String mes) {
        ImageIcon imageIcon = new ImageIcon("icons/High Priority.png");
        ImageIcon imageIcon1 = new ImageIcon(imageIcon.getImage().getScaledInstance(50, 50 ,0));
        int choice = JOptionPane.showConfirmDialog(null, mes, "Thông báo"
                , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, imageIcon1);

        if (choice == JOptionPane.YES_OPTION) {
            return true;
        }
        else {
            return false;
        }
    }
}

