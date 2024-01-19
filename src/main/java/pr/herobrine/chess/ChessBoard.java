package pr.herobrine.chess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pr.herobrine.chess.Pieces.Bishop;
import pr.herobrine.chess.Pieces.King;
import pr.herobrine.chess.Pieces.Knight;
import pr.herobrine.chess.Pieces.Pawn;
import pr.herobrine.chess.Pieces.Queen;
import pr.herobrine.chess.Pieces.Rook;

public class ChessBoard {
    private boolean TestMode = false;
    private static int SIZEX = 8;
    private static int SIZEY = 8;
    private static String currentTurn = "White";
    private static int TurnNumber = 0;
    private static String BoardName = "White_Black";
    private int whiteFiguresLeft = 0;
    private int whiteKingsLeft = 0;
    private int blackFiguresLeft = 0;
    private int blackKingsLeft = 0;
    private Map<String, Piece> figures = new HashMap<>();
    private Map<String, FieldSpace> fields = new HashMap<>();

    public ChessBoard(int SIZEX, int SIZEY, String BoardName) {
        this.SIZEX = SIZEX;
        this.SIZEY = SIZEY;
        this.BoardName = BoardName;
    }

    public BoardUI drawBoard() {
        BoardUI board = new BoardUI(SIZEX, SIZEY, fields, this);
        board.setVisible(true);
        System.out.println("Opened Board");
        return board;
    }

    public Map<String, Piece> InitFigures(ArrayList<String> whiteFigures, ArrayList<String> blackFigures) {
        Map<String, Piece> Figures = new HashMap<>();
        for (int i = 0; i < whiteFigures.size(); i++) {
            if (splitString(whiteFigures.get(i))[0].equals("K")) {
                int posX = Integer.parseInt(splitString(whiteFigures.get(i))[1]);
                int posY = Integer.parseInt(splitString(whiteFigures.get(i))[2]);
                King wKing = new King(posX, posY, "White_King_"+i, true, this, fields.get(posX+"_"+posY));
                Figures.put(wKing.getName(), wKing);
                whiteKingsLeft++;
            }
            else if (splitString(whiteFigures.get(i))[0].equals("KN")) {
                int posX = Integer.parseInt(splitString(whiteFigures.get(i))[1]);
                int posY = Integer.parseInt(splitString(whiteFigures.get(i))[2]);
                Knight wKnight = new Knight(posX, posY, "White_Knight_"+i, true, this, fields.get(posX+"_"+posY));
                Figures.put(wKnight.getName(), wKnight);
            }
            else if (splitString(whiteFigures.get(i))[0].equals("P")) {
                int posX = Integer.parseInt(splitString(whiteFigures.get(i))[1]);
                int posY = Integer.parseInt(splitString(whiteFigures.get(i))[2]);
                Pawn wPawn = new Pawn(posX, posY, "White_Pawn_"+i, true, this, fields.get(posX+"_"+posY));
                Figures.put(wPawn.getName(), wPawn);
            }
            else if (splitString(whiteFigures.get(i))[0].equals("Q")) {
                int posX = Integer.parseInt(splitString(whiteFigures.get(i))[1]);
                int posY = Integer.parseInt(splitString(whiteFigures.get(i))[2]);
                Queen wQueen = new Queen(posX, posY, "White_Queen_"+i, true, this, fields.get(posX+"_"+posY));
                Figures.put(wQueen.getName(), wQueen);
            }
            else if (splitString(whiteFigures.get(i))[0].equals("R")) {
                int posX = Integer.parseInt(splitString(whiteFigures.get(i))[1]);
                int posY = Integer.parseInt(splitString(whiteFigures.get(i))[2]);
                Rook wRook = new Rook(posX, posY, "White_Rook_"+i, true, this, fields.get(posX+"_"+posY));
                Figures.put(wRook.getName(), wRook);
            }
            else if (splitString(whiteFigures.get(i))[0].equals("B")) {
                int posX = Integer.parseInt(splitString(whiteFigures.get(i))[1]);
                int posY = Integer.parseInt(splitString(whiteFigures.get(i))[2]);
                Bishop wBishop = new Bishop(posX, posY, "White_Bishop_"+i, true, this, fields.get(posX+"_"+posY));
                Figures.put(wBishop.getName(), wBishop);
            }
        }
        whiteFiguresLeft = whiteFigures.size();
        for (int i = 0; i < blackFigures.size(); i++) {
            if (splitString(blackFigures.get(i))[0].equals("K")) {
                int posX = Integer.parseInt(splitString(blackFigures.get(i))[1]);
                int posY = Integer.parseInt(splitString(blackFigures.get(i))[2]);
                King bKing = new King(posX, posY, "Black_King_"+i, false, this, fields.get(posX+"_"+posY));
                Figures.put(bKing.getName(), bKing);
                blackKingsLeft++;
            }
            else if (splitString(blackFigures.get(i))[0].equals("KN")) {
                int posX = Integer.parseInt(splitString(blackFigures.get(i))[1]);
                int posY = Integer.parseInt(splitString(blackFigures.get(i))[2]);
                Knight bKnight = new Knight(posX, posY, "Black_Knight_"+i, false, this, fields.get(posX+"_"+posY));
                Figures.put(bKnight.getName(), bKnight);
            }
            else if (splitString(blackFigures.get(i))[0].equals("P")) {
                int posX = Integer.parseInt(splitString(blackFigures.get(i))[1]);
                int posY = Integer.parseInt(splitString(blackFigures.get(i))[2]);
                Pawn bPawn = new Pawn(posX, posY, "Black_Pawn_"+i, false, this, fields.get(posX+"_"+posY));
                Figures.put(bPawn.getName(), bPawn);
            }
            else if (splitString(blackFigures.get(i))[0].equals("Q")) {
                int posX = Integer.parseInt(splitString(blackFigures.get(i))[1]);
                int posY = Integer.parseInt(splitString(blackFigures.get(i))[2]);
                Queen bQueen = new Queen(posX, posY, "Black_Queen_"+i, false, this, fields.get(posX+"_"+posY));
                Figures.put(bQueen.getName(), bQueen);
            }
            else if (splitString(blackFigures.get(i))[0].equals("R")) {
                int posX = Integer.parseInt(splitString(blackFigures.get(i))[1]);
                int posY = Integer.parseInt(splitString(blackFigures.get(i))[2]);
                Rook bRook = new Rook(posX, posY, "Black_Rook_"+i, false, this, fields.get(posX+"_"+posY));
                Figures.put(bRook.getName(), bRook);
            }
            else if (splitString(blackFigures.get(i))[0].equals("B")) {
                int posX = Integer.parseInt(splitString(blackFigures.get(i))[1]);
                int posY = Integer.parseInt(splitString(blackFigures.get(i))[2]);
                Bishop bBishop = new Bishop(posX, posY, "Black_Bishop_"+i, false, this, fields.get(posX+"_"+posY));
                Figures.put(bBishop.getName(), bBishop);
            }
        }
        blackFiguresLeft = blackFigures.size();
        this.figures = Figures;
        return Figures;
    }

    public Map<String, FieldSpace> InitFields() {
        Map<String, FieldSpace> Fields = new HashMap<>();
        for (int i = 0; i < SIZEX; i++) {
            for (int i2 = 0; i2 < SIZEY; i2++) {
                FieldSpace space = new FieldSpace(i, i2, i+"_"+i2, this);
                if (i%2 == 0) {
                    if (i2%2 == 0) {
                        space.setIsWhiteField(true);
                    } else {
                        space.setIsWhiteField(false); 
                    }
                } else {
                    if (i2%2 != 0) {
                        space.setIsWhiteField(true);
                    } else {
                        space.setIsWhiteField(false); 
                    }
                }
                Fields.put(space.getName(), space);
            }
        }
        this.fields = Fields;
        return Fields;
    }

    public int getSizeX() {
        return SIZEX;
    }
    public int getSizeY() {
        return SIZEY;
    }

    public int getTurnNumber() {
        return TurnNumber;
    }

    public String getCurrentTurn() {
        return currentTurn;
    }

    public void increaseTurnNumber() {
        TurnNumber++;
    }

    public void setCurrentTurn(String currentTurn) {
        this.currentTurn = currentTurn;
    }

    public String getBoardName() {
        return BoardName;
    }

    public void switchTestMode() {
        this.TestMode =!TestMode;
    }

    public void decreaseWhiteFiguresLeft() {
        whiteFiguresLeft--;
    }

    public void decreaseBlackFiguresLeft() {
        blackFiguresLeft--;
    }

    public void decreaseWhiteKingsLeft() {
        whiteKingsLeft--;
    }

    public void decreaseBlackKingsLeft() {
        blackKingsLeft--;
    }

    public int getWhiteKingsLeft() {
        return whiteKingsLeft;
    }

    public int getBlackKingsLeft() {
        return blackKingsLeft;
    }

    public int getWhiteFiguresLeft() {
        return whiteFiguresLeft;
    }

    public int getBlackFiguresLeft() {
        return blackFiguresLeft;
    }

    private static String[] splitString(String input) {
        String[] firstSplit = input.split(":");
        String[] secondSplit = firstSplit[1].split("_");

        return new String[]{firstSplit[0], secondSplit[0], secondSplit[1]};
    }

    public void replaceFigureInMap(Piece oldFigure, Piece newFigure) {
        figures.remove(oldFigure.getName());
        figures.put(newFigure.getName(), newFigure);
    }
}
