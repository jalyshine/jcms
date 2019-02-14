package cn.jaly.utils.explorer;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ZipUtils {

    /**
     * 压缩文件到当前文件夹
     * @param srcPath
     */
    public static String zip(String srcPath){
        File srcFile = new File(srcPath);
        String targetPath = srcFile.getParent() + File.separator + srcFile.getName() + ".zip";
        File targetFile = new File(targetPath);
        zip(srcFile, targetFile);
        return targetPath;
    }

    /**
     * 压缩文件到指定文件夹
     * @param srcPath      原文件和文件夹名
     * @param targetPath   目标文件夹路径
     */
    public static String zip(String srcPath, String targetPath){
        File srcFile = new File(srcPath);
        File targetFile = new File(targetPath + File.separator + srcFile.getName() + ".zip");
        zip(srcFile, targetFile);
        return targetFile.getAbsolutePath();
    }

    /**
     * 压缩文件
     *
     * @param srcFile
     */
    public static void zip(File srcFile, File targetFile) {
        if (targetFile.exists()) {
            targetFile.delete();
        }
        ZipOutputStream out = null;
        try {
            out = new ZipOutputStream(new FileOutputStream(targetFile), Charset.forName("GBK"));
            if (srcFile.isFile()) {
                zipFile(srcFile, out, "");
            } else {
                File[] list = srcFile.listFiles();
                for (int i = 0; i < list.length; i++) {
                    compress(list[i], out, "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 压缩文件夹里的文件
     * 起初不知道是文件还是文件夹 统一调用该方法
     *
     * @param file
     * @param out
     * @param basedir
     */
    private static void compress(File file, ZipOutputStream out, String basedir) {
        /* 判断是目录还是文件 */
        if (file.isDirectory()) {
            zipDirectory(file, out, basedir);
        } else {
            zipFile(file, out, basedir);
        }
    }

    /**
     * 压缩单个文件
     *
     * @param srcFile
     */
    private static void zipFile(File srcFile, ZipOutputStream out, String basedir) {
        if (!srcFile.exists())
            return;

        byte[] buf = new byte[1024];
        FileInputStream in = null;

        try {
            int len;
            in = new FileInputStream(srcFile);
            out.putNextEntry(new ZipEntry(basedir + srcFile.getName()));

            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (out != null)
                    out.closeEntry();
                if (in != null)
                    in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 压缩文件夹
     *
     * @param dir
     * @param out
     * @param basedir
     */
    private static void zipDirectory(File dir, ZipOutputStream out, String basedir) {
        if (!dir.exists())
            return;
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            compress(files[i], out, basedir + dir.getName() + "/");
        }
    }

    /**
     * 解压到指定目录
     * @param zipPath
     * @param targetPath
     */
    public static void unZip(String zipPath, String targetPath) throws IOException {
        unZip(new File(zipPath), targetPath);
    }

    /**
     * 解压文件到指定目录
     * 解压后的文件名，和之前一致
     *
     * @param zipFile
     * @param descDir
     */
    public static void unZip(File zipFile, String descDir) throws IOException {
        //解决中文文件夹乱码
        ZipFile zip = new ZipFile(zipFile, Charset.forName("GBK"));
        File pathFile = new File(descDir);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        for (Enumeration<? extends ZipEntry> entries = zip.entries(); entries.hasMoreElements(); ) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            String zipEntryName = entry.getName();
            InputStream in = zip.getInputStream(entry);
            String outPath = (descDir + File.separator + zipEntryName).replaceAll("\\*", "/");
            // 判断路径是否存在,不存在则创建文件路径
            File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
            if (!file.exists()) {
                file.mkdirs();
            }
            // 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
            if (new File(outPath).isDirectory()) {
                continue;
            }
            FileOutputStream out = new FileOutputStream(outPath);
            byte[] buf1 = new byte[1024];
            int len;
            while ((len = in.read(buf1)) > 0) {
                out.write(buf1, 0, len);
            }
            in.close();
            out.close();
        }
        return;
    }
}