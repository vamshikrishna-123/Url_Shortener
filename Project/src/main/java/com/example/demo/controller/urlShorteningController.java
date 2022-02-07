package com.example.demo.controller;
//package com.example.demo.service;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//import com.example.demo.model.Employee;
import com.example.demo.model.Url;
import com.example.demo.model.UrlDto;
import com.example.demo.model.UrlErrorResponseDto;
import com.example.demo.model.UrlResponseDto;
import com.example.demo.repository.UrlRepository;
import com.example.demo.service.UrlService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class urlShorteningController {
	
	@Autowired 
	private UrlService urlservice;
	
	@Autowired
	private UrlRepository urlrepository;
	
	@RequestMapping(method=RequestMethod.POST, value="/generate")
	public ResponseEntity<?> generateShortLink(@RequestBody UrlDto urldto)
	{
		Url urlToRet= urlservice.generateShorterLink(urldto);
		if(urlToRet!=null)
		{	
		
			UrlResponseDto urlresponsedto=new UrlResponseDto();
			urlresponsedto.setExpirationDate(urlToRet.getExpirationDate());
			urlresponsedto.setOriginalUrl(urldto.getUrl());
			urlresponsedto.setShortUrl(urlToRet.getShortLink());
			return new ResponseEntity<UrlResponseDto>(urlresponsedto,HttpStatus.OK);
		}
		
		UrlErrorResponseDto urlerror=new UrlErrorResponseDto();
		urlerror.setStatus("404");
		urlerror.setError("Either there is no input or error while processing the input please try later");
		return new ResponseEntity<UrlErrorResponseDto>(urlerror,HttpStatus.NOT_FOUND);
	}
	@GetMapping("/generate")
	public List<Url> getAllEmployees()
	{
		return urlrepository.findAll();
	}
	@GetMapping("/generate/{shortlink}")
	public ResponseEntity<?> redirectToOriginalLink(@PathVariable String shortlink, HttpServletResponse response) throws IOException
	{
		if(StringUtils.isEmpty(shortlink))
		{
			UrlErrorResponseDto urlerror=new UrlErrorResponseDto();
			urlerror.setStatus("400");
			urlerror.setError("Invalid Url");
			return new ResponseEntity<UrlErrorResponseDto>(urlerror,HttpStatus.NOT_FOUND);
		}
		Url urlToRet= urlservice.getEncodedUrl(shortlink);
		if(urlToRet==null)
		{
			UrlErrorResponseDto urlerror=new UrlErrorResponseDto();
			urlerror.setStatus("400");
			urlerror.setError("Url doesn't exist or might expired");
			return new ResponseEntity<UrlErrorResponseDto>(urlerror,HttpStatus.NOT_FOUND);
		}
		if(urlToRet.getExpirationDate().isBefore(LocalDateTime.now())) {
			urlservice.deleteShortLink(urlToRet);
			UrlErrorResponseDto urlerror=new UrlErrorResponseDto();
			urlerror.setStatus("200");
			urlerror.setError("Url Expired please try create new");
			return new ResponseEntity<UrlErrorResponseDto>(urlerror,HttpStatus.FORBIDDEN);
		}
		response.sendRedirect(urlToRet.getOriginalUrl());
		return null;
	}
	@RequestMapping(method=RequestMethod.DELETE, value="/generate")
	public void  deletealltheurls() {
		urlservice.deleteall();
	}
	
}
