package com.nuclearthinking.game.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created
 * Date: 23.12.2015
 * Time: 14:41
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class    Util {

    public String setFirstCharUpperCase(String string) {
        String firstChar = String.valueOf(string.charAt(0));
        String otherPart = string.substring(1, string.length());
        return firstChar.toUpperCase() + otherPart;
    }

    public boolean isNumericOnly(String string) {
        Pattern p = Pattern.compile("\\p{Digit}++");
        Matcher m = p.matcher(string);
        return m.matches();
    }
}