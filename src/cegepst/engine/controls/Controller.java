package cegepst.engine.controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public abstract class Controller implements KeyListener {

    private final HashMap<Integer, Boolean> pressedKeys;

    public Controller() {
        pressedKeys = new HashMap<>();
    }

    protected void bindKeys(int[] keys) {
        for (int keyCode : keys) {
            pressedKeys.put(keyCode, false);
        }
    }

    protected void bindKey(int keyCode) {
        pressedKeys.put(keyCode, false);
    }

    protected void clearKeys() {
        pressedKeys.clear();
    }

    protected void removeKey(int keyCode) {
        pressedKeys.remove(keyCode);
    }

    protected boolean isKeyPressed(int keyCode) {
        return pressedKeys.containsKey(keyCode) && pressedKeys.get(keyCode);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {}

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (pressedKeys.containsKey(keyCode)) {
            pressedKeys.put(keyCode, true);
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (pressedKeys.containsKey(keyCode)) {
            pressedKeys.put(keyCode, false);
        }
    }
}
