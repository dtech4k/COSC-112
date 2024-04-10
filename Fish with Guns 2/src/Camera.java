public class Camera{
    Pair position;
    Pair size;
    Pair worldSize;
    MainCharacter hero;
    public Camera(Pair position, Pair size,  Pair worldSize, MainCharacter hero){
        this.position = position;
        this.size = size;
        this.worldSize = worldSize;
        this.hero = hero;
    }
    public String toString(){
        return "Position: (" + position.getX() + ", " + position.getY() + ")\n";
    }
    public void update(Pair pos){
        if (pos.getX() >= 0  && pos.getX() <= worldSize.getX()){
            this.position.setX(pos.getX());
        }
        if (pos.getY() >= 0  && pos.getY() <= worldSize.getY()){
            this.position.setY(pos.getY());
        }
    }
    public double getX(){
        return position.getX();
    }
    public double getY(){
        return position.getY();
    }
    public static void main(String[] args) {
        
    }
}
