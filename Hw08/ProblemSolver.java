

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeSet;
public class ProblemSolver{
    public static void main(String[] args){
		EightQueensProblem r = new EightQueensProblem();
		System.out.println(BFS(r));
		System.out.println(DFS(r));

		OtherProblem p = new OtherProblem();
		System.out.println(BFS(p));
		System.out.println(DFS(p));
    }

    public static Node DFS(Problem p){
		System.out.println("Running Depth First Search");
		return getSolution(p,  new MyStack());
    }

    public static Node BFS(Problem p){
		System.out.println("Running Breadth First Search");
		return getSolution(p,  new MyQueue());
    }

    public static Node getSolution(Problem p, PileLike pile){
		TreeSet<State> considered = new TreeSet<State>();
		State initialState = p.getStartState();
		Node initialNode = new Node(null, initialState);
		if (p.isGoal(initialState)){return initialNode;}
		pile.push(initialNode);
		considered.add(initialState);
		while (! pile.isEmpty()){
			Node popped = pile.pop();
			ArrayList<State> succs = popped.s.getSuccessors();
			for (State s : succs){
			if (p.isGoal(s)){return new Node(popped, s);}
			else{
				if (! considered.contains(s))
				{
					pile.push(new Node(popped, s));
					considered.add(s);
				}
			}
			}
		}
		return null;//no solution
    }
}

class Node{
    Node parent;
    State s;

    public Node(Node parent, State s){
		this.parent = parent;
		this.s = s;
    }

    public String toString(){
		if (this.parent == null){
			return this.s.toString();
		}
		else{
			return this.parent.toString() + " -> " + this.s.toString();
		}
		
    }
}
interface PileLike{
    public boolean isEmpty();
    public Node pop();
    public void push(Node s);
}

class MyStack extends LinkedList<Node> implements PileLike{
    
}

class MyQueue extends LinkedList<Node> implements PileLike{
    public Node pop(){
		return this.removeLast();
    }
}
