package service;

import java.util.Random;

public class RandomizerUtill {


    public static int getRandomForIp(){
        return new Random().nextInt(255);
    }


    public static int getRandomForSession(){
        return new Random().nextInt(999999999);
    }
}
