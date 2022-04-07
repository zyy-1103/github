package com.road.utils;

import java.util.Random;

public class RandomUtil {
    private static final Random random = new Random();

    public static int getInt(){
        int i = random.nextInt();
        return Math.abs(i)%600/10*10;
    }
}
