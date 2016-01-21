package com.nuclearthinking.game.DAO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Date: 19.01.2016
 * Time: 18:03
 *
 * @author Vladislav Radchenko (onifent@gmail.com)
 */

@Entity
public class Armor {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Integer armorType;
    private Integer strengthBonus;
    private Integer agilityBonus;
    private Integer intelligenceBonus;

    public Armor() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getArmorType() {
        return armorType;
    }

    public void setArmorType(Integer armorType) {
        this.armorType = armorType;
    }

    public Integer getStrengthBonus() {
        return strengthBonus;
    }

    public void setStrengthBonus(Integer strengthBonus) {
        this.strengthBonus = strengthBonus;
    }

    public Integer getAgilityBonus() {
        return agilityBonus;
    }

    public void setAgilityBonus(Integer agilityBonus) {
        this.agilityBonus = agilityBonus;
    }

    public Integer getIntelligenceBonus() {
        return intelligenceBonus;
    }

    public void setIntelligenceBonus(Integer intelligenceBonus) {
        this.intelligenceBonus = intelligenceBonus;
    }

    public Integer getArmorAmount() {
        return armorAmount;
    }

    public void setArmorAmount(Integer armorAmount) {
        this.armorAmount = armorAmount;
    }

    private Integer armorAmount;
}