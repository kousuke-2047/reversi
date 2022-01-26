package game.object;

import game.enums.PieceColor;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Piece {
	private PieceColor pieceColor;
	private Circle circle;

	public Piece(PieceColor pieceColor,int xGrid,int yGrid,double squareSize) {
		this.pieceColor = pieceColor;
		if(pieceColor == PieceColor.black) {
			this.circle = new Circle(squareSize*xGrid+squareSize/2,squareSize*yGrid+squareSize/2,squareSize/2,Color.BLACK);
		}else if(pieceColor == PieceColor.white){
			this.circle = new Circle(squareSize*xGrid+squareSize/2,squareSize*yGrid+squareSize/2,squareSize/2,Color.WHITE);
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
