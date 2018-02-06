/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package justget10;

import java.util.Random;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Ivona
 */
class Game {

    private static int maxNumber = 2;
    private static int result = 0;
    static private Board board;

    

   

    void start() {
        Stage game = new Stage();
        this.board = new Board();
        Scene scene = new Scene(this.board, 600, 600);
        game.setScene(scene);
        game.show();
        
    }

    static void selectAll(Tile t) {
        int i = board.getRowIndex(t);
        int j = board.getColumnIndex(t);
        select(i, j, t.getNumber());
    }

    static void unselectAll() {
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                Tile t = board.getTileOnIndex(i, j);
                if (t != null) {
                    t.setActive(false);
                    t.setDefaultView();
                }
            }
        }
    }

    static void mergeAll(Tile t) {
        if (!hasNeighbors(t))
            return;
        
        int n = t.getNumber();
        int i = board.getRowIndex(t);
        int j = board.getColumnIndex(t);
        board.getChildren().remove(t);
        Tile tile = new Tile(t.getNumber() + 1);
        tile.setOnMouseClicked(tile);
        board.add(tile, j, i);
        
        
        if (tile.getNumber() > maxNumber)
            maxNumber = tile.getNumber();
        result += getResult();
        
        board.setL(result);
        
        removeAllActiveTiles();
        pushDownAllTiles();
        generateTiles();
        checkGame();

    }

    private static void select(int i, int j, int number) {
       
        if (i > 5 || i < 1 || j > 5 || j < 1) {
            return;
        }
        Tile t = board.getTileOnIndex(i, j);
        if (!t.isActive() && t.getNumber() == number) {
            t.setActiveView();
            t.setActive(true);

            select(i - 1, j, number);
            select(i + 1, j, number);
            select(i, j - 1, number);
            select(i, j + 1, number);

        }

    }

    private static void removeAllActiveTiles() {
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                Tile t = board.getTileOnIndex(i, j);
                if (t != null && t.isActive()) {
                    board.getChildren().remove(t);
                }
            }
        }
    }

    private static void generateTiles() {
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                if(board.getTileOnIndex(i, j) == null){
                    Tile t = new Tile(getNextRandom());
                    t.setOnMouseClicked(t);
                    board.add(t, j, i);
                }
            }
        }
    }

    private static int getNextRandom() {
        int mod = 2;
        if (maxNumber > 4) {
            mod = maxNumber - 2;
        }
        Random r = new Random(System.currentTimeMillis());
        int number = Math.abs(r.nextInt()) % mod + 1;

        return number;
    }

    private static void pushDownAllTiles() {
        for (int i = 4; i >= 1; i--) {
            for (int j = 1; j <= 5; j++) {
                if (board.getTileOnIndex(i + 1, j) == null) {
                    pushDown(i, j);
                }
            }
        }
    }

    private static void pushDown(int i, int j) {
        Tile t = board.getTileOnIndex(i, j);
        Tile t2 = null;
        if (t != null) {

            board.getChildren().remove(t);

        } else {
            return;
        }

        while (i < 5 && board.getTileOnIndex(i + 1, j) == null) {
            i = i + 1;
        }
        board.add(t, j, i);

    }
    
    private static boolean hasNeighbors(Tile t) {
        int i = board.getRowIndex(t);
        int j = board.getColumnIndex(t);
        
        boolean b = false;
        
        if (i - 1 >= 1)
            b = b || board.getTileOnIndex(i - 1, j).getNumber() == t.getNumber();
        if (i + 1 <= 5)
            b = b || board.getTileOnIndex(i + 1, j).getNumber() == t.getNumber();
        
        if (j - 1 >= 1)
            b = b || board.getTileOnIndex(i, j - 1).getNumber() == t.getNumber();
        if (j + 1 <= 5)
            b = b || board.getTileOnIndex(i, j+1).getNumber() == t.getNumber();
        return b;
        
    }
    
    private static int getResult(){
        int result = 0;
         for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                Tile t = board.getTileOnIndex(i, j);
                if (t.isActive())
                    result += t.getNumber();
            }
        }
         
        return result;
    }
    
    private static boolean isGameOver() {
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                Tile t = board.getTileOnIndex(i, j);
               
                 if(hasNeighbors(t))
                     return false;
                 
            }        

        }
        return true;
    }
    private static void checkGame() {
       if(isGameOver()){
           Stage stage = new Stage();
           VBox root = new VBox(20);
           root.setAlignment(Pos.CENTER);
           
           Scene scene = new Scene(root, 400, 200);
           
           
           TextField name = new TextField();
           name.setMaxWidth(300);
           Button save = new Button("Sacuvaj!");
           Label l = new Label("Igra je gotova Vas rezultat je " + result + ".\n Unesite Vase ime:\n");
           
           root.getChildren().addAll(l, name, save);
           
           
           save.setOnMouseClicked(e -> {
               
               ResultList results = new ResultList();
               String s = name.getText().trim();
               
               results.addResult(result, s);
               results.save();
               stage.close();
               
           });
           
           
           stage.setScene(scene);
           stage.show();
       }
    }
}
