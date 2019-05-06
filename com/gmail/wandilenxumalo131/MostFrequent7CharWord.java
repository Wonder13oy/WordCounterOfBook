package com.gmail.wandilenxumalo131;

import java.io.File;

public class MostFrequent7CharWord extends MostFrequentWord{
	private String word7Char;

	public MostFrequent7CharWord(File file) {
		super(file);
	}

	public String get7CharWord() {
		word7Char = getWord();
		
		if(word7Char.length() == 7) {
			return word7Char;
		}
		
		return "NO 7 CHARACTER WORD FOUND!";
	}
	
	public int getNumberOfViews() {
		return getNumberOfViews(get7CharWord());
	}
	
	public String toString() {
		return "Most Frequent 7 character word: " + get7CharWord() + " was seen " + getNumberOfViews() + " times";
	}	
}
