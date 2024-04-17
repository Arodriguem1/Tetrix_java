import javax.swing.JFrame;

public class WindowsGame {
    public static final int WIDTH = 445, HEIGHT = 639;

    private Board board;
    private JFrame window;

    public WindowsGame() {
        window = new JFrame("Tetrix");
        window.setSize(WIDTH, HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);
        window.setLocationRelativeTo(null);

        board = new Board();
        window.add(board);
        window.addKeyListener(board);
        window.setVisible(true);
    }
    
    public static void main(String[] args){
        new WindowsGame();
    }
}
