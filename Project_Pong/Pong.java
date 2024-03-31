import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;



class World{
    int height;
    int width;

    int numAgents;
    Agent agents[];
 
    public World(int initWidth, int initHeight){
    width = initWidth;
    height = initHeight;
    

    }
    public void drawAgents(Graphics g){
    for (int i = 0; i < numAgents; i++){
        agents[i].draw(g);
    }
    }
 
    public void updateAgents(double time){
    for (int i = 0; i < numAgents; i ++)
        agents[i].update(this, time);
    } 
}

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

class Agent {
    Pair position;
    Pair velocity;
    
}
    
class Ball extends Agent{
    double radius;
    double dampening;
    Color color;

    public Ball(double x, double y){
        position = new Pair(1024/2, 768/2);
        this.velocity = new Pair(x, y);
            
    }

    public void Bounce(Ball a){}

}
    
class Paddle extends Agent{
    double height;
    double width;
    Color color;

    public Paddle(double x){
        position = new Pair(x, 384);
    }
}

public class Pong extends JPanel implements KeyListener{
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static final int FPS = 60;
    World world;
 
    class Runner implements Runnable{
    public void run() {
        while(true){
        	world.updateAgents(1.0 / (double)FPS);
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
 
    public Pong(){
    world = new World(WIDTH, HEIGHT);
    addKeyListener(this);
    this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    Thread mainThread = new Thread(new Runner());
    mainThread.start();
    }
     
    public static void main(String[] args){
    JFrame frame = new JFrame("Physics!!!");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Pong mainInstance = new Pong();
    frame.setContentPane(mainInstance);
    frame.pack();
    frame.setVisible(true);
    }
 
 
    public void paintComponent(Graphics g) {
    super.paintComponent(g);        
 
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, WIDTH, HEIGHT);
 
    world.drawAgents(g);
 
    }
 
     
}