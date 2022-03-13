package com.grimaldi;

import java.util.Arrays;

public class Game {
    protected Hero[] heroes;
    protected int playerTurn;
    protected InputParser inputParser;

    public void playGame() {
        int numberOfHeroes = selectNumberOfHeroes();
        createHeroes(numberOfHeroes);

        generateCombat();
    }

    public void generateCombat() {
        // Play until all players are dead
        while (isAPlayerAlive()) {
            Enemy enemy = generateRandomEnemy();
            enemy.setLifePoints(4);

            // Continue level while enemy is alive
            while (enemy.getLifePoints() > 0) {
                int[] entityTurnOrder = generateEntityTurnOrder();
                for (int i = 0; i < entityTurnOrder.length; i++) {
                    // Check what entity's turn it is
                    if (entityTurnOrder[i] != entityTurnOrder.length - 1) {
                        // Player turn
                        playerTurn = entityTurnOrder[i];
                        chooseHeroAction(heroes[playerTurn], enemy);
                    } else {
                        // Enemy turn
                        enemyAttacksHero(enemy);
                    }
                }
            }
        }

        // Game over
        System.out.println("Tout le monde est mort..");
    }

    private boolean isAPlayerAlive() {
        // Check if a player is alive
        for (Hero hero : heroes) {
            if (hero.getLifePoints() > 0) {
                return true;
            }
        }

        return false;
    }

    private int selectNumberOfHeroes() {
        return InputParser.questionWithIntOutput("Combien de héros comptent embarquer dans l'aventure ? ");
    }

    private void createHeroes(int numberOfHeroes) {
        heroes = new Hero[numberOfHeroes];

        for (int i = 0; i < heroes.length; i++) {
            Hero hero = heroes[i];

            while (hero == null) {
                String question = String.format("De quelle classe est le héros %d ? ", i + 1);
                String heroClass = InputParser.questionWithStringOutput(question);

                // Parse hero class
                if (heroClass.equals("Hunter")) {
                    hero = new Hunter();
                } else if (heroClass.equals("Healer")) {
                    hero = new Healer();
                } else if (heroClass.equals("Mage")) {
                    hero = new Mage();
                } else if (heroClass.equals("Warrior")) {
                    hero = new Warrior();
                }
            }

            // Add hero to list of heroes
            heroes[i] = hero;
        }
    }

    private int[] generateEntityTurnOrder() {
        int[] entityTurnOrder = new int[heroes.length + 1];

        // Generate ordered list for turns
        for (int i = 0; i < entityTurnOrder.length; i++) {
            entityTurnOrder[i] = i;
        }

        return entityTurnOrder;
    }

    private Enemy generateRandomEnemy() {
        int randomValue = (int) (Math.random() * 10);

        if (randomValue < 8) {
            // Generate basic enemy
            System.out.println("\nUn ennemi basique a apparu.");
            return new BasicEnemy();
        }

        // Generate boss
        System.out.println("\nUn Boss a apparu.");
        return new Boss();
    }

    private void chooseHeroAction(Hero hero, Enemy enemy) {
        boolean isValidAction = false;

        while (!isValidAction) {
            // Prompt user to select action
            String options = String.format("Héros %d peut :\n1. Attaquer\n2. Défendre\n3. Utiliser un consommable\n",
                    playerTurn + 1);
            String question = String.format("Que veut faire héros %d ? ", playerTurn + 1);
            int actionOption = InputParser.questionWithIntOutput(options + question);

            switch (actionOption) {
                case 1:
                    // Attack action
                    isValidAction = true;

                    // if the current hero is a healer
                    if (hero.getClass().getSimpleName().equals("Healer")) {
                        // Heal
                        heal(hero.attack());
                        break;
                    }

                    // Attack
                    attack(enemy, hero.attack());

                    break;
                case 2:
                    // Defend action
                    isValidAction = true;
                    hero.defend();

                    break;
                case 3:
                    // Use consumable action
                    isValidAction = true;
                    hero.useConsumable(new Potion());

                    System.out.println("Il n'y a plus de consommables.");
                    break;
                default:
                    break;
            }
        }
    }

    private void enemyAttacksHero(Enemy enemy) {
        int randomIndex = (int) (Math.random() * heroes.length);
        Hero randomHero = heroes[randomIndex];
        if (randomHero.getLifePoints() <= 0) {
            System.out.println("L'ennemi a loupé son attaque.");
        } else {
            // Attack hero
            randomHero.setLifePoints(randomHero.getLifePoints() - enemy.getDamage());

            // Display result of attack
            String actionOutput = String.format("\nL'ennemi a attaqué héros %d", randomIndex + 1);
            System.out.println(actionOutput);

            int heroLifePoints = randomHero.getLifePoints();
            String heroStatusOutput;
            if (heroLifePoints > 0) {
                heroStatusOutput = String.format("Héros %d a %d PV", randomIndex + 1, heroLifePoints);
            } else {
                heroStatusOutput = String.format("Héros %d est mort OX", randomIndex + 1);
            }
            System.out.println(heroStatusOutput);
        }
    }

    private void attack(Enemy target, int weaponDamage) {
        target.setLifePoints(target.getLifePoints() - weaponDamage);

        int targetLifePoints = target.getLifePoints();
        String enemyStatus = targetLifePoints > 0 ? String.format("L'ennemi a %d PV", target.getLifePoints())
                : "L'ennemi est mort";
        System.out.println(enemyStatus);
    }

    private void heal(int lifePoints) {
        for (Hero hero : heroes) {
            hero.setLifePoints(hero.getLifePoints() + lifePoints);
        }
    }
}
