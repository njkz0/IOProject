import model.ConnectionLog;
import service.FileService;
import service.RandomizerUtill;
import threads.LogThread;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WorkWithFiles {
    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        String fileDir = userDir + separator + "files";
        String fileName = userDir + separator + "files" + separator + "test.txt";

        FileService.checkAndCreateFileAndDir(fileDir, fileName);
        //    for (int i = 0; i < 10; i++) {
        //        Date date = new Date();
        //        Calendar calendar = Calendar.getInstance();
        //        calendar.setTime(date);
        //        long time = calendar.getTimeInMillis();

        //        int session = RandomizerUtill.getRandomInt(0, 999999999);

        //        StringBuilder ipSB = new StringBuilder();
        //        ipSB.append(RandomizerUtill.getRandomInt(1, 255));
        //        for (int n = 0; n < 3; n++) {
        //            ipSB.append(".");
        //            ipSB.append(RandomizerUtill.getRandomInt(1, 255));
        //        }
        //        String ip = ipSB.toString();
        //        ConnectionLog cl = new ConnectionLog(time, session, ip);
        //        FileService.writeTextToFile(fileName, cl, true);
        //    }


        List<ConnectionLog> ll = FileService.sortLogsByMillies(fileName, 1591370219021l, 1591370219021l);

        FileService.removeOldLogs(fileName, 1591370219021l);
        LogThread lg1 = new LogThread();
        LogThread lg2 = new LogThread();
        LogThread lg3 = new LogThread();
        lg1.start();
        lg2.start();
        lg3.start();
    }
}
