package com.grimaldi;

import java.util.List;

public class Healer extends SpellCaster {
    public Healer() {
        this.manaPoints = 5;
        this.lifePoints = 10;
        this.armor = 10;
        this.weaponDamage = 3;
        this.potions = null;
        this.lembas = null;
    }

    @Override
    public int attack() {
        if (manaPoints == 0) {
            return 0;
        }
        // Launch arrow
        manaPoints--;
        return weaponDamage;
    }

    @Override
    public void defend() {

    }

    @Override
    public void useConsumable(Consumable consumable) {

    }
}
