package game_pack;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public abstract class Addition {

    public static final Scanner sc;
    public static final Random random;
    private static Game game;
    private static Map map;
    private static Hero hero;
    private static String message;
    private static Inventory inventory;
    private final HashMap<Integer, String> description;
    private boolean running;

    static {
        random = new Random();
        sc = new Scanner(System.in);
        map = new Map();
        hero = new Hero();
        inventory = new Inventory();
        message = null;
    }

    {
        running = true;
        description = new HashMap<>();
    }

    public void addDescription(String[] descriptions) {
         for (int i = 1; i <= descriptions.length; i++) {
             description.put(i, descriptions[i - 1]);
         }
    }

    public void printDescription() {
        for (Integer key: description.keySet()) {
            System.out.println(key + ": " + description.get(key));
        }
    }

    public static void newGame() {
        map = new Map();
        hero = new Hero();
        inventory = new Inventory();
        setMessage(null);
    }

    public static void printMessage() {
        if (message != null) {
            System.out.println(message);
        }
        setMessage(null);
    }

    public static int rnd(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    public void invalidAction() {
        System.out.println("Invalid action");
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public static void setMessage(String message) {
        Addition.message = message;
    }

    public Map getMap() {
        return map;
    }

    public Hero getHero() {
        return hero;
    }

    public static Game getGame() {
        return game;
    }

    public static void setGame(Game game) {
        Addition.game = game;
    }

    public static Inventory getInventory() {
        return inventory;
    }
}

