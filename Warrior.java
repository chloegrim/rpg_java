package com.grimaldi;

public class Warrior extends Hero {
    public Warrior() {
        this.lifePoints = 10;
        this.armor = 10;
        this.weaponDamage = 4;
        this.potions = null;
        this.lembas = null;
    }

    @Override
    public int attack() {
        return weaponDamage;
    }

    @Override
    public void defend() {

    }

    @Override
    public void useConsumable(Consumable consumable) {

    }
}
