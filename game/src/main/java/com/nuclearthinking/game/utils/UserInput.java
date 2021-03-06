package com.nuclearthinking.game.utils;

import com.nuclearthinking.game.actions.Action;
import com.nuclearthinking.game.engines.MessagesReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInput {

    private MessagesReader messages = MessagesReader.getInstance();

    public String getUserInput() {
        String input = null;
        boolean valid = false;
        while (!valid) {
            try {
                BufferedReader is = new BufferedReader(new InputStreamReader(System.in, Charset.forName("cp866")));
                input = is.readLine();
                valid = checkInput(input);
                if (!valid) {
                    //Пустое имя
                    if (input.trim().length() <= 0) {
                        System.out.println(messages.getMessage("inputText"));
                    }
                    //Слишком длинное имя
                    if (input.length() > 15) {
                        System.out.println(messages.getMessage("unacceptableInputTooLong"));
                    }
                    //Имя содержит пробелы
                    if (input.trim().length() < input.length() || input.contains(" ")) {
                        System.out.println(messages.getMessage("unacceptableInputTooManyWhiteSpaces"));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return input;
    }

    public int getUserInputInt() {
        String input = null;

        do {
            try {
                BufferedReader is = new BufferedReader(new InputStreamReader(System.in, Charset.forName("cp866")));
                input = is.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (input.equals("")) {
                System.out.println(messages.getMessage("chooseOneVariant"));
            }
        } while (input.equals(""));
        return Integer.parseInt(input);
    }

    private boolean checkInputInt(String input) {
        Pattern p = Pattern.compile("^[0-9]{1,2}$");
        Matcher m = p.matcher(input);
        return m.matches();
    }

    private boolean checkInput(String input) {
        Pattern p = Pattern.compile("^[ёA-ZА-Яа-яa-z0-9_-]{1,15}$");
        Matcher m = p.matcher(input);
        return m.matches();
    }


    public int chouseOne(List<String> stringList) {
        System.out.println(messages.getMessage("chooseOneInVariants"));
        int iter = 1;
        int input = 0;
        for (String str : stringList) {
            System.out.println(iter + ". " + str);
            iter++;
        }

        boolean validInput = false;
        while (!validInput) {
            input = getUserInputInt();
            if (input <= stringList.size() & checkInputInt(String.valueOf(input))) {
                if (input == 0) {
                    System.out.println(messages.getMessage("numberIsIncorrect"));
                } else {
                    validInput = true;
                }
            } else {
                System.out.println(messages.getMessage("numberIsIncorrect"));
            }

        }
        return input;
    }

    public Action selectActionFromMap(Map<String, Action> variants) {
        System.out.println(messages.getMessage("chooseOneInVariants"));
        int iter = 1;
        int input = 0;

        List<String> printVariants = new ArrayList<>();
        for (Map.Entry<String, Action> entry : variants.entrySet()) {
            printVariants.add(entry.getKey());
        }
        for (String str : printVariants) {
            System.out.println(iter + ". " + str);
            iter++;
        }

        boolean validInput = false;
        while (!validInput) {
            input = getUserInputInt();
            if (input <= variants.size() & checkInputInt(String.valueOf(input))) {
                if (input == 0) {
                    System.out.println(messages.getMessage("numberIsIncorrect"));
                } else {
                    validInput = true;
                }
            } else {
                System.out.println(messages.getMessage("numberIsIncorrect"));
            }
        }

        return variants.get(printVariants.get(input - 1));

    }
}
