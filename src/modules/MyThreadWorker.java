package modules;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicBoolean;

public class MyThreadWorker implements Runnable {
    private Thread worker;
    private int interval;
    private AtomicBoolean running = new AtomicBoolean(false);
    private AtomicBoolean stopped = new AtomicBoolean(true);
    private MyThreadWorker threadToBeClosed;
    private LocalDateTime clickTimeStart;
    private LocalDateTime clickTimeEnd;
    private MyJLabel label;
    private MyThreadWorker oldThreadToBeClosed;
    private Thread oldWorker;
    private MyJFrame frame;

    public MyThreadWorker getOldThreadToBeClosed() {
        return oldThreadToBeClosed;
    }

    public Thread getOldWorker() {
        return oldWorker;
    }

    public MyThreadWorker(MyThreadWorker threadToBeClosed, MyJLabel mensagemDeFim, int interval, MyJFrame frame) {
        this.label = mensagemDeFim;
        this.interval = interval;
        this.worker = new Thread(this);
        this.frame = frame;
        if (threadToBeClosed != null) {
            this.threadToBeClosed = threadToBeClosed;
            this.oldThreadToBeClosed = threadToBeClosed;
            System.out.println("ThreadWorker("+threadToBeClosed.toString()+")");
        }
    }

    public MyThreadWorker(MyJLabel label, int interval) {
        this.worker = new Thread(this);
        System.out.println("ThreadWorker("+worker.toString()+")");
        this.label = label;
        this.interval = interval;
        this.oldWorker = worker;
    }

    public void run() {
        running.set(true);
        stopped.set(false);
        clickTimeStart = LocalDateTime.now();
        while (running.get()) {
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
                System.out.println(
                        "Thread was interrupted, Failed to complete operation");
            }
            Duration between = Duration.between(clickTimeStart, LocalDateTime.now());
            String time = between.toString().replace("PT", "").replace("H", "h ").replace("M", "m ").replace("S", "s ");
            String s = time.split("\\.")[0].concat("s");
            label.setText("Duração da jornada de trabalho: " + s+ "!");
            label.setVisible(true);
            System.out.println("Running thread(" + running.get() + ");");
        }
        Duration between = Duration.between(clickTimeStart, LocalDateTime.now());
        String time = between.toString().replace("PT", "").replace("H", "h ").replace("M", "m ").replace("S", "s ");
        String s = time.split("\\.")[0].concat("s");

        StringBuilder stb = new StringBuilder();
        stb.append("Você trabalhou por: ").append(s).append(", ").append("parabéns&");
        String replace = stb.toString().replace("!", "").replace("&", "!");
        label.setText(replace);
        MyJOptionPane.showMessageDialog(frame, replace);
        stopped.set(true);
    }

    public void start() {
        worker.start();
    }

    public void restart() {
        worker.start();
    }

    public void stop() {
        running.set(false);
    }

    public void interrupt() {
        running.set(false);
        worker.interrupt();
    }

    boolean isRunning() {
        return running.get();
    }

    boolean isStopped() {
        return stopped.get();
    }

    public MyThreadWorker getThreadToBeClosed() {
        return threadToBeClosed;
    }

    private void doWork(LocalDateTime clickTimeStart, MyJLabel msg) {
                Duration between = Duration.between(clickTimeStart, LocalDateTime.now());
                String time = between.toString().replace("PT", "").replace("H", "h ").replace("M", "m ").replace("S", "s ");
                String s = time.split("\\.")[0].concat("s");
                msg.setText("Você trabalhou por " + s);
                msg.setVisible(true);
                System.out.println("doWork(" + running.get() + ");");
    }
/*
    private MeuJLabel to;
    private LocalDateTime to2;

    public Worker(MeuJLabel to, LocalDateTime clickTime) {
        this.to = to;
        this.to2 = clickTime;
    }

    @Override
    public void run() {
        Duration between = Duration.between(to2, LocalDateTime.now());
        String time = between.toString().replace("PT", "").replace("H", "h ").replace("M", "m ").replace("S", "s ");
        String s = time.split("\\.")[0].concat("s");
        to.setText("Você trabalhou por "+s);
        to.setVisible(true);
    }*/
}
