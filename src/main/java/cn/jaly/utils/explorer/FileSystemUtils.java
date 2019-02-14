package cn.jaly.utils.explorer;

import org.apache.commons.fileupload.FileItem;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 文件系统操作工具类
 * 文件路径分隔符统一使用"/"
 */
public class FileSystemUtils {

    /**
     * 创建文件夹
     *
     * @param filePath
     */
    public static void make(String filePath) {
        new File(filePath).mkdirs();
    }

    /**
     * 查看目录内容
     *
     * @param filePath
     * @return
     */
    public static List<FileInfo> list(String filePath) {
        List<FileInfo> fileInfos = null;
        File dir = new File(filePath);
        if (dir.exists()) {
            fileInfos = new ArrayList<>();
            File[] files = dir.listFiles();
            for (File file : files) {
                FileInfo info = new FileInfo();
                info.setName(file.getName());
                info.setSize(file.length());
                info.setFolder(file.isDirectory());
                fileInfos.add(info);
            }
        }
        return fileInfos;
    }

    /**
     * 删除文件或文件夹中的所有文件
     *
     * @param filePath
     */
    public static void delete(String filePath) {
        File file = new File(filePath);
        if (file.isDirectory()) {
            String[] fileList = file.list();
            for (String name : fileList) {
                delete(filePath + File.separator + name);
            }
        }
        file.delete();
    }

    /**
     * 复制文件或文件夹到目标目录下
     *
     * @param srcFolder 源文件
     * @param destPath  目标目录
     */
    public static void copy(String srcFolder, String destPath) throws IOException {
        copy(new File(srcFolder), new File(destPath), true);
    }

    private static void copy(File srcFile, File destFile, boolean flag) throws IOException {
        if (srcFile.isDirectory()) {
            if (flag) {
                destFile = new File(destFile + "/" + srcFile.getName());
                if (!destFile.exists()) {
                    destFile.mkdirs();
                }
            }
            flag = true;
            File[] l = srcFile.listFiles();
            if (null != l) {
                for (File ll : l) {
                    copy(ll, destFile, flag);
                }
            }
        } else {
            FileInputStream fis = new FileInputStream(srcFile);
            FileOutputStream fos = new FileOutputStream(destFile + "/" + srcFile.getName());
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = fis.read(b)) != -1) {
                fos.write(b, 0, len);
            }
            fos.close();
            fis.close();
        }
    }

    /**
     * 移动文件或文件夹到目标目录下
     *
     * @param srcPath  源文件
     * @param destPath 目标目录
     * @throws IOException
     */
    public static void move(String srcPath, String destPath) throws IOException {
        copy(srcPath, destPath);
        delete(srcPath);
    }

    /**
     * 文件重命名
     *
     * @param srcPath 源文件
     * @param newName 新名称
     */
    public static void rename(String srcPath, String newName) {
        File srcFile = new File(srcPath);
        File destFile = new File(srcFile.getParent() + "/" + newName);
        srcFile.renameTo(destFile);
    }

    /**
     * 下载文件
     *
     * @param response
     * @param filePath
     * @throws IOException
     */
    public static void download(HttpServletResponse response, String filePath) throws IOException {
        File file = new File(filePath);
        response.setContentType("application/octet-stream");
        String fileName = new String(file.getName().getBytes("GBK"), "ISO-8859-1");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        OutputStream out = response.getOutputStream();
        InputStream in = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        out.flush();
        out.close();
        in.close();
    }

    /**
     * 上传文件到指定目录
     *
     * @param files
     * @param filePath 目标目录
     * @throws IOException
     */
    public static List<String> upload(MultipartFile[] files, String filePath) throws IOException {
        List<String> filePaths = new ArrayList<>();
        for (MultipartFile file : files) {
            String originName = file.getOriginalFilename();
            File outputFile = new File(filePath + "/" + originName);
            File outputDir = new File(outputFile.getParent());
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }
            InputStream in = file.getInputStream();
            OutputStream os = new FileOutputStream(outputFile);

            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = in.read(buf)) > 0) {
                os.write(buf, 0, len);
            }
            os.close();
            in.close();
            filePaths.add(filePath + "/" + originName);
        }
        return filePaths;
    }

    /**
     * 读取文本文件内容
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static String read(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStream in = new FileInputStream(filePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        String temp;
        while ((temp = br.readLine()) != null) {
            sb.append(temp).append("\r\n");
        }
        br.close();
        return sb.subSequence(0, sb.length() - 2).toString();
    }

    /**
     * 保存文本文件
     *
     * @param filePath
     * @param content
     * @throws IOException
     */
    public static void save(String filePath, String content) throws IOException {
        OutputStream out = new FileOutputStream(filePath);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
        bw.write(content);
        bw.flush();
        bw.close();
    }

//    public static void publish(MultipartFile[] files, String contextPath) throws IOException {
//        if(files == null || files.length == 0){
//            return;
//        }
//        List<String> filePath = upload(files, contextPath);
//        File zipFile = new File(filePath.get(0));
//        ZipUtils.unZip(zipFile, contextPath);
//        zipFile.delete();
//    }
}
