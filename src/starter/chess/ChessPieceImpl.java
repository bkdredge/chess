package chess;
import java.util.Collection;
public class ChessPieceImpl implements ChessPiece{
    private PieceType pieceType = null;
    private ChessGame.TeamColor pieceTeamColor = null;
    private ChessPieceImplRules pieceRules;
    //private ChessBoardImpl pieceBoard;
    public ChessPieceImpl(PieceType typeInput, ChessGame.TeamColor colorInput){
        pieceType = typeInput; pieceTeamColor=colorInput;
    }
    @Override public ChessGame.TeamColor getTeamColor() {return pieceTeamColor;}
    @Override public PieceType getPieceType() {return pieceType;}
    public void setPieceType(PieceType type) {this.pieceType = type;}
    @Override public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        return pieceMoves((ChessBoardImpl) board, (ChessPositionImpl) myPosition);
    }
    public Collection<ChessMove> pieceMoves(ChessBoardImpl board, ChessPositionImpl myPosition) {
        if(pieceType == PieceType.BISHOP) {
            pieceRules = new ChessPieceImplRulesBishop(); return pieceRules.populatePieceRules(myPosition, board);
        } else if(pieceType == PieceType.KING){
            pieceRules=new ChessPieceImplRulesKing(); return pieceRules.populatePieceRules(myPosition, board);
        } else if(pieceType == PieceType.KNIGHT){
            pieceRules=new ChessPieceImplRulesKnight(); return pieceRules.populatePieceRules(myPosition, board);
        } else if(pieceType == PieceType.PAWN){
            pieceRules=new ChessPieceImplRulesPawn(); return pieceRules.populatePieceRules(myPosition, board);
        } else if(pieceType == PieceType.QUEEN){
            pieceRules=new ChessPieceImplRulesQueen(); return pieceRules.populatePieceRules(myPosition, board);
        } else if(pieceType == PieceType.ROOK){
            pieceRules=new ChessPieceImplRulesRook(); return pieceRules.populatePieceRules(myPosition, board);
        }
        return null;
    }
    public boolean equals(Object o){
        if(o.getClass()!=this.getClass()) return false; if(o==this) return true;
        ChessPieceImpl d=(ChessPieceImpl) o;
        if(d.pieceTeamColor !=this.pieceTeamColor) return false;
        if(d.pieceType !=this.pieceType) return false;
        return true;
    }
}