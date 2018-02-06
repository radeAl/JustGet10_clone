/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package justget10;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Bojana
 */
public class JustGet10 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);
        Button newGame = new Button("Nova igra");
 
        Button records = new Button("Najbolji rezultati");
        
        
        root.getChildren().addAll(newGame, records);
        root.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(root, 600, 600);
        
        primaryStage.setScene(scene);
        
        
        newGame.setOnMouseClicked( e -> {
            primaryStage.close();
            Game game = new Game();
            game.start();
        
        });
        
        ResultList results = new ResultList();
        
        records.setOnMouseClicked(e -> {
        
            primaryStage.close();
            Stage resultStage = new Stage();
            TextArea text = new TextArea();
            Button back = new Button("Nazad");
            
            text.setEditable(false);
            results.sortList();
            text.setText(results.getResultList());
            text.setMinHeight(450);
            
            VBox resultBox = new VBox(20);
            resultBox.setPadding(new Insets(15,15,15,15));
            
            resultBox.getChildren().addAll(back, text);
            
            Scene resultScene = new Scene(resultBox, 600, 600);
            
            resultStage.setScene(resultScene);
            resultStage.setTitle("Rezultati");
            resultStage.show();
            
            back.setOnMouseClicked(e1 ->{
                resultStage.close();
                primaryStage.show();
            });
        });
        
        
        
        
        
        primaryStage.setTitle("JustGet10");
        primaryStage.show();
        
        
        
        
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
