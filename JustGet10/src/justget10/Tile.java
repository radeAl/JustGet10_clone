/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package justget10;


import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Ivona
 */
class Tile extends StackPane implements EventHandler<MouseEvent>{
    static String[] colors = {  "#B9F349", "#57BFF9", "#FFC85B", 
                                "#FF7155", "#019E87", "#7763FA", 
                                "#F557C2", "#E62E45", "#FEF734",
                                "#21610B", "#F5A9BC", "#F5DA81",
                                "#E2A9F3", "#CEF6EC", "#E6E6E6"
    
    };
    
    static String[] activeColors = {  
                                "#B9Faaa", "#57Baaa", "#FFCaaa", 
                                "#FF7155", "#019aaa", "#776aaa", 
                                "#F55aaa", "#E62aaa", "#FEFaaa",
                                "#216aaa", "#F5Aaaa", "#F5Daaa",
                                "#E2Aaaa", "#CEFaaa", "#E6Eaaa"
    
    };
    private int number;
    private boolean active;
    private String color;
    
    public Tile(int number)  {
        this.number = number;
        this.active = false;
        this.color =  colors[number - 1];
        Label l = new Label("" + number);
        l.setStyle("-fx-text-fill:white; -fx-font-size:32px");
        super.getChildren().add(l);
        super.setAlignment(Pos.CENTER);
        super.setStyle("-fx-background-color:" + color);
        super.setMinSize(100, 100);
        
    }

    public int getNumber() {
        return number;
    }

    public boolean isActive() {
        return active;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public void handle(MouseEvent event) {
        if (!active) {
            this.active = true;
            Game.unselectAll();
            Game.selectAll(this);
        }
        else{
            Game.mergeAll(this);
        }
    }

    void setActiveView() {
        super.setStyle("-fx-background-color: " + activeColors[this.number - 1]+ "; -fx-border-width: 1; -fx-border-color:" + color);
        
    }

    void setDefaultView() {
      super.setStyle("-fx-background-color: " + color);
    }
    
    
    
}
