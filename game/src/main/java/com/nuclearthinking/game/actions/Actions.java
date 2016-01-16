package com.nuclearthinking.game.actions;

import com.nuclearthinking.game.characters.Player;
import com.nuclearthinking.game.obj.world.World;

import java.util.HashMap;
import java.util.Map;

/**
 * Date: 15.01.2016
 * Time: 16:13
 *
 * @author Vladislav Radchenko (onifent@gmail.com)
 */

public class Actions {

    Player player;
    World world;

    public Actions(Player player, World world) {
        this.player = player;
        this.world = world;
    }

    public Map<String, Action> getAviableActions() {
        Map<String, Action> actionsList = new HashMap<String, Action>();
        gatherNavigationActions(actionsList);

        return actionsList;
    }

    protected void gatherNavigationActions(Map<String, Action> actionList) {
        Map<String, Action> tempActionList = new HashMap<String, Action>();
        int currentRoom = player.getCurrentRoom();
        int currentFloor = player.getCurrentFloor();
        int floorSize = world.getWorldArray().get(currentFloor).getFloorSize();

        if (currentRoom == 1) {
            Navigate navigate = new Navigate(player, world);
            navigate.setRoomModificator(player.getCurrentRoom() + 1);
            tempActionList.put("Следующая комната", navigate);
        } else {
            if (currentRoom == floorSize) {

                Navigate navigateNextFloor = new Navigate(player, world);
                navigateNextFloor.setRoomModificator(1);
                navigateNextFloor.setFloorModificator(player.getCurrentFloor() + 1);
                tempActionList.put("Следующий этаж", navigateNextFloor);

                Navigate navigatePreviousRoom = new Navigate(player, world);
                navigatePreviousRoom.setRoomModificator(player.getCurrentRoom() - 1);
                tempActionList.put("Предыдущая комната", navigatePreviousRoom);
            } else {
                Navigate navigateNextRoom = new Navigate(player, world);
                navigateNextRoom.setRoomModificator(player.getCurrentRoom() + 1);
                tempActionList.put("Следующая комната", navigateNextRoom);

                Navigate navigatePreviousRoom = new Navigate(player, world);
                navigatePreviousRoom.setRoomModificator(player.getCurrentRoom() - 1);
                tempActionList.put("Предыдущая комната", navigatePreviousRoom);
            }
        }
        actionList.putAll(tempActionList);
    }
}
