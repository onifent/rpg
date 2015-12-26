package com.nuclearthinking.game;

import com.nuclearthinking.game.config.Config;
import com.nuclearthinking.game.data.SkillData;
import com.nuclearthinking.game.model.skills.Skill;
import com.nuclearthinking.game.utils.ResourceUtil;
import com.nuclearthinking.game.utils.UserInput;
import com.nuclearthinking.game.utils.ResourceUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Created
 * Date: 23.12.2015
 * Time: 12:28
 *
 * @author kuksin-mv
 */
public final class GameStarter
{
    //private static final Logger LOG = Logger.getLogger(GameStarter.class.getName());

    private static final String LOG_FOLDER = "log";
    private static final String LOG_NAME = "log.cfg";

    private static final ResourceUtil resource = new ResourceUtil();

    public static GameStarter gameStarter;
    private UserInput input = new UserInput();

    public GameStarter() throws Exception
    {
        SkillData.getInstance();
        Game.getInstance();


        //Секция для дебага. Настройка в general конфиге
        if (Config.DEBUG)
        {
            SkillData.getInstance().getSkill(1, 10);
        }
    }

    public static void main(String[] args) throws Exception
    {
        File logFile = new File(LOG_FOLDER);
        logFile.mkdir();

        Config.load();

        gameStarter = new GameStarter();
    }

        InputStream is = resource.getResourceAsStream(LOG_NAME);
        LogManager.getLogManager().readConfiguration(is);
    }
}
