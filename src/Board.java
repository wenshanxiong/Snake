import java.util.Random;

public class Board {
    private int height;
    private int width;
    private Tile[][] board;
    private Random rand;


    public Board(){
        this.height = 12;
        this.width = 12;
        this.board = new Tile[width][height];
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                board[x][y] = new Tile(x,y);
            }
        }
        rand = new Random();
        setFood();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Tile getTile(int x, int y) {
        return board[x][y];
    }

    public String getPixel(int x, int y){
        return board[x][y].getUI();
    }

    public void setFood(){
        int x = rand.nextInt(width);
        int y = rand.nextInt(height);
        while (getTile(x,y).getType() != Tile.TileType.EMPTY){
            x = rand.nextInt(width);
            y = rand.nextInt(height);
        }
        getTile(x,y).setType(Tile.TileType.FOOD);
    }


}
