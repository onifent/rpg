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

public class StartApp extends Application
{
    private static Stage primaryStage;
    private static BorderPane rootLayout;
    private static ResourceUtil resourceUtil = new ResourceUtil();
    private static ResourceBundle bundle;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        this.primaryStage = primaryStage;

        bundle = ResourceBundle.getBundle("bundles.Localization", Locale.getDefault());

        initRootLayout(bundle);
        loadOverview(bundle, "fxml\\auth.fxml");
    }

    public void initRootLayout(ResourceBundle bundle)
    {
        try(InputStream is = resourceUtil.getResourceAsStream("fxml\\mainframe.fxml"))
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(bundle);
            rootLayout = (BorderPane) loader.load(is);

            Scene scene = new Scene(rootLayout);
            scene.getStylesheets().add(StartApp.class.getResource("/css/but.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle(getBundle().getString("title"));
            primaryStage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void loadOverview(ResourceBundle bundle, String path)
    {
        if(path.length() <= 0 || path.isEmpty() || path == null)
        {
            //TODO:Запись в лог
            return;
        }

        try(InputStream is = resourceUtil.getResourceAsStream(path))
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(bundle);
            AnchorPane personOverview = (AnchorPane) loader.load(is);

            rootLayout.setCenter(personOverview);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage()
    {
        return primaryStage;
    }

    public ResourceBundle getBundle()
    {
        return bundle;
    }

    public BorderPane getRootLayout()
    {
        return rootLayout;
    }
}
