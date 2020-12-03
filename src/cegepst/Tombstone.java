package cegepst;

import cegepst.engine.Buffer;
import cegepst.engine.entity.StaticEntity;

import java.awt.*;

public class Tombstone extends StaticEntity {

    private Blockade blockade;

    public Tombstone(int x, int y) {
        this.x = x;
        this.y = y;
        blockade = new Blockade();
        blockade.setDimension(48, 30);
        blockade.teleport(x, y);
    }

    public void blockadeFromTop() {
        blockade.teleport(x + 16, y + 64);
    }

    public void blockadeFromBottom() {
        blockade.teleport(x + 16, y + 48);
    }

    @Override
    public void draw(Buffer buffer) {
        //buffer.drawRectangle(x, y, 48, 48, Color.GREEN);
        blockade.draw(buffer);
    }
}
