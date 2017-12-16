package com.k2.CssSelectorParser;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a set of rules to be applied to an Xml element
 * 
 * @author simon
 *
 */
public class CssElementFilter {
	
	/**
	 * This enumeration identifies the type of relationship between filters in a chain
	 * 
	 * @author simon
	 *
	 */
	public enum PreviousFilterRule {
		IS_ANCESTOR,
		IS_PARENT,
		PREVIOUS_SIBLING,
		NEXT_SIBLING
	}
	public PreviousFilterRule rule;
	public CssElementFilter previousFilter;
	
	/**
	 * This list defines the filter rules to be applied to an element
	 */
	public List<CssElementFilterRule> elementFilterRules = new ArrayList<CssElementFilterRule>();
	private CssElementFilterRule currentElementFilterRule;
	
	private CssSelectorParser parser;
	
	/**
	 * Create a new element filter as an extension of an existing element filter to create or extend a filter chain
	 * @param parser The parser parsing the CSS selector
	 * @param previousFilter The previous filter in the chain of element filters
	 * @param rule	The relationship type between this element filter and the end of the chain
	 */
	CssElementFilter(CssSelectorParser parser, CssElementFilter previousFilter, PreviousFilterRule rule) {
		this.parser = parser;
		this.previousFilter = previousFilter;
		this.rule = rule;
		
		currentElementFilterRule = new CssElementFilterRule(parser);
		elementFilterRules.add(currentElementFilterRule);
		
	}

	/**
	 * Create a new element filter 
	 * @param parser The parser parsing the CSS selector
	 */
	CssElementFilter(CssSelectorParser parser) {

		this.parser = parser;

		currentElementFilterRule = new CssElementFilterRule(parser);
		elementFilterRules.add(currentElementFilterRule);

	}
	
	/**
	 * Parse the given character into the filter
	 * 
	 * If the character ends the current rule create a new rule and add it to the rules for this element filter
	 * @param c
	 */
	void parseChar(char c) {
		if (currentElementFilterRule.parseChar(c)) {
			currentElementFilterRule = new CssElementFilterRule(parser);
			elementFilterRules.add(currentElementFilterRule);
			currentElementFilterRule.parseChar(c);
		}
	}

}
