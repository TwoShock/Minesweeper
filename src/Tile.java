import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class Tile extends Button {
    private boolean tileInfo;
    private State state;
    private final int size = 40;

    Tile(){
        super();
        getStyleClass().add("default_tile");
        setFocusTraversable(false);
        setPrefWidth(size);
        setPrefHeight(size);
        state = State.DEFAULT;
        tileInfo = false;
        //need to set on action
    }
    State getState(){
        return state;
    }
    void setBomb(boolean isBomb){
        this.tileInfo = isBomb;
    }
    boolean isBomb(){
        return tileInfo;
    }

}
