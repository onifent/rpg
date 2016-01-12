package com.nuclearthinking.game.obj;

import com.nuclearthinking.game.engines.ClassConfigReader;
import com.nuclearthinking.game.obj.jsonpojo.MageConfig;

/**
 * Date: 25.12.2015
 * Time: 13:06
 *
 * @author Vladislav Radchenko (onifent@gmail.com)
 */

public class MageClass extends PlayerClass implements IPlayerClass {
    MageConfig mConfig;
    ClassConfigReader classConfigReader = ClassConfigReader.getInstance();

    public MageClass() {
        mConfig = classConfigReader.getClassConfig().getMageConfig();
    }

    @Override
    public int pDamage(Player player) {
        double pDmg = player.getAgility() / 2 + player.getStrength() / 2 + player.getLevel();
        return (int) pDmg;
    }

    @Override
    public int mDamage(Player player) {
        double mDmg = player.getIntelligence() * 0.8 + player.getLevel() * 2;
        return (int) mDmg;
    }

    @Override
    public void levelUp(Player player) {
        player.addStrenght(mConfig.getStrengthRate());
        player.addStamina(mConfig.getStaminaRate());
        player.addIntelegence(mConfig.getIntelligenceRate());
        player.addAgility(mConfig.getAgilityRate());
    }

    @Override
    public int mDefence(Player player) {
        double mDef = player.getIntelligence() * 1.5;
        return (int) mDef;
    }

    @Override
    public int pDefence(Player player) {
        double pDef = player.getStrength() / 2 + player.getAgility() / 2;
        return (int) pDef;
    }

}