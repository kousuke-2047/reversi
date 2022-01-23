package game.object;

import game.enums.PieceColor;
import game.param.Param;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TurnPlayer {
	private PieceColor turnPlayer;
	private Text turnPlayerText;
	private GridPane turnPlayerPane;
	private Text labelText;

	public TurnPlayer(PieceColor pieceColor) {
		this.turnPlayerPane = new GridPane();
		this.turnPlayer = pieceColor;
		this.turnPlayerText = new Text(pieceColor.toString());
		this.labelText = new Text();

		//座標調整
		this.turnPlayerPane.setLayoutX( (Param.getSQUARE_SIZE()*Param.getGRID_SIZE()) + Param.getREVERSI_PANE_PADDING() + Param.getDIVIDE_SIZE());
		this.turnPlayerPane.setLayoutY(Param.getREVERSI_PANE_PADDING());
		this.turnPlayerPane.setPrefWidth(Param.getTURNPLAYER_PANE_WIDTH());
		this.turnPlayerPane.setPrefHeight(Param.getTURNPLAYER_PANE_HEIGHT());

		this.turnPlayerPane.setVgap(Param.getGRIDPANE_TEXT_PADDING());
		this.turnPlayerPane.setHgap(Param.getGRIDPANE_TEXT_PADDING());

		this.labelText.setText(Param.getTURNPLAYER_LABEL());

		this.labelText.setFont(Font.font(Param.getNORMAL_FOMT_SIZE()));
		this.turnPlayerText.setFont(Font.font(Param.getNORMAL_FOMT_SIZE()));

		this.turnPlayerPane.add(labelText,0,0);
		this.turnPlayerPane.add(turnPlayerText,1,0);


	}

	//プレイヤーチェンジ
	public void changePlayer() {
		if(this.getTurnPlayer().equals(PieceColor.black)) {
			this.setTurnPlayer(PieceColor.white);
			this.setText(PieceColor.white);
		}else if(this.getTurnPlayer().equals(PieceColor.white)) {
			this.setTurnPlayer(PieceColor.black);
			this.setText(PieceColor.black);
		}
	}

	//文字表示
	private void setText(PieceColor pieceColor) {
		this.turnPlayerText.setText(pieceColor.toString());
	}

	public PieceColor getTurnPlayer() {
		return turnPlayer;
	}
	//文字表示処理も追加
	public void setTurnPlayer(PieceColor turnPlayer) {
		this.turnPlayer = turnPlayer;
		this.setText(turnPlayer);
	}
	public Text getTurnPlayerText() {
		return turnPlayerText;
	}
	public void setTurnPlayerText(Text turnPlayerText) {
		this.turnPlayerText = turnPlayerText;
	}
	public GridPane getTurnPlayerPane() {
		return turnPlayerPane;
	}
	public void setTurnPlayerPane(GridPane turnPlayerPane) {
		this.turnPlayerPane = turnPlayerPane;
	}
	public Text getLabelText() {
		return labelText;
	}
	public void setLabelText(Text labelText) {
		this.labelText = labelText;
	}

}
