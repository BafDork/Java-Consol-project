package game_pack;

public class Game extends Addition {

    private final Map map;
    private final Hero hero;
    private final Enemy enemy;
    private final Chest chest;
    private final Inventory inventory;
    private final Character[] view;

    {
        map = getMap();
        hero = getHero();
        chest = new Chest();
        enemy = new Enemy();
        inventory = getInventory();
        view = new Character[] {'r', '0', '0'};
        addDescription(new String[] {"Forward", "Rightwards", "Leftwards",
                "Backward", "Turn Around", "Inventory", "Menu"});
    }

    public Game() {}

    public void main() {
        getDirection();
        while (isRunning() && hero.isAlive()) {
            Main.cls();
            showHeroFrame();
            printMessage();
            System.out.println("Hero: health - " + hero.getHealth() + ", weapon: " + "name - "
                    + hero.getWeapon().getName() + " damage - " + hero.getWeapon().getDamage());
            printDescription();
            String action = sc.nextLine().toLowerCase();
            dispatcher(action);
            checkPosition();
        }
    }

    private void dispatcher(String action) {
        switch (action) {
            case "1", "forward" -> calculateHeroCoordinates('f');
            case "2", "rightwards" -> calculateHeroCoordinates('r');
            case "3", "leftwards" -> calculateHeroCoordinates('l');
            case "4", "backward" -> calculateHeroCoordinates('d');
            case "5", "turn around" -> turnAround();
            case "6", "inventory" -> {
                inventory.setRunning(true);
                inventory.main();}
            case "7", "menu" -> setRunning(false);
            default -> invalidAction();
        }
    }

    public void loadLevel(boolean newGame) {
        if (newGame) {
            map.generateMap();
            main();
        }
        else {
            if (map.loadMap() && hero.loadLevel() && inventory.loadInventory()) {
                int[] pos = map.getHeroPosition();
                if (pos != null) {
                    hero.setXY(pos[0], pos[1]);
                    main();
                }
                else {
                    System.out.println("There is no hero on the map");
                }
            }
            else {
                System.out.println("Error loading the game");
            }
        }
    }

    private void getDirection() {
        int x = hero.getX(), y = hero.getY();
        char[][] mapArray = map.getMapArray();
        if (mapArray[y][x + 1] == '1') {
            view[0] = 'r';
            return;
        }
        if (y - 1 >= 0 && mapArray[y - 1][x] == '1') {
            view[0] = 'f';
            return;
        }
        if (y + 1 < mapArray.length && mapArray[y + 1][x] == '1') {
            view[0] = 'd';
        }
    }

    private void turnAround() {
        switch (view[0]) {
            case 'f' -> view[0] = 'd';
            case 'd' -> view[0] = 'f';
            case 'r' -> view[0] = 'l';
            case 'l' -> view[0] = 'r';
        }
    }

    private void checkPosition() {
        if (map.getBuffer() == 'e') {
            map.setBuffer('1');
            hero.newLevel();
            System.out.println("You passed the level");
            loadLevel(true);
            main();
        }
        if (map.getBuffer() == 'f') {
            new Fight(enemy.generateEnemy(hero.getLevel()));
        }
        if (map.getBuffer() == 'c') {
            chest.openChest();
        }
    }

    public void calculateHeroCoordinates(char direction) {
        int x = hero.getX(), y = hero.getY();
        char view = this.view[0];
        if ((direction == 'f' && view == 'd') ||
                (direction == 'd' && view == 'f')) {
            checkCoordinates(x, y + 1, new Character[] {null, direction});
        }
        if ((direction == 'f' && view == 'f') ||
                (direction == 'd' && view == 'd')) {
            checkCoordinates(x, y - 1, new Character[] {null, direction});
        }
        if ((direction == 'f' && view == 'r') ||
                (direction == 'd' && view == 'l')) {
            checkCoordinates(x + 1, y, new Character[] {null, direction});
        }
        if ((direction == 'd' && view == 'r') ||
                (direction == 'f' && view == 'l')) {
            checkCoordinates(x - 1, y, new Character[] {null, direction});
        }
        if ((direction == 'r' && view == 'f') ||
                (direction == 'l' && view == 'd')) {
            checkCoordinates(x + 1, y, new Character[] {'r', direction});
            return;
        }
        if ((direction == 'r' && view == 'd') ||
                (direction == 'l' && view == 'f')) {
            checkCoordinates(x - 1, y, new Character[] {'l', direction});
            return;
        }
        if (direction == 'r' && view == 'r') {
            checkCoordinates(x, y + 1, new Character[] {'d', direction});
            return;
        }
        if (direction == 'l' && view == 'r') {
            checkCoordinates(x, y - 1, new Character[] {'f', direction});
            return;
        }
        if (direction == 'l' && view == 'l') {
            checkCoordinates(x, y + 1, new Character[] {'d', direction});
            return;
        }
        if (direction == 'r' && view == 'l') {
            checkCoordinates(x, y - 1, new Character[] {'f', direction});
        }
        if (direction == 'd' && this.view[1] != '0' && this.view[2] == '1') {
            this.view[0] = this.view[1];
            this.view[1] = '0';
        }
    }

    public void checkCoordinates(int x1, int y1, Character[] view) {
        int x = hero.getX(), y = hero.getY();
        char[][] mapArray = map.getMapArray();
        if (0 <= y1 && y1 < mapArray.length && 0 <= x1 && x1 < mapArray[y1].length && mapArray[y1][x1] != '0') {
            map.setHeroPosition(new int[] {x, y, x1, y1});
            hero.setXY(x1, y1);
            if (view[0] != null) {
                this.view[1] = this.view[0];
                this.view[0] = view[0];
                this.view[2] = '1';
            }
            else {
                if (view[1] != 'd') {
                    this.view[2] = '0';
                }
            }
        }
        else {
            System.out.println("You shall not pass!");
        }
    }

    public void showHeroFrame() {
        int x = hero.getX(), y = hero.getY();
        char[][] mapArray = map.getMapArray();
        char f = '0', r = '0', l = '0';
        switch (view[0]) {
            case 'f' -> {
                f = mapArray[y - 1][x];
                r = mapArray[y][x + 1];
                l = mapArray[y][x - 1];
            }
            case 'd' -> {
                f = mapArray[y + 1][x];
                r = mapArray[y][x - 1];
                l = mapArray[y][x + 1];
            }
            case 'r' -> {
                f = mapArray[y][x + 1];
                r = mapArray[y + 1][x];
                l = mapArray[y - 1][x];
            }
            case 'l' -> {
                f = mapArray[y][x - 1];
                r = mapArray[y - 1][x];
                l = mapArray[y + 1][x];
            }
        }
        String frame = "deadEnd";
        if (f != '0' && r != '0' && l != '0') {
            frame = "allParties";
        }
        if (f != '0' && r == '0' && l != '0') {
            frame = "leftForward";
        }
        if (f != '0' && r != '0' && l == '0') {
            frame = "rightForward";
        }
        if (f == '0' && r != '0' && l != '0') {
            frame = "right-left";
        }
        if (f == '0' && r == '0' && l != '0') {
            frame = "left";
        }
        if (f == '0' && r != '0' && l == '0') {
            frame = "right";
        }
        if (f != '0' && r == '0' && l == '0') {
            frame = "forward";
        }
        if (f == 'e' || r == 'e' || l == 'e') {
            frame = "exit";
        }
        if (map.getBuffer() == 'c') {
            frame = "chest";
            map.setMapPosition(hero.getX(), hero.getY(), '1');
            map.setBuffer('1');
        }
        Graphics.setFrame(frame);
        Graphics.showFrame();
    }

}