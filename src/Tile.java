import javafx.scene.control.Button;

public class Tile extends Button {
    private boolean isBomb;
    private State state;
    private final int size = 40;

    Tile(){
        super();
        setMinWidth(size);
        setMinHeight(size);
        state = State.DEFAULT;
        isBomb = false;
    }
    void setBomb(boolean isBomb){
        this.isBomb = isBomb;
    }

}
