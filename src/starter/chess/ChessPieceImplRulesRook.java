package chess;
import java.util.Collection;
import java.util.HashSet;
public class ChessPieceImplRulesRook implements ChessPieceImplRules {
    private Collection<ChessMove> rookMoves=new HashSet<>();
    private ChessBoardImpl rookBoard;
    private ChessPositionImpl rookPosition;
    @Override public Collection<ChessMove> populatePieceRules(ChessPosition position, ChessBoard board) {
        return populatePieceRules((ChessPositionImpl) position, (ChessBoardImpl) board);
    }
    public Collection<ChessMove> populatePieceRules(ChessPositionImpl position, ChessBoardImpl board) {
        rookMoves.clear(); rookBoard =(ChessBoardImpl) board; rookPosition =position;
        ChessPiece piece = rookBoard.getPiece(rookPosition);
        if(piece.getPieceType() != ChessPiece.PieceType.ROOK){
            System.out.println("No rook found."); return null;
        }
        generateMoves(piece.getTeamColor()); return rookMoves;
    }
    private void generateMoves(ChessGame.TeamColor color){
        boolean clearToMove=true; int positionRow= rookPosition.getRow(); int positionColumn= rookPosition.getColumn();
        ChessPositionImpl newPosition = new ChessPositionImpl(positionColumn, positionRow);
        //UP
        while(clearToMove){
            positionRow++; if(positionRow>8) break;
            newPosition.setRow(positionRow); clearToMove= clearToMove(newPosition,color);
        }
        //RIGHT
        clearToMove=true; positionRow= rookPosition.getRow(); positionColumn= rookPosition.getColumn();
        newPosition.setRow(positionRow);
        while(clearToMove){
            positionColumn++; if(positionColumn>8) break;
            newPosition.setColumn(positionColumn); clearToMove= clearToMove(newPosition,color);
        }
        //LEFT
        clearToMove=true; positionRow= rookPosition.getRow(); positionColumn= rookPosition.getColumn();
        newPosition.setRow(positionRow);
        while(clearToMove){
            positionColumn--; if(positionColumn<1) break;
            newPosition.setColumn(positionColumn); clearToMove= clearToMove(newPosition,color);
        }
        //DOWN
        clearToMove=true; positionRow= rookPosition.getRow(); positionColumn= rookPosition.getColumn();
        newPosition.setColumn(positionColumn);
        while(clearToMove){
            positionRow--; if(positionRow<1) break;
            newPosition.setRow(positionRow); clearToMove= clearToMove(newPosition,color);
        }
        //CASTLING

    }
    private boolean clearToMove(ChessPosition newPosition, ChessGame.TeamColor color){
        ChessPosition endPosition = rookBoard.findBoardPosition(newPosition);
        if(rookBoard.getPiece(endPosition) == null){
            rookMoves.add(new ChessMoveImpl(rookPosition, endPosition, null)); return true;
        } else if(rookBoard.getPiece(endPosition).getTeamColor() != color){
            rookMoves.add(new ChessMoveImpl(rookPosition, endPosition, null)); return false;
        } else return false;
    }
    private boolean clearToCastle(ChessPosition newPosition, ChessGame.TeamColor color){
        ChessPosition endPosition = rookBoard.findBoardPosition(newPosition);
        if(rookBoard.getPiece(endPosition) == null){
            rookMoves.add(new ChessMoveImpl(rookPosition, endPosition, null)); return true;
        } else if(rookBoard.getPiece(endPosition).getTeamColor() != color){
            rookMoves.add(new ChessMoveImpl(rookPosition, endPosition, null)); return false;
        } else return false;
    }
}