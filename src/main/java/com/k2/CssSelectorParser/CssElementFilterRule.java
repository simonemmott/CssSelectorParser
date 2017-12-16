package com.k2.CssSelectorParser;

public class CssElementFilterRule {
	
	/**
	 * This enumeration defined the types of rules that can be applied to a single element through CSS selectors
	 * @author simon
	 *
	 */
	public enum Type {
		TAG_EQUALS,
		ID_EQUALS,
		HAS_CLASS,
		ANY_TAG,
		HAS_ATTRIBUTE,
		ATTRUBUTE_EQUALS,
		ATTRIBUTE_CONTAINS_WORD,
		ATTRIBUTE_STARTS_WITH_TILL_HYPHEN,
		ATTRIBUTE_STARTS_WITH,
		ATTRIBUTE_ENDS_WITH,
		ATTRIBUTE_CONTAINS_STRING
		
	}
	
	/**
	 * The type of this rule
	 */
	public Type type = Type.ANY_TAG;
	/**
	 * The name of the attribute to which this rule applies
	 */
	public String attribute = "";
	/**
	 * The value that should be used when checking this rule against an element
	 */
	public String check = "";
	
	private Boolean endRule = null;
	private char quote;
	private CssSelectorParser parser;

	/**
	 * Create a new filter rule for the given parser
	 * @param parser
	 */
	CssElementFilterRule(CssSelectorParser parser) {
		this.parser = parser;
		parser.inQuotes = null;
	}
		
	private boolean isStartRule(char c) {
		return (c=='.' || c=='#' || c=='*' || c=='[');
	}
	
	/**
	 * Parse the given character into this rule
	 * @param c The character to parse
	 * @return True if the given charater is the start of a new rule
	 */
	boolean parseChar(char c) {
		
		// If this is the first character for the rule identify the rule type
		if (endRule == null) {
			endRule = false;
			switch(c) {
			case '.':
				type = Type.HAS_CLASS;
				break;
			case '#':
				type = Type.ID_EQUALS;
				break;
			case '*':
				endRule = true;
				type = Type.ANY_TAG;
				break;
			case '[':
				type = Type.HAS_ATTRIBUTE;
				break;
			default:
				type = Type.TAG_EQUALS;
				check = check+c;
			}
			return false;
		}
		
		// If the previous character identified that the rule was ending end the rule
		if (endRule) return true;
		
		// Build the rule from the given characters
		switch(type) {
		case ANY_TAG:
			return true;
		case ATTRUBUTE_EQUALS:
		case ATTRIBUTE_CONTAINS_STRING:
		case ATTRIBUTE_CONTAINS_WORD:
		case ATTRIBUTE_ENDS_WITH:
		case ATTRIBUTE_STARTS_WITH:
		case ATTRIBUTE_STARTS_WITH_TILL_HYPHEN:
			
			// Build the element equals etc. check value
			if (parser.inQuotes == null && (CssSelectorParser.isWhitespace(c) || c=='=')) {
				return false;
			}
			if (parser.inQuotes == null) {
				if (c=='\'' || c=='"') {
					parser.inQuotes = true;
					quote = c;
					return false;
				} else {
					parser.inQuotes = false;
					if (c!=']') { 
						check=check+c;
					} else {
						endRule=true;
					}
					return false;
				}
			}
			if (parser.inQuotes) {
				if (c==quote) {
					parser.inQuotes = null;
					return false;
				} else {
					check=check+c;
					return false;
				}
			} else {
				if (c!=']') { 
					check=check+c;
				} else {
					endRule=true;
				}
				return false;
			}
		case HAS_ATTRIBUTE:
			
			// If the char is whitespace ignore it
			if (CssSelectorParser.isWhitespace(c)) return false;

			// If the rule is an has attribute rule identify whether the rule should also constrain the attribute and if so how.
			switch(c) {
			case '=':
				type=Type.ATTRUBUTE_EQUALS; 
				return false;
			case '~':
				type=Type.ATTRIBUTE_CONTAINS_WORD; 
				return false;
			case '|':
				type=Type.ATTRIBUTE_STARTS_WITH_TILL_HYPHEN; 
				return false;
			case '^':
				type=Type.ATTRIBUTE_STARTS_WITH; 
				return false;
			case '$':
				type=Type.ATTRIBUTE_ENDS_WITH; 
				return false;
			case '*':
				type=Type.ATTRIBUTE_CONTAINS_STRING; 
				return false;
			case ']':
				endRule = true; 
				return false;
			}
			// Build the attribute name value
			attribute = attribute+c;
			return false;
		case HAS_CLASS:
		case ID_EQUALS:
		case TAG_EQUALS:
			// Build the id, class or tag check value
			if (isStartRule(c) || CssSelectorParser.isWhitespace(c)) return true;
			check = check+c;
			return false;
		default:
			return true;
		}
		
	}

}
