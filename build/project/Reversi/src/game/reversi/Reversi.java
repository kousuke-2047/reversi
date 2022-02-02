package game.reversi;

import java.util.ArrayList;
import java.util.Arrays;

import game.enums.PieceColor;
import game.object.Piece;
import game.object.Score;
import game.object.SpecialPane;
import game.object.Square;
import game.object.TurnPlayer;
import game.param.Param;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


public class Reversi extends Group{
	//xGrid,yGridとする
	private ArrayList<ArrayList<Square>> board;
	private Pane reversiPane;
	private static int selectXGrid;
	private static int selectYGrid;
	private TurnPlayer turnPlayer;
	private Score Score;
	private SpecialPane specialPane;


	public Reversi() {
		this.board = new ArrayList<ArrayList<Square>>();
		this.reversiPane = new Pane();
		this.setReversiPaneConfig();
		this.turnPlayer = new TurnPlayer(
				Param.getPRECEDING_COLOR(),
				Param.getSQUARE_SIZE(),
				Param.getGRID_SIZE(),
				Param.getREVERSI_PANE_PADDING(),
				Param.getTURNPLAYER_PANE_WIDTH(),
				Param.getTURNPLAYER_PANE_HEIGHT(),
				Param.getGRIDPANE_TEXT_PADDING(),
				Param.getTURNPLAYER_LABEL(),
				Param.getNORMAL_FONT_SIZE());
		this.Score = new Score(
				Param.getSCORE_PANE_HEIGHT(),
				Param.getSCORE_PANE_WIDTH(),
				Param.getSQUARE_SIZE(),
				Param.getGRID_SIZE(),
				Param.getREVERSI_PANE_PADDING(),
				Param.getDIVIDE_SIZE(),
				Param.getTURNPLAYER_PANE_HEIGHT(),
				Param.getNORMAL_FONT_SIZE(),
				Param.getBOLD_FONT_SIZE());
		this.specialPane = new SpecialPane(
				Param.getSPECIAL_LIST(),
				Param.getNORMAL_FONT_SIZE(),
				Param.getRANDOM_GRIDPANE_WIDTH(),
				Param.getGRIDPANE_TEXT_PADDING(),
				Param.getBOLD_FONT_SIZE(),
				Param.getRANDOM_TEXTPANE_WIDTH(),
				Param.getSQUARE_SIZE(),
				Param.getGRID_SIZE(),
				Param.getREVERSI_PANE_PADDING());

		//マス目作成処理
		for(int i=0;i<Param.getGRID_SIZE();i++) {
			this.board.add(new ArrayList<Square>());
			for(int j=0;j<Param.getGRID_SIZE();j++) {
				this.board.get(i).add(new Square(
						i,
						j,
						Param.getSQUARE_SIZE(),
						Param.getSQUARE_COLOR(),
						Param.getSQUARE_LINE_COLOR(),
						Param.getCANPUT_SQUARE_COLOR(),
						Param.getCANNOTPUT_OPOCITY()));
			}
		}
		//クリックイベント付与
		for(ArrayList<Square> sList:this.board) {
			for(Square s:sList) {
				s.getCanPutRectangle().setOnMouseClicked(this::mouseClicked);
				this.reversiPane.getChildren().add(s.getRectangle());
				this.reversiPane.getChildren().add(s.getCanPutRectangle());
			}
		}
		getChildren().add(this.Score.getScorePane());
		getChildren().add(this.turnPlayer.getTurnPlayerPane());
		getChildren().add(this.reversiPane);
		getChildren().add(this.specialPane.getSpecialPane());
		this.start();
	}
	private void setReversiPaneConfig() {
		this.reversiPane.setPrefWidth(Param.getSQUARE_SIZE()*Param.getGRID_SIZE());
		this.reversiPane.setPrefHeight(Param.getSQUARE_SIZE()*Param.getGRID_SIZE());
		this.reversiPane.setLayoutX(Param.getREVERSI_PANE_PADDING());
		this.reversiPane.setLayoutY(Param.getREVERSI_PANE_PADDING());
	}


	//ゲーム開始
	public void start() {
		//最初のピース配置処理
		for(Square s:Param.getDEFAULT_SQUARE()) {
			this.putPiece(s.getPiece().getPieceColor(),s.getxGrid(),s.getyGrid());
		}
		this.setCanPutPieceOnBoard();
	}

	//ピース配置処理
	public void putPiece(PieceColor player,int xGrid,int yGrid) {
		//配置処理
		this.board.get(xGrid).get(yGrid).setPiece(new Piece(player,xGrid,yGrid,Param.getSQUARE_SIZE()));
		this.reversiPane.getChildren().add(this.board.get(xGrid).get(yGrid).getPiece().getCircle());

		//裏返し処理
		this.reversePieces(this.board.get(xGrid).get(yGrid).getReversePieceList());
	}

	//ピース裏返し処理
	public void reversePieces(ArrayList<ArrayList<Integer>> pieces) {
		for(ArrayList<Integer> grids : pieces) {
			this.board.get(grids.get(0)).get(grids.get(1)).reversePiece();
		}
	}


	//ターンが変わる度にboardの色リセット
	public void resetBoard() {
		for(ArrayList<Square> a:this.board) {
			for(Square s:a) {
				s.getReversePieceList().clear();
				s.setCanPutPiece(false);
				s.getCanPutRectangle().setOpacity(Param.getCANNOTPUT_OPOCITY());
			}
		}
	}

	//おける箇所のboardの色変え
	public void setCanPutPieceOnBoard() {
		for(ArrayList<Square> a:this.board) {
			for(Square s:a) {
				if(checkPutPiece(s)) {
					s.setCanPutPiece(true);
					//色変え
					s.getCanPutRectangle().setOpacity(Param.getCANPUT_OPOCITY());
				}
			}
		}
	}

	//ピース配置チェック
	private boolean checkPutPiece(Square square) {
		boolean check = false;
		//ピースアリ
		if(square.getPiece() != null) return check;
		if(this.checkFirstPiece(square.getxGrid(), square.getyGrid(), 0,  1)) {
			check = true;
			addReversePieceList(square.getxGrid(), square.getyGrid(), 0,  1,square.getxGrid(), square.getyGrid());
		}
		if(this.checkFirstPiece(square.getxGrid(), square.getyGrid(), 0, -1)) {
			check = true;
			addReversePieceList(square.getxGrid(), square.getyGrid(), 0, -1,square.getxGrid(), square.getyGrid());
		}
		if(this.checkFirstPiece(square.getxGrid(), square.getyGrid(), 1,  0)) {
			check = true;
			addReversePieceList(square.getxGrid(), square.getyGrid(), 1,  0,square.getxGrid(), square.getyGrid());
		}
		if(this.checkFirstPiece(square.getxGrid(), square.getyGrid(),-1,  0)) {
			check = true;
			addReversePieceList(square.getxGrid(), square.getyGrid(),-1,  0,square.getxGrid(), square.getyGrid());
		}
		if(this.checkFirstPiece(square.getxGrid(), square.getyGrid(), 1,  1)) {
			check = true;
			addReversePieceList(square.getxGrid(), square.getyGrid(), 1,  1,square.getxGrid(), square.getyGrid());
		}
		if(this.checkFirstPiece(square.getxGrid(), square.getyGrid(), 1, -1)) {
			check = true;
			addReversePieceList(square.getxGrid(), square.getyGrid(), 1, -1,square.getxGrid(), square.getyGrid());
		}
		if(this.checkFirstPiece(square.getxGrid(), square.getyGrid(),-1,  1)) {
			check = true;
			addReversePieceList(square.getxGrid(), square.getyGrid(),-1,  1,square.getxGrid(), square.getyGrid());
		}
		if(this.checkFirstPiece(square.getxGrid(), square.getyGrid(),-1, -1)) {
			check = true;
			addReversePieceList(square.getxGrid(), square.getyGrid(),-1, -1,square.getxGrid(), square.getyGrid());
		}
		return check;
	}



	//ピース配置の最初のチェック
	private boolean checkFirstPiece(int xGrid,int yGrid,int xAdd,int yAdd) {
		//枠外
		if(this.checkOutOfBoard((xGrid+xAdd), (yGrid+yAdd))) {
			return false;
		//隣のピースが無し
		}else if(this.isNextPieceNull((xGrid+xAdd),(yGrid+yAdd))) {
			return false;
		//隣のピースが自分
		}else if(this.isNextPieceTurnColor((xGrid+xAdd),(yGrid+yAdd))) {
			return false;
		}
		//隣のピースが敵
		return this.checkOtherPiece((xGrid+xAdd),(yGrid+yAdd),xAdd,yAdd);
	}
	//ピース配置の2番目以降のチェック
	private boolean checkOtherPiece(int xGrid,int yGrid,int xAdd,int yAdd) {
		if(this.checkOutOfBoard((xGrid+xAdd), (yGrid+yAdd))) {
			return false;
		//隣のピース無し
		}else if(this.isNextPieceNull((xGrid+xAdd),(yGrid+yAdd))) {
			return false;
		//隣のピースが敵
		}else if(!this.isNextPieceTurnColor((xGrid+xAdd),(yGrid+yAdd))) {
			return this.checkOtherPiece((xGrid+xAdd), (yGrid+yAdd), xAdd, yAdd);
		//隣のピースが味方
		}else{
			return true;
		}
	}

	//リバースリストを作成
	private void addReversePieceList(int xGrid,int yGrid,int xAdd,int yAdd,int orgXGrid,int orgYGrid) {
		//盤面外
		if(this.checkOutOfBoard((xGrid+xAdd), (yGrid+yAdd)) ) {

		//隣のピース無し
		}else if(this.isNextPieceNull((xGrid+xAdd), (yGrid+yAdd))) {

		//隣のピースが敵
		}else if(!this.isNextPieceTurnColor((xGrid+xAdd),(yGrid+yAdd))) {
			//リバースリストに追加
			this.board.get(orgXGrid).get(orgYGrid).getReversePieceList().add(new ArrayList<Integer>(Arrays.asList((xGrid+xAdd),(yGrid+yAdd))));
			this.addReversePieceList(xGrid+xAdd, yGrid+yAdd, xAdd, yAdd, orgXGrid, orgYGrid);
		}
	}

	//盤面外かチェック
	private boolean checkOutOfBoard(int xGridAdd,int yGridAdd) {
		return (xGridAdd<0) || (yGridAdd<0) || (xGridAdd>=Param.getGRID_SIZE()) || (yGridAdd>=Param.getGRID_SIZE());
	}

	//次のピース無しチェック
	private boolean isNextPieceNull(int xGrid,int yGrid) {
		return this.board.get(xGrid).get(yGrid).getPiece() == null;
	}

	//隣のピースが味方
	private boolean isNextPieceTurnColor(int xGrid,int yGrid) {
		return this.board.get(xGrid).get(yGrid).getPiece().getPieceColor().equals(this.getTurnPlayer().getTurnPlayer());
	}



	//クリックイベント
	public void mouseClicked(MouseEvent e) {
		if(this.board.get(selectXGrid).get(selectYGrid).isCanPutPiece()) {
			this.putPiece(this.getTurnPlayer().getTurnPlayer(),selectXGrid, selectYGrid);
			this.resetBoard();
			this.turnPlayer.changePlayer();
			this.setCanPutPieceOnBoard();
			//置き場所ない場合
			if(!this.isCanPutPieceExist()) this.skip();
		}
		this.testPrint();
	}

	//test用
	public void testPrint() {
		System.out.println("test");
		this.specialPane.rouletteSpecial(Param.getSPECIAL_LIST());
		/*
		for(ArrayList<Square> list:this.board) {
			for(Square s:list) {
				if(s.isCanPutPiece())System.out.println("x:" + s.getxGrid() + " y:" + s.getyGrid() + " flg:"+ s.isCanPutPiece() + " list:" + s.getReversePieceList());
			}
		}
		*/

	}

	//skip処理
	private void skip() {
		this.resetBoard();
		this.turnPlayer.changePlayer();
		this.setCanPutPieceOnBoard();
		//ゲーム終了
		if(!this.isCanPutPieceExist()) {
			this.reversiEnd();
		}
	}

	//skip判定
	private boolean isCanPutPieceExist() {
		boolean check = false;
		for(ArrayList<Square> list:this.board) {
			for(Square s:list) {
				if(s.isCanPutPiece()) check = true;
			}
		}
		return check;
	}

	//ゲーム終了処理
	private void reversiEnd() {
		//スコア表示
		int whitePieces = 0;
		int blackPieces = 0;
		for(ArrayList<Square> list:this.board) {
			for(Square s:list) {
				if(s.getPiece() == null) continue;
				else if(s.getPiece().getPieceColor().equals(PieceColor.white)) whitePieces++;
				else if(s.getPiece().getPieceColor().equals(PieceColor.black)) blackPieces++;
			}
		}
		this.Score.setScore(whitePieces, blackPieces,Param.getWINNER_LABEL());
	}



	public TurnPlayer getTurnPlayer() {
		return this.turnPlayer;
	}
	public void setTurnPlayer(TurnPlayer turnP) {
		this.turnPlayer = turnP;
	}
	public static int getSelectXGrid() {
		return selectXGrid;
	}
	public static void setSelectXGrid(int selectedXGrid) {
		selectXGrid = selectedXGrid;
	}
	public static int getSelectYGrid() {
		return selectYGrid;
	}
	public static void setSelectYGrid(int selectedYGrid) {
		selectYGrid = selectedYGrid;
	}
	public Pane getReversiPane() {
		return reversiPane;
	}
	public void setReversiPane(Pane reversiPane) {
		this.reversiPane = reversiPane;
	}
	public ArrayList<ArrayList<Square>> getBoard() {
		return board;
	}
	public void setBoard(ArrayList<ArrayList<Square>> board) {
		this.board = board;
	}
	public Score getScore() {
		return Score;
	}
	public void setScore(Score score) {
		Score = score;
	}
	public SpecialPane getSpecialPane() {
		return specialPane;
	}
	public void setSpecialPane(SpecialPane specialPane) {
		this.specialPane = specialPane;
	}
}
