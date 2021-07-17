package io.github.rangeremerald.tictactoegammawave.objects;

import io.github.rangeremerald.tictactoegammawave.screens.Board;

import java.awt.*;

public class Piece {

    public final int xpos;
    public final int ypos;
    public Character piece;
    public final Rectangle rectangle;
    public final Color colour;

    public Piece(int xpos, int ypos, char piece, Color colour) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.piece = piece;
        this.colour = colour;
        this.rectangle = new Rectangle(xpos, ypos, Board.rectSpacing, Board.rectSpacing);
    }

}
