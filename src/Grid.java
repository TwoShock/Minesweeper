import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.Random;

public class Grid extends GridPane {
    private int gridSize;
    private ArrayList<ArrayList<Tile>> tiles;
    private int bombCount;

    public Grid(int gridSize,int bombCount){
        super();
        this.gridSize = gridSize;
        this.tiles = new ArrayList<ArrayList<Tile>>();
        this.bombCount = bombCount;
        intializeGrid();
        placeBombs();
    }
    Tile getTileAtPosition(int i,int j){
        return tiles.get(j).get(i);
    }

    void intializeGrid(){
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

    void placeBombs(){
        int tempBombCount = bombCount;
        Random random = new Random();
        while (tempBombCount > 0){
            int i = random.nextInt(gridSize);
            int j = random.nextInt(gridSize);
            if(!getTileAtPosition(i,j).isBomb()){
                tempBombCount--;
                getTileAtPosition(i,j).setText("b");//for development
                getTileAtPosition(i,j).setBomb(true);
            }
        }
    }
}
