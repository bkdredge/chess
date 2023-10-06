package chess;

public class ChessMoveImpl implements ChessMove{
    private ChessPositionImpl startPosition,endPosition;
    private ChessPiece.PieceType promotionPiece=null;
    public ChessMoveImpl(ChessPositionImpl startPositionInput, ChessPositionImpl endPositionInput,
                         ChessPiece.PieceType promotionInput) {
        startPosition=startPositionInput;
        endPosition=endPositionInput;
        promotionPiece=promotionInput;
    }
    @Override public ChessPosition getStartPosition() {return startPosition;}
    @Override public ChessPosition getEndPosition() {return endPosition;}
    @Override public ChessPiece.PieceType getPromotionPiece() {return promotionPiece;}
    public String toStringMove() {
        StringBuilder out=new StringBuilder();
        out.append(":MOVE("+startPosition.toStringPositionInMove()+"->"+endPosition.toStringPositionInMove()+")");
        return out.toString();
    }
}
