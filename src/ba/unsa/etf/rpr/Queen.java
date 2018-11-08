package ba.unsa.etf.rpr;

public class Queen extends ChessPiece {
    public Queen(String position, Color color) {
        super(position, color);
    }

    @Override
    public void move(String position) {
        this.checkQueen(position);
        super.move(position);
        super.setPosition(position);
    }

    public void checkQueen(String position) throws IllegalChessMoveException {
        position = position.toUpperCase();
        String pocetna = super.getPosition().toUpperCase();//prebacujemo pocetnu poziciju i zadanu u vrlika slova;

        boolean validno = false; //postavljamo na false prije provjere;
        //da li se Queen krece dijagonalno;
        for (int i = -7; i < 8; i++) {
            if (i != 0) {
                if (pocetna.charAt(0) + i == position.charAt(0) && pocetna.charAt(1) + i == position.charAt(1)) {
                    validno = true;
                    break;
                }
            }
        }

        if (position.charAt(0) != pocetna.charAt(0) && position.charAt(1) != pocetna.charAt(1) && !validno) {
            throw new IllegalChessMoveException();
        }

    }
}
