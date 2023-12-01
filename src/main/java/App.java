import View.MainFrame;
import View.SecondFrame.AddTaskFrame;
import View.SecondFrame.DetailTaskFrame;

import javax.swing.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame mainFrame = new MainFrame();
//                AddTaskFrame addTaskFrame = new AddTaskFrame();
//                DetailTaskFrame detailTaskFrame = new DetailTaskFrame(null);
            }
        });
    }
}