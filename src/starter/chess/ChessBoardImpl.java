package chess;

import java.util.Map;

public class ChessBoardImpl implements ChessBoard{
    private ChessPositionImpl[][] board = new ChessPositionImpl[8][8];
    public ChessBoardImpl() {
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                board[i][j] = new ChessPositionImpl(j+1,i+1);
            }
        }
    }
    @Override public void addPiece(ChessPosition position, ChessPiece piece) {
        if(board[position.getColumn()-1][position.getRow()-1].getPieceOnPosition()==null) {
            board[position.getColumn()-1][position.getRow()-1].setPieceOnPosition((ChessPieceImpl)piece);
        } else {
            System.out.println("Cannot add. Position is occupied.");
        }
    }
    @Override public ChessPiece getPiece(ChessPosition position) {
        return board[position.getColumn()-1][position.getRow()-1].getPieceOnPosition();
    }
    @Override public void resetBoard() {
        /*
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                board[i][j].setPieceOnPosition(null);
            }
        }

         */
        //TEAM WHITE
        addPiece(board[0][0],new ChessPieceImpl(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.ROOK));
        addPiece(board[1][0],new ChessPieceImpl(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.KNIGHT));
        addPiece(board[2][0],new ChessPieceImpl(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.BISHOP));
        addPiece(board[3][0],new ChessPieceImpl(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.QUEEN));
        addPiece(board[4][0],new ChessPieceImpl(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.KING));
        addPiece(board[5][0],new ChessPieceImpl(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.BISHOP));
        addPiece(board[6][0],new ChessPieceImpl(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.KNIGHT));
        addPiece(board[7][0],new ChessPieceImpl(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.ROOK));
        for (int i = 0; i < 8; i++){
            addPiece(board[i][1],new ChessPieceImpl(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.PAWN));
        }
        //TEAM BLACK
        addPiece(board[0][7],new ChessPieceImpl(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.ROOK));
        addPiece(board[1][7],new ChessPieceImpl(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.KNIGHT));
        addPiece(board[2][7],new ChessPieceImpl(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.BISHOP));
        addPiece(board[3][7],new ChessPieceImpl(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.QUEEN));
        addPiece(board[4][7],new ChessPieceImpl(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.KING));
        addPiece(board[5][7],new ChessPieceImpl(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.BISHOP));
        addPiece(board[6][7],new ChessPieceImpl(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.KNIGHT));
        addPiece(board[7][7],new ChessPieceImpl(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.ROOK));
        for (int i = 0; i < 8; i++){
            addPiece(board[i][6],new ChessPieceImpl(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.PAWN));
        }
    }
    //FIXME Will add a toString
}
