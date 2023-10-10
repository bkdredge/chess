package chess;

import java.util.Collection;
import java.util.HashSet;
public class ChessPieceImplRulesPawn implements ChessPieceImplRules {
    private Collection<ChessMove> pawnMoves = new HashSet<>();
    private ChessBoardImpl pawnBoard;
    private ChessPositionImpl pawnPosition;
    @Override public Collection<ChessMove> populatePieceRules(ChessPosition position, ChessBoard board) {
        return populatePieceRules((ChessPositionImpl) position, (ChessBoardImpl) board);
    }
    public Collection<ChessMove> populatePieceRules(ChessPositionImpl position, ChessBoardImpl board) {
        pawnMoves.clear(); pawnBoard =(ChessBoardImpl)board; pawnPosition =position;
        ChessPiece piece = pawnBoard.getPiece(pawnPosition);
        if(piece.getPieceType() != ChessPiece.PieceType.PAWN){
            System.out.println("No pawn found."); return null;
        }
        if(piece.getTeamColor() == ChessGame.TeamColor.WHITE) pawnMovesWhite();
        else if (piece.getTeamColor() == ChessGame.TeamColor.BLACK) pawnMovesBlack();
        return pawnMoves;
    }
    private void pawnMovesWhite(){
        if (pawnPosition.getRow() < 8){
            //MOVE ONE
            ChessPositionImpl newPosition = new ChessPositionImpl(pawnPosition.getColumn(), pawnPosition.getRow()+1);
            boolean clearToMove = clearToMove(newPosition);
            //CONQUER
            if (pawnPosition.getColumn() > 1){
                newPosition.setRow(pawnPosition.getRow()+1);
                newPosition.setColumn(pawnPosition.getColumn()-1); clearToConquer(newPosition, ChessGame.TeamColor.BLACK);
            }
            if (pawnPosition.getColumn() < 8){
                newPosition.setRow(pawnPosition.getRow()+1);
                newPosition.setColumn(pawnPosition.getColumn()+1); clearToConquer(newPosition, ChessGame.TeamColor.BLACK);
            }
            //FIRST MOVE
            if(pawnPosition.getRow() == 2 && clearToMove){
                newPosition.setRow(pawnPosition.getRow()+2);
                newPosition.setColumn(pawnPosition.getColumn()); clearToMove(newPosition);
            }
        }
    }
    private void pawnMovesBlack(){
        if (pawnPosition.getRow() > 1){
            ChessPositionImpl newPosition=new ChessPositionImpl(pawnPosition.getColumn(), pawnPosition.getRow()-1);
            boolean clearToMove = clearToMove(newPosition);
            if (pawnPosition.getColumn() > 1){
                newPosition.setRow(pawnPosition.getRow()-1);
                newPosition.setColumn(pawnPosition.getColumn()-1); clearToConquer(newPosition, ChessGame.TeamColor.WHITE);
            } if (pawnPosition.getColumn() < 8){
                newPosition.setRow(pawnPosition.getRow()-1);
                newPosition.setColumn(pawnPosition.getColumn()+1); clearToConquer(newPosition, ChessGame.TeamColor.WHITE);
            } if(pawnPosition.getRow() == 7 && clearToMove){
                newPosition.setRow(pawnPosition.getRow()-2);
                newPosition.setColumn(pawnPosition.getColumn()); clearToMove(newPosition);
            }
        }
    }
    private boolean clearToMove(ChessPosition newPosition){
        ChessPosition startPosition = pawnBoard.findBoardPosition(newPosition);
        if(pawnBoard.getPiece(startPosition)==null){
            if (startPosition.getRow()==8||startPosition.getRow()==1) pawnPromotion(startPosition);
            else pawnMoves.add(new ChessMoveImpl(pawnPosition, startPosition, null));
            return true;
        }
        else return false;
    }
    private void clearToConquer(ChessPosition newPosition, ChessGame.TeamColor enemyColor){
        ChessPosition startPosition = pawnBoard.findBoardPosition(newPosition);
        if(pawnBoard.getPiece(startPosition)!=null){
            if(pawnBoard.getPiece(startPosition).getTeamColor() == enemyColor) {
                if (startPosition.getRow()==8||startPosition.getRow()==1) pawnPromotion(startPosition);
                else pawnMoves.add(new ChessMoveImpl(pawnPosition, startPosition, null));
            }
        }
    }
    private void pawnPromotion(ChessPosition startPosition){
        pawnMoves.add(new ChessMoveImpl(pawnPosition, startPosition, ChessPiece.PieceType.ROOK));
        pawnMoves.add(new ChessMoveImpl(pawnPosition, startPosition, ChessPiece.PieceType.KNIGHT));
        pawnMoves.add(new ChessMoveImpl(pawnPosition, startPosition, ChessPiece.PieceType.QUEEN));
        pawnMoves.add(new ChessMoveImpl(pawnPosition, startPosition, ChessPiece.PieceType.BISHOP));
    }
}