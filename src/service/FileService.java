package service;

import model.ConnectionLog;

import java.io.*;

public class FileService {

    public static String whatWriteToFile(String fileName, ConnectionLog... connectionLogs) {
        StringBuilder text = new StringBuilder();
        for (ConnectionLog connectionLog : connectionLogs) {
            text.append(connectionLog.toString());
            text.append("\n");
        }
        return text.toString();

    }

    public static void writeTextToFile(String fileName, String text) {

        try (FileWriter writer = new FileWriter(fileName);
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

    public static void writeToEndOfFile(String fileName, ConnectionLog... connectionLogs) {
        String alreadyExsisted = readFromFile(fileName);
        String whatToAppend = whatWriteToFile(fileName, connectionLogs);
        StringBuilder result = new StringBuilder();
        result.append(alreadyExsisted);
        result.append(whatToAppend);
        writeTextToFile(fileName, result.toString());
    }

    public static void checkAndCreateFileAndDir(String fileDir, String fileName)  {
        File file = new File(fileDir);
        if (!file.exists()) {
            file.mkdir();
        }
        file = new File(fileName);
        if (!file.exists()) {
            try{file.createNewFile();}
            catch (IOException e){e.printStackTrace();}
        }


    }
}
