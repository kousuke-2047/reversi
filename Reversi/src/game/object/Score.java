package game.object;

import game.enums.PieceColor;
import game.param.Param;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Score {
	private int whitePieces;
	private int blackPieces;
	private Pane scorePane;
	private Text whiteScore;
	private Text blackScore;
	private Pane whitePane;
	private Pane blackPane;
	private Pane winnerPane;
	private Text winnerText;

	public Score(
			double scorePaneHeight,
			double scorePaneWidth,
			double squareSize,
			double gridSize,
			double reversiPanePadding,
			double divideSize,
			double turnPlayerPaneHeight,
			double normalFontSize,
			double boldFontSize
			) {
		this.scorePane = new Pane();
		this.whitePane = new Pane();
		this.blackPane = new Pane();
		this.whiteScore = new Text();
		this.blackScore = new Text();
		this.winnerPane = new Pane();
		this.winnerText = new Text();

		//各パネル大きさ設定
		this.scorePane.setPrefHeight(scorePaneHeight*2);
		this.scorePane.setPrefWidth(scorePaneWidth*2);
		this.whitePane.setPrefHeight(scorePaneHeight);
		this.whitePane.setPrefWidth(scorePaneWidth);
		this.blackPane.setPrefHeight(scorePaneHeight);
		this.blackPane.setPrefWidth(scorePaneWidth);
		this.winnerPane.setPrefHeight(scorePaneHeight);
		this.winnerPane.setPrefWidth(scorePaneWidth*2);

		this.blackPane.setLayoutX(scorePaneWidth);
		this.winnerPane.setLayoutY(scorePaneHeight);

		//X座標調整
		this.scorePane.setLayoutX( (squareSize*gridSize) + reversiPanePadding + divideSize );
		this.scorePane.setLayoutY(Param.getTURNPLAYER_PANE_HEIGHT());

		this.whiteScore.setFont(Font.font(normalFontSize));
		this.blackScore.setFont(Font.font(normalFontSize));
		this.winnerText.setFont(Font.font(boldFontSize));

		this.whitePane.getChildren().add(this.whiteScore);
		this.blackPane.getChildren().add(this.blackScore);
		this.winnerPane.getChildren().add(this.winnerText);
		this.scorePane.getChildren().add(this.whitePane);
		this.scorePane.getChildren().add(this.blackPane);
		this.scorePane.getChildren().add(this.winnerPane);

	}

	//スコア表示
	public void setScore(int whitePieces,int blackPieces,String winnerLabel) {
		this.setWhitePieces(whitePieces);
		this.setBlackPieces(blackPieces);
		this.setWinner(winnerLabel);
	}
	//結果表示
	private void setWinner(String winnerLabel) {
		if(this.whitePieces > this.blackPieces) {
			this.winnerText.setText(winnerLabel + PieceColor.white.toString());
		}else if(this.blackPieces > this.whitePieces) {
			this.winnerText.setText(winnerLabel + PieceColor.black.toString());
		}else{
			this.winnerText.setText(Param.getDRAW_LABEL());
		}
	}

	public int getWhitePieces() {
		return whitePieces;
	}
	public void setWhitePieces(int whitePieces) {
		this.whitePieces = whitePieces;
		this.whiteScore.setText("白：" + String.valueOf(whitePieces));
	}
	public int getBlackPieces() {
		return blackPieces;
	}
	public void setBlackPieces(int blackPieces) {
		this.blackPieces = blackPieces;
		this.blackScore.setText("黒：" + String.valueOf(blackPieces));
	}
	public Pane getScorePane() {
		return scorePane;
	}
	public void setScorePane(Pane scorePane) {
		this.scorePane = scorePane;
	}
	public Text getWhiteScore() {
		return whiteScore;
	}
	public void setWhiteScore(Text whiteScore) {
		this.whiteScore = whiteScore;
	}
	public Text getBlackScore() {
		return blackScore;
	}
	public void setBlackScore(Text blackScore) {
		this.blackScore = blackScore;
	}
	public Pane getWhitePane() {
		return whitePane;
	}
	public void setWhitePane(Pane whitePane) {
		this.whitePane = whitePane;
	}
	public Pane getBlackPane() {
		return blackPane;
	}
	public void setBlackPane(Pane blackPane) {
		this.blackPane = blackPane;
	}
	public Text getWinnerText() {
		return winnerText;
	}
	public void setWinnerText(Text winnerText) {
		this.winnerText = winnerText;
	}
	public Pane getWinnerPane() {
		return winnerPane;
	}
	public void setWinnerPane(Pane winnerPane) {
		this.winnerPane = winnerPane;
	}
}
