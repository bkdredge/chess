package chess;

public class ChessPositionImpl implements ChessPosition{
    private int row=0;
    private int column=0;
    private ChessPieceImpl pieceOnPosition = null;
    public ChessPositionImpl(int columnInput, int rowInput) {column=columnInput; row=rowInput;}
    @Override public int getRow() {return row;}
    @Override public int getColumn() {return column;}
    public ChessPieceImpl getPieceOnPosition() {return pieceOnPosition;}
    public void setRow(int rowInput) {row = rowInput;}
    public void setColumn(int columnInput) {column = columnInput;}
    public void setPieceOnPosition(ChessPieceImpl pieceOnPositionInput) {pieceOnPosition = pieceOnPositionInput;}
    public void addMoveToPieceOnPosition(ChessMoveImpl move) {
        pieceOnPosition.addMoveToPiece(move);
    }
    public String toStringPosition() {
        StringBuilder out=new StringBuilder();
        if(pieceOnPosition!=null) {
            out.append(pieceOnPosition.toStringPiece());
        } else {
            out.append("NULL");
        }
        out.append("@("+row+","+column+")");
        return out.toString();
    }
    public String toStringPositionInMove() {
        StringBuilder out=new StringBuilder();
        out.append("("+row+","+column+")");
        return out.toString();
    }
}
