package chess;

import java.util.Objects;

public class ChessPositionImpl implements ChessPosition{
    private int column=0, row=0;
    private ChessPiece pieceOnSquare=null;
    public ChessPositionImpl(int colInput, int rowInput) {column = colInput; row = rowInput;}
    @Override public int getRow() {return row;}
    public void setRow(int row) {this.row = row;}
    @Override public int getColumn() {
        return column;
    }
    public void setColumn(int column) {
        this.column = column;
    }
    public ChessPiece getPieceOnSquare() {
        return pieceOnSquare;
    }
    public void setPieceOnSquare(ChessPiece pieceOnSquare) {
        this.pieceOnSquare = pieceOnSquare;
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
}