package sed.test.thenatureofcode;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
    
    Pane subSceneRoot = new Pane();
    AnchorPane apController = new AnchorPane();
    
    @Override
    public void start(Stage stage) {        
        //Side panel buttons
        VBox vbSidePanel = new VBox();
        vbSidePanel.getChildren().addAll(handleSidePanelButtons());
        vbSidePanel.setPrefWidth(300);
        vbSidePanel.setStyle("-fx-background-color: blue");
        vbSidePanel.setAlignment(Pos.CENTER);
        
        
        //subSceneRoot.setStyle("-fx-background-color: yellow");
        SubScene subScene = new SubScene(subSceneRoot, 780, 600);        
        
        apController.setPrefSize(780, 120);
        //apController.setStyle("-fx-background-color: red");
        VBox vbSubSceneAndControllerHolder = new VBox(subScene, apController);
        
        
        //Main Screen Holds the side panel, and the subsceen-and-controller holder. 
        HBox hbMainScreen = new HBox(vbSidePanel, vbSubSceneAndControllerHolder);
        hbMainScreen.setMaxHeight(Double.MAX_VALUE);
        VBox.setVgrow(hbMainScreen, Priority.ALWAYS);
        
        VBox root = new VBox(hbMainScreen);
        var scene = new Scene(root, 1080, 720);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public List<Button> handleSidePanelButtons()
    {
        List<Button> buttons = new ArrayList();
        
        Button btn1 = new Button("Random Walks");
        btn1.setOnAction((actionEvent) ->
        {
            RandomWalks randomWalks = new RandomWalks();
            apController.getChildren().clear();
            apController.getChildren().add(randomWalks.getControllerRoot());
            
            subSceneRoot.getChildren().clear();
            subSceneRoot.getChildren().add(randomWalks.getSubsceneRoot());
        });
        
        buttons.add(btn1);
        
        
        
        return buttons;
    }
}