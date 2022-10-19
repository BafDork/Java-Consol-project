package game_pack;

public class Menu extends Addition {

    private Game game;
    private final Map map;

    {
        map = getMap();
        addDescription(new String[] {"New Game", "Continue", "Load Game", "Save", "Exit"});
    }

    public Menu() {}

    public void main() {
        Main.cls();
        Graphics.loadGraphics();
        while (isRunning()) {
            Graphics.showFrame("name", false);
            printDescription();
            String action = sc.nextLine().toLowerCase();
            dispatcher(action);
        }
    }

    private void dispatcher(String action) {
        switch (action) {
            case "1", "new game" -> startGame(true);
            case "2", "continue" -> returnGame();
            case "3", "load game" -> startGame(false);
            case "4", "save" -> saveGame();
            case "5", "exit" -> setRunning(false);
            default -> invalidAction();
        }
    }

    private void startGame(boolean flag) {
        Addition.newGame();
        game = new Game();
        Addition.setGame(game);
        game.loadLevel(flag);
    }

    private void returnGame() {
         if (checkGame()) {
             game.setRunning(true);
             game.main();
         }
    }

    private void saveGame() {
        if (checkGame()) {
            map.saveMap();
        }
    }

    private boolean checkGame() {
        if (game == null) {
            System.out.println("You haven't even played yet");
            return false;
        }
        if (!game.getHero().isAlive()) {
            System.out.println("You dead");
            return false;
        }
        return true;
    }

}