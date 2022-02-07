package com.example.demo.model;

import java.time.LocalDateTime;

// This is for User Response
public class UrlResponseDto {
	private String originalUrl;
	private String shortUrl;
	private LocalDateTime expirationDate;
	
	
	public UrlResponseDto(String originalUrl, String shortUrl, LocalDateTime expirationDate) {
		super();
		this.originalUrl = originalUrl;
		this.shortUrl = shortUrl;
		this.expirationDate = expirationDate;
	}


	public UrlResponseDto() {
		super();
	}


	public String getOriginalUrl() {
		return originalUrl;
	}


	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}


	public String getShortUrl() {
		return shortUrl;
	}


	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}


	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}


	public void setExpirationDate(LocalDateTime expirationDate) {
		this.expirationDate = expirationDate;
	}


	@Override
	public String toString() {
		return "UrlResponseDto [originalUrl=" + originalUrl + ", shortUrl=" + shortUrl + ", expirationDate="
				+ expirationDate + "]";
	}
	
	
	
}
