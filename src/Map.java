public class Map {

    private final char[][] map;
    private char buffer;

    {
        map = new char[3][];
        map[0] = new char[]{'0', '0', '0', '0', '0'};
        map[1] = new char[]{'0', 'h', '1', '1', '1'};
        map[2] = new char[]{'0', '0', '0', '0', '0'};
        buffer = '1';
    }

    public char[][] getMap() {
        return map;
    }

    public void showMap(){
        for (char[] i : map) {
            for (char j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    public void setHeroPosition(int x, int y, int x1, int y1) {
        map[y][x] = buffer;
        buffer = map[y1][x1];
        map[y1][x1] = 'h';
    }
}
