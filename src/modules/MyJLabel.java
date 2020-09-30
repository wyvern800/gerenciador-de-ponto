package modules;

import javax.swing.*;
import java.awt.*;

public class MyJLabel extends JLabel {
    public String getDefaultText() {
        return defaultText;
    }

    private String defaultText;

    public MyJLabel(String text, Dimension dimensao, Font font, Color foreGround, Color backGround) {
        super(text);
        super.setPreferredSize(dimensao);
        super.setFont(font);
        super.setForeground(foreGround);
        super.setBackground(backGround);
        this.defaultText = text;
    }

    public MyJLabel(String text, Dimension dimensao) {
        super(text);
        super.setPreferredSize(dimensao);
        this.defaultText = text;
    }

    public void setData(String data) {
        StringBuilder stb = new StringBuilder();
        stb.append(defaultText).append(data);
        super.setText(stb.toString());
    }

    public void getData(String data) {
        StringBuilder stb = new StringBuilder();
        stb.append(defaultText).append(data);
        super.setText(stb.toString());
    }
}
