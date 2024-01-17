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

		wFigures.add("K:0_1");
		wFigures.add("KN:0_2");

		ArrayList<String> bFigures = new ArrayList<String>();

		bFigures.add("K:7_1");
		bFigures.add("KN:7_2");

		Map<String, FieldSpace> Fields = chessBoard.InitFields();
		Map<String, Piece> Pieces = chessBoard.InitFigures(wFigures, bFigures);

		//Pieces.get("White_King_0").move(Fields.get("0_1"), chessBoard, null);
		//Pieces.get("White_King_0").move(Fields.get("0_2"), chessBoard, null);
		//Pieces.get("Black_King_0").move(Fields.get("0_1"), chessBoard, null);

		BoardUI BoardUI = chessBoard.drawBoard();

		//Pieces.get("White_King_0").move(Fields.get("0_1"), chessBoard, BoardUI);
		//Pieces.get("White_King_0").move(Fields.get("0_2"), chessBoard, BoardUI);
		//Pieces.get("Black_King_0").move(Fields.get("0_1"), chessBoard, BoardUI);


		//Pieces.get("White_King_0").move(Fields.get("0_1"), chessBoard, BoardUI);
		//Pieces.get("White_King_0").move(Fields.get("1_1"), chessBoard, BoardUI);
		//Pieces.get("White_King_0").move(Fields.get("2_2"), chessBoard, BoardUI);
		//Pieces.get("White_King_0").move(Fields.get("3_3"), chessBoard, BoardUI);
		//Pieces.get("White_King_0").move(Fields.get("4_4"), chessBoard, BoardUI);
		//Pieces.get("White_King_0").move(Fields.get("0_7"), chessBoard, BoardUI);
		//Pieces.get("White_King_1").move(Fields.get("1_0"), chessBoard, BoardUI);
		//Pieces.get("White_King_1").move(Fields.get("2_1"), chessBoard, BoardUI);
		//Pieces.get("Black_King_0").move(Fields.get("1_1"), chessBoard, BoardUI);

	}

}
