package game.object;

import game.enums.PieceColor;
import game.param.Param;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class Score {
	private int whitePieces;
	private int blackPieces;
	private Pane scorePane;
	private TextField whiteScore;
	private TextField blackScore;
	private Pane whitePane;
	private Pane blackPane;
	private Pane winnerPane;
	private TextField winnerText;

	public Score() {
		this.scorePane = new Pane();
		this.whitePane = new Pane();
		this.blackPane = new Pane();
		this.whiteScore = new TextField();
		this.blackScore = new TextField();
		this.winnerPane = new Pane();
		this.winnerText = new TextField();

		this.scorePane.setPrefHeight(Param.getSCORE_PANE_HEIGHT()*2);
		this.scorePane.setPrefWidth(Param.getSCORE_PANE_WIDTH()*2);
		this.whitePane.setPrefHeight(Param.getSCORE_PANE_HEIGHT());
		this.whitePane.setPrefWidth(Param.getSCORE_PANE_WIDTH());
		this.blackPane.setPrefHeight(Param.getSCORE_PANE_HEIGHT());
		this.blackPane.setPrefWidth(Param.getSCORE_PANE_WIDTH());
		this.whiteScore.setPrefHeight(Param.getSCORE_PANE_HEIGHT());
		this.whiteScore.setPrefWidth(Param.getSCORE_PANE_WIDTH());
		this.blackScore.setPrefHeight(Param.getSCORE_PANE_HEIGHT());
		this.blackScore.setPrefWidth(Param.getSCORE_PANE_WIDTH());
		this.winnerPane.setPrefHeight(Param.getSCORE_PANE_HEIGHT());
		this.winnerPane.setPrefWidth(Param.getSCORE_PANE_WIDTH()*2);
		this.winnerText.setPrefHeight(Param.getSCORE_PANE_HEIGHT());
		this.winnerText.setPrefWidth(Param.getSCORE_PANE_WIDTH()*2);

		this.blackPane.setLayoutX(Param.getSCORE_PANE_WIDTH());
		this.winnerPane.setLayoutY(Param.getSCORE_PANE_HEIGHT());

		//X座標調整
		this.scorePane.setLayoutX( (Param.getSQUARE_SIZE()*Param.getGRID_SIZE()) + Param.getREVERSI_PANE_PADDING() + Param.getDIVIDE_SIZE() );
		this.scorePane.setLayoutY(Param.getTURNPLAYER_PANE_HEIGHT());

		this.whiteScore.setEditable(false);
		this.blackScore.setEditable(false);
		this.winnerText.setEditable(false);
		//位置調整
		//this.scorePane.setLayoutY(value);

		this.whitePane.getChildren().add(this.whiteScore);
		this.blackPane.getChildren().add(this.blackScore);
		this.winnerPane.getChildren().add(this.winnerText);
		this.scorePane.getChildren().add(this.whitePane);
		this.scorePane.getChildren().add(this.blackPane);
		this.scorePane.getChildren().add(this.winnerPane);

	}

	//スコア表示
	public void setScore(int whitePieces,int blackPieces) {
		this.setWhitePieces(whitePieces);
		this.setBlackPieces(blackPieces);
		this.setWinner();
	}
	//結果表示
	private void setWinner() {
		if(this.whitePieces > this.blackPieces) {
			this.winnerText.setText(Param.getWINNER_LABEL() + PieceColor.white.toString());
		}else if(this.blackPieces > this.whitePieces) {
			this.winnerText.setText(Param.getWINNER_LABEL() + PieceColor.black.toString());
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
	public TextField getWhiteScore() {
		return whiteScore;
	}
	public void setWhiteScore(TextField whiteScore) {
		this.whiteScore = whiteScore;
	}
	public TextField getBlackScore() {
		return blackScore;
	}
	public void setBlackScore(TextField blackScore) {
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
	public TextField getWinnerText() {
		return winnerText;
	}
	public void setWinnerText(TextField winnerText) {
		this.winnerText = winnerText;
	}
	public Pane getWinnerPane() {
		return winnerPane;
	}
	public void setWinnerPane(Pane winnerPane) {
		this.winnerPane = winnerPane;
	}
}
