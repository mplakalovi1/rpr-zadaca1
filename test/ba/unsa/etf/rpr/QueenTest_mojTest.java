package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest_mojTest {

    @Test
    void move1() { //dijagonalno
        Queen q = new Queen("D3", ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> {
                    q.move("F5");
                    q.move("G6");
                    q.move("H7");
                    q.move("G8");
                }
        );
    }

    @Test
    void move2() { //dijagonalno po svim uglovima
        Queen q = new Queen("A1", ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> {
                    q.move("D4");
                    q.move("G1");
                    q.move("H2");
                    q.move("D6");
                    q.move("F8");
                }
        );
    }

    @Test
    void move3() { //vertikalno i horizontalno
        Queen q = new Queen("A1", ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> {
                    q.move("D1");
                    q.move("G1");
                    q.move("G7");
                    q.move("A7");
                    q.move("A1");
                }
        );
    }

    @Test
    void move4() { //dijagonalno , vertikalno i horizontalno
        Queen q = new Queen("D5", ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> {
                    q.move("F7");
                    q.move("F1");
                    q.move("B5");
                    q.move("B7");
                    q.move("A7");
                }
        );
    }

    @Test
    void move5() {
        Queen q = new Queen("A1", ChessPiece.Color.BLACK);
        assertDoesNotThrow(
                () -> {
                    q.move("D4");
                    q.move("D1");
                    q.move("H5");
                    q.move("A5");
                    q.move("A1");
                }
        );
    }

}