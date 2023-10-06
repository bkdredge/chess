package chess;

public class ChessPositionImpl implements ChessPosition{
    private int row=0;
    private int column=0;
    private ChessPiece pieceOnPosition = null;
    public ChessPositionImpl(int columnInput, int rowInput) {column=columnInput; row=rowInput;}
    @Override public int getRow() {return row;}
    @Override public int getColumn() {return column;}
    public ChessPiece getPieceOnPosition() {return pieceOnPosition;}
    public void setRow(int rowInput) {row = rowInput;}
    public void setColumn(int columnInput) {column = columnInput;}
    public void setPieceOnPosition(ChessPiece pieceOnPositionInput) {pieceOnPosition = pieceOnPositionInput;}
    //FIXME Will add a toString
}
