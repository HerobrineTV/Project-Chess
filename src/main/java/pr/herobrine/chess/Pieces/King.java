package pr.herobrine.chess.Pieces;

import java.util.Map;

import pr.herobrine.chess.BoardUI;
import pr.herobrine.chess.ChessBoard;
import pr.herobrine.chess.FieldSpace;
import pr.herobrine.chess.Piece;

public class King extends Piece {
    // Implement Class King here
    public King(int locX, int locY, String name, boolean isWhite, ChessBoard chessBoard, FieldSpace currentField) {
        this.isKing = true;
        this.locX = locX;
        this.locY = locY;
        this.isWhite = isWhite;
        this.chessBoard = chessBoard;
        this.name = name;
        this.shortname = "K";
        this.currentField = currentField;
        this.currentField.setCurrentPieceOnField(this);

        if (isWhite == true) {
            this.ImageIcon = new javax.swing.ImageIcon(getClass().getResource("/pr/herobrine/chess/Pieces/Images/White_King.png"));
        } else {
            this.ImageIcon = new javax.swing.ImageIcon(getClass().getResource("/pr/herobrine/chess/Pieces/Images/Black_King.png"));
        }
    }

    public String[] getMoves(Map<String, FieldSpace> fields, ChessBoard chessBoard) {
        String[] PossibleMoves = new String[8];
        if (this.currentField != null) {
            // Move one square to the left
            if (this.locX > 0 && isMovePossible(this.locX - 1, this.locY, fields)) {
                PossibleMoves[0] = (this.locX - 1) + "_" + this.locY;
            }
            // Move one square to the right
            if (this.locX < chessBoard.getSizeX() - 1 && isMovePossible(this.locX + 1, this.locY, fields)) {
                PossibleMoves[1] = (this.locX + 1) + "_" + this.locY;
            }
            // Move one square up
            if (this.locY > 0 && isMovePossible(this.locX, this.locY - 1, fields)) {
                PossibleMoves[2] = this.locX + "_" + (this.locY - 1);
            }
            // Move one square down
            if (this.locY < chessBoard.getSizeY() - 1 && isMovePossible(this.locX, this.locY + 1, fields)) {
                PossibleMoves[3] = this.locX + "_" + (this.locY + 1);
            }
            // Move diagonally up-left
            if (this.locX > 0 && this.locY > 0 && isMovePossible(this.locX - 1, this.locY - 1, fields)) {
                PossibleMoves[4] = (this.locX - 1) + "_" + (this.locY - 1);
            }
            // Move diagonally up-right
            if (this.locX < chessBoard.getSizeX() - 1 && this.locY > 0 && isMovePossible(this.locX + 1, this.locY - 1, fields)) {
                PossibleMoves[5] = (this.locX + 1) + "_" + (this.locY - 1);
            }
            // Move diagonally down-left
            if (this.locX > 0 && this.locY < chessBoard.getSizeY() - 1 && isMovePossible(this.locX - 1, this.locY + 1, fields)) {
                PossibleMoves[6] = (this.locX - 1) + "_" + (this.locY + 1);
            }
            // Move diagonally down-right
            if (this.locX < chessBoard.getSizeX() - 1 && this.locY < chessBoard.getSizeY() - 1 && isMovePossible(this.locX + 1, this.locY + 1, fields)) {
                PossibleMoves[7] = (this.locX + 1) + "_" + (this.locY + 1);
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

        if (Field.isFieldBlocked()) {
            if (Field.getCurrentPieceOnField().isWhite() == this.isWhite()) {
                isValidMove = false;
                failReason = "Field is blocked by own Figure";
            } else {
                System.out.println(this.name + " threw out " + Field.getCurrentPieceOnField().getName()+ " ["+this.locX + "," + this.locY+"]");
                Field.getCurrentPieceOnField().ThrowOut();
            }
        } else {

        }

        int locX = Field.getLocX();
        int locY = Field.getLocY();

        if (Math.abs(this.locX - locX) <= 1 && Math.abs(this.locY - locY) <= 1) {

            } else {
                failReason = "Invalid Move";
                isValidMove = false;
            }


            if (isValidMove) {
                if (this.currentField != null) {
                    this.currentField.setCurrentPieceOnField(null);
                }
                int oldLocX = this.locX;
                int oldLocY = this.locY;

                this.locX = locX;
                this.locY = locY;
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
  
