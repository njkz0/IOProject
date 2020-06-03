import model.ConnectionLog;
import service.FileService;
import service.RandomizerUtill;

import java.util.Calendar;
import java.util.Date;

public class WorkWithFiles {
    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        String fileDir = userDir + separator + "files";
        String fileName = userDir + separator + "files" + separator + "test.txt";

        FileService.checkAndCreateFileAndDir(fileDir, fileName);
        for (int i = 0; i < 10; i++) {
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            long time = calendar.getTimeInMillis();

            int session = RandomizerUtill.getRandomForSession();

            StringBuilder ipSB = new StringBuilder();
            ipSB.append(RandomizerUtill.getRandomForIp());
            for (int n = 0; n < 3; n++) {
                ipSB.append(".");
                ipSB.append(RandomizerUtill.getRandomForIp());
            }
            String ip = ipSB.toString();
            ConnectionLog cl = new ConnectionLog(time, session, ip);
            FileService.writeTextToFile(fileName, cl, true);
        }


        FileService.getConnectionLogFromFile(fileName);
        System.out.println(FileService.sortLogsByMillies(fileName, 1591214482520l, 1591214482520l).size());
    }
}
