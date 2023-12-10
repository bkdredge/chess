package chess;

import chessStarter.*;

import java.util.Collection;
import java.util.HashSet;

/**
 * Implementation of rules for the Pawn.
 */
public class ChessPieceImplRulesPawn implements ChessPieceImplRules {
    private Collection<ChessMove> pawnMoves = new HashSet<>();
    private ChessBoardImpl pawnBoard;
    private ChessPositionImpl pawnPosition;
    public boolean enPassantVictimPotential=false;
    public boolean enPassantVictorPotential=false;
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
        if (pawnPosition.getRow()<8){
            if(enPassantVictorPotential) this.enPassantVictorPotential=false;
            if(enPassantVictimPotential) this.enPassantVictimPotential=false;
            //MOVE ONE
            ChessPositionImpl newPosition = new ChessPositionImpl(pawnPosition.getColumn(), pawnPosition.getRow()+1);
            boolean clearToMove = clearToMove(newPosition);
            //CONQUER
            if (pawnPosition.getColumn()>1){
                newPosition.setRow(pawnPosition.getRow()+1);
                newPosition.setColumn(pawnPosition.getColumn()-1); clearToConquer(newPosition, ChessGame.TeamColor.BLACK);
            }
            if (pawnPosition.getColumn()<8){
                newPosition.setRow(pawnPosition.getRow()+1);
                newPosition.setColumn(pawnPosition.getColumn()+1); clearToConquer(newPosition, ChessGame.TeamColor.BLACK);
            }
            //FIRST MOVE
            if(pawnPosition.getRow()==2&&clearToMove){
                newPosition.setRow(pawnPosition.getRow()+2);
                newPosition.setColumn(pawnPosition.getColumn()); clearToMove(newPosition);
            }
            pawnMovesWhiteEnPassant();
        }
    }
    private void pawnMovesWhiteEnPassant(){
        //EN PASSANT
        //The capturing pawn must have advanced exactly three ranks to perform this move.
        //The captured pawn must have moved two squares in one move, landing right next to the capturing pawn.
        //The en passant capture must be performed on the turn immediately after the pawn being captured moves.
        // If the player does not capture en passant on that turn, they no longer can do it later
        if(pawnPosition.getRow()==4){
            enPassantVictimPotential=true;
        }
        if(pawnPosition.getRow()==5){
            ChessPositionImpl victimPositionRight=pawnBoard.findBoardPosition(new ChessPositionImpl(
                    pawnPosition.getColumn()+1, pawnPosition.getRow()));

            if(pawnBoard.getPiece(victimPositionRight).getPieceType()==ChessPiece.PieceType.PAWN) {
                ChessPieceImplRulesPawn victimRulesRight=
                        (ChessPieceImplRulesPawn) pawnBoard.getPiece(victimPositionRight).pieceRules;
                if(victimRulesRight.enPassantVictimPotential &&
                        victimPositionRight.getPieceOnPosition().getTeamColor()==ChessGame.TeamColor.BLACK) {
                    ChessPositionImpl newPosition=
                            new ChessPositionImpl(pawnPosition.getColumn(), pawnPosition.getRow()+1);
                    newPosition.setRow(pawnPosition.getRow()+1);
                    newPosition.setColumn(pawnPosition.getColumn()+1);
                    clearToEnPassant(newPosition,ChessGame.TeamColor.BLACK);
                    enPassantVictorPotential=true;
                }
            }
            ChessPositionImpl victimPositionLeft=pawnBoard.findBoardPosition(new ChessPositionImpl(
                    pawnPosition.getColumn()-1, pawnPosition.getRow()));

        }
    }
    private void pawnMovesBlack(){
        if(enPassantVictorPotential) this.enPassantVictorPotential=false;
        if(enPassantVictimPotential) this.enPassantVictimPotential=false;
        if (pawnPosition.getRow()>1){
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
        ChessPosition endPosition = pawnBoard.findBoardPosition(newPosition);
        if(pawnBoard.getPiece(endPosition)==null){
            if (endPosition.getRow()==8||endPosition.getRow()==1) pawnPromotion(endPosition);
            else pawnMoves.add(new ChessMoveImpl(pawnPosition, endPosition, null));
            return true;
        }
        else return false;
    }
    private void clearToConquer(ChessPosition newPosition, ChessGame.TeamColor opponentColor){
        ChessPosition conquerPosition = pawnBoard.findBoardPosition(newPosition);
        if(pawnBoard.getPiece(conquerPosition)!=null){
            if(pawnBoard.getPiece(conquerPosition).getTeamColor() == opponentColor) {
                if (conquerPosition.getRow()==8||conquerPosition.getRow()==1) pawnPromotion(conquerPosition);
                else pawnMoves.add(new ChessMoveImpl(pawnPosition, conquerPosition, null));
            }
        }
    }
    private void clearToEnPassant(ChessPosition newPosition, ChessGame.TeamColor opponentColor) {
        ChessPosition conquerPosition = pawnBoard.findBoardPosition(newPosition);
    }
    private void pawnPromotion(ChessPosition startPosition){
        pawnMoves.add(new ChessMoveImpl(pawnPosition, startPosition, ChessPiece.PieceType.ROOK));
        pawnMoves.add(new ChessMoveImpl(pawnPosition, startPosition, ChessPiece.PieceType.KNIGHT));
        pawnMoves.add(new ChessMoveImpl(pawnPosition, startPosition, ChessPiece.PieceType.QUEEN));
        pawnMoves.add(new ChessMoveImpl(pawnPosition, startPosition, ChessPiece.PieceType.BISHOP));
    }
}