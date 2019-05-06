package com.gmail.wandilenxumalo131;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Vector;

public class ScrabbleScoring{
	
	private FileInputStream fis;
	private Scanner fileScan;
	private Vector<String> words;
	private Vector<Integer> wordScore;
	private int [] scoringIndex;
	private Hashtable<Character, Integer> scoreTable;
		
	public ScrabbleScoring(File file) {
		
		scoreTable = new Hashtable<Character, Integer>(25);
		scoringIndex = new int [] {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
		words = new Vector<String>();
		wordScore = new Vector<Integer>();
		loadScoreTable();
		readFileForScrabble(file);
	}
	
	private void loadScoreTable() {
		int j = 0;
		for (char i = 'a' ; i <= 'z' ; i++) {//Googled this method
	        scoreTable.put(i, scoringIndex[j]);
	        j++;
	    }
	}
	
	private void readFileForScrabble(File file) {
		try {
			fis = new FileInputStream(file);
			fileScan = new Scanner(fis);
			
			while (fileScan.hasNext()) {
				String string = (String) fileScan.next().toLowerCase();
				int sum = 0;
				
				for (int i = 0; i < string.length(); i++) {
					
					if(scoreTable.containsKey(string.charAt(i))) {
						sum += scoreTable.get(string.charAt(i));
					}
					
				}
				
				words.add(string);
				wordScore.add(sum);
			}
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
			
	}
	
	public String getHighestScoringWord() {
		String word = words.get(0);
		int tempNum = wordScore.get(0);

		for (int i = 0; i < words.size(); i++) {
			if (wordScore.get(i) > tempNum) {
				word = words.get(i);
				tempNum = wordScore.get(i);
			}
		}
		return word;
	}
	
	public int getHighestScore() {
		int tempNum = wordScore.get(0);

		for (int i = 0; i < words.size(); i++) {
			if (wordScore.get(i) > tempNum) {
				tempNum = wordScore.get(i);
			}
		}

		return tempNum;
	}
	
	public String toString() {
		return "Highest scoring word: " + getHighestScoringWord() + " scored: " + getHighestScore();
	}
}
