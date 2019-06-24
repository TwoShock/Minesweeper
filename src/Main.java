import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    private StackPane root = new StackPane();
    private Grid grid;
    @Override
    public void start(Stage primaryStage) throws Exception {
        grid = new Grid(18);
        Scene scene = new Scene(grid);
        primaryStage.setTitle("Minesweeper");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}
