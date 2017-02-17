/**
 * 
 */
package com.test.crawler.downloader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.jsoup.Connection;

import com.test.crawler.downloader.interfaces.IDownloader;
import com.test.crawler.jsoup.document.Document;
import com.test.crawler.jsoup.document.datatypes.UriMapForYear;

/**
 * @author SINGHNAV
 *
 */
public class DocumentDownloader implements IDownloader {
	
	private String baseUri;
	
	private List<UriMapForYear> uriMapforYear;

	public DocumentDownloader(String baseUri, List<UriMapForYear> uriMapforYear) {
		this.baseUri = baseUri;
		this.uriMapforYear = uriMapforYear;
	}
	
	@Override
	public void downloadCrawledFiles() throws IOException{
		Map<String,String> targetUri =  prepareTargetUris();
		processTargetUri(targetUri);
	}

	private void processTargetUri(Map<String, String> targetUri) throws IOException {
		Iterator it = targetUri.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        Document document = new Document(pair.getValue().toString());
	        Connection.Response html  = document.getHtmlPage();
	        final org.jsoup.nodes.Document doc = html.parse();

	        final File f = new File(pair.getKey().toString()+".html");
	        FileUtils.writeStringToFile(f, doc.outerHtml(), "UTF-8");
	    }
		
	}

	private Map<String,String> prepareTargetUris() {
		Map<String,String> uriMap = new HashMap<String,String>();
		for(UriMapForYear uriMapYr : uriMapforYear){
			Iterator it = uriMapYr.getUriMap().entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        if(pair.getValue() != null){
		        	uriMap.put(pair.getKey().toString() , baseUri+pair.getValue());
		        }
		    }
		}
		return uriMap;
	}

}
