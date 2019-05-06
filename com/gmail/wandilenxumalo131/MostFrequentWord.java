package com.gmail.wandilenxumalo131;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class MostFrequentWord {
	private String theWord;
	private Vector<String> comparedWords;
	private Vector<Integer> numberOfViews;
	private FileInputStream fis;
	private Scanner fileScan;

	public MostFrequentWord(File file) {

		comparedWords = new Vector<String>();
		numberOfViews = new Vector<Integer>();
		readFile(file);
	}

	public void readFile(File file) {
		try {
			fis = new FileInputStream(file);
			fileScan = new Scanner(fis);
			searchFile();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			fileScan.close();
		}
	}
	
	public void searchFile() {
		while (fileScan.hasNext()) {
			theWord = getTestWord();

			if (comparedWords.contains(theWord)) {
				int index = comparedWords.indexOf(theWord);
				numberOfViews.set(index, numberOfViews.get(index) + 1);
			} else {
				comparedWords.add(theWord);
				numberOfViews.add(1);
			}
		}
	}
	
	public String getTestWord() {
		return fileScan.next();
	}

	public void readFile(String filePath) {

		try {
			fis = new FileInputStream(filePath);
			fileScan = new Scanner(fis);

			while (fileScan.hasNext()) {
				theWord = fileScan.next();
				compareWords(theWord);
			}

			fis.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			fileScan.close();
		}
	}

	public void compareWords(String wordThatIsCompared) {

		if (comparedWords.contains(wordThatIsCompared)) {
			int index = comparedWords.indexOf(wordThatIsCompared);
			numberOfViews.set(index, numberOfViews.get(index) + 1);
		} else {
			comparedWords.add(theWord);
			numberOfViews.add(1);
		}
	}

	public Vector<String> getCompareWords() {
		return comparedWords;
	}

	public String getWord() {
		String word = comparedWords.get(0);
		int tempNum = numberOfViews.get(0);

		for (int i = 0; i < comparedWords.size(); i++) {
			if (numberOfViews.get(i) > tempNum) {
				word = comparedWords.get(i);
				tempNum = numberOfViews.get(i);
			}
		}
		return word;
	}

	public int getNumberOfViews(String mostFrequentWord) {
		int tempNum = numberOfViews.get(0);

		for (int i = 0; i < comparedWords.size(); i++) {
			if (numberOfViews.get(i) > tempNum && comparedWords.contains(mostFrequentWord)) {
				tempNum = numberOfViews.get(i);
			}
		}

		return tempNum;
	}

	public String toString() {
		return "Most frequent word: " + getWord() + " was seen " + getNumberOfViews(getWord()) + " times";
	}
	
	public static void main(String [] args) {
		File file = new File("/home/wonderboy/eclipse-workspace/WordCounter/src/com/gmail/wandilenxumalo131/resources/IOTest");
		System.out.println(new MostFrequentWord(file).toString());;
	}
}
