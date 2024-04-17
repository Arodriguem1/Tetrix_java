import java.awt.Color;
import java.awt.Graphics;

public class Shape {
    
    public static final int BOARD_WIDTH = 10;
    public static final int BOARD_HEIGTH = 20;
    public static final int BOARD_SIZE = 30;

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
        this.x = 4;
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
            checkLine();

            board.setCurrentShape();
            return;
        }

        boolean moveX = true;
        if(!(x + deltaX + coords[0].length > 10) && !(x + deltaX < 0)){
            for(int row = 0; row < coords.length; row++){
                for(int col = 0; col < coords[row].length; col ++){
                    if(board.getBoard()[y + row][x + deltaX + col] != null){
                        moveX = false;
                    }
                }
            }
            if(moveX){
                x += deltaX;
            }
        }
        deltaX = 0;

        if(System.currentTimeMillis() - beginTime > delayTimeForMovement) {
            // Vertical movement
            if(!(y + 1 + coords.length > BOARD_HEIGTH)){
                for(int row = 0; row < coords.length; row++){
                    for(int col = 0; col < coords[row].length; col ++){
                        if(coords[row][col] != 0){
                            if(board.getBoard()[y + 1 + row][x + deltaX + col] != null) {
                                collision = true;
                            }
                        }
                    }
                }
                if(!collision) {
                    y++;
                }
            } else {
                collision = true;
            }
            
            beginTime = System.currentTimeMillis();
        }
    }

    private void checkLine(){
        int bottonLine = board.getBoard().length - 1;
        for(int row = board.getBoard().length - 1; row >  0; row--) {
            int count = 0;
            for(int col = 0; col < board.getBoard()[0].length; col++){
                if(board.getBoard()[row][col] != null) {
                    count++;
                }
                board.getBoard()[bottonLine][col] = board.getBoard()[row][col];
            }
            if(count < board.getBoard()[0].length){
                bottonLine--;
            }
        }
    }

    public void render(Graphics g){
        for (int row = 0; row < coords.length; row++) {
            for (int col = 0; col < coords[0].length; col++) {
                if(coords[row][col] != 0){
                    g.setColor(color);
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
