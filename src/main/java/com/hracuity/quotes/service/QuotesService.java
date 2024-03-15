package com.hracuity.quotes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hracuity.quotes.model.Quote;
import com.hracuity.quotes.repository.QuotesRepository;

@Service
public class QuotesService {
	@Autowired
	QuotesRepository quotesRepository;

	public Quote createQuote(Quote quote) {
		return quotesRepository.save(quote);
	}

	public List<Quote> createQuotes(List<Quote> quotes) {
		return quotesRepository.saveAll(quotes);
	}

	public List<Quote> listAllQuotes() {
		return quotesRepository.findAll();
	}

	public List<Quote> searchQuotesByKeyword(String text) {
		return quotesRepository.searchByTitleLike(text);
	}
	
	public List<Quote> searchQuotesByAuthor(String text) {
		return quotesRepository.searchByAuthor(text);
	}

	public Quote updateQuote(Quote newQuote) {
		Quote currQuote = quotesRepository.findById(newQuote.getId()).get();
		currQuote.setAuthor(newQuote.getAuthor());
		currQuote.setText(newQuote.getText());
		return quotesRepository.save(currQuote);
	}

	public void deleteQuote(Long quoteId) {
		quotesRepository.deleteById(quoteId);
	}
}
