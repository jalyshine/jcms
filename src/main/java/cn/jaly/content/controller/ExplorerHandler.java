package cn.jaly.content.controller;

import cn.jaly.admin.entity.Site;
import cn.jaly.admin.service.SiteService;
import cn.jaly.utils.common.Constant;
import cn.jaly.utils.common.ExceptionUtils;
import cn.jaly.utils.explorer.FileInfo;
import cn.jaly.utils.explorer.FileSystemUtils;
import cn.jaly.utils.common.ResultBean;
import cn.jaly.utils.explorer.ZipUtils;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 资源管理控制器
 * 功能：包括查看和清空目录，删除、保存、查看、上传和下载文件等操作。
 * 请求方式：AJAX/POST
 * URL携带如下参数
 * 操作[method]，取值分别为：
 * make      创建目录          参数filePath
 * list      查看目录内容      参数filePath
 * delete    删除文件或目录    参数filePath
 * copy      复制文件或目录    参数filePath、destDirectory
 * move      移动文件或目录    参数filePath、destDirectory
 * rename    重命名文件或目录  参数filePath、newName
 * zip       压缩文件          参数filePath、destDirectory
 * unZip     解压文件          参数filePath、destDirectory
 * download  下载文件          参数filePath、response
 * upload    上传文件          参数filePath、MultipartFile
 * read      读取文本文件      参数filePath
 * save      保存文本文件      参数filePath、content
 * 文件路径[path]
 * 上传文件[file]，只有method=upload时有效
 * 其他参数[data]，根据method不同，data的意义不同
 */
@Controller
public class ExplorerHandler {

    private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Autowired
    private SiteService siteService;

    @RequestMapping(value = "/disk", method = RequestMethod.GET)
    public String disk(Map<String, Object> request, HttpSession session) {
        Integer siteId = (Integer) session.getAttribute(Constant.CURRENT_SITE);
        Site site = siteService.getById(siteId);
        request.put("site", site);
        return "content/file_explorer";
    }

    @ResponseBody
    @RequestMapping(value = "/disk", method = RequestMethod.POST)
    public String disk(HttpServletRequest request, HttpServletResponse response,
                       @RequestParam("method") String method,
                       @RequestParam("path") String path,
                       @RequestParam(value = "data", required = false) String data,
                       @RequestParam(value = "file", required = false) MultipartFile[] files) {
        ResultBean result = new ResultBean();
        result.setCode(0);
        String contextPath = request.getServletContext().getRealPath("/");
        String filePath = contextPath + path;
        switch (method) {
            case "make":
                FileSystemUtils.make(filePath);
                break;
            case "list":
                List<FileInfo> list = FileSystemUtils.list(filePath);
                result.setData(new Gson().toJson(list));
                break;
            case "delete":
                FileSystemUtils.delete(filePath);
                break;
            case "copy":
                try {
                    FileSystemUtils.copy(filePath, contextPath + data);
                } catch (IOException e) {
                    result.setCode(1);
                    result.setMsg(e.getMessage());
                    logger.error(ExceptionUtils.formatExceptionInfo(e));
                }
                break;
            case "move":
                try {
                    FileSystemUtils.move(filePath, contextPath + data);
                } catch (IOException e) {
                    result.setCode(1);
                    result.setMsg(e.getMessage());
                    logger.error(ExceptionUtils.formatExceptionInfo(e));
                }
                break;
            case "rename":
                FileSystemUtils.rename(filePath, data);
                break;
            case "zip":
                ZipUtils.zip(filePath);
                break;
            case "unzip":
                try {
                    ZipUtils.unZip(filePath, contextPath + data);
                } catch (IOException e) {
                    result.setCode(1);
                    result.setMsg(e.getMessage());
                    logger.error(ExceptionUtils.formatExceptionInfo(e));
                }
                break;
            case "upload":
                try {
                    FileSystemUtils.upload(files, filePath);
                } catch (IOException e) {
                    result.setCode(1);
                    result.setMsg(e.getMessage());
                    logger.error(ExceptionUtils.formatExceptionInfo(e));
                }
                break;
            case "download":
                try {
                    FileSystemUtils.download(response, filePath);
                } catch (IOException e) {
                    result.setCode(1);
                    result.setMsg(e.getMessage());
                    logger.error(ExceptionUtils.formatExceptionInfo(e));
                }
                break;
            case "read":
                try {
                    String content = FileSystemUtils.read(filePath);
                    result.setData(content);
                } catch (IOException e) {
                    result.setCode(1);
                    result.setMsg(e.getMessage());
                    logger.error(ExceptionUtils.formatExceptionInfo(e));
                }
                break;
            case "save":
                try {
                    FileSystemUtils.save(filePath, data);
                } catch (IOException e) {
                    result.setCode(1);
                    result.setMsg(e.getMessage());
                    logger.error(ExceptionUtils.formatExceptionInfo(e));
                }
                break;
        }
        return result.toJson();
    }
}
