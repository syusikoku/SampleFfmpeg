package com.kasaax.commons.utils;

public class MathUtils {
    public static int constrain(int amount, int _low, int _height) {
        return amount < _low ? _low : (amount > _height ? _height : amount);
    }

    public static float constrain(float amount, float _low, float _height) {
        return amount < _low ? _low : (amount > _height ? _height : amount);
    }
}
