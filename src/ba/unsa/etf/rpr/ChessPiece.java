package ba.unsa.etf.rpr;


public abstract class ChessPiece { //apstraktna klasa Sahovskih figura;
    private String position;
    private Color color;

    public static enum Color { //pobrojani tip Color ; // static je suvisno,ovdje jer se podrazumjeva ;
        BLACK, WHITE;
    }

    public ChessPiece(String position, Color color) { //konstruktor
        check(position);//odg provjera;
        this.position = position;
        this.color = color;
    }

    public String getPosition() { //getter za positon
        return position;
    }

    public Color getColor() { //getter za boju
        return color;
    }

    public void setPosition(String position){
        this.position=position;
    }

    public void move(String position) {
        check(position);//provjere ,ako treba bacit ce izuzetak;
    }

    public static void check(String position) { //pomocna f-ja za provjeru ispravnosti formata i pozicije figure;
        position = position.toUpperCase();  //prebacujemo u velika slova sve zbog manjeg posla;

        if (position.charAt(0) > 'H' || position.charAt(0) < 'A' || position.charAt(1) > '8' || position.charAt(1) < '1' || position.length() != 2) {
            throw new IllegalArgumentException(); //provjeravamo da li je pozicija validna,ako nije bacamo izuzetak;
        }
    }
}
