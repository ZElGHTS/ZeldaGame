package cegepst;

import cegepst.engine.Buffer;
import cegepst.engine.controls.Direction;
import cegepst.engine.entity.ControllableEntity;

import java.awt.*;
import java.util.HashMap;

public class Player extends ControllableEntity {

    private final HashMap<String, Image[]> LINK_FRAMES;
    private final int ANIMATION_SPEED = 8;
    private final int NUMBER_OF_SPRITE = 2;
    private int currentAnimationFrame = 1;
    private int nextFrame = ANIMATION_SPEED;
    private int cooldown;
    private int lastX;
    private int lastY;

    public Player(GamePad gamePad) {
        super(gamePad);
        Animator animator = new Animator();
        setSpeed(6);
        setDimension(32, 32);
        LINK_FRAMES = animator.getLinkFrames(width, height);
    }

    @Override
    public void update() {
        super.update();
        lastX = x;
        lastY = y;
        moveAccordingToHandler();
        cycleFrames();
        updateCooldown();
    }

    @Override
    public void draw(Buffer buffer) {
        if (getDirection() == Direction.UP) {
            buffer.drawImage(LINK_FRAMES.get("upFrames")[currentAnimationFrame], x, y);
        } else if (getDirection() == Direction.DOWN) {
            buffer.drawImage(LINK_FRAMES.get("downFrames")[currentAnimationFrame], x, y);
        } else if (getDirection() == Direction.LEFT) {
            buffer.drawImage(LINK_FRAMES.get("leftFrames")[currentAnimationFrame], x, y);
        } else {
            buffer.drawImage(LINK_FRAMES.get("rightFrames")[currentAnimationFrame], x, y);
        }
    }

    public void resetCoord() {
        x = lastX;
        y = lastY;
    }

    public Arrow fire() {
        cooldown = 35;
        return new Arrow(this);
    }

    public boolean canFire() {
        return cooldown == 0;
    }

    private void cycleFrames() {
        if (x != lastX || y != lastY || super.hasMoved()) {
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

    private void updateCooldown() {
        cooldown--;
        if (cooldown <= 0) {
            cooldown = 0;
        }
    }
}
