package com.k2.CssSelectorParser;


import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.k2.CssSelectorParser.CssElementFilter.PreviousFilterRule;


/**
 * Unit test for CssSelectorParser.
 */
public class CssSelectorParserTest {

	@Test
    public void tagEqualsTest()
    {
    	
		List<CssElementFilter> filters = CssSelectorParser.parse("aaa");
		
		assertEquals(filters.size(), 1);
		
		CssElementFilter filter = filters.get(0);
		
		assertNull(filter.previousFilter);
		assertNull(filter.rule);
		assertNotNull(filter.elementFilterRules);
		if (filter.elementFilterRules != null) {
			assertEquals(filter.elementFilterRules.size(), 1);
			CssElementFilterRule rule = filter.elementFilterRules.get(0);
			assertEquals(rule.type, CssElementFilterRule.Type.TAG_EQUALS);
			assertEquals(rule.check, "aaa");
		}

    }

	@Test
    public void hasClassTest()
    {
    	
		List<CssElementFilter> filters = CssSelectorParser.parse(".cls");
		
		assertEquals(filters.size(), 1);
		
		CssElementFilter filter = filters.get(0);
		
		assertNull(filter.previousFilter);
		assertNull(filter.rule);
		assertNotNull(filter.elementFilterRules);
		if (filter.elementFilterRules != null) {
			assertEquals(filter.elementFilterRules.size(), 1);
			CssElementFilterRule rule = filter.elementFilterRules.get(0);
			assertEquals(rule.type, CssElementFilterRule.Type.HAS_CLASS);
			assertEquals(rule.check, "cls");
		}

    }

	@Test
    public void idEqualsTest()
    {
    	
		List<CssElementFilter> filters = CssSelectorParser.parse("#id");
		
		assertEquals(filters.size(), 1);
		
		CssElementFilter filter = filters.get(0);
		
		assertNull(filter.previousFilter);
		assertNull(filter.rule);
		assertNotNull(filter.elementFilterRules);
		if (filter.elementFilterRules != null) {
			assertEquals(filter.elementFilterRules.size(), 1);
			CssElementFilterRule rule = filter.elementFilterRules.get(0);
			assertEquals(rule.type, CssElementFilterRule.Type.ID_EQUALS);
			assertEquals(rule.check, "id");
		}

    }

	@Test
    public void anyTagTest()
    {
    	
		List<CssElementFilter> filters = CssSelectorParser.parse("*");
		
		assertEquals(filters.size(), 1);
		
		CssElementFilter filter = filters.get(0);
		
		assertNull(filter.previousFilter);
		assertNull(filter.rule);
		assertNotNull(filter.elementFilterRules);
		if (filter.elementFilterRules != null) {
			assertEquals(filter.elementFilterRules.size(), 1);
			CssElementFilterRule rule = filter.elementFilterRules.get(0);
			assertEquals(rule.type, CssElementFilterRule.Type.ANY_TAG);
		}

    }

	@Test
    public void hasAttributeTest()
    {
    	
		List<CssElementFilter> filters = CssSelectorParser.parse("[attr]");
		
		assertEquals(filters.size(), 1);
		
		CssElementFilter filter = filters.get(0);
		
		assertNull(filter.previousFilter);
		assertNull(filter.rule);
		assertNotNull(filter.elementFilterRules);
		if (filter.elementFilterRules != null) {
			assertEquals(filter.elementFilterRules.size(), 1);
			CssElementFilterRule rule = filter.elementFilterRules.get(0);
			assertEquals(rule.type, CssElementFilterRule.Type.HAS_ATTRIBUTE);
			assertEquals(rule.attribute, "attr");
		}

    }

	@Test
    public void AttributeEqualsTest()
    {
    	
		List<CssElementFilter> filters = CssSelectorParser.parse("[attr=value]");
		
		assertEquals(filters.size(), 1);
		
		CssElementFilter filter = filters.get(0);
		
		assertNull(filter.previousFilter);
		assertNull(filter.rule);
		assertNotNull(filter.elementFilterRules);
		if (filter.elementFilterRules != null) {
			assertEquals(1, filter.elementFilterRules.size());
			CssElementFilterRule rule = filter.elementFilterRules.get(0);
			assertEquals(CssElementFilterRule.Type.ATTRUBUTE_EQUALS, rule.type);
			assertEquals("attr", rule.attribute);
			assertEquals("value", rule.check);
		}

    }

	@Test
    public void AttributeEqualsQuotedTest()
    {
    	
		List<CssElementFilter> filters = CssSelectorParser.parse("[attr='value and ,*][~|>+\"']");
		
		assertEquals(filters.size(), 1);
		
		CssElementFilter filter = filters.get(0);
		
		assertNull(filter.previousFilter);
		assertNull(filter.rule);
		assertNotNull(filter.elementFilterRules);
		if (filter.elementFilterRules != null) {
			assertEquals(1, filter.elementFilterRules.size());
			CssElementFilterRule rule = filter.elementFilterRules.get(0);
			assertEquals(CssElementFilterRule.Type.ATTRUBUTE_EQUALS, rule.type);
			assertEquals("attr", rule.attribute);
			assertEquals("value and ,*][~|>+\"", rule.check);
		}

		filters = CssSelectorParser.parse("[attr=\"value and ,*][~|>+\'\"]");
		
		assertEquals(filters.size(), 1);
		
		filter = filters.get(0);
		
		assertNull(filter.previousFilter);
		assertNull(filter.rule);
		assertNotNull(filter.elementFilterRules);
		if (filter.elementFilterRules != null) {
			assertEquals(1, filter.elementFilterRules.size());
			CssElementFilterRule rule = filter.elementFilterRules.get(0);
			assertEquals(CssElementFilterRule.Type.ATTRUBUTE_EQUALS, rule.type);
			assertEquals("attr", rule.attribute);
			assertEquals("value and ,*][~|>+\'", rule.check);
		}

    }

	@Test
    public void AttributeContainsWordTest()
    {
    	
		List<CssElementFilter> filters = CssSelectorParser.parse("[attr~=value]");
		
		assertEquals(filters.size(), 1);
		
		CssElementFilter filter = filters.get(0);
		
		assertNull(filter.previousFilter);
		assertNull(filter.rule);
		assertNotNull(filter.elementFilterRules);
		if (filter.elementFilterRules != null) {
			assertEquals(1, filter.elementFilterRules.size());
			CssElementFilterRule rule = filter.elementFilterRules.get(0);
			assertEquals(CssElementFilterRule.Type.ATTRIBUTE_CONTAINS_WORD, rule.type);
			assertEquals("attr", rule.attribute);
			assertEquals("value", rule.check);
		}

    }

	@Test
    public void AttributeStartsWithTillHyphenTest()
    {
    	
		List<CssElementFilter> filters = CssSelectorParser.parse("[attr|=value]");
		
		assertEquals(filters.size(), 1);
		
		CssElementFilter filter = filters.get(0);
		
		assertNull(filter.previousFilter);
		assertNull(filter.rule);
		assertNotNull(filter.elementFilterRules);
		if (filter.elementFilterRules != null) {
			assertEquals(1, filter.elementFilterRules.size());
			CssElementFilterRule rule = filter.elementFilterRules.get(0);
			assertEquals(CssElementFilterRule.Type.ATTRIBUTE_STARTS_WITH_TILL_HYPHEN, rule.type);
			assertEquals("attr", rule.attribute);
			assertEquals("value", rule.check);
		}

    }

	@Test
    public void AttributeStartsWithTest()
    {
    	
		List<CssElementFilter> filters = CssSelectorParser.parse("[attr^=value]");
		
		assertEquals(filters.size(), 1);
		
		CssElementFilter filter = filters.get(0);
		
		assertNull(filter.previousFilter);
		assertNull(filter.rule);
		assertNotNull(filter.elementFilterRules);
		if (filter.elementFilterRules != null) {
			assertEquals(1, filter.elementFilterRules.size());
			CssElementFilterRule rule = filter.elementFilterRules.get(0);
			assertEquals(CssElementFilterRule.Type.ATTRIBUTE_STARTS_WITH, rule.type);
			assertEquals("attr", rule.attribute);
			assertEquals("value", rule.check);
		}

    }

	@Test
    public void AttributeEndsWithTest()
    {
    	
		List<CssElementFilter> filters = CssSelectorParser.parse("[attr $= value]");
		
		assertEquals(filters.size(), 1);
		
		CssElementFilter filter = filters.get(0);
		
		assertNull(filter.previousFilter);
		assertNull(filter.rule);
		assertNotNull(filter.elementFilterRules);
		if (filter.elementFilterRules != null) {
			assertEquals(1, filter.elementFilterRules.size());
			CssElementFilterRule rule = filter.elementFilterRules.get(0);
			assertEquals(CssElementFilterRule.Type.ATTRIBUTE_ENDS_WITH, rule.type);
			assertEquals("attr", rule.attribute);
			assertEquals("value", rule.check);
		}

    }

	@Test
    public void AttributeContainsStringTest()
    {
    	
		List<CssElementFilter> filters = CssSelectorParser.parse("[attr*=value]");
		
		assertEquals(filters.size(), 1);
		
		CssElementFilter filter = filters.get(0);
		
		assertNull(filter.previousFilter);
		assertNull(filter.rule);
		assertNotNull(filter.elementFilterRules);
		if (filter.elementFilterRules != null) {
			assertEquals(1, filter.elementFilterRules.size());
			CssElementFilterRule rule = filter.elementFilterRules.get(0);
			assertEquals(CssElementFilterRule.Type.ATTRIBUTE_CONTAINS_STRING, rule.type);
			assertEquals("attr", rule.attribute);
			assertEquals("value", rule.check);
		}

    }

	@Test
    public void tagEqualsAndHasClassTest()
    {
    	
		List<CssElementFilter> filters = CssSelectorParser.parse("aaa.cls");
		
		assertEquals(filters.size(), 1);
		
		CssElementFilter filter = filters.get(0);
		
		assertNull(filter.previousFilter);
		assertNull(filter.rule);
		assertNotNull(filter.elementFilterRules);
		if (filter.elementFilterRules != null) {
			assertEquals(filter.elementFilterRules.size(), 2);
			CssElementFilterRule rule;
			if (filter.elementFilterRules.size() > 0) {
				rule = filter.elementFilterRules.get(0);
				assertEquals(rule.type, CssElementFilterRule.Type.TAG_EQUALS);
				assertEquals(rule.check, "aaa");
			}
			if (filter.elementFilterRules.size() > 1) {
				rule = filter.elementFilterRules.get(1);
				assertEquals(rule.type, CssElementFilterRule.Type.HAS_CLASS);
				assertEquals(rule.check, "cls");
			}
			
		}

    }

	@Test
    public void tagEqualsAndIdEqualsTest()
    {
    	
		List<CssElementFilter> filters = CssSelectorParser.parse("aaa#id");
		
		assertEquals(filters.size(), 1);
		
		CssElementFilter filter = filters.get(0);
		
		assertNull(filter.previousFilter);
		assertNull(filter.rule);
		assertNotNull(filter.elementFilterRules);
		if (filter.elementFilterRules != null) {
			assertEquals(filter.elementFilterRules.size(), 2);
			CssElementFilterRule rule;
			if (filter.elementFilterRules.size() > 0) {
				rule = filter.elementFilterRules.get(0);
				assertEquals(rule.type, CssElementFilterRule.Type.TAG_EQUALS);
				assertEquals(rule.check, "aaa");
			}
			if (filter.elementFilterRules.size() > 1) {
				rule = filter.elementFilterRules.get(1);
				assertEquals(rule.type, CssElementFilterRule.Type.ID_EQUALS);
				assertEquals(rule.check, "id");
			}
			
		}

    }
	
	@Test
    public void tagEqualsAndAttributeTest()
    {
    	
		List<CssElementFilter> filters = CssSelectorParser.parse("aaa[attr]");
		
		assertEquals(filters.size(), 1);
		
		CssElementFilter filter = filters.get(0);
		
		assertNull(filter.previousFilter);
		assertNull(filter.rule);
		assertNotNull(filter.elementFilterRules);
		if (filter.elementFilterRules != null) {
			assertEquals(filter.elementFilterRules.size(), 2);
			CssElementFilterRule rule;
			if (filter.elementFilterRules.size() > 0) {
				rule = filter.elementFilterRules.get(0);
				assertEquals(rule.type, CssElementFilterRule.Type.TAG_EQUALS);
				assertEquals(rule.check, "aaa");
			}
			if (filter.elementFilterRules.size() > 1) {
				rule = filter.elementFilterRules.get(1);
				assertEquals(rule.type, CssElementFilterRule.Type.HAS_ATTRIBUTE);
				assertEquals(rule.attribute, "attr");
			}
			
		}

    }

	@Test
    public void tagEqualsAndAttributeEqualsTest()
    {
    	
		List<CssElementFilter> filters = CssSelectorParser.parse("aaa[attr=value]");
		
		assertEquals(filters.size(), 1);
		
		CssElementFilter filter = filters.get(0);
		
		assertNull(filter.previousFilter);
		assertNull(filter.rule);
		assertNotNull(filter.elementFilterRules);
		if (filter.elementFilterRules != null) {
			assertEquals(filter.elementFilterRules.size(), 2);
			CssElementFilterRule rule;
			if (filter.elementFilterRules.size() > 0) {
				rule = filter.elementFilterRules.get(0);
				assertEquals(rule.type, CssElementFilterRule.Type.TAG_EQUALS);
				assertEquals(rule.check, "aaa");
			}
			if (filter.elementFilterRules.size() > 1) {
				rule = filter.elementFilterRules.get(1);
				assertEquals(rule.type, CssElementFilterRule.Type.ATTRUBUTE_EQUALS);
				assertEquals(rule.attribute, "attr");
				assertEquals(rule.check, "value");
			}
			
		}
    }

	@Test
    public void tagEqualsAndTwoAttributeEqualsTest()
    {
    	
		List<CssElementFilter> filters = CssSelectorParser.parse("aaa[attr=value][attr2=value2]");
		
		assertEquals(filters.size(), 1);
		
		CssElementFilter filter = filters.get(0);
		
		assertNull(filter.previousFilter);
		assertNull(filter.rule);
		assertNotNull(filter.elementFilterRules);
		if (filter.elementFilterRules != null) {
			assertEquals(3, filter.elementFilterRules.size());
			CssElementFilterRule rule;
			if (filter.elementFilterRules.size() > 0) {
				rule = filter.elementFilterRules.get(0);
				assertEquals(rule.type, CssElementFilterRule.Type.TAG_EQUALS);
				assertEquals(rule.check, "aaa");
			}
			if (filter.elementFilterRules.size() > 1) {
				rule = filter.elementFilterRules.get(1);
				assertEquals(rule.type, CssElementFilterRule.Type.ATTRUBUTE_EQUALS);
				assertEquals(rule.attribute, "attr");
				assertEquals(rule.check, "value");
			}
			if (filter.elementFilterRules.size() > 2) {
				rule = filter.elementFilterRules.get(2);
				assertEquals(rule.type, CssElementFilterRule.Type.ATTRUBUTE_EQUALS);
				assertEquals(rule.attribute, "attr2");
				assertEquals(rule.check, "value2");
			}
			
		}

    }

	@Test
    public void tagInTagTest()
    {
    	
		List<CssElementFilter> filters = CssSelectorParser.parse("aaa bbb");
		
		assertEquals(1, filters.size());
		
		if (filters.size() > 0) {
			CssElementFilter filter = filters.get(0);
		
			assertNotNull(filter.previousFilter);
			assertEquals(CssElementFilter.PreviousFilterRule.IS_ANCESTOR, filter.rule);
			
			if (filter.previousFilter != null) {
				CssElementFilter previousFilter = filter.previousFilter;
				assertNull(previousFilter.previousFilter);
				assertNull(previousFilter.rule);
				assertNotNull(previousFilter.elementFilterRules);
				if (previousFilter.elementFilterRules != null) {
					assertEquals(1, previousFilter.elementFilterRules.size());
					CssElementFilterRule rule;
					if (previousFilter.elementFilterRules.size() > 0) {
						rule = previousFilter.elementFilterRules.get(0);
						assertEquals(rule.type, CssElementFilterRule.Type.TAG_EQUALS);
						assertEquals(rule.check, "aaa");
					}
					
				}
			}
			
			assertNotNull(filter.elementFilterRules);
			if (filter.elementFilterRules != null) {
				assertEquals(1, filter.elementFilterRules.size());
				CssElementFilterRule rule;
				if (filter.elementFilterRules.size() > 0) {
					rule = filter.elementFilterRules.get(0);
					assertEquals(rule.type, CssElementFilterRule.Type.TAG_EQUALS);
					assertEquals(rule.check, "bbb");
				}
				
			}
			
		}

    }

	@Test
    public void tagParentOfTagTest()
    {
    	
		List<CssElementFilter> filters = CssSelectorParser.parse("aaa > bbb");
		
		assertEquals(1, filters.size());
		
		if (filters.size() > 0) {
			CssElementFilter filter = filters.get(0);
		
			assertNotNull(filter.previousFilter);
			assertEquals(CssElementFilter.PreviousFilterRule.IS_PARENT, filter.rule);
			
			if (filter.previousFilter != null) {
				CssElementFilter previousFilter = filter.previousFilter;
				assertNull(previousFilter.previousFilter);
				assertNull(previousFilter.rule);
				assertNotNull(previousFilter.elementFilterRules);
				if (previousFilter.elementFilterRules != null) {
					assertEquals(1, previousFilter.elementFilterRules.size());
					CssElementFilterRule rule;
					if (previousFilter.elementFilterRules.size() > 0) {
						rule = previousFilter.elementFilterRules.get(0);
						assertEquals(rule.type, CssElementFilterRule.Type.TAG_EQUALS);
						assertEquals(rule.check, "aaa");
					}
					
				}
			}
			
			assertNotNull(filter.elementFilterRules);
			if (filter.elementFilterRules != null) {
				assertEquals(1, filter.elementFilterRules.size());
				CssElementFilterRule rule;
				if (filter.elementFilterRules.size() > 0) {
					rule = filter.elementFilterRules.get(0);
					assertEquals(rule.type, CssElementFilterRule.Type.TAG_EQUALS);
					assertEquals(rule.check, "bbb");
				}
				
			}
			
		}

    }

	@Test
    public void tagPreviousSiblingOfTagTest()
    {
    	
		List<CssElementFilter> filters = CssSelectorParser.parse("aaa + bbb");
		
		assertEquals(1, filters.size());
		
		if (filters.size() > 0) {
			CssElementFilter filter = filters.get(0);
		
			assertNotNull(filter.previousFilter);
			assertEquals(CssElementFilter.PreviousFilterRule.PREVIOUS_SIBLING, filter.rule);
			
			if (filter.previousFilter != null) {
				CssElementFilter previousFilter = filter.previousFilter;
				assertNull(previousFilter.previousFilter);
				assertNull(previousFilter.rule);
				assertNotNull(previousFilter.elementFilterRules);
				if (previousFilter.elementFilterRules != null) {
					assertEquals(1, previousFilter.elementFilterRules.size());
					CssElementFilterRule rule;
					if (previousFilter.elementFilterRules.size() > 0) {
						rule = previousFilter.elementFilterRules.get(0);
						assertEquals(rule.type, CssElementFilterRule.Type.TAG_EQUALS);
						assertEquals(rule.check, "aaa");
					}
					
				}
			}
			
			assertNotNull(filter.elementFilterRules);
			if (filter.elementFilterRules != null) {
				assertEquals(1, filter.elementFilterRules.size());
				CssElementFilterRule rule;
				if (filter.elementFilterRules.size() > 0) {
					rule = filter.elementFilterRules.get(0);
					assertEquals(rule.type, CssElementFilterRule.Type.TAG_EQUALS);
					assertEquals(rule.check, "bbb");
				}
				
			}
			
		}

    }

	@Test
    public void tagNextSiblingOfTagTest()
    {
    	
		List<CssElementFilter> filters = CssSelectorParser.parse("aaa ~ bbb");
		
		assertEquals(1, filters.size());
		
		if (filters.size() > 0) {
			CssElementFilter filter = filters.get(0);
		
			assertNotNull(filter.previousFilter);
			assertEquals(CssElementFilter.PreviousFilterRule.NEXT_SIBLING, filter.rule);
			
			if (filter.previousFilter != null) {
				CssElementFilter previousFilter = filter.previousFilter;
				assertNull(previousFilter.previousFilter);
				assertNull(previousFilter.rule);
				assertNotNull(previousFilter.elementFilterRules);
				if (previousFilter.elementFilterRules != null) {
					assertEquals(1, previousFilter.elementFilterRules.size());
					CssElementFilterRule rule;
					if (previousFilter.elementFilterRules.size() > 0) {
						rule = previousFilter.elementFilterRules.get(0);
						assertEquals(rule.type, CssElementFilterRule.Type.TAG_EQUALS);
						assertEquals(rule.check, "aaa");
					}
					
				}
			}
			
			assertNotNull(filter.elementFilterRules);
			if (filter.elementFilterRules != null) {
				assertEquals(1, filter.elementFilterRules.size());
				CssElementFilterRule rule;
				if (filter.elementFilterRules.size() > 0) {
					rule = filter.elementFilterRules.get(0);
					assertEquals(rule.type, CssElementFilterRule.Type.TAG_EQUALS);
					assertEquals(rule.check, "bbb");
				}
				
			}
			
		}

    }

	@Test
    public void tagNextSiblingOfTagWithAttributesTest()
    {
    	
		List<CssElementFilter> filters = CssSelectorParser.parse("aaa[attr] ~ bbb[attr2]");
		
		assertEquals(1, filters.size());
		
		if (filters.size() > 0) {
			CssElementFilter filter = filters.get(0);
		
			assertNotNull(filter.previousFilter);
			assertEquals(CssElementFilter.PreviousFilterRule.NEXT_SIBLING, filter.rule);
			
			if (filter.previousFilter != null) {
				CssElementFilter previousFilter = filter.previousFilter;
				assertNull(previousFilter.previousFilter);
				assertNull(previousFilter.rule);
				assertNotNull(previousFilter.elementFilterRules);
				if (previousFilter.elementFilterRules != null) {
					assertEquals(2, previousFilter.elementFilterRules.size());
					CssElementFilterRule rule;
					if (previousFilter.elementFilterRules.size() > 0) {
						rule = previousFilter.elementFilterRules.get(0);
						assertEquals(rule.type, CssElementFilterRule.Type.TAG_EQUALS);
						assertEquals(rule.check, "aaa");
					}
					if (previousFilter.elementFilterRules.size() > 1) {
						rule = previousFilter.elementFilterRules.get(1);
						assertEquals(rule.type, CssElementFilterRule.Type.HAS_ATTRIBUTE);
						assertEquals(rule.attribute, "attr");
					}
					
				}
			}
			
			assertNotNull(filter.elementFilterRules);
			if (filter.elementFilterRules != null) {
				assertEquals(2, filter.elementFilterRules.size());
				CssElementFilterRule rule;
				if (filter.elementFilterRules.size() > 0) {
					rule = filter.elementFilterRules.get(0);
					assertEquals(rule.type, CssElementFilterRule.Type.TAG_EQUALS);
					assertEquals(rule.check, "bbb");
				}
				if (filter.elementFilterRules.size() > 1) {
					rule = filter.elementFilterRules.get(1);
					assertEquals(rule.type, CssElementFilterRule.Type.HAS_ATTRIBUTE);
					assertEquals(rule.attribute, "attr2");
				}
				
			}
			
		}

    }

	@Test
    public void tagOrTagTest()
    {
    	
		List<CssElementFilter> filters = CssSelectorParser.parse("aaa, bbb");
		
		assertEquals(2, filters.size());
		
		if (filters.size() > 0) {
			CssElementFilter filter = filters.get(0);
		
			assertNull(filter.previousFilter);
			assertNull(filter.rule);
			
			assertNotNull(filter.elementFilterRules);
			if (filter.elementFilterRules != null) {
				assertEquals(1, filter.elementFilterRules.size());
				CssElementFilterRule rule;
				if (filter.elementFilterRules.size() > 0) {
					rule = filter.elementFilterRules.get(0);
					assertEquals(rule.type, CssElementFilterRule.Type.TAG_EQUALS);
					assertEquals(rule.check, "aaa");
				}
				
			}
			
		}

		if (filters.size() > 1) {
			CssElementFilter filter = filters.get(1);
		
			assertNull(filter.previousFilter);
			assertNull(filter.rule);
			
			assertNotNull(filter.elementFilterRules);
			if (filter.elementFilterRules != null) {
				assertEquals(1, filter.elementFilterRules.size());
				CssElementFilterRule rule;
				if (filter.elementFilterRules.size() > 0) {
					rule = filter.elementFilterRules.get(0);
					assertEquals(rule.type, CssElementFilterRule.Type.TAG_EQUALS);
					assertEquals(rule.check, "bbb");
				}
				
			}
			
		}

    }

	@Test
    public void tagOrTagWithClassAndAttributesTest()
    {
    	
		List<CssElementFilter> filters = CssSelectorParser.parse("zzz aaa[ attr = 'value' ], ccc > bbb.cls[attr2 $= .png]");
		
		assertEquals(2, filters.size());
		{
			CssElementFilter filter = filters.get(0);
			assertNotNull(filter.previousFilter);
			assertEquals(PreviousFilterRule.IS_ANCESTOR, filter.rule);
			{
				CssElementFilter previousFilter = filter.previousFilter;
				assertNull(previousFilter.previousFilter);
				assertNull(previousFilter.rule);
				assertNotNull(previousFilter.elementFilterRules);
				assertEquals(1, previousFilter.elementFilterRules.size());
				{
					CssElementFilterRule rule = previousFilter.elementFilterRules.get(0);
					assertEquals(CssElementFilterRule.Type.TAG_EQUALS, rule.type);
					assertEquals("zzz", rule.check);
				}
			}
			assertNotNull(filter.elementFilterRules);
			assertEquals(2, filter.elementFilterRules.size());
			CssElementFilterRule rule;
			{
				rule = filter.elementFilterRules.get(0);
				assertEquals(rule.type, CssElementFilterRule.Type.TAG_EQUALS);
				assertEquals(rule.check, "aaa");
			}
			{
				rule = filter.elementFilterRules.get(1);
				assertEquals(rule.type, CssElementFilterRule.Type.ATTRUBUTE_EQUALS);
				assertEquals("attr", rule.attribute);
				assertEquals("value", rule.check);
			}
		}

		{
			CssElementFilter filter = filters.get(1);
		
			assertNotNull(filter.previousFilter);
			assertEquals(PreviousFilterRule.IS_PARENT, filter.rule);
			{
				CssElementFilter previousFilter = filter.previousFilter;
				assertNull(previousFilter.previousFilter);
				assertNull(previousFilter.rule);
				assertNotNull(previousFilter.elementFilterRules);
				assertEquals(1, previousFilter.elementFilterRules.size());
				{
					CssElementFilterRule rule = previousFilter.elementFilterRules.get(0);
					assertEquals(CssElementFilterRule.Type.TAG_EQUALS, rule.type);
					assertEquals("ccc", rule.check);
				}
			}
			
			assertNotNull(filter.elementFilterRules);
			if (filter.elementFilterRules != null) {
				assertEquals(3, filter.elementFilterRules.size());
				CssElementFilterRule rule;
				{
					rule = filter.elementFilterRules.get(0);
					assertEquals(CssElementFilterRule.Type.TAG_EQUALS, rule.type);
					assertEquals("bbb", rule.check);
				}
				{
					rule = filter.elementFilterRules.get(1);
					assertEquals(CssElementFilterRule.Type.HAS_CLASS, rule.type);
					assertEquals("cls", rule.check);
				}
				{
					rule = filter.elementFilterRules.get(2);
					assertEquals(CssElementFilterRule.Type.ATTRIBUTE_ENDS_WITH, rule.type);
					assertEquals("attr2", rule.attribute);
					assertEquals(".png", rule.check);
				}
				
			}
			
		}

    }


}
