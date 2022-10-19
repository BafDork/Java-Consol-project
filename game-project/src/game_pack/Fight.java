package game_pack;

public class Fight extends Addition {

    private Enemy enemy;
    private final Hero hero;

    {
        hero = getHero();
        addDescription(new String[] {"Hit", "Inventory", "Run away"});
    }

    public Fight(Enemy enemy) {
        setEnemy(enemy);
        main();
    }

    public void main() {
        Main.cls();
        while (isRunning()) {
            Graphics.showFrame(enemy.getName(), true);
            printDescription();
            System.out.println("Hero: health - " + hero.getHealth() + ", weapon: " + "name - "
                    + hero.getWeapon().getName() + " damage - " + hero.getWeapon().getDamage());
            System.out.println(enemy.getName() + ": health - " + enemy.getHealth());
            String action = sc.nextLine().toLowerCase();
            dispatcher(action);
        }
    }

    private void dispatcher(String action) {
        switch (action) {
            case "1", "hit" -> hitEnemy();
            case "2", "inventory" -> getInventory().main();
            case "3", "run away" -> runAway();
            default -> invalidAction();
        }
    }

    private void hitEnemy() {
        enemy.hurt(hero.heroDamage());
        hero.hurt(enemy.getDamage());
        if (!enemy.isAlive()) {
            setRunning(false);
        }
        if (!hero.isAlive()) {
            setGame(null);
            setRunning(false);
            System.out.println("You dead");
        }
    }

    private void runAway() {
        getGame().calculateHeroCoordinates('d');
        setRunning(false);
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

}
