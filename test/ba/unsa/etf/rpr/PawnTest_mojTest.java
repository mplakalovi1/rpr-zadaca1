package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest_mojTest {

    @Test
    void movePawn1() { //kretnje bijelog pjesaka
        Pawn p = new Pawn("A2", ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> {
                    p.move("A4");
                    p.move("A5");
                    p.move("A6");
                }
        );
    }
    @Test
    void movePawn2() { //kretnje crnog pjesaka
        Pawn p = new Pawn("A7", ChessPiece.Color.BLACK);
        assertDoesNotThrow(
                () -> {
                    p.move("A5");
                    p.move("A4");
                    p.move("A3");
                }
        );
    }

    @Test
    void justCheck() {
    }

    @Test
    void checkPawn() {
    }
}