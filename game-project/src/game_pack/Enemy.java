package game_pack;

public class Enemy extends Creature {

    private final String[] names;

    {
        names = new String[] {"enemy"};
    }

    public Enemy () {}

    public Enemy(String name, int health, int damage) {
        setName(name);
        setHealth(health);
        setDamage(damage);
    }

    public Enemy generateEnemy() {
        String name = names[Addition.random.nextInt(names.length)];
        int health = Addition.rnd(50, 100);
        int damage = Addition.rnd(10, 50);
        return new Enemy(name, health, damage);
    }
}
