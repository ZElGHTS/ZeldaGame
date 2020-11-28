package cegepst;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Map {

    private static final String MAP_PATH = "images/map.png";
    private Image map;

    public Map() {

    }

    private void loadMap() {
        try {
            map = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(MAP_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
