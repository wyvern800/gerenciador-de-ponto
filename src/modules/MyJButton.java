package modules;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyJButton extends JButton implements ActionListener {
    boolean started;

    private JFrame frame;

    public MyThreadWorker getThreadWorker() {
        return threadWorker;
    }

    private LocalDateTime clickTimeStart;

    private MyJButton botaoPraAtivar;

    public void setThreadWorker(MyThreadWorker threadWorker) {
        this.threadWorker = threadWorker;
    }

    private MyThreadWorker threadWorker;

    public LocalDateTime getClickTimeStart() {
        return clickTimeStart;
    }

    public LocalDateTime getClickTimeEnd() {
        return clickTimeEnd;
    }

    public void setClickTimeStart(LocalDateTime clickTimeStart) {
        this.clickTimeStart = clickTimeStart;
    }

    public void setClickTimeEnd(LocalDateTime clickTimeEnd) {
        this.clickTimeEnd = clickTimeEnd;
    }

    private LocalDateTime clickTimeEnd;

    public MyJLabel getAttributedLabel() {
        return attributedLabel;
    }

    private MyJLabel attributedLabel;

    public MyJLabel getMensagemDeFim() {
        return mensagemDeFim;
    }

    private MyJLabel mensagemDeFim;

    public String getOldTitle() {
        return oldTitle;
    }

    private String oldTitle;


    public MyJButton(String title, Dimension size, String tooltip, String actionKey, int key, MyJLabel attributedLabel, MyJLabel mensagemDeFim, boolean enabled, MyThreadWorker workerToBeClosed, MyJFrame frame) throws HeadlessException {
        super(title);
        super.setVerticalTextPosition(AbstractButton.BOTTOM);
        super.setHorizontalAlignment(AbstractButton.CENTER);
        super.setMnemonic(key);
        super.setVisible(true);
        super.setActionCommand(actionKey);
        super.setEnabled(enabled);
        super.setBackground(Color.lightGray);
        super.setFocusPainted(false);
        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        super.setBorder(new CompoundBorder(line, margin));
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
        this.oldTitle = title;
        if (mensagemDeFim != null) {
            this.mensagemDeFim = mensagemDeFim;
        }

        if (actionKey.equals("marcar-ponto:saida")) {
            clickTimeEnd = LocalDateTime.now();
        }

        if (frame != null) {
            this.frame = frame;
        }

        if (workerToBeClosed != null) {
            this.threadWorker =  new MyThreadWorker(workerToBeClosed, getMensagemDeFim(), 1000, frame);
        } else {
            this.threadWorker = new MyThreadWorker(getMensagemDeFim(), 1000);
        }
        //setWorker(new Worker(LocalDateTime.now(), getMensagemDeFim()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "marcar-ponto:entrada":
                System.out.println("marcar-ponto:entrada");
                super.setEnabled(false);
                this.clickTimeStart = LocalDateTime.now();
                this.clickTimeEnd = LocalDateTime.now();
                getAttributedLabel().setData(clickTimeStart.format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss")));

                if (threadWorker != null) {
                    threadWorker.start();
                }
                break;
            case "marcar-ponto:saida":
                System.out.println("marcar-ponto:saida");
                super.setEnabled(false);
                getAttributedLabel().setData(clickTimeEnd.format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss")));

                //Duration between = Duration.between(clickTimeStart, LocalDateTime.now());
               // String time = between.toString().replace("PT", "").replace("H", "h ").replace("M", "m ").replace("S", "s ");
                //String s = time.split("\\.")[0].concat("s");

                if (threadWorker != null) {
                    threadWorker.stop();
                }
                if (threadWorker.getThreadToBeClosed() != null) {
                    threadWorker.getThreadToBeClosed().stop();
                }

                //getMensagemDeFim().setText("Você trabalhou por "+s);
                break;
        }
    }
}
