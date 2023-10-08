import chess.ChessBoardImpl;
import chess.ChessPositionImpl;

class PrintLN {
    public static void main(String[] args)
    {
        ChessBoardImpl board=new ChessBoardImpl();
        board.resetBoard();
        System.out.println(board.toStringBoard()+"\n");
        board.movePiece(new ChessPositionImpl(1,1), new ChessPositionImpl(3,3));
        System.out.println(board.toStringBoard());
    }
}
