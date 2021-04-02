import java.util.concurrent.TimeUnit;

public class Game {
    private Board board; // (0,0) is bottom left
    private Snake snake;
    private String frame;
    private String newPage;

    enum Direction {
        UP,
        RIGHT,
        DOWN,
        LEFT
    }

    public Game(){
        this.board = new Board();
        this.snake = new Snake(board);
        String s = "";

        for (int i = 0; i < board.getWidth() + 2; i++){
            s += "\u25A9 ";
        }
        this.frame = s + '\n';

        s = "";
        for (int i = 0; i < 50; i++){
            s += "\n";
        }
        this.newPage = s;
    }

    public Snake getSnake(){
        return snake;
    }

    public void printScore() {
        System.out.println(newPage + "SCORE: " + snake.getScore());
    }

    private void render(){
        String UI = frame;
        for (int y = board.getHeight() - 1; y >= 0; y--){
            String row = "\u25A9 ";
            for (int x = 0; x < board.getWidth(); x++){
                row += board.getPixel(x,y);
            }
            row += "\u25A9";
            UI += row + '\n';
        }
        UI += frame;
        System.out.print(UI);
    }

    public static void main(String args[]) throws InterruptedException {
        Game game = new Game();
        Snake snake = game.getSnake();
        InputIO inputIO = new InputIO(snake);
        Thread IOThread = new Thread(inputIO);
        IOThread.start();
        int i = 1;
        while (true) {
            game.printScore();
            game.render();
            TimeUnit.MILLISECONDS.sleep(500);
            try {
                snake.forward();
            } catch (GameOverException e){
                game.printScore();
                System.out.println("YOU LOST FUCKING PIECE OF SHIT");
                game.render();
                return;
            }
        }
    }

}
