import modules.MyJLabel;
import modules.MyJButton;
import modules.MyJFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Sistema muito simples utilizado para marcar ponto
 * @author Matheus Ferreira
 */
public class MarcarPonto {
   static MyJFrame frame = new MyJFrame("Puncha'clock - by matheus.ferreirA", new Dimension(400, 300));
   static MyJLabel l1 = new MyJLabel("Data/Hora de entrada: ", new Dimension(300, 15));
   static MyJLabel l2 = new MyJLabel("Data/Hora de saída: ", new Dimension(300, 15));
   static MyJLabel l3 = new MyJLabel("", new Dimension(300, 15), new Font("Verdana", Font.PLAIN, 15), Color.WHITE, Color.BLACK);
   static JLabel[] myLabels = new JLabel[] {l1, l2, l3};

   static MyJButton b1 = new MyJButton("Marcar Início",null,"Clique para marcar seu ponto de inicio", "marcar-ponto:entrada", KeyEvent.VK_ENTER, l1, l3, true, null, null);
   static MyJButton b2 = new MyJButton("Marcar Saída", new Dimension(20, 10),"Clique para marcar sua hora de partida", "marcar-ponto:saida", KeyEvent.VK_ENTER, l2, l3, true, b1.getThreadWorker(), frame);
   //static MyJButton b3 = new MyJButton("Intervalo", new Dimension(20, 10),"Clique para marcar sua hora de partida", "marcar-ponto:saida", KeyEvent.VK_ENTER, l2, l3, false);
   //static MyResetJButton b3 = new MyResetJButton("Reset",null,"Clique para resetar", "marcar-ponto:reset", KeyEvent.VK_DELETE, null, new MyJButton[] {b1, b2}, new MeuJLabel[] {l3}, new MeuJLabel[] {l1, l2}, true, frame);

   public static final JButton[] myButtons = new JButton[] {b1, b2, /*b3*/};


   /**
    * Create and show the gui
    */
   private static void createAndShowGUI() {
      GridBagConstraints c = new GridBagConstraints();

      sendComponents(frame.getContentPane(), c, myButtons, myLabels);

      frame.open();
   }

   /**
    * Sends the components to the frame
    * @param buttons The buttons array
    * @param labels The labels array
    */
   private static void sendComponents(Container pane, GridBagConstraints c,  JButton[] buttons, JLabel[] labels) {
      pane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
      /*for (JLabel label : labels) {
         frame.add(label);
      }
      for (JButton button : buttons) {
         frame.add(button);
      }*/

      pane.setLayout(new GridBagLayout());

         //natural height, maximum width
         c.fill = GridBagConstraints.HORIZONTAL;
         c.gridx = 1;
         c.insets = new Insets(5,0,10,0);

      for (JLabel label : labels) {
         pane.add(label, c);
      }
      for (JButton button : buttons) {
         pane.add(button, c);
      }
   }

   /**
    * Opens the application with invokeLater
    * @param args The args
    */
   public static void main(String[] args) {
      javax.swing.SwingUtilities.invokeLater(MarcarPonto::createAndShowGUI);
   }
}