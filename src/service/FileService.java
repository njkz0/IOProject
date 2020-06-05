package service;

import model.ConnectionLog;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class FileService {


    public static void writeTextToFile(String fileName, ConnectionLog connectionLog, boolean append) {
        StringBuilder text = new StringBuilder();
        text.append(connectionLog.toString());


        try (FileWriter writer = new FileWriter(fileName, append);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            bufferedWriter.write(text + "\n");
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFromFile(String fileName) {
        String result = "";
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String tempText = "";
            while ((tempText = bufferedReader.readLine()) != null) {
                result += tempText + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static void checkAndCreateFileAndDir(String fileDir, String fileName) {
        File file = new File(fileDir);
        if (!file.exists()) {
            file.mkdir();
        }
        file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public static List<ConnectionLog> getConnectionLogFromFile(String fileName) {

        String text = readFromFile(fileName);
        List<ConnectionLog> connectionLogs = new ArrayList<>();
        try (StringReader stringReader = new StringReader(text);
             BufferedReader bufferedReader = new BufferedReader(stringReader);) {
            String tempText = "";
            while ((tempText = bufferedReader.readLine()) != null) {
                String[] connectionLogFields = tempText.split(" ");
                long time = Long.valueOf(connectionLogFields[0]);
                int session = Integer.valueOf(connectionLogFields[1]);
                String ip = connectionLogFields[2];
                ConnectionLog connectionLog = new ConnectionLog(time, session, ip);
                connectionLogs.add(connectionLog);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return connectionLogs;
    }

    public static List<ConnectionLog> sortLogsByMillies(String fileName, long milliesFrom, long milliesTo) {
        List<ConnectionLog> list = getConnectionLogFromFile(fileName);
        List<ConnectionLog>listResult=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).getTime()>=milliesFrom&&list.get(i).getTime()<=milliesTo ) {
                listResult.add(list.get(i));
            }
        }

        return listResult;
    }

  public static void removeOldLogs(String fileName,long timeHowOld){
      Date date = new Date();
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      long timeNow = calendar.getTimeInMillis();
      List<ConnectionLog>logs=sortLogsByMillies(fileName, timeNow-timeHowOld, timeNow);
      boolean append=false;
      for(int i=0; i<logs.size(); i++){
          writeTextToFile(fileName, logs.get(i),append);
          append=true;
      }

  }
}
