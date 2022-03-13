package com.grimaldi;

public class Hunter extends Hero {
    private int arrows;

    public Hunter() {
        this.lifePoints = 10;
        this.armor = 10;
        this.weaponDamage = 3;
        this.potions = null;
        this.lembas = null;
    }

    @Override
    public int attack() {
        if (arrows == 0) {
            return 0;
        }
        // Launch arrow
        arrows--;
        return weaponDamage;
    }

    @Override
    public void defend() {

    }

    @Override
    public void useConsumable(Consumable consumable) {

    }
}
