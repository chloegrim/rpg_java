package com.grimaldi;

import java.util.List;

public abstract class Hero {
    protected int lifePoints;
    protected int armor;
    protected int weaponDamage;
    protected List<Potion> potions;
    protected List<Food> lembas;

    public abstract int attack();

    public abstract void defend();

    public abstract void useConsumable(Consumable consumable);

    // Getters/setters
    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public int getArmor() {
        return armor;
    }

    public int getWeaponDamage() {
        return weaponDamage;
    }
}
