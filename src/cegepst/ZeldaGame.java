package cegepst;

import cegepst.engine.*;
import cegepst.engine.entity.StaticEntity;
import java.awt.*;
import java.util.ArrayList;

public class ZeldaGame extends Game {

    private final Player PLAYER;
    private final Map MAP;
    private final GamePad GAME_PAD;
    private final ArrayList<Arrow> ARROWS;
    private final ArrayList<Zelda> ZELDAS;
    private final WaveFactory FACTORY;
    private final Sound SOUND = new Sound();
    private final int INITIAL_COOLDOWN_WAVE = 500;
    private final int BOSS_WAVE = 6;
    private int cooldownWave = INITIAL_COOLDOWN_WAVE;
    private int numberOfZelda = 50;
    private int currentWave = 1;

    public ZeldaGame() {
        ARROWS = new ArrayList<>();
        ZELDAS = new ArrayList<>();
        GAME_PAD = new GamePad();
        PLAYER = new Player(GAME_PAD);
        MAP = new Map();
        FACTORY = new WaveFactory();
    }

    @Override
    public void update() {
        if (GAME_PAD.isQuitPressed()) {
            super.stop();
        }

        if (GAME_PAD.isFirePressed() && PLAYER.canFire()) {
            ARROWS.add(PLAYER.fire());
        }

        PLAYER.update();

        ArrayList<StaticEntity> killedElements = new ArrayList<>();
        updateArrows(killedElements);
        updateZeldas();
        updateKilledElements(killedElements);

        updateWave();

        if (PLAYER.isDead()) {
            SOUND.playSoundEffect("sounds/best1.wav");
            super.stop();
        }
    }

    @Override
    public void draw(Buffer buffer) {
        MAP.draw(buffer);
        PLAYER.draw(buffer);

        for (Zelda zelda : ZELDAS) {
            zelda.draw(buffer);
        }

        for (Arrow arrow : ARROWS) {
            arrow.draw(buffer);
        }

        buffer.drawText("FPS: " + GameTime.getCurrentFps(), 740, 20, Color.WHITE);
        buffer.drawText(GameTime.getElapsedFormattedTime(), 740, 40, Color.WHITE);
    }

    @Override
    public void initialize() {
        RenderingEngine.getInstance().getScreen().hideCursor();
        SOUND.playMusic("musics/letTheBattleBegin.wav");
    }

    @Override
    public void conclude() {

    }

    private void updateArrows(ArrayList<StaticEntity> killedElements) {
        for (Arrow arrow : ARROWS) {
            arrow.update();
            for(MapBorders border : MAP.getMapBorders()) {
                if(arrow.hitBoxIntersectWith(border)) {
                    killedElements.add(arrow);
                }
            }
            for(Zelda zelda : ZELDAS) {
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
        for(Zelda zelda : ZELDAS) {
            zelda.update();
            if(zelda.hitBoxIntersectWith(PLAYER) && zelda.canAttack()) {
                PLAYER.receiveDamage(zelda.attack());
            }
        }
    }

    private void updateKilledElements(ArrayList<StaticEntity> killedElements) {
        for(StaticEntity killedElement : killedElements) {
            if(killedElement instanceof Zelda) {
                ZELDAS.remove(killedElement);
            } else if(killedElement instanceof Arrow) {
                ARROWS.remove(killedElement);
            }
            CollidableRepository.getInstance().unregisterEntity(killedElement);
        }
    }

    private void updateWave() {
        cooldownWave--;
        if(cooldownWave <= 0 && ZELDAS.isEmpty()) {
            if (currentWave != BOSS_WAVE) {
                for (int i = 0; i < numberOfZelda; ++i) {
                    ZELDAS.add(FACTORY.createZelda(PLAYER));
                }

                numberOfZelda += 35;
                currentWave++;
            }
        }
        if (ZELDAS.size() == 1) {
            cooldownWave = INITIAL_COOLDOWN_WAVE;
        }
    }
}
