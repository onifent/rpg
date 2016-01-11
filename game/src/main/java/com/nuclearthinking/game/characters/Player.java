package com.nuclearthinking.game.characters;

/**
 * Date: 22.12.2015
 * Time: 19:52
 *
 * @author Vladislav Radchenko (onifent@gmail.com)
 */

public class Player extends GameCharacter {

    private static final double DIFFICULITY = 1.0;
    private PlayerClass pClass;
    private int level = 1;
    private String name;
    private double strength = 10;
    private double intelligence = 10;
    private double agility = 10;
    private double stamina = 10;

    public Player() {
    }


    public void levelUP() {

        if (getpClass() instanceof RogueClass) {
            RogueClass rClass = new RogueClass();
            rClass.levelUp(this);
            this.level++;
        } else {
            if (getpClass() instanceof MageClass) {
                MageClass mClass = new MageClass();
                mClass.levelUp(this);
                this.level++;
            } else {
                if (getpClass() instanceof WarriorClass) {
                    WarriorClass wClass = new WarriorClass();
                    wClass.levelUp(this);
                    this.level++;
                } else {
                    throw new RuntimeException("У обьекта" + this.getName() + "не задан класс :" + pClass + "=" + pClass.toString());
                }
            }
        }
    }

    protected void addStrenght(double amount) {
        strength = strength + amount;
    }

    protected void addIntelegence(double amount) {
        intelligence = intelligence + amount;
    }

    protected void addAgility(double amount) {
        agility = agility + amount;
    }

    protected void addStamina(double amount) {
        stamina = stamina + amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public PlayerClass getpClass() {
        return pClass;
    }

    public void setClass(PlayerClass pClass) {
        this.pClass = pClass;
    }

    public double getStrength() {
        return strength;
    }

    public double getIntelligence() {
        return intelligence;
    }

    public double getAgility() {
        return agility;
    }

    public double getStamina() {
        return stamina;
    }

}