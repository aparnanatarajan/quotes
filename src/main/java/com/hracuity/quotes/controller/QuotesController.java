package com.hracuity.quotes.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hracuity.quotes.model.Quote;
import com.hracuity.quotes.service.QuotesService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/quotes")
@Log4j2
public class QuotesController {	
	
	@Autowired
    QuotesService quotesService;
	
	@PostMapping
	public ResponseEntity<Quote> createQuote(@Valid @RequestBody Quote quote) {
		System.out.println("Post received - createQuote " + quote.getAuthor());
		Quote newQuote = quotesService.createQuote(quote);
	    return new ResponseEntity<Quote>(newQuote, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Quote>> listAllQuotes() {
		System.out.println("Get request received - listAllQuotes");
	    return new ResponseEntity<List<Quote>>(quotesService.listAllQuotes(), HttpStatus.OK);
	}
	
	@GetMapping("text={quoteContains}")
	public ResponseEntity<List<Quote>> getQuoteByKeyword(@PathVariable(value = "quoteContains") @Size(min = 3) String text) {
		System.out.println("Get request received - getQuoteByKeyword - " + text);
	    return new ResponseEntity<List<Quote>>(quotesService.searchQuotesByKeyword(text), HttpStatus.OK);
	}
	
	@GetMapping("author={quoteAuthor}")
	public ResponseEntity<List<Quote>> getQuoteByAuthor(@PathVariable(value = "quoteAuthor") @Size(min = 3) String author) {
		System.out.println("Get request received - getQuoteByAuthor - " + author);
	    return new ResponseEntity<List<Quote>>(quotesService.searchQuotesByAuthor(author), HttpStatus.OK);
	}
	
	@PutMapping
	public Quote updateQuote(@Valid @RequestBody Quote quote) {
		System.out.println ("Put request received - updateQuote " + quote.getId());
	    return quotesService.updateQuote(quote);
	}
	
	@DeleteMapping("{quoteId}")
	public ResponseEntity<String> deleteQuote(@PathVariable(value = "quoteId") Long id) {
		System.out.println("Delete request received - deleteQuote - " + id);
		quotesService.deleteQuote(id);
		return new ResponseEntity<String>("Quote has been deleted successfully.", HttpStatus.OK);
	}
}
