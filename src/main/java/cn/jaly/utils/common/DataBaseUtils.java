package cn.jaly.utils.common;

import java.io.*;

public class DataBaseUtils {

    public static final String MYSQL_BIN_PATH = "";
    /**
     * 数据库备份
     *
     * @param hostIP       MySQL数据库所在服务器地址IP
     * @param userName     进入数据库所需要的用户名
     * @param password     进入数据库所需要的密码
     * @param savePath     数据库导出文件保存路径
     * @param fileName     数据库导出文件文件名
     * @param databaseName 要导出的数据库名
     * @return 返回true表示导出成功，否则返回false。
     */
    public static boolean backup(String hostIP, String userName,
                                 String password, String savePath,
                                 String fileName, String databaseName) throws InterruptedException {
        File saveFile = new File(savePath);
        if (!saveFile.exists()) {
            saveFile.mkdirs();
        }
        if (!savePath.endsWith(File.separator)) {
            savePath = savePath + File.separator;
        }

        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        try {
            printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(savePath + fileName), "utf8"));
            Process process = Runtime.getRuntime().exec(MYSQL_BIN_PATH + "mysqldump -h "
                    + hostIP + " -u" + userName + " -p" + password + " --set-charset=UTF8 " + databaseName);
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(), "utf8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                printWriter.println(line);
            }
            printWriter.flush();
            if (process.waitFor() == 0) {//0 表示线程正常终止。
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 数据库恢复
     * @param hostIP
     * @param userName
     * @param password
     * @param savePath
     * @param fileName
     * @param databaseName
     * @return
     * @throws InterruptedException
     */
    public static boolean restore(String hostIP, String userName,
                               String password, String savePath,
                               String fileName, String databaseName) throws InterruptedException {
        BufferedReader br = null;
        OutputStreamWriter writer = null;
        try {
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec(MYSQL_BIN_PATH + "mysql -h "
                    + hostIP + " -u" + userName + " -p" + password + " --default-character-set=utf8 " + databaseName);
            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(savePath + File.separator + fileName), "utf-8"));
            String str = null;
            StringBuffer sb = new StringBuffer();
            while ((str = br.readLine()) != null) {
                sb.append(str + "\r\n");
            }
            str = sb.toString();
            writer = new OutputStreamWriter(process.getOutputStream(), "utf-8");
            writer.write(str);
            writer.flush();
            if (process.waitFor() == 0) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(br != null){
                    br.close();
                }
                if(writer != null){
                    writer.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void main(String[] args){
        try {
            if (backup("localhost", "root",
                    "jalyshine-2018", "D:/backupDatabase",
                    "jcms.sql", "jcms")) {
                System.out.println("数据库成功备份！！！");
            } else {
                System.out.println("数据库备份失败！！！");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
