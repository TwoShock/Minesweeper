import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Random;

public class Grid extends GridPane {
    private int gridSize;
    private ArrayList<ArrayList<Tile>> tiles;
    private int bombCount;
    private int flagCount;
    private boolean gameState;

    public boolean isGameOver(){
        return gameState;
    }

    public Grid(int gridSize, int bombCount){
        super();
        this.gridSize = gridSize;
        this.tiles = new ArrayList<ArrayList<Tile>>();
        this.bombCount = bombCount;
        this.flagCount = 0;
        this.gameState = false;
        fillGrid();
    }
    void fillGrid(){
        intializeGrid();
        placeBombs();
        placeNumbers();
        printGrid();
    }
    void newGame(){
        System.out.println();
        clearGrid();
        placeBombs();
        placeNumbers();
        printGrid();

    }
    void clearGrid() {
        for (int i = 0;i<gridSize;i++){
            for (int j = 0;j< gridSize;j++){
                getTileAtPosition(i,j).resetTile();
            }
        }
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
                if (!(i >= gridSize || i < 0 || j >= gridSize || j < 0)){
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
                currentTile.setOnMouseClicked(e->{handleUserInput(e,currentTile,x,y);});
            }
        }
    }

    void handleUserInput(MouseEvent event,Tile currentTile,int i,int j){
        if (event.getButton().equals(MouseButton.PRIMARY)){
            if(!currentTile.getState().equals(State.FLAGGED)) {
                if (currentTile.isBomb()) {
                    currentTile.setStyle("-fx-background-color:red");
                    gameOver();
                } else {
                    reveal(i,j);
                }
            }
        }
        else if(event.getButton().equals(MouseButton.SECONDARY)){
            if (!(flagCount == bombCount)){
                currentTile.toggleFlag();
                flagCount++;
            }
            else if(flagCount == bombCount){
                if (currentTile.getState().equals(State.FLAGGED)) {
                    currentTile.toggleFlag();
                    flagCount--;
                }
            }
        }
    }

    void gameOver(){
        for (int i = 0;i < gridSize;i++){
            for (int j = 0;j < gridSize;j++){
                getTileAtPosition(i,j).displayTile();
            }
        }
        this.gameState = true;
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
                else if(getTileAtPosition(i,j).isEmpty())
                    System.out.print("0 ");
                else if(getTileAtPosition(i,j).isNumber())
                    System.out.print(getTileAtPosition(i,j).getNumber()+" ");
            }
            System.out.println();
        }
    }

    void reveal(int i,int j){//when u reveal u should remove flags before revealing valid tiles
        if(i == gridSize || j == gridSize || i == -1 || j == -1)
            return;
        Tile currentTile = getTileAtPosition(i,j);
        if (currentTile.getState().equals(State.OPENED)){
            return;
        }
        if(currentTile.getState().equals(State.FLAGGED))
            return;
        if (currentTile.isEmpty()) {
            currentTile.displayEmpty();
        }
        if (currentTile.isNumber()){
            currentTile.displayNumber();
            return;
        }
        for (int x = i-1; x <= i+1;x++){
            for(int y = j-1;y <= j+1;y++){
                    reveal(x,y);
            }
        }
    }
}
