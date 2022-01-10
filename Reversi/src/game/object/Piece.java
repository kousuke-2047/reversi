package game.object;

import game.enums.PieceColor;
import game.param.Param;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Piece {
	private PieceColor pieceColor;
	private Circle circle;

	public Piece(PieceColor pieceColor,int xGrid,int yGrid) {
		this.pieceColor = pieceColor;
		if(pieceColor == PieceColor.black) {
			this.circle = new Circle(Param.getSQUARE_SIZE()*xGrid+Param.getSQUARE_SIZE()/2,Param.getSQUARE_SIZE()*yGrid+Param.getSQUARE_SIZE()/2,Param.getSQUARE_SIZE()/2,Color.BLACK);
		}else if(pieceColor == PieceColor.white){
			this.circle = new Circle(Param.getSQUARE_SIZE()*xGrid+Param.getSQUARE_SIZE()/2,Param.getSQUARE_SIZE()*yGrid+Param.getSQUARE_SIZE()/2,Param.getSQUARE_SIZE()/2,Color.WHITE);
		}
	}

	public PieceColor getPieceColor() {
		return pieceColor;
	}
	public void setPieceColor(PieceColor pieceColor) {
		this.pieceColor = pieceColor;
	}
	public Circle getCircle() {
		return circle;
	}
	public void setCircle(Circle circle) {
		this.circle = circle;
	}

}
