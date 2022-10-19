package game_pack;

public abstract class Creature {

    private String name;
    private int health;
    private int damage;
    private boolean isAlive;

    {
        isAlive = true;
    }

    public void hurt(int damage) {
        health -= damage;
        if (health <= 0) {
            setAlive(false);
        }
    }

    public void heal(int heal) {
        health += heal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
