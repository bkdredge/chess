package chess;
import java.util.Collection;
import java.util.HashSet;

/**
 * Implementation of rules for the Bishop.
 */
public class ChessPieceImplRulesBishop implements ChessPieceImplRules {
    private Collection<ChessMove>bishopMoves = new HashSet<>();
    private ChessBoardImpl bishopBoard;
    private ChessPositionImpl bishopPosition;
    @Override public Collection<ChessMove> populatePieceRules(ChessPosition position, ChessBoard board) {
        return populatePieceRules((ChessPositionImpl) position, (ChessBoardImpl) board);
    }
    public Collection<ChessMove> populatePieceRules(ChessPositionImpl position, ChessBoardImpl board) {
        bishopMoves.clear(); bishopBoard =board; bishopPosition =position;
        ChessPiece piece = bishopBoard.getPiece(bishopPosition);
        if(piece.getPieceType() != ChessPiece.PieceType.BISHOP){
            System.out.println("No bishop found."); return null;
        }
        generateMoves(piece.getTeamColor()); return bishopMoves;
    }
    private void generateMoves(ChessGame.TeamColor color){
        boolean clearToMove = true;
        int positionRow= bishopPosition.getRow(); int positionColumn= bishopPosition.getColumn();
        ChessPositionImpl newPosition=new ChessPositionImpl(positionColumn, positionRow);
        //RIGHT-UP
        while(clearToMove){
            positionRow++; positionColumn++;
            if(positionRow>8||positionColumn>8) break;
            newPosition.setRow(positionRow); newPosition.setColumn(positionColumn);
            clearToMove= clearToMove(newPosition, color);
        }
        //LEFT-UP
        clearToMove=true; positionRow= bishopPosition.getRow(); positionColumn= bishopPosition.getColumn();
        while(clearToMove){
            positionRow++; positionColumn--;
            if(positionRow>8||positionColumn<1) break;
            newPosition.setRow(positionRow); newPosition.setColumn(positionColumn);
            clearToMove= clearToMove(newPosition, color);
        }
        //LEFT-DOWN
        clearToMove=true; positionRow= bishopPosition.getRow(); positionColumn= bishopPosition.getColumn();
        while(clearToMove){
            positionRow--; positionColumn--;
            if(positionRow<1||positionColumn<1) break;
            newPosition.setRow(positionRow); newPosition.setColumn(positionColumn);
            clearToMove= clearToMove(newPosition, color);
        }
        //RIGHT-DOWN
        clearToMove=true; positionRow = bishopPosition.getRow(); positionColumn = bishopPosition.getColumn();
        while(clearToMove){
            positionRow--; positionColumn++;
            if(positionRow<1||positionColumn>8) break;
            newPosition.setRow(positionRow); newPosition.setColumn(positionColumn);
            clearToMove=clearToMove(newPosition, color);
        }
    }
    private boolean clearToMove(ChessPositionImpl newPosition, ChessGame.TeamColor color){
        ChessPosition endPosition = bishopBoard.findBoardPosition(newPosition);
        if(bishopBoard.getPiece(endPosition) == null){
            bishopMoves.add(new ChessMoveImpl(bishopPosition, endPosition, null)); return true;
        } else if(bishopBoard.getPiece(endPosition).getTeamColor() != color){
            bishopMoves.add(new ChessMoveImpl(bishopPosition, endPosition, null)); return false;
        } else return false;
    }
}