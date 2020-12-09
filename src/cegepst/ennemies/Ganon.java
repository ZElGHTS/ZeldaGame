package cegepst.ennemies;

import cegepst.engine.Buffer;
import cegepst.engine.entity.LivingEntity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Ganon extends LivingEntity {

    private BufferedImage spriteSheet;
    private Random random;
    private int spawnCooldown;

    public Ganon() {
        initialize();
    }

    @Override
    public void draw(Buffer buffer) {
        if (isDead()) {
            return;
        }
        int sprite = random.nextInt(5);
        switch(sprite) {
            case 0: buffer.drawImage(spriteSheet.getSubimage(240, 924, width, height), x, y); break;
            case 1: buffer.drawImage(spriteSheet.getSubimage(438, 924, width, height), x, y); break;
            case 2: buffer.drawImage(spriteSheet.getSubimage(636, 924, width, height), x, y); break;
            case 3: buffer.drawImage(spriteSheet.getSubimage(834, 924, width, height), x, y); break;
            case 4: buffer.drawImage(spriteSheet.getSubimage(1032, 924, width, height), x, y); break;
        }
    }

    private void initialize() {
        loadSpriteSheet();
        random = new Random();
        setHp(999999999);
        setDamage(100);
        setDimension(192, 192);
        spawnCooldown = 50;
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
