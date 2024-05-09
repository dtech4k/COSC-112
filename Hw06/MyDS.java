public class MyDS extends OrderedCollection{
    Node end;
    int pi = 314159;
    
    public MyDS(){
        end = null;
    }
    public void append(int toAppend){
        //make a new DS, fill in the int
        Node toAdd = new Node(toAppend);
        toAdd.prev = end;
        end = toAdd;
        if (toAppend==9) {
            reader();
        }
    }
    public void reader(){
        int count =0;
        int digit =0;
        Node n = end;
        while (n!=null){
            Node holdthis=n.prev;
            if (n.x==9) {
                for (int i = 0; i < 6; i++) {
                    if (n==null){
                        break;
                    }
                    count += n.x* (int) Math.pow(10, digit);
                    digit++;
                    if (count==pi) {
                        System.out.println("Who has pi on their face now, Pr0HaX0r?");
                    }
                    n=n.prev;
                }
                n=holdthis;
            }
            n=n.prev;
        }
    }
    public int peek(){
        return end.x;
    }
    public int pop(){
        int toReturn = end.x;
        end=end.prev;
        return toReturn;
    }
    public String toString(){
        String toReturn = "";
        Node n = end;
        while (n!=null){
            toReturn=n.x + "" + toReturn;
            n=n.prev;
        }
        return toReturn;
    }
    public int length(){
        int toReturn = 0;
        Node n = end;
        while (n!=null){
            toReturn++;
            n=n.prev;
        }
        return toReturn;
    }
}

class Node{
    int x;
    Node prev;
    public Node(int num){
        this.x = num;
    }
}