package ba.unsa.etf.rpr;

import java.util.TreeSet;


public class Board {
    private TreeSet<ChessPiece> aktivneFigure = new TreeSet<>(); //ovdje cemo cuvati evidenciju o aktivnim figurama;


    public Board() { //postavljamo plocu u pocetno stanje za igru;
        for (int i = 0; i < 8; i++) {
            aktivneFigure.add(new Pawn((char) ('A' + i) + "2", ChessPiece.Color.WHITE)); //alokacija white pijuna;
            aktivneFigure.add(new Pawn((char) ('A' + i) + "7", ChessPiece.Color.BLACK));
        }
        aktivneFigure.add(new Rook("A1", ChessPiece.Color.WHITE));
        aktivneFigure.add(new Rook("H1", ChessPiece.Color.WHITE));
        aktivneFigure.add(new Rook("A8", ChessPiece.Color.BLACK));
        aktivneFigure.add(new Rook("H8", ChessPiece.Color.BLACK));
        aktivneFigure.add(new Knight("B1", ChessPiece.Color.WHITE));
        aktivneFigure.add(new Knight("G1", ChessPiece.Color.WHITE));
        aktivneFigure.add(new Knight("B8", ChessPiece.Color.BLACK));
        aktivneFigure.add(new Knight("G8", ChessPiece.Color.BLACK));
        aktivneFigure.add(new Bishop("C1", ChessPiece.Color.WHITE));
        aktivneFigure.add(new Bishop("F1", ChessPiece.Color.WHITE));
        aktivneFigure.add(new Bishop("C8", ChessPiece.Color.BLACK));
        aktivneFigure.add(new Bishop("F8", ChessPiece.Color.BLACK));
        aktivneFigure.add(new Queen("D1", ChessPiece.Color.WHITE));
        aktivneFigure.add(new Queen("D8", ChessPiece.Color.BLACK));
        aktivneFigure.add(new King("E1", ChessPiece.Color.WHITE));
        aktivneFigure.add(new King("E8", ChessPiece.Color.BLACK));

    }

    public void move(Class type, ChessPiece.Color color, String position) {
        for (ChessPiece x : aktivneFigure) {//trazimo odg.figuru
            if (x.getClass() == type) {
                if (x.getColor() == color) {
                    x.move(position); // pomjeramo ga ;
                }
            }
        }

    }
}
