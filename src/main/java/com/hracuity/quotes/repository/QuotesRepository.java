package com.hracuity.quotes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hracuity.quotes.model.Quote;

@Repository
public interface QuotesRepository extends JpaRepository<Quote, Long>{
	@Query("SELECT m FROM Quote m WHERE m.text LIKE %:text%")
    List<Quote> searchByTitleLike(@Param("text") String text);
	
	@Query("SELECT m FROM Quote m WHERE m.author LIKE %:author%")
    List<Quote> searchByAuthor(@Param("author") String author);
}
