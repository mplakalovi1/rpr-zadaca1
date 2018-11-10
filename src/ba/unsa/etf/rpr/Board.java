package ba.unsa.etf.rpr;

import com.sun.source.tree.Tree;
import com.sun.source.tree.WhileLoopTree;

import java.util.ArrayList;
import java.util.Iterator;
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
        String pozicija = "";
        boolean pozivLegalan = false;

        for (ChessPiece figura : aktivneFigure) { //prolazimo kroz aktivne figure
            if (figura.getClass() == type && figura.getColor() == color) {   //ako se poklapaju tipovi i boja
                try {
                    figura.justCheck(position); //provjeravamo da li je potez Legalan;format i granice ploce
                    preskakanje(figura, position);//provjeravamo da li preskace nesto;
                    pozivLegalan = true;
                    pozicija = figura.getPosition();
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


        for (Iterator<ChessPiece> iterator = aktivneFigure.iterator(); iterator.hasNext(); ) {
            ChessPiece figura = iterator.next();
            if (figura.getPosition().toUpperCase().equals(position.toUpperCase())) {
                if (figura.getColor() == color) {
                    throw new IllegalChessMoveException();
                } else {
                    iterator.remove();
                }
            }
        }

        for (ChessPiece figura : aktivneFigure) { //prolazimo kroz aktivne figure
            if (figura.getPosition().toUpperCase().equals(pozicija.toUpperCase())) {   //trazimo figuru koju dokazano mozemo prebaciti
                figura.move(position); //METODOM MOVE PREBACUJEMO !!!
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
        boolean ima = false; //prep da na oldPosition nema nista,pokusajmo dokazati suprotno :P
        String pozicija = ""; //ovdje cemo cuvati poziciju figure koju mozda budemo mogli prebaciti;
        ChessPiece.Color boja = ChessPiece.Color.WHITE;

        for (ChessPiece figura : aktivneFigure) {
            if (figura.getPosition().toUpperCase().equals(oldPosition.toUpperCase())) {
                figura.justCheck(newPosition); //da li je poziv validan;
                preskakanje(figura, newPosition); //da li preskace nesto
                ima = true;
                pozicija = figura.getPosition();
                boja = figura.getColor();
            }
        }
        if (!ima) {
            throw new IllegalChessMoveException();//ako uopste nema nista na tom mjestu bacamo izuzetak;
        }
        for (Iterator<ChessPiece> iterator = aktivneFigure.iterator(); iterator.hasNext(); ) { //provjeravamo sta je na novoj poziciji,figura koje boje;
            ChessPiece figura = iterator.next();
            if (figura.getPosition().toUpperCase().equals(newPosition.toUpperCase())) {
                if (figura.getColor() == boja) { // ako je figura iste boje bacamo izuzetak , a ako nije remove-amo figuru koja je druge boje;
                    throw new IllegalChessMoveException();
                } else {
                    iterator.remove();
                }
            }
        }
        for (ChessPiece figura : aktivneFigure) { //prolazimo kroz aktivne figure
            if (figura.getPosition().toUpperCase().equals(pozicija.toUpperCase())) {   //trazimo figuru koju dokazano mozemo prebaciti
                figura.move(newPosition); //METODOM MOVE PREBACUJEMO !!!
            }
        }
    }


    public boolean isCheck(ChessPiece.Color color) {

        String pozicija = "";//pozicija kralja
        String kraljica = ""; //pozicija kraljice protivnika; analogno cemo i za ostale figure;
        ArrayList<String> topovi = new ArrayList<>();
        ArrayList<String> lovci = new ArrayList<>();
        ArrayList<String> skakaci = new ArrayList<>();
        ArrayList<String> pjesaci = new ArrayList<>();

        for (ChessPiece figura : aktivneFigure) { //prolazimo kroz aktivne figure da lociramo kralja date boje;
            if (figura instanceof King && figura.getColor() == color) { //nasli smo kralja date boje;
                pozicija = figura.getPosition().toUpperCase(); //sacuvajmo poziciju kralja;
            }
        }

        // BILJEZIMO GDJE SE KOJA FIGURA PROTIVNIKA NALAZI i biljeziti onu ciji je put do Kralja cist!!!;
        for (ChessPiece figura : aktivneFigure) {
            try {
                preskakanje(figura, pozicija);
                if (figura instanceof Queen && figura.getColor() != color) {
                    kraljica = figura.getPosition().toUpperCase();
                }
                if (figura instanceof Rook && figura.getColor() != color) {
                    topovi.add(figura.getPosition().toUpperCase());
                }
                if (figura instanceof Bishop && figura.getColor() != color) {
                    lovci.add(figura.getPosition().toUpperCase());
                }
                if (figura instanceof Knight && figura.getColor() != color) {
                    skakaci.add(figura.getPosition().toUpperCase());
                }
                if (figura instanceof Pawn && figura.getColor() != color) {
                    pjesaci.add(figura.getPosition().toUpperCase());
                }
            } catch (IllegalChessMoveException e) {
                //samo uhvati uredu je ;
            }
        }

        //SADA ZABILJEZIMO POZICIJE U ODGOVARAJUCIM SMJEROVIMA OKO KRALJA;

        ArrayList<String> uPlus = new ArrayList<>(); //ovo odgovara kraljici i topu;
        for (int i = 1; i < 8; i++) {
            uPlus.add((char) (pozicija.charAt(0) + i) + "" + pozicija.charAt(1));
            uPlus.add((char) (pozicija.charAt(0) - i) + "" + pozicija.charAt(1));
            uPlus.add(pozicija.charAt(0) + "" + (char) (pozicija.charAt(1) + i));
            uPlus.add(pozicija.charAt(0) + "" + (char) (pozicija.charAt(1) - i));
        }

        ArrayList<String> dijagonalno = new ArrayList<>(); //kraljica i lovac
        for (int i = -7; i < 8; i++) {
            if (i != 0) {
                dijagonalno.add((char) (pozicija.charAt(0) + i) + "" + (char) (pozicija.charAt(1) + i)); //sporedna dijagonala;
                dijagonalno.add((char) (pozicija.charAt(0) + i) + "" + (char) (pozicija.charAt(1) - i)); //sporedna dijagonala;
            }
        }

        //za skakaca
        ArrayList<String> uL = new ArrayList<>(); //skakacu odgovara
        uL.add((char) (pozicija.charAt(0) - 2) + "" + (char) (pozicija.charAt(1) + 1));
        uL.add((char) (pozicija.charAt(0) - 2) + "" + (char) (pozicija.charAt(1) - 1));
        uL.add((char) (pozicija.charAt(0) + 2) + "" + (char) (pozicija.charAt(1) + 1));
        uL.add((char) (pozicija.charAt(0) + 2) + "" + (char) (pozicija.charAt(1) - 1));
        uL.add((char) (pozicija.charAt(0) + 1) + "" + (char) (pozicija.charAt(1) + 2));
        uL.add((char) (pozicija.charAt(0) + 1) + "" + (char) (pozicija.charAt(1) - 2));
        uL.add((char) (pozicija.charAt(0) - 1) + "" + (char) (pozicija.charAt(1) + 2));
        uL.add((char) (pozicija.charAt(0) - 1) + "" + (char) (pozicija.charAt(1) - 2));
        //8 pozicija oko kralja

        //za pjesaka 4pozicije
        ArrayList<String> malaDijag = new ArrayList<>(); //odg pjesacima
        for (int i = -1; i < 2; i++) {
            if (i != 0) {
                malaDijag.add((char) (pozicija.charAt(0) + i) + "" + (char) (pozicija.charAt(1) + i)); //sporedna dijagonala;
                malaDijag.add((char) (pozicija.charAt(0) + i) + "" + (char) (pozicija.charAt(1) - i)); //sporedna dijagonala;
            }
        }

        //sada prolazimo kroz odg biljeske lokacija;

        for (String dijagonala : dijagonalno) {
            if (dijagonala.equals(kraljica)) {
                return true;
            }
            if (lovci.contains(dijagonala)) {
                return true;
            }
        }

        for (String x : uPlus) {
            if (x.equals(kraljica)) {
                return true;
            }
            if (topovi.contains(x)) {
                return true;
            }
        }

        for (String L : uL) {
            if (skakaci.contains(L)) {
                return true;
            }
        }

        for (String d : malaDijag) {
            if (pjesaci.contains(d)) {
                return true;
            }
        }
        return false;
    }

}