package cegepst.world;

import cegepst.engine.Buffer;
import cegepst.engine.entity.StaticEntity;

public class Tombstone extends StaticEntity {

    private Blockade blockade;

    public Tombstone(int x, int y) {
        this.x = x;
        this.y = y;
        blockade = new Blockade();
        blockade.setDimension(48, 30);
        blockade.teleport(x, y);
    }

    @Override
    public void draw(Buffer buffer) {
        blockade.draw(buffer);
    }
}
