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
  public void run(){
    while(gameThread != null){
        update();
        repaint();
        try{Thread.sleep(1000/60);}catch(Exception e){System.out.println(e);}
      }
  }

  
  //update stuff
  public void update(){

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


    if(keyH.uppress == true){
      player.y-=3;
    }
    if(keyH.downpress == true){
      player.y+=3;
    }
    if(keyH.leftpress == true){
      player.x-=3;
    }
    if(keyH.rightpress == true){
      player.x+=3;
    }
    if(mouseH.click == true){
      System.out.println("click");
      mouseH.click = false;
    }
    player.direction += 0.1;
  }

  
  //what to paint
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    g2.setColor(Color.white);
    
    
    //render player
    g2.drawImage(playerfront, (int) player.x, (int) player.y, null);
    g2.drawImage(playerfront, (int) mouseX,   (int) mouseY,   null);
    g2.dispose();
  }
}
