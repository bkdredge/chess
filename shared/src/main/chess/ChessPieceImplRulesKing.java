package chess;

import chessStarter.*;

import java.util.Collection;
import java.util.HashSet;

/**
 * Implementation of rules for the King.
 */
public class ChessPieceImplRulesKing implements ChessPieceImplRules {
    private Collection<ChessMove> kingMoves = new HashSet<>();
    private ChessBoardImpl kingBoard;
    private ChessPositionImpl kingPosition;
    @Override public Collection<ChessMove> populatePieceRules(ChessPosition position, ChessBoard board) {
        return populatePieceRules((ChessPositionImpl) position, (ChessBoardImpl) board);
    }
    public Collection<ChessMove> populatePieceRules(ChessPositionImpl position, ChessBoardImpl board) {
        kingMoves.clear(); kingBoard=board; kingPosition=position;
        ChessPiece piece = kingBoard.getPiece(kingPosition);
        if(piece.getPieceType() != ChessPiece.PieceType.KING){
            System.out.println("No king found."); return null;
        }
        generateMoves(piece.getTeamColor()); return kingMoves;
    }
    private void generateMoves(ChessGame.TeamColor color){
        ChessPositionImpl newPosition = new ChessPositionImpl(kingPosition.getColumn(), kingPosition.getRow());
        if (kingPosition.getRow() < 8){
            //UP
            newPosition.setRow(kingPosition.getRow() + 1); clearToMove(newPosition, color);
            //RIGHT-UP
            if(kingPosition.getColumn() < 8){
                newPosition.setColumn(kingPosition.getColumn() + 1); clearToMove(newPosition, color);
            }
            //LEFT-UP
            if(kingPosition.getColumn() > 1){
                newPosition.setColumn(kingPosition.getColumn() - 1); clearToMove(newPosition, color);
            }
        }
        if (kingPosition.getRow() > 1){
            //DOWN
            newPosition.setRow(kingPosition.getRow() - 1);
            newPosition.setColumn(kingPosition.getColumn()); clearToMove(newPosition, color);
            //RIGHT-DOWN
            if(kingPosition.getColumn() < 8){
                newPosition.setColumn(kingPosition.getColumn() + 1); clearToMove(newPosition, color);
            }
            //LEFT-DOWN
            if(kingPosition.getColumn() > 1){
                newPosition.setColumn(kingPosition.getColumn() - 1); clearToMove(newPosition, color);
            }
        }
        //LEFT
        if (kingPosition.getColumn() > 1){
            newPosition.setRow(kingPosition.getRow());
            newPosition.setColumn(kingPosition.getColumn() - 1); clearToMove(newPosition, color);
        }
        //RIGHT
        if (kingPosition.getColumn() < 8){
            newPosition.setRow(kingPosition.getRow());
            newPosition.setColumn(kingPosition.getColumn() + 1); clearToMove(newPosition, color);
        }
        //CASTLING
    }
    private void clearToMove(ChessPositionImpl newPosition, ChessGame.TeamColor color){
        ChessPosition endPosition = kingBoard.findBoardPosition(newPosition);
        if(kingBoard.getPiece(endPosition)==null){
            kingMoves.add(new ChessMoveImpl(kingPosition, endPosition, null));
        }  else if(kingBoard.getPiece(endPosition).getTeamColor()!=color){
            kingMoves.add(new ChessMoveImpl(kingPosition, endPosition, null));
        }
    }
    private boolean clearToCastle(ChessPosition newPosition, ChessGame.TeamColor color){
        ChessPosition endPosition = kingBoard.findBoardPosition(newPosition);
        if(kingBoard.getPiece(endPosition) == null){
            kingMoves.add(new ChessMoveImpl(kingPosition, endPosition, null)); return true;
        } else if(kingBoard.getPiece(endPosition).getTeamColor() != color){
            kingMoves.add(new ChessMoveImpl(kingPosition, endPosition, null)); return false;
        } else return false;
    }
}