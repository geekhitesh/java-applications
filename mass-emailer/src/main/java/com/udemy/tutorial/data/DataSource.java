package com.udemy.tutorial.data;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.udemy.tutorial.constants.FileConstants;

public class DataSource {

	private CountDownLatch countDownLatch;

	private static List<List<String>> emailListReference;
	private static List<List<String>> emailListSent;
	private static Predicate<? super List<String>> p1 = (s) -> !emailListSent.contains(s) && ! (s.get(0) == "");

	public DataSource() {

	}

	public DataSource(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}

	private void getEmailListReference() {
		emailListReference = getCSVContent(FileConstants.EMAIL_INPUT_FILE_NAME);
		System.out.println("Reference List size:"+emailListReference.size());
	}

	private void getEmailListSent() {

		emailListSent = getCSVContent(FileConstants.EMAIL_OUTPUT_FILE_NAME);
		System.out.println("Sent List size:"+emailListSent.size());
	}

	private List<List<String>> getCSVContent(String fileName) {

		URL url = getClass().getResource(fileName);
		File file = new File(fileName);
		List<List<String>> data = null;

		try {
			// Create an object of file reader
			// class with CSV file as a parameter.
			//FileReader filereader = new FileReader(url.getPath());
			
			FileReader filereader = new FileReader(fileName);
			// create csvReader object and skip first Line
			CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
			List<String[]> allData = csvReader.readAll();

			//data = allData.stream().map(Arrays::asList).flatMap(List::stream).collect(Collectors.toList());
			data = allData.stream().map(Arrays::asList).collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// System.out.println(url.toString());
		return data;

	}

	public List<List<String>> getEmailListPending() throws InterruptedException {
		
		//Thread.sleep(3000);
		getEmailListReference();
		getEmailListSent();

		List<List<String>> emailPending = emailListReference.stream()
											.filter(p1)
											.limit(FileConstants.TOTAL_EMAILS_IN_BATCH)
											.collect(Collectors.toList());

		countDownLatch.countDown();
		
		return emailPending;
	}

}
