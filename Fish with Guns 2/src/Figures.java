import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;

public class Figures{

    public static BufferedImage heroImage;  
    public static BufferedImage mapImage;
    public static BufferedImage rock1Image;  
    public static BufferedImage babyShrimpImage;  
    public static BufferedImage bigShrimpImage;  
    public static BufferedImage crabImage;  
    public static BufferedImage salmonImage;  
    public static BufferedImage schoolImage;  

    public Figures(){
        try{
            heroImage = ImageIO.read(new File("images/characters/hero.png"));
            mapImage = ImageIO.read(new File("images/tilemap/water1.jpg"));
            rock1Image = ImageIO.read(new File("images/objects/rock1.png"));
            babyShrimpImage = ImageIO.read(new File("images/characters/babyshrimp.png"));
            bigShrimpImage = ImageIO.read(new File("images/characters/bigshrimp.png"));
            crabImage = ImageIO.read(new File("images/characters/crab.png"));
            salmonImage = ImageIO.read(new File("images/characters/salmon.png"));
            schoolImage = ImageIO.read(new File("images/characters/school.png"));

        }
        catch (IOException e){
            e.getStackTrace();
        }
    }
    public static void main(String[] args) {
        Figures fig = new Figures();
    }
}