package cegepst;

import cegepst.engine.*;
import cegepst.engine.entity.StaticEntity;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.util.ArrayList;

public class ZeldaGame extends Game {

    private final Player PLAYER;
    private final Map MAP;
    private final GamePad GAME_PAD;
    private final int initialCoolDownWave = 500;
    private int cooldownWave = initialCoolDownWave;
    private ArrayList<Arrow> arrows;
    private ArrayList<Zelda> zeldas;
    private int soundCoolDown;

    public ZeldaGame() {
        arrows = new ArrayList<>();
        zeldas = new ArrayList<>();
        GAME_PAD = new GamePad();
        PLAYER = new Player(GAME_PAD);
        MAP = new Map();
        Zelda test = new Zelda(PLAYER);
        zeldas.add(test);
    }

    @Override
    public void update() {
        if (GAME_PAD.isQuitPressed()) {
            super.stop();
        }

        if (GAME_PAD.isFirePressed() && PLAYER.canFire()) {
            arrows.add(PLAYER.fire());
        }

        PLAYER.update();

        ArrayList<StaticEntity> killedElements = new ArrayList<>();
        updateArrows(killedElements);
        updateZeldas();
        updateKilledElements(killedElements);
/*
        if (GAME_PAD.isFirePressed() && soundCoolDown == 0) {
            soundCoolDown = 40;
            Sound.play("sounds/best1.wav");
        }
 */
        if (PLAYER.isDead()) {
            super.stop();
        }
    }

    @Override
    public void draw(Buffer buffer) {
        MAP.draw(buffer);
        PLAYER.draw(buffer);

        for (Zelda zelda : zeldas) {
            zelda.draw(buffer);
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

    private void updateArrows(ArrayList<StaticEntity> killedElements) {
        for (Arrow arrow : arrows) {
            arrow.update();
            for(MapBorders border : MAP.getMapBorders()) {
                if(arrow.hitBoxIntersectWith(border)) {
                    killedElements.add(arrow);
                }
            }
            for(Zelda zelda : zeldas) {
                if(arrow.hitBoxIntersectWith(zelda)) {
                    zelda.receiveDamage(arrow.getDamage());
                    if(zelda.isDead()) {
                        killedElements.add(zelda);
                    }
                    killedElements.add(arrow);
                }
            }
        }
    }

    private void updateZeldas() {
        for(Zelda zelda : zeldas) {
            zelda.update();
            if(zelda.hitBoxIntersectWith(PLAYER) && zelda.canAttack()) {
                PLAYER.receiveDamage(zelda.attack());
            }
        }
    }

    private void updateKilledElements(ArrayList<StaticEntity> killedElements) {
        for(StaticEntity killedElement : killedElements) {
            if(killedElement instanceof Zelda) {
                zeldas.remove(killedElement);
            } else if(killedElement instanceof Arrow) {
                arrows.remove(killedElement);
            }
            CollidableRepository.getInstance().unregisterEntity(killedElement);
        }
    }


}
