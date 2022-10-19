package game_pack;

public class Hero extends Creature {

    private Weapon weapon;
    private int x;
    private int y;
    private int level;
    private int chanceCriticalDamage;
    private final String saveFile;

    {
        level = 0;
        chanceCriticalDamage = 10;
        setName("Hero");
        setHealth(100);
        weapon = new Weapon("Hand", 5);
        saveFile = "game_data/saves/level.txt";
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

    public void saveLevel() {
        String[] save = new String[1];
        save[0] = Integer.toString(level);
        FileWork.saveToFile(saveFile, save);
    }

    public boolean loadLevel() {
        String[] save = FileWork.loadFromFile(saveFile);
        if (save != null) {
            level = Integer.parseInt(save[0]);
            return true;
        }
        else {
            System.out.println("The file is empty");
            return false;
        }
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
        System.out.println("Hero set " + weapon.getName());
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void newLevel() {
        level++;
    }

    public int getLevel() {
        return level;
    }

    public int getChanceCriticalDamage() {
        return chanceCriticalDamage;
    }

    public void setChanceCriticalDamage(int chanceCriticalDamage) {
        this.chanceCriticalDamage = chanceCriticalDamage;
    }
}