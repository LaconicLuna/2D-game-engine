import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.*;
import java.util.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
public class gamepanel extends JPanel implements Runnable{
  //game loop set up variables
  long lastFpsTime = System.currentTimeMillis();
  int fps = 0;
  int frames = 0;
  
  
  //variables
  Player player = new Player(0.0,0.0,10.0);
  Image playerfront;
  final int screenwidth = 800;
  final int screenheight = 600;

  int mouseX;
  int mouseY;

  KeyHandler keyH = new KeyHandler();
  MouseHandler mouseH = new MouseHandler();
  Thread gameThread;
  void loadimg(){
    try{
    playerfront = ImageIO.read(new File("sprites/test.png"));
    } catch(Exception e){
      System.out.println(e);
    };
  }
  
  //game panel set up
  public gamepanel(){
    this.setPreferredSize(new Dimension(screenwidth, screenheight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyH);
    this.addMouseListener(mouseH);
    this.setFocusable(true);
  }

  
  //gameloop stuff
  public void startGameThread(){
    gameThread = new Thread(this);
    gameThread.start();
    loadimg();
  }
  @Override
  public void run() {
      final double TARGET_FPS = 60.0;
      final double OPTIMAL_TIME = 1_000_000_000 / TARGET_FPS; // nanoseconds per frame

      long previousTime = System.nanoTime();

      long lastFpsCheck = System.currentTimeMillis();
      int frames = 0;

      while (gameThread != null) {
          long currentTime = System.nanoTime();
          double deltaTime = (currentTime - previousTime) / 1_000_000_000.0;
          previousTime = currentTime;

          update(deltaTime);
          repaint();

          frames++;
          if (System.currentTimeMillis() - lastFpsCheck >= 1000) {
              fps = frames;
              frames = 0;
              lastFpsCheck = System.currentTimeMillis();
              
          }

          // Sleep until next frame
          long frameTime = System.nanoTime() - currentTime;
          long sleepTime = (long)(OPTIMAL_TIME - frameTime) / 1_000_000; // convert to ms

          if (sleepTime > 0) {
              try {
                  Thread.sleep(sleepTime);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
      }
  }

  
  //update stuff
  public void update(double deltaTime) {

    //MOUSE POSITION
    try {
        Point mousePos = this.getMousePosition();
        if (mousePos != null) {
            mouseX = mousePos.x;
            mouseY = mousePos.y;
            // Use mouseX and mouseY as needed
        } else {
            
            // Optional: handle out-of-bounds state
        }
    } catch (Exception e) {
        System.out.println("Error retrieving mouse position: " + e.getMessage());
    }


    double moveSpeed = 180.0; // units per second

    if (keyH.uppress) {
        player.y -= moveSpeed * deltaTime;
    }
    if (keyH.downpress) {
        player.y += moveSpeed * deltaTime;
    }
    if (keyH.leftpress) {
        player.x -= moveSpeed * deltaTime;
    }
    if (keyH.rightpress) {
        player.x += moveSpeed * deltaTime;
    }
    player.direction += 0.1 * deltaTime * 60;
  }

  
  //what to paint
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    g2.setColor(Color.white);
    
    
    //render player
    g2.drawImage(playerfront, (int) player.x, (int) player.y, null);
    g2.drawImage(playerfront, (int) mouseX,   (int) mouseY,   null);
    

    g2.setColor(Color.WHITE);
    g2.setFont(new Font("Arial", Font.BOLD, 16));
    g2.drawString("FPS: " + fps, 10, 20);




    g2.dispose();
  }
}
