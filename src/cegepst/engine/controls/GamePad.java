package cegepst.engine.controls;

import cegepst.engine.RenderingEngine;

import java.awt.event.KeyEvent;

public class GamePad extends MovementController {

    private final int quitKey = KeyEvent.VK_ESCAPE;
    private final int fireKey = KeyEvent.VK_SPACE;

    public GamePad() {
        super.bindKey(quitKey);
        super.bindKey(fireKey);
        RenderingEngine.getInstance().addInputListener(this);
    }

    public boolean isFirePressed() {
        return super.isKeyPressed(fireKey);
    }

    public boolean isQuitPressed() {
        return super.isKeyPressed(quitKey);
    }
}
