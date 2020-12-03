package cegepst;

import cegepst.engine.Buffer;
import cegepst.engine.controls.Direction;
import cegepst.engine.entity.MovableEntity;

import java.awt.*;
import java.util.HashMap;

public class Zelda extends MovableEntity {

    private final HashMap<String, Image[]> ZELDA_FRAMES;
    private final int ANIMATION_SPEED = 8;
    private final int NUMBER_OF_SPRITE = 2;
    private int currentAnimationFrame = 1;
    private int nextFrame = ANIMATION_SPEED;
    private Player link;

    public Zelda(Player player) {
        Animator animator = new Animator();
        setSpeed(7);
        setDimension(24, 32);
        link = player;
        ZELDA_FRAMES = animator.loadZeldaFrames(width, height);
    }

    @Override
    public void update() {
        super.update();
        moveToLink();
        attackLink();
        cycleFrames();
    }

    @Override
    public void draw(Buffer buffer) {
        if (getDirection() == Direction.UP) {
            buffer.drawImage(ZELDA_FRAMES.get("upFrames")[currentAnimationFrame], x, y);
        } else if (getDirection() == Direction.DOWN) {
            buffer.drawImage(ZELDA_FRAMES.get("downFrames")[currentAnimationFrame], x, y);
        } else if (getDirection() == Direction.LEFT) {
            buffer.drawImage(ZELDA_FRAMES.get("leftFrames")[currentAnimationFrame], x, y);
        } else {
            buffer.drawImage(ZELDA_FRAMES.get("rightFrames")[currentAnimationFrame], x, y);
        }
    }

    private void moveToLink() {
        //TODO : intelligence pour se diriger vers Link
    }

    private void attackLink() {
        //TODO : système de détection de position pour attaquer
    }

    private void cycleFrames() {
        if (super.hasMoved()) {
            --nextFrame;
            if (nextFrame == 0) {
                ++currentAnimationFrame;
                if (currentAnimationFrame >= NUMBER_OF_SPRITE) {
                    currentAnimationFrame = 0;
                }
                nextFrame = ANIMATION_SPEED;
            }
        } else {
            currentAnimationFrame = 1;
        }
    }
}
