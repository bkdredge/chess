package chessStarter;

import java.util.Collection;

/**
 * Self-made inheritance for ChessPiece rules.
 */
public interface ChessPieceImplRules {
    public Collection<ChessMove> populatePieceRules(ChessPosition position, ChessBoard board);
}