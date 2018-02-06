/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package justget10;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Random;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Ivona
 */
class Board extends GridPane {
    private Label l;
    public Board() {
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                Random r = new Random(System.currentTimeMillis());
                Tile t = new Tile(Math.abs(r.nextInt() % 3) + 1);
                t.setOnMouseClicked(t);
                super.add(t, i, j);
                super.setAlignment(Pos.CENTER);
            }
        }

        l = new Label("0");
        l.setStyle("-fx-text-fill:green; -fx-font-size:32px");
        super.add(l, 3, 7);
    }

    public Tile getTileOnIndex(int i, int j) {
        ObservableList<Node> childrens = super.getChildren();
        Node result = null;
        for (Node node : childrens) {
            if (super.getRowIndex(node) == i && super.getColumnIndex(node) == j) {
                result = node;
                break;
            }

        }
        return (Tile) result;

    }

    public void SetTileOnIndex(Tile t, int i, int j) {
        super.add(t, i, j);
    }
    public void setL(int r){
        l.setText(" " + r);
    }
}
