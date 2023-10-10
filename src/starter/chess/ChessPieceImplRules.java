package chess;
import java.util.Collection;
public interface ChessPieceImplRules {
    public Collection<ChessMove> populatePieceRules(ChessPosition position, ChessBoard board);
}