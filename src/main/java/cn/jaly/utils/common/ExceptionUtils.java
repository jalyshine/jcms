package cn.jaly.utils.common;

public class ExceptionUtils {

    /**
     * 打印异常信息
     * @param exception
     * @return
     */
    public static String formatExceptionInfo(Exception exception){
        StringBuilder sb = new StringBuilder();
        sb.append(exception.getMessage()).append("\r\n");
        for(StackTraceElement element : exception.getStackTrace()){
            sb.append("\t-> ").append(element).append("\r\n");
        }
        return sb.toString();
    }
}
