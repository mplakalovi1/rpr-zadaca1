package ba.unsa.etf.rpr;

public class Pawn extends ChessPiece {      //pješak;
    // atributi se nasljedjuju!!! i dovoljni su kao takvi;

    public Pawn(String position, Color color) { //konstruktor
        super(position,color); //pozivamo const bazne klase;
    }

    @Override
    public void move(String positon) { //dopunit cemo motodu move pogodno ovoj figuri;

        this.checkPawn(positon);//provjeramo da li je potez dozvoljen za odg. figuru;
        super.move(positon); //zatim pozivamo metodu iz bazne klase da provjerimo korektnost formata i pozicije;
        super.setPosition(positon); //tek tada pomjeramo pješaka!!!
    }

    public void checkPawn(String position) { // f-ja kojom provjeravamo kretnje pješaka;     //OVDJE JOS VIDJETI DA LI SE MOGU POSITION DEFAULTNO CUVATI KAO VELIKA SLOVA ILI CU OVDJE I TO PRETVORITIT U VELIKA SLOVA!!!
        position = position.toUpperCase();//prebacujemo u velika slova sve radi manje posla;

        if (position.charAt(0) != super.getPosition().charAt(0)) {
            throw new IllegalChessMoveException(); //jos nismo definisali ovaj izuzetak;
        }
        if (position.charAt(1) != super.getPosition().charAt(1) + 1) {

            if (super.getPosition().charAt(1) == '2' && position.charAt(1) == '2' + 2) {
                //ne bacaj izuzetak i ne radi nista!!!
            } else {
                throw new IllegalChessMoveException();
            }
        }

    }
}
