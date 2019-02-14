package cn.jaly.utils.common;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapUtils {

    /**
     * properties转map
     * @param param
     * @return
     */
    public static Map<String, String> fromProperties(String param) {
        Map<String, String> map = new LinkedHashMap<>();
        String[] tokens = param.split("\n");
        for(String token : tokens){
            if("".equals(token) || !token.contains("=")){
                continue;
            }
            String[] entry = token.split("=");
            map.put(entry[0].trim(), entry[1].trim());
        }
        return map;
    }

    /**
     * map转properties
     * @param map
     * @return
     */
    public static String toProperties(Map<String, String> map){
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, String> entry : map.entrySet()){
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }

    /**
     * 字符串转map
     *
     * @param param 格式{key=value, key=value...}
     * @return
     */
    public static Map<String, Object> parse(String param) {
        Map<String, Object> map = new HashMap();
        String str = "";
        String key = "";
        Object value = "";
        char[] charList = param.toCharArray();
        boolean valueBegin = false;
        for (int i = 0; i < charList.length; i++) {
            char c = charList[i];
            if (c == '{') {
                if (valueBegin == true) {
                    value = parse(param.substring(i, param.length()));
                    i = param.indexOf('}', i) + 1;
                    map.put(key, value);
                }
            } else if (c == '=') {
                valueBegin = true;
                key = str;
                str = "";
            } else if (c == ',') {
                valueBegin = false;
                value = str;
                str = "";
                map.put(key, value);
            } else if (c == '}') {
                if (str != "") {
                    value = str;
                }
                map.put(key, value);
                return map;
            } else if (c != ' ') {
                str += c;
            }
        }
        return map;
    }

    /**
     * 分解URI为 module/entity/action 3部分
     *
     * @param requestURI ?/admin/Admin/add
     * @return
     */
    public static Map<String, String> fromRequestURI(String requestURI) {
        String[] tokens = requestURI.split("\\/");
        int length = tokens.length;
        if (length < 4) {
            return null;
        }
        Map<String, String> result = new HashMap<>();
        result.put("module", tokens[length - 3]);
        result.put("entity", tokens[length - 2]);
        result.put("action", tokens[length - 1]);
        return result;
    }
}
