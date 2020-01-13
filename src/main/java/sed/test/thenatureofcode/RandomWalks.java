/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sed.test.thenatureofcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javafx.util.Pair;

/**
 *
 * @author blj0011
 */
public class RandomWalks
{
    private List<Circle> circles = new ArrayList();
    private final double subsceneWidth = 780;
    private final double subsceneHeight = 600;
    private final double circleRaidus = 5;
    private Timeline timeline;
    Pane mainRoot = new Pane();
    
    public RandomWalks()
    {
    }
    
    Pane getSubsceneRoot()
    {            
        mainRoot.setPrefSize(subsceneWidth, subsceneHeight);
        
        Circle originalCircle = new Circle(subsceneWidth / 2 + circleRaidus, subsceneHeight / 2 + circleRaidus, circleRaidus, Color.BLUE);
        mainRoot.getChildren().add(originalCircle);
        
        return mainRoot;
    }
    
    StackPane getControllerRoot()
    {
        StackPane root = new StackPane();
        AnchorPane.setBottomAnchor(root, 0.0);
        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        
        
        Button btnRandowWalks1 = new Button("Randome Walk 1");
        btnRandowWalks1.setOnAction((actionEvent) ->
        {
            List<Boolean> walks = getWalks();
            AtomicInteger counter = new AtomicInteger(0);
            timeline = new Timeline(new KeyFrame(Duration.seconds(.2), (event) ->
            {
                Circle oldCircle = (Circle)mainRoot.getChildren().get(counter.get());
                Circle newCircle = new Circle(oldCircle.getCenterX(), oldCircle.getCenterY(), circleRaidus, Color.BLUE);
                
                if(counter.get() < walks.size())
                {
                    if(walks.get(counter.getAndIncrement()))
                    {
                        
                        newCircle.setCenterX(newCircle.getCenterX() + 1);
                    }
                    else
                    {
                        newCircle.setCenterX(newCircle.getCenterX() - 1);
                    }
                    
                    mainRoot.getChildren().add(newCircle);
                }
                else 
                {
                    timeline.stop();
                }
            }));
            
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();        
        });
        
        Button btnRandowWalks2 = new Button("Randome Walk 2"); 
        btnRandowWalks2.setOnAction((actionEvent) ->
        {
            List<Pair<Boolean, Boolean>> walks = getWalks2();
            AtomicInteger counter = new AtomicInteger(0);
            
            
            timeline = new Timeline(new KeyFrame(Duration.seconds(.2), (event) ->
            {
                Circle oldCircle = (Circle)mainRoot.getChildren().get(counter.get());
                Circle newCircle = new Circle(oldCircle.getCenterX(), oldCircle.getCenterY(), circleRaidus, Color.BLUE);
            
                if(counter.get() < walks.size())
                {
                    boolean b1 = walks.get(counter.get()).getKey();
                    boolean b2 = walks.get(counter.getAndIncrement()).getValue();
                    
                    if(b1 == true && b2 == true)
                    {
                        newCircle.setCenterX(newCircle.getCenterX() + 1);
                    }
                    else if(b1 == true && b2 == false)
                    {
                        newCircle.setCenterY(newCircle.getCenterY() + 1);
                    }
                    else if(b1 == false && b2 == true)
                    {
                        newCircle.setCenterY(newCircle.getCenterY() - 1);
                    }
                    else if(b1 == false && b2 == false)
                    {
                        newCircle.setCenterX(newCircle.getCenterX() - 1);
                    }
                    
                    mainRoot.getChildren().add(newCircle);
                }
                else 
                {
                    timeline.stop();
                }
            }));
            
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        });
        
        HBox hBox = new HBox(btnRandowWalks1, btnRandowWalks2);
        hBox.setSpacing(5);
        hBox.setMaxSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
        root.getChildren().add(hBox);
        
        root.setStyle("-fx-background-color: yellow");
        return root;
    }
    
    
    List<Boolean> getWalks()
    {
        List<Boolean> walks = new ArrayList();
        int numberOfWalks = 100;
        
        Random random = new Random();
        
        for(int i = 0; i < numberOfWalks; i++)
        {
            walks.add(random.nextInt(2) == 0);
        }
        
        return walks;
    }
    
    List<Pair<Boolean, Boolean>> getWalks2()
    {
        List<Pair<Boolean, Boolean>> walks = new ArrayList();
        
        int numberOfWalks = 100;
        
        Random random = new Random();
        
        for(int i = 0; i < numberOfWalks; i++)
        {
            walks.add(new Pair(random.nextInt(2) == 0, random.nextInt(2) == 0));
        }
        
        return walks;
    }
}
