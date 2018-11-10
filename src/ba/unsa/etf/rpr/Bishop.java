package ba.unsa.etf.rpr;

public class Bishop extends ChessPiece {
    public Bishop(String position, Color color) {
        super(position, color);
    }

    @Override
    public void move(String position) throws IllegalChessMoveException {
        justCheck(position);
        super.setPosition(position);
    }
    @Override
    public void justCheck(String position) throws IllegalChessMoveException {
        super.move(position);
        checkBishop(position);
    }

    public void checkBishop(String position) throws IllegalChessMoveException {
        if (!kretnjaPoDijagonali(position, super.getPosition())) {
            throw new IllegalChessMoveException();
        }
    }

    public static boolean kretnjaPoDijagonali(String position, String pocetna) {
        position = position.toUpperCase();//sve velika zbog tretmana za ista mala,i velika slova;
        pocetna = pocetna.toUpperCase(); //pocetna pozicija figure,u ovom slucaju lovca;

        boolean moveBishop = false; //postavljamo na false prije provjere;

        for (int i = -7; i < 8; i++) { //testiramo da li se krece po dijagonali ???
            if (i != 0) {

                if (position.charAt(0) == pocetna.charAt(0) + i && position.charAt(1) == pocetna.charAt(1) + i) {
                    moveBishop = true;
                } else if (position.charAt(0) == pocetna.charAt(0) + i && position.charAt(1) == pocetna.charAt(1) - i) {
                    moveBishop = true;
                }
            }
        }
        return moveBishop;
    }
}
