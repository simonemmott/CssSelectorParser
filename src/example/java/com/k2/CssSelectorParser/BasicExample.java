package com.k2.CssSelectorParser;

import java.util.List;

public class BasicExample {

	public BasicExample() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		String cssSelector = ".myClass > img[src$=.jpg], ol#thisId + li[data-myData='some data value']";
		
		List<CssElementFilter> filters = CssSelectorParser.parse(cssSelector);
		
		System.out.println();
		System.out.println("The CSS selector: "+cssSelector+" was parsed to generate");
		System.out.println();		
		System.out.println(filters.get(0).previousFilter.elementFilterRules.get(0).type + " " + filters.get(0).previousFilter.elementFilterRules.get(0).check);
		System.out.println(filters.get(0).rule + " of");
		System.out.println(filters.get(0).elementFilterRules.get(0).type + " " + filters.get(0).elementFilterRules.get(0).check + " and");
		System.out.println(filters.get(0).elementFilterRules.get(1).attribute + " " + filters.get(0).elementFilterRules.get(1).type + " " + filters.get(0).elementFilterRules.get(1).check);
		System.out.println("Or");
		System.out.println(filters.get(1).previousFilter.elementFilterRules.get(0).type + " " + filters.get(1).previousFilter.elementFilterRules.get(0).check + " and");
		System.out.println(filters.get(1).previousFilter.elementFilterRules.get(1).type + " " + filters.get(1).previousFilter.elementFilterRules.get(1).check);
		System.out.println(filters.get(1).rule + " of");
		System.out.println(filters.get(1).elementFilterRules.get(0).type + " " + filters.get(1).elementFilterRules.get(0).check);
		System.out.println(filters.get(1).elementFilterRules.get(1).attribute + " " + filters.get(1).elementFilterRules.get(1).type + " " + filters.get(1).elementFilterRules.get(1).check);
		
	}

}
