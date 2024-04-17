
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;

public class Board extends JPanel implements KeyListener{
    
    private static int FPS = 60;
    private static int delay = FPS / 1000;

    public static final int BOARD_WIDTH = 10;
    public static final int BOARD_HEIGTH = 20;
    public static final int BOARD_SIZE = 30;

    private Timer looper;

    private Color[] colors = {Color.decode("#ed1c24"), Color.decode("#ff7f27"), Color.decode("#fff200"), 
        Color.decode("#22b14c"), Color.decode("#00a2e8"), Color.decode("#a349a4"), Color.decode("#3f48cc")};

    private Shape[] shapes = new Shape[7];
    private Shape currentShape;

    public Board() {

        shapes[0] = new Shape(new int[][] {
            {1, 1, 1, 1},
        }, this, colors[0]);

        shapes[1] = new Shape(new int[][] {
            {1, 1, 1},
            {0, 1, 0}
        }, this, colors[1]);
        
        shapes[2] = new Shape(new int[][] {
            {1, 1, 1},
            {0, 0, 1}
        }, this, colors[2]);
    
        shapes[3] = new Shape(new int[][] {
            {1, 1, 1},
            {1, 0, 0}
        }, this, colors[3]);
        
        shapes[4] = new Shape(new int[][] {
            {1, 1, 1},
            {0, 1, 0}
        }, this, colors[4]);
    
        shapes[5] = new Shape(new int[][] {
            {0, 1, 1},
            {1, 1, 0}
        }, this, colors[5]);
    
        shapes[6] = new Shape(new int[][] {
            {1, 1},
            {1, 1}
        }, this, colors[6]);

        currentShape = shapes[0];

        looper = new Timer(delay, new ActionListener() {
            int n = 0;

            @Override
            public void actionPerformed(ActionEvent evt) {
                update();
                repaint();
            }
        });
        looper.start();

    }

    private void update() {
        currentShape.update();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        currentShape.render(g);
        
        g.setColor(Color.WHITE);
        
        for (int row = 0; row < BOARD_HEIGTH; row++) {
            g.drawLine(0, BOARD_SIZE * row, BOARD_SIZE * BOARD_WIDTH, BOARD_SIZE * row); 
        }

        for (int col = 0; col < BOARD_WIDTH + 1; col++) {
            g.drawLine(BOARD_SIZE * col, 0, BOARD_SIZE * col, BOARD_SIZE * BOARD_HEIGTH);
        }

    }

    @Override 
    public void keyTyped(KeyEvent e) {

    }

    @Override 
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            currentShape.speedUp();
        } 
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            currentShape.moveRight();
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            currentShape.moveLeft();
        }
    }

    @Override 
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            currentShape.speedDown();
        }
    }
}


