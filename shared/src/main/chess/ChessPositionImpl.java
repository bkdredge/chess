package chess;

import chessStarter.ChessPiece;
import chessStarter.ChessPosition;

import java.util.Objects;

/**
 * Custom implementation of the ChessPosition class.
 */
public class ChessPositionImpl implements ChessPosition {
    private int column=0, row=0;
    private ChessPiece pieceOnPosition =null;
    public ChessPositionImpl(int colInput, int rowInput) {column = colInput; row = rowInput;}
    @Override public int getRow() {return row;}
    public void setRow(int row) {this.row = row;}
    @Override public int getColumn() {
        return column;
    }
    public void setColumn(int column) {
        this.column = column;
    }
    public ChessPiece getPieceOnPosition() {
        return pieceOnPosition;
    }
    public void setPieceOnPosition(ChessPiece pieceOnPosition) {
        this.pieceOnPosition = pieceOnPosition;
    }
    public int hashCode(){
        return Objects.hash(row, column);
    }
    public boolean equals(Object o){
        if(o.getClass()!=this.getClass()) return false; if(o==this) return true;
        ChessPositionImpl d = (ChessPositionImpl) o;
        if (d.column != this.column) return false;
        if(d.row != this.row) return false;
        return true;
    }
    @Override public String toString() {
        return "ChessPositionImpl{" +
                "column=" + column +
                ", row=" + row +
                ", pieceOnPosition=" + pieceOnPosition +
                '}';
    }
}