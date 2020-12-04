package cegepst;

import cegepst.engine.Buffer;
import cegepst.engine.entity.StaticEntity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Map extends StaticEntity {

    private static final String MAP_PATH = "images/gameMap.png";
    private final ArrayList<Tombstone> tombstones;
    private final ArrayList<MapBorders> borders;
    private Image map;

    public Map() {
        loadMap();
        teleport(0, 0);
        borders = new ArrayList<>();
        setMapBorders();
        tombstones = new ArrayList<>();
        setTombstone();
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(map, x, y);
        for (Tombstone tombstone : tombstones) {
            tombstone.draw(buffer);
        }
        for (MapBorders border : borders) {
            border.draw(buffer);
        }
    }

    public ArrayList<MapBorders> getMapBorders() {
        return borders;
    }

    private void loadMap() {
        try {
            map = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(MAP_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setMapBorders() {
        borders.add(new MapBorders(346, 384, 50, 2463));
        borders.add(new MapBorders(396, 334, 1344, 50));
        borders.add(new MapBorders(1740, 384, 50, 2463));
        borders.add(new MapBorders(396, 2847, 1344, 50));

        borders.add(new MapBorders(972, 720, 195, 195));
        borders.add(new MapBorders(972, 1251, 195, 195));

        borders.add(new MapBorders(396, 1782, 570, 96));
        borders.add(new MapBorders(1164, 1782, 576, 96));

        borders.add(new MapBorders(396, 2025, 1260, 50));
        borders.add(new MapBorders(1606, 2025, 50, 323));
        borders.add(new MapBorders(1606, 2416, 50, 299));
        borders.add(new MapBorders(396, 2646, 1260, 69));

        borders.add(new MapBorders(1278, 2348, 378, 10));
        borders.add(new MapBorders(1278, 2406, 378, 10));

        borders.add(new MapBorders(606, 2214, 48, 336));
        borders.add(new MapBorders(606, 2118, 672, 96));
        borders.add(new MapBorders(606, 2550, 672, 96));
        borders.add(new MapBorders(1230, 2214, 48, 144));
        borders.add(new MapBorders(1230, 2406, 48, 144));
    }

    private void setTombstone() {
        tombstones.add(new Tombstone(588, 432));
        tombstones.add(new Tombstone(732, 432));
        tombstones.add(new Tombstone(1359, 432));
        tombstones.add(new Tombstone(1503, 432));
        tombstones.add(new Tombstone(588, 528));
        tombstones.add(new Tombstone(732, 528));
        tombstones.add(new Tombstone(1359, 528));
        tombstones.add(new Tombstone(1503, 528));
        tombstones.add(new Tombstone(588, 624));
        tombstones.add(new Tombstone(732, 624));
        tombstones.add(new Tombstone(1359, 624));
        tombstones.add(new Tombstone(1503, 624));

        tombstones.add(new Tombstone(588, 963));
        tombstones.add(new Tombstone(732, 963));
        tombstones.add(new Tombstone(1359, 963));
        tombstones.add(new Tombstone(1503, 963));
        tombstones.add(new Tombstone(588, 1059));
        tombstones.add(new Tombstone(732, 1059));
        tombstones.add(new Tombstone(1359, 1059));
        tombstones.add(new Tombstone(1503, 1059));
        tombstones.add(new Tombstone(588, 1155));
        tombstones.add(new Tombstone(732, 1155));
        tombstones.add(new Tombstone(1359, 1155));
        tombstones.add(new Tombstone(1503, 1155));

        tombstones.add(new Tombstone(588, 1494));
        tombstones.add(new Tombstone(732, 1494));
        tombstones.add(new Tombstone(1359, 1494));
        tombstones.add(new Tombstone(1503, 1494));
        tombstones.add(new Tombstone(588, 1590));
        tombstones.add(new Tombstone(732, 1590));
        tombstones.add(new Tombstone(1359, 1590));
        tombstones.add(new Tombstone(1503, 1590));
        tombstones.add(new Tombstone(588, 1686));
        tombstones.add(new Tombstone(732, 1686));
        tombstones.add(new Tombstone(1359, 1686));
        tombstones.add(new Tombstone(1509, 1686));

        tombstones.add(new Tombstone(918, 2219));
    }
}
