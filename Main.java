import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main{
  public static void main(String[] args){

    JFrame window = new JFrame();
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setResizable(false);
    window.setTitle("game");
    gamepanel game = new gamepanel();
    window.add(game);
    window.pack();
    window.setLocationRelativeTo(null);
    window.setVisible(true);
    game.startGameThread();
  }
}
