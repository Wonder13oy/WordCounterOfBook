package com.gmail.wandilenxumalo131;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class MostFrequentWord {
	private String theWord;
	private int numberOfViews;
	private File file;
	private FileReader fr;
	private BufferedReader br;
	private StringTokenizer stringToken;
	
	public MostFrequentWord() {
		theWord = "";
		file = new File("/home/wonderboy/eclipse-workspace/WordCounter/src/com/gmail/wandilenxumalo131/IOTest");
		
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			
			while((theWord = br.readLine()) != null) {
				stringToken = new StringTokenizer(theWord, " ");
				
				for(int i = 0;i < wordcount(theWord); i++) {
					System.out.println(stringToken.nextToken());
				}					
			}
		} catch (IOException ioe) {
			// TODO: handle exception
			ioe.printStackTrace();
		}	
	}
	
	public int wordcount(String string)//Googled this method
    {
	  int count=0;

        char ch[]= new char[string.length()];   
        for(int i=0;i<string.length();i++)
        {
            ch[i]= string.charAt(i);
            if( ((i>0)&&(ch[i]!=' ')&&(ch[i-1]==' ')) || ((ch[0]!=' ')&&(i==0)) )
            	count++;
        }
        return count;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MostFrequentWord();
	}

}
