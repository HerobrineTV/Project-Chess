package pr.herobrine.chess;

import javax.swing.*;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;

public class BoardUI extends JFrame {
    private final int SIZEX; // assuming these are defined
    private final int SIZEY; // assuming these are defined
    private Map<String, FieldSpace> fields; // assuming this is your field data
    private Map<String, JLabel> jlabels; // assuming this is your piece data
    private ChessBoard chessBoard;
    private Piece CurrentSelectedPiece;
    private BoardUI BoardUI;

    public BoardUI(int sizeX, int sizeY, Map<String, FieldSpace> fields, ChessBoard chessBoard) {
        this.SIZEX = sizeX;
        this.SIZEY = sizeY;
        this.fields = fields;
        this.chessBoard = chessBoard;
        this.jlabels = new HashMap<>();
        this.BoardUI = this;
        this.CurrentSelectedPiece = null;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Board Game [Map: "+chessBoard.getBoardName()+"] [Turn: "+chessBoard.getTurnNumber()+"] "+chessBoard.getCurrentTurn()+"'s Turn!");
        setSize(1080, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(SIZEX, SIZEY));

        int labelWidth = getWidth() / SIZEX;
        int labelHeight = getHeight() / SIZEY;
        
        JLabel[] currentOutlinedLabels = new JLabel[fields.size()];

        for (int i = 0; i < SIZEX; i++) {
            for (int i2 = 0; i2 < SIZEY; i2++) {
                FieldSpace space = fields.get(i + "_" + i2);
                JLabel label = new JLabel();
                label.setOpaque(true);
                label.setName(i + "_" + i2);
    
                // Set the background color
                if (space.isWhiteField()) {
                    label.setBackground(Color.WHITE);
                } else {
                    label.setBackground(Color.DARK_GRAY);
                }

                // Add Interaction Ability 
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (currentOutlinedLabels[fields.size()-1]!= null) {
                            for (int i2 = 0; i2 < currentOutlinedLabels.length; i2++){
                                if (currentOutlinedLabels[i2]!= null) {
                                    currentOutlinedLabels[i2].setBorder(BorderFactory.createEmptyBorder());
                                }
                            }
                        }
                        if (label.getBorder() == BorderFactory.createEmptyBorder()) {
                            if (space.getCurrentPieceOnField()!= null) {
                                Boolean isThrowable = false;
                                if (CurrentSelectedPiece != null && CurrentSelectedPiece.isOnTurn()) {
                                    String[] Moves = CurrentSelectedPiece.getMoves(fields, chessBoard);
                                    for (int i2 = 0; i2 < Moves.length; i2++) {
                                        if (jlabels.get(Moves[i2]) == jlabels.get(label.getName())) {
                                            CurrentSelectedPiece.move(fields.get(label.getName()), chessBoard, BoardUI);
                                            isThrowable = true;
                                        }
                                    }
                                }
                                if (isThrowable == false) {
                                    label.setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));
                                    currentOutlinedLabels[fields.size()-1] = label;
                                    String[] Moves2 = space.getCurrentPieceOnField().getMoves(fields, chessBoard);
                                    CurrentSelectedPiece = space.getCurrentPieceOnField();
    
                                    for (int i3 = 0; i3 < Moves2.length; i3++) {
                                        if (jlabels.get(Moves2[i3])!= null) {
                                            jlabels.get(Moves2[i3]).setBorder(BorderFactory.createLineBorder(Color.RED, 5));
                                            currentOutlinedLabels[i3] = jlabels.get(Moves2[i3]);
                                        }
                                    }
                                }
                            } else {
                                if (CurrentSelectedPiece != null) {
                                    String[] Moves = CurrentSelectedPiece.getMoves(fields, chessBoard);
                                    for (int i2 = 0; i2 < Moves.length; i2++) {
                                        if (jlabels.get(Moves[i2]) == jlabels.get(label.getName())) {
                                            CurrentSelectedPiece.move(fields.get(label.getName()), chessBoard, BoardUI);
                                            //System.out.println("Wright Field Name");
                                        } else {
                                            //System.out.println("Wrong Field Name: "+label.getName()+" got "+Moves[i2]);
                                        }
                                    }
                                    CurrentSelectedPiece = null;
                                }
                            }
                        } else {
                            label.setBorder(BorderFactory.createEmptyBorder());
                            if (space.getCurrentPieceOnField() != null) {
                                String[] Moves = space.getCurrentPieceOnField().getMoves(fields, chessBoard);
                                for (int i2 = 0; i2 < Moves.length; i2++) {
                                    if (jlabels.get(Moves[i2])!= null) {
                                        jlabels.get(Moves[i2]).setBorder(BorderFactory.createEmptyBorder());
                                    }
                                }
                            }
                        }
                    }
                });
    
                // Check if there's a piece on this field and set its image
                if (space.getCurrentPieceOnField() != null) {
                    ImageIcon originalIcon = space.getCurrentPieceOnField().getImageIcon();
        
                    // Resize the icon to fit the label
                    Image resizedImage = originalIcon.getImage().getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
                    ImageIcon resizedIcon = new ImageIcon(resizedImage);
        
                    // Set the resized icon to the label
                    label.setIcon(resizedIcon);
                }

                add(label);
                jlabels.put(space.getName(), label);
            }
        }
    }

    public void updatePieceLocation(String oldField, String newField) {
        // Get the JLabels for the old and new fields
        CurrentSelectedPiece = null;

        JLabel oldLabel = jlabels.get(oldField);
        JLabel newLabel = jlabels.get(newField);

        //System.out.println(oldLabel+" "+newLabel);

        // Move the icon from the old label to the new label
        if (oldLabel != null && newLabel != null) {
            newLabel.setIcon(oldLabel.getIcon());
            oldLabel.setIcon(null);

            //System.out.println("HELP");
        }
        setTitle("Board Game [Map: "+chessBoard.getBoardName()+"] [Turn: "+chessBoard.getTurnNumber()+"] "+chessBoard.getCurrentTurn()+"'s Turn!");

        
        if (chessBoard.getBlackFiguresLeft() == 0 || chessBoard.getWhiteFiguresLeft() == 0 || chessBoard.getWhiteKingsLeft() == 0 || chessBoard.getBlackKingsLeft() == 0) {
            String Winner = "";
            if (chessBoard.getBlackFiguresLeft() == 0) {
                Winner = "White";
            } else if (chessBoard.getWhiteFiguresLeft() == 0) {
                Winner = "Black";
            } else if (chessBoard.getWhiteKingsLeft() == 0) {
                Winner = "Black";
            } else if (chessBoard.getBlackKingsLeft() == 0) {
                Winner = "White";
            }

            setTitle("Game Over [Map: "+chessBoard.getBoardName()+"] [Turn: "+chessBoard.getTurnNumber()+"] Winner: "+Winner+"!");
            JOptionPane.showMessageDialog(null, "Game Over!");
            System.exit(0);
        }
    }
}