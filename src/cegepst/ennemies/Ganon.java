package cegepst.ennemies;

import cegepst.engine.Buffer;
import cegepst.engine.entity.LivingEntity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Ganon extends LivingEntity {

    private BufferedImage spriteSheet;
    private int cooldown;

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
        cooldown = 30;
    }

    public int attack() {
        return getDamage();
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    private void loadSpriteSheet() {
        final String spritePath = "images/Ganon.png";
        try {
            spriteSheet = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(spritePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
