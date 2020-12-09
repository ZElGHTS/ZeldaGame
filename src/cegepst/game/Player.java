package cegepst.game;

import cegepst.factories.FrameFactory;
import cegepst.engine.Buffer;
import cegepst.engine.Camera;
import cegepst.engine.controls.Direction;
import cegepst.engine.controls.GamePad;
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

    public Player(GamePad gamePad) {
        super(gamePad);
        initialize();
        FrameFactory frameFactory = new FrameFactory();
        LINK_FRAMES = frameFactory.loadLinkFrames(width, height);
    }

    @Override
    public void update() {
        super.update();
        moveAccordingToHandler();
        cycleFrames();
        updateCooldown();
        clipped();
    }

    @Override
    public void draw(Buffer buffer) {
        hearts(buffer);
        final Camera camera = Camera.getInstance();
        camera.move(x - camera.getHalfScreenX() + offsetX, y - camera.getHalfScreenY() + offsetY);
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

    public void initialize() {
        setHp(100);
        setSpeed(4);
        setOffset(32, 32);
        setDimension(32, 32);
        teleport(675, 500);
    }

    public Arrow fire() {
        cooldown = 20;
        return new Arrow(this);
    }

    public boolean canFire() {
        return cooldown == 0;
    }

    private void hearts(Buffer buffer) {
        final int FULL = 0;
        final int EMPTY = 1;
        final int numberOfHearts = 10;
        int x = numberOfHearts;
        for (int i = 1; i <= numberOfHearts; ++i) {
            buffer.drawHearts(LINK_FRAMES.get("heartsFrames")[EMPTY], x, 10);
            x += 20;
        }
        x = numberOfHearts;
        for (int i = 1; i <= (getHp() /numberOfHearts); ++i) {
            buffer.drawHearts(LINK_FRAMES.get("heartsFrames")[FULL], x, 10);
            x += 20;
        }
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

    private void updateCooldown() {
        cooldown--;
        if (cooldown <= 0) {
            cooldown = 0;
        }
    }

    private void clipped() {
        if ((x <= 390 && y <= 385) || (x >= 1740 && y <= 385)
                || (x <= 390 && y >= 2772) || (x >= 1740 && y >= 2772)) {
            teleport(1055, 1085);
        }
    }
}
