package com.gency.subscribe.model.business;

import java.util.List;


public class SocketMessage {

	private Integer type;
	
	private String message;
	
	private List<Goods> goods;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Goods> getGoods() {
		return goods;
	}

	public void setGoods(List<Goods> goods) {
		this.goods = goods;
	}
	
	
	
}
