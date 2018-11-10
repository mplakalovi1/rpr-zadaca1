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
    void movePawn3() { //kretnje crnog pjesaka (nedozvoljeno)
        Pawn p = new Pawn("E4", ChessPiece.Color.BLACK);
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    p.move("E5");
                    p.move("G7");
                    p.move("G8");
                }
        );
    }
    @Test
    void movePawn4() { //kretnje bijelog pjesaka (nedozvoljeno)
        Pawn p = new Pawn("E4", ChessPiece.Color.WHITE);
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    p.move("E5");
                    p.move("E6");
                    p.move("E5");
                }
        );
    }

    @Test
    void movePawn5() { //kretnje bijelog pjesaka (nedozvoljeno)
        Pawn p = new Pawn("E4", ChessPiece.Color.WHITE);
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    p.move("E5");
                    p.move("E6");
                    p.move("G8");
                }
        );
    }


}