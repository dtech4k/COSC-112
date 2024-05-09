import java.util.ArrayList;
import java.util.Map;
import java.util.TreeSet;
import java.util.HashMap;

public class EightQueensProblem extends Problem {
    private int[][] board;
    private static final int BOARD_SIZE = 8;
    private static final int QUEENS_COUNT = 8;

    public EightQueensProblem() {
        this.board = new int[BOARD_SIZE][BOARD_SIZE];
    }

    public State getStartState() {
        return new EightQueensState(this.board, 0);
    }

    public boolean isGoal(State s) {
        EightQueensState state = (EightQueensState) s;
        return state.getQueens() == QUEENS_COUNT;
    }
     public Node getSolution(Problem p, PileLike pile) {
        TreeSet<State> considered = new TreeSet<State>();
        State initialState = p.getStartState();
        Node initialNode = new Node(null, initialState);
        if (p.isGoal(initialState)) {
            return initialNode;
        }
        pile.push(initialNode);
        considered.add(initialState);
        Node finalNode = null;
        while (!pile.isEmpty()) {
            Node popped = pile.pop();
            ArrayList<State> succs = popped.s.getSuccessors();
            for (State s : succs) {
                if (p.isGoal(s)) {
                    finalNode = new Node(popped, s);
                    break;
                } else {
                    if (!considered.contains(s)) {
                        Node newNode = new Node(popped, s);
                        pile.push(newNode);
                        considered.add(s);
                    }
                }
            }
            if (finalNode != null) {
                break;
            }
        }
        return finalNode;
    }
}

class EightQueensState extends State {
    private int[][] board;
    private int queens;

    public EightQueensState(int[][] board, int queens) {
        this.board = board;
        this.queens = queens;
    }

    public int getQueens() {
        return queens;
    }

    public ArrayList<State> getSuccessors() {
        ArrayList<State> successors = new ArrayList<>();

        // Iterate over each row in the current column
        for (int row = 0; row < queens; row++) {
            // Check if placing a queen at this position is valid
            if (isValidPlacement(row, queens)) {
                // Create a new board with the queen placed at this position
                int[][] newBoard = cloneBoard(board);
                newBoard[row][queens] = 1; // Place the queen
                // Create a new state representing the successor and add it to the list
                successors.add(new EightQueensState(newBoard, queens + 1));
            }
        }

        return successors;
    }

    private boolean isValidPlacement(int row, int col) {
        // Check if there is no queen in the same row
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1) {
                return false;
            }
        }
        // Check upper diagonal on left side
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }
        // Check lower diagonal on left side
        for (int i = row, j = col; i < board.length && j >= 0; i++, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }
        return true;
    }

    private int[][] cloneBoard(int[][] board) {
        int[][] newBoard = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, newBoard[i], 0, board[i].length);
        }
        return newBoard;
    }

    @Override
    public int compareTo(State otherState) {
        if (otherState instanceof EightQueensState) {
            EightQueensState other = (EightQueensState) otherState;
            // Compare based on the number of queens placed
            return Integer.compare(this.queens, other.queens);
        } else {
            // If the other state is not an EightQueensState, it's arbitrary
            return 1;
        }
    }

    public String toString(State finalState) {
        Node finalNode = (Node) finalState;
        StringBuilder path = new StringBuilder();
        Node current = finalNode;
        while (current != null) {
            path.insert(0, current.s.toString());
            if (current.parent != null) {
                path.insert(0, " -> ");
            }
            current = current.parent;
        }
        return path.toString();
    }

    private Map<Integer, Character> createColumnMap() {
        // Create a map to convert column index to column character (a, b, c, ...)
        Map<Integer, Character> columnMap = new HashMap<>();
        for (int i = 0; i < board.length; i++) {
            columnMap.put(i, (char) ('a' + i));
        }
        return columnMap;
    }
}