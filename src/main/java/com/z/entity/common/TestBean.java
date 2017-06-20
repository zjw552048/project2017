package com.z.entity.common;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;

@XmlRootElement
public class TestBean {
	private int id;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "TestBean [id=" + id + ", date=" + date + "]";
	}
	
}
