package com.autographics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.apache.lucene.analysis.core.KeywordTokenizer;
import org.junit.Before;
import org.junit.Test;

public class IsbnFilterTest 
{
	
	IsbnFilter uut;
	
	@Before
	public void setup() {
		KeywordTokenizer tokenStream = new KeywordTokenizer();
		uut = new IsbnFilter(tokenStream);
	}
	
	/**
	 * Accepts 10 digit isbn and return 13 digit without checksum
	 */
    @Test
    public void accepts10DigitIsbn()
    {
    	//with x for checksum and hyphen, should drop and prefix 978
    	String results = uut.normalizeIsbn("19377-1568X");
    	assertEquals("978193771568", results);

    	//with checksum and hyphen, should drop and prefix 978
    	results = uut.normalizeIsbn("19377-15684");
    	assertEquals("978193771568", results);

    	//without checksum, prefix 978
    	results = uut.normalizeIsbn("193771568");
    	assertEquals("978193771568", results);
    }

	/**
	 * Accepts 13 digit isbn and returns without checksum
	 */
    @Test
    public void accepts13DigitIsbn()
    {
    	String results = uut.normalizeIsbn("9781937715687");
    	assertEquals("978193771568", results);

    	results = uut.normalizeIsbn("978-1937715687");
    	assertEquals("978193771568", results);
    }

	/**
	 * Accepts 10 digit isbn and return 13 digit without checksum
	 */
    @Test
    public void accepts10DigitWithGarbageIsbn()
    {
    	String results = uut.normalizeIsbn("1937715681 16.00");
    	assertEquals("978193771568", results);

    	results = uut.normalizeIsbn("1937715681 vol 1");
    	assertEquals("978193771568", results);
    }

	/**
	 * Exception cases when value is invalid
	 */
    @Test
    public void handleBadIsbn()
    {
    	//too short
    	String results = uut.normalizeIsbn("19377156");
    	assertNull(results);

    	//starts with letters
    	results = uut.normalizeIsbn("Book1937715687");
    	assertNull(results);
    }

}
