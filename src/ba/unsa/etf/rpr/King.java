package ba.unsa.etf.rpr;

import java.util.HashSet;

public class King extends ChessPiece {
    public King(String position, Color color) {
        super(position, color);
    }

    @Override
    public void move(String position) throws IllegalChessMoveException {
        super.move(position);
        checkKing(position);
        super.setPosition(position);
    }

    public void checkKing(String position) throws IllegalChessMoveException {
        position = position.toUpperCase();
        String pocetna = super.getPosition().toUpperCase();
        //Kralj se može pokretati svim smjerovima za jedno polje;
        HashSet<String> evidencija = new HashSet<>();
        for (int i = -1; i < 2; i++) { //sve moguce kretnje evidentiramo
            if (i != 0) {
                evidencija.add((char) (pocetna.charAt(0) + i) + "" + pocetna.charAt(1));
                evidencija.add(pocetna.charAt(0) + "" + (char) (pocetna.charAt(1) + i));
                evidencija.add((char) (pocetna.charAt(0) + i) + "" + (char) (pocetna.charAt(1) + i));
                evidencija.add((char) (pocetna.charAt(0) + i) + "" + (char) (pocetna.charAt(1) - i));
            }
        }
        if (!evidencija.contains(position)) {
            throw new IllegalChessMoveException();
        }
    }
}
