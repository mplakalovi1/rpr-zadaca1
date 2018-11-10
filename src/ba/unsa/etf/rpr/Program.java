package ba.unsa.etf.rpr;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Board board = new Board();
        String unos = "";
        Scanner ulaz = new Scanner(System.in);
        int i = 0;

        for (; ; ) {
            try {

                if (board.isCheck(ChessPiece.Color.WHITE) || board.isCheck(ChessPiece.Color.BLACK)) {
                    System.out.println("CHECK!!!");
                    break;
                }

                if (i % 2 == 0) {
                    System.out.println("White move: ");
                } else {
                    System.out.println("Black move: ");
                }
                unos = ulaz.nextLine();

                if (unos.equals("X")) {
                    break;
                }
                if (unos.length() == 2) {
                    if (i % 2 == 0) {
                        board.move(Pawn.class, ChessPiece.Color.WHITE, unos);
                        i++;
                    } else {
                        board.move(Pawn.class, ChessPiece.Color.BLACK, unos);
                        i++;
                    }
                } else if (unos.charAt(0) == 'K') {

                    if (i % 2 == 0) {
                        board.move(King.class, ChessPiece.Color.WHITE, unos);
                        i++;
                    } else {
                        board.move(King.class, ChessPiece.Color.BLACK, unos);
                        i++;
                    }
                } else if (unos.charAt(0) == 'Q') {

                    if (i % 2 == 0) {
                        board.move(Queen.class, ChessPiece.Color.WHITE, unos);
                        i++;
                    } else {
                        board.move(Queen.class, ChessPiece.Color.BLACK, unos);
                        i++;
                    }
                } else if (unos.charAt(0) == 'R') {

                    if (i % 2 == 0) {
                        board.move(Rook.class, ChessPiece.Color.WHITE, unos);
                        i++;
                    } else {
                        board.move(Rook.class, ChessPiece.Color.BLACK, unos);
                        i++;
                    }
                } else if (unos.charAt(0) == 'B') {

                    if (i % 2 == 0) {
                        board.move(Bishop.class, ChessPiece.Color.WHITE, unos);
                        i++;
                    } else {
                        board.move(Bishop.class, ChessPiece.Color.BLACK, unos);
                        i++;
                    }
                } else if (unos.charAt(0) == 'N') {

                    if (i % 2 == 0) {
                        board.move(Knight.class, ChessPiece.Color.WHITE, unos);
                        i++;
                    } else {
                        board.move(Knight.class, ChessPiece.Color.BLACK, unos);
                        i++;
                    }
                } else {
                    System.out.println("Illegal move");
                }


            } catch (Exception e) {
                System.out.println("Illegal move");
            }
        }
    }
}

