import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

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
        getTileAtPosition(0,0).setBomb(true);
        placeNumbers();

    }
    Tile getTileAtPosition(int i,int j){
        return tiles.get(j).get(i);
    }
    void placeNumbers(){
        for (int i = 0;i < gridSize;i++){
            for (int j = 0;j < gridSize; j++){
                int currentNeighbourCount = getNeighbourBombCount(i,j);
                Number currentNumber = new Number(currentNeighbourCount);
                if (currentNeighbourCount > 0 ){
                    getTileAtPosition(i,j).setText(currentNumber.getCount());
                    getTileAtPosition(i,j).setStyle(currentNumber.getStyle()+" -fx-font-weight:bold");
                }
            }
        }
    }
    int getNeighbourBombCount(int x,int y){
        if (getTileAtPosition(x,y).isBomb()) {
            return -1;
        }
        int neighbourBombCount = 0;
        for (int i = x-1;i <= x+1;i++){
            for (int j = y-1;j <= y+1;j++){
                if (!(i >= gridSize || i < 0 || j>= gridSize || j < 0)){
                    if (getTileAtPosition(i,j).isBomb())
                        neighbourBombCount++;
                }
            }
        }
        return neighbourBombCount;
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
    void setBomb(int i,int j){
        Image flag = new Image(getClass().getResourceAsStream("assets/bomb.png"));
        ImageView imageView = new ImageView(flag);

        imageView.setFitHeight(40/2);
        imageView.setFitWidth(40/2);
        getTileAtPosition(i,j).setGraphic(imageView);

    }

    void placeBombs(){
        int tempBombCount = bombCount;
        Random random = new Random();
        while (tempBombCount > 0){
            int i = random.nextInt(gridSize);
            int j = random.nextInt(gridSize);
            if(!getTileAtPosition(i,j).isBomb()){
                tempBombCount--;
                setBomb(i,j);
                getTileAtPosition(i,j).setBomb(true);
            }
        }
    }
}
