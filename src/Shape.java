import java.awt.Color;
import java.awt.Graphics;

public class Shape {
    
    public static final int BOARD_WIDTH = 10;
    public static final int BOARD_HEIGTH = 20;
    public static final int BOARD_SIZE = 30;

    private Color[][] shape = {
        {Color.RED, Color.RED, Color.RED},
        {null, Color.RED, null}
    };

    private int x = 4, y = 0;
    private int normal = 600;
    private int fast = 50;
    private int delayTimeForMovement = normal;
    private long beginTime;

    private int deltaX = 0;
    private boolean collision = false;

    private int[][] coords;
    private Board board;
    private Color color;

    public Shape(int[][] coords, Board board, Color color) {
        this.coords = coords;
        this.board = board;
        this.color = color;

    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public void reset() {
        this.x = 0;
        this.y = 0;
        collision = false;
    }

    public void update(){
        if(collision){
            // Draw de color for board
            for(int row = 0; row < coords.length; row++){
                for(int col = 0; col < coords[0].length; col++){
                    if(coords[row][col] != 0){
                        board.getBoard()[y + row][x + col] = color;
                    }
                }
            }
            board.setCurrentShape();
            return;
        }

        if(!(x + deltaX + shape[0].length > 10) && !(x + deltaX < 0)){
            x+= deltaX;
        }
        deltaX = 0;

        if(System.currentTimeMillis() - beginTime > delayTimeForMovement) {
            if(!(y + 1 + shape.length > BOARD_HEIGTH)){
                y ++;
            }else {
                collision = true;
            }
            
            beginTime = System.currentTimeMillis();
        }
    }

    public void render(Graphics g){
        for (int row = 0; row < coords.length; row++) {
            for (int col = 0; col < coords[0].length; col++) {
                if(coords[row][col] != 0){
                    g.setColor(Color.RED);
                    g.fillRect(BOARD_SIZE * col + x * BOARD_SIZE, BOARD_SIZE * row + y * BOARD_SIZE, BOARD_SIZE, BOARD_SIZE);
                }
            }
        }
    }

    public void speedUp(){
        delayTimeForMovement = fast;
    }

    public void speedDown(){
        delayTimeForMovement = normal;
    }

    public void moveRight(){
        deltaX = 1;
    }

    public void moveLeft(){
        deltaX = -1;
    }
}
