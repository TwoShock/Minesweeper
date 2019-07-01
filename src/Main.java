import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



public class Main extends Application {
    private Grid grid;
    private BorderPane mainLayout;
    private Scene mainScene;
    @Override
    public void start(Stage primaryStage) throws Exception {
        initializeLayout();
        intializeStage(primaryStage);
        mainScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER) && grid.isGameOver()){
                    grid.newGame();
                }

            }
        });

        for (int i = 0;i < grid.getGridSize()*grid.getGridSize();i++){
            final int tempI = i;
            grid.getChildren().get(i).setOnMouseClicked(e->{
                int y = (int) tempI/grid.getGridSize();
                int x = (int) tempI % grid.getGridSize();
                grid.handleUserInput(e,x,y);

            });
        }
    }
    void initializeLayout(){
        grid = new Grid(8,1);
        mainLayout = new BorderPane();

        MenuBar menuBar = new MenuBar();

        Menu newGame = new Menu("New");
        Menu help = new Menu("Help");
        Menu about = new Menu("About");

        MenuItem rules = new MenuItem("Rules");
        MenuItem eightByEight = new MenuItem("8 x 8");
        MenuItem sixteenBySixteen = new MenuItem("16 x 16");
        MenuItem twentyFourByTwentyFour = new MenuItem("24 x 24");

        menuBar.getMenus().add(newGame);
        menuBar.getMenus().add(help);
        menuBar.getMenus().add(about);

        help.getItems().add(rules);
        newGame.getItems().addAll(eightByEight,sixteenBySixteen,twentyFourByTwentyFour);

        mainLayout.setCenter(grid);
        mainLayout.setTop(menuBar);

        mainScene = new Scene(mainLayout);
        mainScene.getStylesheets().add("assets/styles.css");
    }
    void intializeStage(Stage stage){
        stage.setTitle("Minesweeper");
        stage.setResizable(false);
        stage.setScene(mainScene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
