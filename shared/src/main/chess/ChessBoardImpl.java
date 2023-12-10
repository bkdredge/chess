package chess;

import chessStarter.*;

import java.util.Arrays;

/**
 * Custom implementation of the ChessBoard class.
 */
public class ChessBoardImpl implements ChessBoard {
    private ChessPositionImpl[][] boardPositions=new ChessPositionImpl[8][8];;
    public ChessBoardImpl(){
        boardPositions=new ChessPositionImpl[8][8];
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                boardPositions[i][j]=new ChessPositionImpl(j+1,i+1);
            }
        }
    }
    @Override public void addPiece(ChessPosition position, ChessPiece piece) {
        addPiece((ChessPositionImpl) position, (ChessPieceImpl) piece);
    }
    public void addPiece(ChessPositionImpl position, ChessPieceImpl piece) {
        ChessPositionImpl boardPosition=(ChessPositionImpl) findBoardPosition(position);
        boardPosition.setPieceOnPosition(piece);
    }
    public void boardMove(ChessMove move){boardMove((ChessMoveImpl) move);}
    public void boardMove(ChessMoveImpl move){
        ChessPieceImpl piece=(ChessPieceImpl)getPiece(move.getStartPosition());
        addPiece(move.getEndPosition(),piece);
        addPiece(move.getStartPosition(), null);
        if (move.getPromotionPiece() != null) piece.setPieceType(move.getPromotionPiece());
    }
    @Override public ChessPiece getPiece(ChessPosition position) {return getPiece((ChessPositionImpl) position);}
    public ChessPieceImpl getPiece(ChessPositionImpl position) {return (ChessPieceImpl)findBoardPosition(position).getPieceOnPosition();}
    public ChessPosition findBoardPosition(ChessPosition position){return findBoardPosition((ChessPositionImpl) position);}
    public ChessPositionImpl findBoardPosition(ChessPositionImpl position){
        return boardPositions[position.getRow()-1][position.getColumn()-1];
    }
    public void resetBoard() {
        //TEAM WHITE
        addPiece(boardPositions[0][0],new ChessPieceImpl(ChessPiece.PieceType.ROOK, ChessGame.TeamColor.WHITE));
        addPiece(boardPositions[0][7],new ChessPieceImpl(ChessPiece.PieceType.ROOK, ChessGame.TeamColor.WHITE));
        addPiece(boardPositions[0][1],new ChessPieceImpl(ChessPiece.PieceType.KNIGHT, ChessGame.TeamColor.WHITE));
        addPiece(boardPositions[0][6],new ChessPieceImpl(ChessPiece.PieceType.KNIGHT, ChessGame.TeamColor.WHITE));
        addPiece(boardPositions[0][2],new ChessPieceImpl(ChessPiece.PieceType.BISHOP, ChessGame.TeamColor.WHITE));
        addPiece(boardPositions[0][5],new ChessPieceImpl(ChessPiece.PieceType.BISHOP, ChessGame.TeamColor.WHITE));
        addPiece(boardPositions[0][3],new ChessPieceImpl(ChessPiece.PieceType.QUEEN, ChessGame.TeamColor.WHITE));
        addPiece(boardPositions[0][4],new ChessPieceImpl(ChessPiece.PieceType.KING, ChessGame.TeamColor.WHITE));
        for(int i=0;i<8;i++){
            addPiece(boardPositions[1][i],new ChessPieceImpl(ChessPiece.PieceType.PAWN, ChessGame.TeamColor.WHITE));
        }
        //TEAM BLACK
        addPiece(boardPositions[7][0],new ChessPieceImpl(ChessPiece.PieceType.ROOK, ChessGame.TeamColor.BLACK));
        addPiece(boardPositions[7][7],new ChessPieceImpl(ChessPiece.PieceType.ROOK, ChessGame.TeamColor.BLACK));
        addPiece(boardPositions[7][1],new ChessPieceImpl(ChessPiece.PieceType.KNIGHT, ChessGame.TeamColor.BLACK));
        addPiece(boardPositions[7][6],new ChessPieceImpl(ChessPiece.PieceType.KNIGHT, ChessGame.TeamColor.BLACK));
        addPiece(boardPositions[7][2],new ChessPieceImpl(ChessPiece.PieceType.BISHOP, ChessGame.TeamColor.BLACK));
        addPiece(boardPositions[7][5],new ChessPieceImpl(ChessPiece.PieceType.BISHOP, ChessGame.TeamColor.BLACK));
        addPiece(boardPositions[7][3],new ChessPieceImpl(ChessPiece.PieceType.QUEEN, ChessGame.TeamColor.BLACK));
        addPiece(boardPositions[7][4],new ChessPieceImpl(ChessPiece.PieceType.KING, ChessGame.TeamColor.BLACK));
        for(int i=0;i<8;i++){
            addPiece(boardPositions[6][i],new ChessPieceImpl(ChessPiece.PieceType.PAWN, ChessGame.TeamColor.BLACK));
        }
    }
    @Override public String toString() {
        return "ChessBoardImpl{" +
                "boardPositions=" + Arrays.toString(boardPositions) +
                '}';
    }
}