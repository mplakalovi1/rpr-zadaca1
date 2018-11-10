package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BishopTest_mojTest {
    @Test
    void move1() {
        Bishop b = new Bishop("F2", ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> {
                    b.move("A7");
                    b.move("B8");
                    b.move("E5");
                    b.move("A1");
                }
        );
    }

    @Test
    void move2() {
        Bishop b = new Bishop("H8", ChessPiece.Color.BLACK);
        assertDoesNotThrow(
                () -> {
                    b.move("A1");
                    b.move("D4");
                    b.move("G7");
                }
        );
    }
    @Test
    void move3() {
        Bishop b= new Bishop("F2", ChessPiece.Color.BLACK);
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move("A7");
                    b.move("B8");
                    b.move("E5");
                    b.move("E1");
                }
        );
    }
    @Test
    void move4() {
        Bishop b= new Bishop("F2", ChessPiece.Color.BLACK);
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move("B6");
                    b.move("C8");
                    b.move("E6");
                    b.move("F7");
                }
        );
    }

}