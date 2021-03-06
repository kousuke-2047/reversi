package game.object;
import java.util.ArrayList;

import game.enums.PieceColor;
import game.param.Param;
import game.reversi.Reversi;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square {
	private int xGrid;
	private int yGrid;
	private Piece piece;
	private Rectangle rectangle;
	//全てのチェック後に置くことができる場合のみtrue
	private boolean canPutPiece;
	private Rectangle canPutRectangle;
	private ArrayList<ArrayList<Integer>> reversePieceList;
	private int minXGrid;
	private int maxXGrid;
	private int minYGrid;
	private int maxYGrid;
	//ピース配置禁止フラグ
	private boolean banPutPiece;

	public Square(
			int xGrid,
			int yGrid,
			double squareSize,
			Color squareColor,
			Color squareLineColor,
			Color canPutSquareColor,
			double canNotPutOpocity,
			int minXGrid,
			int maxXGrid,
			int minYGrid,
			int maxYGrid
			) {
		this.xGrid = xGrid;
		this.yGrid = yGrid;
		this.minXGrid = minXGrid;
		this.maxXGrid = maxXGrid;
		this.minYGrid = minYGrid;
		this.maxYGrid = maxYGrid;
		this.banPutPiece = false;
		this.canPutRectangle = new Rectangle(xGrid*squareSize,yGrid*squareSize,squareSize,squareSize);
		this.rectangle = new Rectangle(xGrid*squareSize,yGrid*squareSize,squareSize,squareSize);

		//背景色、サイズ、グリッド線、透明度
		this.rectangle.setFill(squareColor);
		this.rectangle.setStroke(squareLineColor);

		this.canPutRectangle.setFill(canPutSquareColor);
		this.canPutRectangle.setOpacity(canNotPutOpocity);
		this.reversePieceList = new ArrayList<ArrayList<Integer>>();

		this.getCanPutRectangle().setOnMouseEntered(this::mouseEntered);
	}

	//ピースありコンストラクタ
	//規定値用のコンストラクタ
	public Square(
			int xGrid,
			int yGrid,
			PieceColor pieceColor,
			double squareSize,
			Color squareColor,
			Color squareLineColor,
			Color canPutSquareColor,
			double canNotPutOpocity,
			int minXGrid,
			int maxXGrid,
			int minYGrid,
			int maxYGrid
			) {
		this(xGrid,yGrid,squareSize,squareColor,squareLineColor,canPutSquareColor,canNotPutOpocity,minXGrid,maxXGrid,minYGrid,maxYGrid);
		this.piece = new Piece(pieceColor,xGrid,yGrid,squareSize);
	}

	//裏返し処理
	public void reversePiece() {
		if(piece.getPieceColor().equals(PieceColor.black)) {
			piece.setPieceColor(PieceColor.white);
			piece.getCircle().setFill(Param.getPIECE_WHITE_COLOR());
		}else if(piece.getPieceColor().equals(PieceColor.white)) {
			piece.setPieceColor(PieceColor.black);
			piece.getCircle().setFill(Param.getPIECE_BLACK_COLOR());
		}
	}

	//ホバーイベント
	public void mouseEntered(MouseEvent e) {
		if(Reversi.getSelectXGrid() != this.xGrid )Reversi.setSelectXGrid(this.xGrid);
		if(Reversi.getSelectYGrid() != this.yGrid )Reversi.setSelectYGrid(this.yGrid);
	}

	//禁止されていないかつピースが存在しないかチェック
	//通常のチェックのほかにスペシャルイベントでも使用。
	public boolean checkCanPutPiece() {
		return !this.banPutPiece && this.piece == null;
	}

	public int getxGrid() {
		return xGrid;
	}
	public void setxGrid(int xGrid) {
		this.xGrid = xGrid;
	}
	public int getyGrid() {
		return yGrid;
	}
	public void setyGrid(int yGrid) {
		this.yGrid = yGrid;
	}
	public Piece getPiece() {
		return piece;
	}
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	public Rectangle getRectangle() {
		return rectangle;
	}
	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}
	public boolean isCanPutPiece() {
		return canPutPiece;
	}
	public void setCanPutPiece(boolean canPutPiece) {
		this.canPutPiece = canPutPiece;
	}
	public Rectangle getCanPutRectangle() {
		return canPutRectangle;
	}
	public void setCanPutRectangle(Rectangle canPutRectangle) {
		this.canPutRectangle = canPutRectangle;
	}
	public ArrayList<ArrayList<Integer>> getReversePieceList() {
		return reversePieceList;
	}
	public void setReversePieceList(ArrayList<ArrayList<Integer>> reversePieceList) {
		this.reversePieceList = reversePieceList;
	}
	public int getMinXGrid() {
		return minXGrid;
	}
	public void setMinXGrid(int minXGrid) {
		this.minXGrid = minXGrid;
	}
	public int getMaxXGrid() {
		return maxXGrid;
	}
	public void setMaxXGrid(int maxXGrid) {
		this.maxXGrid = maxXGrid;
	}
	public int getMinYGrid() {
		return minYGrid;
	}
	public void setMinYGrid(int minYGrid) {
		this.minYGrid = minYGrid;
	}
	public int getMaxYGrid() {
		return maxYGrid;
	}
	public void setMaxYGrid(int maxYGrid) {
		this.maxYGrid = maxYGrid;
	}
	public boolean isBanPutPiece() {
		return banPutPiece;
	}
	public void setBanPutPiece(boolean banPutPiece) {
		this.banPutPiece = banPutPiece;
	}
}
