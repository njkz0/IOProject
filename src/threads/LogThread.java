package threads;

import model.ConnectionLog;
import service.FileService;
import service.RandomizerUtill;

import java.util.Calendar;
import java.util.Date;

public class LogThread extends Thread {
    String userDir = System.getProperty("user.dir");
    String separator = System.getProperty("file.separator");

    String fileName = userDir + separator + "files" + separator + "test.txt";

    public LogThread() {
        start();
    }


    @Override
    public void run() {
        for (int n = 0; n < 3; n++) {
            for (int i = 0; i < 10; i++) {
                Date date = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                long time = calendar.getTimeInMillis();

                int session = RandomizerUtill.getRandomInt(0, 999999999);

                StringBuilder ipSB = new StringBuilder();
                ipSB.append(RandomizerUtill.getRandomInt(1, 255));
                for (int l = 0; l < 3; l++) {
                    ipSB.append(".");
                    ipSB.append(RandomizerUtill.getRandomInt(1, 255));
                }
                String ip = ipSB.toString();
                ConnectionLog cl = new ConnectionLog(time, session, ip);
                FileService.writeTextToFile(fileName, cl, true);
            }
            try {

                sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}