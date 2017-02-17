/**
 * 
 */
package com.test.crawler.jsoup.document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Node;

import com.test.crawler.jsoup.document.datatypes.UriMapForYear;
import com.test.crawler.jsoup.document.interfaces.IParser;


/**
 * @author SINGHNAV
 *
 */
public class DocumentParser implements IParser {
	
	private String baseUri;
	
	private Document document;

	public DocumentParser(String baseUri) {
		super();
		this.baseUri = baseUri;
		document = new Document(baseUri);
	}
	
	@Override
	public List<UriMapForYear> parseCrawledDocumentForYear(Integer year) throws IOException{
		List<String> mailsPerYear = extractDocumentData();
		List<String> emailForSpecificYear = extractMailDataForSpecificYear(mailsPerYear,year);
		List<UriMapForYear> uriMapList = extractUriForAllMonths(emailForSpecificYear,year);
		return uriMapList;
	}

	private List<UriMapForYear> extractUriForAllMonths(List<String> emailForSpecificYear, Integer year) {
		List<UriMapForYear> uriMapList = new ArrayList<UriMapForYear>();
		UriMapForYear uriMapForYear = new UriMapForYear(); 
		for(String emailData : emailForSpecificYear){
			Map<String, String>uriMap = uriMapForYear.getUriMap(emailData,year);
			uriMapList.add(uriMapForYear);
		}
		return uriMapList;
	}

	private List<String> extractDocumentData() throws IOException {
		org.jsoup.nodes.Document jDocument = document.getProcessedPage();
		List<Node> documentNodes = jDocument.childNodes();
		List<String> mailsPerYear = extractMailsPerYear(documentNodes);
		return mailsPerYear;
	}

	private List<String> extractMailDataForSpecificYear(List<String> mailsPerYear, Integer year) {
		List<String> emailForSpecificYear = new LinkedList<String>();
		for(String mailsData : mailsPerYear){
			String mailsText = mailsData.toString();
			Pattern p = Pattern.compile(SPECIFIC_YEAR_REG_EX_START+year+SPECIFIC_YEAR_REG_EX_END, Pattern.MULTILINE);
		    Matcher m = p.matcher(mailsText);
	        while (m.find()) {
	        	emailForSpecificYear.add(m.group(1));
	        	System.out.println(m.group(1));
	        }
		}
		return emailForSpecificYear;
	}

	private List<String> extractMailsPerYear(List<Node> documentNodes) {
		List<String> emailPerYear = new LinkedList<String>();
		for(Node docNode : documentNodes){
			String docText = docNode.toString();
			Pattern p = Pattern.compile(YEAR_REG_EX, Pattern.MULTILINE);
		    Matcher m = p.matcher(docText);
	        while (m.find()) {
	        	emailPerYear.add(m.group(1));
	        }
		}
		return emailPerYear;
	}

	
}
