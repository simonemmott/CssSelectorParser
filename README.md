# CssSelectorParser
The CssSelectorParser simple light weight utility to parse CSS selectors into structured data for use elsewhere
It parses the given css selector in a single pass through the string and generates a list of element filters.
Each element filter can have previous filters which encapsulate the css rules of contained in, is parent, is sibling of etc.
The list represents the ',' operator of css to identify several rules in a single css selector.
Each element filter defines a list of rules such as tag equals value or attribute ends with value etc.

The resultant structure data can be applied to any hierarchy of data where the type of each node in the hierarchy is identified
by a string value and the nodes contain attributes identifiable by name and whose values can be encapsulated in a string. This is 
the basic structure of all xml and html documents and most data in an object hierarchy.

### License

[GNU GENERAL PUBLIC LICENSE v3](http://fsf.org/)

## Basic Example

The CssSelectorParser parses CSS selectors into structured data that is simple to interrogate in java.

This code:
```java
String cssSelector = ".myClass > img[src$=.jpg], ol#thisId + li[data-myData='some data value']";

List<CssElementFilter> filters = CssSelectorParser.parse(cssSelector);
```
Parses the CSS selector `.myClass > img[src$=.jpg], ol#thisId + li[data-myData='some data value']` into
a set of structured data that can be easily examined in java.

## Getting Started

Download a jar file containing the latest version or fork this project and install in your IDE

Maven users can add this project using the following additions to the pom.xml file.
```maven
<dependencies>
    ...
    <dependency>
        <groupId>com.k2</groupId>
        <artifactId>CssSelectorParser</artifactId>
        <version>0.1.0</version>
    </dependency>
    ...
</dependencies>
```

## Working With CssSelectorParser
The CssSelectorParser has a very simple API. Parsing a CSS selector is a simple as calling the static method
`CssSelectorParser.parse(...)` passing in the CSS selector as a string.

e.g.
```java
CssSelectorParser.parse("audio[src$=.ogg]")
```
The result of the call to the parse static method is a `List` of instances of `CssElementFilter`

Each element filter defines the filter rules to be applied to an element 
e.g. 
The CSS selector `#myId` identifies the rule 
1. element id equals myId

And CSS Selector `img.myClass[src$=.png]` identifies the rules

1. element with tag `img`
1. element with class `myClass`
1. element with attribute name `src` and ending with `.png`

An element filter can have preceding filters. Preseding filters are applied to the elements above and beside the element in the 
node hierarchy. For example the CSS selector `div > p` defines 2 element filters

1. Elements with tag `p`
1. With a parent with tag `div`

This can be extended indefinitely e.g. CSS selector `div > p h1` defines 3 filters

1. Elements with tag `h1`
1. Within an element with tag `p`
1. That is the direct child of an element with tag `div`

This construction can be thought of as a chain of element filters. The chain of element filters can be navigated through the
`CssElementFilter#previousFilter` field and the type of the link in the chain is defined in the `CssElementFilter#rule`

The element filter rule is an enumeration with the following possible values
1. IS_ANCESTOR
1. IS_PARENT
1. PREVIOUS_SIBLING
1. NEXT_SIBLING

CSS selectors can define many element filters (or filter chains) and a positive match should be generated if any of the filter 
chains match the element in question. e.g. the CSS selector `div, span, table` matches all divisions **Or** all spans **Or** all tables.

This concept is encapsuleted in the structured data genrated by CssSelectorParser as multiple element filters *potentially the 
start of a filter chain* in the list returned by the call to `CssSelectorParser#parse(...)`

## Example
The source below:
```java
String cssSelector = ".myClass > img[src$=.jpg], ol#thisId + li[data-myData='some data value']";

List<CssElementFilter> filters = CssSelectorParser.parse(cssSelector);

System.out.println();
System.out.println("The CSS selector: "+cssSelector+" was parsed to generate");
System.out.println();		
System.out.println(
		filters.get(0).previousFilter.elementFilterRules.get(0).type + " " + 
		filters.get(0).previousFilter.elementFilterRules.get(0).check);
System.out.println(filters.get(0).rule + " of");
System.out.println(
		filters.get(0).elementFilterRules.get(0).type + " " + 
		filters.get(0).elementFilterRules.get(0).check + " and");
System.out.println(
		filters.get(0).elementFilterRules.get(1).attribute + " " + 
		filters.get(0).elementFilterRules.get(1).type + " " + 
		filters.get(0).elementFilterRules.get(1).check);
System.out.println("Or");
System.out.println(
		filters.get(1).previousFilter.elementFilterRules.get(0).type + " " + 
		filters.get(1).previousFilter.elementFilterRules.get(0).check + " and");
System.out.println(
		filters.get(1).previousFilter.elementFilterRules.get(1).type + " " + 
		filters.get(1).previousFilter.elementFilterRules.get(1).check);
System.out.println(filters.get(1).rule + " of");
System.out.println(
		filters.get(1).elementFilterRules.get(0).type + " " + 
		filters.get(1).elementFilterRules.get(0).check);
System.out.println(
		filters.get(1).elementFilterRules.get(1).attribute + " " + 
		filters.get(1).elementFilterRules.get(1).type + " " + 
		filters.get(1).elementFilterRules.get(1).check);
```
produces the follwing output
```text
The CSS selector: .myClass > img[src$=.jpg], ol#thisId + li[data-myData='some data value'] was parsed to generate

HAS_CLASS myClass
IS_PARENT of
TAG_EQUALS img and
src ATTRIBUTE_ENDS_WITH .jpg
Or
TAG_EQUALS ol and
ID_EQUALS thisId
PREVIOUS_SIBLING of
TAG_EQUALS li
data-myData ATTRUBUTE_EQUALS some data value
```









