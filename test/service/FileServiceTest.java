package service;

import model.ConnectionLog;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

class FileServiceTest {

   final static String User_Dir = System.getProperty("user.dir");
  final static   String Separator = System.getProperty("file.separator");
   final static String File_Dir = User_Dir + Separator + "files";
    final static String File_Name = User_Dir + Separator + "files" + Separator + "testFile.txt";

    @Test
    void writeAndGetCL() {
        ConnectionLog cl = new ConnectionLog(1591357385773l, 170981229, "247.156.204.3");

        FileService.checkAndCreateFileAndDir(File_Dir, File_Name);
        FileService.writeTextToFile(File_Name, cl, false);
        List<ConnectionLog> list = FileService.getConnectionLogFromFile(File_Name);
        assertEquals(list.size(), 1);
    }




 @Test
 void readFromFile(){
     ConnectionLog cl = new ConnectionLog(1591357385773l, 170981229, "247.156.204.3");
        FileService.writeTextToFile(File_Name, cl, false);
        String result= FileService.readFromFile(File_Name);
        assertEquals("1591357385773 170981229 247.156.204.3 \n", result);
 }




    @Test
    void sortAndRemoveOldLogs() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        long timeNow = calendar.getTimeInMillis();
        ConnectionLog[] connectionLogs=new ConnectionLog[2];
        ConnectionLog cl1 = new ConnectionLog(timeNow, 170981229, "247.156.204.3");
        ConnectionLog cl2 = new ConnectionLog(timeNow-86400001l, 442013545, "154.72.130.82" );
        connectionLogs[0]=cl1;
        connectionLogs[1]=cl2;

        boolean append=false;
        for (int i = 0; i <connectionLogs.length ; i++) {
            FileService.writeTextToFile(File_Name, connectionLogs[i], append);
            append=true;
        }
        FileService.removeOldLogs(File_Name, 86400000);
        List<ConnectionLog>result=FileService.getConnectionLogFromFile(File_Name);
        assertEquals(1, result.size());

    }
}