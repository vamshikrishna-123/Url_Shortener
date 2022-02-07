package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Url;

// This is used for crud operations  
@Repository
public interface UrlRepository extends JpaRepository<Url,Long> {
	public Url findByShortLink(String shortLink);
}
