package cn.jaly.vote.entity;

public class VoteOption { 
	private String text; // 选项文字
	private String icon; // 选项图片
	
	public VoteOption(String text, String icon) { 
		this.text = text;
		this.icon = icon;
	} 

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

}
