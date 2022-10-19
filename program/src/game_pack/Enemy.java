package game_pack;

public class Enemy extends Creature {

    private final int[] weak;
    private final int[] average;
    private final int[] strong;
    private final String[] weakNames;
    private final String[] averageNames;
    private final String[] strongNames;

    {
        weak = new int[] {10, 5, 60};
        average = new int[] {15, 10, 30};
        strong = new int[] {20, 20, 10};
        weakNames = new String[] {"skeleton", "devil", "alien"};
        averageNames = new String[] {"reaper", "gryphon"};
        strongNames = new String[] {"dragon"};
    }

    public Enemy () {}

    public Enemy(String name, int health, int damage) {
        setName(name);
        setHealth(health);
        setDamage(damage);
    }

    public Enemy generateEnemy(int heroLevel) {
        Enemy enemy = null;
        String name;
        int rnd = Addition.rnd(0, weak[2] + average[2] + strong[2]);
        if (rnd <= weak[2]) {
            name = weakNames[Addition.rnd(0, weakNames.length - 1)];
            enemy = new Enemy(name, weak[0], weak[1]);
        }
        if (rnd > weak[2] && rnd <= weak[2] + average[2]) {
            name = averageNames[Addition.rnd(0, averageNames.length - 1)];
            enemy = new Enemy(name, average[0], average[1]);
        }
        if (rnd > weak[2] + average[2]) {
            name = strongNames[Addition.rnd(0, strongNames.length - 1)];
            enemy = new Enemy(name, strong[0], strong[1]);
        }
        if (heroLevel != 0) {
            enemy.setHealth(enemy.getHealth() * heroLevel * 3);
            enemy.setDamage(enemy.getDamage() * heroLevel * 2);
        }
        return enemy;
    }
}
