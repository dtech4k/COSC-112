import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class EntityObj implements Drawable {
    protected Pair position;
    protected transient Pair collider;
    protected transient Pair velocity; 
    protected transient BufferedImage image;
    protected transient Classifier classifier;

    public EntityObj(Pair position, Pair collider, BufferedImage image, Classifier c){
        this.position = position;
        this.collider = collider;
        this.velocity = new Pair();
        this.image = image;
        this.classifier = c;
    }
    public EntityObj(){
        this.position = new Pair();
        this.collider = new Pair();
        this.velocity = new Pair();
        this.image = null;
        this.classifier = Classifier.DEFAULT;
    }
    @Override
    public void draw(Graphics g){
        g.drawImage(image, (int)position.getX(), (int)position.getY(),(int)collider.getX(),(int)collider.getY(), null);
    }
    public void collide(EntityObj other){
        if (this.position.getX() < other.position.getX() + other.collider.getX() &&
            this.position.getX() + this.collider.getX() > other.position.getX() &&
            this.position.getY() < other.position.getY() + other.collider.getY() &&
            this.position.getY() + this.collider.getY() > other.position.getY()) {
                
            double dx = (this.position.getX() + this.collider.getX() / 2.) - (other.position.getX() + other.collider.getX() / 2.);
            double dy = (this.position.getY() + this.collider.getY() / 2.) - (other.position.getY() + other.collider.getY() / 2.);

            if (dy < 0 ){
                this.position.add(new Pair(0, this.velocity.getY()/2.0));
            } else {
                this.position.subtract(new Pair(0,this.velocity.getY()/2.0));
            }
            if (dx < 0){
                this.position.add(new Pair(this.velocity.getX()/2.0,0)); 
            }else{
                this.position.subtract(new Pair(this.velocity.getX()/2.0,0)); 

            }

        }
    }
    public static void main(String[] args) {
        
    }
}
