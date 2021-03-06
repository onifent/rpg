package com.nuclearthinking.game.app.controller;

import com.nuclearthinking.game.app.StartApp;
import com.nuclearthinking.game.app.TestScene;
import com.nuclearthinking.game.app.alldrow.ObjectWorld;
import com.nuclearthinking.game.engines.PreparePlayer;
import com.nuclearthinking.game.player.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
 * Created by kuksin-mv on 28.01.2016.
 */
public class CreateCharacterController
{
    private static final StartApp startApp = new StartApp();
    //Возможно он не должен быть статичным
    private static Player player;

    @FXML
    private TextField playerName;

    @FXML
    private ChoiceBox selectClass;

    @FXML
    private Button create, back;

    @FXML
    private void initialize()
    {
        selectClass.getItems().addAll("Mag", "Warrior", "Rogue");
        selectClass.getSelectionModel().selectFirst();

        create.setOnAction(event ->
        {
            player = new PreparePlayer(playerName.getText(), (String) selectClass.getSelectionModel().getSelectedItem()).getPlayer();
            initGameScene();
        });

        back.setOnAction(event -> startApp.loadOverview(startApp.getBundle(), "fxml\\auth.fxml"));
    }

    //TODO: Тут должно быть создание и заселение мира, и добавление туда созданного\загруженного персонажа
    //Тут два способа загрузки, я пока не могу определитсья нужен MenuBar или нет
    //Если не нужен, то раскоменировать и закоменитить строчку -> startApp.getRootLayout().setCenter(container.getSurface());
    public void initGameScene()
    {
        ObjectWorld world = new TestScene(60, "Test Frame");
        world.initialize(startApp.getPrimaryStage());
        world.beginGameLoop();
        startApp.getPrimaryStage().show();
    }

    public Player getPlayer()
    {
        return player;
    }

}
