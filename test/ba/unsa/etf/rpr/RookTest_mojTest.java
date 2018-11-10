package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RookTest_mojTest {

    @Test
    void move1() {
        Rook r = new Rook("A1", ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> {
                    r.move("A4");
                    r.move("B4");
                    r.move("B7");
                    r.move("H7");
                }
        );
    }

    @Test
    void move2() { //gore dole desno lijevo
        Rook r = new Rook("D4", ChessPiece.Color.BLACK);
        assertDoesNotThrow(
                () -> {
                    r.move("D6");
                    r.move("G6");
                    r.move("G4");
                    r.move("A4");
                }
        );
    }

    @Test
    void move3() { //dijagonalno
        Rook r = new Rook("D3", ChessPiece.Color.WHITE);
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    r.move("F3");
                    r.move("G5");
                    r.move("G7");
                    r.move("A7");
                }
        );
    }

    @Test
    void move4() {//kao skakac
        Rook r = new Rook("E3", ChessPiece.Color.WHITE);
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    r.move("A3");
                    r.move("B5");
                    r.move("B7");
                    r.move("C7");
                }
        );
    }

}