import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;



public class Main extends Application {
    private StackPane root = new StackPane();
    private Grid grid;
    private BorderPane mainLayout;
    private Scene mainScene;
    @Override
    public void start(Stage primaryStage) throws Exception {
        initializeLayout();
        intializeStage(primaryStage);
    }
    void initializeLayout(){
        grid = new Grid(9,10);
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
        mainScene.getStylesheets().add("styles.css");
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
