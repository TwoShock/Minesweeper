import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tile extends Button {
    private TileType tileType;
    private State state;
    private final int size = 40;
    private int number;

    public int getNumber() {
        if (tileType.equals(TileType.NUMBER)){
            return number;
        }
        return -1;
    }

    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }
    void displayBomb(){
        Image flag = new Image(getClass().getResourceAsStream("assets/bomb.png"));
        ImageView imageView = new ImageView(flag);

        imageView.setFitHeight(40/2);
        imageView.setFitWidth(40/2);
        setGraphic(imageView);

    }
    boolean isNumber(){
        return tileType.equals(TileType.NUMBER);
    }
    boolean isEmpty(){
        return tileType.equals(TileType.EMPTY);
    }

    public void setState(State state) {
        this.state = state;
    }

    void displayNumber(){
        Number number = new Number(this.number);
        setText(number.getCount());
        setStyle(number.getStyle());
    }

    public void setNumber(int number) {
        this.number = number;
    }

    Tile(){
        super();
        getStyleClass().add("default_tile");
        setFocusTraversable(false);
        setPrefWidth(size);
        setPrefHeight(size);
        state = State.DEFAULT;
        tileType = TileType.EMPTY;
    }
    State getState(){
        return state;
    }

    public TileType getTileType() {
        return tileType;
    }

    void setBomb(){
        tileType = TileType.BOMB;
    }
    boolean isBomb(){
        return tileType == TileType.BOMB;
    }
    void displayFlag(){
        if(state == State.DEFAULT) {
            Image flag = new Image(getClass().getResourceAsStream("assets/flag.png"));
            ImageView imageView = new ImageView(flag);

            imageView.setFitHeight(size / 2);
            imageView.setFitWidth(size / 2);
            setGraphic(imageView);
            state = State.FLAGGED;
        }
        else {
            setGraphic(null);
            state = State.DEFAULT;
        }
    }

}
