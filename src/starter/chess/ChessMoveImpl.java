package chess;

import java.util.Objects;

public class ChessMoveImpl implements ChessMove{
    private ChessPosition startPosition,endPosition;
    private ChessPiece.PieceType promotionPiece;
    public ChessMoveImpl(){startPosition = null; endPosition = null; promotionPiece = null;}
    public ChessMoveImpl(ChessPosition start, ChessPosition end, ChessPiece.PieceType promotion){
        startPosition = start; endPosition = end; promotionPiece = promotion;
    }
    @Override public ChessPosition getStartPosition() {return startPosition;}
    @Override public ChessPosition getEndPosition() {return endPosition;}
    @Override public ChessPiece.PieceType getPromotionPiece() {return promotionPiece;}
    public void setStartPosition(ChessPosition startPosition) {this.startPosition = startPosition;}
    public void setEndPosition(ChessPosition endPosition) {this.endPosition = endPosition;}
    public void setPromotionPiece(ChessPiece.PieceType pieceType) {promotionPiece=pieceType;}
    public int hashCode(){return Objects.hash(startPosition,endPosition,promotionPiece);}
    public boolean equals(Object o){
        if(o.getClass()!=this.getClass()) return false; if(o==this) return true;
        ChessMoveImpl d = (ChessMoveImpl) o;
        if(!this.startPosition.equals(d.startPosition)) return false;
        if(!this.endPosition.equals(d.endPosition)) return false;
        if(d.promotionPiece != this.promotionPiece) return false;
        return true;
    }
}