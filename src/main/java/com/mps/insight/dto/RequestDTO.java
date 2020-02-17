package com.mps.insight.dto;

public class RequestDTO {
	private String userID;
	private String webmartID;
	private String requestDate;
	private String origin;
	private String host;
	private String ipaddress;
	private String userAgent;
	private String referer;
	private String uri;
	private String token;
	private String data;
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getWebmartID() {
		return webmartID;
	}
	public void setWebmartID(String webmartID) {
		this.webmartID = webmartID;
	}
	public String getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public String getReferer() {
		return referer;
	}
	public void setReferer(String referer) {
		this.referer = referer;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "RequestDTO [userID=" + userID + ", webmartID=" + webmartID + ", requestDate=" + requestDate
				+ ", origin=" + origin + ", host=" + host + ", ipaddress=" + ipaddress + ", userAgent=" + userAgent
				+ ", referer=" + referer + ", uri=" + uri + ", token=" + token + ", data=" + data + "]";
	}
	

}
