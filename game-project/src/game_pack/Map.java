package game_pack;

public class Map {

    private char[][] map;
    private char buffer;
    private int levelNum;
    private final int levelMax;
    private final String saveFile;
    private final String levelFile;

    {
        buffer = '1';
        levelNum = 1;
        levelMax = 2;
        saveFile = "game_data/saves/save.txt";
        levelFile = "game_data/levels/level";
    }

    public Map() {};

    public void setHeroPosition(int[] xy) {
        map[xy[1]][xy[0]] = buffer;
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

    public boolean loadMap(Boolean newLevel) {
        String load = levelFile + levelNum + ".txt";
        if (!newLevel) {
            load = saveFile;
        }
        String[] level = FileWork.loadFromFile(load);
        if (level != null) {
            map = new char[level.length][];
            for (int i = 0; i < level.length; i++) {
                if (newLevel || i != level.length - 1) {
                    map[i] = level[i].toCharArray();
                }
                else {
                    levelNum = Integer.parseInt(level[i]);
                }
            }
            return true;
        }
        else {
            System.out.println("The file is empty");
            return false;
        }
    }

    public void saveMap() {
        String[] save = new String[map.length + 1];
        for (int i = 0; i < map.length; i++) {
            String str = new String(map[i]);
            save[i] = str;
        }
        save[map.length] = Integer.toString(levelNum);
        FileWork.saveToFile(saveFile, save);
    }

    public void showMap() {
        for (char[] i : map) {
            for (char j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    public char[][] getMap() {
        return map;
    }

    public void newLevel() {
        this.levelNum++;
    }

    public char getBuffer() {
        return buffer;
    }

    public void setBuffer(char buffer) {
        this.buffer = buffer;
    }

    public int getLevelNum() {
        return levelNum;
    }

    public void setLevelNum(int levelNum) {
        this.levelNum = levelNum;
    }

    public int getLevelMax() {
        return levelMax;
    }

}
