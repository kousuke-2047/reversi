package game.reversi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import game.enums.PieceColor;
import game.enums.Special;
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
	private int specialSpan;
	private int specialSpanCount = 0;


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
		this.specialSpan = Param.getSPECIAL_SPAN();

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
						Param.getCANNOTPUT_OPOCITY(),
						0,Param.getGRID_SIZE(),0,Param.getGRID_SIZE()));
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
		//ピースアリまたはbanされている
		if(!square.checkCanPutPiece()) return check;
		if(this.checkFirstPiece(square.getxGrid(), square.getyGrid(), 0,  1,square.getMinXGrid(),square.getMaxXGrid(),square.getMinYGrid(),square.getMaxYGrid())) {
			check = true;
			addReversePieceList(square.getxGrid(), square.getyGrid(), 0,  1,square.getxGrid(), square.getyGrid(),square.getMinXGrid(),square.getMaxXGrid(),square.getMinYGrid(),square.getMaxYGrid());
		}
		if(this.checkFirstPiece(square.getxGrid(), square.getyGrid(), 0, -1,square.getMinXGrid(),square.getMaxXGrid(),square.getMinYGrid(),square.getMaxYGrid())) {
			check = true;
			addReversePieceList(square.getxGrid(), square.getyGrid(), 0, -1,square.getxGrid(), square.getyGrid(),square.getMinXGrid(),square.getMaxXGrid(),square.getMinYGrid(),square.getMaxYGrid());
		}
		if(this.checkFirstPiece(square.getxGrid(), square.getyGrid(), 1,  0,square.getMinXGrid(),square.getMaxXGrid(),square.getMinYGrid(),square.getMaxYGrid())) {
			check = true;
			addReversePieceList(square.getxGrid(), square.getyGrid(), 1,  0,square.getxGrid(), square.getyGrid(),square.getMinXGrid(),square.getMaxXGrid(),square.getMinYGrid(),square.getMaxYGrid());
		}
		if(this.checkFirstPiece(square.getxGrid(), square.getyGrid(),-1,  0,square.getMinXGrid(),square.getMaxXGrid(),square.getMinYGrid(),square.getMaxYGrid())) {
			check = true;
			addReversePieceList(square.getxGrid(), square.getyGrid(),-1,  0,square.getxGrid(), square.getyGrid(),square.getMinXGrid(),square.getMaxXGrid(),square.getMinYGrid(),square.getMaxYGrid());
		}
		if(this.checkFirstPiece(square.getxGrid(), square.getyGrid(), 1,  1,square.getMinXGrid(),square.getMaxXGrid(),square.getMinYGrid(),square.getMaxYGrid())) {
			check = true;
			addReversePieceList(square.getxGrid(), square.getyGrid(), 1,  1,square.getxGrid(), square.getyGrid(),square.getMinXGrid(),square.getMaxXGrid(),square.getMinYGrid(),square.getMaxYGrid());
		}
		if(this.checkFirstPiece(square.getxGrid(), square.getyGrid(), 1, -1,square.getMinXGrid(),square.getMaxXGrid(),square.getMinYGrid(),square.getMaxYGrid())) {
			check = true;
			addReversePieceList(square.getxGrid(), square.getyGrid(), 1, -1,square.getxGrid(), square.getyGrid(),square.getMinXGrid(),square.getMaxXGrid(),square.getMinYGrid(),square.getMaxYGrid());
		}
		if(this.checkFirstPiece(square.getxGrid(), square.getyGrid(),-1,  1,square.getMinXGrid(),square.getMaxXGrid(),square.getMinYGrid(),square.getMaxYGrid())) {
			check = true;
			addReversePieceList(square.getxGrid(), square.getyGrid(),-1,  1,square.getxGrid(), square.getyGrid(),square.getMinXGrid(),square.getMaxXGrid(),square.getMinYGrid(),square.getMaxYGrid());
		}
		if(this.checkFirstPiece(square.getxGrid(), square.getyGrid(),-1, -1,square.getMinXGrid(),square.getMaxXGrid(),square.getMinYGrid(),square.getMaxYGrid())) {
			check = true;
			addReversePieceList(square.getxGrid(), square.getyGrid(),-1, -1,square.getxGrid(), square.getyGrid(),square.getMinXGrid(),square.getMaxXGrid(),square.getMinYGrid(),square.getMaxYGrid());
		}
		return check;
	}



	//ピース配置の最初のチェック
	private boolean checkFirstPiece(int xGrid,int yGrid,int xAdd,int yAdd,
			int minXGrid,int maxXGrid,int minYGrid,int maxYGrid) {
		//枠外
		if(this.checkOutOfBoard((xGrid+xAdd), (yGrid+yAdd),minXGrid,maxXGrid,minYGrid,maxYGrid)) {
			return false;
		//隣のピースが無し
		}else if(this.isNextPieceNull((xGrid+xAdd),(yGrid+yAdd))) {
			return false;
		//隣のピースが自分
		}else if(this.isNextPieceTurnColor((xGrid+xAdd),(yGrid+yAdd))) {
			return false;
		}
		//隣のピースが敵
		return this.checkOtherPiece((xGrid+xAdd),(yGrid+yAdd),xAdd,yAdd,minXGrid,maxXGrid,minYGrid,maxYGrid);
	}
	//ピース配置の2番目以降のチェック
	private boolean checkOtherPiece(int xGrid,int yGrid,int xAdd,int yAdd,
			int minXGrid,int maxXGrid,int minYGrid,int maxYGrid) {
		if(this.checkOutOfBoard((xGrid+xAdd), (yGrid+yAdd),minXGrid,maxXGrid,minYGrid,maxYGrid)) {
			return false;
		//隣のピース無し
		}else if(this.isNextPieceNull((xGrid+xAdd),(yGrid+yAdd))) {
			return false;
		//隣のピースが敵
		}else if(!this.isNextPieceTurnColor((xGrid+xAdd),(yGrid+yAdd))) {
			return this.checkOtherPiece((xGrid+xAdd), (yGrid+yAdd), xAdd, yAdd,minXGrid,maxXGrid,minYGrid,maxYGrid);
		//隣のピースが味方
		}else{
			return true;
		}
	}

	//リバースリストを作成
	private void addReversePieceList(int xGrid,int yGrid,int xAdd,int yAdd,int orgXGrid,int orgYGrid,
			int minXGrid,int maxXGrid,int minYGrid,int maxYGrid) {
		//盤面外
		if(this.checkOutOfBoard((xGrid+xAdd), (yGrid+yAdd),minXGrid,maxXGrid,minYGrid,maxYGrid) ) {

		//隣のピース無し
		}else if(this.isNextPieceNull((xGrid+xAdd), (yGrid+yAdd))) {

		//隣のピースが敵
		}else if(!this.isNextPieceTurnColor((xGrid+xAdd),(yGrid+yAdd))) {
			//リバースリストに追加
			this.board.get(orgXGrid).get(orgYGrid).getReversePieceList().add(new ArrayList<Integer>(Arrays.asList((xGrid+xAdd),(yGrid+yAdd))));
			this.addReversePieceList(xGrid+xAdd, yGrid+yAdd, xAdd, yAdd, orgXGrid, orgYGrid,minXGrid,maxXGrid,minYGrid,maxYGrid);
		}
	}

	//盤面外かチェック
	//★最大値と最小値に変える
	private boolean checkOutOfBoard(int xGridAdd,int yGridAdd,int minXGrid,int maxXGrid,int minYGrid,int maxYGrid) {
		return (xGridAdd<minXGrid) || (yGridAdd<minYGrid) || (xGridAdd>=maxXGrid) || (yGridAdd>=maxYGrid);
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

			//ランダムイベント記載
			if(this.specialSpanCount >= this.specialSpan) {
				//ランダムイベント実行
				this.specialSpanCount = 0;
			}

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
		//this.specialPane.rouletteSpecial(Param.getSPECIAL_LIST(),Param.getDEFAULT_SPECIAL());
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

	//ランダムイベント処理
	private void specialEvent(Special special) {
		if(special == Special.Miss) this.specialEventMiss();
		else if(special == Special.Random_ban) this.specialEventRandomBan();
		else if(special == Special.Random_change) this.specialEventRandomChange();
		else if(special == Special.Random_break) this.specialEventRandomBreak();
		else if(special == Special.Meteor) this.specialEventMeteor();
		else if(special == Special.Horizontal_divide) this.specialEventHorizontalDivide();
		else if(special == Special.Vertical_divide) this.specialEventVerticalDivide();
		else if(special == Special.Cross_divide) this.specialEventCrossDivide();
	}
	private void specialEventMiss() {

	}
	private void specialEventRandomBan() {
		Square target = this.getRandomSquare(null);
		if(target.checkCanPutPiece()) {
			target.setBanPutPiece(true);
		}else {
			this.specialEventRandomBan();
		}
	}
	private void specialEventRandomChange() {
		this.getRandomSquare(PieceColor.white).reversePiece();
		this.getRandomSquare(PieceColor.black).reversePiece();
	}
	private void specialEventRandomBreak() {
		Random r = new Random();
		int randomInt = r.nextInt(2);
		if(randomInt == 0) this.getRandomSquare(PieceColor.white).setPiece(null);
		else if(randomInt == 1) this.getRandomSquare(PieceColor.black).setPiece(null);

	}
	private void specialEventMeteor() {
		for(int i=0;i<4;i++) {
			this.specialEventRandomBreak();
		}
	}
	//水平割り
	private void specialEventHorizontalDivide() {
		Random r = new Random();
		//1-7の間
		int divideTarget = r.nextInt(Param.getGRID_SIZE()-1) + 1;
		//一つ前の列を対象に探索限界値を設定する。（最大値）
		for(Square s:this.board.get(divideTarget-1)) {
			s.setMaxYGrid(s.getyGrid());
		}
		//対象列に探索限界値を設定する。（最小値）
		for(Square s:this.board.get(divideTarget)) {
			s.setMinYGrid(s.getyGrid());
		}

	}
	//垂直割り
	//水平割りのY軸バージョン
	private void specialEventVerticalDivide() {
		Random r = new Random();
		int divideTarget = r.nextInt(Param.getGRID_SIZE()-1) + 1;
		for(ArrayList<Square> sList:this.board) {
			sList.get(divideTarget-1).setMaxXGrid(sList.get(divideTarget-1).getxGrid());
		}
		for(ArrayList<Square> sList:this.board) {
			sList.get(divideTarget).setMinXGrid(sList.get(divideTarget).getxGrid());
		}
	}
	private void specialEventCrossDivide() {
		this.specialEventHorizontalDivide();
		this.specialEventVerticalDivide();
	}



	//ランダムマス取得(ピースカラー指定)
	//nullの場合は無いやつを取得。
	private Square getRandomSquare(PieceColor targetPiece) {
		Random r =new Random();
		int randomXGrid = r.nextInt(this.board.size());
		int randomYGrid = r.nextInt(this.board.size());
		//nullチェック
		if( this.board.get(randomXGrid).get(randomYGrid).getPiece() == null) {
			if(targetPiece == null) return this.board.get(randomXGrid).get(randomYGrid);
			else return getRandomSquare(targetPiece);
		}
		if( this.board.get(randomXGrid).get(randomYGrid).getPiece().getPieceColor() == targetPiece ) {
			return this.board.get(randomXGrid).get(randomYGrid);
		}else {
			return getRandomSquare(targetPiece);
		}
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
