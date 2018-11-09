package ba.unsa.etf.rpr;

public class Queen extends ChessPiece {
    public Queen(String position, Color color) {
        super(position, color);
    }

    @Override
    public void move(String position) throws IllegalChessMoveException {
        super.move(position);
        checkQueen(position);
        super.setPosition(position);
    }

    public void checkQueen(String position) throws IllegalChessMoveException {
        position = position.toUpperCase();//sve velika zbog tretmana za ista mala,i velika slova;
        String pocetna = super.getPosition().toUpperCase();

        if (position.charAt(0) != pocetna.charAt(0) && position.charAt(1) != pocetna.charAt(1) && !Bishop.kretnjaPoDijagonali(position, super.getPosition())) {
            throw new IllegalChessMoveException();
        }

    }
}
