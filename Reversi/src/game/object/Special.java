package game.object;

import java.util.ArrayList;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Special {
	private Pane specialPane;
	private GridPane gridPane;
	private Pane randomPane;
	private ArrayList<Text> idTextList;
	private ArrayList<Text> specialTextList;
	private TextField randomText;

	public Special() {
		this.specialPane = new Pane();
		this.gridPane = new GridPane();
		this.randomPane = new Pane();
		this.idTextList = new ArrayList<Text>();
		this.specialTextList = new ArrayList<Text>();
		this.randomText = new TextField();

	}

	public Pane getSpecialPane() {
		return specialPane;
	}
	public void setSpecialPane(Pane specialPane) {
		this.specialPane = specialPane;
	}
	public GridPane getGridPane() {
		return gridPane;
	}
	public void setGridPane(GridPane gridPane) {
		this.gridPane = gridPane;
	}
	public Pane getRandomPane() {
		return randomPane;
	}
	public void setRandomPane(Pane randomPane) {
		this.randomPane = randomPane;
	}
	public ArrayList<Text> getIdTextList() {
		return idTextList;
	}
	public void setIdTextList(ArrayList<Text> idTextList) {
		this.idTextList = idTextList;
	}
	public ArrayList<Text> getSpecialTextList() {
		return specialTextList;
	}

	public void setSpecialTextList(ArrayList<Text> specialTextList) {
		this.specialTextList = specialTextList;
	}
	public TextField getRandomText() {
		return randomText;
	}
	public void setRandomText(TextField randomText) {
		this.randomText = randomText;
	}
}
