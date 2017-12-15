package com.k2.CssSelectorParser;

import java.util.List;

public class BasicExample {

	public BasicExample() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		List<CssElementFilter> filters = CssSelectorParser.parse("[attr=value]");
		
		System.out.println(filters.get(0).elementFilterRules.size());
		
	}

}
