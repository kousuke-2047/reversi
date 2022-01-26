package game.param;

import java.util.ArrayList;
import java.util.Arrays;

import game.enums.PieceColor;
import game.enums.Special;
import game.object.SpecialAttribute;
import game.object.Square;
import javafx.scene.paint.Color;

public class Param {
	//先行
	private static PieceColor PRECEDING_COLOR = PieceColor.black;
	//マス目の大きさ
	private static int SQUARE_SIZE = 50;
	//マス数
	private static int GRID_SIZE = 8;
	//マス目背景色
	private static Color SQUARE_COLOR = Color.GREEN;
	//マス目グリッド線色
	private static Color SQUARE_LINE_COLOR = Color.WHITE;
	//配置可能時の色
	private static Color CANPUT_SQUARE_COLOR = Color.WHITE;
	//配置可能時のopocity値
	private static double CANPUT_OPOCITY = 0.5;
	//配置不可時のopocity値
	private static double CANNOTPUT_OPOCITY = 0;
	//初期配置ピース
	private static ArrayList<Square> DEFAULT_SQUARE = new ArrayList<Square>(Arrays.asList(
			new Square(3,3,PieceColor.white,Param.getSQUARE_SIZE(),Param.getSQUARE_COLOR(),Param.getSQUARE_LINE_COLOR(),Param.getCANPUT_SQUARE_COLOR(),Param.getCANNOTPUT_OPOCITY()),
			new Square(3,4,PieceColor.black,Param.getSQUARE_SIZE(),Param.getSQUARE_COLOR(),Param.getSQUARE_LINE_COLOR(),Param.getCANPUT_SQUARE_COLOR(),Param.getCANNOTPUT_OPOCITY()),
			new Square(4,3,PieceColor.black,Param.getSQUARE_SIZE(),Param.getSQUARE_COLOR(),Param.getSQUARE_LINE_COLOR(),Param.getCANPUT_SQUARE_COLOR(),Param.getCANNOTPUT_OPOCITY()),
			new Square(4,4,PieceColor.white,Param.getSQUARE_SIZE(),Param.getSQUARE_COLOR(),Param.getSQUARE_LINE_COLOR(),Param.getCANPUT_SQUARE_COLOR(),Param.getCANNOTPUT_OPOCITY())));
	//白ピース色
	private static Color PIECE_WHITE_COLOR = Color.WHITE;
	//黒ピース色
	private static Color PIECE_BLACK_COLOR = Color.BLACK;
	//デフォルトウィンドウサイズ
	private static double ROOT_PANE_SIZE = 700;
	//パネル調整用
	private static double REVERSI_PANE_PADDING = 50;
	//分割サイズ
	private static double DIVIDE_SIZE = 50;
	//scoreパネルサイズ
	private static double SCORE_PANE_HEIGHT = 100;
	private static double SCORE_PANE_WIDTH = 100;
	//スコア表示テキスト
	private static String WINNER_LABEL = "勝者：";
	private static String DRAW_LABEL = "引き分け";
	//turnPlayerパネルサイズ
	private static double TURNPLAYER_PANE_HEIGHT = 100;
	private static double TURNPLAYER_PANE_WIDTH = 100;
	//turnPlayerテキスト高さ
	private static double TURNPLAYER_TEXTFIELD_HEIGHT = 25;
	//turnPlayerパネルラベルテキスト
	private static String TURNPLAYER_LABEL = "ターン";

	//スペシャル
	private static ArrayList<SpecialAttribute> SPECIAL_LIST = new ArrayList<SpecialAttribute>(Arrays.asList(
			new SpecialAttribute(1,Special.Miss,50,false,false),
			new SpecialAttribute(2,Special.Random_ban,65,false,false),
			new SpecialAttribute(3,Special.Random_change,75,false,false),
			new SpecialAttribute(4,Special.Random_break,85,false,false),
			new SpecialAttribute(5,Special.Meteor,88,false,false),
			new SpecialAttribute(6,Special.Horizontal_divide,93,true,false),
			new SpecialAttribute(7,Special.Vertical_divide,98,false,true),
			new SpecialAttribute(8,Special.Cross_divide,100,true,true)
			));
	//ランダム表のパネルサイズ
	private static double RANDOM_GRIDPANE_WIDTH = 100;
	//ランダムテキストのパネルサイズ
	private static double RANDOM_TEXTPANE_WIDTH = 100;
	//gridPaneの文字パディング
	private static double GRIDPANE_TEXT_PADDING = 5;
	//通常textフォントサイズ
	private static double NORMAL_FONT_SIZE = 15;
	//強調textフォントサイズ
	private static double BOLD_FONT_SIZE = 20;

	public static PieceColor getPRECEDING_COLOR() {
		return PRECEDING_COLOR;
	}
	public static int getSQUARE_SIZE() {
		return SQUARE_SIZE;
	}
	public static int getGRID_SIZE() {
		return GRID_SIZE;
	}
	public static Color getSQUARE_COLOR() {
		return SQUARE_COLOR;
	}
	public static Color getSQUARE_LINE_COLOR() {
		return SQUARE_LINE_COLOR;
	}
	public static Color getCANPUT_SQUARE_COLOR() {
		return CANPUT_SQUARE_COLOR;
	}
	public static double getCANPUT_OPOCITY() {
		return CANPUT_OPOCITY;
	}
	public static double getCANNOTPUT_OPOCITY() {
		return CANNOTPUT_OPOCITY;
	}
	public static ArrayList<Square> getDEFAULT_SQUARE() {
		return DEFAULT_SQUARE;
	}
	public static Color getPIECE_WHITE_COLOR() {
		return PIECE_WHITE_COLOR;
	}
	public static Color getPIECE_BLACK_COLOR() {
		return PIECE_BLACK_COLOR;
	}
	public static double getROOT_PANE_SIZE() {
		return ROOT_PANE_SIZE;
	}
	public static double getSCORE_PANE_HEIGHT() {
		return SCORE_PANE_HEIGHT;
	}
	public static double getSCORE_PANE_WIDTH() {
		return SCORE_PANE_WIDTH;
	}
	public static double getTURNPLAYER_PANE_HEIGHT() {
		return TURNPLAYER_PANE_HEIGHT;
	}
	public static double getTURNPLAYER_PANE_WIDTH() {
		return TURNPLAYER_PANE_WIDTH;
	}
	public static String getTURNPLAYER_LABEL() {
		return TURNPLAYER_LABEL;
	}
	public static double getTURNPLAYER_TEXTFIELD_HEIGHT() {
		return TURNPLAYER_TEXTFIELD_HEIGHT;
	}
	public static String getWINNER_LABEL() {
		return WINNER_LABEL;
	}
	public static String getDRAW_LABEL() {
		return DRAW_LABEL;
	}
	public static double getREVERSI_PANE_PADDING() {
		return REVERSI_PANE_PADDING;
	}
	public static double getDIVIDE_SIZE() {
		return DIVIDE_SIZE;
	}
	public static ArrayList<SpecialAttribute> getSPECIAL_LIST() {
		return SPECIAL_LIST;
	}
	public static double getRANDOM_GRIDPANE_WIDTH() {
		return RANDOM_GRIDPANE_WIDTH;
	}
	public static double getRANDOM_TEXTPANE_WIDTH() {
		return RANDOM_TEXTPANE_WIDTH;
	}
	public static double getGRIDPANE_TEXT_PADDING() {
		return GRIDPANE_TEXT_PADDING;
	}
	public static double getNORMAL_FONT_SIZE() {
		return NORMAL_FONT_SIZE;
	}
	public static double getBOLD_FONT_SIZE() {
		return BOLD_FONT_SIZE;
	}

}
