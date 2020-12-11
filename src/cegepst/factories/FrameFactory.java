package cegepst.factories;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class FrameFactory {

    private BufferedImage spriteSheet;
    private Image[] upFrames;
    private Image[] downFrames;
    private Image[] rightFrames;
    private Image[] leftFrames;

    public HashMap<String, Image[]> loadLinkFrames(int width, int height) {
        final String SPRITE_PATH_LINK = "images/Link.png";
        HashMap<String, Image[]> linkFrames = new HashMap<>();
        loadSpriteSheet(SPRITE_PATH_LINK);

        rightFrames = new Image[2];
        rightFrames[0] = spriteSheet.getSubimage(104, 22, width, height);
        rightFrames[1] = spriteSheet.getSubimage(70, 22, width, height);
        linkFrames.put("rightFrames", rightFrames);

        leftFrames = new Image[2];
        leftFrames[0] = spriteSheet.getSubimage(70, 58, width, height);
        leftFrames[1] = spriteSheet.getSubimage(100, 58, width, height);
        linkFrames.put("leftFrames", leftFrames);

        downFrames = new Image[2];
        downFrames[0] = spriteSheet.getSubimage(2, 22, width, height);
        downFrames[1] = spriteSheet.getSubimage(36, 22, width, height);
        linkFrames.put("downFrames", downFrames);

        upFrames = new Image[2];
        upFrames[0] = spriteSheet.getSubimage(138, 22, width, height);
        upFrames[1] = spriteSheet.getSubimage(172, 22, width, height);
        linkFrames.put("upFrames", upFrames);

        Image[] heartsFrames;
        heartsFrames = new Image[2];
        heartsFrames[0] = spriteSheet.getSubimage(4, 110, 14, 16);
        heartsFrames[1] = spriteSheet.getSubimage(22, 110, 14, 16);
        linkFrames.put("heartsFrames", heartsFrames);

        return linkFrames;
    }

    public HashMap<String, Image[]> loadZeldaFrames(int width, int height) {
        final String SPRITE_PATH_ZELDA = "images/Zelda.png";
        HashMap<String, Image[]> zeldaFrames = new HashMap<>();
        loadSpriteSheet(SPRITE_PATH_ZELDA);

        rightFrames = new Image[2];
        rightFrames[0] = spriteSheet.getSubimage(74, 0, width, height);
        rightFrames[1] = spriteSheet.getSubimage(108, 0, width, height);
        zeldaFrames.put("rightFrames", rightFrames);

        leftFrames = new Image[2];
        leftFrames[0] = spriteSheet.getSubimage(38, 70, width, height);
        leftFrames[1] = spriteSheet.getSubimage(4, 70, width, height);
        zeldaFrames.put("leftFrames", leftFrames);

        downFrames = new Image[2];
        downFrames[0] = spriteSheet.getSubimage(6, 0, width, height);
        downFrames[1] = spriteSheet.getSubimage(40, 0, width, height);
        zeldaFrames.put("downFrames", downFrames);

        upFrames = new Image[2];
        upFrames[0] = spriteSheet.getSubimage(142, 0, width, height);
        upFrames[1] = spriteSheet.getSubimage(176, 0, width, height);
        zeldaFrames.put("upFrames", upFrames);

        return zeldaFrames;
    }

    private void loadSpriteSheet(String spritePath) {
        try {
            spriteSheet = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(spritePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
