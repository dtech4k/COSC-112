public class Score {
    private int count;
    
    public Score(){
        count =0;
    }

    public Score goal(Score a){
        a.count++;
        return a;
    }
}
