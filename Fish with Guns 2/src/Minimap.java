import java.awt.Graphics;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Minimap extends JPanel implements Drawable {
    //to finish
    Pair position;
    Pair size;
    public void draw(Graphics g){
        // replace with the background of the minimap
        g.setColor(Color.getHSBColor(90f / 360f, 1f, 1f));
        g.drawOval((int)position.getX(), (int)position.getY(), (int)size.getX(), (int)size.getY());
        g.setColor(Color.getHSBColor(120f / 360f, 1f, 0.5f));
        g.fillOval((int)position.getX() + (int)(Main.hero.velocity.getX()) , (int)position.getY(), (int)size.getX(), (int)size.getY());
        g.setColor(Color.white);
        g.fillOval((int)position.getX() + (int)size.getX()/2,(int)position.getY()+(int)size.getY()/2, 25, 25);
        //drawObjects(g);

    }        
    public void drawObjects(Graphics g){
        for (EntityObj entity : Main.entities) {
            switch (entity.classifier) {
                case ROCK:
                g.setColor(Color.getHSBColor(45f / 360f, 1f, 1f));
                    break;
                case ENEMY:
                g.setColor(Color.getHSBColor(90f / 360f, 1f, 1f));
                    break;
            }
            g.fillOval((int)entity.position.getX() - Main.SCREEN_WIDTH/2 + (int)entity.collider.getX()/2, (int)entity.position.getY() - Main.SCREEN_HEIGHT / 2 + (int)entity.collider.getY()/2, 25, 25);
        }
        
    }
    public Minimap(JPanel mainWindow, Camera cam){
        JPanel minimap = new JPanel();
        setLayout(null);
        this.position = cam.position;
        this.size = new Pair(200,200);
        minimap.setBounds((int)position.getX(),(int)position.getY(),(int)size.getX(),(int)size.getY());
        minimap.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        mainWindow.add(minimap);
        minimap.setVisible(true);
        mainWindow.setVisible(true);
    }
    public static void main(String[] args) {
        
    }
}

