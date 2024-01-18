package pr.herobrine.chess;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class ChessApplication {

	public static void main(String[] args) {
//		SpringApplication.run(ChessApplication.class, args);

		//System.out.println("Hello World!");

		ChessBoard chessBoard = new ChessBoard(8, 8, "White_Black");

		ArrayList<String> wFigures = new ArrayList<String>();

		// White Rooks
		wFigures.add("R:7_0");
		wFigures.add("R:7_7");

		// White Knights
		wFigures.add("KN:7_1");
		wFigures.add("KN:7_6");

		// White Bishops
		wFigures.add("B:7_2");
		wFigures.add("B:7_5");

		// White Queens
		wFigures.add("Q:3_3");

		// White King
		wFigures.add("K:7_4");

		// White Pawns
		wFigures.add("P:6_0");
		wFigures.add("P:6_1");
		wFigures.add("P:6_2");
		wFigures.add("P:6_3");
		wFigures.add("P:6_4");
		wFigures.add("P:6_5");
		wFigures.add("P:6_6");
		wFigures.add("P:6_7");

		ArrayList<String> bFigures = new ArrayList<String>();

		// Black Rooks
		bFigures.add("R:0_0");
		bFigures.add("R:0_7");

		// Black Knights
		bFigures.add("KN:0_1");
		bFigures.add("KN:0_6");

		// Black Bishops
		bFigures.add("B:0_2");
		bFigures.add("B:0_5");

		// Black Queens
		bFigures.add("Q:0_3");

		// Black King
		bFigures.add("K:0_4");

		// Black Pawns
		bFigures.add("P:1_0");
		bFigures.add("P:1_1");
		bFigures.add("P:1_2");
		bFigures.add("P:1_3");
		bFigures.add("P:1_4");
		bFigures.add("P:1_5");
		bFigures.add("P:1_6");
		bFigures.add("P:1_7");

		Map<String, FieldSpace> Fields = chessBoard.InitFields();
		Map<String, Piece> Pieces = chessBoard.InitFigures(wFigures, bFigures);

		BoardUI BoardUI = chessBoard.drawBoard();
	}

}
