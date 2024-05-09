public class Tester{
    public static void main(String args[]){
        MyDS ds = new MyDS();
        testDS(ds);
    }
    public static void testDS(OrderedCollection ds){
      //append some ints to ds, pop from ds, peek, and print it

      System.out.println(ds);
      ds.append(1);
      ds.append(2);
      ds.append(3);
      ds.append(9);
      System.out.println(ds);
      ds.pop();
      System.out.println(ds.peek());
      System.out.println(ds);
      ds.append(4);
      ds.append(5);
      ds.append(6);
      ds.pop();
      ds.pop();
      ds.pop();
      ds.append(7);
      System.out.println(ds);
      ds.append(8);
      //ds.append(9);
      ds.append(3);ds.append(1);ds.append(4);ds.append(1);ds.append(5);ds.append(9);
      System.out.println(ds);
    }
}