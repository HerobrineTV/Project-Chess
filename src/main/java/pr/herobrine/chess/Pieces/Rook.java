package pr.herobrine.chess.Pieces;

import java.util.ArrayList;
import java.util.Map;

import pr.herobrine.chess.BoardUI;
import pr.herobrine.chess.ChessBoard;
import pr.herobrine.chess.FieldSpace;
import pr.herobrine.chess.Piece;

public class Rook extends Piece {
    // Implement Class King here
    public Rook(int locX, int locY, String name, boolean isWhite, ChessBoard chessBoard, FieldSpace currentField) {
        this.isRook = true;
        this.locX = locX;
        this.locY = locY;
        this.isWhite = isWhite;
        this.chessBoard = chessBoard;
        this.name = name;
        this.shortname = "R";
        this.currentField = currentField;
        this.currentField.setCurrentPieceOnField(this);

        if (isWhite == true) {
            this.ImageIcon = new javax.swing.ImageIcon(getClass().getResource("/pr/herobrine/chess/Pieces/Images/White_Rook.png"));
        } else {
            this.ImageIcon = new javax.swing.ImageIcon(getClass().getResource("/pr/herobrine/chess/Pieces/Images/Black_Rook.png"));
        }
    }

    public String[] getMoves(Map<String, FieldSpace> fields, ChessBoard chessBoard) {
        ArrayList<String> possibleMoves = new ArrayList<>();
    
        if (this.currentField != null) {
            // Directions: horizontal, vertical, and diagonal
            int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
            for (int[] dir : directions) {
                int stepX = dir[0];
                int stepY = dir[1];
                int x = this.locX;
                int y = this.locY;
    
                while (true) {
                    x += stepX;
                    y += stepY;
    
                    // Check if the new position is within the bounds of the board
                    if (x >= 0 && x < chessBoard.getSizeX() && y >= 0 && y < chessBoard.getSizeY()) {
                        if (isMovePossible(x, y, fields)) {
                            possibleMoves.add(x + "_" + y);
                            if (fields.containsKey(x + "_" + y) && fields.get(x + "_" + y).isFieldBlocked()) {
                                // If an enemy piece is encountered, stop in this direction after adding the capture move
                                break;
                            }
                        } else {
                            // If move is not possible (blocked by a piece), stop in this direction
                            break;
                        }
                    } else {
                        // If out of bounds, stop in this direction
                        break;
                    }
                }
            }
        }
        this.LastFieldsMap = fields;
        // Convert the ArrayList to an array
        return possibleMoves.toArray(new String[0]);
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
                if (isValidMove) {
                    System.out.println(this.name + " threw out " + Field.getCurrentPieceOnField().getName()+ " ["+this.locX + "," + this.locY+"]");
                    Field.getCurrentPieceOnField().ThrowOut();
                }
            }
        } else {

        }

        int locX = Field.getLocX();
        int locY = Field.getLocY();

        if (isMoveValid(locX, locY, this.LastFieldsMap, chessBoard)) {

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

        public boolean isMoveValid(int locX, int locY, Map<String, FieldSpace> fields, ChessBoard chessBoard) {
            // Check if the target location Field exists
            if (locX < 0 || locX >= chessBoard.getSizeX() || locY < 0 || locY >= chessBoard.getSizeY()) {
                return false;
            }
        
            // Check if the move is horizontal, vertical, or diagonal
            boolean isHorizontal = this.locY == locY;
            boolean isVertical = this.locX == locX;
        
            // Check if the Direction is legal
            if (!(isHorizontal || isVertical )) {
                return false;
            }
        
            int directionX = Integer.compare(locX, this.locX);
            int directionY = Integer.compare(locY, this.locY);
        
            int currentX = this.locX + directionX;
            int currentY = this.locY + directionY;
        
            // Check for obstacles in the path
            while (currentX != locX || currentY != locY) {
                if (fields.containsKey(currentX + "_" + currentY) && fields.get(currentX + "_" + currentY).isFieldBlocked()) {
                    return false; // Path blocked
                }
                currentX += directionX;
                currentY += directionY;
            }
        
            // The move is valid
            return true;
        }
        
            
    }
  
