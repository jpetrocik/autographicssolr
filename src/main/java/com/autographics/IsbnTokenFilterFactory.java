package com.autographics;

import java.util.Map;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.util.TokenFilterFactory;

public class IsbnTokenFilterFactory extends TokenFilterFactory {

	public IsbnTokenFilterFactory(Map<String, String> args) {
		super(args);
		if (!args.isEmpty()) {
			throw new IllegalArgumentException("Unknown parameters: " + args);
		}
	}

	@Override
	public TokenStream create(TokenStream input) {
		return new IsbnFilter(input);
	}

}
