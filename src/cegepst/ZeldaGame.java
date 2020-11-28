package cegepst;

import cegepst.engine.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.util.ArrayList;

public class ZeldaGame extends Game {

    private final Player PLAYER;
    private final Camera CAMERA;
    private final GamePad GAME_PAD;
    private ArrayList<Tombstone> tombstones;
    private ArrayList<Arrow> arrows;
    private int soundCoolDown;

    public ZeldaGame() {
        tombstones = new ArrayList<>();
        setTombstone();
        arrows = new ArrayList<>();
        GAME_PAD = new GamePad();
        CAMERA = new Camera(GAME_PAD);
        PLAYER = new Player(GAME_PAD);
        PLAYER.teleport(368, 268);
        CAMERA.teleport(0, 0);
    }

    @Override
    public void update() {
        CAMERA.update();
        PLAYER.update();
        --soundCoolDown;
        if (soundCoolDown < 0) {
            soundCoolDown = 0;
        }

        if (GAME_PAD.isQuitPressed()) {
            super.stop();
        }

        if (GAME_PAD.isFirePressed() && PLAYER.canFire()) {
            arrows.add(PLAYER.fire());
        }

        for (Arrow arrow : arrows) {
            arrow.update();
        }

        PLAYER.resetCoord();
/*
        if (GAME_PAD.isFirePressed() && soundCoolDown == 0) {
            soundCoolDown = 40;
            Sound.play("sounds/best1.wav");
        }

 */
    }

    @Override
    public void draw(Buffer buffer) {
        CAMERA.draw(buffer);
        PLAYER.draw(buffer);
        for (Tombstone tombstone : tombstones) {
            tombstone.draw(buffer);
        }

        for (Arrow arrow : arrows) {
            arrow.draw(buffer);
        }

        buffer.drawText("FPS: " + GameTime.getCurrentFps(), 10, 20, Color.WHITE);
        buffer.drawText(GameTime.getElapsedFormattedTime(), 10, 40, Color.WHITE);
    }

    @Override
    public void initialize() {
        RenderingEngine.getInstance().getScreen().hideCursor();
        //RenderingEngine.getInstance().getScreen().fullScreen();
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

    private void setTombstone() {
        tombstones.add(new Tombstone(291, 144));
        tombstones.add(new Tombstone(435, 144));
        tombstones.add(new Tombstone(1062, 144));
        tombstones.add(new Tombstone(1206, 144));
        tombstones.add(new Tombstone(291, 240));
        tombstones.add(new Tombstone(435, 240));
        tombstones.add(new Tombstone(1062, 240));
        tombstones.add(new Tombstone(1206, 240));
        tombstones.add(new Tombstone(291, 336));
        tombstones.add(new Tombstone(435, 336));
        tombstones.add(new Tombstone(1062, 336));
        tombstones.add(new Tombstone(1206, 336));
        tombstones.add(new Tombstone(291, 675));
        tombstones.add(new Tombstone(435, 675));
        tombstones.add(new Tombstone(1062, 675));
        tombstones.add(new Tombstone(1206, 675));
        tombstones.add(new Tombstone(291, 771));
        tombstones.add(new Tombstone(435, 771));
        tombstones.add(new Tombstone(1062, 771));
        tombstones.add(new Tombstone(1206, 771));
        tombstones.add(new Tombstone(291, 867));
        tombstones.add(new Tombstone(435, 867));
        tombstones.add(new Tombstone(1062, 867));
        tombstones.add(new Tombstone(1206, 867));
        tombstones.add(new Tombstone(291, 1206));
        tombstones.add(new Tombstone(435, 1206));
        tombstones.add(new Tombstone(1062, 1206));
        tombstones.add(new Tombstone(1206, 1206));
        tombstones.add(new Tombstone(291, 1302));
        tombstones.add(new Tombstone(435, 1302));
        tombstones.add(new Tombstone(1062, 1302));
        tombstones.add(new Tombstone(1206, 1302));
        tombstones.add(new Tombstone(291, 1398));
        tombstones.add(new Tombstone(435, 1398));
        tombstones.add(new Tombstone(1062, 1398));
        tombstones.add(new Tombstone(1206, 1398));
        tombstones.add(new Tombstone(621, 1932));
    }
}
