import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class Grid extends GridPane {
    private int gridSize;
    private ArrayList<ArrayList<Tile>> tiles;

    public Grid(int gridSize){
        super();
        this.gridSize = gridSize;
        this.tiles = new ArrayList<ArrayList<Tile>>();
        for (int i = 0;i < gridSize;i++){
            ArrayList<Tile> currentList = new ArrayList<Tile>();
            tiles.add(currentList);
            for (int j = 0;j < gridSize;j++){
                Tile currentTile = new Tile();
                currentList.add(currentTile);
                add(currentTile,i,j);
            }
        }
    }
}
