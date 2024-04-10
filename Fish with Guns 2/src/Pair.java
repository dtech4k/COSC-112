
public class Pair {
    private double x,y;
    public Pair(double x,double y){
        this.x = x;
        this.y = y;
    }
    public Pair(){
        this(0,0);
    }
    public String toString(){
        return "x: " + this.x + "y: " + this.y;
    }
    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }
    public void add(Pair other){
        this.x += other.x;
        this.y += other.y;
    }
    public void subtract(Pair other){
        this.x -= other.x;
        this.y -= other.y;
    }
    public Pair divide(double coeff){
        return new Pair(this.x / coeff, this.y / coeff);
    }
    public Pair multiply(double coeff){
        return new Pair(this.x * coeff, this.y * coeff);
    }
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
    public static void main(String[] args) {
        
    }
}
