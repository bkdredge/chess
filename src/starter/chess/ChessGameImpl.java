package chess;
import java.util.Collection;
import java.util.HashSet;

/**
 * Custom implementation of the ChessGame class.
 */
public class ChessGameImpl implements ChessGame{
    private TeamColor gameTeamColor = null;
    private ChessBoardImpl gameBoard = new ChessBoardImpl();
    @Override public TeamColor getGameTeamColor() {return gameTeamColor;}
    @Override public void setGameTeamColor(TeamColor team) {gameTeamColor=team;}
    @Override public Collection<ChessMove> validMoves(ChessPosition startPosition) {return validMoves((ChessPositionImpl) startPosition);}
    public Collection<ChessMove> validMoves(ChessPositionImpl startPosition) {
        ChessPieceImpl piece=gameBoard.getPiece(startPosition);
        Collection<ChessMove> allMoves=piece.pieceMoves(gameBoard, startPosition);
        Collection<ChessMove> validMoves=new HashSet<>();
        ChessMoveImpl undoMove=new ChessMoveImpl();
        ChessPiece pieceTrapped;
        for(ChessMove move:allMoves){
            undoMove.setEndPosition(move.getStartPosition());
            undoMove.setStartPosition(move.getEndPosition());
            undoMove.setPromotionPiece(piece.getPieceType());
            pieceTrapped = gameBoard.getPiece(move.getEndPosition());
            gameBoard.boardMove(move);
            if (!isInCheck(piece.getTeamColor())){validMoves.add(move);}
            gameBoard.boardMove(undoMove);
            gameBoard.addPiece(move.getEndPosition(),pieceTrapped);
        }
        return validMoves;
    }
    @Override public void gameMove(ChessMove move) throws InvalidMoveException {gameMove((ChessMoveImpl) move);}
    public void gameMove(ChessMoveImpl move) throws InvalidMoveException {
        if(this.gameTeamColor!=null){
            if(gameBoard.getPiece(move.getStartPosition()).getTeamColor() != this.gameTeamColor){
                throw new InvalidMoveException("Incorrect team.");
            }
        }
        Collection<ChessMove> validMoveSet = validMoves(move.getStartPosition());
        if(!validMoveSet.contains(move)){throw new InvalidMoveException("Not a valid move.");}
        gameBoard.boardMove(move);
        if(gameTeamColor ==TeamColor.WHITE){setGameTeamColor(TeamColor.BLACK);}
        else{setGameTeamColor(TeamColor.WHITE);}
    }
    @Override public boolean isInCheck(TeamColor teamColor) {
        ChessPositionImpl thisPosition = new ChessPositionImpl(0,0);
        ChessPositionImpl kingPosition = new ChessPositionImpl(0,0);
        Collection<ChessMove> opponentMoves;
        for (int i = 1; i <= 8; i++){
            for (int j = 1; j <= 8; j++){
                thisPosition.setRow(i);
                thisPosition.setColumn(j);
                if(gameBoard.getPiece(thisPosition)!=null &&
                        gameBoard.getPiece(thisPosition).getPieceType() == ChessPiece.PieceType.KING &&
                        gameBoard.getPiece(thisPosition).getTeamColor() == teamColor){
                    kingPosition.setRow(i); kingPosition.setColumn(j);
                }
            }
        }
        for (int i=1;i<=8;i++){
            for (int j=1;j<=8;j++){
                thisPosition.setRow(i);
                thisPosition.setColumn(j);
                if (gameBoard.getPiece(thisPosition) != null &&
                        gameBoard.getPiece(thisPosition).getTeamColor() != teamColor){
                    opponentMoves = gameBoard.getPiece(thisPosition).pieceMoves(gameBoard, thisPosition);
                    for(ChessMove move:opponentMoves){
                        if (move.getEndPosition().equals(kingPosition)) return true;
                    }
                }
            }
        }
        return false;
    }
    @Override public boolean isInCheckmate(TeamColor teamColor) {
        if(!isInCheck(teamColor)) return false;
        ChessPositionImpl thisPosition = new ChessPositionImpl(0,0);
        Collection<ChessMove> teamMoves;
        for (int i = 1; i <= 8; i++){
            for (int j = 1; j <= 8; j++){
                thisPosition.setRow(i);
                thisPosition.setColumn(j);
                if(gameBoard.getPiece(thisPosition) != null &&
                        gameBoard.getPiece(thisPosition).getTeamColor() == teamColor){
                    teamMoves = validMoves(thisPosition);
                    if(!teamMoves.isEmpty()) return false;
                }
            }
        }
        return true;
    }
    @Override public boolean isInStalemate(TeamColor teamColor) {
        if(isInCheck(teamColor)) return false;
        ChessPositionImpl thisPosition = new ChessPositionImpl(0,0);
        Collection<ChessMove> teamMoves;
        for (int i = 1; i <= 8; i++){
            for (int j = 1; j <= 8; j++){
                thisPosition.setRow(i);
                thisPosition.setColumn(j);
                if(gameBoard.getPiece(thisPosition)!=null &&
                        gameBoard.getPiece(thisPosition).getTeamColor() == teamColor){
                    teamMoves = validMoves(thisPosition);
                    if(!teamMoves.isEmpty()) return false;
                }
            }
        }
        return true;
    }
    @Override public void setBoard(ChessBoard board) {gameBoard=(ChessBoardImpl) board;}
    @Override public ChessBoard getBoard() {return gameBoard;}
    @Override public String toString() {
        return "ChessGameImpl{" +
                "gameTeamColor=" + gameTeamColor +
                ", gameBoard=" + gameBoard +
                '}';
    }
}