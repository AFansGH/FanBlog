package top.afanee.blog.po.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

import top.afanee.blog.utils.StringUtils;

/**
 * 
 * @ClassName ArticleType
 * @Description TODO(在设置提醒消息类型时用到)
 * @author Fan
 * @Date 2019年4月2日 下午7:03:02
 * @version 1.0.0
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ArticleType {
	SHUOSHUO("S","说说"), TOPIC("T","论坛"), BLOG("B", "博客"), KNOWLEDGE("K", "知识库"), Ask("A", "问答");
	
	private String type;
	
	private String desc;

	private ArticleType(String type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static ArticleType getArticleTypeByType(String type){
		if(StringUtils.isEmpty(type)){
			return null;
		}
		for(ArticleType articleType : ArticleType.values()){
			if(articleType.getType().equals(type)){
				return articleType;
			}
		}
		return null;
	}
	
}
