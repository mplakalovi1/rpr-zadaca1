package ba.unsa.etf.rpr;

public class Knight extends ChessPiece { //skakač(konj)
    public Knight(String position, Color color) {
        super(position, color);
    }

    @Override
    public void move(String position) {
        this.checkKnight(position);
        super.move(position);
        super.setPosition(position);
    }

    public void checkKnight(String position) {
        position = position.toUpperCase();
        String pocetna = super.getPosition();//pocetna pozicija figure,u ovom slucaju skakača;
        //
        if (position.charAt(0) != pocetna.charAt(0) - 2 && position.charAt(0) != pocetna.charAt(0) - 1 && position.charAt(0) != pocetna.charAt(0) + 1 && position.charAt(0) != pocetna.charAt(0) + 2) {
            throw new IllegalChessMoveException(); //jos nismo definisali ovaj izuzetak;
        } else {
            if (position.charAt(1) != pocetna.charAt(1) - 2 && position.charAt(1) != pocetna.charAt(1) - 1 && position.charAt(1) != pocetna.charAt(1) + 1 && position.charAt(1) != pocetna.charAt(1) + 2) {
                throw new IllegalChessMoveException();
            }
        }
    }

}
