package com.nuclearthinking.game.characters;

import com.nuclearthinking.game.characters.interfaces.IName;
import com.nuclearthinking.game.characters.interfaces.IObjectId;

/**
 * Date: 11.01.2016
 * Time: 12:23
 *
 * @author Vladislav Radchenko (onifent@gmail.com)
 *
 * Пока что будет такой вот слабенький запил
 * Player - будет инстансится от GameCharacter
 * Npc - должен будет инстансится от Player
 * Нужно реализовать инстансы для создания разных объектов (Нпс, игрок и так далее)
 */

public abstract class GameCharacter implements IName, IObjectId
{
    private String _name;
    private int _objectId;

    public GameCharacter(){}

    public GameCharacter(int objectId)
    {
        _objectId = objectId;
    }

    public void onSpawn()
    {
    }

    public boolean canBeAttacked()
    {
        return false;
    }

    @Override
    public String getName()
    {
        return _name;
    }

    public void setName(String value)
    {
        _name = value;
    }

    @Override
    public final int getObjectId()
    {
        return _objectId;
    }


    // Вся бадяга ниже нужна что бы избавляться от instanceof
    public boolean isAttackable()
    {
        return false;
    }

    public boolean isCharacter()
    {
        return false;
    }

    public boolean isMonster()
    {
        return false;
    }

    public boolean isNpc()
    {
        return false;
    }

    public boolean isPlayer()
    {
        return false;
    }

    public boolean isTargetable()
    {
        return true;
    }

    @Override
    public boolean equals(Object obj)
    {
        return ((obj instanceof GameCharacter) && (((GameCharacter) obj).getObjectId() == getObjectId()));
    }

    @Override
    public String toString()
    {
        return (getClass().getSimpleName() + ":" + getName() + "[" + getObjectId() + "]");
    }
}