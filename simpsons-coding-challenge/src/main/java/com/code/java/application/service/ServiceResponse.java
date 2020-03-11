package com.code.java.application.service;

import com.code.java.application.bean.CharacterBean;

public class ServiceResponse<T> {
	private T data;
	private String status;
	public ServiceResponse(String string, CharacterBean savedCBean) {
		status=string;
		data=(T) savedCBean;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ServiceResponse [data=" + data + ", status=" + status + ", getData()=" + getData() + ", getStatus()="
				+ getStatus() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	

}
