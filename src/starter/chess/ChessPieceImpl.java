package chess;

import java.util.ArrayList;
import java.util.Collection;

public class ChessPieceImpl implements ChessPiece {
    private ChessGame.TeamColor teamColor=null;
    private PieceType pieceType=null;
    private Collection<ChessMove> pieceMoves = new ArrayList<>();
    public ChessPieceImpl(ChessGame.TeamColor colorInput, PieceType typeInput) {
        teamColor=colorInput; pieceType=typeInput;
    }
    @Override public ChessGame.TeamColor getTeamColor() {return teamColor;}
    @Override public PieceType getPieceType() {return pieceType;}
    @Override public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        return pieceMoves;
    }
}
