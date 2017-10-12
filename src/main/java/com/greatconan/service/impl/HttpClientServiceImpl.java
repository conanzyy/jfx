package com.greatconan.service.impl;

import com.greatconan.service.HttpClientService;
import com.greatconan.util.HttpClientUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "httpClientService")
public class HttpClientServiceImpl implements HttpClientService
{
	@Resource(name = "httpClientUtils")
	HttpClientUtils httpClientUtils;
	@Override
	public String doGet(String url)
	{
		return httpClientUtils.doGet(url);
	}
}
