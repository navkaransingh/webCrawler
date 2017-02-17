/**
 * 
 */
package com.test.crawler.jsoup.document.interfaces;

import java.io.IOException;
import java.util.List;



/**
 * @author SINGHNAV
 *
 */
public interface IParser {
	
	public static final String YEAR_REG_EX = "(?s)<table class=\"year\">\\s(.*?)</table> ";
	
	
	public static final String SPECIFIC_YEAR_REG_EX_START = "(?s)<th colspan=\"3\">Year ";
	public static final String SPECIFIC_YEAR_REG_EX_END = "</th>\\s(.*?)</tbody> ";

	public List parseCrawledDocumentForYear(Integer year) throws IOException;
	
}
