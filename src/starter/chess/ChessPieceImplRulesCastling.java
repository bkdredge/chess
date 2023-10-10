package chess;

import java.util.Collection;
import java.util.HashSet;

public class ChessPieceImplRulesCastling implements ChessPieceImplRules{
    private Collection<ChessMove>castlingMoves = new HashSet<>();
    private ChessBoardImpl castlingBoard;
    @Override public Collection<ChessMove> populatePieceRules(ChessPosition position, ChessBoard board) {
        return null;
    }
}
