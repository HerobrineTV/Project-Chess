package pr.herobrine.chess;

import java.util.Map;

import javax.swing.ImageIcon;

public class Piece implements java.io.Serializable{
    // Implement Class Piece here

    protected int locX;
    protected int locY;
    protected String name;
    protected String shortname;
    protected boolean isWhite = false;
    protected boolean isKing = false;
    protected boolean isQueen = false;
    protected boolean isRook = false;
    protected boolean isBishop = false;
    protected boolean isKnight = false;
    protected boolean isPawn = false;
    protected boolean isOut = false;
    protected ChessBoard chessBoard;
    protected FieldSpace currentField;
    protected ImageIcon ImageIcon;
    protected Map<String, FieldSpace> LastFieldsMap;

    public boolean move(FieldSpace Field, ChessBoard chessBoard, BoardUI BoardUI) {
        return false;
    }

    public String[] getMoves(Map<String, FieldSpace> fields, ChessBoard chessBoard) {
        return new String[0];
    }

    public String getName() {
        return this.name;
    }
    
    public Boolean isWhite() {
        return this.isWhite;
    }

    public void ThrowOut() {
        this.isOut = true;
        if (this.isWhite) {
            if (this.isKing) {chessBoard.decreaseWhiteKingsLeft();}
            chessBoard.decreaseWhiteFiguresLeft();
        } else {
            if (this.isKing) {chessBoard.decreaseBlackKingsLeft();}
            chessBoard.decreaseBlackFiguresLeft();
        }
    }

    public String getShortname() {
        return this.shortname;
    }

    public ImageIcon getImageIcon() {
        return this.ImageIcon;
    }

    public boolean isKing() {
        return this.isKing;
    }

    public boolean isQueen() {
        return this.isQueen;
    }

    public boolean isRook() {
        return this.isRook;
    }
    
    public boolean isBishop() {
        return this.isBishop;
    }

    public boolean isKnight() {
        return this.isKnight;
    }

    public boolean isPawn() {
        return this.isPawn;
    }

    protected boolean isMovePossible(int x, int y, Map<String, FieldSpace> fields) {
        return !fields.get(x + "_" + y).isFieldBlocked() || fields.get(x + "_" + y).getCurrentPieceOnField().isWhite() != this.isWhite();
    }

    public boolean isOnTurn() {
        if (chessBoard.getCurrentTurn() == "White" && this.isWhite) {
            return true;
        } else if (chessBoard.getCurrentTurn() == "Black" &&!this.isWhite) {
            return true;
        } else {
            return false;
        }
    }
    
}
