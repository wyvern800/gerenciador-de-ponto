package modules;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class MyResetJButton extends JButton implements ActionListener {
    public MyJLabel getAttributedLabel() {
        return attributedLabel;
    }

    private MyJLabel attributedLabel;
    private MyJButton[] otherButtons;
    private MyJLabel[] otherLabels;
    private MyJLabel[] resetLabels;
    private JFrame frame;
    private JFrame oldFrame;

    public MyResetJButton(String title, Dimension size, String tooltip, String actionKey, int key, MyJLabel attributedLabel, MyJButton[] otherButtons, MyJLabel[] otherLabels, MyJLabel[] resetLabels, boolean enabled, JFrame frame) throws HeadlessException {
        super(title);
        super.setVerticalTextPosition(AbstractButton.BOTTOM);
        super.setHorizontalAlignment(AbstractButton.CENTER);
        super.setMnemonic(key);
        super.setVisible(true);
        super.setActionCommand(actionKey);
        super.setEnabled(enabled);
        if (size != null) {
            super.setSize(size);
        } else {
            super.setSize(new Dimension(30, 20));
        }
        super.addActionListener(this);
        if (tooltip != null) {
            super.setToolTipText(tooltip);
        }
        if (attributedLabel != null) {
            this.attributedLabel = attributedLabel;
        }
        this.otherButtons = otherButtons;
        this.otherLabels = otherLabels;

        if (resetLabels != null) {
            this.resetLabels = resetLabels;
        }

        this.frame = frame;
        this.oldFrame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "marcar-ponto:reset":
                for (MyJButton button: otherButtons) {
                    button.setText(button.getOldTitle());
                    button.setEnabled(true);
                    button.setClickTimeEnd(LocalDateTime.now());
                    button.setClickTimeStart(LocalDateTime.now());
                }
                for (MyJLabel label: otherLabels) {
                    label.setText("");
                }
                for (MyJLabel labels: resetLabels) {
                    labels.setText(labels.getDefaultText());
                }

                frame = oldFrame;
                System.out.println("Sistema resetado");
                break;
        }
    }
}
