package com.test.crawler.jsoup.document;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

public class Document {
	
	private String uri;

	public Document(String uri) {
		super();
		this.uri = uri;
	}
	
	public org.jsoup.nodes.Document getProcessedPage() throws IOException{
		return processPage();
	}
	
	public Connection.Response getHtmlPage() throws IOException{
		return processHtmlPage();
	}

	private Response processHtmlPage() throws IOException {
		return Jsoup.connect(uri).execute();
	}

	private org.jsoup.nodes.Document processPage() throws IOException {
		return Jsoup.connect(uri).get();
	}

}
