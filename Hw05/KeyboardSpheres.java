import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
class Pair{
  public double x;
  public double y;
   
  public Pair(double initX, double initY){
      x = initX;
      y = initY;
  }

  public Pair add(Pair toAdd){
      return new Pair(x + toAdd.x, y + toAdd.y);
  }

  public Pair divide(double denom){
      return new Pair(x / denom, y / denom);
  }

  public Pair times(double val){
      return new Pair(x * val, y * val);
  }

  public void flipX(){
      x = -x;
  }
   
  public void flipY(){
      y = -y;
  }
}

class Sphere{
  Pair position;
  Pair velocity;
  Pair acceleration;
  double radius;
  double dampening;
  Color color;
  public Sphere() {
  Random rand = new Random(); 
  position = new Pair(500.0, 500.0);
  velocity = new Pair((double)(rand.nextInt(1000) - 500), (double)(rand.nextInt(1000) - 500));
  acceleration = new Pair(0.0, 200.0);
  radius = 25;
  dampening = 1.3;
  color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
  }
  
  public void update(World w, double time){
  position = position.add(velocity.times(time));
  velocity = velocity.add(acceleration.times(time));
  bounce(w);
  }
   
  public void setPosition(Pair p){
      position = p;
  }
  public void setVelocity(Pair v){
      velocity = v;
  }
  public void setAcceleration(Pair a){
      acceleration = a;
  } 
  public Pair getPosition(){
      return position;
  }
  public Pair getVelocity(){
      return velocity;
  }
  public Pair getAcceleration(){
      return acceleration;
  } 
  public double flipX() {
      acceleration.flipX();
  return 0.0;
  }
  public double flipY() {
      acceleration.flipY();
  return 0.0;
  }
  public void draw(Graphics g){
  Color c = g.getColor();
   
  g.setColor(color);
  g.drawOval((int)(position.x - radius), (int)(position.y - radius), (int)(2*radius), (int)(2*radius));
  g.setColor(c);
  }
  
  private void bounce(World w){
  Boolean bounced = false;
  if (position.x - radius < 0){
      velocity.flipX();
      position.x = radius;
      bounced = true;
  }
  else if (position.x + radius > w.width){
      velocity.flipX();
      position.x = w.width - radius;
      bounced = true;
  }
  if (position.y - radius < 0){
      velocity.flipY();
      position.y = radius;
      bounced = true;
  }
  else if(position.y + radius >  w.height){
      velocity.flipY();
      position.y = w.height - radius;
      bounced = true;
  }
  if (bounced){
      velocity = velocity.divide(dampening);
  }
  }
   
}

class World{
  int height;
  int width;
   
  int numSpheres;
  Sphere spheres[];

  public World(int initWidth, int initHeight, int initNumSpheres){
  width = initWidth;
  height = initHeight;

  numSpheres = initNumSpheres;
  spheres  = new Sphere[numSpheres];
   
  for (int i = 0; i < numSpheres; i ++)
      {
      spheres[i] = new Sphere();
      }
  }

  public void drawSpheres(Graphics g){
  for (int i = 0; i < numSpheres; i++){
      spheres[i].draw(g);
  }
  }

  public void updateSpheres(double time){
  for (int i = 0; i < numSpheres; i ++)
      spheres[i].update(this, time);
  } 
}

public class KeyboardSpheres extends JPanel implements KeyListener{
  public static final int WIDTH = 1024;
  public static final int HEIGHT = 768;
  public static final int FPS = 60;
  World world;

  class Runner implements Runnable{
  public void run() {
      while(true){
          world.updateSpheres(1.0 / (double)FPS);
          repaint();
      try{
          Thread.sleep(1000/FPS);
          }
      catch(InterruptedException e){}
      }

  }
   
  }


  public void keyPressed(KeyEvent e) {
      char c = e.getKeyChar();
      System.out.println("You pressed down: " + c);
      if (c=='w') {
        for (int i = 0; i < world.spheres.length; i++) {
            Pair toWork = world.spheres[i].getAcceleration();
            if (toWork.y>=0) {
                if (toWork.x!=0) {
                    if (toWork.x<0) {
                        toWork.flipX();
                    }
                    toWork.y=toWork.x;
                    toWork.x=0;
                }
                toWork.x=0;
                toWork.flipY();
            }
            world.spheres[i].setAcceleration(toWork);
        }
      }
      if (c=='s') {
        for (int i = 0; i < world.spheres.length; i++) {
            Pair toWork = world.spheres[i].getAcceleration();
            if (toWork.y<=0) {
                if (toWork.x!=0) {
                    if (toWork.x>0) {
                        toWork.flipX();
                    }
                    toWork.y=toWork.x;
                    toWork.x=0;
                }
                toWork.x=0;
                toWork.flipY();
            }
            world.spheres[i].setAcceleration(toWork);
        }
      }
      if (c=='a') {
        for (int i = 0; i < world.spheres.length; i++) {
            Pair toWork = world.spheres[i].getAcceleration();
            if (toWork.x>=0) {
                if (toWork.y!=0) {
                    if (toWork.y<0) {
                        toWork.flipY();
                    }
                    toWork.x=toWork.y;
                    toWork.y=0;
                }
                
                toWork.flipX();
            }
            world.spheres[i].setAcceleration(toWork);
        }
      }
      if (c=='d') {
        for (int i = 0; i < world.spheres.length; i++) {
            Pair toWork = world.spheres[i].getAcceleration();
            if (toWork.x<=0) {
                if (toWork.y!=0) {
                    if (toWork.y>0) {
                        toWork.flipY();
                    }
                    toWork.x=toWork.y;
                    toWork.y=0;
                }
                toWork.flipX();
            }
            world.spheres[i].setAcceleration(toWork);
        }
      }
      //color
      if (c=='1') {
        for (int i = 0; i < world.spheres.length; i++) {
            world.spheres[i].color=Color.PINK;
        }
      }

      if (c=='2') {
        for (int i = 0; i < world.spheres.length; i++) {
            world.spheres[i].color=Color.red;
        }
      }

      if (c=='3') {
        for (int i = 0; i < world.spheres.length; i++) {
            world.spheres[i].color=Color.yellow;
        }
      }

      if (c=='4') {
        for (int i = 0; i < world.spheres.length; i++) {
            world.spheres[i].color=Color.green;
        }
      }

      //thats all the colors I know
  }
  
  public void keyReleased(KeyEvent e) {
      char c=e.getKeyChar();
  }


  public void keyTyped(KeyEvent e) {
      char c = e.getKeyChar();
  }
  
  public void addNotify() {
      super.addNotify();
      requestFocus();
  }

  public KeyboardSpheres(){
  world = new World(WIDTH, HEIGHT, 50);
  addKeyListener(this);
  this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
  Thread mainThread = new Thread(new Runner());
  mainThread.start();
  }
   
  public static void main(String[] args){
  JFrame frame = new JFrame("Physics!!!");
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  KeyboardSpheres mainInstance = new KeyboardSpheres();
  frame.setContentPane(mainInstance);
  frame.pack();
  frame.setVisible(true);
  }


  public void paintComponent(Graphics g) {
  super.paintComponent(g);        

  g.setColor(Color.BLACK);
  g.fillRect(0, 0, WIDTH, HEIGHT);

  world.drawSpheres(g);

  }

   
}
