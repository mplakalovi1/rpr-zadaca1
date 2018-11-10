package ba.unsa.etf.rpr;

import java.util.HashSet;

public class Pawn extends ChessPiece {      //pješak;
    // atributi se nasljedjuju!!! i dovoljni su kao takvi;

    public Pawn(String position, Color color) { //konstruktor
        super(position, color); //pozivamo const bazne klase;
    }

    @Override
    public void move(String positon) throws IllegalChessMoveException { //dopunit cemo motodu move pogodno ovoj figuri;
        justCheck(positon);
        super.setPosition(positon);
    }

    @Override
    public void justCheck(String position) throws IllegalChessMoveException{
        super.move(position);
        checkPawn(position);
    }

    public void checkPawn(String position) throws IllegalChessMoveException { // f-ja kojom provjeravamo kretnje pješaka;
        position = position.toUpperCase();//prebacujemo u velika slova sve radi manje posla;
        String pocetna = super.getPosition().toUpperCase();
        HashSet<String> evidencija = new HashSet<>();

        evidencija.add(pocetna.charAt(0) + "" + (char) (pocetna.charAt(1) + 1));
        evidencija.add((char) (pocetna.charAt(0) + 1) + "" + (char) (pocetna.charAt(1) + 1));
        evidencija.add((char) (pocetna.charAt(0) - 1) + "" + (char) (pocetna.charAt(1) + 1));
        if (pocetna.charAt(1) == '2') {
            evidencija.add(pocetna.charAt(0) + "" + (char) (pocetna.charAt(1) + 2));
        }
        if (!evidencija.contains(position)) {
            throw new IllegalChessMoveException();
        }
    }
}
