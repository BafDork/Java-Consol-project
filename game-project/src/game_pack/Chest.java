package game_pack;

import java.util.ArrayList;

public class Chest extends Addition {

    private final Inventory inventory;
    private final ArrayList<String> openChests;
    private final String[] weapons;
    private final String[] potions;
    private final int common;
    private final int rare;
    private final int epic;

    {
        weapons = new String[] {"Stick", "Baton", "Sword", "Nagibator"};
        potions = new String[] {"Water", "Potion"};
        openChests = new ArrayList<>();
        inventory = getInventory();
        common = 70;
        rare = 20;
        epic = 10;
    }

    public Chest() {}

    public void openChest(String xy) {
        if (openChests.contains(xy)) {
            setMessage("You have already opened this chest");
            return;
        }
        Item item = null;
        int quantity = 1;
        if (random.nextBoolean()) {
            item = generateWeapon();
        }
        else {
            quantity = rnd(1, 3);
            item = generatePotion();
        }
        inventory.addItem(item, quantity);
        openChests.add(xy);
        setMessage("you got " + quantity + " " + item.getName());
    }

    public Weapon generateWeapon() {
        int[] damage = generateMas(10, 30, 70, 100);
        int damageValue = damage[random.nextInt(damage.length)];
        String name = weapons[random.nextInt(weapons.length)];
        return new Weapon(name, damageValue);

    }

    public Potion generatePotion() {
        int[] heal = generateMas(10, 20, 50, 100);
        int healValue = heal[random.nextInt(heal.length)];
        String name = potions[random.nextInt(potions.length)];
        return new Potion(name, healValue);

    }

    public int[] generateMas(int a, int b, int c, int d) {
        int[] mas = new int[100];
        for (int i = 0; i < common; i++) {
            mas[i] = rnd(a, b);
        }
        for (int i = common; i < rare; i++) {
            mas[i] = rnd(b, c);
        }
        for (int i = common + rare; i < epic; i++) {
            mas[i] = rnd(c, d);
        }
        return mas;
    }

}