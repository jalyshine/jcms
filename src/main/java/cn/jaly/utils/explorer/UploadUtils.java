package cn.jaly.utils.explorer;

import cn.jaly.content.entity.AttachSetting;
import cn.jaly.utils.common.BasicUtils;
import cn.jaly.utils.image.ImageUtils;
import cn.jaly.utils.image.Position;
import cn.jaly.utils.image.Positions;
import cn.jaly.utils.image.Watermark;
import com.google.gson.Gson;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 上传文件工具类
 * 扩展名均不带“.”
 * Created by Administrator on 2018/2/3.
 */
public class UploadUtils {

    enum ImageSize {
        BIG("big", 1920),
        MID("mid", 1024),
        SML("sml", 512),
        MIN("min", 128),
        PRM("prm", 0);

        private final String name;
        private final Integer size;

        ImageSize(String name, Integer size) {
            this.name = name;
            this.size = size;
        }

        public Integer getSize() {
            return size;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * 检查文件大小和扩展名
     *
     * @param uploadResult
     * @param originName
     * @param available
     * @param setting
     * @return
     * @throws IOException
     */
    private static boolean check(UploadResult uploadResult, String originName, float available,
                                 AttachSetting setting) throws IOException {
        String extension = getExtension(originName).toLowerCase();       // 扩展名
        // 检查扩展名
        List<String> extensionList = setting.getExtensionList();
        if(extensionList != null && extensionList.contains(extension)){
            uploadResult.setCode(1);
            String message = uploadResult.getMsg();
            message += originName + "为不允许上传的文件类型！";
            uploadResult.setMsg(message);
            return false;
        }
        // 检查文件大小
        Integer uploadMaxSize = setting.getLimitSize();
        if (uploadMaxSize > 0 && available > uploadMaxSize) {
            uploadResult.setCode(1);
            String message = uploadResult.getMsg();
            message += originName + "文件大小超过限制！";
            uploadResult.setMsg(message);
            return false;
        }
        return true;
    }

    /**
     * 根据上传类型，生成文件路径 files/[type]/
     * 对于图片集type=pic需要存储以下三种尺寸
     * PRM 原图，位置files/[type]/[yyyyMM]/prm
     * SML 小图，位置files/[type]/[yyyyMM]/
     * MID 中图，位置files/[type]/[yyyyMM]/mid
     *
     * @param type
     * @param sizeName
     * @param extension
     * @return
     */
    private static String generateFileName(String type, String sizeName, String extension) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM", Locale.CHINA);
        Date curTime = new Date();
        StringBuilder sb = new StringBuilder("files/");
        if (type == null) {
            type = "file";
        }
        sb.append(type).append("/")
                .append(format.format(curTime)).append("/");
        if (sizeName != null) {
            sb.append(sizeName).append("/");
        }
        sb.append(curTime.getTime()).append(".").append(extension);
        return sb.toString();
    }

    /**
     * 获取文件扩展名
     *
     * @param fileName
     * @return
     */
    private static String getExtension(String fileName) {
        int idx = fileName.lastIndexOf(".");
        return idx == -1 ? "" : fileName.substring(idx + 1);
    }

    /**
     * 返回Layui框架的响应结果
     *
     * @param result
     * @return
     */
    public static String getLayUIResponse(UploadResult result) {
        StringBuilder sb = new StringBuilder();
        List<UploadFile> uploadFiles = result.getUploadFiles();
        for (UploadFile file : uploadFiles) {
            sb.append(file.getUrl()).append(",");
        }
        if (sb.length() > 1) {
            sb.deleteCharAt(sb.length() - 1);
        }
        Map<String, Object> srcMap = new HashMap<>();
        srcMap.put("src", sb.toString());
        result.setData(srcMap);
        Gson gson = new Gson();
        return gson.toJson(result);
    }

    /**
     * 上传图片
     *
     * @param files
     * @param contextPath
     * @param type
     * @param setting
     * @return
     * @throws IOException
     */
    public static UploadResult uploadImages(MultipartFile[] files, String contextPath, String type,
                                            AttachSetting setting) throws IOException {
        UploadResult uploadResult = new UploadResult();
        List<ImageSize> imageSizes = new ArrayList<>();
        switch (type) {
            case "icon":
                imageSizes.add(ImageSize.MIN);
                break;
            case "thumb":
                imageSizes.add(ImageSize.MID);
                break;
            case "banner":
                imageSizes.add(ImageSize.BIG);
                break;
            case "pic":
                imageSizes.add(ImageSize.PRM);
                imageSizes.add(ImageSize.MID);
                imageSizes.add(ImageSize.SML);
                break;
            case "ill":
                imageSizes.add(ImageSize.MID);
                break;
            case "customer":
                imageSizes.add(ImageSize.PRM);
                break;
        }
        uploadResult.setType(type);
        int length = imageSizes.size();

        for (MultipartFile file : files) {
            String originName = file.getOriginalFilename();                   // 原始文件名
            String extension = getExtension(originName);
            float available = (float) file.getInputStream().available() / 1024; // 文件大小KB

            // 文件大小和扩展名检验
            if (!check(uploadResult, originName, available, setting)) {
                continue;
            }

            // 开始上传图片
            String url = generateFileName(type, null, extension);
            for (ImageSize size : imageSizes) {
                String gUrl = url;
                if (length > 1 && size != ImageSize.SML) {
                    gUrl = generateFileName(type, size.getName(), extension);
                }
                if(size.equals(ImageSize.PRM)){
                    uploadSingleFile(file, contextPath, gUrl);
                } else {
                    uploadSingleImage(file, contextPath, setting, size.getSize(), gUrl);
                }
            }

            // 记录上传结果
            InputStream fis = new FileInputStream(contextPath + File.separator + url);
            available = (float) fis.available() / 1024;
            fis.close();

            UploadFile uploadFile = new UploadFile();
            uploadFile.setName(originName);
            uploadFile.setSize(available);
            uploadFile.setUrl(url);
            uploadFile.setAuthCode(BasicUtils.md5Encrypt(url));
            uploadResult.getUploadFiles().add(uploadFile);
        }
        return uploadResult;
    }

    private static void uploadSingleImage(MultipartFile file, String contextPath,
                                          AttachSetting setting, int size, String url) throws IOException {
        InputStream is = file.getInputStream();
        ImageUtils imageUtils = ImageUtils.fromInputStream(is).quality(1.0f).keepRatio(true);
        if (size != 0) {
            imageUtils = imageUtils.size(size, size);
        }

        boolean flag = true;
        if (size != 0 && size < setting.getMarkWidth()) {
            flag = false;
        }

        // 添加水印
        if (flag && setting.getEnableMark()) {
            File waterFile = new File(contextPath + "/" + setting.getMark());
            if (waterFile.exists()) {
                BufferedImage watermarkImage = ImageIO.read(waterFile);
                float opacity = (float) setting.getMarkOpacity() / 100;
                int waterMarkPos = setting.getMarkPosition();
                if (waterMarkPos == 0) {
                    waterMarkPos = (int) (Math.round(Math.random() * 8) + 1);
                }
                Position position = null;
                // 0 随机位置 1,2,3顶部 4,5,6中部 7,8,9底部
                switch (waterMarkPos) {
                    case 1:
                        position = Positions.TOP_LEFT;
                        break;
                    case 2:
                        position = Positions.TOP_CENTER;
                        break;
                    case 3:
                        position = Positions.TOP_RIGHT;
                        break;
                    case 4:
                        position = Positions.CENTER_LEFT;
                        break;
                    case 5:
                        position = Positions.CENTER;
                        break;
                    case 6:
                        position = Positions.CENTER_RIGHT;
                        break;
                    case 7:
                        position = Positions.BOTTOM_LEFT;
                        break;
                    case 8:
                        position = Positions.BOTTOM_CENTER;
                        break;
                    case 9:
                        position = Positions.BOTTOM_RIGHT;
                        break;
                }
                Watermark watermark = new Watermark(position, watermarkImage, opacity);
                imageUtils = imageUtils.watermark(watermark);
            }
        }
        File imgFile = new File(contextPath + "/" + url);
        File imgParent = new File(imgFile.getParent());
        if (!imgParent.exists()) {
            imgParent.mkdirs();
        }
        imageUtils.toFile(imgFile);
        is.close();
    }

    /**
     * 上传文件
     *
     * @param files
     * @param contextPath
     * @param setting
     * @return
     * @throws IOException
     */
    public static UploadResult uploadFiles(MultipartFile[] files, String contextPath,
                                           AttachSetting setting) throws IOException {
        UploadResult uploadResult = new UploadResult();
        uploadResult.setType("file");
        for (MultipartFile file : files) {
            String originName = file.getOriginalFilename();                     // 原始文件名
            String extension = getExtension(originName);
            float available = (float) file.getInputStream().available() / 1024; // 文件大小KB

            // 文件大小和扩展名检验
            if (!check(uploadResult, originName, available, setting)) {
                continue;
            }

            // 开始上传文件
            String url = generateFileName(null, null, extension);
            uploadSingleFile(file, contextPath, url);

            // 记录上传结果
            InputStream fis = new FileInputStream(contextPath + File.separator + url);
            available = (float) fis.available() / 1024;
            fis.close();

            UploadFile uploadFile = new UploadFile();
            uploadFile.setName(originName);
            uploadFile.setSize(available);
            uploadFile.setUrl(url);
            uploadFile.setAuthCode(BasicUtils.md5Encrypt(url));
            uploadResult.getUploadFiles().add(uploadFile);
        }
        return uploadResult;
    }

    /**
     * 上传单个文件
     * @param file
     * @param contextPath
     * @param url
     * @throws IOException
     */
    private static void uploadSingleFile(MultipartFile file, String contextPath, String url) throws IOException {
        File outputFile = new File(contextPath + "/" + url);
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
    }

    /**
     * 删除附件（包括不同规格的图片文件）
     *
     * @param filePath
     */
    public static void deleteAttachment(String filePath) {
        File file = new File(filePath);
        if(!file.exists()){
            return;
        }
        String fileName = file.getName();
        File[] files = new File(file.getParent()).listFiles();
        for (File dir : files) {
            if (dir.isDirectory()) {
                File temp = new File(dir.getAbsolutePath() + File.separator + fileName);
                if (temp.exists()) {
                    temp.delete();
                }
            }
        }
        file.delete();
    }
}
