package cegepst.ennemies;

import cegepst.engine.Buffer;
import cegepst.engine.entity.LivingEntity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Wizard extends LivingEntity {

    private BufferedImage spriteSheet;
    private final Ganon ganon;

    public Wizard(Ganon ganon) {
        initialize();
        this.ganon = ganon;
    }

    @Override
    public void update() {
        if (isDead()) {
            ganon.setHp(1);
        }
    }

    @Override
    public void draw(Buffer buffer) {
        if (isDead()) {
            return;
        }
        buffer.drawImage(spriteSheet.getSubimage(77, 1035, width, height), x, y);
    }

    private void initialize() {
        loadSpriteSheet();
        teleport(710, 2270);
        setHp(1);
        setDimension(32, 32);
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
