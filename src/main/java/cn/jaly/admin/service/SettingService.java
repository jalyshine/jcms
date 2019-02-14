package cn.jaly.admin.service;

import cn.jaly.admin.entity.EmailSetting;
import cn.jaly.admin.entity.ErrorLogSetting;
import cn.jaly.admin.entity.SecuritySetting;
import cn.jaly.utils.common.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SettingService {

    private static final String CLASS_PATH = SettingService.class.getResource("/").getPath();
    private static final String SETTING_FILE = "setting.xml";
    private static final String LOG4J2_FILE = "log4j2.xml";

    @Transactional(readOnly = true)
    public ErrorLogSetting queryLogSetting() throws DocumentException {
        ErrorLogSetting setting = new ErrorLogSetting();
        Document document = read(CLASS_PATH + "/" + LOG4J2_FILE);

        Element root = document.getRootElement();
        String status = root.attribute("status").getValue();
        String monitorInterval = root.attribute("monitorInterval").getValue();
        setting.setStatus(status);
        setting.setMonitorInterval(Integer.parseInt(monitorInterval));

        Element rollingFile = root.element("Appenders").element("RollingFile");
        String maxFiles =
                rollingFile.element("DefaultRolloverStrategy").attribute("max").getValue();
        setting.setMaxFiles(Integer.parseInt(maxFiles));

        Element policies = rollingFile.element("Policies");
        String rollingInterval =
                policies.element("TimeBasedTriggeringPolicy").attribute("interval").getValue();
        setting.setRollingInterval(Integer.parseInt(rollingInterval));

        String maxSize =
                policies.element("SizeBasedTriggeringPolicy").attribute("size").getValue();
        Matcher m = Pattern.compile("[^0-9]").matcher(maxSize);
        setting.setMaxSize(Integer.parseInt(m.replaceAll("").trim()));

        return setting;
    }

    @Transactional
    public void updateLogSetting(ErrorLogSetting setting) throws DocumentException, IOException {
        String filePath = CLASS_PATH + "/" + LOG4J2_FILE;
        Document document = read(filePath);

        Element root = document.getRootElement();
        root.attribute("status").setValue(setting.getStatus());
        root.attribute("monitorInterval").setValue(setting.getMonitorInterval().toString());

        Element rollingFile = root.element("Appenders").element("RollingFile");
        rollingFile.element("DefaultRolloverStrategy")
                .attribute("max").setValue(setting.getMaxFiles().toString());

        Element policies = rollingFile.element("Policies");
        policies.element("TimeBasedTriggeringPolicy")
                .attribute("interval").setValue(setting.getRollingInterval().toString());
        policies.element("SizeBasedTriggeringPolicy")
                .attribute("size").setValue(setting.getMaxSize() + " MB");

        write(document, filePath);
    }

    @Transactional(readOnly = true)
    public SecuritySetting querySecuritySetting() throws DocumentException {
        SecuritySetting setting = new SecuritySetting();
        Document document = read(CLASS_PATH + "/" + SETTING_FILE);
        List<Element> elements = document.getRootElement().element("security").elements("property");
        for(Element e : elements){
            String property = e.attribute("name").getValue();
            String value = e.getText();
            switch (property){
                case "allowOperLogger":
                    setting.setAllowOperLogger(Boolean.parseBoolean(value));
                    break;
                case "maxLoginTimes":
                    setting.setMaxLoginTimes(Integer.parseInt(value));
                    break;
                case "backDomain":
                    setting.setBackDomain(value);
                    break;
            }
        }
        return setting;
    }

    @Transactional
    public void updateSecuritySetting(SecuritySetting setting) throws DocumentException, IOException {
        String filePath = CLASS_PATH + "/" + SETTING_FILE;
        Document document = read(filePath);
        List<Element> elements = document.getRootElement().element("security").elements("property");
        for(Element e : elements){
            String property = e.attribute("name").getValue();
            String value = "";
            switch (property){
                case "allowOperLogger":
                    value = setting.getAllowOperLogger().toString();
                    break;
                case "maxLoginTimes":
                    value = setting.getMaxLoginTimes().toString();
                    break;
                case "backDomain":
                    value = setting.getBackDomain();
                    break;
            }
            e.setText(value);
        }
        write(document, filePath);
    }

    @Transactional(readOnly = true)
    public EmailSetting queryEmailSetting() throws DocumentException {
        EmailSetting setting = new EmailSetting();
        Document document = read(CLASS_PATH + "/" + SETTING_FILE);
        List<Element> elements = document.getRootElement().element("email").elements("property");
        for(Element e : elements){
            String property = e.attribute("name").getValue();
            String value = e.getText();
            switch (property){
                case "server":
                    setting.setServer(value);
                    break;
                case "sender":
                    setting.setSender(value);
                    break;
                case "needAuth":
                    setting.setNeedAuth(Boolean.parseBoolean(value));
                    break;
                case "userName":
                    setting.setUserName(value);
                    break;
                case "password":
                    setting.setPassword(value);
                    break;
            }
        }
        return setting;
    }

    @Transactional
    public void updateEmailSetting(EmailSetting setting) throws DocumentException, IOException {
        String filePath = CLASS_PATH + "/" + SETTING_FILE;
        Document document = read(filePath);
        List<Element> elements = document.getRootElement().element("email").elements("property");
        for(Element e : elements){
            String property = e.attribute("name").getValue();
            String value = "";
            switch (property){
                case "server":
                    value = setting.getServer();
                    break;
                case "sender":
                    value = setting.getSender();
                    break;
                case "needAuth":
                    value = setting.getNeedAuth().toString();
                    break;
                case "userName":
                    value = setting.getUserName();
                    break;
                case "password":
                    value = setting.getPassword();
                    break;
            }
            e.setText(value);
        }
        write(document, filePath);
    }

    private Document read(String filePath) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(filePath));
        return document;
    }

    private void write(Document document, String filePath) throws IOException {
        XMLWriter writer = new XMLWriter(new FileWriter(new File(filePath)));
        writer.write(document);
        writer.close();
    }

}
