package ba.unsa.etf.rpr;

public class Pawn extends ChessPiece {      //pješak;
    // atributi se nasljedjuju!!! i dovoljni su kao takvi;

    public Pawn(String position, Color color) { //konstruktor
        super(position, color); //pozivamo const bazne klase;
    }

    @Override
    public void move(String positon) throws IllegalChessMoveException { //dopunit cemo motodu move pogodno ovoj figuri;
        super.move(positon);
        checkPawn(positon);
    }

    public void checkPawn(String position) throws IllegalChessMoveException { // f-ja kojom provjeravamo kretnje pješaka;
        position = position.toUpperCase();//prebacujemo u velika slova sve radi manje posla;
        String pocetna = super.getPosition().toUpperCase();

        if (position.charAt(0) != pocetna.charAt(0)) {
            throw new IllegalChessMoveException(); //jos nismo definisali ovaj izuzetak;
        }
        if (position.charAt(1) != pocetna.charAt(1) + 1) {

            if (pocetna.charAt(1) == '2' && position.charAt(1) == '4') {
                //ne bacaj izuzetak i ne radi nista!!!
            } else {
                throw new IllegalChessMoveException();
            }

        }
    }
}
