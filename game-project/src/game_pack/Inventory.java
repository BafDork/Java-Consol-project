package game_pack;

import java.util.HashMap;

public class Inventory extends Addition {

    private final HashMap<Item, Integer> inventory;

    {
        inventory = new HashMap<>();
    }

    public Inventory() {}

    public void main() {
        Main.cls();
        while (isRunning()) {
            Graphics.showFrame();
            printDescription();
            String action = sc.nextLine().toLowerCase();
            dispatcher(Integer.parseInt(action));
        }
    }

    private void dispatcher(int action) {
        if (action >= 1 && action <= inventory.size()) {
            Item item = (Item) inventory.keySet().toArray()[action - 1];
            item.useItem();
            return;
        }
        if (action == inventory.size() + 1) {
            setRunning(false);
            return;
        }
        invalidAction();
    }

    public void addItem(Item item, Integer quantity) {
        inventory.put(item, quantity);
    }

    public void removeItem(Item item, Integer num) {
        Integer quantity = inventory.get(item);
        if (quantity > num) {
            inventory.put(item, quantity - num);
        }
        else {
            inventory.remove(item);
        }
    }

    public void printDescription() {
        int caseInt = 1;
        for (Item item : inventory.keySet()) {
            System.out.println(caseInt++ + " : Use " + item.getName() + " (" + inventory.get(item) + ")");
        }
        System.out.println(caseInt + " : Exit");
    }
}
