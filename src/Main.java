import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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
                    grid.setFirstMove(true);
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
        grid = new Grid(16,40);
        mainLayout = new BorderPane();

        MenuBar menuBar = new MenuBar();

        Menu newGame = new Menu("New");
        Menu help = new Menu("Help");
        Menu about = new Menu("About");

        menuBar.getMenus().add(newGame);
        menuBar.getMenus().add(help);
        menuBar.getMenus().add(about);

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
