package cegepst;

import cegepst.engine.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.util.ArrayList;

public class ZeldaGame extends Game {

    private final Player PLAYER;
    private final Map MAP;
    private final GamePad GAME_PAD;
    private ArrayList<Arrow> arrows;
    private int soundCoolDown;

    public ZeldaGame() {
        arrows = new ArrayList<>();
        GAME_PAD = new GamePad();
        PLAYER = new Player(GAME_PAD);
        MAP = new Map();
    }

    @Override
    public void update() {
        if (GAME_PAD.isQuitPressed()) {
            super.stop();
        }

        if (GAME_PAD.isFirePressed() && PLAYER.canFire()) {
            arrows.add(PLAYER.fire());
        }

        for (Arrow arrow : arrows) {
            arrow.update();
        }

        PLAYER.update();
/*
        if (GAME_PAD.isFirePressed() && soundCoolDown == 0) {
            soundCoolDown = 40;
            Sound.play("sounds/best1.wav");
        }

 */
    }

    @Override
    public void draw(Buffer buffer) {
        MAP.draw(buffer);
        PLAYER.draw(buffer);

        for (Arrow arrow : arrows) {
            arrow.draw(buffer);
        }

        buffer.drawText("FPS: " + GameTime.getCurrentFps(), 10, 20, Color.WHITE);
        buffer.drawText(GameTime.getElapsedFormattedTime(), 10, 40, Color.WHITE);
    }

    @Override
    public void initialize() {
        RenderingEngine.getInstance().getScreen().hideCursor();
        startMusic();
    }

    @Override
    public void conclude() {

    }

    private void startMusic() {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResourceAsStream("musics/map1.wav"));
            clip.open(inputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
