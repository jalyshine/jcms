package cn.jaly.utils.taglib;

import com.google.gson.Gson;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 根据访问源，生成不同的链接地址
 * Jsp                                                                      html
 * [root]/template/default/content/home_news.jsp?id=[id]           [domain]/html/content/home_news_[id].html
 * [root]/template/default/content/list_news.jsp?id=[id]&pn=[pn]   [domain]/html/content/list_news_[id]_[pn].html
 * [root]/template/default/content/show_news.jsp?id=[id]           [domain]/html/content/show_news_[id].html
 *
 * [root]为默认站点的域名
 * [domain]为节点站的域名
 *
 * home:
 * <jcp:url jsp="[root]/template/default/content/home_news.jsp?id={1}"
 * 	 html="[domain]/html/content/home_news_{1}.html"
 * 	 auto="${ param.auto != null }"
 * 	 data="{1}"
 * />
 *
 * list:
 * <jcp:url jsp="[root]/template/default/content/list_news.jsp?id={1}&pn={2}"
 * 	 html="[domain]/html/content/list_news_{1}_{2}.html"
 * 	 auto="${ param.auto != null }"
 * 	 data="{1,2}"
 * />
 *
 * show:
 * <jcp:url jsp="[root]/template/default/content/show_news.jsp?id={1}"
 * 	 html="[domain]/html/content/home_news_{1}.html"
 * 	 auto="${ param.auto != null }"
 * 	 data="{1}"
 * />
 */
public class ContentUrl extends SimpleTagSupport {

	private String jsp;
	private String html;
	private Boolean isAuto;
	private String data;

    public void setJsp(String jsp) {
        this.jsp = jsp;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public void setIsAuto(Boolean isAuto) {
        this.isAuto = isAuto;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
	public void doTag() throws IOException {
        String url = isAuto?html : jsp;
        if(data != null){
            int index1 = data.indexOf("{");
            int index2 = data.indexOf("}");
            if(index1 != -1 && index2 != -1 && index1 < index2){
                String[] tokens = data.substring(index1 + 1, index2).split("\\,");
                for(int i=0; i<tokens.length; i++){
                    url = url.replace("{" + (i+1) + "}", tokens[i]);
                }
            }
        }
        getJspContext().getOut().write(url);
	}
}
