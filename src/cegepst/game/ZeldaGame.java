package cegepst.game;

import cegepst.engine.entity.LivingEntity;
import cegepst.ennemies.Ganon;
import cegepst.ennemies.Wizard;
import cegepst.factories.WaveFactory;
import cegepst.ennemies.Zelda;
import cegepst.engine.*;
import cegepst.engine.controls.GamePad;
import cegepst.engine.entity.StaticEntity;
import cegepst.world.Map;
import cegepst.world.MapBorders;

import javax.sound.sampled.Clip;
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
    private final int FINAL_WAVE = 3;

    private Clip clip;
    private int cooldownWave = INITIAL_COOLDOWN_WAVE;
    private int numberOfZelda = 50;
    private int currentWave = 0;
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
            SOUND.playSoundEffect("sounds/best1.wav");
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

        if (currentWave != FINAL_WAVE) {
            updateWave();
        } else if (!ganonExist && ZELDAS.isEmpty()) {
            ganonExist = true;
            ganonWave();
        }

        if (ganonExist && !winner) {
            updateGanonWave();
        }
    }

    @Override
    public void draw(Buffer buffer) {
        MAP.draw(buffer);

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

        PLAYER.draw(buffer);
    }

    @Override
    public void initialize() {
        RenderingEngine.getInstance().getScreen().hideCursor();
        clip = SOUND.playMusic("musics/letTheBattleBegin.wav");
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
                arrowIntersect(arrow, zelda, killedElements);
            }
            arrowIntersect(arrow, wizard, killedElements);
            arrowIntersect(arrow, ganon, killedElements);
        }
    }

    private void arrowIntersect(Arrow arrow, LivingEntity entity, ArrayList<StaticEntity> killedElements) {
        if(arrow.hitBoxIntersectWith(entity)) {
            entity.receiveDamage(arrow.getDamage());
            if(entity.isDead()) {
                killedElements.add(entity);
            }
            killedElements.add(arrow);
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
                if(ZELDAS.isEmpty() && currentWave != FINAL_WAVE) {
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
            currentWave++;
            numberOfZelda += 35;
        }
        if (ZELDAS.size() == 1) {
            cooldownWave = INITIAL_COOLDOWN_WAVE;
        }
    }

    private void ganonWave() {
        clip.close();
        clip = SOUND.playMusic("musics/bossMusic.wav");
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
                if (wizard.isDead()) {
                    ganon.setCooldown(70);
                }
                cooldownWave = ganon.getCooldown();
            }
        } else {
            clip.close();
            SOUND.playMusic("musics/victoryFanfare.wav");
            winner = true;
        }
    }
}
