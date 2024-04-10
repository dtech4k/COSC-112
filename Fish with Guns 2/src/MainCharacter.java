import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class MainCharacter extends Character{
    private Camera cam;
    public MainCharacter(){
        super();
       
    } 
    public MainCharacter(int health, Pair position, Pair velocity, Pair acceleration, Pair collider, BufferedImage image, Classifier c){
        super(health, position, velocity, acceleration, collider, image, c);
    }
    public MainCharacter(int health, Pair position, Pair collider, BufferedImage image){
        super(health, position, collider, image);
    }
    public void setCamera(Camera cam){
        this.cam = cam;
    }

    
    public static void main(String[] args) {
        
    }
}