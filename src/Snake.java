import java.util.LinkedList;

public class Snake {
    private LinkedList<Tile> body;
    private LinkedList<Tile> eaten;
    private Game.Direction d;
    private Board board;

    public Snake(Board board) {
        this.board = board;
        this.body = new LinkedList<>();
        this.eaten = new LinkedList<>();
        d = Game.Direction.LEFT;
        int y = board.getHeight() / 2;
        for (int x = board.getWidth() / 2; x < (board.getWidth() / 2) + 2; x++){
            Tile curr = board.getTile(x,y);
            if (x == board.getWidth() / 2) {
                curr.setType(Tile.TileType.SNAKE_HEAD_LEFT);
            } else{
                curr.setType(Tile.TileType.SNAKE_BODY);
            }
            body.add(curr);
        }
    }

    public int getScore() {
        return body.size() + eaten.size() - 2;
    }

    public synchronized void setD(Game.Direction d) {
            if (d == Game.Direction.UP && this.d == Game.Direction.DOWN) {
                return;
            } else if(d == Game.Direction.DOWN && this.d == Game.Direction.UP){
                return;
            }
            else if(d == Game.Direction.LEFT && this.d == Game.Direction.RIGHT){
                return;
            }
            else if(d == Game.Direction.RIGHT && this.d == Game.Direction.LEFT){
                return;
            }
        this.d = d;
    }

    public synchronized Game.Direction getD() { return d;}

    public void forward() throws GameOverException {
        Tile head = body.getFirst();
        head.setType(Tile.TileType.SNAKE_BODY);
//        System.out.println("Direction: " + getD());
        Tile.TileType oldType = Tile.TileType.EMPTY;
        try {
            switch (getD()) {
                case UP:
                    head = board.getTile(head.getX(), head.getY() + 1);
                    oldType = head.getType();
                    head.setType(Tile.TileType.SNAKE_HEAD_UP);
//                    System.out.println("UP");
                    break;
                case RIGHT:
                    head = board.getTile(head.getX() + 1, head.getY());
                    oldType = head.getType();
                    head.setType(Tile.TileType.SNAKE_HEAD_RIGHT);
//                    System.out.println("RIGHT");
                    break;
                case DOWN:
                    head = board.getTile(head.getX(), head.getY() - 1);
                    oldType = head.getType();
                    head.setType(Tile.TileType.SNAKE_HEAD_DOWN);
//                    System.out.println("DOWN");
                    break;
                case LEFT:
                    head = board.getTile(head.getX() - 1, head.getY());
                    oldType = head.getType();
                    head.setType(Tile.TileType.SNAKE_HEAD_LEFT);
//                    System.out.println("UP");
                    break;
            }
        } catch (IndexOutOfBoundsException e){
            throw new GameOverException();
        }
        body.addFirst(head);
        if (oldType == Tile.TileType.FOOD){
            board.setFood();
            eaten.addLast(head);
        } else if (oldType != Tile.TileType.EMPTY){
            throw new GameOverException();
        }

        Tile tail = body.getLast();
        if (eaten.peekFirst() != tail) {
            tail.setType(Tile.TileType.EMPTY);
            body.removeLast();
        } else {
            eaten.removeFirst();
        }
    }
}
