package com.PDFReader;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PDFReader {

	
	static String content = "Selenium";
	
	/*public void verifyPDFContent() throws IOException {
		
		PDDocument pd = PDDocument.load(new File("C:\\Users\\a631020\\Documents\\SeleniumTrainingLyncRecording\\AutomationFramework.pdf"));
		System.out.println("Number of Pages:" + pd.getNumberOfPages());
		
		PDFTextStripper pdf = new PDFTextStripper();
		String output = pdf.getText(pd);
		
		if(output.contains(content)) {
			System.out.println("Data is here: "+output);
		}
		else {
			System.out.println("Not here.");
		}
	}*/
	
	/*public void verifyURLPDFContent(String strURL, String text) throws IOException {
		
		URL url =  new URL(strURL);
		
		BufferedInputStream file = new BufferedInputStream(url.openStream());
		
		PDDocument pd = PDDocument.load(file);
		System.out.println("Number of Pages:" + pd.getNumberOfPages());
		
		PDFTextStripper pdf = new PDFTextStripper();
		String output = pdf.getText(pd);
		
		if(output.contains(text)) {
			System.out.println("Data is here: "+output);
		}
		else {
			System.out.println("Not here.");
		}
	}*/
	
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		PDFReader reader = new PDFReader();
		//reader.verifyPDFContent();
		//reader.verifyURLPDFContent("http://www.princexml.com/howcome/2016/samples//malthus/essay.pdf", "EASSY");

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\a631020\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.princexml.com/samples/");
		driver.findElement(By.xpath("//img[@src='http://www.princexml.com/howcome/2016/samples/magic6/magic-2-185.png']")).click();
		
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.16.147.12", 8080));

		 URL url = new URL(driver.getCurrentUrl());
		 
		 HttpURLConnection uc = (HttpURLConnection) url.openConnection(proxy);
		uc.setConnectTimeout(5000);
		
		//URL url =  new URL(driver.getCurrentUrl());
		
		BufferedInputStream file = new BufferedInputStream(url.openStream());
		
		PDFParser parser = new PDFParser(file);
	    parser.parse();
	    
	    COSDocument cosDoc = parser.getDocument();
		
		PDDocument pd = new PDDocument(cosDoc);
		System.out.println("Number of Pages:" + pd.getNumberOfPages());
		
		PDFTextStripper pdf = new PDFTextStripper();
		String output = pdf.getText(pd);
		
		if(output.contains(content)) {
			System.out.println("Data is here: ");
		}
		else {
			System.out.println("Not here.");
		}
	}

}
