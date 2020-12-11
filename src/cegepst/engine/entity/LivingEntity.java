package cegepst.engine.entity;



public abstract class LivingEntity extends MovableEntity  {

    private int hp;

    public void receiveDamage(int damage) {
        hp -= damage;
    }

    public boolean isDead() {
        return hp <= 0;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public void isClipped(LivingEntity entity) {
        if ((entity.x <= 390 && entity.y <= 385) || (entity.x >= 1740 && entity.y <= 385)
                || (entity.x <= 390 && entity.y >= 2772) || (entity.x >= 1740 && entity.y >= 2772)) {
            teleport(1055, 1085);
        }
    }
}