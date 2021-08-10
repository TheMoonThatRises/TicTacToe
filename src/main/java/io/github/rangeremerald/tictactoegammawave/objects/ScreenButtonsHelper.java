package io.github.rangeremerald.tictactoegammawave.objects;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings({"SameReturnValue", "CanBeFinal"})
public abstract class ScreenButtonsHelper {

    //Default values
    public int roundedSize = 20;
    public int rectHeight = 80;
    public int rectWidth = 200;

    public int size = 40;

    public ArrayList<String> buttonNames = new ArrayList<>();
    public final HashMap<String, Button> buttonHash = new HashMap<>();

    public int xpos = 0;
    public int ypos = 0;
    public int scrollDisplace = 0;
    protected int namePos = 1;

    public void drawButtons(Graphics g) {
        for (Button button : buttonHash.values()) button.drawButton(g);
    }

    public boolean initHash(Graphics g) {
        namePos = 1;
        for (String buttonName : buttonNames) {
            updatePos();
            buttonHash.put(buttonName, new Button(xpos, ypos, rectHeight, rectWidth, buttonName, size, roundedSize, g));
            namePos++;
        }
        return true;
    }

    public abstract void updatePos();

    public abstract void updateAnimSpeed(int speed);

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

    public void buttonPressed(Rectangle mouseRect, Runnable waitCursor, Runnable defaultCursor) {
        for (Button button : buttonHash.values()) {
            if (mouseRect.intersects(button.buttonRect)) {
                whichButton(button, waitCursor, defaultCursor);
                break;
            }
        }
    }

    public abstract void whichButton(Button pressedButton, Runnable waitCursor, Runnable defaultCursor);


}