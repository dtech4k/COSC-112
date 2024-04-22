import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.util.Random;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.RenderingHints;
import java.awt.GradientPaint;

public class Skyline extends JPanel{
    public static final int WIDTH=1024;
    public static final int HEIGHT=768;
    public static void main(String[] args){
        JFrame frame = new JFrame("Skyline");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Skyline());
        frame.pack();
        frame.setVisible(true);
    }
    public Skyline(){
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }
    
    @Override
    public void paintComponent(Graphics gOri){
        Graphics2D g = (Graphics2D) gOri;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);

        GradientPaint sunSet= new GradientPaint(0, 0, Color.BLACK, 0, HEIGHT, new Color(55, 0, 0));
        g.setPaint(sunSet);
        g.fill(new Rectangle2D.Double(0, 0, WIDTH, HEIGHT));
        Random rand = new Random();
        //Your code here
        //an array for all the various heights to live inside
        int[] heights=new int[1024];
        //generate the initial height w/in bounds [95,105]
        int skyHeight=95+rand.nextInt(10);
        heights[0]=skyHeight;
        //nowgenerate the heights of each of the subsequent
        //to keep the stars above the horizon i ought to keep track of the tallest one.
        int tallest=105;
        for (int i = 1; i < heights.length; i++) {
            skyHeight=newSkyHeight(skyHeight);
            if(skyHeight>tallest){
                tallest=skyHeight;
            }
            heights[i]=skyHeight;
        }

        //100 stars
        int[] starsHeight = new int[100];
        //if i generate a bunch of random heights+tallest, then i can just place each one randomly across the range of width
        //define space we have to work with
        int spaceRemaining = HEIGHT-tallest;
        for (int i = 0; i < starsHeight.length; i++) {
            starsHeight[i]=rand.nextInt(spaceRemaining);
        }

        //lets get drawing
        g.setColor(Color.white);
        for (int i = 0; i < heights.length; i++) {
            //total hight minus each height for the y value
            g.drawRect(i, HEIGHT-heights[i], 1, heights[i]);
        }

        //draw stars
        for (int i = 0; i < starsHeight.length; i++) {
            int xCoord = rand.nextInt(WIDTH);
            g.drawOval(xCoord, starsHeight[i], 1, 1);
        }
    }

    public static int newSkyHeight(int a){
        Random rand = new Random();
        //next height should be +||- 5\
        //[a-5, a+5]==11 total combinations
        //so if I just generate a random number [0, 10]
        int toUse = rand.nextInt(10);
        //now i have some int in that range, if i subtract 5 from it, (centering this at 5) it'll give me something [-5, +5]
        toUse-=5;
        int toReturn = a=toUse;
        return toReturn;
    }
}
