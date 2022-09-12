package com.example.slidingpuzzle;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class SlidingPuzzle extends Application {
    int xpos = 0;
    int ypos = 0;
    int count = 1;

    int size = 100;
    String xkey = "X";
    String ykey = "Y";

    private void move(Node node, int x, int y) {
        // adjust position
        node.setLayoutX( size*xpos);
        node.setLayoutY( size*ypos);

        // save coordinates to property
        node.getProperties().put(xkey, xpos);
        node.getProperties().put(ykey, ypos);

        // save node pos as blank pos
        xpos= x;
        ypos = y;
    }


    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i != 0 || j != 0) {
                    Button button = new Button(Integer.toString(count++));
                    button.setPrefSize(100, 100);
                    button.setLayoutX(size * i);
                    button.setLayoutY(size * j);
                    button.getProperties().put(xkey, i);
                    button.getProperties().put(ykey, j);
                    button.setOnMouseClicked(evt -> {
                        Node node = (Node) evt.getSource();
                        int x = (Integer) node.getProperties().get(xkey);
                        int y = (Integer) node.getProperties().get(ykey);

                        if (Math.abs(x - xpos) + Math.abs(y - ypos) == 1) {
                            // move button, if it's adjacent to the blank
                            move(node, x, y);
                        }
                    });

                    pane.getChildren().add(button);
                }
            }
        }



        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
