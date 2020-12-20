import java.util.HashMap;
import java.util.Scanner;

public abstract class Interface {

    public final Scanner sc;
    private boolean running;
    private final HashMap<Integer, String> description;

     {
        sc = new Scanner(System.in);
        running = true;
        description = new HashMap<Integer, String>();
    }

    public void  addDescription(Integer key, String value) {
        description.put(key, value);
    }

    public void printDescription() {
        for (Integer key: description.keySet()) {
            System.out.println(key + ": " + description.get(key));
        }
    }

    public void invalidAction() {
        System.out.println("Invalid action");
    }

    public boolean stillRun() {
        return running;
    }

    public void exit() {
        running = false;
    }

}

