import model.ConnectionLog;
import service.FileService;

public class WorkWithFiles {
    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        String fileDir = userDir + separator + "files";
        String fileName = userDir + separator + "files"+ separator+ "test.txt";
        FileService.checkAndCreateFileAndDir(fileDir,fileName);
        ConnectionLog cl1=new ConnectionLog();
        ConnectionLog cl2=new ConnectionLog();
        ConnectionLog cl3=new ConnectionLog();
        ConnectionLog cl4=new ConnectionLog();
        ConnectionLog cl5=new ConnectionLog();
        ConnectionLog cl6=new ConnectionLog();
        ConnectionLog cl7=new ConnectionLog();
        ConnectionLog cl8=new ConnectionLog();
        ConnectionLog cl9=new ConnectionLog();
        ConnectionLog cl10=new ConnectionLog();
        FileService.writeToEndOfFile(fileName, cl1, cl2, cl3, cl4, cl5, cl6, cl7, cl8, cl9, cl10);
    }
}
