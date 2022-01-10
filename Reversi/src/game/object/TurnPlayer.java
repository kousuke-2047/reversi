package game.object;

import game.enums.PieceColor;
import game.param.Param;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class TurnPlayer {
	private PieceColor turnPlayer;
	private TextField turnPlayerText;
	private Pane turnPlayerPane;
	private TextField labelText;

	public TurnPlayer(PieceColor pieceColor) {
		this.turnPlayerPane = new Pane();
		this.turnPlayer = pieceColor;
		this.turnPlayerText = new TextField(pieceColor.toString());
		this.labelText = new TextField();

		//X座標調整
		this.turnPlayerPane.setLayoutX( (Param.getSQUARE_SIZE()*Param.getGRID_SIZE()) + Param.getREVERSI_PANE_PADDING() + Param.getDIVIDE_SIZE());
		this.turnPlayerPane.setPrefWidth(Param.getTURNPLAYER_PANE_WIDTH());
		this.turnPlayerPane.setPrefHeight(Param.getTURNPLAYER_PANE_HEIGHT());

		this.turnPlayerText.setEditable(false);
		this.turnPlayerText.setPrefHeight(Param.getTURNPLAYER_TEXTFIELD_HEIGHT());
		this.turnPlayerText.setLayoutY(Param.getTURNPLAYER_TEXTFIELD_HEIGHT());

		this.labelText.setPrefHeight(Param.getTURNPLAYER_TEXTFIELD_HEIGHT());
		this.labelText.setText(Param.getTURNPLAYER_LABEL());
		this.labelText.setEditable(false);

		this.turnPlayerPane.getChildren().add(this.labelText);
		this.turnPlayerPane.getChildren().add(this.turnPlayerText);

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
	public TextField getTurnPlayerText() {
		return turnPlayerText;
	}
	public void setTurnPlayerText(TextField turnPlayerText) {
		this.turnPlayerText = turnPlayerText;
	}
	public Pane getTurnPlayerPane() {
		return turnPlayerPane;
	}
	public void setTurnPlayerPane(Pane turnPlayerPane) {
		this.turnPlayerPane = turnPlayerPane;
	}
	public TextField getLabelText() {
		return labelText;
	}
	public void setLabelText(TextField labelText) {
		this.labelText = labelText;
	}

}
