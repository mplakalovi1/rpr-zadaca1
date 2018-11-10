package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KingTest_mojTest {
    @Test
    void move1() {
        King k= new King("C7", ChessPiece.Color.BLACK);
        assertDoesNotThrow(
                () -> {
                    k.move("D6");
                    k.move("E5");
                    k.move("F5");
                    k.move("F4");
                }
        );
    }
    @Test
    void move2() {
        King k= new King("B3", ChessPiece.Color.BLACK);
        assertDoesNotThrow(
                () -> {
                    k.move("C4");
                    k.move("C5");
                    k.move("C6");
                    k.move("C5");
                }
        );
    }
    @Test
    void move3() {
        King k= new King("C7", ChessPiece.Color.BLACK);
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    k.move("D6");
                    k.move("E5");
                    k.move("G7");
                    k.move("G8");
                }
        );
    }
    @Test
    void move4() {
        King k= new King("C7", ChessPiece.Color.BLACK);
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    k.move("D6");
                    k.move("E5");
                    k.move("C5");
                    k.move("D6");
                }
        );
    }
}