package com.greatconan.bean;
public class FtpUseBean {
	private String host;
	private Integer port;
	private String userName;
	private String password;
   	private String ftpSeperator;
   	private String ftpPath="";
	private int repeatTime = 0;//连接ftp服务器的次数
	
	public String getHost() {
		return host;
	}
	
	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public void setFtpSeperator(String ftpSeperator) {
		this.ftpSeperator = ftpSeperator;
	}

	public String getFtpSeperator() {
		return ftpSeperator;
	}

	public void setFtpPath(String ftpPath) {
		if(ftpPath!=null)
			this.ftpPath = ftpPath;
	}

	public String getFtpPath() {
		return ftpPath;
	}

	public void setRepeatTime(int repeatTime) {
		if (repeatTime > 0)
			this.repeatTime = repeatTime;
	}

	public int getRepeatTime() {
		return repeatTime;
	}

	/**
	 * take an example:<br>
	 * ftp://userName:password@ip:port/ftpPath/
	 * @return 
	 */
	public String getFTPURL() {
		StringBuffer buf = new StringBuffer();
		buf.append("ftp://");
		buf.append(getUserName());
		buf.append(":");
		buf.append(getPassword());
		buf.append("@");
		buf.append(getHost());
		buf.append(":");
		buf.append(getPort());
		buf.append("/");
		buf.append(getFtpPath());
		 
		return buf.toString();
	}
}
