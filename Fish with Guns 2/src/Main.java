import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.border.Border;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
public class Main extends JPanel implements KeyListener, MouseListener{
    /*
        This is the main game. From this the game will start 
    
     */

    public static boolean paused = false;

    private static BufferedImage heroImage;
    public static MainCharacter hero;
    private static Enemy babyshrimp;
    private static Camera cam;
    private static TileMap map;
    private static Minimap miniMap;
    private static Main window;

    private static EntityObj rock1;

    public static final int SCREEN_WIDTH = 1024;
    public static final int SCREEN_HEIGHT = 768;
    private static final int WORLD_WIDTH = (int)(1024 * TileMap.tileSize);
    private static final int WORLD_HEIGHT = (int)(768 * TileMap.tileSize);
    public static final int FPS = 60;
    
    public static int SPEED = 200;

    public static ArrayList<Character> characters = new ArrayList<Character>();
    public static ArrayList<Drawable> toDraw = new ArrayList<Drawable>();
    public static ArrayList<EntityObj> entities = new ArrayList<EntityObj>();
    public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }
    //Drawing the map
    public void paintComponent(Graphics g) {
        super.paintComponent(g);    
        resetCamera(g);
        draw(g);
    }
    public static void main(String[] args) {
        window = new Main();
        Figures fig = new Figures();
        map = new TileMap(); 
        window.startGame();

        JFrame frame = new JFrame("Fish with Guns");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(window, BorderLayout.CENTER);
        frame.add(miniMap, BorderLayout.EAST);
        frame.setSize(WORLD_WIDTH, WORLD_HEIGHT);
        frame.setPreferredSize(new Dimension(WORLD_WIDTH,WORLD_HEIGHT));
        frame.pack();        
        frame.setVisible(true);
        window.run();
    }
    public Main(){
        this.addKeyListener(this);
        this.addMouseListener(this);
    }

    public void run()
    {
        while(true){
            if (!paused) {
                updateWorld();
            }
            repaint();
            try{
                Thread.sleep(1000 / FPS);
            }
            catch(InterruptedException e){}
        }

    }
    public static void draw(Graphics g){
        for (Drawable drawable : toDraw) {
            drawable.draw(g);
        }

    }
    public static void resetCamera(Graphics g){
        g.translate(-(int)cam.getX(), -(int)cam.getY());
    }
    public void startGame(){  
        //Initiliazing
        //Characters
        hero = new MainCharacter(100, new Pair(2500,500), new Pair(0,0),new Pair(0,0), new Pair(200,100), Figures.heroImage, Classifier.HERO);
        //Babyshrimps
        babyshrimp = new Enemy(100, new Pair(2800,500), new Pair(0,0),new Pair(0,0), new Pair(200,100), Figures.babyShrimpImage, Classifier.ENEMY);
        cam = new Camera(new Pair(hero.getPosition().getX() - SCREEN_WIDTH / 2., hero.getPosition().getY() - SCREEN_HEIGHT / 2.), new Pair(SCREEN_WIDTH,SCREEN_HEIGHT), new Pair(WORLD_WIDTH, WORLD_HEIGHT),hero);
        //Objects
        rock1 = new EntityObj(new Pair(100,100), new Pair(Figures.rock1Image.getWidth(), Figures.rock1Image.getHeight()), Figures.rock1Image, Classifier.ROCK);
        miniMap = new Minimap(window, cam);
        hero.setCamera(cam);

        enemies.add(babyshrimp);
        // characters arraylist for update 
        characters.add(hero);
        characters.addAll(enemies);
         // objects arraylist for collision

        entities.addAll(characters);
        entities.add(rock1);
        // Drawables arraylist for draw 
        toDraw.add(map);
        toDraw.addAll(entities);
        toDraw.add(miniMap);
        

    }
    public static void updateWorld(){
        for (Enemy enemy : enemies) {
            enemy.interact(hero);
        }
        for (Character character : characters) {
            character.update(1 / (double)FPS);
        }
        for (int i = 0; i < entities.size(); i++) {
            for (int j = i+1; j < entities.size(); j++) {
                entities.get(i).collide(entities.get(j));
            }
        }
        
        cam.update(new Pair(hero.getPosition().getX() - SCREEN_WIDTH / 2., hero.getPosition().getY() - SCREEN_HEIGHT / 2.));

    }
    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        if (c == KeyEvent.VK_A && hero.position.getX() > 0){
            hero.setVelocity(-SPEED,true);
        }
        if (c == KeyEvent.VK_D && hero.position.getX() < WORLD_WIDTH){
            hero.setVelocity(SPEED,true);
        }
        if (c == KeyEvent.VK_W && hero.position.getY() > 0){
            hero.setVelocity(-SPEED,false);
        }
        if (c == KeyEvent.VK_S && hero.position.getY() < WORLD_HEIGHT){
            hero.setVelocity(SPEED,false);
        } 
        if (c == KeyEvent.VK_F5){
            for (int i = 0; i < characters.size(); i++) {
                characters.get(i).save("Save_" + i + "_.dat");
            }
        } 
        if (c == KeyEvent.VK_SHIFT){
            SPEED = 400;
        }
        if (c == KeyEvent.VK_F6){
            for (int i = 0; i < characters.size(); i++) {
                characters.get(i).load("Save_" + i + "_.dat");
            }
        } 
        if (c == KeyEvent.VK_P){
            if (!paused) paused = true;
            else {
                paused = false;
            }
        }
    }
        
    
    @Override
    public void keyReleased(KeyEvent e) {
        int c = e.getKeyCode();
        if (c == KeyEvent.VK_SHIFT){
            SPEED = 200;
        }
        hero.setVelocity(new Pair());

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int c = e.getButton();
        if (c == 1){
            int posX = e.getX();
            int posY = e.getY();
        }
        else if (c == 2){
        }
        else if (c == 3){
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
       

    }
    @Override
    public void mouseReleased(MouseEvent e) {
        
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        
    }
    @Override
    public void mouseExited(MouseEvent e) {
       
    }
}
