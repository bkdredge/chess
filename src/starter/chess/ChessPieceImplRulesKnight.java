package chess;
import java.util.Collection;
import java.util.HashSet;
public class ChessPieceImplRulesKnight implements ChessPieceImplRules {
    private Collection<ChessMove> knightMoves = new HashSet<>();
    private ChessBoardImpl knightBoard;
    private ChessPositionImpl knightPosition;
    @Override public Collection<ChessMove> populatePieceRules(ChessPosition position, ChessBoard board) {
        return populatePieceRules((ChessPositionImpl) position, (ChessBoardImpl) board);
    }
    public Collection<ChessMove> populatePieceRules(ChessPositionImpl position, ChessBoardImpl board) {
        knightMoves.clear(); knightBoard =(ChessBoardImpl)board; knightPosition = position;
        ChessPiece piece = knightBoard.getPiece(knightPosition);
        if(piece.getPieceType()!=ChessPiece.PieceType.KNIGHT){
            System.out.println("No knight found."); return null;
        }
        generateMoves(piece.getTeamColor()); return knightMoves;
    }

    private void generateMoves(ChessGame.TeamColor color){
        ChessPositionImpl newPosition = new ChessPositionImpl(knightPosition.getColumn(), knightPosition.getRow());
        if (knightPosition.getColumn() < 7){
            //RIGHT-UP
            if(knightPosition.getRow() < 7){
                newPosition.setRow(knightPosition.getRow()+2);
                newPosition.setColumn(knightPosition.getColumn()+1); clearToMove(newPosition, color);
                newPosition.setRow(knightPosition.getRow()+1);
                newPosition.setColumn(knightPosition.getColumn()+2); clearToMove(newPosition, color);
            }
            //RIGHT-DOWN
            if(knightPosition.getRow() > 2){
                newPosition.setRow(knightPosition.getRow()-2);
                newPosition.setColumn(knightPosition.getColumn()+1); clearToMove(newPosition, color);
                newPosition.setRow(knightPosition.getRow()-1);
                newPosition.setColumn(knightPosition.getColumn()+2); clearToMove(newPosition, color);
            }
        }
        if (knightPosition.getColumn() > 2){
            //LEFT-UP
            if(knightPosition.getRow() < 7){
                newPosition.setRow(knightPosition.getRow()+2);
                newPosition.setColumn(knightPosition.getColumn()-1); clearToMove(newPosition, color);
                newPosition.setRow(knightPosition.getRow()+1);
                newPosition.setColumn(knightPosition.getColumn()-2); clearToMove(newPosition, color);
            }
            //LEFT-DOWN
            if(knightPosition.getRow() > 2){
                newPosition.setRow(knightPosition.getRow()-2);
                newPosition.setColumn(knightPosition.getColumn()-1); clearToMove(newPosition, color);
                newPosition.setRow(knightPosition.getRow()-1);
                newPosition.setColumn(knightPosition.getColumn()-2); clearToMove(newPosition, color);
            }
        }
    }
    private void clearToMove(ChessPosition newPosition, ChessGame.TeamColor color){
        ChessPosition endPosition = knightBoard.findBoardPosition(newPosition);
        if(knightBoard.getPiece(endPosition) == null || knightBoard.getPiece(endPosition).getTeamColor() != color){
            knightMoves.add(new ChessMoveImpl(knightPosition, endPosition, null));
        }
    }
}