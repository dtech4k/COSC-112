import java.util.ArrayList;

public class DoubleSubtractOneProblem extends Problem{
    private int target;

    //constructor, establishes some int 'target' via a passed argument
    public DoubleSubtractOneProblem(int target){
	    this.target = target;
    }

    public State getStartState(){
	    return new DoubleSubtractOneState(1, this.target);
    }

    public boolean isGoal(State s){
	    return ((DoubleSubtractOneState)s).currentValue == this.target;
    }
}

class DoubleSubtractOneState extends State{
    private int target;
    public int currentValue;

    public DoubleSubtractOneState(int currentValue, int target){
        this.currentValue = currentValue;
        this.target = target;
    }

    public ArrayList<State> getSuccessors(){
        int doub = this.currentValue * 2;
        int subone = this.currentValue - 1;
        ArrayList<State> toR = new ArrayList<State>();

        if (this.currentValue > 1){
            toR.add(new DoubleSubtractOneState(subone, this.target));
        }
        
        if (this.currentValue < this.target){
            toR.add(new DoubleSubtractOneState(doub, this.target));
        }
        return toR;
    }

    public String toString(){
	    return "[Val: " + this.currentValue + " (" + this.target + ")]";
    }

    public boolean equals(Object s){
        DoubleSubtractOneState other = (DoubleSubtractOneState)s;
        return other.currentValue == this.currentValue && other.target == this.target;
    }

    public int compareTo(State other){
	    return this.currentValue - ((DoubleSubtractOneState)other).currentValue;
    }
    
}