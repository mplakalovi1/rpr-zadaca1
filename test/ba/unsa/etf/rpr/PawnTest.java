package ba.unsa.etf.rpr;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {

    @org.junit.jupiter.api.Test
    void move1() {
        Pawn p = new Pawn("E2", ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> p.move("E4")
        );
    }

    @org.junit.jupiter.api.Test // Pawn eats diagonally //MOJ TEST
    void pawnDiagonal() {
        Pawn p = new Pawn("E2", ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> {
                    p.move("E4");
                    p.move("E5");
                    p.move("E6");
                    p.move("D7");
                    p.move("C8");
                }
        );
    }

}

