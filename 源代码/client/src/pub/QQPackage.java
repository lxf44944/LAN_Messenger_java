package pub;

import java.io.Serializable;

//封装数据的包
public class QQPackage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6815508882700277814L;
	/**
	 * 
	 */
	private String from;
	private String to;
	private PackType packType;// 包类型
	private Object data;// 传送数据

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public PackType getPackType() {
		return packType;
	}

	public void setPackType(PackType packType) {
		this.packType = packType;
	}

}
