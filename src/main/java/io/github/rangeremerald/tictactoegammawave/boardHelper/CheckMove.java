package io.github.rangeremerald.tictactoegammawave.boardHelper;

import io.github.rangeremerald.tictactoegammawave.objects.Piece;
import io.github.rangeremerald.tictactoegammawave.screens.Board;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CheckMove {

    public static ArrayList<Object> checkMove(Character whichPiece) {
        int[] checkPositionSides = {1, 4, 7};
        int[] leftRight = {1, -1};

        int[] checkPositionsHeads = {3, 4, 5};
        int[] upDown = {3, -3};

        final int length = (int) Math.ceil(Board.rectThickness * Math.sqrt(2));

        for (int x : checkPositionSides)
            if (Board.pieceArray[x + leftRight[0]].piece.equals(whichPiece) && Board.pieceArray[x].piece.equals(whichPiece) && Board.pieceArray[x + leftRight[1]].piece.equals(whichPiece))
                return new ArrayList<>(Arrays.asList(0.0, new Rectangle(Board.pieceArray[x + leftRight[1]].xpos, Board.pieceArray[x + leftRight[0]].ypos + Board.rectSpacing / 2 - length / 2, Board.rectLength, length)));
        for (int y : checkPositionsHeads)
            if (Board.pieceArray[y + upDown[0]].piece.equals(whichPiece) && Board.pieceArray[y].piece.equals(whichPiece) && Board.pieceArray[y + upDown[1]].piece.equals(whichPiece))
                return new ArrayList<>(Arrays.asList(0.0, new Rectangle(Board.pieceArray[y + upDown[0]].xpos + Board.rectSpacing / 2 - length / 2, Board.pieceArray[y + upDown[1]].ypos, length, Board.rectLength)));
        if (Board.pieceArray[4].piece.equals(whichPiece)) {
            if (Board.pieceArray[0].piece.equals(whichPiece) && Board.pieceArray[8].piece.equals(whichPiece))
                return new ArrayList<>(Arrays.asList(45.0, new Rectangle(Board.pieceArray[0].xpos, Board.pieceArray[0].ypos, Board.rectLength, length)));
            else if (Board.pieceArray[2].piece.equals(whichPiece) && Board.pieceArray[6].piece.equals(whichPiece))
                return new ArrayList<>(Arrays.asList(135.0, new Rectangle(Board.pieceArray[2].xpos + Board.rectSpacing, Board.pieceArray[2].ypos, Board.rectLength, length)));
        }

        return new ArrayList<>(Arrays.asList(0.0, 0));
    }

    public static boolean ifDraw() {
        int pieceTaken = 0;
        for (Piece piece : Board.pieceArray) {
            if (piece.piece != 'n') pieceTaken++;
        }
        return pieceTaken > 8;
    }

}
