package cn.jaly.utils.explorer;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;

public class HttpUtils {

    /**
     * 页面静态化
     *
     * @param url  需要转换的链接
     * @param path 文件要保存的真实路径
     * @param name 文件要保存的名字
     * @return 返回静态页面的绝对路径
     */
    public static String staticHtml(String url, String path, String name) throws IOException {
        HttpClient client = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        client.executeMethod(getMethod);
        File filePath = new File(path);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        System.out.println("*****");
        if (getMethod.getResponseContentLength() == 0) {
            return null;
        }
        System.out.println("#######");
        // 写入文件
        String htmlFilePath = path + "/" + name + ".html";
        InputStream is = getMethod.getResponseBodyAsStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(htmlFilePath),"UTF-8");
        BufferedWriter writer = new BufferedWriter(osw);
        String temp;
        while((temp = reader.readLine()) != null){
            if(!temp.trim().equals("")){
                writer.write(temp + "\r\n");
            }
        }
        writer.flush();
        writer.close();
        reader.close();
        is.close();
        return htmlFilePath;
    }

    /**
     * 获取客户端IP
     *
     * @param request
     * @return
     */
    public static String getIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (isNotEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Fowarded-For");
        if (isNotEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }

    private static boolean isNotEmpty(String str) {
        return str != null && str.trim() != "";
    }

    /**
     * 向URL发送get请求
     *
     * @param url
     * @return
     */
    public static String httpGet(String url) throws IOException {
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    /**
     * 发送POST请求
     *
     * @param urlStr
     * @param params url参数：name=abc&kwd=efg...
     * @return
     * @throws IOException
     */
    public static String httpPost(String urlStr, String params) throws IOException {
        URL connect;
        connect = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) connect.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("accept", "*/*");
        connection.setRequestProperty("connection", "Keep-Alive");
        connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
        osw.write(params);
        osw.flush();

        int code = connection.getResponseCode();
        InputStream is = null;
        if (code == 200) {
            is = connection.getInputStream();
        } else {
            is = connection.getErrorStream();
        }

        // 读取响应
        int length = connection.getContentLength(); // 获取长度
        if (length != -1) {
            byte[] data = new byte[length];
            byte[] temp = new byte[512];
            int readLen = 0;
            int destPos = 0;
            while ((readLen = is.read(temp)) > 0) {
                System.arraycopy(temp, 0, data, destPos, readLen);
                destPos += readLen;
            }
            String result = new String(data, "UTF-8"); // utf-8编码
            return result;
        }
        // 自定义错误信息
        return "error";
    }

    /**
     * 上传文件
     * @param urlStr
     * @param textMap
     * @param fileMap
     * @param contentType 没有传入文件类型默认采用application/octet-stream，contentType非空采用filename匹配默认的图片类型
     * @return 返回response数据
     */
    public static String formUpload(String urlStr, Map<String, String> textMap,
                                    Map<String, String> fileMap,String contentType) throws IOException{
        String res = "";
        HttpURLConnection conn = null;
        // boundary就是request头和上传文件内容的分隔符
        String BOUNDARY = "---------------------------123821742118716";
        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
            OutputStream out = new DataOutputStream(conn.getOutputStream());
            // text
            if (textMap != null) {
                StringBuffer strBuf = new StringBuffer();
                Iterator iterator = textMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    String inputName = (String) entry.getKey();
                    String inputValue = (String) entry.getValue();
                    if (inputValue == null) {
                        continue;
                    }
                    strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
                    strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"\r\n\r\n");
                    strBuf.append(inputValue);
                }
                out.write(strBuf.toString().getBytes());
            }
            // file
            if (fileMap != null) {
                Iterator iterator = fileMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    String inputName = (String) entry.getKey();
                    String inputValue = (String) entry.getValue();
                    if (inputValue == null) {
                        continue;
                    }
                    File file = new File(inputValue);
                    String filename = file.getName();

                    //没有传入文件类型，同时根据文件获取不到类型，默认采用application/octet-stream
                    contentType = new MimetypesFileTypeMap().getContentType(file);
                    //contentType非空采用filename匹配默认的图片类型
                    if(!"".equals(contentType)){
                        if (filename.endsWith(".png")) {
                            contentType = "image/png";
                        }else if (filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename.endsWith(".jpe")) {
                            contentType = "image/jpeg";
                        }else if (filename.endsWith(".gif")) {
                            contentType = "image/gif";
                        }else if (filename.endsWith(".ico")) {
                            contentType = "image/image/x-icon";
                        }
                    }
                    if (contentType == null || "".equals(contentType)) {
                        contentType = "application/octet-stream";
                    }
                    StringBuffer strBuf = new StringBuffer();
                    strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
                    strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"; filename=\"" + filename + "\"\r\n");
                    strBuf.append("Content-Type:" + contentType + "\r\n\r\n");
                    out.write(strBuf.toString().getBytes());
                    DataInputStream in = new DataInputStream(new FileInputStream(file));
                    int bytes = 0;
                    byte[] bufferOut = new byte[1024];
                    while ((bytes = in.read(bufferOut)) != -1) {
                        out.write(bufferOut, 0, bytes);
                    }
                    in.close();
                }
            }
            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
            out.write(endData);
            out.flush();
            out.close();
            // 读取返回数据
            StringBuffer strBuf = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                strBuf.append(line).append("\n");
            }
            res = strBuf.toString();
            reader.close();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return res;
    }
}
