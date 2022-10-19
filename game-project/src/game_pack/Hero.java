package game_pack;

public class Hero extends Creature {

    private Weapon weapon;
    private int x;
    private int y;

    {
        setName("Hero");
        setHealth(100);
        weapon = new Weapon("Hand", 5);
    }

    public Hero() {}

    public int heroDamage() {
        return weapon.getDamage();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
        System.out.println("Hero set " + weapon.getName());
    }

    public Weapon getWeapon() {
        return weapon;
    }
}