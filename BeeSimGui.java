package beeSim2;


import java.awt.Image;
import java.util.Random;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import javafx.util.Duration;



public class BeeSimGui extends Application{
	
	int x = 10;
	int y = 10;

	public void start(Stage primaryStage) throws Exception {
		SimMap s = new SimMap(y, x);
		s.makeForestTestMap();
		

        Group root = new Group();
        
        
        Scene scene = new Scene(root, 20*x, 20*y, Color.BLACK);
        primaryStage.setScene(scene);
        
        primaryStage.show();
        
        Group tiles = new Group();
        for (Tile[] tArry : s.getGrid()) {
        	for (Tile t : tArry) {
        		Rectangle tile = new Rectangle(20, 20);
        		tile.relocate(t.getX() * 20, t.getY() * 20);
        		if (t instanceof Forest ) {
        			tile.setFill(Color.GREEN);
        		}else if (!t.getPassable()) {
        			tile.setFill(Color.GRAY);
        		} else {
        			tile.setFill(Color.BLANCHEDALMOND);
        		}
        		
        		tile.setStroke(Color.WHITE);
        		tile.setStrokeWidth(0.5);
        		
                tiles.getChildren().add(tile);
        	}
        }
        root.getChildren().add(tiles);
        
		
//		Random r = new Random();
//		
//        Scene scene = new Scene(root, 800, 600, Color.BLACK);
//        primaryStage.setScene(scene);
//
//        primaryStage.show();
//        
//        Group circles = new Group();
//        for (int i = 0; i < 30; i++) {
//           Circle circle = new Circle(150, Color.web("white", 0.05));
//           circle.setStrokeType(StrokeType.OUTSIDE);
//           circle.setStroke(Color.web("white", 0.16));
//           circle.setStrokeWidth(4);
//           circles.getChildren().add(circle);
//        }
//        root.getChildren().add(circles);
//        
//        Timeline timeline = new Timeline();
//        for (Node circle: circles.getChildren()) {
//            timeline.getKeyFrames().addAll(
//                new KeyFrame(Duration.ZERO, // set start position at 0
//                    new KeyValue(circle.translateXProperty(), r.nextDouble() * 800),
//                    new KeyValue(circle.translateYProperty(), r.nextDouble() * 600)
//                ),
//                new KeyFrame(new Duration(40000), // set end position at 40s
//                    new KeyValue(circle.translateXProperty(), r.nextDouble() * 800),
//                    new KeyValue(circle.translateYProperty(), r.nextDouble() * 600)
//                )
//            );
//        }
//        // play 40s of animation
//        timeline.play();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}