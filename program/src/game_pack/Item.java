package game_pack;

public abstract class Item extends Addition {

    private String name;
    private Integer heal;
    private Integer damage;
    private final Hero hero;

    {
        heal = null;
        damage = null;
        hero = getHero();
    }

    public void useItem() {
        if (heal != null) {
            hero.heal(heal);
            getInventory().removeItem(this, 1);
        }
        if (damage != null) {
            hero.setWeapon(new Weapon(name, damage));
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHeal() {
        return heal;
    }

    public void setHeal(Integer heal) {
        this.heal = heal;
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }
}
