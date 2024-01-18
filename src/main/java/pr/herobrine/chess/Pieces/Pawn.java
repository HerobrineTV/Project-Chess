package pr.herobrine.chess.Pieces;

import java.util.Map;

import pr.herobrine.chess.BoardUI;
import pr.herobrine.chess.ChessBoard;
import pr.herobrine.chess.FieldSpace;
import pr.herobrine.chess.Piece;

public class Pawn extends Piece {
    private int startRow;
    // Implement Class King here
    public Pawn(int locX, int locY, String name, boolean isWhite, ChessBoard chessBoard, FieldSpace currentField) {
        this.isPawn = true;
        this.locX = locX;
        this.locY = locY;
        this.isWhite = isWhite;
        this.chessBoard = chessBoard;
        this.name = name;
        this.shortname = "P";
        this.currentField = currentField;
        this.startRow = locX;
        this.currentField.setCurrentPieceOnField(this);

        if (isWhite == true) {
            this.ImageIcon = new javax.swing.ImageIcon(getClass().getResource("/pr/herobrine/chess/Pieces/Images/White_Pawn.png"));
        } else {
            this.ImageIcon = new javax.swing.ImageIcon(getClass().getResource("/pr/herobrine/chess/Pieces/Images/Black_Pawn.png"));
        }
    }

    public String[] getMoves(Map<String, FieldSpace> fields, ChessBoard chessBoard) {
        String[] PossibleMoves = new String[4];
        if (this.currentField != null) {
            int direction = this.isWhite() ? -1 : 1; // White pawns move up (-1), black pawns move down (+1)
        
            // Forward move
            if (this.locX + direction >= 0 && this.locX + direction <= chessBoard.getSizeX() 
            && fields.get((this.locX + direction) + "_" + (this.locY)) != null 
            && !fields.get((this.locX + direction) + "_" + (this.locY)).isFieldBlocked()) {
                PossibleMoves[0] = (this.locX + direction) + "_" + (this.locY);
            }
        
            // Diagonal capture moves to the left and right
            if (this.locX >= 0 && this.locX + direction >= 0 && this.locX + direction < chessBoard.getSizeX()  
            && fields.get((this.locX + direction) + "_" + (this.locY - 1)) != null
            && fields.get((this.locX + direction) + "_" + (this.locY - 1)).isFieldBlocked() 
            && fields.get((this.locX + direction) + "_" + (this.locY - 1)).getCurrentPieceOnField().isWhite() != this.isWhite()) {
                PossibleMoves[1] = (this.locX + direction) + "_" + (this.locY - 1);
            }
            if (this.locX <= chessBoard.getSizeX() - 1 && this.locX + direction >= 0 && this.locX + direction < chessBoard.getSizeX() 
            && fields.get((this.locX + direction) + "_" + (this.locY + 1)) != null
            && fields.get((this.locX + direction) + "_" + (this.locY + 1)).isFieldBlocked() 
            && fields.get((this.locX + direction) + "_" + (this.locY + 1)).getCurrentPieceOnField().isWhite() != this.isWhite()) {
                PossibleMoves[2] = (this.locX + direction) + "_" + (this.locY + 1);
            }
        
            // Two-square move from start position
            if (this.locX == this.startRow 
            && fields.get((this.locX + direction) + "_" + (this.locY)) != null 
            && fields.get((this.locX + 2 * direction) + "_" + (this.locY)) != null
            && !fields.get((this.locX + direction) + "_" + (this.locY)).isFieldBlocked() 
            && !fields.get((this.locX + 2 * direction) + "_" + (this.locY)).isFieldBlocked()) {
                PossibleMoves[3] = (this.locX + 2*direction) + "_" + (this.locY);
            }
        }        
        
        return PossibleMoves;
    }

    public boolean move(FieldSpace Field, ChessBoard chessBoard, BoardUI BoardUI) {
        boolean isValidMove = true;
        String failReason = "";
        String nextTurnColor = "";

        if ((this.isWhite == true && chessBoard.getCurrentTurn() == "White") || this.isWhite == false && chessBoard.getCurrentTurn() == "Black") {
            if (this.isWhite == true) {
                nextTurnColor = "Black";
            } else {
                nextTurnColor = "White";
            }
        } else {
            failReason = "Not your turn";
            isValidMove = false;
        }

        int locXField = Field.getLocX(); // Target X position
        int locYField = Field.getLocY(); // Target Y position
        
        // Correct the direction for white and black pawns
        int direction = this.isWhite() ? -1 : 1; 
        
        // Standard move: Move one square forward
        boolean standardMove = this.locY == locYField && this.locX + direction == locXField;
        
        // Capture move: Move one square diagonally forward
        boolean captureMoveLeft = this.locY - 1 == locYField && this.locX + direction == locXField;
        boolean captureMoveRight = this.locY + 1 == locYField && this.locX + direction == locXField;
        
        // Double move: Move two squares forward from the starting position
        boolean doubleMove = this.locX == this.startRow && this.locX + 2 * direction == locXField && this.locY == locYField;
        
        
        if (standardMove || captureMoveLeft || captureMoveRight || doubleMove) {
            // Valid move for a Pawn
        } else {
            failReason = "Invalid Move";
            isValidMove = false;
        }

        if (Field.isFieldBlocked()) {
            if (Field.getCurrentPieceOnField().isWhite() == this.isWhite()) {
                isValidMove = false;
                failReason = "Field is blocked by own Figure";
            } else {
                if (isValidMove) {
                    System.out.println(this.name + " threw out " + Field.getCurrentPieceOnField().getName()+ " ["+this.locX + "," + this.locY+"]");
                    Field.getCurrentPieceOnField().ThrowOut();
                }
            }
        } else {

        }

        if (isValidMove) {

            if (this.currentField != null) {
                this.currentField.setCurrentPieceOnField(null);
            }
            int oldLocX = this.locX;
            int oldLocY = this.locY;

            this.locX = locXField;
            this.locY = locYField;
            this.currentField = Field;
            
            chessBoard.setCurrentTurn(nextTurnColor);
            chessBoard.increaseTurnNumber();
            
            Field.setCurrentPieceOnField(this);
            //System.out.println(this.name + " moved to " + this.locX + "," + this.locY);
            if (BoardUI != null){
                BoardUI.updatePieceLocation(oldLocX + "_" + oldLocY, this.locX + "_" + this.locY);
            }
            return true;
        } else {
            this.currentField.setCurrentPieceOnField(this);
            System.out.println(this.name + " cannot move to " + locX + "," + locY + " because " + failReason + " ["+this.locX + "," + this.locY+"]");
            return false;
        }
        
    }
}  
