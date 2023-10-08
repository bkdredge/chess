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
        //System.out.println(toStringBoard());
    }
    @Override public void addPiece(ChessPosition position, ChessPiece piece) {
        addPiece((ChessPositionImpl) position, (ChessPieceImpl) piece);
    }
    public void addPiece(ChessPositionImpl position, ChessPieceImpl piece) {
        if(board[position.getColumn()-1][position.getRow()-1].getPieceOnPosition()==null) {
            board[position.getColumn()-1][position.getRow()-1].setPieceOnPosition((ChessPieceImpl)piece);
        } else {
            System.out.println("Cannot add. Position is occupied.");
        }
    }
    @Override public ChessPieceImpl getPiece(ChessPosition position) {
        return board[position.getColumn()-1][position.getRow()-1].getPieceOnPosition();
    }
    public void movePiece(ChessMoveImpl move) {
        ChessPieceImpl piece=getPiece(move.getStartPosition());
        addPiece(move.getEndPosition(),piece);
        addPiece(move.getStartPosition(),null);
        updateMoves();
    }
    public void addMoveToPieceOnBoard(ChessMoveImpl move) {
        board[move.getStartPosition().getColumn()-1][move.getStartPosition().getRow()-1].addMoveToPieceOnPosition(move);
    }
    @Override public void resetBoard() {
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
        updateMoves();
    }

    public void updateMoves() {
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                if(board[i][j].getPieceOnPosition()!=null) {
                    if (board[i][j].getPieceOnPosition().getPieceType()==ChessPiece.PieceType.PAWN) {
                        updatePawn(board[i][j]);
                    } else if (board[i][j].getPieceOnPosition().getPieceType()==ChessPiece.PieceType.KNIGHT) {
                        updateKnight(board[i][j]);
                    } else if (board[i][j].getPieceOnPosition().getPieceType()==ChessPiece.PieceType.BISHOP) {
                        updateBishop(board[i][j]);
                    } else if (board[i][j].getPieceOnPosition().getPieceType()==ChessPiece.PieceType.ROOK) {
                        updateRook(board[i][j]);
                    } else if (board[i][j].getPieceOnPosition().getPieceType()==ChessPiece.PieceType.KING) {
                        updateKing(board[i][j]);
                    } else if (board[i][j].getPieceOnPosition().getPieceType()==ChessPiece.PieceType.QUEEN) {
                        updateQueen(board[i][j]);
                    } else if (board[i][j].getPieceOnPosition().getPieceType()==null) {
                        System.out.println("NOPE");
                    }
                }
            }
        }
    }

    private void updatePawn(ChessPositionImpl chessPosition) {
        //System.out.println("PAWN UPDATED");
        System.out.println(chessPosition.getPieceOnPosition().toStringPiece()+"-updated");
    }
    private void updateKnight(ChessPositionImpl chessPosition) {
        //System.out.println("KNIGHT UPDATED");
        System.out.println(chessPosition.getPieceOnPosition().toStringPiece()+"-updated");
    }
    private void updateBishop(ChessPositionImpl chessPosition) {
        //System.out.println("BISHOP UPDATED");
        System.out.println(chessPosition.getPieceOnPosition().toStringPiece()+"-updated");
    }
    private void updateRook(ChessPositionImpl chessPosition) {
        ChessGame.TeamColor thisColor = getPiece(chessPosition).getTeamColor();
        int column = chessPosition.getColumn() - 1;
        int row = chessPosition.getRow() - 1;

        getPiece(chessPosition).pieceMoves(this,chessPosition);
        //addMoveToPieceOnBoard(new ChessMoveImpl(board[column][row], board[column][3], null));
        //System.out.println("ROOK UPDATED");
        System.out.println(chessPosition.getPieceOnPosition().toStringPiece()+"-updated");
    }


    private void updateQueen(ChessPositionImpl chessPosition) {
        //System.out.println("QUEEN UPDATED");
        System.out.println(chessPosition.getPieceOnPosition().toStringPiece()+"-updated");
    }
    private void updateKing(ChessPositionImpl chessPosition) {
        //System.out.println("KING UPDATED");
        System.out.println(chessPosition.getPieceOnPosition().toStringPiece()+"-updated");
    }

    public String toStringBoard() {
        StringBuilder out=new StringBuilder();
        for(int i=0;i<8;i++) {
            for(int j=0;j<8;j++) {
                if(board[i][j].getPieceOnPosition()!=null) {
                    out.append(board[i][j].toStringPosition() + "\n");
                }
            }
        }
        return out.toString();
    }
}
