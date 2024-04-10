public class Handgun extends Weapon {
    
    //base fire rate for a handgun should be 2 bullets/sec, can be increased with power ups
    public Handgun(){
        this.fireRate=30;
    }

    public void Shoot() {
        // Example bullet properties
       
        Pair position = MainCharacter.getPosition; 
        //ToDo Pair velocity = new Pair(); 
        //ToDo BufferedImage image = 
        Classifier c = Classifier.BULLET;

        Bullet newBullet = new Bullet(position, velocity, image, c);
    }

    public void update(double time){
        fireRate--;
        if (fireRate<=0) {
            fireRate=30;
            Shoot();
        }
    }
}
