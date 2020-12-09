package cegepst;

import cegepst.engine.Buffer;
import cegepst.engine.CollidableRepository;
import cegepst.engine.controls.Direction;
import cegepst.engine.entity.LivingEntity;

import java.awt.*;
import java.util.HashMap;

public class Zelda extends LivingEntity {

    private final HashMap<String, Image[]> ZELDA_FRAMES;
    private final int ANIMATION_SPEED = 8;
    private final int NUMBER_OF_SPRITE = 2;
    private final Player LINK;
    private int currentAnimationFrame = 1;
    private int nextFrame = ANIMATION_SPEED;
    private int cooldown;

    public Zelda(Player player) {
        FrameFactory frameFactory = new FrameFactory();
        initialize();
        LINK = player;
        ZELDA_FRAMES = frameFactory.loadZeldaFrames(width, height);
        CollidableRepository.getInstance().registerEntity(this);
    }

    @Override
    public void update() {
        super.update();
        moveToLink();
        cycleFrames();
        updateCooldown();
    }

    @Override
    public void draw(Buffer buffer) {
        if (getDirection() == Direction.UP) {
            buffer.drawImage(ZELDA_FRAMES.get("upFrames")[currentAnimationFrame], x, y);
        } else if (getDirection() == Direction.DOWN) {
            buffer.drawImage(ZELDA_FRAMES.get("downFrames")[currentAnimationFrame], x, y);
        } else if (getDirection() == Direction.LEFT) {
            buffer.drawImage(ZELDA_FRAMES.get("leftFrames")[currentAnimationFrame], x, y);
        } else if (getDirection() == Direction.RIGHT) {
            buffer.drawImage(ZELDA_FRAMES.get("rightFrames")[currentAnimationFrame], x, y);
        }
    }

    public void moveToLink() {
        if(x < LINK.getX() || hitBoxIntersectWith(LINK)) {
            moveRight();
        }
        if(x > LINK.getX() || hitBoxIntersectWith(LINK)) {
            moveLeft();
        }
        if(y < LINK.getY() || hitBoxIntersectWith(LINK)) {
            moveDown();
        }
        if(y > LINK.getY() || hitBoxIntersectWith(LINK)) {
            moveUp();
        }
    }

    public boolean canAttack() {
        return cooldown < 0;
    }

    public void updateCooldown() {
        cooldown--;
        if(cooldown <= -4) {
            cooldown = 20;
        }
    }

    public int attack() {
        return getDamage();
    }

    private void initialize() {
        setHp(10);
        setDamage(10);
        setSpeed(2);
        setDimension(24, 32);
        cooldown = 20;
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
