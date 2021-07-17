package io.github.rangeremerald.tictactoegammawave.objects;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings({"SameReturnValue", "CanBeFinal"})
public abstract class ScreenButtonsHelper {

    //Default values
    public final int roundedSize = 20;
    public final int rectHeight = 80;
    public final int rectWidth = 200;

    public final int size = 40;

    public ArrayList<String> buttonNames = new ArrayList<>();
    public final HashMap<String, Button> buttonHash = new HashMap<>();

    public int xpos = 0;
    public int ypos = 0;
    protected int namePos = 1;

    public void drawButtons(Graphics g) {
        for (Button button : buttonHash.values()) button.drawButton(g);
    }

    public boolean initHash(Graphics g) {
        namePos = 1;
        for (String buttonName : buttonNames) {
            updatePos(rectWidth, rectHeight);
            buttonHash.put(buttonName, new Button(xpos, ypos, rectHeight, rectWidth, buttonName, size, roundedSize, g));
            namePos++;
        }
        return true;
    }

    public abstract void updatePos(int width, int height);

    public String addButtonReInit(String text, Graphics g) {
        buttonNames.add(text);
        initHash(g);
        return "done";
    }

    public boolean removeButtonInit(String text) {
        buttonHash.remove(text);
        buttonNames.remove(text);
        return false;
    }

}