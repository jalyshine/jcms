package cn.jaly.content.entity;

public class PictureItem {

	private String name;     // 图片名称
	private Long size;       // 图片大小
	private String url;      // 图片地址
	private String desc;     // 图片简介

	public PictureItem(String name, Long size, String url, String desc) {
		this.name = name;
		this.size = size;
		this.url = url;
		this.desc = desc;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "PictureItem{" +
				"name='" + name + '\'' +
				", size=" + size +
				", url='" + url + '\'' +
				", desc='" + desc + '\'' +
				'}';
	}
}
