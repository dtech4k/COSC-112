import java.util.ArrayList;

public class OtherProblem extends Problem{
    private int target;

    //constructor, establishes some int 'target' via a passed argument
    public OtherProblem(){
	    this.target = 257;
    }

    public State getStartState(){
	    return new OtherProblemState(1, this.target);
    }

    public boolean isGoal(State s){
	    return ((OtherProblemState)s).currentValue == this.target;
    }
}

class OtherProblemState extends State{
    private int target;
    public int currentValue;

    public OtherProblemState(int currentValue, int target){
        this.currentValue = currentValue;
        this.target = target;
    }

    public ArrayList<State> getSuccessors(){
        int trip = this.currentValue * 3;
        int subtwo = this.currentValue - 2;
        ArrayList<State> toR = new ArrayList<State>();

        if (this.currentValue > 1){
            toR.add(new OtherProblemState(subtwo, this.target));
        }
        
        if (this.currentValue < this.target){
            toR.add(new OtherProblemState(trip, this.target));
        }
        return toR;
    }

    public String toString(){
	    return "[Val: " + this.currentValue + " (" + this.target + ")]";
    }

    public boolean equals(Object s){
        OtherProblemState other = (OtherProblemState)s;
        return other.currentValue == this.currentValue && other.target == this.target;
    }

    public int compareTo(State other){
	    return this.currentValue - ((OtherProblemState)other).currentValue;
    }
    
}
