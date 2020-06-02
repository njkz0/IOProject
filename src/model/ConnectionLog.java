package model;

import service.RandomizerUtill;

import java.util.Calendar;
import java.util.Date;

public class ConnectionLog {
    private long time;
    private int session;
    private String ip;

    public ConnectionLog() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        time = calendar.getTimeInMillis();

        session = RandomizerUtill.getRandomForSession();

        StringBuilder ipSB = new StringBuilder();
        ipSB.append(RandomizerUtill.getRandomForIp());
        for (int i = 0; i < 3; i++) {
            ipSB.append(".");
            ipSB.append(RandomizerUtill.getRandomForIp());
        }
        ip = ipSB.toString();
    }

    public String toString(){
        return time + " " + session + " " + ip;
    }












}
