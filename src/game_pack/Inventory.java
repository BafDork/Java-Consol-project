package game_pack;

import java.util.HashMap;

public class Inventory extends Addition {

    private final HashMap<Item, Integer> inventory;
    private final String saveFile;

    {
        inventory = new HashMap<>();
        saveFile = "game_data/saves/inventory.txt";
    }

    public Inventory() {}

    public void main() {
        Main.cls();
        while (isRunning()) {
            Graphics.showFrame();
            printMessage();
            printDescription();
            String action = sc.nextLine().toLowerCase();
            dispatcher(Integer.parseInt(action));
        }
    }

    private void dispatcher(int action) {
        if (action >= 1 && action <= inventory.size()) {
            Item item = (Item) inventory.keySet().toArray()[action - 1];
            item.useItem();
            setMessage("You use " + item.getName());
            return;
        }
        if (action == inventory.size() + 1) {
            setRunning(false);
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
            if (item.getDamage() != null) {
                System.out.println(caseInt++ + " : Use " + item.getName() + " (" +
                        inventory.get(item) + ") damage - " + item.getDamage());
            }
            if (item.getHeal() != null) {
                System.out.println(caseInt++ + " : Use " + item.getName() + " (" +
                        inventory.get(item) + ") heal - " + item.getHeal());
            }
        }
        System.out.println(caseInt + " : Exit");
    }

    public void saveInventory() {
        String[] save = new String[inventory.size()];
        int damage = 0, heal = 0;
        int ind = 0;
        for (Item item : inventory.keySet()) {
            if (item.getDamage() != null) {
                damage = item.getDamage();
            }
            if (item.getHeal() != null) {
                heal = item.getHeal();
            }
            save[ind++] = item.getName() + "." + damage + "." + heal + ":" + inventory.get(item);
        }
        FileWork.saveToFile(saveFile, save);
    }

    public boolean loadInventory() {
        String[] save = FileWork.loadFromFile(saveFile);
        if (save != null) {
            for (String line : save) {
                String[] item = line.split(":")[0].split("\\.");
                Integer quantity = Integer.parseInt(line.split(":")[1]);
                int damage = Integer.parseInt(item[1]), heal = Integer.parseInt(item[2]);
                String name = item[0];
                if (damage != 0) {
                    addItem(new Weapon(name, damage), quantity);
                }
                if (heal != 0) {
                    addItem(new Potion(name, heal), quantity);
                }
            }
            return true;
        }
        else {
            System.out.println("The file is empty");
            return false;
        }
    }
}
