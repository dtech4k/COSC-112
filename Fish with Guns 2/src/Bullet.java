import java.awt.image.BufferedImage;

public class Bullet extends EntityObj {
    int halfLife;

    public Bullet(Pair position, Pair velocity, BufferedImage image, Classifier c) {
        super(position, velocity, image, c); // Assuming EntityObj constructor fits this signature.
        this.halfLife = 60; // 1 second at 60 FPS.
    }

    public void update(double time) {
        // Assuming a fixed time step for simplicity; in a real game, you'd use the actual frame time.
        position.add(velocity.multiply(time));
        halfLife--;

        if (halfLife <= 0) {
            // Code to remove or deactivate the bullet.
            // This might involve setting a flag that tells the game to remove this bullet from its active list.
        }
    }
}