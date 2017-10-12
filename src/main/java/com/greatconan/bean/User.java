package com.greatconan.bean;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component(value="user")
public class User implements Serializable
{
	public static String field_id="id";
	public static String fileId_name="name";
	
	private String id;
	private String name;


	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
