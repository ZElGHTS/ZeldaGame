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
}
