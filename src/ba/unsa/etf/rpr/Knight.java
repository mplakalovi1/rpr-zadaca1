package ba.unsa.etf.rpr;

public class Knight extends ChessPiece { //skakač(konj)
    public Knight(String position, Color color) {
        super(position, color);
    }

    @Override
    public void move(String position) throws IllegalChessMoveException {
        super.move(position);
        checkKnight(position);
    }

    public void checkKnight(String position) throws IllegalChessMoveException {
        position = position.toUpperCase();
        String pocetna = super.getPosition().toUpperCase();//pocetna pozicija figure,u ovom slucaju skakača;
        boolean validno = false;

        if (position.charAt(0) == pocetna.charAt(0) - 2) {
            if (position.charAt(1) == pocetna.charAt(1) + 1 || position.charAt(1) == pocetna.charAt(1) - 1) {
                validno = true;
            }
        }
        if (position.charAt(0) == pocetna.charAt(0) - 1) {
            if (position.charAt(1) == pocetna.charAt(1) + 2 || position.charAt(1) == pocetna.charAt(1) - 2) {
                validno = true;
            }
        }
        if (position.charAt(0) == pocetna.charAt(0) + 1) {
            if (position.charAt(1) == pocetna.charAt(1) + 2 || position.charAt(1) == pocetna.charAt(1) - 2) {
                validno = true;
            }
        }
        if (position.charAt(0) == pocetna.charAt(0) + 2) {
            if (position.charAt(1) == pocetna.charAt(1) + 1 || position.charAt(1) == pocetna.charAt(1) - 1) {
                validno = true;
            }
        }
        if (!validno) {
            throw new IllegalChessMoveException();
        }

    }

}
