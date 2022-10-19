package game_pack;

import java.util.ArrayList;
import java.util.HashMap;

public class Chest extends Addition {

    private final Inventory inventory;
    private final HashMap<String, ArrayList<Weapon>> weapons;
    private final HashMap<String, ArrayList<Potion>> potions;
    private final String saveFile;
    private final int common;
    private final int rare;
    private final int epic;

    {
        saveFile = "game_data/items";
        weapons = new HashMap<>();
        potions = new HashMap<>();
        inventory = getInventory();
        loadWeapons();
        loadPotions();
        common = 70;
        rare = 20;
        epic = 10;
    }

    public Chest() {}

    public void openChest() {
        Item item;
        int quantity = 1;
        if (random.nextBoolean()) {
            item = generateWeapon();
        }
        else {
            quantity = rnd(1, 3);
            item = generatePotion();
        }
        inventory.addItem(item, quantity);
        if (item.getDamage() != null) {
            setMessage("you got " + quantity + " " + item.getName() + ": damage - " + item.getDamage());
        }
        if (item.getHeal() != null) {
            setMessage("you got " + quantity + " " + item.getName() + ": heal - " + item.getHeal());
        }
    }

    public Weapon generateWeapon() {
        Weapon weapon = null;
        int rnd = rnd(0, common + rare + epic);
        if (rnd <= common) {
            ArrayList<Weapon> commonWeapons = this.weapons.get("common");
            weapon = commonWeapons.get(random.nextInt(commonWeapons.size()));
        }
        if (rnd > common && rnd <= common + rare) {
            ArrayList<Weapon> rareWeapons = this.weapons.get("rare");
            weapon = rareWeapons.get(random.nextInt(rareWeapons.size()));
        }
        if (rnd > common + rare) {
            ArrayList<Weapon> epicWeapons = this.weapons.get("epic");
            weapon = epicWeapons.get(random.nextInt(epicWeapons.size()));
        }
        weapon.setDamage(weapon.getDamage() + getHero().getLevel() * 20);
        return weapon;
    }

    public Potion generatePotion() {
        Potion potion = null;
        int rnd = rnd(0, common + rare + epic);
        if (rnd <= common) {
            ArrayList<Potion> commonPotions = this.potions.get("common");
            potion = commonPotions.get(random.nextInt(commonPotions.size()));
        }
        if (rnd > common && rnd <= common + rare) {
            ArrayList<Potion> rarePotions = this.potions.get("rare");
            potion = rarePotions.get(random.nextInt(rarePotions.size()));
        }
        if (rnd > common + rare) {
            ArrayList<Potion> epicPotions = this.potions.get("epic");
            potion = epicPotions.get(random.nextInt(epicPotions.size()));
        }
        potion.setHeal(potion.getHeal() + getHero().getLevel() * 15);
        return potion;
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

    public void loadWeapons() {
        weapons.put("common", new ArrayList<>());
        weapons.put("rare", new ArrayList<>());
        weapons.put("epic", new ArrayList<>());
        String[] save = FileWork.loadFromFile(saveFile + "/weapons.txt");
        if (save != null) {
            for (String line : save) {
                String[] item = line.split(":");
                weapons.get(item[2]).add(new Weapon(item[0], Integer.parseInt(item[1])));
            }
        }
        else {
            System.out.println("The file is empty");
        }
    }

    public void loadPotions() {
        potions.put("common", new ArrayList<>());
        potions.put("rare", new ArrayList<>());
        potions.put("epic", new ArrayList<>());
        String[] save = FileWork.loadFromFile(saveFile + "/potions.txt");
        if (save != null) {
            for (String line : save) {
                String[] item = line.split(":");
                potions.get(item[2]).add(new Potion(item[0], Integer.parseInt(item[1])));
            }
        }
        else {
            System.out.println("The file is empty");
        }
    }

}