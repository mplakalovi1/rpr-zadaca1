package ba.unsa.etf.rpr;


import java.util.HashSet;


public class Board {
    private HashSet<ChessPiece> aktivneFigure = new HashSet<>(); //ovdje cemo cuvati evidenciju o aktivnim figurama;


    public Board() { //postavljamo plocu u pocetno stanje za igru;
        for (int i = 0; i < 8; i++) {
            aktivneFigure.add(new Pawn((char) ('A' + i) + "2", ChessPiece.Color.WHITE)); //alokacija white pijuna;
            aktivneFigure.add(new Pawn((char) ('A' + i) + "7", ChessPiece.Color.BLACK));
        }

    }
}
