
// Camera premier jet



/*package cegepst;

import cegepst.engine.Buffer;
import cegepst.engine.controls.Direction;
import cegepst.engine.entity.ControllableEntity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Camera {

    public class Camera extends ControllableEntity {

        private static final String MAP_PATH = "images/map.png";
        private Image background;

        public Camera(GamePad gamePad) {
            super(gamePad);
            setSpeed(6);
            loadMap();
        }

        @Override
        public void update() {
            super.update();
            moveOppositeToHandler();
        }

        @Override
        public void draw(Buffer buffer) {
            if (hasMoved()) {
                if (getDirection() == Direction.UP) {
                    buffer.drawImage(background, x, y += getSpeed());
                } else if (getDirection() == Direction.DOWN) {
                    buffer.drawImage(background, x, y -= getSpeed());
                } else if (getDirection() == Direction.LEFT) {
                    buffer.drawImage(background, x += getSpeed(), y);
                } else if (getDirection() == Direction.RIGHT) {
                    buffer.drawImage(background, x -= getSpeed(), y);
                }
            }
            buffer.drawImage(background, x, y);
        }

        private void loadMap() {
            try {
                background = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(MAP_PATH));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}*/
