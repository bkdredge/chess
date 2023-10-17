package chess;

import java.util.Collection;
import java.util.HashSet;

/**
 * Implementation of rules for the Queen.
 */
public class ChessPieceImplRulesQueen implements ChessPieceImplRules {
    private Collection<ChessMove> queenMoves = new HashSet<>();
    private ChessBoardImpl queenBoard;
    private ChessPositionImpl queenPosition;
    @Override public Collection<ChessMove> populatePieceRules(ChessPosition position, ChessBoard board) {
        return populatePieceRules((ChessPositionImpl) position, (ChessBoardImpl) board);
    }
    public Collection<ChessMove> populatePieceRules(ChessPositionImpl position, ChessBoardImpl board) {
        queenMoves.clear(); queenBoard =(ChessBoardImpl)board; queenPosition = position;
        ChessPiece piece = queenBoard.getPiece(queenPosition);
        if(piece.getPieceType() != ChessPiece.PieceType.QUEEN){
            System.out.println("No queen found."); return null;
        }
        generateMoves(piece.getTeamColor()); return queenMoves;
    }
    private void generateMoves(ChessGame.TeamColor color){
        generateBishopMoves(color); generateRookMoves(color);
    }
    private void generateRookMoves(ChessGame.TeamColor color){
        boolean clearToMove=true;
        int positionRow= queenPosition.getRow(); int positionColumn= queenPosition.getColumn();
        ChessPositionImpl newPosition = new ChessPositionImpl(positionColumn, positionRow);
        //UP
        while(clearToMove){
            positionRow++;
            if(positionRow>8) break;
            newPosition.setRow(positionRow); clearToMove= clearToMove(newPosition,color);
        }
        //LEFT
        clearToMove=true; positionRow= queenPosition.getRow(); positionColumn= queenPosition.getColumn();
        newPosition.setRow(positionRow);
        while(clearToMove){
            positionColumn--;
            if(positionColumn<1) break;
            newPosition.setColumn(positionColumn); clearToMove= clearToMove(newPosition,color);
        }
        //DOWN
        clearToMove=true; positionRow= queenPosition.getRow(); positionColumn= queenPosition.getColumn();
        newPosition.setColumn(positionColumn);
        while(clearToMove){
            positionRow--;
            if(positionRow<1) break;
            newPosition.setRow(positionRow); clearToMove= clearToMove(newPosition,color);
        }
        //RIGHT
        clearToMove=true; positionRow= queenPosition.getRow(); positionColumn= queenPosition.getColumn();
        newPosition.setRow(positionRow);
        while(clearToMove){
            positionColumn++;
            if (positionColumn>8) break;
            newPosition.setColumn(positionColumn); clearToMove= clearToMove(newPosition, color);
        }
    }
    private void generateBishopMoves(ChessGame.TeamColor color){
        boolean clearToMove = true;
        int positionRow = queenPosition.getRow(); int positionColumn = queenPosition.getColumn();
        ChessPositionImpl newPosition = new ChessPositionImpl(positionColumn, positionRow);
        //RIGHT-UP
        while(clearToMove){
            positionRow++; positionColumn++;
            if (positionRow>8||positionColumn>8) break;
            newPosition.setRow(positionRow); newPosition.setColumn(positionColumn); clearToMove= clearToMove(newPosition, color);
        }
        //LEFT-UP
        clearToMove = true; positionRow = queenPosition.getRow(); positionColumn = queenPosition.getColumn();
        while(clearToMove){
            positionRow++; positionColumn--;
            if (positionRow>8||positionColumn<1) break;
            newPosition.setRow(positionRow);
            newPosition.setColumn(positionColumn);
            clearToMove = clearToMove(newPosition, color);
        }
        //LEFT-DOWN
        clearToMove = true; positionRow = queenPosition.getRow(); positionColumn = queenPosition.getColumn();
        while(clearToMove){
            positionRow--; positionColumn--;
            if (positionRow < 1 || positionColumn < 1) break;
            newPosition.setRow(positionRow); newPosition.setColumn(positionColumn);
            clearToMove = clearToMove(newPosition, color);
        }
        //RIGHT-DOWN
        clearToMove = true; positionRow = queenPosition.getRow(); positionColumn = queenPosition.getColumn();
        while(clearToMove){
            positionRow--; positionColumn++;
            if (positionRow< 1||positionColumn>8) break;
            newPosition.setRow(positionRow); newPosition.setColumn(positionColumn);
            clearToMove = clearToMove(newPosition, color);
        }
    }
    private boolean clearToMove(ChessPosition newPosition, ChessGame.TeamColor color){
        ChessPosition endPosition = queenBoard.findBoardPosition(newPosition);
        if(queenBoard.getPiece(endPosition) == null){
            queenMoves.add(new ChessMoveImpl(queenPosition, endPosition, null)); return true;
        } else if(queenBoard.getPiece(endPosition).getTeamColor() != color){
            queenMoves.add(new ChessMoveImpl(queenPosition, endPosition, null)); return false;
        }
        else return false;
    }
}