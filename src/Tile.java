public class Tile {
    private int x;
    private int y;
    private TileType type;

    enum TileType {
        EMPTY,
        FOOD,
        SNAKE
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
            case SNAKE:
                return "\u25A0 ";
        }
        return "! ";
    }
}
