public class Menu extends Interface {

     {
        addDescription(1, "New Game");
        addDescription(2, "Continue");
        addDescription(3, "Save");
        addDescription(4, "Exit");
    }

    public Menu() {}

    public void main() {
        System.out.println("Тута название игры");
        while (stillRun()) {
            printDescription();
            String action = sc.nextLine().toLowerCase();
            dispatcher(action);
        }
    }

    private void dispatcher(String action) {
        switch (action) {
            case "1", "new game" -> startGame(true);
            case "2", "continue" -> startGame(false);
            case "3", "save" -> saveGame();
            case "4", "exit" -> exit();
            default -> invalidAction();
        }
    }

    private void startGame(boolean newGame) {
        Game game = new Game();
        game.main();
        // loadMap()
    }

    private void saveGame() {
        System.out.println("");
    }

}
;