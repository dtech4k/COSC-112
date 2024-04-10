import java.awt.image.BufferedImage;
public class Enemy extends Character{
    public Enemy(int health, Pair position, Pair velocity, Pair acceleration, Pair collider, BufferedImage image, Classifier c){
        super(health, position, velocity, acceleration, collider, image, c);
    }
    public Enemy(){
        super();
       
    }
    public void interact(MainCharacter hero){
        if (this.position.getX() < hero.position.getX() + hero.collider.getX()*5 &&
        this.position.getX() + this.collider.getX()*5 > hero.position.getX() &&
        this.position.getY() < hero.position.getY() + hero.collider.getY()*5 &&
        this.position.getY() + this.collider.getY()*5 > hero.position.getY()) {
            if (this.position.getX() + hero.collider.getX()/2. < hero.position.getX()){
                this.velocity.setX(Main.SPEED/3);
            }
            else if (this.position.getX() > hero.position.getX() + hero.collider.getX()/2.){
                this.velocity.setX(-Main.SPEED/3);
            }
            else {
                this.velocity.setX(0);
            }
            if (this.position.getY() + hero.collider.getY()/2.< hero.position.getY()){
                this.velocity.setY(Main.SPEED/3);
            }
            else if (this.position.getY() > hero.position.getY() + hero.collider.getY()/2.){
                this.velocity.setY(-Main.SPEED/3);
            }
            else {
                this.velocity.setY(0);
            }




           }
        }
    public static void main(String[] args) {
        
    }
}
