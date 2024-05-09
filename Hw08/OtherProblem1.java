import java.util.ArrayList;

public class OtherProblem1 extends Problem{
    private int target;

    //constructor, establishes some int 'target' via a passed argument
    public OtherProblem1(){
	    this.target = 257;
    }

    public State getStartState(){
	    return new OtherProblem1State(1, this.target);
    }

    public boolean isGoal(State s){
	    return ((OtherProblem1State)s).currentValue == this.target;
    }
}

class OtherProblem1State extends State{
    private int target;
    public int currentValue;

    public OtherProblem1State(int currentValue, int target){
        this.currentValue = currentValue;
        this.target = target;
    }

    public ArrayList<State> getSuccessors(){
        int trip = this.currentValue * 3;
        int subtwo = this.currentValue - 2;
        ArrayList<State> toR = new ArrayList<State>();

        if (this.currentValue > 1){
            toR.add(new OtherProblem1State(subtwo, this.target));
        }
        
        if (this.currentValue < this.target){
            toR.add(new OtherProblem1State(trip, this.target));
        }
        return toR;
    }

    public String toString(){
	    return "[Val: " + this.currentValue + " (" + this.target + ")]";
    }

    public boolean equals(Object s){
        OtherProblem1State other = (OtherProblem1State)s;
        return other.currentValue == this.currentValue && other.target == this.target;
    }

    public int compareTo(State other){
	    return this.currentValue - ((OtherProblem1State)other).currentValue;
    }
    
}
