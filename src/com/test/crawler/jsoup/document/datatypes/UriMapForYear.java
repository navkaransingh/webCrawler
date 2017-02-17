/**
 * 
 */
package com.test.crawler.jsoup.document.datatypes;

import java.text.DateFormatSymbols;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

/**
 * @author SINGHNAV
 *
 */
public class UriMapForYear {
	
	private Map<String, String>uriMap;

	public UriMapForYear() {
		uriMap = new HashMap<String, String>();
		String[] months = new DateFormatSymbols().getShortMonths();
		for (String month : months) {
			uriMap.put(month, null);
		}
	}

	public Map<String, String> getUriMap(String emailData, Integer year) {
		Iterator it = uriMap.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        Pattern p = Pattern.compile("(?s)<td class=\"date\">"+pair.getKey()+" "+year+"</td>\\s(.*?)Thread");
		    Matcher m = p.matcher(emailData);
	        while (m.find()) {
	        	String hrefString = m.group(1);
	        	Document doc = Jsoup.parse(hrefString);
	        	Elements linksOnPage = doc.select("a[href]");
	        	for (Element page : linksOnPage) {
	        		uriMap.put(pair.getKey().toString(), page.attr("href"));
                }
	        }
	    }
		
		return uriMap;
	}

	public void setUriMap(Map<String, String> uriMap) {
		this.uriMap = uriMap;
	}

	public Map<String, String> getUriMap() {
		return uriMap;
	}
	
	

}
