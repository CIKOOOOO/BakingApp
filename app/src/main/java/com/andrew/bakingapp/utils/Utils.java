package com.andrew.bakingapp.utils;

public class Utils {

    public static String removeZeroFromFloat(float value) {
        return Float.toString(value).replaceAll("\\.?0*$", "");
    }

}
