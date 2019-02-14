package cn.jaly.utils.explorer;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;

public class FtpUtils {

    private FTPClient ftpClient = null;

    /**
     * 建立连接
     *
     * @param hostName  服务器地址
     * @param port      连接端口
     * @param userName  用户名
     * @param password  密码
     * @param isPassive 是否开启被动模式
     * @return
     */
    public boolean open(String hostName, int port, String userName,
                        String password, boolean isPassive) throws IOException {
        if (ftpClient != null) {  // 避免重复连接
            return true;
        }
        ftpClient = new FTPClient();
        ftpClient.setControlEncoding("utf-8");
        ftpClient.connect(hostName, port);        //连接ftp服务器
        ftpClient.login(userName, password);      //登录ftp服务器
        if (isPassive) {
            ftpClient.enterLocalPassiveMode();
        }
        int replyCode = ftpClient.getReplyCode(); //是否成功登录服务器
        return FTPReply.isPositiveCompletion(replyCode);
    }

    /**
     * 关闭ftp连接
     */
    public void finish() throws IOException {
        if (ftpClient != null && ftpClient.isConnected()) {
            ftpClient.logout();
            ftpClient.disconnect();
        }
    }

    /**
     * 上传文件
     *
     * @param pathName    ftp服务保存地址
     * @param fileName    上传到ftp的文件名
     * @param inputStream 输入文件流
     * @return
     */
    public void uploadFile(String pathName, String fileName, InputStream inputStream) throws IOException {
        try {
            ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
            createDirecroty(pathName);
            ftpClient.makeDirectory(pathName);
            ftpClient.changeWorkingDirectory(pathName);
            ftpClient.storeFile(fileName, inputStream);
        } finally {
            if(null != inputStream){
                inputStream.close();
            }
        }
    }

    /**
     * 上传文件
     *
     * @param pathName    ftp服务保存地址
     * @param fileName    上传到ftp的文件名
     * @param srcFilePath 待上传文件的名称（绝对地址）
     * @return
     */
    public void uploadFile(String pathName, String fileName, String srcFilePath) throws IOException {
        InputStream inputStream = new FileInputStream(new File(srcFilePath));
        uploadFile(pathName, fileName, inputStream);
    }

    /**
     * 创建多层目录文件，如果有ftp服务器已存在该文件，则不创建，如果无，则创建
     *
     * @param remote
     * @return
     * @throws IOException
     */
    public void createDirecroty(String remote) throws IOException {
        String directory = remote + "/";
        // 如果远程目录不存在，则递归创建远程服务器目录
        if (!directory.equalsIgnoreCase("/") &&
                !ftpClient.changeWorkingDirectory(directory)) {
            int start = directory.startsWith("/") ? 1 : 0;
            int end = directory.indexOf("/", start);
            String path = "";
            while (end > start) {
                String subDirectory =
                        new String(remote.substring(start, end).getBytes("GBK"), "iso-8859-1");
                path += "/" + subDirectory;
                if (!existFile(path)) {
                    ftpClient.makeDirectory(subDirectory);
                }
                ftpClient.changeWorkingDirectory(subDirectory);
                start = end + 1;
                end = directory.indexOf("/", start);
            }
        }
    }

    /**
     * 判断ftp服务器文件是否存在
     *
     * @param path
     * @return
     * @throws IOException
     */
    public boolean existFile(String path) throws IOException {
        if (ftpClient == null) {
            return false;
        }
        boolean flag = false;
        FTPFile[] ftpFileArr = ftpClient.listFiles(path);
        if (ftpFileArr.length > 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * 下载文件 *
     *
     * @param pathName  FTP服务器文件目录
     * @param fileName  文件名称
     * @param localPath 下载后的文件路径
     * @return
     */
    public void downloadFile(String pathName, String fileName, String localPath) throws IOException {
        OutputStream os = null;
        try {
            //切换FTP目录
            ftpClient.changeWorkingDirectory(pathName);
            FTPFile[] ftpFiles = ftpClient.listFiles();
            for (FTPFile file : ftpFiles) {
                if (fileName.equalsIgnoreCase(file.getName())) {
                    File localFile = new File(localPath + "/" + file.getName());
                    os = new FileOutputStream(localFile);
                    ftpClient.retrieveFile(file.getName(), os);
                    os.close();
                }
            }
        } finally {
            if (null != os) {
                os.close();
            }
        }
    }

    /**
     * 删除文件
     *
     * @param pathName FTP服务器保存目录
     * @param filename 要删除的文件名称
     * @return
     */
    public void deleteFile(String pathName, String filename) throws IOException {
        //切换FTP目录
        ftpClient.changeWorkingDirectory(pathName);
        ftpClient.dele(filename);
        ftpClient.logout();
    }

//    public static void main(String[] args) throws IOException {
//        FtpUtils ftp = new FtpUtils();
//        if(ftp.open("192.168.217.128", 21, "kaka", "jalyshine", false)){
//            ftp.uploadFile("test/",
//                    "data.txt", "C:\\Users\\jaly\\Desktop\\desktop\\data.txt");
//        }
//        ftp.finish();
//    }
}
