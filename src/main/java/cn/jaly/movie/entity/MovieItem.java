package cn.jaly.movie.entity;


/**
 * 影片剧集
 * @author Administrator
 *
 */ 
public class MovieItem {

	private String name;   // 剧集名称
	private String url1;   // 地址1
	private String url2;   // 地址2

	public MovieItem(String name, String url1, String url2) {
		this.name = name;
		this.url1 = url1;
		this.url2 = url2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl1() {
		return url1;
	}

	public void setUrl1(String url1) {
		this.url1 = url1;
	}

	public String getUrl2() {
		return url2;
	}

	public void setUrl2(String url2) {
		this.url2 = url2;
	}
}
