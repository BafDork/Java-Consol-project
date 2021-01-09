package game_pack;

public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.main();
    }

    public static void cls(){
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
