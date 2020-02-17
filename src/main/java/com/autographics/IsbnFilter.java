package com.autographics;

import java.io.IOException;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class IsbnFilter extends TokenFilter{
	private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);

	public IsbnFilter(TokenStream input) {
		super(input);
	}

	@Override
	final public boolean incrementToken() throws IOException {
		
		//increments token stream to next token, if no more tokens returns false
		boolean nextToken = input.incrementToken();
		if (nextToken) {

			//length and value of next token
			String term = termAtt.toString();

			//cleans the isbn
			String cleanIsbn = normalizeIsbn(term);
			
			//clears the current token term attributes and resets it with the cleaned value 
			termAtt.setEmpty();
			termAtt.copyBuffer(cleanIsbn.toCharArray(), 0, cleanIsbn.length());
		}

		return nextToken;
	}

	/**
	 * Accepts a 10 or 13 digit isbn number and returns a 12 digit without a checksum value.  
	 * Trimming anything after the first 10 or 13 digits
	 */
	String normalizeIsbn(String term) {
		return term;
	}
}