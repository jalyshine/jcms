package cn.jaly.admin.entity;

/**
 * 安全设置
 */
public class SecuritySetting {

    private Boolean allowOperLogger;  // 启用后台管理操作日志
    private Integer maxLoginTimes;    // 后台最大登陆失败次数
    private String backDomain;        // 后台访问域名
    private ErrorLogSetting errorLogSetting;

    public Boolean getAllowOperLogger() {
        return allowOperLogger;
    }

    public void setAllowOperLogger(Boolean allowOperLogger) {
        this.allowOperLogger = allowOperLogger;
    }

    public Integer getMaxLoginTimes() {
        return maxLoginTimes;
    }

    public void setMaxLoginTimes(Integer maxLoginTimes) {
        this.maxLoginTimes = maxLoginTimes;
    }

    public String getBackDomain() {
        return backDomain;
    }

    public void setBackDomain(String backDomain) {
        this.backDomain = backDomain;
    }

    public ErrorLogSetting getErrorLogSetting() {
        return errorLogSetting;
    }

    public void setErrorLogSetting(ErrorLogSetting errorLogSetting) {
        this.errorLogSetting = errorLogSetting;
    }

}
