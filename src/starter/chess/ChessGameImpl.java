package chess;

import java.util.ArrayList;
import java.util.Collection;

public class ChessGameImpl implements ChessGame{
    private ChessBoardImpl board=new ChessBoardImpl();
    private TeamColor currentTeam=null;
    @Override public TeamColor getTeamTurn() {
        return currentTeam;
    }
    @Override public void setTeamTurn(TeamColor team) {currentTeam=team;}
    @Override public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        return null;
    }
    @Override public void makeMove(ChessMove move) throws InvalidMoveException {
        board.movePiece(move.getStartPosition(), move.getEndPosition());
    }
    @Override public boolean isInCheck(TeamColor teamColor) {
        return false;
    }
    @Override public boolean isInCheckmate(TeamColor teamColor) {
        return false;
    }
    @Override public boolean isInStalemate(TeamColor teamColor) {
        return false;
    }
    @Override public void setBoard(ChessBoard board) {
        board.resetBoard();
    }
    @Override public ChessBoard getBoard() {
        return board;
    }
}
