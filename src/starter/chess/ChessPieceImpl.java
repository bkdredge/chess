package chess;

import java.util.ArrayList;
import java.util.Collection;

public class ChessPieceImpl implements ChessPiece {
    private ChessGame.TeamColor teamColor=null;
    private PieceType pieceType=null;
    private Collection<ChessMoveImpl> pieceMoves=new ArrayList<>();
    public ChessPieceImpl(ChessGame.TeamColor colorInput, PieceType typeInput) {teamColor=colorInput; pieceType=typeInput;}
    @Override public ChessGame.TeamColor getTeamColor() {return teamColor;}
    @Override public PieceType getPieceType() {return pieceType;}
    @Override public Collection<ChessMoveImpl> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        return pieceMoves;
    }
    public String toStringPiece() {
        StringBuilder out=new StringBuilder();
        if(teamColor==ChessGame.TeamColor.WHITE) {out.append("(WHITE)");}
        else if (teamColor==ChessGame.TeamColor.WHITE) {out.append("(BLACK)");}
        else {out.append("(UNKNOWN)");}
        if(pieceType==PieceType.PAWN) {out.append("PAWN");}
        else if(pieceType==PieceType.ROOK) {out.append("ROOK");}
        else if(pieceType==PieceType.KNIGHT) {out.append("KNIGHT");}
        else if(pieceType==PieceType.BISHOP) {out.append("BISHOP");}
        else if(pieceType==PieceType.KING) {out.append("KING");}
        else if(pieceType==PieceType.QUEEN) {out.append("QUEEN");}
        return out.toString();
    }
}
