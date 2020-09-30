package modules;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MyJFrame extends JFrame {
    public MyJFrame(String title, Dimension dimensao) throws HeadlessException {
        super(title);
        super.setSize(dimensao);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setResizable(false);
        super.setLocationRelativeTo(null);
        try {
            super.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("src/resources/fundo.png")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Display the window.
        //super.pack();
    }

    public void open() {
        super.setVisible(true);
    }
}
