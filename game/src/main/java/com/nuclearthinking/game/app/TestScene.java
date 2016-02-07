package com.nuclearthinking.game.app;

import com.nuclearthinking.game.app.alldrow.Atom;
import com.nuclearthinking.game.app.alldrow.ObjectWorld;
import com.nuclearthinking.game.app.alldrow.SpriteAnimation;
import com.nuclearthinking.game.app.alldrow.SpriteManager;
import com.nuclearthinking.game.app.controller.Input;
import com.nuclearthinking.game.app.map.MapContainer;
import com.nuclearthinking.game.app.utils.ManagerAudio;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by kuksin-mv on 04.02.2016.
 */
public class TestScene extends ObjectWorld
{
    private Input input;

    public static Pane appRoot = new Pane();
    public static Pane gameRoot = new Pane();
    public Atom player;
    public MapContainer mapContainer;
    public SpriteManager spriteManager = new SpriteManager();

    public TestScene(int fps, String title)
    {
        super(fps, title);
    }

    @Override
    public void initialize(Stage primaryStage)
    {
        //Создаем персонажа
        createPlayer();
        //Создаем мир
        createWorld();
        //Создаем геодату
        //TODO: Сделать геодату

        //Добавляем игрока на игровой слой
        gameRoot.getChildren().add(player);
        //Добавляем игровой слой и карту на главный слой приложения
        appRoot.getChildren().addAll(mapContainer, gameRoot);

        //Задаем тайтл
        primaryStage.setTitle(getWindowsTitle());
        //Создаем группу нод
        setSceneNode(new Group(appRoot));
        //Создаем сцену с группой нод и указанием разрешения
        setGameSurface(new Scene(getSceneNodes(), 640, 580));
        //Объявляем инпут
        input = new Input(getGameSurface());
        //Вклчаем слушатель для инпута
        input.addListeners();
        //Добавляем сцену на оболочку
        primaryStage.setScene(getGameSurface());
        //Рисуем все что есть
        primaryStage.show();
        //Запускаем музыку
        ManagerAudio.backMusicStart("/audio/bg.mp3");
    }

    private void createPlayer()
    {
        //Инициализируем игрока
        player = new Atom();
        //Указываем его положение на карте
        player.setTranslateX(10);
        player.setTranslateY(10);
        //Добавляем в спрайт менеджер
        //TODO: После переделки с канвасов он как бы не нужен, надо подумать над актуальностью
        spriteManager.addSprites(player);
    }

    private void createWorld()
    {
        //Инициализируем мир
        mapContainer = new MapContainer();
    }

    /**
     * На каждый фрейм вызывается этот апдейтер
     */
    @Override
    protected void updateSprites()
    {
        //Чекаем нажатые клавиши каждый фрейм
        player.input(input);
    }

    /**
     * TODO: Реализовать коллизии у объектов
     */
    @Override
    protected boolean handleCollision(SpriteAnimation spriteA, SpriteAnimation spriteB)
    {
        return false;
    }

    @Override
    protected void cleanupSprites()
    {
    }

}
