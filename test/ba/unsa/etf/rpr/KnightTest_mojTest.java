package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest_mojTest {

    @Test
    void move1() {
        Knight k = new Knight("C7", ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> {
                    k.move("D5");
                    k.move("E7");
                    k.move("F5");
                    k.move("G3");
                }
        );
    }

    @Test
    void move2() {
        Knight k = new Knight("A1", ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> {
                    k.move("C2");
                    k.move("D4");
                    k.move("F3");
                    k.move("H4");
                }
        );
    }

    @Test
    void move3() { //dijagonalno
        Knight k = new Knight("C7", ChessPiece.Color.WHITE);
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    k.move("D5");
                    k.move("E7");
                    k.move("F5");
                    k.move("H7");
                }
        );
    }
}