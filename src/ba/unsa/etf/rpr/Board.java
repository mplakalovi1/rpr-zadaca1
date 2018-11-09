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

    public void move(Class type, ChessPiece.Color color, String position) throws IllegalChessMoveException {

        boolean pozivLegalan = false;

        for (ChessPiece figura : aktivneFigure) { //prolazimo kroz aktivne figure
            if (figura.getClass() == type && figura.getColor() == color) {   //ako se poklapaju tipovi i boja
                try {
                    figura.move(position); //provjeravamo da li je potez Legalan;format i granice ploce
                    preskakanje(figura, position);//provjeravamo da li preskace nesto;
                    pozivLegalan = true;
                    break;
                } catch (Exception e) {
                    //dovoljno je da uhvati samo izuzetak; }
                }
            }
        }
        if (!pozivLegalan) { //ako nema figure odgovarajuce ili ovaj potez nije validan za niti jednu od njoh bacamo izuzetak;
            throw new IllegalChessMoveException();
        }
        //prethodno smo se uvjerili da je potez validan i da postoji odgovarajuca figura za ovaj potez, sada provjerimo sta se nalazi na trazenoj lokaciji,ako se nalazi;

        for (ChessPiece figura : aktivneFigure) { //trazimo da li na odredisnoj poziciji ima figure i u zavisnosti od njene boje radimo odgovarajuce stvari;
            if (figura.getPosition().toUpperCase().equals(position.toUpperCase())) {
                if (figura.getColor() == color) {
                    throw new IllegalChessMoveException(); //ako na odredistu imamo figuru iste boje
                } else {
                    aktivneFigure.remove(figura); //sklanjamo tu figuru koja je druge boje;
                }
            }
        }
        for (ChessPiece figura : aktivneFigure) { //prolazimo kroz aktivne figure opet kako bi figuri odg promijenili lokaciju
            if (figura.getClass() == type && figura.getColor() == color) {
                figura.setPosition(position);
            }
        }

    }

    public void preskakanje(ChessPiece figura, String odrediste) throws IllegalChessMoveException {
        boolean preskok = false;

        if (figura instanceof Pawn) {//ako je pješak provjeravamo ima li preskakanja, a ono je moguce samo ako je to prvi potez kada moze naprijed za 2 mjesta;
            int pomocna = odrediste.charAt(1) - figura.getPosition().charAt(1);
            if (pomocna == -2) {
                preskok = daLiJePozicijaZauzeta(figura.getPosition().charAt(0) + "" + (char) (figura.getPosition().charAt(1) - 1));
            }
            if (pomocna == 2) {
                preskok = daLiJePozicijaZauzeta(figura.getPosition().charAt(0) + "" + (char) (figura.getPosition().charAt(1) + 1));
            }
        }

        if (figura instanceof Rook) {
            int pom1 = odrediste.charAt(0) - figura.getPosition().charAt(0);
            int pom2 = odrediste.charAt(1) - figura.getPosition().charAt(1);

            if (pom2 > 1) { //krece se vert prema gore
                for (int i = 1; i < pom2; i++) {
                    preskok = daLiJePozicijaZauzeta(figura.getPosition().charAt(0) + "" + (char) (figura.getPosition().charAt(1) + i));
                    if (preskok) {
                        break;
                    }
                }
            }
            if (pom2 < -1) { //krece se vert prema dolee
                for (int i = -1; i > pom2; i--) {
                    preskok = daLiJePozicijaZauzeta(figura.getPosition().charAt(0) + "" + (char) (figura.getPosition().charAt(1) + i));
                    if (preskok) {
                        break;
                    }
                }
            }
            if (pom1 > 1) { //krece se horiz prema desno
                for (int i = 1; i < pom1; i++) {
                    preskok = daLiJePozicijaZauzeta((char) (figura.getPosition().charAt(0) + i) + "" + figura.getPosition().charAt(1));
                    if (preskok) {
                        break;
                    }
                }
            }
            if (pom1 < -1) { //krece se horz prema lijevo
                for (int i = -1; i > pom1; i--) {
                    preskok = daLiJePozicijaZauzeta((char) (figura.getPosition().charAt(0) + i) + "" + figura.getPosition().charAt(1));
                    if (preskok) {
                        break;
                    }
                }
            }
        }

        if (figura instanceof Bishop) {
            int pom1 = odrediste.charAt(0) - figura.getPosition().charAt(0);
            int pom2 = odrediste.charAt(1) - figura.getPosition().charAt(1);

            if (pom1 > 0 && pom2 > 0) { //ako se krece dijagonalno po 45 stepeni
                for (int i = 1; i < pom1; i++) {
                    preskok = daLiJePozicijaZauzeta((char) (figura.getPosition().charAt(0) + i) + "" + (char) (figura.getPosition().charAt(1) + i));
                    if (preskok) {
                        break;
                    }
                }
            }
            if (pom1 < 0 && pom2 < 0) { //ako se krece dijagonalno po -45 stepeni
                for (int i = -1; i > pom1; i--) {
                    preskok = daLiJePozicijaZauzeta((char) (figura.getPosition().charAt(0) + i) + "" + (char) (figura.getPosition().charAt(1) + i));
                    if (preskok) {
                        break;
                    }
                }
            }
            if (pom1 > 0 && pom2 < 0) { //ako se krece dijagonalno po 135 stepeni
                for (int i = 1; i < pom1; i++) {
                    preskok = daLiJePozicijaZauzeta((char) (figura.getPosition().charAt(0) + i) + "" + (char) (figura.getPosition().charAt(1) - i));
                    if (preskok) {
                        break;
                    }
                }
            }
            if (pom1 < 0 && pom2 > 0) { //ako se krece dijagonalno po 225 stepeni
                for (int i = 1; i < pom2; i++) {
                    preskok = daLiJePozicijaZauzeta((char) (figura.getPosition().charAt(0) - i) + "" + (char) (figura.getPosition().charAt(1) + i));
                    if (preskok) {
                        break;
                    }
                }
            }


        }

        if (figura instanceof Queen) {
            int pom1 = odrediste.charAt(0) - figura.getPosition().charAt(0);
            int pom2 = odrediste.charAt(1) - figura.getPosition().charAt(1);

            if (pom1 == 0 && pom2 > 0) { //ako se krece vertikalno prema gore
                for (int i = 1; i < pom2; i++) {
                    preskok = daLiJePozicijaZauzeta(figura.getPosition().charAt(0) + "" + (char) (figura.getPosition().charAt(1) + i));
                    if (preskok) {
                        break;
                    }
                }
            }
            if (pom1 == 0 && pom2 < 0) { //ako se krece vertikalno prema dole
                for (int i = -1; i > pom2; i--) {
                    preskok = daLiJePozicijaZauzeta(figura.getPosition().charAt(0) + "" + (char) (figura.getPosition().charAt(1) + i));
                    if (preskok) {
                        break;
                    }
                }
            }
            if (pom1 > 0 && pom2 > 0) { //ako se krece dijagonalno po 45 stepeni
                for (int i = 1; i < pom2; i++) {
                    preskok = daLiJePozicijaZauzeta((char) (figura.getPosition().charAt(0) + i) + "" + (char) (figura.getPosition().charAt(1) + i));
                    if (preskok) {
                        break;
                    }
                }
            }
            if (pom1 < 0 && pom2 < 0) { //ako se krece dijagonalno po 225 stepeni
                for (int i = -1; i > pom2; i--) {
                    preskok = daLiJePozicijaZauzeta((char) (figura.getPosition().charAt(0) + i) + "" + (char) (figura.getPosition().charAt(1) + i));
                    if (preskok) {
                        break;
                    }
                }
            }
            if (pom1 > 0 && pom2 < 0) { //ako se krece dijagonalno po -45 stepeni
                for (int i = 1; i < pom1; i++) {
                    preskok = daLiJePozicijaZauzeta((char) (figura.getPosition().charAt(0) + i) + "" + (char) (figura.getPosition().charAt(1) - i));
                    if (preskok) {
                        break;
                    }
                }
            }
            if (pom1 < 0 && pom2 > 0) { //ako se krece dijagonalno po 135 stepeni
                for (int i = 1; i < pom2; i++) {
                    preskok = daLiJePozicijaZauzeta((char) (figura.getPosition().charAt(0) - i) + "" + (char) (figura.getPosition().charAt(1) + i));
                    if (preskok) {
                        break;
                    }
                }
            }
            if (pom1 > 0 && pom2 == 0) { //ako se krece horizontalno udesno
                for (int i = 1; i < pom1; i++) {
                    preskok = daLiJePozicijaZauzeta((char) (figura.getPosition().charAt(0) + i) + "" + figura.getPosition().charAt(1));
                    if (preskok) {
                        break;
                    }
                }
            }
            if (pom1 < 0 && pom2 == 0) { //ako se krece horizontalno ulijevo
                for (int i = -1; i > pom1; i--) {
                    preskok = daLiJePozicijaZauzeta((char) (figura.getPosition().charAt(0) + i) + "" + figura.getPosition().charAt(1));
                    if (preskok) {
                        break;
                    }
                }
            }
        }
        if (preskok) {
            throw new IllegalChessMoveException();
        }
    }


    public boolean daLiJePozicijaZauzeta(String s) {
        for (ChessPiece figura : aktivneFigure) {
            if (figura.getPosition().toUpperCase().equals(s.toUpperCase())) {
                return true;
            }
        }
        return false;

    }

    public void move(String oldPosition, String newPosition) throws IllegalChessMoveException {
        boolean ima = false; //pretp. da je prazno to mjesto
        for (ChessPiece figura : aktivneFigure) {
            if (figura.getPosition().toUpperCase().equals(oldPosition.toUpperCase())) {
                move(figura.getClass(), figura.getColor(), newPosition);
                ima = true;
            }
        }
        if (!ima) { //ako je zaista prazno bacamo izuzetak, jer nemamo koju figuru pomjeriti;
            throw new IllegalChessMoveException();
        }
    }

    public boolean isCheck(ChessPiece.Color color) {
        boolean imaIgraca = false;
        for (ChessPiece figura : aktivneFigure) {
            if (figura.getColor() == color) {
                imaIgraca = true;
            }
        }
        return imaIgraca;
    }

}