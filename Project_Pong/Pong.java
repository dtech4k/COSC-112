import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;



class World{
    public int player1Score =0;
    public int player2Score =0;
    int height;
    int width;
    Paddle paddles[] = new Paddle[2];
    public Ball balls[] = new Ball[1];
    public int numBalls=1;

    public World(int initWidth, int initHeight){
    width = initWidth;
    height = initHeight;
    paddles[0] = new Paddle(1000);
    paddles[1] = new Paddle(24);
    balls[0] = new Ball();
    }

    public void drawAgents(Graphics g){
    for (int i = 0; i < paddles.length; i++){
            paddles[i].draw(g);
    }
    for (int i = 0; i < balls.length; i++){
        balls[i].drawSphere(g);
    }
    }
 
    public void updateAgents(double time){


        
        for (int i = 0; i < paddles.length; i ++){
            paddles[i].update(this, time);
            if (paddles[i].position.y<=0 || paddles[i].position.y>=768-150) {
                paddles[i].velocity.y=0;
            }
        }
        for (int j = 0; j < balls.length; j ++){
            balls[j].update(this, time);
        }
        
    }
    

    public void teeHee(){
        numBalls++;
        Ball[] ballsNew = new Ball[numBalls];
        for (int i = 0; i < balls.length; i++) {
            ballsNew[i]= balls[i];
        }
        ballsNew[balls.length] = new Ball();
        balls = ballsNew;
    }

    public void uhOh(){
        if (balls.length == 0) {
            balls = new Ball[1];
            balls[0] = new Ball();
        }
    }

    public void checkBoundaries(){
        for (int i = 0; i < balls.length; i++) {
            Ball ball = balls[i];
            double ballX = ball.position.x;
            double ballY = ball.position.y;
            double rPaddleX = paddles[0].position.x;
            double lPaddleX = paddles[1].position.x;
            double paddleY = paddles[0].position.y;
    
            //right padle collision
            if ((ballX + ball.radius >= rPaddleX) && (ballX - ball.radius <= rPaddleX + paddles[0].width) && (ballY >= paddleY) && (ballY <= paddleY + paddles[0].height)){
                //ew
                handlePaddleCollision(ball, paddles[0]);
                teeHee();
            }
    
            //left paddle collision
            if ((ballX-ball.radius<=lPaddleX+paddles[1].width)&&(ballX+ball.radius>=lPaddleX)&&(ballY+ball.radius>=paddleY)&&(ballY-ball.radius<=paddleY+paddles[1].height)){
                handlePaddleCollision(ball, paddles[1]);
                //this... is gross
                teeHee();
            }
            //goes off right side
            if (ballX - ball.radius > width) {
                balls[i] = null;  //removes ball from array
                //get rid of null balls
                int index = 0;
                Ball[] newBalls = new Ball[balls.length];
                for (int j = 0; j < balls.length; j++) {
                    if (balls[j]!=null) {
                        newBalls[index++] = balls[j];
                    }
                }
                balls = new Ball[index];

                for (int j = 0; j < index; j++) {
                    balls[j] = newBalls[j];
                }

                //check 4 no more balls
                uhOh();
                  //increment player1 score
                player1Score++;
                System.out.println("Player One Score: "+player1Score+"\nPlayer Two Score: "+player2Score);
                continue;
            }
    
            //goes off left side
            if (ballX + ball.radius < 0) {
                balls[i] = null;  //removes ball from array
                //get rid of null balls
                int index = 0;
                Ball[] newBalls = new Ball[balls.length];
                for (int j = 0; j < balls.length; j++) {
                    if (balls[j]!=null) {
                        newBalls[index++] = balls[j];
                    }
                }
                balls = new Ball[index];

                for (int j = 0; j < index; j++) {
                    balls[j] = newBalls[j];
                }

                //check 4 no more balls
                uhOh();
                //increment player2 score
                player2Score++;
                System.out.println("Player One Score: "+player1Score+"\nPlayer Two Score: "+player2Score);
                continue;
            }
            //get rid of null balls
            int index = 0;
            Ball[] newBalls = new Ball[balls.length];
            for (int j = 0; j < balls.length; j++) {
                if (balls[j]!=null) {
                    newBalls[index++] = balls[j];
                }
            }
            balls = new Ball[index];

            for (int j = 0; j < index; j++) {
                balls[j] = newBalls[j];
            }

            //check 4 no more balls
            uhOh();
        }
    }
    
    private void handlePaddleCollision(Ball ball, Paddle paddle) {
        double paddleCenter = paddle.position.y + paddle.height / 2;
        double distanceFromCenter = ball.position.y - paddleCenter;
        
        //i hate hate hate pythagoras
        double angle = distanceFromCenter*(Math.PI / (paddle.height / 2));
        ball.velocity.flipX();
        //i h8 pythagoras 
        ball.velocity.y = Math.sin(angle)*ball.velocity.magnitude();
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

    public double magnitude() {
        double toReturn = Math.sqrt((x*x)+(y*y));
        return toReturn;
    }

}

class Agent {
    Pair position;
    Pair velocity;

    public void update(World w, double time){
        position = position.add(velocity.times(time));
        bounce(w);
        }
    public void bounce(World w){}
}
    
class Ball extends Agent{
    double radius=8;
    Color color;

    public Ball(){
        Random rand = new Random(); 
        position = new Pair(1024/2, 768/2);
        velocity = new Pair((double)(rand.nextInt(600) - 500), (double)(rand.nextInt((600) - 500)+1));
        
        color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
    }
    public void update(World w, double time){
        position = position.add(velocity.times(time));
        
        bounce(w);
        }

        
    public void bounce(World w){
        
        if (position.y - radius < 0){
            velocity.flipY();
            position.y = radius;
            
        }

        else if(position.y+radius > w.height){
            velocity.flipY();
            position.y = w.height-radius;
            
        }
    }

    public void drawSphere(Graphics g){
        Color c = g.getColor();
         
        g.setColor(color);
        g.fillOval((int)(position.x - radius), (int)(position.y - radius), (int)(2*radius), (int)(2*radius));
        g.setColor(c);
    }

    

}
    
class Paddle extends Agent{
    double height=150;
    double width=16;
    Color color;

    public void update(World w, double time){
        position = position.add(velocity.times(time));
        
        bounce(w);
        }

    @SuppressWarnings("static-access")
    public Paddle(double x){
        
        position = new Pair(x, 384);
        color = color.white;
        velocity = new Pair(0, 0);
    }

    public void draw(Graphics g){
        Color c = g.getColor();
         
        g.setColor(color);
        g.fillRect((int)(position.x), (int)(position.y), (int)(width), (int)(height));
        g.setColor(c);
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
            world.checkBoundaries();
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
        //left paddle
        if (c=='r') {
            //up
            if (world.paddles[1].velocity.y!=-200 && world.paddles[1].position.y>50) {
                world.paddles[1].velocity.y-=200;
                
            }
        }
        if (c=='f') {
            if (world.paddles[1].velocity.y!=0) {
                world.paddles[1].velocity.y=0;
            }
        }
        if (c=='v') {
            if (world.paddles[1].velocity.y!=200&& world.paddles[1].position.y<(693)) {
                world.paddles[1].velocity.y+=200;
            }
            
        }
        //righ paddle
        if (c=='u') {
            if (world.paddles[0].velocity.y!=-200 && world.paddles[0].position.y>50) {
                world.paddles[0].velocity.y-=200;
            }
        }
        if (c=='j') {
            if (world.paddles[0].velocity.y!=0) {
                world.paddles[0].velocity.y=0;
            }
        }
        if (c=='n') {
            if (world.paddles[0].velocity.y!=200&& world.paddles[0].position.y<(693)) {
                world.paddles[0].velocity.y+=200;
            }
            
        }
    }
    public void keyReleased(KeyEvent e) {
        
    }
 
 
    public void keyTyped(KeyEvent e) {
    	
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
        //machine god, please compile && run
    JFrame frame = new JFrame("Pong");
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