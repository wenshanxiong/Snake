import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class InputIO implements Runnable{
    private Snake snake;

    public InputIO(Snake snake){
        this.snake = snake;
    }

    @Override
    public void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            char c = 'a';
            try {
                c = (char) br.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (c != '\n') {
                switch (c) {
                    case 'w':
                        snake.setD(Game.Direction.UP);
                        break;
                    case 'd':
                        snake.setD(Game.Direction.RIGHT);
                        break;
                    case 's':
                        snake.setD(Game.Direction.DOWN);
                        break;
                    case 'a':
                        snake.setD(Game.Direction.LEFT);
                        break;
                }
            }
        }
    }

//    public static void main(String args[]) throws InterruptedException {
//        InputIO runnable = new InputIO();
//        Thread myThread = new Thread(runnable);
//        myThread.start();
//        int i = 1;
//        while (true) {
//            TimeUnit.SECONDS.sleep(1);
//            String dir = "";
//            Game.Direction d = runnable.getDir();
//            switch (d) {
//                case UP:
//                    dir = "w";
//                    break;
//                case RIGHT:
//                    dir = "d";
//                    break;
//                case DOWN:
//                    dir = "s";
//                    break;
//                case LEFT:
//                    dir = "a";
//            }
//            System.out.println(i + ", " + dir);
//            i++;
//        }
//    }
}

