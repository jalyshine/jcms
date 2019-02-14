package cn.jaly.admin.entity;

public class ErrorLogSetting {

    private String status;
    private Integer monitorInterval;
    private Integer maxFiles;
    private Integer maxSize;
    private Integer rollingInterval;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getMonitorInterval() {
        return monitorInterval;
    }

    public void setMonitorInterval(Integer monitorInterval) {
        this.monitorInterval = monitorInterval;
    }

    public Integer getMaxFiles() {
        return maxFiles;
    }

    public void setMaxFiles(Integer maxFiles) {
        this.maxFiles = maxFiles;
    }

    public Integer getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }

    public Integer getRollingInterval() {
        return rollingInterval;
    }

    public void setRollingInterval(Integer rollingInterval) {
        this.rollingInterval = rollingInterval;
    }

    @Override
    public String toString() {
        return "ErrorLogSetting{" +
                "status='" + status + '\'' +
                ", monitorInterval=" + monitorInterval +
                ", maxFiles=" + maxFiles +
                ", maxSize=" + maxSize +
                ", rollingInterval=" + rollingInterval +
                '}';
    }
}
