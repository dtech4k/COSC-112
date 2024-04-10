import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Tile {
    private Pair position;
    private double size;
    private BufferedImage image;
    public Tile(Pair position, double size, BufferedImage image){
        this.position = position; 
        this.size = size;
        this.image = image;
    }
    public void draw(Graphics g){
        g.drawImage(image, (int) position.getX(), (int) position.getY(), (int) size, (int) size, null);
    }
    public static void main(String[] args) {
        
    }
}
