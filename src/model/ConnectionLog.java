package model;

import service.RandomizerUtill;

import java.util.Calendar;
import java.util.Date;

public class ConnectionLog {
    private long time;
    private int session;
    private String ip;

    public ConnectionLog(long time, int session, String ip) {
        this.time = time;
        this.session = session;
        this.ip = ip;
    }

    public String toString(){
        return time + " " + session + " " + ip + " ";
    }


    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getSession() {
        return session;
    }

    public void setSession(int session) {
        this.session = session;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
