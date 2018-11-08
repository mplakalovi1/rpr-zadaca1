package ba.unsa.etf.rpr;

public class Rook extends ChessPiece {
    public Rook(String position, Color color) {
        super(position, color);
    }

    @Override
    public void move(String position) {
        this.checkRook(position);
        super.move(position);
        super.setPosition(position);
    }

    public void checkRook(String position) throws IllegalChessMoveException {
        position = position.toUpperCase();
        String pocetna = super.getPosition().toUpperCase();//prebacujemo pocetnu poziciju i zadanu u vrlika slova;

        if (position.charAt(0) != pocetna.charAt(0) && position.charAt(1) != pocetna.charAt(1)) {
            throw new IllegalChessMoveException();
        }
    }
}
