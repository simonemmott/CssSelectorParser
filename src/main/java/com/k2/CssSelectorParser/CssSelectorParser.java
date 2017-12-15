package com.k2.CssSelectorParser;

import java.util.ArrayList;
import java.util.List;

/**
 * This class parses CSS selectors into structured data to compare to Xml elements
 * @author simon
 *
 */
public class CssSelectorParser {

	/**
	 * This method checks whether the given character is whitespace
	 * @param c	The character to test
	 * @return	True is the character is whitespace
	 */
	public static boolean isWhitespace(char c) {
		return (c==' ' || c=='\n' || c=='\t' || c=='\f');
	}

	/**
	 * This static method invokes the parser to parse the given CSS selector into structured data
	 * 
	 * @param cssSelector The CSS selector to parse
	 * @return Structured data representing the given CSS selector
	 */
	public static List<CssElementFilter> parse(String cssSelector) {
		CssSelectorParser parser = new CssSelectorParser();
		return parser.parseInner(cssSelector);
	}
	
	Boolean inQuotes = null;
	
	/**
	 * This private method is called internally after creating an instance of the CssSelectorParser
	 * @param cssSelector The CSS selector to parse
	 * @return	Structured data representing the given CSS selector
	 */
	private List<CssElementFilter> parseInner(String cssSelector) {
		
		// The filters list is a list of each CSS selector chain to be applied to an element
		List<CssElementFilter> filters = new ArrayList<CssElementFilter>();
		
		// Create the first element filter and add it to the list
		CssElementFilter filter = new CssElementFilter(this);
		filters.add(filter);
		
		// Initially we are not in an attributes clause
		boolean inAttrClause = false;
		
		// Trim leading and trailing white space and store the resultant characters in an array for parsing
		char[] chars = cssSelector.trim().toCharArray();
		
		// read each character in the trimmed CSS selector
		for (int i=0; i<chars.length; i++) {
			
			char c = chars[i];
			
			// If the parser is no currently in quotes check whether the parser is parsing an attraibutes clause
			if (inQuotes == null || !inQuotes) {
				if (c=='[') inAttrClause = true;
				if (c==']') inAttrClause = false;
			}
				
			// Check whether a new element filter is being defined
			if (!inAttrClause && (isWhitespace(c) || c==',' || c=='>' || c=='+' || c=='~')) {
				CssElementFilter.PreviousFilterRule previousFilterRule = null;
				boolean newFilter = false;
				
				// Identify the type of the new filter new chain or add to chain 
				while (isWhitespace(c) || c==',' || c=='>' || c=='+' || c=='~') {
					if (isWhitespace(c)) {
						if (previousFilterRule == null && !newFilter) previousFilterRule = CssElementFilter.PreviousFilterRule.IS_ANCESTOR;
					} else {
						switch(c) {
						case ',':
							newFilter = true;
							break;
						case '>':
							if (!newFilter) previousFilterRule = CssElementFilter.PreviousFilterRule.IS_PARENT;
							break;
						case '+':
							if (!newFilter) previousFilterRule = CssElementFilter.PreviousFilterRule.PREVIOUS_SIBLING;
							break;
						case '~':
							if (!newFilter) previousFilterRule = CssElementFilter.PreviousFilterRule.NEXT_SIBLING;
							break;
						}
					}
					c = chars[++i];
				}
				
				// If a new filter chain is required create a new filter for this parser and add it to the list of filters
				if (newFilter) {
					filter = new CssElementFilter(this);
					filters.add(filter);
				} else {
					// A new filter chain is not required so add the new filter to the end of the current chain
					filters.remove(filter);
					filter = new CssElementFilter(this, filter, previousFilterRule);
					filters.add(filter);
				}
			}
			
			// Pass the character to the current filter for parsing
			filter.parseChar(c);
		}
		
		return filters;
	}

}
