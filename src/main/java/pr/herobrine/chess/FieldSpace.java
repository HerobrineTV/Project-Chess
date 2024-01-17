package pr.herobrine.chess;

public class FieldSpace {
    private Piece currentPieceOnField = null;
    private int locX;
    private int locY;
    private ChessBoard chessBoard;
    private String name;
    private boolean isWhiteField;


    public FieldSpace(int i, int i2, String name, ChessBoard chessBoard2) {
        this.locX = i;
        this.locY = i2;
        this.chessBoard = chessBoard2;
        this.name = name;
    }

    public void setCurrentPieceOnField(Piece currentPieceOnField) {
        this.currentPieceOnField = currentPieceOnField;
    }

    public boolean isFieldBlocked() {
        return currentPieceOnField != null;
    }

    public String getName() {
        return name;
    }

    public int getLocX() {
        return locX;
    }

    public int getLocY() {
        return locY;
    }
    
    public Piece getCurrentPieceOnField() {
        return currentPieceOnField;
    }

    public void setIsWhiteField(boolean isWhiteField) {
        this.isWhiteField = isWhiteField;
    }

    public boolean isWhiteField() {
        return isWhiteField;
    }

}