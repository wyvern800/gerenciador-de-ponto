package modules;

import javax.swing.*;

public class MyJOptionPane extends JOptionPane {
    public MyJOptionPane(Object message, JFrame frame) {
        super(message);
        showMessageDialog(frame, message);
    }
}
