package com.nuclearthinking.game.app.controller;

import com.nuclearthinking.game.DAO.Account;
import com.nuclearthinking.game.app.StartApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


/**
 * Created by kuksin-mv on 26.01.2016.
 */
public class LoginController
{
    private ObservableList<Account> accountsData = FXCollections.observableArrayList();

    private StartApp startApp = new StartApp();

    @FXML
    private TableView<Account> accountTable;

    @FXML
    private TableColumn<Account, String> loginColumn, passwordColumn;

    @FXML
    private Button login, exit;

    @FXML
    private TextField loginTxt;

    @FXML
    private Label msgLogin;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private Label msgPassword;

    @FXML
    private void initialize()
    {
        accountTable.setPlaceholder(new Text(startApp.getBundle().getString("table")));
        login.setOnAction((event -> {
            String loginTextTmp = loginTxt.getText();
            String passwordTextTmp = passwordTxt.getText();
            if (isValid(loginTextTmp) && isValid(passwordTextTmp))
            {
                accountsData.add(new Account(loginTextTmp, passwordTextTmp));
                accountTable.setItems(accountsData);

                loginColumn.setCellValueFactory(cellData -> cellData.getValue().getLoginProperty());
                passwordColumn.setCellValueFactory(cellData -> cellData.getValue().getPasswordProperty());

                loginTxt.clear();
                passwordTxt.clear();

                msgLogin.setTextFill(null);
                msgPassword.setTextFill(null);

                startApp.loadOverview(startApp.getBundle(), "fxml\\create.fxml");
            }
            else if(!isValid(loginTextTmp) && isValid(passwordTextTmp))
            {
                msgLogin.setText(startApp.getBundle().getString("msg.login"));
                msgLogin.setTextFill(Color.RED);

                msgPassword.setTextFill(null);
            }
            else if(isValid(loginTextTmp) && !isValid(passwordTextTmp))
            {
                msgPassword.setText(startApp.getBundle().getString("msg.password"));
                msgPassword.setTextFill(Color.RED);

                msgLogin.setTextFill(null);
            }
            else
            {
                msgLogin.setText(startApp.getBundle().getString("msg.login"));
                msgLogin.setTextFill(Color.RED);
                msgPassword.setText(startApp.getBundle().getString("msg.password"));
                msgPassword.setTextFill(Color.RED);
            }
        }));

        exit.setOnAction((event -> System.exit(0)));

    }

    private boolean isValid(String value)
    {
        if (value.isEmpty() || value.length() <= 0 || value == null )
        {
            return false;
        }
        return true;
    }

    public ObservableList<Account> getAccountsData()
    {
        return accountsData;
    }
}
