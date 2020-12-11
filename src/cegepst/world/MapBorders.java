package cegepst.world;

import cegepst.engine.Buffer;
import cegepst.engine.entity.StaticEntity;

public class MapBorders extends StaticEntity {

    private Blockade blockade;

    public MapBorders(int x, int y, int w, int h) {
        blockade = new Blockade();
        blockade.setDimension(w, h);
        blockade.teleport(x, y);
    }

    @Override
    public void draw(Buffer buffer) {
        blockade.draw(buffer);
    }
}
