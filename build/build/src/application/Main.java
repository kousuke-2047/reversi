package application;

import game.param.Param;
import game.reversi.Reversi;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

			Reversi reversi = new Reversi();
			Pane rootPane = new Pane();
			Pane reversiPane = new Pane();

			reversiPane.getChildren().add(reversi);
			rootPane.getChildren().add(reversiPane);
			Scene scene = new Scene(rootPane,Param.getROOT_PANE_SIZE(),Param.getROOT_PANE_SIZE());

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
