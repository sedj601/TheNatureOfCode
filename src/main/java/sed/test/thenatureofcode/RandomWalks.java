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
import javafx.animation.Animation;
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
    private final double subsceneWidth = 780;
    private final double subsceneHeight = 600;
    private final double circleRaidus = 5;
    private final int step = 5;
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
        Button btnRandowWalks2 = new Button("Randome Walk 2"); 
        
        btnRandowWalks1.setOnAction((actionEvent) ->
        {
            if(mainRoot.getChildren().size() > 1)
            {
                mainRoot.getChildren().clear();
                Circle originalCircle = new Circle(subsceneWidth / 2 + circleRaidus, subsceneHeight / 2 + circleRaidus, circleRaidus, Color.BLUE);
                mainRoot.getChildren().add(originalCircle);
            }
            
            List<Boolean> walks = getWalks();
            AtomicInteger counter = new AtomicInteger(0);
            timeline = new Timeline(new KeyFrame(Duration.seconds(.2), (event) ->
            {
                System.out.println("running 0");
                Circle oldCircle = (Circle)mainRoot.getChildren().get(counter.get());
                oldCircle.setFill(Color.BLACK);
                Circle newCircle = new Circle(oldCircle.getCenterX(), oldCircle.getCenterY(), circleRaidus, Color.BLUE);
                
                if(counter.get() < walks.size())
                {
                    if(walks.get(counter.getAndIncrement()))
                    {
                        
                        newCircle.setCenterX(newCircle.getCenterX() + step);
                    }
                    else
                    {
                        newCircle.setCenterX(newCircle.getCenterX() - step);
                    }
                    
                    mainRoot.getChildren().add(newCircle);
                }
                else 
                {
                    System.out.println("stopped 0");
                    timeline.stop();
                }
            }));
            
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.statusProperty().addListener((ov, t, t1) ->
            {
                if(t1.equals(Animation.Status.STOPPED))
                {
                    btnRandowWalks1.setDisable(false);
                    btnRandowWalks2.setDisable(false);
                }
            });
            timeline.play();     
            btnRandowWalks1.setDisable(true);
            btnRandowWalks2.setDisable(true);
        });
        
        
        btnRandowWalks2.setOnAction((actionEvent) ->
        {
            if(mainRoot.getChildren().size() > 1)
            {
                mainRoot.getChildren().clear();
                Circle originalCircle = new Circle(subsceneWidth / 2 + circleRaidus, subsceneHeight / 2 + circleRaidus, circleRaidus, Color.BLUE);
                mainRoot.getChildren().add(originalCircle);
            }
            
            List<Pair<Boolean, Boolean>> walks = getWalks2();
            AtomicInteger counter = new AtomicInteger(0);
            
            
            timeline = new Timeline(new KeyFrame(Duration.seconds(.2), (event) ->
            {
                System.out.println("running");
                Circle oldCircle = (Circle)mainRoot.getChildren().get(counter.get());
                oldCircle.setFill(Color.BLACK);
                Circle newCircle = new Circle(oldCircle.getCenterX(), oldCircle.getCenterY(), circleRaidus, Color.BLUE);
            
                if(counter.get() < walks.size())
                {
                    boolean b1 = walks.get(counter.get()).getKey();
                    boolean b2 = walks.get(counter.getAndIncrement()).getValue();
                    
                    if(b1 == true && b2 == true)
                    {
                        newCircle.setCenterX(newCircle.getCenterX() + step);
                    }
                    else if(b1 == true && b2 == false)
                    {
                        newCircle.setCenterY(newCircle.getCenterY() + step);
                    }
                    else if(b1 == false && b2 == true)
                    {
                        newCircle.setCenterY(newCircle.getCenterY() - step);
                    }
                    else if(b1 == false && b2 == false)
                    {
                        newCircle.setCenterX(newCircle.getCenterX() - step);
                    }
                    
                    mainRoot.getChildren().add(newCircle);
                }
                else 
                {
                    System.out.println("stopped");
                    timeline.stop();
                }
            }));
            
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.statusProperty().addListener((ov, t, t1) ->
            {
                if(t1.equals(Animation.Status.STOPPED))
                {
                    btnRandowWalks1.setDisable(false);
                    btnRandowWalks2.setDisable(false);
                }
            });
            timeline.play();     
            btnRandowWalks1.setDisable(true);
            btnRandowWalks2.setDisable(true);
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
