package cegepst.ennemies;

import cegepst.engine.Buffer;
import cegepst.engine.entity.LivingEntity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Ganon extends LivingEntity {

    private BufferedImage spriteSheet;

    public Ganon() {
        initialize();
    }

    @Override
    public void draw(Buffer buffer) {
        if (isDead()) {
            return;
        }
        buffer.drawImage(spriteSheet.getSubimage(1032, 924, width, height), x, y);
    }

    private void initialize() {
        loadSpriteSheet();
        setHp(999999999);
        setDamage(100);
        setDimension(192, 192);
    }

    public int attack() {
        return getDamage();
    }

    private void loadSpriteSheet() {
        String spritePath = "images/Ganon.png";
        try {
            spriteSheet = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(spritePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
