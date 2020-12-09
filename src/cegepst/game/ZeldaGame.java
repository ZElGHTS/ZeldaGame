package cegepst.game;

import cegepst.ennemies.Ganon;
import cegepst.ennemies.Wizard;
import cegepst.factories.WaveFactory;
import cegepst.ennemies.Zelda;
import cegepst.engine.*;
import cegepst.engine.controls.GamePad;
import cegepst.engine.entity.StaticEntity;
import cegepst.world.Map;
import cegepst.world.MapBorders;

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
    private final int GANON_WAVE = 1;

    private int cooldownWave = INITIAL_COOLDOWN_WAVE;
    private int numberOfZelda = 50;
    private int currentWave = 1;
    private boolean winner = false;
    private boolean ganonExist = false;
    private Ganon ganon;
    private Wizard wizard;

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

        if (currentWave != GANON_WAVE) {
            updateWave();
        } else if (!ganonExist) {
            ganonExist = true;
            ganonWave();
        }

        if (ganonExist && !winner) {
            updateGanonWave();
        }

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

        if (ganonExist) {
            ganon.draw(buffer);
            wizard.draw(buffer);
        }

        buffer.drawText("FPS: " + GameTime.getCurrentFps(), 740, 20, Color.WHITE);
        buffer.drawText(GameTime.getElapsedFormattedTime(), 740, 40, Color.WHITE);

        if (winner) {

        }
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
            if(arrow.hitBoxIntersectWith(wizard)) {
                wizard.receiveDamage(arrow.getDamage());
                if(wizard.isDead()) {
                    killedElements.add(wizard);
                }
                killedElements.add(arrow);
            }
            if(arrow.hitBoxIntersectWith(ganon)) {
                ganon.receiveDamage(arrow.getDamage());
                if(ganon.isDead()) {
                    killedElements.add(ganon);
                }
                killedElements.add(arrow);
            }
        }
    }

    private void updateZeldas() {
        for(Zelda zelda : ZELDAS) {
            zelda.update();
            if(zelda.hitBoxIntersectWith(PLAYER) && zelda.canAttack()) {
                PLAYER.receiveDamage(zelda.attack());
            }
            if ((zelda.getX() <= 390 && zelda.getY() <= 385) || (zelda.getX() >= 1740 && zelda.getY() <= 385)
                    || (zelda.getX() <= 390 && zelda.getY() >= 2772) || (zelda.getX() >= 1740 && zelda.getY() >= 2772)) {
                FACTORY.randomSpawn(zelda);
            }
        }
    }

    private void updateKilledElements(ArrayList<StaticEntity> killedElements) {
        for(StaticEntity killedElement : killedElements) {
            if(killedElement instanceof Zelda) {
                ZELDAS.remove(killedElement);
                if(ZELDAS.isEmpty()) {
                    SOUND.playSoundEffect("sounds/waveFinished.wav");
                }
            } else if(killedElement instanceof Arrow) {
                ARROWS.remove(killedElement);
            }
            CollidableRepository.getInstance().unregisterEntity(killedElement);
        }
    }

    private void updateWave() {
        cooldownWave--;
        if(cooldownWave <= 0 && ZELDAS.isEmpty()) {
            for (int i = 0; i < numberOfZelda; ++i) {
                ZELDAS.add(FACTORY.createZelda(PLAYER));
            }

            numberOfZelda += 35;
            currentWave++;
        }
        if (ZELDAS.size() == 1) {
            cooldownWave = INITIAL_COOLDOWN_WAVE;
        }
    }

    private void ganonWave() {
        SOUND.playSoundEffect("sounds/ganonEntrance.wav");
        ganon = FACTORY.createGanon();
        wizard = FACTORY.createWizard(ganon);
    }

    private void updateGanonWave() {
        cooldownWave--;
        if (!ganon.isDead()) {
            if(ganon.intersectWith(PLAYER)) {
                PLAYER.receiveDamage(ganon.attack());
            }
            wizard.update();
            if (cooldownWave <= 0) {
                FACTORY.ganonSpawn(ganon);
            }
        } else {
            winner = true;
            SOUND.playMusic("musics/victory.wav");
        }
    }
}
