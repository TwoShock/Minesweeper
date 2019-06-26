import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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
        getTileAtPosition(0,0).setBomb();
        placeNumbers();
        printGrid();

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
                    getTileAtPosition(i,j).setNumber(currentNeighbourCount);
                    getTileAtPosition(i,j).setTileType(TileType.NUMBER);
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
                final int x  = j;
                final int y = i;
                currentTile.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton() == MouseButton.PRIMARY){
                            if (currentTile.isBomb()){
                                gameOver();
                            }
                            else{
                                reveal(x,y);
                            }
                        }
                        else if(event.getButton().equals(MouseButton.SECONDARY)){
                            currentTile.displayFlag();
                        }
                    }
                });
            }
        }
    }
    void gameOver(){
        System.out.println("GAME OVA");
    }

    void placeBombs(){
        int tempBombCount = bombCount;
        Random random = new Random();
        while (tempBombCount > 0){
            int i = random.nextInt(gridSize);
            int j = random.nextInt(gridSize);
            if(!getTileAtPosition(i,j).isBomb()){
                tempBombCount--;
                getTileAtPosition(i,j).setBomb();
            }
        }
    }
    void printGrid(){
        for (int i = 0; i < gridSize;i++ ){
            for (int j = 0;j<gridSize;j++){
                if (getTileAtPosition(i,j).isBomb())
                    System.out.print("b ");
                else if(getTileAtPosition(i,j).getTileType() == TileType.EMPTY)
                    System.out.print("0 ");
                else if(getTileAtPosition(i,j).getTileType() == TileType.NUMBER)
                    System.out.print(getTileAtPosition(i,j).getNumber()+" ");
            }
            System.out.println();
        }
    }
    void reveal(int i,int j){
        if(i == gridSize)
            return;
        if (j == gridSize)
            return;
        if (i == -1)
            return;
        if (j == -1)
            return;
        Tile currentTile = getTileAtPosition(i,j);
        if (currentTile.getState().equals(State.OPENED)){
            return;
        }
        if (currentTile.isEmpty()) {
            currentTile.setState(State.OPENED);
            currentTile.setDisable(true);
        }
        if (currentTile.isNumber()){
            currentTile.displayNumber();
            currentTile.setState(State.OPENED);
            currentTile.setDisable(true);
            return;
        }
            reveal(i+1,j+1);
            reveal(i+1,j);
            reveal(i+1,j-1);
            reveal(i,j+1);
            reveal(i,j-1);
            reveal(i-1,j+1);
            reveal(i-1,j);
            reveal(i-1,j-1);
    }
}
