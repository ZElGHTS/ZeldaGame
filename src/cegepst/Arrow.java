package cegepst;

import cegepst.engine.Buffer;
import cegepst.engine.controls.Direction;
import cegepst.engine.entity.MovableEntity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Arrow extends MovableEntity {

    private final Direction PLAYER_DIRECTION;
    private BufferedImage spriteSheet;

    public Arrow(Player player) {
        loadSpriteSheet();
        setDamage(10);
        setSpeed(8);
        PLAYER_DIRECTION = player.getDirection();
        if (PLAYER_DIRECTION == Direction.RIGHT) {
            super.teleport(player.getX() + player.getWidth() + 1, player.getY() + 15 - 2);
            setDimension(32, 10);
        } else if (PLAYER_DIRECTION == Direction.LEFT) {
            super.teleport(player.getX() - 9, player.getY() + 15 - 2);
            setDimension(32, 10);
        } else if (PLAYER_DIRECTION == Direction.DOWN) {
            super.teleport(player.getX() + 15 - 2, player.getY() + player.getHeight() + 1);
            setDimension(10, 32);
        } else if (PLAYER_DIRECTION == Direction.UP) {
            super.teleport(player.getX() + 15 - 2, player.getY() - 9);
            setDimension(10, 32);
        }
    }

    @Override
    public void update() {
        super.update();
        super.move(PLAYER_DIRECTION);
    }

    @Override
    public void draw(Buffer buffer) {
        switch (PLAYER_DIRECTION) {
            case UP: buffer.drawImage(spriteSheet.getSubimage(2, 58, width, height), x, y); break;
            case DOWN: buffer.drawImage(spriteSheet.getSubimage(14, 58, width, height), x, y); break;
            case LEFT: buffer.drawImage(spriteSheet.getSubimage(30, 77, width, height), x, y); break;
            case RIGHT: buffer.drawImage(spriteSheet.getSubimage(30, 58, width, height), x, y); break;
        }
    }

    private void loadSpriteSheet() {
        String spritePath = "images/Link.png";
        try {
            spriteSheet = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(spritePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
