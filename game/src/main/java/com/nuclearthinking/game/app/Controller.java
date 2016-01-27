package com.nuclearthinking.game.app;/**
 * Created by Izonami on 25.01.2016.
 */

import com.nuclearthinking.game.utils.ResourceUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.ResourceBundle;

public class Controller extends Application
{
    private Stage primaryStage;
    private BorderPane rootLayout;
    private static ResourceUtil resourceUtil = new ResourceUtil();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("RPG");

        initRootLayout(new Locale("ru", "RU"));
        showPersonOverview(new Locale("ru", "RU"));
    }

    public void initRootLayout(Locale locale)
    {
        try(InputStream is = resourceUtil.getResourceAsStream("view\\mainframe.fxml"))
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(ResourceBundle.getBundle("bundles.Localization", locale));
            rootLayout = (BorderPane) loader.load(is);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void showPersonOverview(Locale locale)
    {
        try(InputStream is = resourceUtil.getResourceAsStream("view\\app.fxml"))
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(ResourceBundle.getBundle("bundles.Localization", locale));
            AnchorPane personOverview = (AnchorPane) loader.load(is);

            rootLayout.setCenter(personOverview);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage()
    {
        return primaryStage;
    }
}
