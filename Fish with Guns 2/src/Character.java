import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.Graphics;
import java.awt.Color;

public class Character extends EntityObj implements Updatable{
    protected int health;
    protected transient Pair acceleration; 
    protected transient boolean right;
    protected transient boolean up;

    public Character(){
        this(0,new Pair(0,0),new Pair(0,0),new Pair(0,0), new Pair(0,0), null, Classifier.DEFAULT);
    }
    public Character(int health, Pair position, Pair velocity, Pair acceleration, Pair collider, BufferedImage image, Classifier c){
        super(position, collider, image, c);
        this.health = health;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }
    public Character(int health, Pair position, Pair velocity, Pair collider, BufferedImage image) {   
        this(health, position, velocity, new Pair(0,0), collider, image, Classifier.DEFAULT);
    }
    public Character(int health, Pair position, Pair collider, BufferedImage image){
        this(health, position, new Pair(0,0), new Pair(0,0), collider, image, Classifier.DEFAULT);
    }
    public boolean isAlive(){
        return health <= 0;
    }
    public Pair getPosition(){
        return position;
    }
    public void setPosition(Pair position){
        this.position = position;
    }
    public Pair getVelocity(){
        return velocity;
    }
    public void setVelocity(Pair velocity){
        this.velocity = velocity;
    }
    public void setVelocity(double velocity, boolean horizontal){
        if (horizontal){
            this.velocity.setX(velocity);
        }
        else{
            this.velocity.setY(velocity);
        }
    }
   
    @Override
    public void draw(Graphics g){
        if (this.velocity.getX() < 0){
            // Flip the image horizontally 
          if (right && !Main.paused){
                flipX();
                right = false;
          }          

        } else if (this.velocity.getX() > 0){
            // Flip the image horizontally 
            if (!right && !Main.paused){
                flipX();
                right = true;
            }
            
        }
        if (this.velocity.getY() < 0){
            // Flip the image horizontally 
          if (up && !Main.paused){
                flipY();
                up = false;
          }          

        } else if (this.velocity.getY() > 0){
            // Flip the image horizontally 
            if (!up && !Main.paused){
                flipY();
                up = true;
            }
            
        }
        super.draw(g);

    }
    private void flipX(){
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-image.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        this.image = op.filter(this.image, null);
    }
    private void flipY(){
        AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
        tx.translate(0, -image.getHeight(null));
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        this.image = op.filter(this.image, null);

        // Calculate the new size of the image based on rotation
        // int angle = -90;
        // int originalWidth = image.getWidth();
        // int originalHeight = image.getHeight();
        // int rotatedWidth = (int) Math.ceil(originalWidth * Math.abs(Math.cos(angle)) + originalHeight * Math.abs(Math.sin(angle)));
        // int rotatedHeight = (int) Math.ceil(originalHeight * Math.abs(Math.cos(angle)) + originalWidth * Math.abs(Math.sin(angle)));
       
        // AffineTransform rotate = new AffineTransform();
        // rotate.rotate(angle, originalWidth / 2, originalHeight / 2);
        // AffineTransformOp operation = new AffineTransformOp(rotate, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        // this.image = operation.filter(this.image, null);

    }
    public void update(double time){
        position.add(velocity.multiply(time));
    }
     public void save(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeInt(health);
            out.writeDouble(position.getX());
            out.writeDouble(position.getY());
            out.close();
            System.out.println("Game saved successfully!");

        } catch (IOException e) {
            System.out.println("Error saving game: " + e.getMessage());
        }
    }
    public void load(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            this.health = in.readInt();
            this.position.setX(in.readDouble());
            this.position.setY(in.readDouble());
        } catch (IOException e) {
            System.out.println("Error loading game: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        
    }
    
}
  

