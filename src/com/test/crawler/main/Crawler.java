package com.test.crawler.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import com.test.crawler.downloader.DocumentDownloader;
import com.test.crawler.downloader.interfaces.IDownloader;
import com.test.crawler.jsoup.document.Document;
import com.test.crawler.jsoup.document.DocumentParser;
import com.test.crawler.jsoup.document.datatypes.UriMapForYear;
import com.test.crawler.jsoup.document.interfaces.IParser;

/**
 * @author singhnav
 */

/*
 * Revision History:
 * 
 * *****************************************************************************
 * 1. CR: Date: Feb 6, 2017 Author: singhnav Description:
 * *****************************************************************************
 */

public class Crawler {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String baseUri = "http://mail-archives.apache.org/mod_mbox/maven-users/";
		Integer year = 2017;
		
		Crawler crawler = new Crawler();
		List<UriMapForYear> uriMapforYear = crawler.parseCrawledDocument(baseUri,year);
		
		crawler.downloadCrawledDocument(baseUri, uriMapforYear);

	}

	protected void downloadCrawledDocument(String baseUri,List<UriMapForYear> uriMapforYear) throws IOException {
		IDownloader downloader = new DocumentDownloader(baseUri,uriMapforYear);
		downloader.downloadCrawledFiles();
	}

	protected List<UriMapForYear> parseCrawledDocument(String baseUri, Integer year) throws IOException {
		IParser parser = new DocumentParser(baseUri);
		List<UriMapForYear> uriMapforYear = parser.parseCrawledDocumentForYear(year);
		return uriMapforYear;
	}

}
