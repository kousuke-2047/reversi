package game.object;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SpecialPane {
	private Pane specialPane;
	private GridPane gridPane;
	private Pane randomPane;
	private Text randomText;
	//選ばれたスペシャル情報
	private SpecialAttribute selectSpecial;
	//すでに分けているかチェック
	private boolean alreadyHorizontalFlg;
	private boolean alreadyVerticalFlg;

	public SpecialPane(
			ArrayList<SpecialAttribute> specialList,
			double normalFontSize,
			double randomGridPaneWidth,
			double gridPaneTextPadding,
			double boldFontSize,
			double randomTextPaneWidth,
			double squareSize,
			double gridSize,
			double reversiPanePadding) {
		this.specialPane = new Pane();
		this.gridPane = new GridPane();
		this.randomPane = new Pane();
		this.selectSpecial = new SpecialAttribute(0);
		this.randomText = new Text(String.valueOf(selectSpecial.getId()));

		setDefaultGridPane(specialList,normalFontSize,randomGridPaneWidth,gridPaneTextPadding);
		setDefaultRandomPane(boldFontSize,randomTextPaneWidth,randomGridPaneWidth);

		specialPane.setPrefWidth(randomGridPaneWidth + randomTextPaneWidth);
		specialPane.setLayoutY(squareSize*gridSize + reversiPanePadding*2);
		specialPane.setLayoutX(reversiPanePadding);

		specialPane.getChildren().add(gridPane);
		specialPane.getChildren().add(randomPane);

	}

	//初期設定
	private void setDefaultGridPane(
			ArrayList<SpecialAttribute> specialList,
			double normalFontSize,
			double randomGridPaneWidth,
			double gridPaneTextPadding
			) {
		for(SpecialAttribute s:specialList) {

			gridPane.add(getText(String.valueOf(s.getId()),normalFontSize), 0, s.getId()-1);
			gridPane.add(getText(s.getSpecial().toString(),normalFontSize), 1, s.getId()-1);
		}
		gridPane.setPrefWidth(randomGridPaneWidth);
		gridPane.setVgap(gridPaneTextPadding);
		gridPane.setHgap(gridPaneTextPadding);

	}
	//初期設定
	private void setDefaultRandomPane(
			double boldFontSize,
			double randomTextPaneWidth,
			double randomGridPaneWidth) {

		randomText.setFont(Font.font(boldFontSize));
		randomText.setLayoutX(randomTextPaneWidth);
		randomText.setLayoutY(randomTextPaneWidth);

		randomPane.getChildren().add(randomText);
		randomPane.setPrefWidth(randomTextPaneWidth);
		randomPane.setLayoutX(randomGridPaneWidth);
	}

	private Text getText(String text,double fontSize) {
		Text t = new Text(text);
		t.setFont(Font.font(fontSize));
		return t;
	}

	//数値ルーレット
	public void rouletteSpecial(ArrayList<SpecialAttribute> specialList) {
		Timer t = new Timer();
		t.scheduleAtFixedRate(new TimerTask() {
			int count = 0;
			@Override
			public void run() {
				Random r = new Random();
				String text = String.valueOf(r.nextInt(specialList.size()+1));
				randomText.setText(text);
				count++;
				if(count > 100) {
					blinkingSpecial();
					t.cancel();
				}
			}
		}, 0, 10);
	}
	//確率テーブルから決定
	private void setRandomInt(ArrayList<SpecialAttribute> specialList) {
		Random r = new Random();
		int randomInt = r.nextInt(100);
		for(SpecialAttribute s:specialList) {
			/*
			if(s.getProbability() > randomInt && (!s.isNeedHorizontalFlg() || s. )) {
				this.selectSpecial = s;
				break;
			}
			*/
		}

	}



	//点滅エフェクト
	private void blinkingSpecial() {
		Timer t = new Timer();
		t.scheduleAtFixedRate(new TimerTask() {
			int count = 0;
			@Override
			public void run() {
				if(count%2 == 0) {
					randomText.setVisible(false);
				}else {
					randomText.setVisible(true);
				}
				count++;
				if(count > 7) {
					t.cancel();
				}
			}
		}, 0, 100);
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
	public SpecialAttribute getSelectSpecial() {
		return selectSpecial;
	}
	public void setSelectSpecial(SpecialAttribute selectSpecial) {
		this.selectSpecial = selectSpecial;
	}

}
