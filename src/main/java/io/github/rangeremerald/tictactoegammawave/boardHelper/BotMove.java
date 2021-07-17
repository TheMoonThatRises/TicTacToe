package io.github.rangeremerald.tictactoegammawave.boardHelper;

import io.github.rangeremerald.tictactoegammawave.screens.Board;

public class BotMove {

    public BotMove() {
        while (true) {
            int random = (int) Math.round(Math.random() * (Board.pieceArray.length - 1));
            if (Board.pieceArray[random].piece.equals('n')) {
                Board.pieceArray[random].piece = Board.botPiece;
                break;
            }
        }
    }

}
