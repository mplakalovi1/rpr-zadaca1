package ba.unsa.etf.rpr;

public class Bishop extends ChessPiece {
    public Bishop(String position, Color color) {
        super(position, color);
    }

    @Override
    public void move(String position) {
        this.checkBishop(position);
        super.move(position);
        super.setPosition(position);
    }

    public void checkBishop(String position) throws IllegalChessMoveException{
        position = position.toUpperCase();//sve velika zbog tretmana za ista mala,i velika slova;
        String pocetna = super.getPosition().toUpperCase(); //pocetna pozicija figure,u ovom slucaju lovca;
        boolean validno = false; //postavljamo na false prije provjere;
        //da li se lovac krece dijagonalno;
        for (int i = -7; i < 8; i++) {
            if (i != 0) {
                if (pocetna.charAt(0) + i == position.charAt(0) && pocetna.charAt(1) + i == position.charAt(1)) {
                    validno = true;
                    break;
                }
            }
        }
        if (!validno) {
            throw new IllegalChessMoveException();
        }

    }
}
