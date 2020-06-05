package service;

import java.util.Random;

public class RandomizerUtill {

    public static int getRandomInt(int min, int max) {
        return min + new Random().nextInt(max - min);
    }
}
