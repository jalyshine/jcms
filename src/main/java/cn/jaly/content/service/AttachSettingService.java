package cn.jaly.content.service;

import cn.jaly.content.dao.AttachSettingMapper;
import cn.jaly.admin.dao.SiteMapper;
import cn.jaly.content.entity.AttachSetting;
import cn.jaly.admin.entity.Site;
import cn.jaly.utils.explorer.HttpUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
public class AttachSettingService {

    @Autowired
    private AttachSettingMapper attachSettingMapper;

    @Autowired
    private SiteMapper siteMapper;

    @Transactional(readOnly = true)
    public AttachSetting getBySiteId(Integer siteId){
        AttachSetting attachSetting = attachSettingMapper.selectByPrimaryKey(siteId);
        if(attachSetting == null){
            attachSetting = new AttachSetting();
        }
        return attachSetting;
    }

    @Transactional
    public void save(AttachSetting attachSetting){
        Integer siteId = attachSetting.getSiteId();
        AttachSetting setting = attachSettingMapper.selectByPrimaryKey(siteId);
        if(setting == null){
            attachSettingMapper.insert(attachSetting);
        } else {
            attachSettingMapper.updateByPrimaryKeySelective(attachSetting);
        }
    }

    /**
     * 同步附件配置
     *
     * @param attachSetting
     * @param siteId
     */
    @Transactional
    public void synchronizeSetting(AttachSetting attachSetting, Integer siteId) throws IOException {
        Site site = siteMapper.selectByPrimaryKey(siteId);
        StringBuilder sb = new StringBuilder();
        sb.append("account=").append(site.getUserName())
                .append("&password=").append(site.getPassword())
                .append("&data=").append(new Gson().toJson(attachSetting));
        HttpUtils.httpPost(site.getDomain() + "/attach", sb.toString());
    }
}
