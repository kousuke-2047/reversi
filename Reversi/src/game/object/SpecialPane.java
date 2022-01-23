package game.object;

import game.param.Param;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SpecialPane {
	private Pane specialPane;
	private GridPane gridPane;
	private Pane randomPane;
	private Text randomText;

	public SpecialPane() {
		this.specialPane = new Pane();
		this.gridPane = new GridPane();
		this.randomPane = new Pane();
		this.randomText = new Text("0");

		setDefaultGridPane();
		setDefaultRandomPane();

		specialPane.setPrefWidth(Param.getRANDOM_GRIDPANE_WIDTH() + Param.getRANDOM_TEXTPANE_WIDTH());
		specialPane.setLayoutY(Param.getSQUARE_SIZE()*Param.getGRID_SIZE() + Param.getREVERSI_PANE_PADDING()*2);
		specialPane.setLayoutX(Param.getREVERSI_PANE_PADDING());

		specialPane.getChildren().add(gridPane);
		specialPane.getChildren().add(randomPane);

	}

	private void setDefaultGridPane() {
		for(SpecialAttribute s:Param.getSPECIAL_LIST()) {

			gridPane.add(getText(String.valueOf(s.getId()),Param.getNORMAL_FOMT_SIZE()), 0, s.getId()-1);
			gridPane.add(getText(s.getSpecial().toString(),Param.getNORMAL_FOMT_SIZE()), 1, s.getId()-1);
		}
		gridPane.setPrefWidth(Param.getRANDOM_GRIDPANE_WIDTH());
		gridPane.setVgap(Param.getGRIDPANE_TEXT_PADDING());
		gridPane.setHgap(Param.getGRIDPANE_TEXT_PADDING());

	}
	private void setDefaultRandomPane() {

		randomText.setFont(Font.font(Param.getBOLD_FONT_SIZE()));
		randomText.setLayoutX(Param.getRANDOM_TEXTPANE_WIDTH());
		randomText.setLayoutY(Param.getRANDOM_TEXTPANE_WIDTH());

		randomPane.getChildren().add(randomText);
		randomPane.setPrefWidth(Param.getRANDOM_TEXTPANE_WIDTH());
		randomPane.setLayoutX(Param.getRANDOM_GRIDPANE_WIDTH());
	}

	private Text getText(String text,double fontSize) {
		Text t = new Text(text);
		t.setFont(Font.font(fontSize));
		return t;
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
	public Text getRandomText() {
		return randomText;
	}
	public void setRandomText(Text randomText) {
		this.randomText = randomText;
	}
}
