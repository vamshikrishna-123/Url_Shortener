package com.example.demo.service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Url;
import com.example.demo.model.UrlDto;
import com.example.demo.repository.UrlRepository;
import com.google.common.hash.Hashing;

import ch.qos.logback.classic.Logger;
@Component
public class UrlserviceLogic implements UrlService{

    private static final org.jboss.logging.Logger logger = LoggerFactory.logger(UrlserviceLogic.class);
	@Autowired
	private UrlRepository urlrepository;
	
	
	@Override
	public Url generateShorterLink(UrlDto urlDto) {
		if(StringUtils.isNotEmpty(urlDto.getUrl()))
		{
			String encodedeUrl=encodedUrl(urlDto.getUrl());
			Url urlToPersist = new Url();
			urlToPersist.setCreationDate(LocalDateTime.now());
			urlToPersist.setOriginalUrl(urlDto.getUrl());
			urlToPersist.setShortLink(encodedeUrl);
			urlToPersist.setExpirationDate(getExpirationDate(urlDto.getExpirationDate(),urlToPersist.getCreationDate()));
			Url UrlToRet=persistShortLink(urlToPersist);
			if(UrlToRet!=null)
			{
			return UrlToRet;	
			}
			return null;
			
			
		}
		
		return null;
	}

	private LocalDateTime getExpirationDate(String expirationDate, LocalDateTime creationDate) {
		if(StringUtils.isBlank(expirationDate))
		{
			return creationDate.plusMinutes(5);
		}
		LocalDateTime expirationtime=LocalDateTime.parse(expirationDate);
		return expirationtime;
	}

	private String encodedUrl(String url) {
		
	String encodedOutput="";
	LocalDateTime time=LocalDateTime.now();
	encodedOutput=Hashing.murmur3_32_fixed().hashString(url.concat(time.toString()),StandardCharsets.UTF_8).toString();
	return encodedOutput;
	}
	// Save to Date Base
	@Override
	public Url persistShortLink(Url url) {
		Url UrlToRet=urlrepository.save(url);
		return UrlToRet;
	}

	@Override
	public Url getEncodedUrl(String url) {
		Url UrlToRet=urlrepository.findByShortLink(url);
		return UrlToRet;
		
	}

	@Override
	public void deleteShortLink(Url url) {
			urlrepository.delete(url);
		
	}
//	@Override
	public void deleteall() {
		urlrepository.deleteAll();
	}

}
