public class Tile {
    private int x;
    private int y;
    private TileType type;

    enum TileType {
        EMPTY,
        FOOD,
        SNAKE_BODY,
        SNAKE_HEAD_UP,
        SNAKE_HEAD_RIGHT,
        SNAKE_HEAD_DOWN,
        SNAKE_HEAD_LEFT
    }

    public Tile(int x, int y){
        this.x = x;
        this.y = y;
        type = TileType.EMPTY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public TileType getType(){
        return type;
    }

    public String getUI(){
        switch (type){
            case EMPTY:
                return "  ";
            case FOOD:
                return "\u25AA ";
            case SNAKE_BODY:
                return "\u25A0 ";
            case SNAKE_HEAD_UP:
                return "\u25B2 ";
            case SNAKE_HEAD_DOWN:
                return "\u25BC ";
            case SNAKE_HEAD_LEFT:
                return "\u25C0 ";
            case SNAKE_HEAD_RIGHT:
                return "\u25B6 ";
        }
        return "! ";
    }
}
