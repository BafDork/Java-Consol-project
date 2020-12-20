public class Game extends Interface{

    Map map;
    Hero hero;

    {
        addDescription(1, "Forward");
        addDescription(2, "Rightwards");
        addDescription(3, "Leftwards");
        addDescription(4, "Back");
        addDescription(5, "Menu");
        map = new Map();
        hero = new Hero();
    }

    public Game() {}

    public void main() {
        while (stillRun()) {
            printDescription();
            String action = sc.nextLine().toLowerCase();
            dispatcher(action);
        }
    }

    private void dispatcher(String action) {
        int x = hero.getX(), y = hero.getY();
        switch (action) {
            case "1", "forward" -> setHeroPosition(x + 1, y);
            case "2", "rightwards" -> setHeroPosition(x, y + 1);
            case "3", "leftwards" -> setHeroPosition(x, y - 1);
            case "4", "back" -> setHeroPosition(x - 1, y);
            case "5", "menu" -> exit();
            default -> invalidAction();
        }
    }

    private void setHeroPosition(int x1, int y1) {
        int x = hero.getX(), y = hero.getY();
        char[][] mapArray = map.getMap();
        if (0 <= y1 && y1 < mapArray.length && 0 <= x1 && x1 < mapArray[y1].length && mapArray[y1][x1] == '1') {
            map.setHeroPosition(x, y, x1, y1);
            hero.setXY(x1, y1);
        }
        else {
            System.out.println("You shall not pass!");
        }
        map.showMap();
    }

}
