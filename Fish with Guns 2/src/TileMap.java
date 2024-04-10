import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

// """
//         This class reads a map from the image and converts to 2d array which can later be used 
//         to make physical structures in the game
//         """;

public class TileMap extends JPanel implements Drawable{
    private BufferedImage mapImage;
    private int width;
    private int height;
    private int colorIndex;
    private Tile[][] pixels;
    public static final double tileSize = 64; // Size of each cell
    public static int numRows;
    public static int numCols;
    // Dictionary of colors on the map
    //HashMap<Integer, Integer> colorCounts = new HashMap<Integer,Integer>();


    public TileMap(){
            // Get the width and height of the image
            //width = mapImage.getWidth();
            //height = mapImage.getHeight();

            width = 1024;
            height = 768; 
            
            // Create a 2D array to store the pixel values
            numRows = height;
            numCols = width;

            pixels = new Tile[numCols][numRows];
            
            // Loop through each pixel and store its RGB value in the array

            for (int col = 0; col < numCols; col++) {
                for (int row = 0; row < numRows; row++) {
                    //int rgb = mapImage.getRGB(x, y);
                    pixels[col][row] = new Tile(new Pair(col*tileSize,row*tileSize), tileSize, Figures.mapImage);
                    //counts amount of colors in image
                    // if (colorCounts.containsKey(rgb)) {
                    //     colorCounts.put(rgb, colorCounts.get(rgb) + 1);
                    // } else {
                    //     colorCounts.put(rgb, 1);
                    // }
                    
                }
            }
    
    }
    public void addNotify() {
        super.addNotify();
        requestFocus();
    }
    //Drawing the map
    public void paintComponent(Graphics g) {
        super.paintComponent(g);                    
    }
   public void draw(Graphics g){
    // Loop through the array and draw each cell
    for (int row = 0; row < numRows; row++) {
        for (int col = 0; col < numCols; col++) {
            Tile tile = pixels[col][row];
            tile.draw(g);
        }
    }
   }
   public static void main(String[] args) {
    
   }

    
}
