package cegepst;

import cegepst.engine.Buffer;
import cegepst.engine.entity.StaticEntity;

public class Tombstone extends StaticEntity {

    private Blockade blockade;

    public Tombstone(int x, int y) {
        blockade = new Blockade();
        blockade.setDimension(48, 24);
        blockade.teleport(x, y + 24);
    }

    public void blockadeFromTop() {
        blockade.teleport(x + 16, y + 64);
    }

    public void blockadeFromBottom() {
        blockade.teleport(x + 16, y + 48);
    }

    @Override
    public void draw(Buffer buffer) {
        blockade.draw(buffer);
        //buffer.drawRectangle(finalX, finalY, 48, 48, Color.GREEN);
    }
}
