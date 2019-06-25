import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

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

        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.SECONDARY){
                    if (state.equals(State.DEFAULT)) {
                        setFlag();
                    }
                    else {
                        setGraphic(null);
                        state = State.DEFAULT;
                    }
                }
            }
        });
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
    void setFlag(){
        Image flag = new Image(getClass().getResourceAsStream("assets/flag.png"));
        ImageView imageView = new ImageView(flag);

        imageView.setFitHeight(size/2);
        imageView.setFitWidth(size/2);
        setGraphic(imageView);
        state = State.FLAGGED;
    }

}
