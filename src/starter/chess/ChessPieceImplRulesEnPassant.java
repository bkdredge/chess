package chess;

import java.util.Collection;
import java.util.HashSet;

public class ChessPieceImplRulesEnPassant implements ChessPieceImplRules{
    private Collection<ChessMove>enPassantMoves = new HashSet<>();
    private ChessBoardImpl enPassantBoard;
    @Override public Collection<ChessMove> populatePieceRules(ChessPosition position, ChessBoard board) {
        return null;
    }
}
