package game_pack;

public class Map extends Addition {

    private char[][] map;
    private char buffer;
    private final int levelSize;
    private final int minEnemyCount;
    private final int minChestCount;
    private final String saveFile;

    {
        buffer = '1';
        levelSize = 5;
        minEnemyCount = 7;
        minChestCount = 5;
        saveFile = "game_data/saves/map.txt";
    }

    public Map() {}

    public void setHeroPosition(int[] xy) {
        map[xy[1]][xy[0]] = buffer;
        if (buffer  == 'c') {
            map[xy[1]][xy[0]] = '1';
        }
        buffer = map[xy[3]][xy[2]];
        map[xy[3]][xy[2]] = 'h';
    }

    public int[] getHeroPosition() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 'h') {
                    return new int[]{j, i};
                }
            }
        }
        return null;
    }

    public void generateMap() {
        int levelSize = this.levelSize + getHero().getLevel();
        Ellers eller = new Ellers(levelSize, levelSize);
        eller.makeMaze();
        map = eller.getMaze();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (j == 0 && map[i][j] == '1') {
                    map[i][j] = '0';
                    map[i][j + 1] = 'h';
                    getHero().setXY(j + 1, i);
                }
                if (j == map[i].length - 1 && map[i][j] == '1') {
                    char[] newLine = new char[map[i].length + 1];
                    System.arraycopy(map[i], 0, newLine, 0, map[i].length);
                    newLine[j + 1] = 'e';
                    map[i] = newLine;
                }
            }
        }
        generateChests();
        generateEnemies();
    }

    private void generateChests() {
        int count = 0, maxCount = minChestCount + getHero().getLevel();
        boolean flag = true;
        while (count <= maxCount && flag) {
            flag = false;
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (map[i][j] == '1' && checkDirections(i, j) == 3 && random.nextBoolean() && count <= maxCount) {
                        map[i][j] = 'c';
                        flag = true;
                        count++;
                    }
                }
            }
        }
    }

    private void generateEnemies() {
        int count = 0, maxCount = minEnemyCount + getHero().getLevel();
        boolean flag = true;
        while (count <= maxCount && flag) {
            flag = false;
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (map[i][j] == '1' && rnd(0, 15) == rnd(0, 10) && count <= maxCount) {
                        map[i][j] = 'f';
                        flag = true;
                        count++;
                    }
                }
            }
        }
    }

    public void saveMap() {
        String[] save = new String[map.length];
        for (int i = 0; i < map.length; i++) {
            save[i] =  new String(map[i]);
        }
        FileWork.saveToFile(saveFile, save);
    }

    public boolean loadMap() {
        String[] save = FileWork.loadFromFile(saveFile);
        if (save != null) {
            map = new char[save.length][];
            for (int i = 0; i < save.length; i++) {
                map[i] = save[i].toCharArray();
            }
            return true;
        }
        else {
            System.out.println("The file is empty");
            return false;
        }
    }

    private int checkDirections(int i, int j) {
        int f = 0, d = 0, l = 0, r = 0;
        if (i - 1 >= 0 && map[i - 1][j] == '0') {
            f = 1;
        }
        if (i + 1 < map.length && map[i + 1][j] == '0') {
            d = 1;
        }
        if (j - 1 >= 0 && map[i][j - 1] == '0') {
            l = 1;
        }
        if (j + 1 < map[i].length && map[i][j + 1] == '0') {
            r = 1;
        }
        return f + d + l + r;
    }

    public void setMapPosition(int x, int y, char value) {
        if (y >= 0 && x >= 0 && y < map.length && x < map[y].length) {
            map[y][x] = value;
        }
    }

    public void showMap() {
        for (char[] i : map) {
            for (char j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    public char[][] getMapArray() {
        return map;
    }

    public char getBuffer() {
        return buffer;
    }

    public void setBuffer(char buffer) {
        this.buffer = buffer;
    }

}
